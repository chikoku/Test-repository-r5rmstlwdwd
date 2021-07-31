
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