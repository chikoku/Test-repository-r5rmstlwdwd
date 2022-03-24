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
	private A