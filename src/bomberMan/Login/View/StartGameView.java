/* 
 * File: StartGameView.java
 * -----------------------
 * This class draws the start game view for the user to choose a stage number
 */
package bomberMan.Login.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bomberMan.Login.Model.UserDatabase;
import bomberMan.gamePlay.Model.CONSTANTS;
import bomberMan.gamePlay.Model.GameBoard;
import bomberMan.gamePlay.Model.GameObject;
import bomberMan.gamePlay.View.GameBoardView;

public class StartGameView extends JPanel {

	/*Instance Variables*/
	private int window_width=CONSTANTS.WINDOW_WIDTH;
	private int window_height=CONSTANTS.WINDOW_HEIGHT;
	private Graphics2D myCanvas;
	private JTextField unlockedStageNumber;
	private JLabel error;
	private JLabel Message;
	private JButton startButton;
	private JButton backButton;
	private JButton exitButton;
	private Image backgroundImage;
	private Image loginImage;
	private JFrame myframe;
	private UserDatabase DB;
	private GameBoard myBoard;
	private String previousView;

	/** 
	 * Constructor
	 * This method takes care of the initialization of the start game view
	 * @param x is the JFrame that will be used to display the view
	 * @param DB is the user database 
	 * @param previousView is a string representing the previous view "Pause" or "Main"
	 */
	public StartGameView(JFrame x,UserDatabase DB,String previousView){
		super();
		myframe=x;
		this.DB=DB;
	    this.setOpaque(true);
	    setBackgroundImage();
	    this.setLayout(null);
	    this.previousView=previousView;
	    setTextFields();
	    seterrorText();
	    setButtons();
	    this.repaint();
	    
	}
	/** 
	 * This method takes care of the initialization of the text fields and labels
	 */
	public void setTextFields(){
		setUnlockedStageNumberTextField();
		setMessageText();
	}
	/** 
	 * This method takes care of the initialization and management of the buttons
	 */
	public void setButtons(){
		setStartButton();
		setExitButton();
		setBackButton();

	}
	/** 
	 * This method takes care of the initialization and management of the start button
	 */
	public void setStartButton(){
		startButton= new JButton("Start");
		startButton.setSize(100, 40);
		startButton.setBorderPainted(false);
		startButton.setOpaque(true);
		startButton.setBackground(Color.BLACK);
		startButton.setForeground(Color.white);
		startButton.setLocation(window_width/2+40, window_height-200);
		 this.add(startButton);
		 //adding action listener and directing it to the appropriate function
		 startButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	startButtonActionPerformed(evt);
	            }
		 });
	}
	/** 
	 * This method takes care of the initialization and management of the message label
	 */
	public void setMessageText(){
		Message= new JLabel("Please enter any Stage between 1 and "+this.DB.getCurrentUser().getUnlockedLevel());
		Message.setSize(400, 40);
		Message.setOpaque(true);
		Message.setForeground(Color.BLACK);
		Message.setBackground(new Color(0, 0, 0, 0));
		Message.setLocation(window_width/2-100, window_height-375);
		 this.add(Message);
	}
	/** 
	 * This method takes care of the initialization and management of the error label when
	 * the user try to access a locked stage
	 */
	public void seterrorText(){
		 error= new JLabel("Not allowed to access this stage");
		 error.setSize(300, 40);
		 error.setOpaque(true);
		 error.setForeground(Color.red);
		 error.setBackground(new Color(0, 0, 0, 0));
		 error.setLocation(window_width/2-100, window_height-250);
	}
	/** 
	 * This method takes care of the action performed when the start button is pressed
	 * @param evt is the event triggered when the button is pressed 
	 */
	private void startButtonActionPerformed(ActionEvent evt) 
	{
		if(Integer.parseInt(unlockedStageNumber.getText())>this.DB.getCurrentUser().getUnlockedLevel()){
			this.add(error);
		}
		else{
			myframe.remove(this);
			int [] powerUps = new int[3];
			powerUps[0]=0;
			powerUps[1] = CONSTANTS.BOMB_RANGE1;
			powerUps[2] = CONSTANTS.DEFAULT_SPEEDBOMBERMAN;
			GameBoardView x= new GameBoardView(myframe, CONSTANTS.LIVESBOMBERMAN,powerUps,this.DB,Integer.parseInt(unlockedStageNumber.getText()));
			myframe.setFocusable(true);
			myframe.addKeyListener(x);
			x.setBackground(Color.black);
			x.setVisible(true);
			myframe.add(x);
		        myframe.validate();
		        myframe.repaint();
		        x.requestFocusInWindow();
			myframe.setVisible(true);
		}
        
    }
	/** 
	 * This method takes care of the initialization and management of the exit button
	 */
	public void setExitButton(){
		exitButton= new JButton("EXIT");
		exitButton.setSize(100, 40);
		exitButton.setBorderPainted(false);
		exitButton.setOpaque(true);
		exitButton.setBackground(Color.BLACK);
		exitButton.setForeground(Color.white);
		exitButton.setLocation(startButton.getX()-startButton.getWidth()-10, window_height-200);
		 this.add(exitButton);
		 //adding action listener and directing it to the appropriate function
		 exitButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	exitButtonActionPerformed(evt);
	            }
		 });
	}
	/** 
	 * This method takes care of the action performed when the exit button is pressed
	 * @param evt is the event triggered when the button is pressed 
	 */
	private void exitButtonActionPerformed(ActionEvent evt) 
	{
        System.exit(1);
    }
	/** 
	 * This method takes care of the initialization of the back button
	 */
	public void setBackButton(){
		backButton= new JButton("GO BACK");
		backButton.setSize(100, 40);
		backButton.setBorderPainted(false);
		backButton.setOpaque(true);
		backButton.setBackground(Color.BLACK);
		backButton.setForeground(Color.white);
		backButton.setLocation(exitButton.getX()-backButton.getWidth()-10, window_height-200);
		 this.add(backButton);
		 backButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	backButtonActionPerformed(evt);
	            }
		 });
	}
	/** 
	 * This method takes care of the action performed when the back button is pressed
	 * @param evt is the event triggered when the button is pressed 
	 */
	private void backButtonActionPerformed(ActionEvent evt){
		JPanel x;
		if(previousView.equals("Pause")){
			x=new PauseMenuView(myframe,this.myBoard,this.DB);
		}
		else{
			x=new MainMenuView(myframe,this.DB);
		}
		myframe.remove(this);
		myframe.setFocusable(true);
		x.setBackground(Color.black);
		x.setVisible(true);
		myframe.add(x);
	        myframe.validate();
	        myframe.repaint();
	        x.requestFocusInWindow();
		myframe.setVisible(true);
	}
	/** 
	 * This method takes care of the initialization and drawing of the text field 
	 * that the user will enter the stage number in
	 */
	public void setUnlockedStageNumberTextField(){
		unlockedStageNumber=new JTextField();
		unlockedStageNumber.setSize(220, 25);
		unlockedStageNumber.setBackground(Color.BLACK);
		unlockedStageNumber.setCaretColor(Color.BLUE);
		unlockedStageNumber.setForeground(Color.WHITE);
		unlockedStageNumber.setLocation(window_width/2,window_height/3 );
		this.add(unlockedStageNumber);	
	}
	/** 
	 * This method takes care of the setting and uploading of the background images
	 */
	public void setBackgroundImage(){
	    backgroundImage = Toolkit.getDefaultToolkit().createImage(GameObject.class.getResource("/image/" + "giphy.gif"));
	    loginImage=Toolkit.getDefaultToolkit().createImage(GameObject.class.getResource("/image/" + "StartGame.png"));
	    backgroundImage=backgroundImage.getScaledInstance(CONSTANTS.WINDOW_WIDTH, CONSTANTS.WINDOW_HEIGHT, Image.SCALE_DEFAULT);
	}
	/** 
	 * This method takes care of the painting of the background images 
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.myCanvas = (Graphics2D) g;	
		myCanvas.drawImage(backgroundImage, 0,0,this);
		myCanvas.drawImage(loginImage, (backgroundImage.getWidth(null)/2)-(loginImage.getWidth(null)/2),(backgroundImage.getHeight(null)/2)-(loginImage.getHeight(null)/2),this);
	}


}
