
package dynamic_voca;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.im.InputContext;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import exception.InputException;

@SuppressWarnings("serial")
public class DynamicVocaGUI extends JFrame {
	private DynamicVocaHandler dynamicHandler = DynamicVocaHandler.createDynamicVocaHandlerInst();
	private final Component mComponent = this;
	private Image screenImage;
	private Graphics screenGraphic;
	private Image background;
	private JLabel menuBar;
	private JButton quitButton;
	private LimitTimeThread showLimitTime;
	private boolean isTestEnd;

	private int mouseX, mouseY;

	private ImageIcon mainBackgroundImage = new ImageIcon("images/mainBackground.jpg");
	private ImageIcon subBackgroundImage = new ImageIcon("images/subBackground.jpg");
	private ImageIcon testMenuButtonImage = new ImageIcon("images/testMenuButton.png");
	private ImageIcon testMenuEnteredButtonImage = new ImageIcon("images/testMenuEnteredButton.png");
	private ImageIcon loadMenuButtonImage = new ImageIcon("images/loadMenuButton.png");
	private ImageIcon loadMenuEnteredButtonImage = new ImageIcon("images/loadMenuEnteredButton.png");
	private ImageIcon apndMenuButtonImage = new ImageIcon("images/apndMenuButton.png");
	private ImageIcon apndMenuEnteredButtonImage = new ImageIcon("images/apndMenuEnteredButton.png");
	private ImageIcon searchMenuButtonImage = new ImageIcon("images/searchMenuButton.png");
	private ImageIcon searchMenuEnteredButtonImage = new ImageIcon("images/searchMenuEnteredButton.png");
	private ImageIcon infoMenuButtonImage = new ImageIcon("images/infoMenuButton.png");
	private ImageIcon infoMenuEnteredButtonImage = new ImageIcon("images/infoMenuEnteredButton.png");
	private ImageIcon quitMenuButtonImage = new ImageIcon("images/quitMenuButton.png");
	private ImageIcon quitMenuEnteredButtonImage = new ImageIcon("images/quitMenuEnteredButton.png");
	private ImageIcon quitButtonImage = new ImageIcon("images/quitButton.png");
	private ImageIcon quitEnteredButtonImage = new ImageIcon("images/quitEnteredButton.png");
	private ImageIcon appendButtonImage = new ImageIcon("images/appendButton.png");
	private ImageIcon appendEnteredButtonImage = new ImageIcon("images/appendEnteredButton.png");
	private ImageIcon backButtonImage = new ImageIcon("images/backButton.png");
	private ImageIcon backEnteredButtonImage = new ImageIcon("images/backEnteredButton.png");
	private ImageIcon deleteButtonImage = new ImageIcon("images/deleteButton.png");
	private ImageIcon deleteEnteredButtonImage = new ImageIcon("images/deleteEnteredButton.png");
	private ImageIcon saveButtonImage = new ImageIcon("images/saveButton.png");
	private ImageIcon saveEnteredButtonImage = new ImageIcon("images/saveEnteredButton.png");
	private ImageIcon searchButtonImage = new ImageIcon("images/searchButton.png");
	private ImageIcon searchEnteredButtonImage = new ImageIcon("images/searchEnteredButton.png");
	private ImageIcon selectButtonImage = new ImageIcon("images/selectButton.png");
	private ImageIcon selectEnteredButtonImage = new ImageIcon("images/selectEnteredButton.png");
	private ImageIcon createButtonImage = new ImageIcon("images/createButton.png");
	private ImageIcon createEnteredButtonImage = new ImageIcon("images/createEnteredButton.png");
	private ImageIcon showTotalButtonImage = new ImageIcon("images/showTotalButton.png");
	private ImageIcon showTotalEnteredButtonImage = new ImageIcon("images/showTotalEnteredButton.png");
	private ImageIcon initializeButtonImage = new ImageIcon("images/initializeButton.png");
	private ImageIcon initializeEnteredButtonImage = new ImageIcon("images/initializeEnteredButton.png");
	private ImageIcon retestButtonImage = new ImageIcon("images/retestButton.png");
	private ImageIcon retestEnteredButtonImage = new ImageIcon("images/retestEnteredButton.png");

	// Menu Components
	private JButton testMenuButton;
	private JButton loadMenuButton;
	private JButton apndMenuButton;
	private JButton searchMenuButton;
	private JButton infoMenuButton;
	private JButton quitMenuButton;

