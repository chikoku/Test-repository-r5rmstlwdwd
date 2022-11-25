package dynamic_voca;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

public class QuestionList implements Serializable {
	private static final long serialVersionUID = 29326037884185078L;

	private ArrayList<Voca> questionVocas;
	private ArrayList<Voca> reviewVocas;
	private int questionNum;
	private int correctCnt;
	private int incorrectCnt;

	public QuestionList() {
		questionVocas = new ArrayList<>(3000);
		reviewVocas = new ArrayList<>(1000);
		questionNum = 0;
		correctCnt = 0;
		incorrectCnt = 0;
	}

	public void setQuestionVocas(Set<Voca> vocaSet) {
		Iterator<Voca> itr = vocaSet.iterator();
		while (itr.hasNext())
			questionVocas.add(itr.next());
	}

	public Voca getQuestionVoca() {
		if (questionNum == questionVocas.size())
			return null;
		return questionVocas.get(questionNum);
	}

	public void shuffleQuestionVocas() {
		Collections.shuffle(questionVocas);
	}

	public void addInReviewVocas(Voca misVoca) {
		reviewVocas.add(misVoca);
		incorrectCnt++;
	}

	public ArrayList<Voca> getReviewVoca() {
		return reviewVocas;
	}

	public void setQusetionVocasByReviewVocas() {
		questionVocas = reviewVocas;
		reviewVocas = new ArrayList<>(1000);
		questionNum = 0;
		correctCnt = 0;
		incorrectCnt = 0;
	}

	public String getProcessRate() {
		if (questionVocas.size() == 0)
			return "0";
		DecimalFormat df = new DecimalFormat("##.#");
		double processRate = (double) questionNum / questionVocas.size() * 100;
		return df.format(processRate);
	}

	public String getCorrectRate() {
		if (correctCnt == 0)
			return "0";
		DecimalFormat df = new DecimalFormat("##.#");
		double correctRate = (double) correctCnt / (correctCnt + incorrectCnt) * 100;
		return df.format(correctRate);
	}

	public void addCorrectCnt() {
		correctCnt++;
	}

	public int getRemainedQuestionCnt() {
		return questionVocas.size() - questionNum;
	}

	public int getCorrectCnt() {
		return correctCnt;
	}

	public int getIncorrectCnt() {
		return incorrectCnt;
	}

	public boolean removeInQuestionVocas(Voca delVoca) {
		return questionVocas.remove(delVoca);
	}

	public boolean removeInReviewVocas(Voca delVoca) {
		return reviewVocas.remove(delVoca);
	}

	public boolean isEmptyQuestionVocas() {
		return questionVocas.isEmpty();
	}

	public boolean isEmptyReviewVocas() {
		return reviewVocas.isEmpty();
	}

	public int getQuestionNum() {
		return questionNum;
	}

	public void addQuestionNum() {
		questionNum++;
	}

}
