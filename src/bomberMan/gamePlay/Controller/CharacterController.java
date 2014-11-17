/* 
 * File: CharacterController.java
 * -----------------------
 * This Class represents the control of any character on screen. Mainly for movement 
 * and detecting collisions
 */
package bomberMan.gamePlay.Controller;


import java.awt.event.KeyEvent;
import java.util.Calendar;

import bomberMan.gamePlay.Model.*;
import bomberMan.gamePlay.Model.Character;


public class CharacterController extends Thread{
	
	/*Instance Variables*/
	GameBoard myGameBoard;
	Calendar timer;
	
	/** 
	 * Constructor
	 * This method takes care of the passing of the game board to the controller to have
	 * access to it
	 */
	public CharacterController(GameBoard  board){
		
	this.myGameBoard=board;
	timer =Calendar.getInstance();

	}
	/** 
	 * This is a helper method which takes care of detecting collisions given a character and a direction of movement.
	 * We should create an enum for movement instead of numbers. To be implemented. The width and 
	 * height of the image are hard coded here. We should change that. 
	 */
	public Calendar getTimer(){return this.timer;}
}
