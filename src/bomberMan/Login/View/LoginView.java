package bomberMan.Login.View;

import javax.swing.*;

import bomberMan.Login.Controller.LoginController;
import bomberMan.Login.Model.UserDatabase;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;

public class LoginView extends JPanel   
{
	//constants to be moved
	private int window_width=CONSTANTS.WINDOW_WIDTH;
	private int window_height=CONSTANTS.WINDOW_HEIGHT;
	private Graphics2D myCanvas;
	private JTextField userNameInput;
	private JPasswordField userPasswordInput;
	private JLabel error;
	private JButton loginButton;
	private JButton signupButton;
	private JButton exitButton;
	private Image backgroundImage;
	private Image loginImage;
	private JFrame myframe;
	private UserDatabase DB;

	
	public LoginView(JFrame x)
	{
		super();
		myframe=x;
		DB=new UserDatabase("CSVfiles/trial.csv");
	    this.setOpaque(true);
	   // this.setBackground(Color.WHITE);
	    setBackgroundImage();
	    this.setLayout(null);
	    setTextFields();
	    seterrorText();
	    setButtons();
	    this.repaint();
	    
	}
	public void setTextFields()
	{
		setUserNameTextField();
		setPasswordTextField();
	}
	public void setButtons()
	{
		setLoginButton();
		setExitButton();
		setSignupButton();

	}
	public void setLoginButton()
	{
		 loginButton= new JButton("LOGIN");
		 loginButton.setSize(100, 40);
		 loginButton.setBorderPainted(false);
		 loginButton.setOpaque(true);
		 loginButton.setBackground(Color.BLACK);
		 loginButton.setForeground(Color.white);
		 loginButton.setLocation(window_width/2+40, window_height-200);
		 this.add(loginButton);
		 //adding action listener and directing it to the appropiate function
		 loginButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	loginButtonActionPerformed(evt);
	            }
		 });
	}
	public void seterrorText()
	{
		 error= new JLabel("Invalid username and password");
		 error.setSize(200, 40);
		 error.setOpaque(true);
		 error.setForeground(Color.red);
		 error.setBackground(new Color(0, 0, 0, 0));
		 error.setLocation(window_width/2, window_height-250);
		 //adding action listener and directing it to the appropiate function
	}
	private void loginButtonActionPerformed(ActionEvent evt) 
	{
        System.out.println();
        System.out.println();
        LoginController myController=new LoginController(DB);
        int errorCode=myController.login(userNameInput.getText(), userPasswordInput.getText());
        if(errorCode==0)
        {	myframe.remove(this);
			MainMenuView x=new MainMenuView(myframe);
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
        else
        {
        	this.add(error);
        }
        
    }
	public void setExitButton()
	{
		exitButton= new JButton("EXIT");
		exitButton.setSize(100, 40);
		exitButton.setBorderPainted(false);
		exitButton.setOpaque(true);
		exitButton.setBackground(Color.BLACK);
		exitButton.setForeground(Color.white);
		exitButton.setLocation(loginButton.getX()-exitButton.getWidth()-10, window_height-200);
		 this.add(exitButton);
		 //adding action listener and directing it to the appropiate function
		 exitButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	exitButtonActionPerformed(evt);
	            }
		 });
	}
	private void exitButtonActionPerformed(ActionEvent evt) 
	{
        System.exit(1);
    }
	

	public void setSignupButton()
	{
		signupButton= new JButton("SIGN UP");
		signupButton.setSize(100, 40);
		signupButton.setBorderPainted(false);
		signupButton.setOpaque(true);
		signupButton.setBackground(Color.BLACK);
		signupButton.setForeground(Color.white);
		signupButton.setLocation(exitButton.getX()-signupButton.getWidth()-10, window_height-200);
		 this.add(signupButton);
		 signupButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	signupButtonActionPerformed(evt);
	            }
		 });
	}
	private void signupButtonActionPerformed(ActionEvent evt)
	{
		myframe.remove(this);
		SignUpView x=new SignUpView(myframe);
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
	public void setPasswordTextField()
	{
		userPasswordInput=new JPasswordField();
		userPasswordInput.setSize(220, 25);
		userPasswordInput.setBackground(Color.BLACK);
		userPasswordInput.setCaretColor(Color.BLUE);
		userPasswordInput.setForeground(Color.WHITE);
		userPasswordInput.setLocation(window_width/2,(window_height/3)+userNameInput.getHeight()+10 );
		userPasswordInput.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	loginButtonActionPerformed(evt);
	            }
		 });
		this.add(userPasswordInput);
		
	}
	public void setUserNameTextField()
	{
		userNameInput=new JTextField();
		userNameInput.setSize(220, 25);
		userNameInput.setBackground(Color.BLACK);
		userNameInput.setCaretColor(Color.BLUE);
		userNameInput.setForeground(Color.WHITE);
		userNameInput.setLocation(window_width/2,window_height/3 );
		this.add(userNameInput);	
	}
	//setting the background image, should change the size of the window to constants
	public void setBackgroundImage()
	{
	    backgroundImage = Toolkit.getDefaultToolkit().createImage("giphy.gif");
	    loginImage=Toolkit.getDefaultToolkit().createImage("LoginMenu.png");
	    backgroundImage=backgroundImage.getScaledInstance(CONSTANTS.WINDOW_WIDTH, CONSTANTS.WINDOW_HEIGHT, Image.SCALE_DEFAULT);
	}
	
	//updates the login view, not used till now
	public void updateLoginView()
	{
		this.repaint();
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.myCanvas = (Graphics2D) g;	
		myCanvas.drawImage(backgroundImage, 0,0,this);
		myCanvas.drawImage(loginImage, (backgroundImage.getWidth(null)/2)-(loginImage.getWidth(null)/2),(backgroundImage.getHeight(null)/2)-(loginImage.getHeight(null)/2),this);
	}


}
