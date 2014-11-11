/* 
 * File: GamePlayView.java
 * -----------------------
 * This Class takes care of the initialization of the frame and adding the gameboard panel in it. 
 * Run the game from here for now. 
 */
package bomberMan.gamePlay.View;

import bomberMan.gamePlay.Controller.GameBoardController;
import bomberMan.gamePlay.Model.*;

import javax.swing.*;

import java.awt.Color;

public class GamePlayView {
	/*Instance Variables*/
	static JFrame gameView;
	static GameBoardView board;
	private GameBoardController gmController;
	
	public static void main(String[] args)
	{
		gameView = new JFrame("BomberMan");
		
		
		board= new GameBoardView(gameView);
		gameView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameView.setSize(CONSTANTS.WINDOW_WIDTH, CONSTANTS.WINDOW_HEIGHT); 
		//gameView.setResizable(false); //Resizing Not working should look into it
		gameView.addKeyListener(board);		
		board.setBackground(Color.black);
		board.setVisible(true);
		
		gameView.add(board);
		gameView.setVisible(true);
		while(gameView.isVisible()){board.updateGameBoardView();}
	}

}