	// Test Components
	private JLabel questionLabel = new JLabel("문제");
	private JLabel limitTimeLabel = new JLabel();
	private JTextField questionField = new JTextField();
	private JTextField answerField = new JTextField();
	private JTextArea situationArea = new JTextArea();
	private JTextArea testInfoArea = new JTextArea();
	private JLabel testInfoLabel = new JLabel("테스트 정보");
	private JScrollPane testInfoScrollPane = new JScrollPane(testInfoArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JButton saveButton = new JButton();
	private JButton quitTestButton = new JButton();

	// Select VocaCollectionFile for test Components
	private JLabel testCollectionListLabel = new JLabel("테스트 목록");
	private JList<String> testCollectionList = new JList<>();
	private JScrollPane testListScrollPane = new JScrollPane(testCollectionList,
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JLabel selectlimitTimeLabel = new JLabel("제한 시간");
	private final String[] limitTimes = { "3초", "5초", "10초", "30초", "무제한" };
	private JComboBox<String> limitTimeComboBox = new JComboBox<>(limitTimes);
	private JCheckBox allowColorByMistakeCntCheckBox = new JCheckBox("오답 수에 따른 색깔 표현 허용");
	private JCheckBox allowEffectSoundCheckBox = new JCheckBox("효과음 허용");
	private JButton selectTestButton = new JButton();
	private JButton cancelTestButton = new JButton();

	// Load Components
	private JLabel loadCollectionListLabel = new JLabel("저장 목록");
	private JList<String> loadCollectionList = new JList<>();
	private JScrollPane loadListScrollPane = new JScrollPane(loadCollectionList,
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JButton selectLoadButton = new JButton();
	private JButton cancelLoadButton = new JButton();
	private JButton deleteLoadButton = new JButton();
	private JButton retestLoadButton = new JButton();

	// Select VocaCollectionFile for append Components
	private JLabel collectionListLabel = new JLabel("문제집 목록");
	private JList<String> collectionList = new JList<>();
	private JScrollPane listScrollPane = new JScrollPane(collectionList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JLabel newCollectionNameLabel = new JLabel("생성할 문제집 이름");
	private JTextField newCollectionNameField = new JTextField();
	private JButton createCollectionButton = new JButton();
	private JButton selectCollectionButton = new JButton();
	private JButton deleteCollectionButton = new JButton();
	private JButton cancelCollectionButton = new JButton();

	// Append Components
	private JLabel englishLabel = new JLabel("단어");
	private JTextField englishField = new JTextField();
	private JLabel koreanLabel = new JLabel("의미");
	private JTextField[] koreanFields = new JTextField[3];
	private JTextArea checkApndArea = new JTextArea();
	private JLabel fileNameLabel = new JLabel();
	private JScrollPane apndAreaScrollPane = new JScrollPane(checkApndArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JButton vocaApndButton = new JButton();
	private JButton apndQuitButton = new JButton();

	// search Components
	private JTextField inputField = new JTextField();
	private JLabel infoLabel = new JLabel("정보");
	private JTextArea searchResultArea = new JTextArea();
	private JScrollPane searchAreaScrollPane = new JScrollPane(searchResultArea,
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JButton searchButton = new JButton();
	private JButton deleteButton = new JButton();
	private JButton showTotalButton = new JButton();
	private JButton searchQuitButton = new JButton();
	private JButton initMisCntButton = new JButton();

	// Information Components
	private JTextArea developInfoArea = new JTextArea();
	private JButton infoQuitButton = new JButton();

	public DynamicVocaGUI() {
		background = mainBackgroundImage.getImage();
		setUndecorated(true);
		setTitle("Dynamic Voca");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		setQuitButton();
		setMenuBar();
		setMenuComponents();
		setTestComponentsVisible(false);
		setTestComponents();
		setSelectForTestComponentsVisible(false);
		setSelectForTestComponents();
		setLoadComponentsVisible(false);
		setLoadComponents();
		setAppendComponentsVisible(false);
		setAppendComponents();
		setSelectForAppendComponentsVisible(false);
		setSelectForAppendComponents();
		setSearchComponentsVisible(false);
		setSearchComponents();
		setInformationComponentsVisible(false);
		setInformationComponents();
	}

	public void setMenuComponents() {
		int posX = 100, posY = 100;
		final int BUTTON_WIDTH = 273;
		final int BUTTON_HEIGHT = 80;
		final int INTERVAL = 90;

		testMenuButton = new JButton(testMenuButtonImage);
		testMenuButton.setBackground(new Color(0, 0, 0, 0));
		testMenuButton.setBorderPainted(false);
		testMenuButton.setBounds(posX, posY, BUTTON_WIDTH, BUTTON_HEIGHT);
		testMenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				testMenuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				testMenuButton.setIcon(testMenuEnteredButtonImage);
				Sound EnteredButtonSound = new Sound("ButtonEnteredSound.mp3", false);
				EnteredButtonSound.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				testMenuButton.setIcon(testMenuButtonImage);
				testMenuButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Sound buttonPressedSound = new Sound("buttonPressedSound.mp3", false);
				buttonPressedSound.start();
				background = subBackgroundImage.getImage();
				setSelectForTestComponentsVisible(true);
				setMenuComponentsVisible(false);
				answerField.requestFocus();
			}
		});
		add(testMenuButton);

		loadMenuButton = new JButton(loadMenuButtonImage);
		loadMenuButton.setBackground(new Color(0, 0, 0, 0));
		loadMenuButton.setBorderPainted(false);
		loadMenuButton.setBounds(posX, posY += INTERVAL, BUTTON_WIDTH, BUTTON_HEIGHT);
		loadMenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				loadMenuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				loadMenuButton.setIcon(loadMenuEnteredButtonImage);
				Sound EnteredButtonSound = new Sound("ButtonEnteredSound.mp3", false);
				EnteredButtonSound.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				loadMenuButton.setIcon(loadMenuButtonImage);
				loadMenuButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Sound buttonPressedSound = new Sound("buttonPressedSound.mp3", false);
				buttonPressedSound.start();
				background = subBackgroundImage.getImage();
				setLoadComponentsVisible(true);
				setMenuComponentsVisible(false);
			}
		});
		add(loadMenuButton);

		apndMenuButton = new JButton(apndMenuButtonImage);
		apndMenuButton.setBackground(new Color(0, 0, 0, 0));
		apndMenuButton.setBorderPainted(false);
		apndMenuButton.setBounds(posX, posY += INTERVAL, BUTTON_WIDTH, BUTTON_HEIGHT);
		apndMenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				apndMenuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				apndMenuButton.setIcon(apndMenuEnteredButtonImage);
				Sound EnteredButtonSound = new Sound("ButtonEnteredSound.mp3", false);
				EnteredButtonSound.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				apndMenuButton.setIcon(apndMenuButtonImage);
				apndMenuButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Sound buttonPressedSound = new Sound("buttonPressedSound.mp3", false);
				buttonPressedSound.start();
				background = subBackgroundImage.getImage();
				setSelectForAppendComponentsVisible(true);
				setMenuComponentsVisible(false);
			}
		});
		add(apndMenuButton);

		searchMenuButton = new JButton(searchMenuButtonImage);
		searchMenuButton.setBackground(new Color(0, 0, 0, 0));
		searchMenuButton.setBorderPainted(false);
		searchMenuButton.setBounds(posX, posY += INTERVAL, BUTTON_WIDTH, BUTTON_HEIGHT);
		searchMenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				searchMenuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				searchMenuButton.setIcon(searchMenuEnteredButtonImage);
				Sound EnteredButtonSound = new Sound("ButtonEnteredSound.mp3", false);
				EnteredButtonSound.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				searchMenuButton.setIcon(searchMenuButtonImage);
				searchMenuButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Sound buttonPressedSound = new Sound("buttonPressedSound.mp3", false);
				buttonPressedSound.start();
				background = subBackgroundImage.getImage();
				setSearchComponentsVisible(true);
				setMenuComponentsVisible(false);
				inputField.requestFocus();
			}
		});
		add(searchMenuButton);

		infoMenuButton = new JButton(infoMenuButtonImage);
		infoMenuButton.setBackground(new Color(0, 0, 0, 0));
		infoMenuButton.setBorderPainted(false);
		infoMenuButton.setBounds(posX, posY += INTERVAL, BUTTON_WIDTH, BUTTON_HEIGHT);
		infoMenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				infoMenuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				infoMenuButton.setIcon(infoMenuEnteredButtonImage);
				Sound EnteredButtonSound = new Sound("ButtonEnteredSound.mp3", false);
				EnteredButtonSound.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				infoMenuButton.setIcon(infoMenuButtonImage);
				infoMenuButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Sound buttonPressedSound = new Sound("buttonPressedSound.mp3", false);
				buttonPressedSound.start();
				background = subBackgroundImage.getImage();
				setInformationComponentsVisible(true);
				setMenuComponentsVisible(false);
			}
		});
		add(infoMenuButton);

		quitMenuButton = new JButton(quitMenuButtonImage);
		quitMenuButton.setBackground(new Color(0, 0, 0, 0));
		quitMenuButton.setBorderPainted(false);
		quitMenuButton.setBounds(posX, posY += INTERVAL, BUTTON_WIDTH, BUTTON_HEIGHT);
		quitMenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitMenuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				quitMenuButton.setIcon(quitMenuEnteredButtonImage);
				Sound EnteredButtonSound = new Sound("ButtonEnteredSound.mp3", false);
				EnteredButtonSound.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitMenuButton.setIcon(quitMenuButtonImage);
				quitMenuButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Sound buttonPressedSound = new Sound("buttonPressedSound.mp3", false);
				buttonPressedSound.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(quitMenuButton);
	}

	public void setTestComponents() {
		final int BUTTON_WIDTH = 173;
		final int BUTTON_HEIGHT = 80;

		saveButton.setIcon(saveButtonImage);
		saveButton.setBackground(new Color(0, 0, 0, 0));
		saveButton.setBorderPainted(false);
		saveButton.setBounds(820, 270, BUTTON_WIDTH, BUTTON_HEIGHT);
		saveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				saveButton.setIcon(saveEnteredButtonImage);
				Sound EnteredButtonSound = new Sound("ButtonEnteredSound.mp3", false);
				EnteredButtonSound.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				saveButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				saveButton.setIcon(saveButtonImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Sound buttonPressedSound = new Sound("buttonPressedSound.mp3", false);
				buttonPressedSound.start();
				testInfoArea.append(dynamicHandler.saveQuestionList());
				testInfoArea.append(" 저장완료\n");
				loadCollectionList.setListData(dynamicHandler.loadQuestionListFileList());
			}
		});
		add(saveButton);

		quitTestButton.setIcon(backButtonImage);
		quitTestButton.setBackground(new Color(0, 0, 0, 0));
		quitTestButton.setBorderPainted(false);
		quitTestButton.setBounds(1020, 270, BUTTON_WIDTH, BUTTON_HEIGHT);
		quitTestButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitTestButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				quitTestButton.setIcon(backEnteredButtonImage);
				Sound EnteredButtonSound = new Sound("ButtonEnteredSound.mp3", false);
				EnteredButtonSound.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitTestButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				quitTestButton.setIcon(backButtonImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (dynamicHandler.getLimitTime() != 0 && !questionField.getText().equals("테스트 완료"))
					showLimitTime.interrupt();
				Sound buttonPressedSound = new Sound("buttonPressedSound.mp3", false);
				buttonPressedSound.start();
				background = mainBackgroundImage.getImage();
				setTestComponentsVisible(false);
				setMenuComponentsVisible(true);
				answerField.setText("");
				testInfoArea.setText("");
				questionField.setText("");
				situationArea.setText("");
				answerField.setEditable(true);
				dynamicHandler.saveVocaSet();
				limitTimeLabel.setText("");
			}
		});
		add(quitTestButton);

		limitTimeLabel.setBounds(580, 50, 200, 50);
		limitTimeLabel.setForeground(Color.ORANGE);
		limitTimeLabel.setFont(new Font("basic", Font.ITALIC, 50));
		add(limitTimeLabel);

		questionLabel.setBounds(730, 100, 100, 50);
		questionLabel.setForeground(Color.black);
		questionLabel.setFont(new Font("basic", 0, 30));
		add(questionLabel);

		questionField.setBounds(830, 100, 350, 50);
		questionField.setBackground(Color.GRAY);
		questionField.setFont(new Font("basic", 0, 24));
		questionField.setEditable(false);
		add(questionField);

		answerField.setBounds(830, 200, 350, 50);
		answerField.setFont(new Font("basic", 0, 24));
		answerField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dynamicHandler.setNextQuestion();
				testInfoArea.append(dynamicHandler.testVocaInfo(answerField.getText()));
				situationArea.setText(dynamicHandler.testSituationInfo());
				questionField.setText(dynamicHandler.getQuestion());
				answerField.setText("");
				answerField.requestFocus();
				if (questionField.getText().equals("테스트 완료")) {
					isTestEnd = true;
					answerField.setEditable(false);
					questionField.requestFocus();
					questionField.setForeground(Color.black);
					limitTimeLabel.setText("");
					return;
				}
				setQuestionFieldColor();
			}
		});
		add(answerField);

		situationArea.setBounds(830, 370, 350, 250);
		situationArea.setFont(new Font("area", 0, 24));
		situationArea.setBackground(Color.LIGHT_GRAY);
		situationArea.setEditable(false);
		add(situationArea);

		testInfoLabel.setBounds(100, 100, 300, 50);
		testInfoLabel.setForeground(Color.black);