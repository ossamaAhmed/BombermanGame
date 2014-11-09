/* 
 * File: GameBoardView.java
 * -----------------------
 * This Class represents takes care of the drawing of the gameboard on a graphics2D object
 * and also it takes care of key listening to direct it to the appropriate controller methods
 */

package bomberMan.gamePlay.View;

import bomberMan.Login.View.PauseMenuView;
import bomberMan.gamePlay.Controller.CharacterController;
import bomberMan.gamePlay.Model.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class GameBoardView extends JPanel implements KeyListener {
	
	/*Instance Variables*/
	private GameBoard myBoard;
	private Graphics2D myCanvas;
	private CharacterController controller;
	private JFrame myFrame;
	/** 
	 * Constructor
	 * This method takes care of the initialization of the different instance variable 
	 * and adding a key listener to the canvas and painting the init board.
	 */
	  public GameBoardView(JFrame myFrame)
	  {
		  super();
		  this.myFrame=myFrame;
		  myBoard=new GameBoard(12);
		  controller=new CharacterController(myBoard);
		  this.addKeyListener(this);
		  this.repaint();		  
	  }
	  public GameBoardView(JFrame myFrame, GameBoard myBoard)
	  {
		  super();
		  this.myFrame=myFrame;
		  this.myBoard=myBoard;
		  controller=new CharacterController(myBoard);
		  this.addKeyListener(this);
		  this.repaint();		  
	  }
	  /** 
	   * This method takes care of the key listening and calls the different methods of the controller
	   * according to the key pressed or the event happening
	   */
	 public void keyPressed(KeyEvent keyE){
		 if (keyE.getKeyCode() == KeyEvent.VK_RIGHT || keyE.getKeyCode() == KeyEvent.VK_LEFT || keyE.getKeyCode() == KeyEvent.VK_DOWN || keyE.getKeyCode() == KeyEvent.VK_UP)
		 {
			 controller.move(keyE);
			 this.updateGameBoardView(10);
		 }
		 if(keyE.getKeyCode()==KeyEvent.VK_SPACE)
		 {
			 myFrame.remove(this);
				PauseMenuView x=new PauseMenuView(myFrame,this.myBoard);
				myFrame.setFocusable(true);
				//myframe.addKeyListener(x);
				x.setBackground(Color.black);
				x.setVisible(true);
				myFrame.add(x);
				myFrame.validate();
				myFrame.repaint();
			        x.requestFocusInWindow();
			        myFrame.setVisible(true);
		 }
	 }
	  /** 
	   * These methods are required by the interface implemented. Not used yet. 
	   */
	 public void keyReleased(KeyEvent keyE){ }
	 public void keyTyped(KeyEvent keyE){ }
	
	  /** 
	   * This function refreshes the screen. should be called if any changes happened to the gameBoard.
	   */
	 public void updateGameBoardView(int num)
	 {
		 this.repaint();
//		 GameObject o;
//		 
//		 if (o instanceof Character) {
//			 Character c = (Character) o;
//		 }
		 
	 }
	 
	 /** 
	   * This function Draws the gameBoard on the screen. Renderes the data only. Any changes 
	   * shouldn't happen here. 
	  */
	 public void paintComponent(Graphics g)
	 {
		 super.paintComponent(g);
		 this.myCanvas = (Graphics2D) g;
		 
		 myCanvas.drawImage(myBoard.getBomberMan().getImage(), myBoard.getBomberMan().getPositionX(),myBoard.getBomberMan().getPositionY(), null);
		 for(int i=0;i<CONSTANTS.NUMBER_OF_VERTICAL_TILES;i++)
		 {
			 for(int j=0;j<CONSTANTS.NUMBER_OF_HORIZONTAL_TILES;j++)
			 {
				 if(!this.myBoard.getCell(i, j).isEmpty())
					 myCanvas.drawImage(myBoard.getCell(i, j).getImage(),myBoard.getCell(i, j).getObjects().get(0).getPositionX(),myBoard.getCell(i, j).getObjects().get(0).getPositionY(), null);
			 }
		 }
	 }
	 
	

}
