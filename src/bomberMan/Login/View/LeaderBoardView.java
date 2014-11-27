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
import bomberMan.gamePlay.View.GameBoardView;

public class LeaderBoardView extends JPanel
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
			private String previousView;
			private GameBoard myBoard;

			
			public LeaderBoardView(JFrame x,UserDatabase DB,String previousView)
			{
				super();
				myframe=x;
				this.DB=DB;
				this.myframe.setSize(CONSTANTS.WINDOW_WIDTH,CONSTANTS.WINDOW_HEIGHT);
				this.previousView=previousView;
			    this.setOpaque(true);
			   // this.setBackground(Color.WHITE);
			    setBackgroundImage();
			    this.setLayout(null);
				loadTopScores();
				goBackButton();
				exitButton();
			   // seterrorText();
			    
			    this.repaint();
			    
			}
			public LeaderBoardView(JFrame x,GameBoard myboard, UserDatabase DB,String previousView)
			{
				super();
				myframe=x;
				this.myframe.setSize(CONSTANTS.WINDOW_WIDTH,CONSTANTS.WINDOW_HEIGHT);
				this.DB=DB;
				this.myBoard=myboard;
				this.previousView=previousView;
			    this.setOpaque(true);
			   // this.setBackground(Color.WHITE);
			    setBackgroundImage();
			    this.setLayout(null);
				loadTopScores();
				goBackButton();
				exitButton();
			   // seterrorText();
			    
			    this.repaint();
			    
			}
			public void loadTopScores()
			{
				 ArrayList<Record> TopScores;
				 TopScores = LeaderBoard.getTopTenUsers();
				 //adding action listener and directing it to the appropiate function
				    JPanel p = new JPanel();
				    p.setSize(400, 400);
				    p.setLayout(new GridLayout(TopScores.size(), 3, 10, 0));
				    for (int row = 0; row < TopScores.size(); row++) 
				    {
				      for (int col = 0; col < 3; col++)
				      {
				    	  if (col==0)
				    	  {
				    		  p.add(new JLabel(("Position "+(row+1))));
				    	  }
				      
				      else if (col == 1) 
				        {
				          p.add(new JLabel(TopScores.get(row).getUser()));
				        } 
				        else 
				        {
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
				JPanel x;
				if(previousView.equals("Pause"))
				{
					x=new PauseMenuView(myframe,this.myBoard,this.DB);
				}
				else
				{
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
			    loadGameImage=Toolkit.getDefaultToolkit().createImage("LeaderBoard.png");
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
