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
import javax.swing.JPanel;

import bomberMan.gamePlay.Controller.EnemyController;
import bomberMan.gamePlay.Model.GameBoard;
import bomberMan.gamePlay.View.GameBoardView;
import bomberMan.gamePlay.View.GamePlayView;

public class PauseMenuView extends JPanel
{
	//constants to be moved
	private Graphics2D myCanvas;
	private JButton resumeGameButton;
	private JButton startNewGameButton;
	private JButton saveGameButton;
	private JButton leaderBoardButton;
	private JButton returnToMainMenuButton;
	private JButton exitButton;
	private Image backgroundImage;
	private Image mainMenuImage;
	private JFrame myframe;
	private int startFrame=110;
	private GameBoard myBoard;

	
	public PauseMenuView(JFrame x, GameBoard myBoard)
	{
		super();
		myframe=x;
		this.myBoard=myBoard;
	    this.setOpaque(true);
	    setBackgroundImage();
	    this.setLayout(null);
	    setButtons();
	    this.repaint();
	    
	}
	public void setButtons()
	{
		setResumeGameButtonButton();
		setStartNewGameButtonButton();
		setSaveGameButton();
		setLeaderBoardButton();
		setReturnToMainMenuButton();
		setExitButton();
	}
	public void setResumeGameButtonButton()
	{
		resumeGameButton= new JButton("RESUME GAME");
		resumeGameButton.setSize(200, 40);
		resumeGameButton.setBorderPainted(false);
		resumeGameButton.setOpaque(true);
		resumeGameButton.setBackground(Color.BLACK);
		resumeGameButton.setForeground(Color.white);
		resumeGameButton.setLocation(CONSTANTS.WINDOW_WIDTH/2-100, startFrame);
		 this.add(resumeGameButton);
		 //adding action listener and directing it to the appropiate function
		 resumeGameButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	resumeGameButtonActionPerformed(evt);
	            }
		 });
	}
	private void resumeGameButtonActionPerformed(ActionEvent evt) 
	{
		myframe.remove(this);
		GameBoardView x= new GameBoardView(myframe,myBoard);
		x.unpause();
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
	public void setStartNewGameButtonButton()
	{
		startNewGameButton= new JButton("START NEW GAME");
		startNewGameButton.setSize(200, 40);
		startNewGameButton.setBorderPainted(false);
		startNewGameButton.setOpaque(true);
		startNewGameButton.setBackground(Color.BLACK);
		startNewGameButton.setForeground(Color.white);
		startNewGameButton.setLocation(CONSTANTS.WINDOW_WIDTH/2-100, resumeGameButton.getY()+resumeGameButton.getHeight()+10);
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
		GameBoardView x=new GameBoardView(myframe);
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
	public void setSaveGameButton()
	{
		saveGameButton= new JButton("SAVE GAME");
		saveGameButton.setSize(200, 40);
		saveGameButton.setBorderPainted(false);
		saveGameButton.setOpaque(true);
		saveGameButton.setBackground(Color.BLACK);
		saveGameButton.setForeground(Color.white);
		saveGameButton.setLocation(CONSTANTS.WINDOW_WIDTH/2-100, startNewGameButton.getY()+startNewGameButton.getHeight()+10);
		 this.add(saveGameButton);
		 saveGameButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	saveGameButtonActionPerformed(evt);
	            }
		 });
	}
	private void saveGameButtonActionPerformed(ActionEvent evt) 
	{
        
    }
	public void setLeaderBoardButton()
	{
		leaderBoardButton= new JButton("VIEW LEADER BOARD");
		leaderBoardButton.setSize(200, 40);
		leaderBoardButton.setBorderPainted(false);
		leaderBoardButton.setOpaque(true);
		leaderBoardButton.setBackground(Color.BLACK);
		leaderBoardButton.setForeground(Color.white);
		leaderBoardButton.setLocation(CONSTANTS.WINDOW_WIDTH/2-100, saveGameButton.getY()+saveGameButton.getHeight()+10);
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

	public void setReturnToMainMenuButton()
	{
		returnToMainMenuButton= new JButton("RETURN TO MAIN MENU");
		returnToMainMenuButton.setSize(200, 40);
		returnToMainMenuButton.setBorderPainted(false);
		returnToMainMenuButton.setOpaque(true);
		returnToMainMenuButton.setBackground(Color.BLACK);
		returnToMainMenuButton.setForeground(Color.white);
		returnToMainMenuButton.setLocation(CONSTANTS.WINDOW_WIDTH/2-100, leaderBoardButton.getY()+leaderBoardButton.getHeight()+10);
		 this.add(returnToMainMenuButton);
		 //adding action listener and directing it to the appropiate function
		 returnToMainMenuButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            	returnToMainMenuButtonActionPerformed(evt);
	            }
		 });
	}

	private void returnToMainMenuButtonActionPerformed(ActionEvent evt) 
	{
		myframe.remove(this);
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
	public void setExitButton()
	{
		exitButton= new JButton("EXIT");
		exitButton.setSize(200, 40);
		exitButton.setBorderPainted(false);
		exitButton.setOpaque(true);
		exitButton.setBackground(Color.BLACK);
		exitButton.setForeground(Color.white);
		exitButton.setLocation(CONSTANTS.WINDOW_WIDTH/2-100, returnToMainMenuButton.getY()+returnToMainMenuButton.getHeight()+10);
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
	    mainMenuImage=Toolkit.getDefaultToolkit().createImage("PauseMenu.png");
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
		//myCanvas.drawImage(backgroundImage, 0,0,this);
		myCanvas.drawImage(mainMenuImage, (CONSTANTS.WINDOW_WIDTH/2)-(mainMenuImage.getWidth(null)/2),(CONSTANTS.WINDOW_HEIGHT/2)-(mainMenuImage.getHeight(null)/2),this);
	}


}
