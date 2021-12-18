
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