/* 
 * File: CharacterController.java
 * -----------------------
 * This Class represents the control of any character on screen. Mainly for movement 
 * and detecting collisions
 */
package bomberMan.gamePlay.Controller;


import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.util.Calendar;

import bomberMan.gamePlay.Model.*;
import bomberMan.gamePlay.Model.Character;


public class CharacterController{
	
	/*Instance Variables*/
	GameBoard myGameBoard;
	Calendar timer;
	EnemyController enemyController;
	
	/** 
	 * Constructor
	 * This method takes care of the passing of the game board to the controller to have
	 * access to it
	 */
	public CharacterController(GameBoard  board)
	{
		
	this.myGameBoard=board;
	timer =Calendar.getInstance();

	}
	/** 
	 * This is a helper method which takes care of detecting collisions given a character and a direction of movement.
	 * We should create an enum for movement instead of numbers. To be implemented. The width and 
	 * height of the image are hard coded here. We should change that. 
	 */
	private boolean checkCollision(Character myCharacter, int direction,String collidingObject)
	{
		//apply the new changes on a temporary integers
		int tempX=-1;
		int tempY=-1;
		int counter=0;
		if(direction==2)//right
		{
			tempX=myCharacter.getPositionX()+myCharacter.getSpeed();
			tempY=myCharacter.getPositionY();
		}
		else if(direction==1)//left
		{
			tempX=myCharacter.getPositionX()-myCharacter.getSpeed();
			tempY=myCharacter.getPositionY();
		}
		else if(direction==3)//Up
		{
			tempX=myCharacter.getPositionX();
			tempY=myCharacter.getPositionY()-myCharacter.getSpeed();;
		}
		else if(direction==4)//Down
		{
			tempX=myCharacter.getPositionX();
			tempY=myCharacter.getPositionY()+myCharacter.getSpeed();;
		}
		//check for each edge if it collides with anything
		//top left check
		if(!myGameBoard.getCell(tempY/CONSTANTS.TILE_SIDE_SIZE,tempX/CONSTANTS.TILE_SIDE_SIZE).searchTheCell(collidingObject))
		{
			counter++;
		}
		//top right check
		if(!myGameBoard.getCell((tempY+20)/CONSTANTS.TILE_SIDE_SIZE,tempX/CONSTANTS.TILE_SIDE_SIZE).searchTheCell(collidingObject))
		{
			counter++;

		}
		//bottom left check
		if(!myGameBoard.getCell(tempY/CONSTANTS.TILE_SIDE_SIZE,(tempX+20)/CONSTANTS.TILE_SIDE_SIZE).searchTheCell(collidingObject))
		{
			counter++;
		}
		//bottom right check
		if(!myGameBoard.getCell((tempY+20)/CONSTANTS.TILE_SIDE_SIZE,(tempX+20)/CONSTANTS.TILE_SIDE_SIZE).searchTheCell(collidingObject))
		{
			counter++;
		}
		//counter will be 4 only of no edge collides
		if(counter==4)
		{
			return false;
		}
		return true;
	}
	/** 
	 * This method takes care of movement of a character if UP, DOWN, RIGHT OR LEFT key was pressed
	 * it used the collision function to detect if a move should take place
	 */
	 public void move(KeyEvent keyE) 
	 {
		 if(keyE.getKeyCode() == KeyEvent.VK_RIGHT && !(checkCollision(myGameBoard.getBomberMan(), 2,"CONCRETE")) )
		 {
			 myGameBoard.getBomberMan().moveRight(myGameBoard.getBomberMan().getSpeed());
		 }
		 else if(keyE.getKeyCode() == KeyEvent.VK_LEFT && !(checkCollision(myGameBoard.getBomberMan(), 1,"CONCRETE")))
		 {
			 myGameBoard.getBomberMan().moveLeft(myGameBoard.getBomberMan().getSpeed());
		 }
		 else if(keyE.getKeyCode() == KeyEvent.VK_DOWN && !(checkCollision(myGameBoard.getBomberMan(), 4,"CONCRETE")))
		 {
			 myGameBoard.getBomberMan().moveDown(myGameBoard.getBomberMan().getSpeed());
		 }
		 else  if(keyE.getKeyCode() == KeyEvent.VK_UP && !(checkCollision(myGameBoard.getBomberMan(), 3,"CONCRETE")))
		 {
			 myGameBoard.getBomberMan().moveUp(myGameBoard.getBomberMan().getSpeed());
		 }
	 }
	 public void moveEnemy(Enemy enemy, boolean wallPass) 
	 {
		 
		 String collidingObject1="CONCRETE";
		 String collidingObject3="BRICK";
	     String collidingObject2="BOMB";
			 if(enemy.getMovmentDirection()==2 && !(checkCollision(enemy, 2,collidingObject1))&& !(checkCollision(enemy, 2,collidingObject2)) )
			 {
				 if(wallPass)
					 enemy.moveRight(enemy.getSpeed());
				 else if(!(checkCollision(enemy, 2,collidingObject3)))
				    enemy.moveRight(enemy.getSpeed());
				 else
				 {
					 enemy.changeDirection();
				 }
			 }
			 else if(enemy.getMovmentDirection()==1 && !(checkCollision(enemy, 1,collidingObject1))&&!(checkCollision(enemy, 1,collidingObject2)) )
			 {
				 if(wallPass)
					 enemy.moveLeft(enemy.getSpeed());
				 else if(!(checkCollision(enemy, 1,collidingObject3)))
					 enemy.moveLeft(enemy.getSpeed());
				 else
				 {
					 enemy.changeDirection();
				 }
			
			 }
			 else if(enemy.getMovmentDirection()==4 && !(checkCollision(enemy, 4,collidingObject1))&&!(checkCollision(enemy, 4,collidingObject2)))
			 {
				 if(wallPass)
					 enemy.moveDown(enemy.getSpeed());
				 else if(!(checkCollision(enemy, 4,collidingObject3)))
					 enemy.moveDown(enemy.getSpeed());
				 else
				 {
					 enemy.changeDirection();
				 }
			 }
			 else  if(enemy.getMovmentDirection()==3 && !(checkCollision(enemy, 3,collidingObject1))&&!(checkCollision(enemy, 3,collidingObject2)))
			 {
				 if(wallPass)
					 enemy.moveUp(enemy.getSpeed());
				 else if(!(checkCollision(enemy, 3,collidingObject3)))
					 enemy.moveUp(enemy.getSpeed());
				 else
				 {
					 enemy.changeDirection();
				 }
			 }
			 else
			 {
				 enemy.changeDirection();
			 }
	 }
	 public GameBoard getGameBoard()
	 {
		 return this.myGameBoard;
	 }

	public Calendar getTimer(){return this.timer;}
//	public void pause()
//	{
//		enemyController.suspend();
//	}
//	public void unpause()
//	{
//		enemyController.resume();
//	}
}
