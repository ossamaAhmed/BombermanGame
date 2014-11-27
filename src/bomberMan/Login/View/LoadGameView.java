package bomberMan.Login.View;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bomberMan.Login.Controller.LoginController;
import bomberMan.Login.Model.UserDatabase;
import bomberMan.gamePlay.Model.CONSTANTS;
import bomberMan.gamePlay.Model.GameBoard;
import bomberMan.gamePlay.View.GameBoardView;

public class LoadGameView extends JPanel   
{
	//constants to be moved
		private int window_width=CONSTANTS.WINDOW_WIDTH;
		private int window_height=CONSTANTS.WINDOW_HEIGHT;
		private Graphics2D myCanvas;
		private JScrollPane scrollpane;
		private JButton exitButton;
		private JButton goBackButton;
		private Image backgroundImage;
		private Image loadGameImage;
		private JFrame myframe;
		private UserDatabase DB;
		private int scrollWidth;
		private int scrollHeight;
		private int scrollX;
		private int scrollY;

		
		public LoadGameView(JFrame x,UserDatabase DB)
		{
			super();
			myframe=x;
			this.DB=DB;
		    this.setOpaque(true);
		   // this.setBackground(Color.WHITE);
		    setBackgroundImage();
		    this.setLayout(null);
			loadSavedGames();
			goBackButton();
			exitButton();
		   // seterrorText();
		    
		    this.repaint();
		    
		}
		public void loadSavedGames()
		{
			   ArrayList<String>SavedGameLabel = this.DB.getCurrentUser().loadSavedGamesList(this.DB.getCurrentUser().getUserName());
			 //adding action listener and directing it to the appropiate function
			    JPanel p = new JPanel();
			    p.setSize(400, 400);
			    p.setLayout(new GridLayout(SavedGameLabel.size()-1, 2, 10, 0));
			    for (int row = 1; row < SavedGameLabel.size(); row++) 
			    {
			      for (int col = 0; col < 2; col++)
			      {
			        if (col == 0) 
			        {
			          p.add(new JLabel(SavedGameLabel.get(row)));
			        } 
			        else 
			        {
			        	JButton temp= new JButton("LOAD");
			        	temp.setSize(100, 40);
			        	temp.setBorderPainted(false);
			        	temp.setOpaque(true);
			        	temp.setBackground(Color.BLACK);
			        	temp.setForeground(Color.white);
						 p.add(temp);
						 final String temp2=SavedGameLabel.get(row);
						 //adding action listener and directing it to the appropiate function
						 temp.addActionListener(new ActionListener() {
					            public void actionPerformed(ActionEvent evt) {
					            	loadGameButtonActionPerformed(evt,temp2);
					            }
						 });
			        } 
			       }
			    }
			    p.setOpaque(false);
			    p.setBackground(new Color(0,0,0,65));
			    scrollpane= new JScrollPane(p);
				scrollpane.setSize(388, 300);
				scrollpane.setOpaque(false);
				scrollpane.getViewport().setOpaque(false);
				scrollpane.setLocation(426,38+30);
				scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				 this.add(scrollpane);
		}
		private void loadGameButtonActionPerformed(ActionEvent evt,String loadGameName) 
		{
			String fileLocation = this.DB.getCurrentUser().loadSavedGame(loadGameName);
			GameBoard objectLoad = null;
		    try
		    {
		    	
		    	
		       FileInputStream fileIn = new FileInputStream(fileLocation);
		       ObjectInputStream in = new ObjectInputStream(fileIn);
		       objectLoad = (GameBoard) in.readObject();
		       in.close();
		       fileIn.close();
		       System.out.println("Serialized data loaded!");
		       GameBoardView x= new GameBoardView(myframe,objectLoad,objectLoad.getLives(),objectLoad.getBomberMan().getPowerUpsKeptAfterDeath(),this.DB,objectLoad.getStage());
				myframe.remove(this);
				myframe.setFocusable(true);
				myframe.addKeyListener(x);
				x.setBackground(Color.black);
				x.setVisible(true);
				myframe.add(x);
			        myframe.validate();
			        myframe.repaint();
			        x.requestFocusInWindow();
				myframe.setVisible(true);
		    }catch(IOException i)
		    {
		       i.printStackTrace();
		       return;
		    }catch(ClassNotFoundException c)
		    {
		       System.out.println("GameBoard class not found");
		       c.printStackTrace();
		       return;
		    }

	    }
		private void goBackButton()
		{
			goBackButton= new JButton("BACK");
			goBackButton.setSize(100, 40);
			goBackButton.setBorderPainted(false);
			goBackButton.setOpaque(true);
			goBackButton.setBackground(Color.BLACK);
			goBackButton.setForeground(Color.white);
			goBackButton.setLocation(window_width/2, window_height-100);
				 this.add(goBackButton);
				 //adding action listener and directing it to the appropiate function
				 goBackButton.addActionListener(new ActionListener() {
			            public void actionPerformed(ActionEvent evt) {
			            	goBackButtonActionPerformed(evt);
			            }
				 });
		}
		private void goBackButtonActionPerformed(ActionEvent evt) 
		{
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
		private void exitButton()
		{
	    		exitButton= new JButton("EXIT");
				exitButton.setSize(100, 40);
				exitButton.setBorderPainted(false);
				exitButton.setOpaque(true);
				exitButton.setBackground(Color.BLACK);
				exitButton.setForeground(Color.white);
				exitButton.setLocation(goBackButton.getX()-exitButton.getWidth()-10, window_height-100);
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
		    loadGameImage=Toolkit.getDefaultToolkit().createImage("LoadGame.png");
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
			myCanvas.drawImage(loadGameImage, (backgroundImage.getWidth(null)/2)-(loadGameImage.getWidth(null)/2),(backgroundImage.getHeight(null)/2)-(loadGameImage.getHeight(null)/2),this);
			this.scrollWidth=loadGameImage.getWidth(null);
			this.scrollHeight=loadGameImage.getHeight(null);
			this.scrollX=(backgroundImage.getWidth(null)/2)-(loadGameImage.getWidth(null)/2);
			this.scrollY=(backgroundImage.getHeight(null)/2)-(loadGameImage.getHeight(null)/2);
		}


}
