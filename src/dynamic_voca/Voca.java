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

	public void setEnglish(String eng