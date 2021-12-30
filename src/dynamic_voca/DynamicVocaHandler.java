
package dynamic_voca;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import exception.InputException;

public class DynamicVocaHandler {
	private static DynamicVocaHandler inst = null;
	private Set<Voca> vocaSet;
	private QuestionList mQuestionList;
	private String selectedFileName;
	private boolean allowEffectSound;
	private boolean allowColorByMistakeCnt;
	private int limitTime;
	private Voca curQuestionVoca;
	private boolean isAnswer;
	private int questionNum;
	private int mistakeCntForShow;

	File vocaSetDirectory = new File("sets");
	File questionListDirectory = new File("lists");

	public static DynamicVocaHandler createDynamicVocaHandlerInst() {
		if (inst == null)
			inst = new DynamicVocaHandler();
		return inst;
	}

	private DynamicVocaHandler() {
		vocaSet = new HashSet<>(3000);
		if (!vocaSetDirectory.exists())
			vocaSetDirectory.mkdir();
		if (!questionListDirectory.exists())
			questionListDirectory.mkdir();
	}

	public void setQuestionListForTest() {
		mQuestionList = new QuestionList();
		mQuestionList.setQuestionVocas(vocaSet);
		mQuestionList.shuffleQuestionVocas();
	}

	public void setQuestionListForRetest() {
		mQuestionList.setQusetionVocasByReviewVocas();
	}

	public String getQuestion() {
		curQuestionVoca = mQuestionList.getQuestionVoca();
		questionNum = mQuestionList.getQuestionNum();
		if (curQuestionVoca == null)
			return "테스트 완료";
		return curQuestionVoca.getEnglish();
	}

	public int getQuestionNum() {
		return questionNum;
	}

	public void setNextQuestion() {
		mQuestionList.addQuestionNum();
	}

	public String testVocaInfo(String answer) {
		StringBuffer testInfo = new StringBuffer();
		String question = curQuestionVoca.getEnglish();
		Vector<String> koreans = curQuestionVoca.getKoreans();
		isAnswer = false;
		for (String kor : koreans) {
			if (answer.equals(kor) && !answer.equals("")) {
				if (allowEffectSound) {
					Sound buttonPressedSound = new Sound("correctEffectSound.mp3", false);
					buttonPressedSound.start();
				}
				isAnswer = true;
				mQuestionList.addCorrectCnt();
				break;
			}
		}
		if (!isAnswer) {
			if (allowEffectSound) {
				Sound buttonPressedSound = new Sound("incorrectEffectSound.mp3", false);
				buttonPressedSound.start();
			}
			mQuestionList.addInReviewVocas(curQuestionVoca);
			addMistakeCnt();
		}
		testInfo.append(mQuestionList.getQuestionNum()).append(". ").append(question);
		testInfo.append(" : ").append(koreans.get(0));
		if (!koreans.get(1).equals(""))
			testInfo.append(", ").append(koreans.get(1));
		if (!koreans.get(2).equals(""))
			testInfo.append(", ").append(koreans.get(2));
		testInfo.append("\n");
		return testInfo.toString();
	}

	public void addMistakeCnt() {
		Iterator<Voca> itr = vocaSet.iterator();
		while (itr.hasNext()) {
			Voca targetVoca = itr.next();
			if (targetVoca.equals(curQuestionVoca)) {
				targetVoca.addMistakeCnt();
				break;
			}
		}
	}

	public String testSituationInfo() {
		StringBuffer situationInfo = new StringBuffer();
		situationInfo.append("남은 단어 수: ").append(mQuestionList.getRemainedQuestionCnt()).append("\n\n");
		situationInfo.append("정답 수: ").append(mQuestionList.getCorrectCnt()).append("\n");
		situationInfo.append("오답 수: ").append(mQuestionList.getIncorrectCnt()).append("\n\n");
		situationInfo.append("정답률: ").append(mQuestionList.getCorrectRate()).append(" %\n");
		situationInfo.append("진행률: ").append(mQuestionList.getProcessRate()).append(" %");
		return situationInfo.toString();
	}

	public String appendVocaInfo(String eng, Vector<String> kors) {
		StringBuffer appendInfo = new StringBuffer();
		if (eng.equals("")) {
			appendInfo.append("단어를 입력해주세요!").append("\n");
			return appendInfo.toString();
		}
		if (kors.get(0).equals("")) {
			appendInfo.append("첫번째 의미를 입력해주세요!").append("\n");
			return appendInfo.toString();
		}
		Voca mVoca = new Voca(eng, kors);
		if (vocaSet.add(mVoca)) {
			appendInfo.append(eng).append(" : ").append(kors.get(0));
			if (!kors.get(1).equals(""))
				appendInfo.append(", ").append(kors.get(1));
			if (!kors.get(2).equals(""))
				appendInfo.append(", ").append(kors.get(2));
			appendInfo.append("\n");
			appendInfo.append("단어 추가 완료 ");
			appendInfo.append("[").append(vocaSet.size()).append("]");
			appendInfo.append("\n");
		} else {
			appendInfo.append(eng);
			appendInfo.append("는 이미 등록된 단어입니다!").append("\n");
		}
		return appendInfo.toString();
	}

	private String searchVocaInfo(String input) {
		StringBuffer searchInfo = new StringBuffer();
		Iterator<Voca> itr = vocaSet.iterator();
		if ((input.charAt(0) >= 65 && input.charAt(0) <= 90) || (input.charAt(0) >= 97 && input.charAt(0) <= 122)) {
			Voca findVoca = null;
			while (itr.hasNext()) {
				findVoca = itr.next();
				if (input.equals(findVoca.getEnglish())) {
					Vector<String> koreans = findVoca.getKoreans();
					searchInfo.append("파일명: ").append(selectedFileName).append("\n");
					searchInfo.append("[ ").append(findVoca.getEnglish()).append(" ]");
					searchInfo.append(" 오답수: ").append(findVoca.getMistakeCnt()).append("\n");
					searchInfo.append(koreans.get(0));
					if (!koreans.get(1).equals(""))
						searchInfo.append(", ").append(koreans.get(1));
					if (!koreans.get(2).equals(""))
						searchInfo.append(", ").append(koreans.get(2));
					searchInfo.append("\n");
					return searchInfo.toString();
				}
			}
			return searchInfo.toString();
		} else {
			Voca findVoca = null;
			while (itr.hasNext()) {
				findVoca = itr.next();
				Vector<String> koreans = findVoca.getKoreans();
				for (String kor : koreans) {