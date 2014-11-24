package bomberMan.gamePlay.View;


import bomberMan.gamePlay.Controller.GameBoardController;
import bomberMan.gamePlay.Model.*;
import bomberMan.gamePlay.View.GameBoardView;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.Timer;

public class GamePlayView {
	/*Instance Variables*/
	static JFrame gameView;
	static GameBoardView board;
	private GameBoardController gmController;
	
	public static void main(String[] args)
	{
		gameView = new JFrame("BomberMan");
		
		
		board= new GameBoardView(gameView, CONSTANTS.LIVESBOMBERMAN);
		SwingUtilities.invokeLater(new Runnable() 
	    {
	      @Override
	      public void run()  
	      {  	
	  		gameView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gameView.setSize(CONSTANTS.SCREEN_WIDTH, CONSTANTS.WINDOW_HEIGHT+100); 
			//gameView.setResizable(false); //Resizing Not working should look into it
			gameView.addKeyListener(board);		
			board.setBackground(Color.black);
			board.setVisible(true);
			gameView.add(board);
			gameView.setVisible(true);
	      }
	    });
//		gameView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		gameView.setSize(CONSTANTS.WINDOW_WIDTH, CONSTANTS.WINDOW_HEIGHT); 
//		//gameView.setResizable(false); //Resizing Not working should look into it
//		gameView.addKeyListener(board);		
//		board.setBackground(Color.black);
//		board.setVisible(true);
//		
//		gameView.add(board);
//		gameView.setVisible(true);
//		}
	}

}