/* 
 * File: SignUpView.java
 * -----------------------
 * This class draws the main menu game view for the user once the user logs in and presses sign up.
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bomberMan.Login.Controller.SignUpController;
import bomberMan.Login.Model.UserDatabase;
import bomberMan.gamePlay.Model.GameObject;
import bomberMan.gamePlay.View.GameBoardView;

public class SignUpView extends JPanel   {
	
	/*Instance Variables*/
	private Graphics2D myCanvas;
	private JTextField userNameInput;
	private JTextField userFirstNameInput;
	private JPasswordField userPasswordInput;
	private JPasswordField confirmUserPasswordInput;
	private JLabel error;
	private JButton confirmButton;
	private JButton goBackButton;
	private JButton exitButton;
	private Image backgroundImage;
	private Image loginImage;
	private JFrame myframe;
	private UserDatabase DB;
	private int startFrame=110;

	/** 
	 * Constructor
	 * This method takes care of the initialization of the start game view
	 * @param x is the JFrame that will be used to display the view
	 */
	public SignUpView(JFrame x){
		super();
		myframe=x;
		DB=new UserDatabase("CSVfiles/trial.csv");
	    this.setOpaque(true);
	    setBackgroundImage();
	    this.setLayout(null);
	    setButtons();
	    setTextFields();
	    setErrorText();
	    this.repaint();
	    
	}
	/** 
	 * This method takes care of the initialization of the text fields 
	 */
	public void setTextFields(){
		setUserFirstNameTextField();
		setUserNameTextField();
		setPasswordTextField();
		setConfirmPasswordTextField();
	}
	/** 
	 * This method takes care of the initialization of the user real name text field
	 */
	public void setUserFirstNameTextField(){
		userFirstNameInput=new JTextField();
		userFirstNameInput.setSize(220, 25);
		userFirstNameInput.setBackground(Color.BLACK);
		userFirstNameInput.setCaretColor(Color.BLUE);
		userFirstNameInput.setForeground(Color.WHITE);
		userFirstNameInput.setLocation(CONSTANTS.WINDOW_WIDTH/2,startFrame+30 );
		this.add(userFirstNameInput);
		
	}
	/** 
	 * This method takes care of the initialization of the user name text field
	 */
	public void setUserNameTextField(){
		userNameInput=new JTextField();
		userNameInput.setSize(220, 25);
		userNameInput.setBackground(Color.BLACK);
		userNameInput.setCaretColor(Color.BLUE);
		userNameInput.setForeground(Color.WHITE);
		userNameInput.setLocation(CONSTANTS.WINDOW_WIDTH/2,userFirstNameInput.getY()+userFirstNameInput.getHeight()+10 );
		this.add(userNameInput);	
	}
	/** 
	 * This method takes care of the initialization of the password text field
	 */
	public void setPasswordTextField(){
		userPasswordInput=new JPasswordField();
		userPasswordInput.setSize(220, 25);
		userPasswordInput.setBackground(Color.BLACK);
		userPasswordInput.setCaretColor(Color.BLUE);
		userPasswordInput.setForeground(Color.WHITE);
		userPasswordInput.setLocation(CONSTANTS.WINDOW_WIDTH/2, userNameInput.getY()+userNameInput.getHeight()+10);
		this.add(userPasswordInput);
	}
	/** 
	 * This method takes care of the initialization of the confirm password text field
	 */
	public void setConfirmPasswordTextField(){
		confirmUserPasswordInput=new JPasswordField();
		confirmUserPasswordInput.setSize(220, 25);
		confirmUserPasswordInput.setBackground(Color.BLACK);
		confirmUserPasswordInput.setCaretColor(Color.BLUE);
		confirmUserPasswordInput.setForeground(Color.WHITE);
		confirmUserPasswordInput.setLocation(CONSTANTS.WINDOW_WIDTH/2, userPasswordInput.getY()+userPasswordInput.getHeight()+10);
		this.add(confirmUserPasswordInput);
	}
	/** 
	 * This method takes care of the initialization of the error label if the user exits, password mismatch..etc
	 */
	public void setErrorText(){
		 error= new JLabel();
		 error.setSize(200, 40);
		 error.setOpaque(true);
		 error.setForeground(Color.red);
		 error.setBackground(new Color(0, 0, 0, 0));
		 error.setLocation(CONSTANTS.WINDOW_WIDTH/2, confirmButton.getY()+confirmButton.getHeight()+10);
	}
	/** 
	 * This method takes care of the initialization and management of the buttons
	 */
	public void setButtons(){
		setConfirmButton();
		setGoBackButton();
		setExitButton();
	}
	/** 
	 * This method takes care of the initialization and management of the confirm button
	 */
	public void setConfirmButton(){
		confirmButton= new JButton("CONFIRM");
		confirmButton.setSize(110, 40);
		confirmButton.setBorderPainted(false);
		confirmButton.setOpaque(true);
		confirmButton.setBackground(Color.BLACK);
		confirmButton.setForeground(Color.white);
		 confirmButton.setLocation(CONSTANTS.WINDOW_WIDTH/2+40, CONSTANTS.WINDOW_HEIGHT-200);
		 this.add(confirmButton);
		 //adding action listener and directing it to the appropriate function
		 confirmButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	setConfirmButtonActionPerformed(evt);
	            }
		 });
	}
	/** 
	 * This method takes care of the action performed when the confirm button is pressed
	 * @param evt is the event triggered when the button is pressed 
	 */
	private void setConfirmButtonActionPerformed(ActionEvent evt) {
	        SignUpController myController=new SignUpController(DB);
	        int errorNum=myController.signUp(userFirstNameInput.getText(), userNameInput.getText(), userPasswordInput.getText(), confirmUserPasswordInput.getText());
	        if(errorNum==3){
	        	error.setText("please fill out all the information above");
	        	this.add(error);
	        }
	        else if(errorNum==1){
	        	error.setText("user name already exists");
	        	this.add(error);
	        }
	        //check also for password match 
	        else if(errorNum==4){
	        	error.setText("Password doesn't match");
	        	this.add(error);
	        }
	        else if(errorNum==2) {
	        	error.setText("Password is wrong format");
	        	this.add(error);
	        }
	        
	        else {
	        	myframe.remove(this);
				MainMenuView x=new MainMenuView(myframe,this.DB);
				myframe.setFocusable(true);
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
		exitButton.setLocation(CONSTANTS.WINDOW_WIDTH/2-70, CONSTANTS.WINDOW_HEIGHT-200);
		 this.add(exitButton);
		 //adding action listener and directing it to the appropiate function
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
	 * This method takes care of the initialization and management of the confirm button
	 */
	public void setGoBackButton(){
		goBackButton= new JButton("<<BACK");
		goBackButton.setSize(120, 40);
		goBackButton.setBorderPainted(false);
		goBackButton.setOpaque(true);
		goBackButton.setBackground(Color.BLACK);
		goBackButton.setForeground(Color.white);
		goBackButton.setLocation(CONSTANTS.WINDOW_WIDTH/2-200, CONSTANTS.WINDOW_HEIGHT-200);
		 this.add(goBackButton);
		 goBackButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	goBackButtonActionPerformed(evt);
	            }
		 });
	}
	/** 
	 * This method takes care of the action performed when the back button is pressed
	 * @param evt is the event triggered when the button is pressed 
	 */
	private void goBackButtonActionPerformed(ActionEvent evt){
		myframe.remove(this);
		LoginView x=new LoginView(myframe);
		myframe.setFocusable(true);
		//myframe.addKeyListener(x);
		x.setBackground(Color.black);
		x.setVisible(true);
		myframe.add(x);
	        myframe.validate();
	        myframe.repaint();
	        x.requestFocusInWindow();
		myframe.setVisible(true);
	}
	/** 
	 * This method takes care of the setting and uploading of the background images
	 */
	public void setBackgroundImage(){
	    backgroundImage = Toolkit.getDefaultToolkit().createImage(GameObject.class.getResource("/image/" + "giphy.gif"));
	    loginImage=Toolkit.getDefaultToolkit().createImage(GameObject.class.getResource("/image/" + "SignupMenu.png"));
	    backgroundImage=backgroundImage.getScaledInstance(CONSTANTS.WINDOW_WIDTH, CONSTANTS.WINDOW_HEIGHT, Image.SCALE_DEFAULT);
	}
	/** 
	 * This method takes care of the painting of the background images 
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.myCanvas = (Graphics2D) g;	
		myCanvas.drawImage(backgroundImage, 0,0,this);
		myCanvas.drawImage(loginImage, (CONSTANTS.WINDOW_WIDTH/2)-(loginImage.getWidth(null)/2),(CONSTANTS.WINDOW_HEIGHT/2)-(loginImage.getHeight(null)/2),this);
	}

}
