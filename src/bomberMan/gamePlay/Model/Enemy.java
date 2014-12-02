/* 
 * File: Enemy.java
 * -----------------------
 * This Class represents the enemy abstract class. Any enemy should extend this class
 */
package bomberMan.gamePlay.Model;


public class Enemy extends Character implements java.io.Serializable {
	
	/*Instance Variables*/
	private boolean wallPass;
	private int movmentDirection;
	private int expectedNextMovmentDirection;
	private int score;
	
	/** 
	 * Constructor
	 * This method takes care of the initialization of the enemy including position, initial
	 * Movement direction and enemyName.
	 * @param xPos is the x position of the wall on the screen 
	 * @param yPos is the y position of the wall on the screen
	 * @param movmentDirection is the initial movment direction of the enemy.
	 * @param enemyName is the enemy name, it have to be one of the allowed ones
	 */
	public Enemy(int xPos,int yPos,int movmentDirection,String enemyName){
		super(xPos,yPos,enemyName+".png",enemyName);
		this.movmentDirection=movmentDirection;
		this.expectedNextMovmentDirection=movmentDirection;
	}
	/** 
	 * has wall pass
	 * @return returns true if the enemy has a wall pass
	 */
	public boolean hasWallPass(){
		return this.wallPass;
	}
	/** 
	 * Gets the expected next direction of the enemy
	 * @return returns the expected next direction of the enemy
	 */
	public int getExpectedMovmentDirection(){
		return this.expectedNextMovmentDirection;
	}
	/** 
	 * Sets the expected next direction of the enemy
	 * @param direction is the expected next direction of the enemy
	 */
	public void setExpectedMovmentDirection(int direction){
		this.expectedNextMovmentDirection=direction;
	}
	/** 
	 * Sets the wall pass of the enemy
	 * @param wallPass is true if the enemy you want has a property if wall pass
	 */
	public void setWallPass(boolean wallPass){
		this.wallPass=wallPass;
	}
	/** 
	 * Gets the current movment direction of the enemy
	 * @return returns the current direction of the enemy
	 */
	public int getMovmentDirection(){
		return this.movmentDirection;
	}
	/** 
	 * Sets the score or points associated with the enemy that the user will gain if killed
	 * @param score is the score gained if the enemy dies
	 */
	public void setScore(int score){
		this.score=score;
	}
	/** 
	 * Gets the score or points associated with the enemy that the user will gain if killed
	 * @return the score gained if the enemy dies
	 */
	public int getScore(){
		return this.score;
	}
	/** 
	 * Reverses the direction of the enemy probably called when collision happens
	 */
	public void changeDirection(){
		if(this.getExpectedMovmentDirection()==CONSTANTS.RIGHT){
			this.movmentDirection=CONSTANTS.LEFT;
			this.expectedNextMovmentDirection=CONSTANTS.LEFT;
		}
		else if(this.getExpectedMovmentDirection()==CONSTANTS.LEFT){
			this.movmentDirection=CONSTANTS.RIGHT;
			this.expectedNextMovmentDirection=CONSTANTS.RIGHT;
		}
		else if(this.getExpectedMovmentDirection()==CONSTANTS.UP){
			this.movmentDirection=CONSTANTS.DOWN;
			this.expectedNextMovmentDirection=CONSTANTS.DOWN;
		}
		else if(this.getExpectedMovmentDirection()==CONSTANTS.DOWN){
			this.movmentDirection=CONSTANTS.UP;
			this.expectedNextMovmentDirection=CONSTANTS.UP;
		}
	}
	

}
