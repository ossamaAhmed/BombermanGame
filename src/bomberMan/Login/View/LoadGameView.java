/* 
 * File: LoadGameView.java
 * -----------------------
 * This class draws the load game view for the user to choose a game to load it 
 */
package bomberMan.Login.View;

import java.awt.Color;
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import bomberMan.Login.Model.UserDatabase;
import bomberMan.gamePlay.Model.CONSTANTS;
import bomberMan.gamePlay.Model.GameBoard;
import bomberMan.gamePlay.Model.GameObject;
import bomberMan.gamePlay.View.GameBoardView;

public class LoadGameView extends JPanel   {
	
		/*Instance Variables*/
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
		/** 
		 * Constructor
		 * This method takes care of the initialization of the load game view
		 * @param x is the JFrame that will be used to display the view
		 * @param DB is the user database 
		 */		
		public LoadGameView(JFrame x,UserDatabase DB){
			super();
			myframe=x;
			this.DB=DB;
		    this.setOpaque(true);
		    setBackgroundImage();
		    this.setLayout(null);
			loadSavedGames();
			goBackButton();
			exitButton();
		    this.repaint();
		}
		/** 
		 * This method takes care of the displaying of the save game of the user for the user to choose a game to load.
		 */	
		private void loadSavedGames(){
			   ArrayList<String>SavedGameLabel = this.DB.getCurrentUser().loadSavedGamesList(this.DB.getCurrentUser().getUserName());
			    JPanel p = new JPanel();
			    p.setSize(400, 400);
			    p.setLayout(new GridLayout(SavedGameLabel.size()-1, 2, 10, 0));
			    for (int row = 1; row < SavedGameLabel.size(); row++)  {
			      for (int col = 0; col < 2; col++) {
			        if (col == 0) {
			          p.add(new JLabel(SavedGameLabel.get(row)));
			        } 
			        else  {
			        	JButton temp= new JButton("LOAD");
			        	temp.setSize(100, 40);
			        	temp.setBorderPainted(false);
			        	temp.setOpaque(true);
			        	temp.setBackground(Color.BLACK);
			        	temp.setForeground(Color.white);
						 p.add(temp);
						 final String temp2=SavedGameLabel.get(row);
						 //adding action listener and directing it to the appropriate function
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
		/** 
		 * This method takes care of the action performed when the load button is pressed
		 * @param evt is the event triggered when the button is pressed 
		 * @param loadGameName is the game name that the user chose to load
		 */
		private void loadGameButtonActionPerformed(ActionEvent evt,String loadGameName) 
		{
			String fileLocation = this.DB.getCurrentUser().loadSavedGame(loadGameName);
			GameBoard objectLoad = null;
		    try{
		       FileInputStream fileIn = new FileInputStream(fileLocation);
		       ObjectInputStream in = new ObjectInputStream(fileIn);
		       objectLoad = (GameBoard) in.readObject();
		       in.close();
		       fileIn.close();
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
		    }catch(IOException i) {
		       i.printStackTrace();
		       return;
		    }catch(ClassNotFoundException c){
		       System.out.println("GameBoard class not found");
		       c.printStackTrace();
		       return;
		    }

	    }
		/** 
		 * This method takes care of the initialization of the back button
		 */
		private void goBackButton(){
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
		/** 
		 * This method takes care of the action performed when the back button is pressed
		 * @param evt is the event triggered when the button is pressed 
		 */
		private void goBackButtonActionPerformed(ActionEvent evt) {
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
		/** 
		 * This method takes care of the initialization and management of the exit button
		 */
		private void exitButton(){
	    		exitButton= new JButton("EXIT");
				exitButton.setSize(100, 40);
				exitButton.setBorderPainted(false);
				exitButton.setOpaque(true);
				exitButton.setBackground(Color.BLACK);
				exitButton.setForeground(Color.white);
				exitButton.setLocation(goBackButton.getX()-exitButton.getWidth()-10, window_height-100);
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
		 * This method takes care of the setting and uploading of the background images
		 */
		public void setBackgroundImage(){
		    backgroundImage = Toolkit.getDefaultToolkit().createImage(GameObject.class.getResource("/image/" + "giphy.gif"));
		    loadGameImage=Toolkit.getDefaultToolkit().createImage(GameObject.class.getResource("/image/" + "LoadGame.png"));
		    backgroundImage=backgroundImage.getScaledInstance(CONSTANTS.WINDOW_WIDTH, CONSTANTS.WINDOW_HEIGHT, Image.SCALE_DEFAULT);
		}
		/** 
		 * This method takes care of the painting of the background images 
		 */
		public void paintComponent(Graphics g){
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
