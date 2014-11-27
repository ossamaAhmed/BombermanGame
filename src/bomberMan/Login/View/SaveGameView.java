package bomberMan.Login.View;

import javax.swing.*;

import bomberMan.Login.Controller.LoginController;
import bomberMan.Login.Model.UserDatabase;
import bomberMan.gamePlay.Model.GameBoard;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;

public class SaveGameView extends JPanel  
{
	//constants to be moved
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

	
	public SaveGameView(JFrame x, GameBoard myBoard,UserDatabase DB)
	{
		super();
		myframe=x;
//		DB=new UserDatabase("CSVfiles/trial.csv");
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
	public void setTextFields()
	{
		setSavedGameNameTextField();
	}
	public void setButtons()
	{
		setSaveButton();
		setExitButton();
		setBackButton();

	}
	public void setSaveButton()
	{
		saveButton= new JButton("Save");
		saveButton.setSize(100, 40);
		saveButton.setBorderPainted(false);
		saveButton.setOpaque(true);
		saveButton.setBackground(Color.BLACK);
		saveButton.setForeground(Color.white);
		saveButton.setLocation(window_width/2+40, window_height-200);
		 this.add(saveButton);
		 //adding action listener and directing it to the appropiate function
		 saveButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	saveButtonActionPerformed(evt);
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
	private void saveButtonActionPerformed(ActionEvent evt) 
	{
        System.out.println();
        System.out.println();
    
        if(!DB.getCurrentUser().gameExists(savedGameName.getText()).equals(" ")) {
        	// DIsplay a message box
        	JOptionPane.showMessageDialog(null, "Game already exists!");
        	return;
        } else {
            DB.getCurrentUser().saveGame(this.myBoard, savedGameName.getText());
        }

//        int errorCode=myController.login(savedGameName.getText(), savedGameName.getText());
//        if(errorCode==0)
//        {	
        myframe.remove(this);
			PauseMenuView x=new PauseMenuView(myframe,this.myBoard,this.DB);
			myframe.setFocusable(true);
			//myframe.addKeyListener(x);
			x.setBackground(Color.black);
			x.setVisible(true);
			myframe.add(x);
		        myframe.validate();
		        myframe.repaint();
		        x.requestFocusInWindow();
			myframe.setVisible(true);
//		}
//        else
//        {
//        	this.add(error);
//        }
        
    }
	public void setExitButton()
	{
		exitButton= new JButton("EXIT");
		exitButton.setSize(100, 40);
		exitButton.setBorderPainted(false);
		exitButton.setOpaque(true);
		exitButton.setBackground(Color.BLACK);
		exitButton.setForeground(Color.white);
		exitButton.setLocation(saveButton.getX()-saveButton.getWidth()-10, window_height-200);
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
	

	public void setBackButton()
	{
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
	private void backButtonActionPerformed(ActionEvent evt)
	{
		myframe.remove(this);
		PauseMenuView x=new PauseMenuView(myframe,this.myBoard,this.DB);
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

	public void setSavedGameNameTextField()
	{
		savedGameName=new JTextField();
		savedGameName.setSize(220, 25);
		savedGameName.setBackground(Color.BLACK);
		savedGameName.setCaretColor(Color.BLUE);
		savedGameName.setForeground(Color.WHITE);
		savedGameName.setLocation(window_width/2,window_height/3 );
		this.add(savedGameName);	
	}
	//setting the background image, should change the size of the window to constants
	public void setBackgroundImage()
	{
	    backgroundImage = Toolkit.getDefaultToolkit().createImage("giphy.gif");
	    loginImage=Toolkit.getDefaultToolkit().createImage("SaveGame.png");
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
