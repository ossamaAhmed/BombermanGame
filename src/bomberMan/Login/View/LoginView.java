package bomberMan.Login.View;

import javax.swing.*;

import bomberMan.gamePlay.View.GameBoardView;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;

public class LoginView extends JPanel   
{
	//constants to be moved
	private int window_width=900;
	private int window_height=500;
	private Graphics2D myCanvas;
	private JTextField userNameInput;
	private JPasswordField userPasswordInput;
	private JButton loginButton;
	private JButton signupButton;
	private JButton exitButton;
	private Image backgroundImage;
	private Image loginImage;
	private JFrame myframe;

	
	public LoginView(JFrame x)
	{
		super();
		myframe=x;
	    this.setOpaque(true);
	   // this.setBackground(Color.WHITE);
	    setBackgroundImage();
	    this.setLayout(null);
	    setTextFields();
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
		setSignupButton();
		setExitButton();
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
	private void loginButtonActionPerformed(ActionEvent evt) 
	{
        System.out.println(userNameInput.getText());
        System.out.println(userPasswordInput.getText());
        
    }
	public void setExitButton()
	{
		exitButton= new JButton("EXIT");
		exitButton.setSize(100, 40);
		exitButton.setBorderPainted(false);
		exitButton.setOpaque(true);
		exitButton.setBackground(Color.BLACK);
		exitButton.setForeground(Color.white);
		exitButton.setLocation(window_width/2-70, window_height-200);
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
		signupButton.setLocation(window_width/2-200, window_height-200);
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
		GameBoardView x=new GameBoardView();
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
	    backgroundImage=backgroundImage.getScaledInstance(window_width, window_height, Image.SCALE_DEFAULT);
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
