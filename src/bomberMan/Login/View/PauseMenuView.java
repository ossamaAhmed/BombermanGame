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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import bomberMan.Login.Model.User;
import bomberMan.gamePlay.Controller.EnemyController;
import bomberMan.gamePlay.Model.GameBoard;
import bomberMan.gamePlay.View.GameBoardView;
import bomberMan.gamePlay.View.GamePlayView;

import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.*;

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
		this.myframe.setSize(CONSTANTS.WINDOW_WIDTH,CONSTANTS.WINDOW_HEIGHT);
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
		GameBoardView x= new GameBoardView(myframe,myBoard,CONSTANTS.LIVESBOMBERMAN);
		myframe.setFocusable(true);
		myframe.addKeyListener(x);
		x.setBackground(Color.black);
		x.setVisible(true);
		myframe.add(x);
	        myframe.validate();
	        myframe.repaint();
	        x.requestFocusInWindow();
		myframe.setVisible(true);
		x.unpause();
        
    }
	
	/*
	 * Create a proper database if existing one is empty or doesn't exist
	 */
	private boolean createSaveGameDB(String fileName) 
	{
		// Write header in the following format:
		// Name,Password,Username,Score
		
		try {
			// Open up the database file for writing
	        CSVWriter writer = new CSVWriter(new FileWriter(fileName));
	        writer.writeNext(new String[]{"Game Name","Date/Time"});
	        writer.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());			
			// Unable to create the file
			return false;
		}        
		return true;        
	}
	
	/*
	 * Add a saved game to the saved game list
	 */
	public boolean addSavedGame(String user, String gameName, String dateTime) {
		
		
		String fileName = "CSVfiles" + File.separator + user + File.separator + "savedGames.txt";

			// First try to read the database file and make sure it's not empty
			try {
				CSVReader reader = new CSVReader(new FileReader(fileName));
				} catch (FileNotFoundException e) {
					// File was not found so create it
					if(!createSaveGameDB(fileName)) {
						// Database creation failed
						return false;
					}
				} catch (IOException e) {
					System.out.println(e.getMessage());
					return false;
				}
			
			// Now that we know database file is there, append a new username to it
			try {
				// Open up the database file in write append
		        CSVWriter writer = new CSVWriter(new FileWriter(fileName, true));
		        writer.writeNext(new String[]{gameName, dateTime});
		        writer.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());			
				return false;
			}        

			return true;		
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
		try{
			
		   DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		   //get current date time with Date()
		   Date date = new Date();
		  // String date2 Format.format(date);
		 
		   //get current date time with Calendar()
		   Calendar cal = Calendar.getInstance();
		   System.out.println(dateFormat.format(cal.getTime()));
			
		   // Display a dialog box asking user for the saved game name
		   JFrame myFrame = new JFrame("Save Game Name Dialogbox");
		     int messageType = JOptionPane.INFORMATION_MESSAGE;
		      String saveGameName = JOptionPane.showInputDialog(myFrame, 
		         "What is name of the saved game?", 
		         "Input Dialog Box", messageType);

		   String saveGameTime = dateFormat.format(cal.getTime());
		   String gameFileName = "CSVfiles" + File.separator + 
				   LoginView.DB.getCurrentUser().getUserName() + File.separator + saveGameName + 
				   "_" + saveGameTime + ".ser";

		   addSavedGame(LoginView.DB.getCurrentUser().getUserName(),
				   saveGameName,
				   saveGameTime);
		   
		   OutputStream fileOut = new FileOutputStream(gameFileName); //"./CSVfiles/TRIAL2.ser");
		   OutputStream buffer = new BufferedOutputStream(fileOut);
		   ObjectOutputStream out = new ObjectOutputStream(buffer);
	       out.writeObject(this.myBoard);
	       out.flush();
	       out.close();
	       fileOut.close();
	       System.out.println("Serialized data is saved in " + gameFileName);
		}
	       catch(IOException i)
	    {
	        i.printStackTrace();
	    }

        
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
		myCanvas.drawImage(backgroundImage, 0,0,this);
		myCanvas.drawImage(mainMenuImage, (CONSTANTS.WINDOW_WIDTH/2)-(mainMenuImage.getWidth(null)/2),(CONSTANTS.WINDOW_HEIGHT/2)-(mainMenuImage.getHeight(null)/2),this);
	}


}
