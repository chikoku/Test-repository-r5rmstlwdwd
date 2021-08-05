
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