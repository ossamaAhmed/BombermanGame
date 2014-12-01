package bomberMan.Login.View;

import javax.swing.*;

import bomberMan.Login.Controller.LoginController;
import bomberMan.Login.Model.UserDatabase;
import bomberMan.gamePlay.Model.GameBoard;
import bomberMan.gamePlay.Model.GameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;

public class SaveGameView extends JPanel  
{
	/*Instance Variables*/
	private int window_width=CONSTANTS.WINDOW_WIDTH;
	private int window_height=CONSTANTS.WINDOW_HEIGHT;
	private Graphics2D myCanvas;
	private JTextField savedGameName;
	private JLabel error;
	private JButton saveButton;
	private JButton backButton;
	private JButton exitButton;
	private Image backgroundImage;
	private Image loginImage;
	private JFrame myframe;
	private UserDatabase DB;
	private GameBoard myBoard;

	/** 
	 * Constructor
	 * This method takes care of the initialization of the load game view
	 * @param x is the JFrame that will be used to display the view
	 * @param DB is the user database 
	 * @param myBoard is the game board that the user wants to go back to after saving the game
	 */		
	public SaveGameView(JFrame x, GameBoard myBoard,UserDatabase DB){
		super();
		myframe=x;
		this.DB=DB;
	    this.setOpaque(true);
	    this.myBoard=myBoard;
	    setBackgroundImage();
	    this.setLayout(null);
	    setTextFields();
	    seterrorText();
	    setButtons();
	    this.repaint();
	    
	}
	/** 
	 * This method takes care of the initialization of the text fields to be displayed
	 */		
	public void setTextFields(){
		setSavedGameNameTextField();
	}
	/** 
	 * This method takes care of the initialization of the buttons to be displayed
	 */	
	public void setButtons(){
		setSaveButton();
		setExitButton();
		setBackButton();
	}
	/** 
	 * This method takes care of the initialization of the save button
	 */	
	public void setSaveButton(){
		saveButton= new JButton("Save");
		saveButton.setSize(100, 40);
		saveButton.setBorderPainted(false);
		saveButton.setOpaque(true);
		saveButton.setBackground(Color.BLACK);
		saveButton.setForeground(Color.white);
		saveButton.setLocation(window_width/2+40, window_height-200);
		 this.add(saveButton);
		 //adding action listener and directing it to the appropriate function
		 saveButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	saveButtonActionPerformed(evt);
	            }
		 });
	}
	/** 
	 * This method takes care of the initialization of the error label 
	 */
	public void seterrorText(){
		 error= new JLabel("Invalid username and password");
		 error.setSize(200, 40);
		 error.setOpaque(true);
		 error.setForeground(Color.red);
		 error.setBackground(new Color(0, 0, 0, 0));
		 error.setLocation(window_width/2, window_height-250);
	}
	/** 
	 * This method takes care of the action performed when the save button is pressed
	 * @param evt is the event triggered when the button is pressed 
	 */
	private void saveButtonActionPerformed(ActionEvent evt) {
        System.out.println();
        System.out.println();
    
        if(!DB.getCurrentUser().gameExists(savedGameName.getText()).equals(" ")) {
        	JOptionPane.showMessageDialog(null, "Game already exists!");
        	return;
        } else {
            DB.getCurrentUser().saveGame(this.myBoard, savedGameName.getText());
        }
        myframe.remove(this);
			PauseMenuView x=new PauseMenuView(myframe,this.myBoard,this.DB);
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
	 * This method takes care of the initialization of the exit button
	 */	
	public void setExitButton(){
		exitButton= new JButton("EXIT");
		exitButton.setSize(100, 40);
		exitButton.setBorderPainted(false);
		exitButton.setOpaque(true);
		exitButton.setBackground(Color.BLACK);
		exitButton.setForeground(Color.white);
		exitButton.setLocation(saveButton.getX()-saveButton.getWidth()-10, window_height-200);
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
	private void exitButtonActionPerformed(ActionEvent evt) {
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
		myframe.remove(this);
		PauseMenuView x=new PauseMenuView(myframe,this.myBoard,this.DB);
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
	 * This method takes care of the initialization of save game text field
	 */
	public void setSavedGameNameTextField(){
		savedGameName=new JTextField();
		savedGameName.setSize(220, 25);
		savedGameName.setBackground(Color.BLACK);
		savedGameName.setCaretColor(Color.BLUE);
		savedGameName.setForeground(Color.WHITE);
		savedGameName.setLocation(window_width/2,window_height/3 );
		this.add(savedGameName);	
	}
	/** 
	 * This method takes care of the setting and uploading of the background images
	 */
	public void setBackgroundImage(){
	    backgroundImage = Toolkit.getDefaultToolkit().createImage(GameObject.class.getResource("/image/" + "giphy.gif"));
	    loginImage=Toolkit.getDefaultToolkit().createImage(GameObject.class.getResource("/image/" + "SaveGame.png"));
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
