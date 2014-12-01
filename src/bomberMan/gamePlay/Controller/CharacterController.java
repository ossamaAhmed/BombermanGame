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


public class CharacterController{
	
	/*Instance Variables*/
	GameBoard myGameBoard;
	Calendar timer;
	/** 
	 * Constructor
	 * This method takes care of the passing of the game board to the controller to have
	 * access to it
	 * @param board is the game board that the controller will use
	 */
	public CharacterController(GameBoard  board)
	{
		
	this.myGameBoard=board;
	timer =Calendar.getInstance();

	}
	/** 
	 * This methods checks collision betweeen a character and an object at the character next 
	 * expected movment
	 * @param myCharacter is the character that you want to check if collides 
	 * @param direction is the next expected movment direction 
	 * @param collidingObject is the object that you want to test if there will be a collision such as "Bomb","Brick"..etc
	 * @return returns true if a collision is detected and false otherwise
	 * 
	 */
	public boolean checkCollision(Character myCharacter, int direction,String collidingObject){
		//apply the new changes on a temporary integers
		int tempX=-1;
		int tempY=-1;
		int counter=0;
		if(direction==CONSTANTS.RIGHT){
			tempX=myCharacter.getPositionX()+myCharacter.getSpeed();
			tempY=myCharacter.getPositionY();
		}
		else if(direction==CONSTANTS.LEFT){
			tempX=myCharacter.getPositionX()-myCharacter.getSpeed();
			tempY=myCharacter.getPositionY();
		}
		else if(direction==CONSTANTS.UP){
			tempX=myCharacter.getPositionX();
			tempY=myCharacter.getPositionY()-myCharacter.getSpeed();;
		}
		else if(direction==CONSTANTS.DOWN){
			tempX=myCharacter.getPositionX();
			tempY=myCharacter.getPositionY()+myCharacter.getSpeed();;
		}
		//check for each edge if it collides with anything
		//top left check
		if(!myGameBoard.getCell(tempY/CONSTANTS.TILE_SIDE_SIZE,tempX/CONSTANTS.TILE_SIDE_SIZE).searchTheCell(collidingObject)){
			counter++;
		}
		//top right check
		if(!myGameBoard.getCell((tempY+CONSTANTS.BOMBERMAN_WIDTH)/CONSTANTS.TILE_SIDE_SIZE,tempX/CONSTANTS.TILE_SIDE_SIZE).searchTheCell(collidingObject)){
			counter++;

		}
		//bottom left check
		if(!myGameBoard.getCell(tempY/CONSTANTS.TILE_SIDE_SIZE,(tempX+CONSTANTS.BOMBERMAN_WIDTH)/CONSTANTS.TILE_SIDE_SIZE).searchTheCell(collidingObject)){
			counter++;
		}
		//bottom right check
		if(!myGameBoard.getCell((tempY+CONSTANTS.BOMBERMAN_WIDTH)/CONSTANTS.TILE_SIDE_SIZE,(tempX+CONSTANTS.BOMBERMAN_WIDTH)/CONSTANTS.TILE_SIDE_SIZE).searchTheCell(collidingObject)){
			counter++;
		}
		//counter will be 4 only of no edge collides
		if(counter==4){
			return false;
		}
		return true;
	}
	/** 
	 * This method takes care of movement of a character if UP, DOWN, RIGHT OR LEFT key was pressed
	 * it used the collision function to detect if a move collides with a concrete wall
	 * @param keyE is the key event pressed 
	 */
	 public void move(KeyEvent keyE) {
		 if(keyE.getKeyCode() == KeyEvent.VK_RIGHT && !(checkCollision(myGameBoard.getBomberMan(), 2,"CONCRETE")) ){
			 myGameBoard.getBomberMan().moveRight(myGameBoard.getBomberMan().getSpeed());
		 }
		 else if(keyE.getKeyCode() == KeyEvent.VK_LEFT && !(checkCollision(myGameBoard.getBomberMan(), 1,"CONCRETE"))){
			 myGameBoard.getBomberMan().moveLeft(myGameBoard.getBomberMan().getSpeed());
		 }
		 else if(keyE.getKeyCode() == KeyEvent.VK_DOWN && !(checkCollision(myGameBoard.getBomberMan(), 4,"CONCRETE"))){
			 myGameBoard.getBomberMan().moveDown(myGameBoard.getBomberMan().getSpeed());
		 }
		 else  if(keyE.getKeyCode() == KeyEvent.VK_UP && !(checkCollision(myGameBoard.getBomberMan(), 3,"CONCRETE"))){
			 myGameBoard.getBomberMan().moveUp(myGameBoard.getBomberMan().getSpeed());
		 }
	 }
	 /** 
	  * Gets the Game Board currently used by the character controller
	  * @return returns the game board used by the controller
	*/
	 public GameBoard getGameBoard(){
		 return this.myGameBoard;
	 }
	 /** 
	  * Gets the time used by the character controller
	  * @return returns the timer used by the controller
	*/
	public Calendar getTimer(){
		return this.timer;
	}
}
