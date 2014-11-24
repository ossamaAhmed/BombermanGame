package bomberMan.Login.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import bomberMan.gamePlay.View.GameBoardView;
import bomberMan.gamePlay.View.GamePlayView;

public class MainMenuView extends JPanel
{
	//constants to be moved
	private Graphics2D myCanvas;
	private JButton startNewGameButton;
	private JButton loadGameButton;
	private JButton leaderBoardButton;
	private JButton modifyProfileButton;
	private JButton signOutButton;
	private JButton exitButton;
	private Image backgroundImage;
	private Image mainMenuImage;
	private JFrame myframe;
	private int startFrame=110;

	
	public MainMenuView(JFrame x)
	{
		super();
		myframe=x;
	    this.setOpaque(true);
	    setBackgroundImage();
	    this.setLayout(null);
	    setButtons();
	    this.repaint();
	    
	}


	
	public void setButtons()
	{
		setStartNewGameButtonButton();
		setLoadGameButton();
		setLeaderBoardButton();
		setModifyProfileButton();
		setSignOutButton();
		setExitButton();
	}
	public void setStartNewGameButtonButton()
	{
		startNewGameButton= new JButton("START NEW GAME");
		startNewGameButton.setSize(200, 40);
		startNewGameButton.setBorderPainted(false);
		startNewGameButton.setOpaque(true);
		startNewGameButton.setBackground(Color.BLACK);
		startNewGameButton.setForeground(Color.white);
		startNewGameButton.setLocation(CONSTANTS.WINDOW_WIDTH/2-100, startFrame);
		 this.add(startNewGameButton);
		 //adding action listener and directing it to the appropriate function
		 startNewGameButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	startNewGameButtonButtonActionPerformed(evt);
	            }
		 });
	}
	private void startNewGameButtonButtonActionPerformed(ActionEvent evt) 
	{
		myframe.remove(this);
		GameBoardView x=new GameBoardView(myframe, CONSTANTS.LIVESBOMBERMAN);
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
	public void setLoadGameButton()
	{
		loadGameButton= new JButton("LOAD GAME");
		loadGameButton.setSize(200, 40);
		loadGameButton.setBorderPainted(false);
		loadGameButton.setOpaque(true);
		loadGameButton.setBackground(Color.BLACK);
		loadGameButton.setForeground(Color.white);
		loadGameButton.setLocation(CONSTANTS.WINDOW_WIDTH/2-100, startNewGameButton.getY()+startNewGameButton.getHeight()+10);
		 this.add(loadGameButton);
		 loadGameButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	loadGameButtonActionPerformed(evt);
	            }
		 });
	}
	private void loadGameButtonActionPerformed(ActionEvent evt) 
	{
		myframe.remove(this);
		LoadGameView x=new LoadGameView(myframe);
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
	public void setLeaderBoardButton()
	{
		leaderBoardButton= new JButton("VIEW LEADER BOARD");
		leaderBoardButton.setSize(200, 40);
		leaderBoardButton.setBorderPainted(false);
		leaderBoardButton.setOpaque(true);
		leaderBoardButton.setBackground(Color.BLACK);
		leaderBoardButton.setForeground(Color.white);
		leaderBoardButton.setLocation(CONSTANTS.WINDOW_WIDTH/2-100, loadGameButton.getY()+loadGameButton.getHeight()+10);
		 this.add(leaderBoardButton);
		 //adding action listener and directing it to the appropiate function
		 leaderBoardButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	leaderBoardButtonActionPerformed(evt);
	            }
		 });
	}
	private void leaderBoardButtonActionPerformed(ActionEvent evt) 
	{
        
    }
	public void setModifyProfileButton()
	{
		modifyProfileButton= new JButton("MODIFY PROFILE");
		modifyProfileButton.setSize(200, 40);
		modifyProfileButton.setBorderPainted(false);
		modifyProfileButton.setOpaque(true);
		modifyProfileButton.setBackground(Color.BLACK);
		modifyProfileButton.setForeground(Color.white);
		modifyProfileButton.setLocation(CONSTANTS.WINDOW_WIDTH/2-100, leaderBoardButton.getY()+leaderBoardButton.getHeight()+10);
		 this.add(modifyProfileButton);
		 //adding action listener and directing it to the appropiate function
		 modifyProfileButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	modifyProfileButtonActionPerformed(evt);
	            }
		 });
	}
	private void modifyProfileButtonActionPerformed(ActionEvent evt) 
	{
        
    }
	public void setSignOutButton()
	{
		signOutButton= new JButton("SIGN OUT");
		signOutButton.setSize(200, 40);
		signOutButton.setBorderPainted(false);
		signOutButton.setOpaque(true);
		signOutButton.setBackground(Color.BLACK);
		signOutButton.setForeground(Color.white);
		signOutButton.setLocation(CONSTANTS.WINDOW_WIDTH/2-100, modifyProfileButton.getY()+modifyProfileButton.getHeight()+10);
		 this.add(signOutButton);
		 //adding action listener and directing it to the appropiate function
		 signOutButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	signOutButtonActionPerformed(evt);
	            }
		 });
	}

	private void signOutButtonActionPerformed(ActionEvent evt) 
	{
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
	public void setExitButton()
	{
		exitButton= new JButton("EXIT");
		exitButton.setSize(200, 40);
		exitButton.setBorderPainted(false);
		exitButton.setOpaque(true);
		exitButton.setBackground(Color.BLACK);
		exitButton.setForeground(Color.white);
		exitButton.setLocation(CONSTANTS.WINDOW_WIDTH/2-100, signOutButton.getY()+signOutButton.getHeight()+10);
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
	
	//setting the background image, should change the size of the window to constants
	public void setBackgroundImage()
	{
	    backgroundImage = Toolkit.getDefaultToolkit().createImage("giphy.gif");
	    mainMenuImage=Toolkit.getDefaultToolkit().createImage("MainMenu.png");
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
		myCanvas.drawImage(mainMenuImage, (CONSTANTS.WINDOW_WIDTH/2)-(mainMenuImage.getWidth(null)/2),(CONSTANTS.WINDOW_HEIGHT/2)-(mainMenuImage.getHeight(null)/2),this);
	}

}
