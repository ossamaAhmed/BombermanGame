/* 
 * File: LeaderBoardView.java
 * -----------------------
 * This class draws the leader board view for the user to choose see the top scores
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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import bomberMan.Login.Model.LeaderBoard;
import bomberMan.Login.Model.Record;
import bomberMan.Login.Model.UserDatabase;
import bomberMan.gamePlay.Model.CONSTANTS;
import bomberMan.gamePlay.Model.GameBoard;
import bomberMan.gamePlay.Model.GameObject;
import bomberMan.gamePlay.View.GameBoardView;

public class LeaderBoardView extends JPanel{
	
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
			private String previousView;
			private GameBoard myBoard;

			/** 
			 * Constructor
			 * This method takes care of the initialization of the leader board view
			 * @param x is the JFrame that will be used to display the view
			 * @param DB is the user database 
			 * @param previousView is a string representing the previous view "Pause" or "Main"
			 */		
			public LeaderBoardView(JFrame x,UserDatabase DB,String previousView){
				super();
				myframe=x;
				this.DB=DB;
				this.myframe.setSize(CONSTANTS.WINDOW_WIDTH,CONSTANTS.WINDOW_HEIGHT);
				this.previousView=previousView;
			    this.setOpaque(true);
			    setBackgroundImage();
			    this.setLayout(null);
				loadTopScores();
				goBackButton();
				exitButton();
			    this.repaint();
			    
			}
			/** 
			 * Constructor
			 * This method takes care of the initialization of the leader board view
			 * @param x is the JFrame that will be used to display the view
			 * @param DB is the user database 
			 * @param previousView is a string representing the previous view "Pause" or "Main"
			 * @param myboard is a game board that the user would like to return to after viewing the leader board
			 */	
			public LeaderBoardView(JFrame x,GameBoard myboard, UserDatabase DB,String previousView){
				super();
				myframe=x;
				this.myframe.setSize(CONSTANTS.WINDOW_WIDTH,CONSTANTS.WINDOW_HEIGHT);
				this.DB=DB;
				this.myBoard=myboard;
				this.previousView=previousView;
			    this.setOpaque(true);
			    setBackgroundImage();
			    this.setLayout(null);
				loadTopScores();
				goBackButton();
				exitButton();
			    this.repaint();
			}
			/** 
			 * This method takes care of the displaying of the top scores and users corresponding to it
			 */	
			public void loadTopScores(){
				 ArrayList<Record> TopScores;
				 TopScores = LeaderBoard.getTopTenUsers();
				    JPanel p = new JPanel();
				    p.setSize(400, 400);
				    p.setLayout(new GridLayout(TopScores.size(), 3, 10, 0));
				    for (int row = 0; row < TopScores.size(); row++) {
				      for (int col = 0; col < 3; col++){
				    	  if (col==0){
				    		  p.add(new JLabel(("Position "+(row+1))));
				    	  }
				      
				      else if (col == 1) {
				          p.add(new JLabel(TopScores.get(row).getUser()));
				        } 
				        else  {
				        	p.add(new JLabel( " Score: "+TopScores.get(row).getScore()));
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
					 //adding action listener and directing it to the appropriate function
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
				JPanel x;
				if(previousView.equals("Pause")){
					x=new PauseMenuView(myframe,this.myBoard,this.DB);
				}
				else{
					x=new MainMenuView(myframe,this.DB);
				}
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
			    loadGameImage=Toolkit.getDefaultToolkit().createImage(GameObject.class.getResource("/image/" + "LeaderBoard.png"));
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
