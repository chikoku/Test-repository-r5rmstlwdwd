
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
	private JLabel questionLabel = new JLabel("????");
	private JLabel limitTimeLabel = new JLabel();
	private JTextField questionField = new JTextField();
	private JTextField answerField = new JTextField();
	private JTextArea situationArea = new JTextArea();
	private JTextArea testInfoArea = new JTextArea();
	private JLabel testInfoLabel = new JLabel("?????? ????");
	private JScrollPane testInfoScrollPane = new JScrollPane(testInfoArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JButton saveButton = new JButton();
	private JButton quitTestButton = new JButton();

	// Select VocaCollectionFile for test Components
	private JLabel testCollectionListLabel = new JLabel("?????? ????");
	private JList<String> testCollectionList = new JList<>();
	private JScrollPane testListScrollPane = new JScrollPane(testCollectionList,
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JLabel selectlimitTimeLabel = new JLabel("???? ????");
	private final String[] limitTimes = { "3??", "5??", "10??", "30??", "??????" };
	private JComboBox<String> limitTimeComboBox = new JComboBox<>(limitTimes);
	private JCheckBox allowColorByMistakeCntCheckBox = new JCheckBox("???? ???? ???? ???? ???? ????");
	private JCheckBox allowEffectSoundCheckBox = new JCheckBox("?????? ????");
	private JButton selectTestButton = new JButton();
	private JButton cancelTestButton = new JButton();

	// Load Components
	private JLabel loadCollectionListLabel = new JLabel("???? ????");
	private JList<String> loadCollectionList = new JList<>();
	private JScrollPane loadListScrollPane = new JScrollPane(loadCollectionList,
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JButton selectLoadButton = new JButton();
	private JButton cancelLoadButton = new JButton();
	private JButton deleteLoadButton = new JButton();
	private JButton retestLoadButton = new JButton();

	// Select VocaCollectionFile for append Components
	private JLabel collectionListLabel = new JLabel("?????? ????");
	private JList<String> collectionList = new JList<>();
	private JScrollPane listScrollPane = new JScrollPane(collectionList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JLabel newCollectionNameLabel = new JLabel("?????? ?????? ????");
	private JTextField newCollectionNameField = new JTextField();
	private JButton createCollectionButton = new JButton();
	private JButton selectCollectionButton = new JButton();
	private JButton deleteCollectionButton = new JButton();
	private JButton cancelCollectionButton = new JButton();

	// Append Components
	private JLabel englishLabel = new JLabel("????");
	private JTextField englishField = new JTextField();
	private JLabel koreanLabel = new JLabel("????");
	private JTextField[] koreanFields = new JTextField[3];
	private JTextArea checkApndArea = new JTextArea();
	private JLabel fileNameLabel = new JLabel();
	private JScrollPane apndAreaScrollPane = new JScrollPane(checkApndArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JButton vocaApndButton = new JButton();
	private JButton apndQuitButton = new JButton();

	// search Components
	private JTextField inputField = new JTextField();
	private JLabel infoLabel = new JLabel("????");
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
				testInfoArea.append(" ????????\n");
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
				if (dynamicHandler.getLimitTime() != 0 && !questionField.getText().equals("?????? ????"))
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
				if (questionField.getText().equals("?????? ????")) {
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
		testInfoLabel.setFont(new Font("basic", 0, 30));
		add(testInfoLabel);

		testInfoScrollPane.setBounds(100, 150, 500, 470);
		testInfoArea.setLineWrap(true);
		testInfoArea.setWrapStyleWord(true);
		testInfoArea.setFont(new Font("area", 0, 16));
		testInfoArea.setEditable(false);
		add(testInfoScrollPane);
	}

	public void setSelectForTestComponents() {
		final int BUTTON_WIDTH = 173;
		final int BUTTON_HEIGHT = 80;

		selectTestButton.setIcon(selectButtonImage);
		selectTestButton.setBackground(new Color(0, 0, 0, 0));
		selectTestButton.setBorderPainted(false);
		selectTestButton.setBounds(760, 420, BUTTON_WIDTH, BUTTON_HEIGHT);
		selectTestButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				selectTestButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				selectTestButton.setIcon(selectEnteredButtonImage);
				Sound EnteredButtonSound = new Sound("ButtonEnteredSound.mp3", false);
				EnteredButtonSound.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				selectTestButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				selectTestButton.setIcon(selectButtonImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Sound buttonPressedSound = new Sound("buttonPressedSound.mp3", false);
					buttonPressedSound.start();
					dynamicHandler.setSelectedFileName(testCollectionList.getSelectedValue());
					dynamicHandler.loadVocaSet(dynamicHandler.getSelectedFileName());
					dynamicHandler.setLimitTime(limitTimeComboBox.getSelectedIndex());
					setSelectForTestComponentsVisible(false);
					setTestComponentsVisible(true);
					JOptionPane.showMessageDialog(mComponent, "???????? ???????????", "????", JOptionPane.INFORMATION_MESSAGE);
					dynamicHandler.setQuestionListForTest();
					questionField.setText(dynamicHandler.getQuestion());
					answerField.requestFocus();
					setQuestionFieldColor();
					isTestEnd = false;
					if (dynamicHandler.getLimitTime() != 0) {
						showLimitTime = new LimitTimeThread();
						showLimitTime.start();
					}
					if (questionField.getText().equals("?????? ????")) {
						isTestEnd = true;
						answerField.setEditable(false);
						questionField.requestFocus();
						questionField.setForeground(Color.black);
						limitTimeLabel.setText("");
						return;
					}
				} catch (InputException expn) {
					JOptionPane.showMessageDialog(mComponent, expn.getMessage(), "????", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		add(selectTestButton);

		cancelTestButton.setIcon(backButtonImage);
		cancelTestButton.setBackground(new Color(0, 0, 0, 0));
		cancelTestButton.setBorderPainted(false);
		cancelTestButton.setBounds(980, 420, BUTTON_WIDTH, BUTTON_HEIGHT);
		cancelTestButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cancelTestButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				cancelTestButton.setIcon(backEnteredButtonImage);
				Sound EnteredButtonSound = new Sound("ButtonEnteredSound.mp3", false);
				EnteredButtonSound.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				cancelTestButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				cancelTestButton.setIcon(backButtonImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Sound buttonPressedSound = new Sound("buttonPressedSound.mp3", false);
				background = mainBackgroundImage.getImage();
				setSelectForTestComponentsVisible(false);
				setMenuComponentsVisible(true);
				buttonPressedSound.start();
			}
		});
		add(cancelTestButton);

		testCollectionListLabel.setBounds(100, 100, 300, 50);
		testCollectionListLabel.setForeground(Color.black);
		testCollectionListLabel.setFont(new Font("basic", 0, 24));
		add(testCollectionListLabel);

		selectlimitTimeLabel.setBounds(800, 100, 300, 50);
		selectlimitTimeLabel.setForeground(Color.black);
		selectlimitTimeLabel.setFont(new Font("basic", 0, 24));
		add(selectlimitTimeLabel);

		limitTimeComboBox.setBounds(800, 150, 300, 50);
		limitTimeComboBox.setFont(new Font("basic", 0, 24));
		add(limitTimeComboBox);

		allowColorByMistakeCntCheckBox.setBounds(780, 250, 400, 50);
		allowColorByMistakeCntCheckBox.setBackground(new Color(0, 0, 0, 0));
		allowColorByMistakeCntCheckBox.setFont(new Font("basic", Font.BOLD, 24));
		allowColorByMistakeCntCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					dynamicHandler.setAllowColorByMistkeCnt(true);
				else
					dynamicHandler.setAllowColorByMistkeCnt(false);
			}

		});
		add(allowColorByMistakeCntCheckBox);

		allowEffectSoundCheckBox.setBounds(780, 300, 400, 50);
		allowEffectSoundCheckBox.setBackground(new Color(0, 0, 0, 0));
		allowEffectSoundCheckBox.setFont(new Font("basic", Font.BOLD, 24));
		allowEffectSoundCheckBox.setSelected(true);
		dynamicHandler.setAllowEffectSound(true);
		allowEffectSoundCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					dynamicHandler.setAllowEffectSound(true);
				else
					dynamicHandler.setAllowEffectSound(false);
			}

		});
		add(allowEffectSoundCheckBox);

		testListScrollPane.setBounds(100, 150, 500, 470);
		testCollectionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		testCollectionList.setFont(new Font("basic", 0, 20));
		if (dynamicHandler.loadVocaSetsFileList() != null)
			testCollectionList.setListData(dynamicHandler.loadVocaSetsFileList());
		add(testListScrollPane);
	}

	public void setLoadComponents() {
		final int BUTTON_WIDTH = 173;
		final int BUTTON_HEIGHT = 80;

		selectLoadButton.setIcon(selectButtonImage);
		selectLoadButton.setBackground(new Color(0, 0, 0, 0));
		selectLoadButton.setBorderPainted(false);
		selectLoadButton.setBounds(760, 420, BUTTON_WIDTH, BUTTON_HEIGHT);
		selectLoadButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				selectLoadButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				selectLoadButton.setIcon(selectEnteredButtonImage);
				Sound EnteredButtonSound = new Sound("ButtonEnteredSound.mp3", false);
				EnteredButtonSound.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				selectLoadButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				selectLoadButton.setIcon(selectButtonImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Sound buttonPressedSound = new Sound("buttonPressedSound.mp3", false);
					buttonPressedSound.start();
					dynamicHandler.setSelectedFileName(loadCollectionList.getSelectedValue());
					dynamicHandler.loadVocaSet(dynamicHandler.getSelectedFileName());
					dynamicHandler.loadQuestionList(dynamicHandler.getSelectedFileName());
					dynamicHandler.setLimitTime(limitTimeComboBox.getSelectedIndex());
					setLoadComponentsVisible(false);
					setTestComponentsVisible(true);
					JOptionPane.showMessageDialog(mComponent, "???????? ???????????", "????", JOptionPane.INFORMATION_MESSAGE);
					questionField.setText(dynamicHandler.getQuestion());
					answerField.requestFocus();
					situationArea.setText(dynamicHandler.testSituationInfo());
					if (questionField.getText().equals("?????? ????")) {
						questionField.setForeground(Color.black);
						answerField.setEditable(false);
						questionField.requestFocus();
						return;
					}
					setQuestionFieldColor();
					isTestEnd = false;
					if (dynamicHandler.getLimitTime() != 0 && !questionField.getText().equals("?????? ????")) {
						showLimitTime = new LimitTimeThread();
						showLimitTime.start();
					}
				} catch (InputException expn) {
					JOptionPane.showMessageDialog(mComponent, expn.getMessage(), "????", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		add(selectLoadButton);

		retestLoadButton.setIcon(retestButtonImage);
		retestLoadButton.setBackground(new Color(0, 0, 0, 0));
		retestLoadButton.setBorderPainted(false);
		retestLoadButton.setBounds(980, 420, BUTTON_WIDTH, BUTTON_HEIGHT);
		retestLoadButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				retestLoadButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				retestLoadButton.setIcon(retestEnteredButtonImage);
				Sound EnteredButtonSound = new Sound("ButtonEnteredSound.mp3", false);
				EnteredButtonSound.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				retestLoadButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				retestLoadButton.setIcon(retestButtonImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Sound buttonPressedSound = new Sound("buttonPressedSound.mp3", false);
					buttonPressedSound.start();
					dynamicHandler.setSelectedFileName(loadCollectionList.getSelectedValue());
					dynamicHandler.loadVocaSet(dynamicHandler.getSelectedFileName());
					dynamicHandler.loadQuestionList(dynamicHandler.getSelectedFileName());
					dynamicHandler.setQuestionListForRetest();
					dynamicHandler.setLimitTime(limitTimeComboBox.getSelectedIndex());
					setLoadComponentsVisible(false);
					setTestComponentsVisible(true);
					JOptionPane.showMessageDialog(mComponent, "???????? ???????????", "????", JOptionPane.INFORMATION_MESSAGE);
					questionField.setText(dynamicHandler.getQuestion());
					answerField.requestFocus();
					situationArea.setText(dynamicHandler.testSituationInfo());
					if (questionField.getText().equals("?????? ????")) {
						questionField.setForeground(Color.black);
						answerField.setEditable(false);
						questionField.requestFocus();
						return;
					}
					setQuestionFieldColor();
					isTestEnd = false;
					if (dynamicHandler.getLimitTime() != 0 && !questionField.getText().equals("?????? ????")) {
						showLimitTime = new LimitTimeThread();
						showLimitTime.start();
					}
				} catch (InputException expn) {
					JOptionPane.showMessageDialog(mComponent, expn.getMessage(), "????", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		add(retestLoadButton);

		deleteLoadButton.setIcon(deleteButtonImage);
		deleteLoadButton.setBackground(new Color(0, 0, 0, 0));
		deleteLoadButton.setBorderPainted(false);
		deleteLoadButton.setBounds(760, 540, BUTTON_WIDTH, BUTTON_HEIGHT);
		deleteLoadButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				deleteLoadButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				deleteLoadButton.setIcon(deleteEnteredButtonImage);
				Sound EnteredButtonSound = new Sound("ButtonEnteredSound.mp3", false);
				EnteredButtonSound.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				deleteLoadButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				deleteLoadButton.setIcon(deleteButtonImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Sound buttonPressedSound = new Sound("buttonPressedSound.mp3", false);
					buttonPressedSound.start();
					dynamicHandler.deleteQuestionList(loadCollectionList.getSelectedValue());
					loadCollectionList.setListData(dynamicHandler.loadQuestionListFileList());
				} catch (InputException expn) {
					JOptionPane.showMessageDialog(mComponent, expn.getMessage(), "????", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		add(deleteLoadButton);

		cancelLoadButton.setIcon(backButtonImage);
		cancelLoadButton.setBackground(new Color(0, 0, 0, 0));
		cancelLoadButton.setBorderPainted(false);
		cancelLoadButton.setBounds(980, 540, BUTTON_WIDTH, BUTTON_HEIGHT);
		cancelLoadButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cancelLoadButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				cancelLoadButton.setIcon(backEnteredButtonImage);
				Sound EnteredButtonSound = new Sound("ButtonEnteredSound.mp3", false);
				EnteredButtonSound.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				cancelLoadButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				cancelLoadButton.setIcon(backButtonImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Sound buttonPressedSound = new Sound("buttonPressedSound.mp3", false);
				buttonPressedSound.start();
				background = mainBackgroundImage.getImage();
				setLoadComponentsVisible(false);
				setMenuComponentsVisible(true);
			}
		});
		add(cancelLoadButton);

		loadCollectionListLabel.setBounds(100, 100, 300, 50);
		loadCollectionListLabel.setForeground(Color.black);
		loadCollectionListLabel.setFont(new Font("basic", 0, 24));
		add(loadCollectionListLabel);

		loadListScrollPane.setBounds(100, 150, 500, 470);
		loadCollectionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		loadCollectionList.setFont(new Font("basic", 0, 20));
		if (dynamicHandler.loadQuestionListFileList() != null)
			loadCollectionList.setListData(dynamicHandler.loadQuestionListFileList());
		add(loadListScrollPane);
	}

	public void setAppendComponents() {
		final int BUTTON_WIDTH = 173;
		final int BUTTON_HEIGHT = 80;

		vocaApndButton.setIcon(appendButtonImage);
		vocaApndButton.setBackground(new Color(0, 0, 0, 0));
		vocaApndButton.setBorderPainted(false);
		vocaApndButton.setBounds(818, 550, BUTTON_WIDTH, BUTTON_HEIGHT);
		vocaApndButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				vocaApndButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				vocaApndButton.setIcon(appendEnteredButtonImage);
				Sound EnteredButtonSound = new Sound("ButtonEnteredSound.mp3", false);
				EnteredButtonSound.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				vocaApndButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				vocaApndButton.setIcon(appendButtonImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Sound buttonPressedSound = new Sound("buttonPressedSound.mp3", false);
				buttonPressedSound.start();
				String english = englishField.getText();
				englishField.setText("");
				Vector<String> koreans = new Vector<>(koreanFields.length);
				for (int i = 0; i < koreanFields.length; i++) {
					koreans.add(koreanFields[i].getText());
					koreanFields[i].setText("");
				}
				checkApndArea.append(dynamicHandler.appendVocaInfo(english, koreans));
				englishField.requestFocus();
			}
		});
		add(vocaApndButton);

		apndQuitButton.setIcon(backButtonImage);
		apndQuitButton.setBackground(new Color(0, 0, 0, 0));
		apndQuitButton.setBorderPainted(false);
		apndQuitButton.setBounds(1018, 550, BUTTON_WIDTH, BUTTON_HEIGHT);
		apndQuitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				apndQuitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				apndQuitButton.setIcon(backEnteredButtonImage);
				Sound EnteredButtonSound = new Sound("ButtonEnteredSound.mp3", false);
				EnteredButtonSound.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				apndQuitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				apndQuitButton.setIcon(backButtonImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Sound buttonPressedSound = new Sound("buttonPressedSound.mp3", false);
				background = mainBackgroundImage.getImage();
				setAppendComponentsVisible(false);
				setMenuComponentsVisible(true);
				buttonPressedSound.start();
				checkApndArea.setText("");
				englishField.setText("");
				for (int i = 0; i < koreanFields.length; i++)
					koreanFields[i].setText("");
				englishField.requestFocus();
				InputContext inCtx = englishField.getInputContext();
				Character.Subset[] subset = null;
				inCtx.setCharacterSubsets(subset);
				dynamicHandler.saveVocaSet();
			}
		});
		add(apndQuitButton);

		englishLabel.setBounds(730, 150, 100, 50);
		englishLabel.setForeground(Color.black);
		englishLabel.setFont(new Font("basic", 0, 30));
		add(englishLabel);

		englishField.setBounds(830, 150, 350, 50);
		englishField.setFont(new Font("basic", 0, 30));
		englishField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				koreanFields[0].requestFocus();
			}
		});

		englishField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				InputContext inCtx = englishField.getInputContext();
				Character.Subset[] subset = null;
				inCtx.setCharacterSubsets(subset);
			}

			@Override
			public void focusLost(FocusEvent e) {
				InputContext inCtx = englishField.getInputContext();
				Character.Subset[] subset = { Character.UnicodeBlock.HANGUL_SYLLABLES };
				inCtx.setCharacterSubsets(subset);
			}
		});

		add(englishField);

		koreanLabel.setForeground(Color.black);
		koreanLabel.setBounds(730, 250, 100, 50);
		koreanLabel.setFont(new Font("basic", 0, 30));
		add(koreanLabel);

		for (int i = 0; i < koreanFields.length; i++) {
			koreanFields[i].setBounds(830, 250 + 100 * i, 350, 50);
			koreanFields[i].setFont(new Font("basic", 0, 30));
			koreanFields[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Sound buttonPressedSound = new Sound("buttonPressedSound.mp3", false);
					buttonPressedSound.start();
					String english = englishField.getText();
					englishField.setText("");
					Vector<String> koreans = new Vector<>(koreanFields.length);
					for (int i = 0; i < koreanFields.length; i++) {
						koreans.add(koreanFields[i].getText());
						koreanFields[i].setText("");
					}
					checkApndArea.append(dynamicHandler.appendVocaInfo(english, koreans));
					englishField.requestFocus();
				}
			});
			add(koreanFields[i]);
		}

		fileNameLabel.setBounds(100, 100, 500, 50);
		fileNameLabel.setForeground(Color.black);
		fileNameLabel.setFont(new Font("basic", 0, 24));
		add(fileNameLabel);

		apndAreaScrollPane.setBounds(100, 150, 500, 470);
		checkApndArea.setLineWrap(true);
		checkApndArea.setWrapStyleWord(true);
		checkApndArea.setFont(new Font("area", Font.BOLD, 16));
		checkApndArea.setEditable(false);
		add(apndAreaScrollPane);
	}

	public void setSelectForAppendComponents() {
		final int BUTTON_WIDTH = 173;
		final int BUTTON_HEIGHT = 80;

		createCollectionButton.setIcon(createButtonImage);
		createCollectionButton.setBackground(new Color(0, 0, 0, 0));
		createCollectionButton.setBorderPainted(false);
		createCollectionButton.setBounds(760, 300, BUTTON_WIDTH, BUTTON_HEIGHT);
		createCollectionButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				createCollectionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				createCollectionButton.setIcon(createEnteredButtonImage);
				Sound EnteredButtonSound = new Sound("ButtonEnteredSound.mp3", false);
				EnteredButtonSound.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				createCollectionButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				createCollectionButton.setIcon(createButtonImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Sound buttonPressedSound = new Sound("buttonPressedSound.mp3", false);
					String createFileName = newCollectionNameField.getText();
					buttonPressedSound.start();
					newCollectionNameField.setText("");
					dynamicHandler.createVocaCollection(createFileName);
					collectionList.setListData(dynamicHandler.loadVocaSetsFileList());
					testCollectionList.setListData(dynamicHandler.loadVocaSetsFileList());
				} catch (InputException expn) {
					JOptionPane.showMessageDialog(mComponent, expn.getMessage(), "????", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		add(createCollectionButton);

		selectCollectionButton.setIcon(selectButtonImage);
		selectCollectionButton.setBackground(new Color(0, 0, 0, 0));
		selectCollectionButton.setBorderPainted(false);
		selectCollectionButton.setBounds(980, 300, BUTTON_WIDTH, BUTTON_HEIGHT);
		selectCollectionButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				selectCollectionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				selectCollectionButton.setIcon(selectEnteredButtonImage);
				Sound EnteredButtonSound = new Sound("ButtonEnteredSound.mp3", false);
				EnteredButtonSound.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				selectCollectionButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				selectCollectionButton.setIcon(selectButtonImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Sound buttonPressedSound = new Sound("buttonPressedSound.mp3", false);
					buttonPressedSound.start();
					dynamicHandler.setSelectedFileName(collectionList.getSelectedValue());
					String selectedFileName = dynamicHandler.getSelectedFileName();
					fileNameLabel.setText("?????? : " + selectedFileName);
					setSelectForAppendComponentsVisible(false);
					setAppendComponentsVisible(true);
					dynamicHandler.loadVocaSet(selectedFileName);
					englishField.requestFocus();
				} catch (InputException expn) {
					JOptionPane.showMessageDialog(mComponent, expn.getMessage(), "????", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		add(selectCollectionButton);

		deleteCollectionButton.setIcon(deleteButtonImage);
		deleteCollectionButton.setBackground(new Color(0, 0, 0, 0));
		deleteCollectionButton.setBorderPainted(false);
		deleteCollectionButton.setBounds(760, 420, BUTTON_WIDTH, BUTTON_HEIGHT);
		deleteCollectionButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				deleteCollectionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				deleteCollectionButton.setIcon(deleteEnteredButtonImage);
				Sound EnteredButtonSound = new Sound("ButtonEnteredSound.mp3", false);
				EnteredButtonSound.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				deleteCollectionButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				deleteCollectionButton.setIcon(deleteButtonImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Sound buttonPressedSound = new Sound("buttonPressedSound.mp3", false);
					buttonPressedSound.start();
					dynamicHandler.deleteVocaCollection(collectionList.getSelectedValue());
					dynamicHandler.deleteQusetionListOfVocaSet(collectionList.getSelectedValue());
					collectionList.setListData(dynamicHandler.loadVocaSetsFileList());
					testCollectionList.setListData(dynamicHandler.loadVocaSetsFileList());
					loadCollectionList.setListData(dynamicHandler.loadQuestionListFileList());
				} catch (InputException expn) {
					JOptionPane.showMessageDialog(mComponent, expn.getMessage(), "????", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		add(deleteCollectionButton);

		cancelCollectionButton.setIcon(backButtonImage);
		cancelCollectionButton.setBackground(new Color(0, 0, 0, 0));
		cancelCollectionButton.setBorderPainted(false);
		cancelCollectionButton.setBounds(980, 420, BUTTON_WIDTH, BUTTON_HEIGHT);
		cancelCollectionButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cancelCollectionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				cancelCollectionButton.setIcon(backEnteredButtonImage);
				Sound EnteredButtonSound = new Sound("ButtonEnteredSound.mp3", false);
				EnteredButtonSound.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				cancelCollectionButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				cancelCollectionButton.setIcon(backButtonImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Sound buttonPressedSound = new Sound("buttonPressedSound.mp3", false);
				background = mainBackgroundImage.getImage();
				setSelectForAppendComponentsVisible(false);
				setMenuComponentsVisible(true);
				buttonPressedSound.start();
				newCollectionNameField.setText("");
			}
		});
		add(cancelCollectionButton);

		collectionListLabel.setBounds(100, 100, 300, 50);
		collectionListLabel.setForeground(Color.black);
		collectionListLabel.setFont(new Font("basic", 0, 24));
		add(collectionListLabel);

		newCollectionNameLabel.setBounds(730, 100, 300, 50);
		newCollectionNameLabel.setForeground(Color.black);
		newCollectionNameLabel.setFont(new Font("basic", 0, 24));
		add(newCollectionNameLabel);

		listScrollPane.setBounds(100, 150, 500, 470);
		collectionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		collectionList.setFont(new Font("basic", 0, 20));
		if (dynamicHandler.loadVocaSetsFileList() != null)
			collectionList.setListData(dynamicHandler.loadVocaSetsFileList());
		add(listScrollPane);

		newCollectionNameField.setBounds(730, 150, 450, 50);
		newCollectionNameField.setFont(new Font("basic", 0, 30));
		add(newCollectionNameField);

	}

	public void setSearchComponents() {
		final int BUTTON_WIDTH = 173;
		final int BUTTON_HEIGHT = 80;

		searchButton.setIcon(searchButtonImage);
		searchButton.setBackground(new Color(0, 0, 0, 0));
		searchButton.setBorderPainted(false);
		searchButton.setBounds(760, 300, BUTTON_WIDTH, BUTTON_HEIGHT);
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				searchButton.setIcon(searchEnteredButtonImage);
				Sound EnteredButtonSound = new Sound("ButtonEnteredSound.mp3", false);
				EnteredButtonSound.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				searchButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				searchButton.setIcon(searchButtonImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Sound buttonPressedSound = new Sound("buttonPressedSound.mp3", false);
					buttonPressedSound.start();
					searchResultArea.append(dynamicHandler.searchVocaInfoInAllFile(inputField.getText()));
				} catch (InputException expn) {
					JOptionPane.showMessageDialog(mComponent, expn.getMessage(), "????", JOptionPane.WARNING_MESSAGE);
				}
				inputField.setText("");
				inputField.requestFocus();
			}
		});
		add(searchButton);

		showTotalButton.setIcon(showTotalButtonImage);
		showTotalButton.setBackground(new Color(0, 0, 0, 0));
		showTotalButton.setBorderPainted(false);
		showTotalButton.setBounds(980, 300, BUTTON_WIDTH, BUTTON_HEIGHT);
		showTotalButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				showTotalButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				showTotalButton.setIcon(showTotalEnteredButtonImage);
				Sound EnteredButtonSound = new Sound("ButtonEnteredSound.mp3", false);
				EnteredButtonSound.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				showTotalButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				showTotalButton.setIcon(showTotalButtonImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Sound buttonPressedSound = new Sound("buttonPressedSound.mp3", false);
					buttonPressedSound.start();
					dynamicHandler.checkInputException(inputField.getText());
					String mistakeCnt = (String) JOptionPane.showInputDialog(mComponent, "???????? ???? ?? ?????? ???????? ??????????.",
							"???? ?? ????", JOptionPane.PLAIN_MESSAGE, null, null, null);
					dynamicHandler.setMistakeCntForShow(mistakeCnt);
					searchResultArea.append(dynamicHandler.showTotalVocaByFile(inputField.getText()));
				} catch (InputException expn) {
					JOptionPane.showMessageDialog(mComponent, expn.getMessage(), "????", JOptionPane.WARNING_MESSAGE);
				}
				inputField.setText("");
				inputField.requestFocus();
			}
		});
		add(showTotalButton);

		deleteButton.setIcon(deleteButtonImage);
		deleteButton.setBackground(new Color(0, 0, 0, 0));
		deleteButton.setBorderPainted(false);
		deleteButton.setBounds(760, 420, BUTTON_WIDTH, BUTTON_HEIGHT);
		deleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				deleteButton.setIcon(deleteEnteredButtonImage);
				Sound EnteredButtonSound = new Sound("ButtonEnteredSound.mp3", false);
				EnteredButtonSound.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				deleteButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				deleteButton.setIcon(deleteButtonImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Sound buttonPressedSound = new Sound("buttonPressedSound.mp3", false);
					buttonPressedSound.start();
					String fileName = (String) JOptionPane.showInputDialog(mComponent, "?????? ?????? ???? ?????????? ??????????.",
							"???????? ????", JOptionPane.PLAIN_MESSAGE, null, null, null);
					searchResultArea.append(dynamicHandler.deleteVocaInfoByFileName(inputField.getText(), fileName));
					loadCollectionList.setListData(dynamicHandler.loadQuestionListFileList());
				} catch (InputException expn) {
					JOptionPane.showMessageDialog(mComponent, expn.getMessage(), "????", JOptionPane.WARNING_MESSAGE);
				}
				inputField.setText("");
				inputField.requestFocus();
			}
		});
		add(deleteButton);

		searchQuitButton.setIcon(backButtonImage);
		searchQuitButton.setBackground(new Color(0, 0, 0, 0));
		searchQuitButton.setBorderPainted(false);
		searchQuitButton.setBounds(980, 420, BUTTON_WIDTH, BUTTON_HEIGHT);
		searchQuitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				searchQuitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				searchQuitButton.setIcon(backEnteredButtonImage);
				Sound EnteredButtonSound = new Sound("ButtonEnteredSound.mp3", false);
				EnteredButtonSound.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				searchQuitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				searchQuitButton.setIcon(backButtonImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Sound buttonPressedSound = new Sound("buttonPressedSound.mp3", false);
				buttonPressedSound.start();
				background = mainBackgroundImage.getImage();
				setSearchComponentsVisible(false);
				setMenuComponentsVisible(true);
				inputField.setText("");
				searchResultArea.setText("");
			}
		});
		add(searchQuitButton);

		initMisCntButton.setIcon(initializeButtonImage);
		initMisCntButton.setBackground(new Color(0, 0, 0, 0));
		initMisCntButton.setBorderPainted(false);
		initMisCntButton.setBounds(760, 540, BUTTON_WIDTH, BUTTON_HEIGHT);
		initMisCntButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				initMisCntButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				initMisCntButton.setIcon(initializeEnteredButtonImage);
				Sound EnteredButtonSound = new Sound("ButtonEnteredSound.mp3", false);
				EnteredButtonSound.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				initMisCntButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				initMisCntButton.setIcon(initializeButtonImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Sound buttonPressedSound = new Sound("buttonPressedSound.mp3", false);
					buttonPressedSound.start();
					searchResultArea.append(dynamicHandler.initializeMistakeCnt(inputField.getText()));
					dynamicHandler.saveVocaSet();
				} catch (InputException expn) {
					JOptionPane.showMessageDialog(mComponent, expn.getMessage(), "????", JOptionPane.WARNING_MESSAGE);
				}
				inputField.setText("");
				inputField.requestFocus();
			}
		});
		add(initMisCntButton);

		inputField.setBounds(780, 150, 350, 50);
		inputField.setFont(new Font("basic", 0, 30));
		inputField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					searchResultArea.append(dynamicHandler.searchVocaInfoInAllFile(inputField.getText()));
					Sound buttonPressedSound = new Sound("buttonPressedSound.mp3", false);
					buttonPressedSound.start();
				} catch (InputException expn) {
					JOptionPane.showMessageDialog(mComponent, expn.getMessage(), "????", JOptionPane.WARNING_MESSAGE);
				}
				inputField.setText("");
				inputField.requestFocus();
			}
		});
		add(inputField);

		infoLabel.setBounds(100, 100, 300, 50);
		infoLabel.setFont(new Font("basic", Font.BOLD, 30));
		add(infoLabel);

		searchAreaScrollPane.setBounds(100, 150, 500, 470);
		searchResultArea.setLineWrap(true);
		searchResultArea.setWrapStyleWord(true);
		searchResultArea.setFont(new Font("area", Font.BOLD, 16));
		searchResultArea.setEditable(false);
		add(searchAreaScrollPane);
	}

	public void setInformationComponents() {
		developInfoArea.setBounds(100, 250, 800, 300);
		developInfoArea.setFont(new Font("area", Font.PLAIN, 30));
		developInfoArea.setForeground(Color.DARK_GRAY);
		developInfoArea.setBackground(new Color(0, 0, 0, 0));
		developInfoArea.setEditable(false);
		developInfoArea.setText(dynamicHandler.getDevelopInformation());
		add(developInfoArea);

		infoQuitButton.setIcon(backButtonImage);
		infoQuitButton.setBackground(new Color(0, 0, 0, 0));
		infoQuitButton.setBorderPainted(false);
		infoQuitButton.setBounds(550, 520, 173, 80);
		infoQuitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				infoQuitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				infoQuitButton.setIcon(backEnteredButtonImage);
				Sound EnteredButtonSound = new Sound("ButtonEnteredSound.mp3", false);
				EnteredButtonSound.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				infoQuitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				infoQuitButton.setIcon(backButtonImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Sound buttonPressedSound = new Sound("buttonPressedSound.mp3", false);
				buttonPressedSound.start();
				background = mainBackgroundImage.getImage();
				setInformationComponentsVisible(false);
				setMenuComponentsVisible(true);
			}
		});
		add(infoQuitButton);
	}

	public void setMenuComponentsVisible(boolean visible) {
		testMenuButton.setVisible(visible);
		loadMenuButton.setVisible(visible);
		apndMenuButton.setVisible(visible);
		searchMenuButton.setVisible(visible);
		infoMenuButton.setVisible(visible);
		quitMenuButton.setVisible(visible);
	}

	public void setTestComponentsVisible(boolean visible) {
		limitTimeLabel.setVisible(visible);
		questionLabel.setVisible(visible);
		questionField.setVisible(visible);
		answerField.setVisible(visible);
		situationArea.setVisible(visible);
		testInfoArea.setVisible(visible);
		testInfoLabel.setVisible(visible);
		testInfoScrollPane.setVisible(visible);
		saveButton.setVisible(visible);
		quitTestButton.setVisible(visible);
	}

	public void setSelectForTestComponentsVisible(boolean visible) {
		testCollectionListLabel.setVisible(visible);
		testCollectionList.setVisible(visible);
		testListScrollPane.setVisible(visible);
		limitTimeComboBox.setVisible(visible);
		allowColorByMistakeCntCheckBox.setVisible(visible);
		allowEffectSoundCheckBox.setVisible(visible);
		selectTestButton.setVisible(visible);
		cancelTestButton.setVisible(visible);
		selectlimitTimeLabel.setVisible(visible);
	}

	public void setLoadComponentsVisible(boolean visible) {
		loadCollectionListLabel.setVisible(visible);
		loadCollectionList.setVisible(visible);
		loadListScrollPane.setVisible(visible);
		limitTimeComboBox.setVisible(visible);
		allowColorByMistakeCntCheckBox.setVisible(visible);
		allowEffectSoundCheckBox.setVisible(visible);
		selectLoadButton.setVisible(visible);
		cancelLoadButton.setVisible(visible);
		deleteLoadButton.setVisible(visible);
		retestLoadButton.setVisible(visible);
		selectlimitTimeLabel.setVisible(visible);
	}

	public void setAppendComponentsVisible(boolean visible) {
		vocaApndButton.setVisible(visible);
		apndQuitButton.setVisible(visible);
		englishLabel.setVisible(visible);
		englishField.setVisible(visible);
		koreanLabel.setVisible(visible);
		fileNameLabel.setVisible(visible);
		apndAreaScrollPane.setVisible(visible);
		for (int i = 0; i < koreanFields.length; i++) {
			if (koreanFields[i] == null)
				koreanFields[i] = new JTextField();
			koreanFields[i].setVisible(visible);
		}
	}

	public void setSelectForAppendComponentsVisible(boolean visible) {
		collectionListLabel.setVisible(visible);
		listScrollPane.setVisible(visible);
		newCollectionNameLabel.setVisible(visible);
		newCollectionNameField.setVisible(visible);
		createCollectionButton.setVisible(visible);
		selectCollectionButton.setVisible(visible);
		deleteCollectionButton.setVisible(visible);
		cancelCollectionButton.setVisible(visible);
	}

	public void setSearchComponentsVisible(boolean visible) {
		inputField.setVisible(visible);
		infoLabel.setVisible(visible);
		searchResultArea.setVisible(visible);
		searchAreaScrollPane.setVisible(visible);
		searchButton.setVisible(visible);
		deleteButton.setVisible(visible);
		searchQuitButton.setVisible(visible);
		showTotalButton.setVisible(visible);
		initMisCntButton.setVisible(visible);
	}

	public void setInformationComponentsVisible(boolean visible) {
		developInfoArea.setVisible(visible);
		infoQuitButton.setVisible(visible);
	}

	public void setQuestionFieldColor() {
		final int TEXTCOLOR_GREEN = 1;
		final int TEXTCOLOR_CYAN = 2;
		final int TEXTCOLOR_BLUE = 3;
		final int TEXTCOLOR_YELLOW = 4;
		final int TEXTCOLOR_MAGENTA = 5;
		final int TEXTCOLOR_RED = 6;

		situationArea.setText(dynamicHandler.testSituationInfo());
		switch (dynamicHandler.getQuestionTextColorByMistakeCnt()) {
		case TEXTCOLOR_GREEN:
			questionField.setForeground(Color.green);
			break;
		case TEXTCOLOR_CYAN:
			questionField.setForeground(Color.cyan);
			break;
		case TEXTCOLOR_BLUE:
			questionField.setForeground(Color.blue);
			break;
		case TEXTCOLOR_YELLOW:
			questionField.setForeground(Color.yellow);
			break;
		case TEXTCOLOR_MAGENTA:
			questionField.setForeground(Color.magenta);
			break;
		case TEXTCOLOR_RED:
			questionField.setForeground(Color.red);
			break;
		default:
			questionField.setForeground(Color.black);
		}
	}

	public void setQuitButton() {
		quitButton = new JButton(quitButtonImage);
		quitButton.setBackground(new Color(0, 0, 0, 0));
		quitButton.setBorderPainted(false);
		quitButton.setBounds(1250, 0, 30, 30);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				quitButton.setIcon(quitEnteredButtonImage);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		add(quitButton);
	}

	public void setMenuBar() {
		menuBar = new JLabel(new ImageIcon("images/menuBar.jpg"));
		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);
	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	private void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null);
		paintComponents(g);
		this.repaint();
	}

	private class LimitTimeThread extends Thread {
		private Robot mRobot;

		public LimitTimeThread() {
			try {
				mRobot = new Robot();
			} catch (Exception e) {

			}
		}

		public void run() {
			while (!isTestEnd) {
				int curQuestionNum = dynamicHandler.getQuestionNum();
				boolean isInputAnswer = false;
				for (int time = dynamicHandler.getLimitTime(); time > 0; time--) {
					try {
						limitTimeLabel.setText(getTimeString(time));
						Thread.sleep(1000);
						if (curQuestionNum < dynamicHandler.getQuestionNum()) {
							isInputAnswer = true;
							break;
						}
					} catch (InterruptedException expn) {
						return;
					}
				}
				if (!isInputAnswer) {
					mRobot.keyPress(KeyEvent.VK_ENTER);
					mRobot.keyRelease(KeyEvent.VK_ENTER);
				}
			}
		}
	}

	public String getTimeString(int time) {
		StringBuffer timeSb = new StringBuffer();
		int minute = 0;
		int second = time;
		if (time > 60) {
			minute++;
			second -= 60;
		}
		timeSb.append(minute).append(":").append(String.format("%02d", second));
		return timeSb.toString();
	}

}