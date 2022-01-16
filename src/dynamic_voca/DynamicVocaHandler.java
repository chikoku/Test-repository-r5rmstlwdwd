
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
			return "�׽�Ʈ �Ϸ�";
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
		situationInfo.append("���� �ܾ� ��: ").append(mQuestionList.getRemainedQuestionCnt()).append("\n\n");
		situationInfo.append("���� ��: ").append(mQuestionList.getCorrectCnt()).append("\n");
		situationInfo.append("���� ��: ").append(mQuestionList.getIncorrectCnt()).append("\n\n");
		situationInfo.append("�����: ").append(mQuestionList.getCorrectRate()).append(" %\n");
		situationInfo.append("�����: ").append(mQuestionList.getProcessRate()).append(" %");
		return situationInfo.toString();
	}

	public String appendVocaInfo(String eng, Vector<String> kors) {
		StringBuffer appendInfo = new StringBuffer();
		if (eng.equals("")) {
			appendInfo.append("�ܾ �Է����ּ���!").append("\n");
			return appendInfo.toString();
		}
		if (kors.get(0).equals("")) {
			appendInfo.append("ù��° �ǹ̸� �Է����ּ���!").append("\n");
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
			appendInfo.append("�ܾ� �߰� �Ϸ� ");
			appendInfo.append("[").append(vocaSet.size()).append("]");
			appendInfo.append("\n");
		} else {
			appendInfo.append(eng);
			appendInfo.append("�� �̹� ��ϵ� �ܾ��Դϴ�!").append("\n");
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
					searchInfo.append("���ϸ�: ").append(selectedFileName).append("\n");
					searchInfo.append("[ ").append(findVoca.getEnglish()).append(" ]");
					searchInfo.append(" �����: ").append(findVoca.getMistakeCnt()).append("\n");
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
					if (input.equals(kor)) {
						searchInfo.append("���ϸ�: ").append(selectedFileName).append("\n");
						searchInfo.append("[ ").append(findVoca.getEnglish()).append(" ]");
						searchInfo.append(" �����: ").append(findVoca.getMistakeCnt()).append("\n");
						searchInfo.append(koreans.get(0));
						if (!koreans.get(1).equals(""))
							searchInfo.append(", ").append(koreans.get(1));
						if (!koreans.get(2).equals(""))
							searchInfo.append(", ").append(koreans.get(2));
						searchInfo.append("\n");
						break;
					}
				}
			}
			return searchInfo.toString();
		}
	}

	public String searchVocaInfoInAllFile(String input) throws InputException {
		if (input.equals(""))
			throw new InputException("�˻��� �ܾ��� �Է����ּ���!");
		StringBuffer searchInfos = new StringBuffer();
		String[] fileList = loadVocaSetsFileList();
		for (String fileName : fileList) {
			selectedFileName = fileName;
			loadVocaSet(selectedFileName);
			searchInfos.append(searchVocaInfo(input));
		}
		if (searchInfos.toString().equals(""))
			searchInfos.append("�ش��ϴ� �ܾ �������� �ʽ��ϴ�!").append("\n");
		return searchInfos.toString();
	}

	private String deleteVocaInfo(String input) throws InputException {
		StringBuffer deleteInfo = new StringBuffer();
		Iterator<Voca> itr = vocaSet.iterator();
		if ((input.charAt(0) >= 65 && input.charAt(0) <= 90) || (input.charAt(0) >= 97 && input.charAt(0) <= 122)) {
			Voca findVoca = null;
			while (itr.hasNext()) {
				findVoca = itr.next();
				if (input.equals(findVoca.getEnglish())) {
					deleteInfo.append("���ϸ�: ").append(selectedFileName).append("\n");
					deleteInfo.append("[ ").append(findVoca.getEnglish()).append(" ] ");
					deleteInfo.append("�����Ϸ�").append("\n");
					vocaSet.remove(findVoca);
					saveVocaSet();
					return deleteInfo.toString();
				}
			}
			return deleteInfo.toString();
		} else {
			throw new InputException("�ǹ̷δ� �ܾ ������ �� �����ϴ�!");
		}
	}

	public String deleteVocaInfoByFileName(String input, String fileName) throws InputException {
		if (input.equals(""))
			throw new InputException("������ �ܾ��� �Է����ּ���!");
		if (!loadVocaSet(fileName))
			throw new InputException("�ش� ������ �������� �ʽ��ϴ�!");
		StringBuffer deleteInfos = new StringBuffer();
		selectedFileName = fileName;
		String[] questionListFileList = loadQuestionListFileList();
		for (String questionListFile : questionListFileList) {
			if (questionListFile.indexOf(selectedFileName) != -1) {
				System.out.println(selectedFileName);
				deleteQuestionList(questionListFile);
			}
		}
		deleteInfos.append(deleteVocaInfo(input));
		if (deleteInfos.toString().equals(""))
			deleteInfos.append("�ش��ϴ� �ܾ �������� �ʽ��ϴ�!").append("\n");
		return deleteInfos.toString();
	}

	public void checkInputException(String fileName) throws InputException {
		if (fileName.equals(""))
			throw new InputException("���ϸ� �̸��� �Է����ּ���!");
		if (!loadVocaSet(fileName))
			throw new InputException("�������� �ʴ� ���ϸ��Դϴ�!");
	}

	public void setMistakeCntForShow(String misCnt) throws InputException {
		if (misCnt.equals(""))
			throw new InputException("���� ���� �Է����ּ���!");
		if (misCnt.charAt(0) < 48 || misCnt.charAt(0) > 57)
			throw new InputException("���� ���� ���ڷ� �Է����ּ���!");
		mistakeCntForShow = Integer.valueOf(misCnt);
	}

	public String showTotalVocaByFile(String fileName) {
		StringBuffer totalInfo = new StringBuffer();
		Iterator<Voca> itr = vocaSet.iterator();
		int vocaNum = 0;
		totalInfo.append("=====================================================\n");
		totalInfo.append("���ϸ�: ").append(fileName).append("\n");
		while (itr.hasNext()) {
			Voca findVoca = itr.next();
			if (findVoca.getMistakeCnt() < mistakeCntForShow)
				continue;
			Vector<String> koreans = findVoca.getKoreans();
			totalInfo.append(++vocaNum).append(". ");
			totalInfo.append("[ ").append(findVoca.getEnglish()).append(" ]");
			totalInfo.append(" �����: ").append(findVoca.getMistakeCnt()).append("\n");
			totalInfo.append(koreans.get(0));
			if (!koreans.get(1).equals(""))
				totalInfo.append(", ").append(koreans.get(1));
			if (!koreans.get(2).equals(""))
				totalInfo.append(", ").append(koreans.get(2));
			totalInfo.append("\n");
		}
		return totalInfo.toString();
	}

	public void createVocaCollection(String fileName) throws InputException {
		if (fileName.equals(""))
			throw new InputException("������ �̸��� �Է����ּ���!");
		if (fileName.indexOf("_") != -1 || fileName.indexOf(".") != -1)
			throw new InputException("_�� .�� ����� �� �����ϴ�!");
		try {
			File createFile = new File("sets/" + fileName + ".txt");
			if (createFile.exists())
				throw new InputException("�̹� �����ϴ� �̸��Դϴ�.");
			createFile.createNewFile();
		} catch (IOException expn) {
			expn.printStackTrace();
		}
	}

	public void setSelectedFileName(String fileName) throws InputException {
		if (fileName == null)
			throw new InputException("������ �������ּ���!");
		selectedFileName = fileName;
	}

	public String getSelectedFileName() {
		return selectedFileName;
	}

	public void deleteVocaCollection(String fileName) throws InputException {
		if (fileName == null)
			throw new InputException("������ ������ �������ּ���!");
		File deleteFile = new File("sets/" + fileName + ".txt");
		deleteFile.delete();
	}

	public void deleteQuestionList(String fileName) throws InputException {
		if (fileName == null)
			throw new InputException("������ ������ �������ּ���!");
		File deleteFile = new File("lists/" + fileName);
		deleteFile.delete();
	}

	public void deleteQusetionListOfVocaSet(String fileName) {
		String[] fileList = questionListDirectory.list();
		for (String file : fileList) {
			if (file.indexOf(fileName) != -1) {
				File deleteFile = new File("lists/" + file);
				deleteFile.delete();
			}
		}
	}

	public String initializeMistakeCnt(String fileName) throws InputException {
		if (fileName == null)
			throw new InputException("������� �ʱ�ȭ�� ������ �������ּ���!");
		if (!loadVocaSet(fileName))
			throw new InputException("�������� �ʴ� ���ϸ��Դϴ�!");
		selectedFileName = fileName;
		StringBuffer initInfo = new StringBuffer();
		Iterator<Voca> itr = vocaSet.iterator();
		while (itr.hasNext())
			itr.next().setMistakeCnt(0);
		initInfo.append(fileName).append(" ������ ��� �ܾ� ����� �ʱ�ȭ �Ϸ�!\n");
		return initInfo.toString();
	}

	public String[] loadVocaSetsFileList() {
		String[] fileList = vocaSetDirectory.list();
		for (int i = 0; i < fileList.length; i++)
			fileList[i] = fileList[i].substring(0, fileList[i].indexOf("."));
		return fileList;
	}

	public String[] loadQuestionListFileList() {
		String[] fileList = questionListDirectory.list();
		return fileList;
	}

	public void setLimitTime(int index) {
		switch (index) {
		case 0:
			limitTime = 3;
			break;
		case 1:
			limitTime = 5;
			break;
		case 2:
			limitTime = 10;
			break;
		case 3:
			limitTime = 30;
			break;
		case 4:
			limitTime = 0;
			break;
		}
	}
