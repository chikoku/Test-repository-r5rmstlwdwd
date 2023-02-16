package dynamic_voca;

import java.io.Serializable;
import java.util.Vector;

@SuppressWarnings("serial")
public class Voca implements Serializable {
	private String english;
	private Vector<String> koreans = new Vector<String>(5);
	private int mistakeCnt;

	public Voca(String english, Vector<String> koreans) {
		super();
		this.english = english;
		this.koreans = koreans;
		this.mistakeCnt = 0;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public Vector<String> getKoreans() {
		return koreans;
	}

	public void setKoreans(Vector<String> koreans) {
		this.koreans = koreans;
	}

	public int getMistakeCnt() {
		return mistakeCnt;
	}

	public void setMistakeCnt(int mistakeCnt) {
		this.mistakeCnt = mistakeCnt;
	}

	public void addMistakeCnt() {
		mistakeCnt++;
	}

	public int hashCode() {
		return english.hashCode();
	}

	public boolean equals(Object obj) {
		String s = ((Voca) obj).english;
		if (english.compareTo(s) == 0)
			return true;
		else
			return false;
	}
	
}
