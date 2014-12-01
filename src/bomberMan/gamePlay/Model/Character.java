/* 
 * File: Character.java
 * -----------------------
 * This Class represents each character on the gameboard (i.e BomberMan and different types of enemies)
 * The characters will be moving on the screen on a pixel-based movement.
 */
package bomberMan.gamePlay.Model;

public class Character extends GameObject implements java.io.Serializable {
	
	/*Instance Variables*/
	private boolean alive;
	private int speed;
	private int numOfLives;
	protected int lastDirection = -1;
	protected int forbiddenDirection = -1;
	/** 
	 * Constructor
	 * This method takes care of the initialization of the different types of variables that 
	 * each character has, including the number of Lives, If the character is alive or dead,
	 * and the speed of movements(in pixels). This Constructor is not used yet
	 */
	public Character() {
		super("Character");
		this.alive=true;
		this.speed=CONSTANTS.DEFAULT_SPEED;
		this.numOfLives=0;
	}
	/** 
	 * Constructor
	 * This method takes care of the initialization of the different types of variables that 
	 * each character has, including the number of Lives, If the character is alive or dead,
	 * and the speed of movements(in pixels). 
	 * @param xPos is the x position on the screen
	 * @param yPos is the y position on the screen
	 * @param location is the image location 
	 * @param objectType is the objectType if it is enemy or bomberman 
	 */
	public Character(int xPos,int yPos,String location,String objectType){
		super(xPos,yPos,location,objectType);
		this.alive=true;
		this.speed=CONSTANTS.DEFAULT_SPEED;
		this.numOfLives=0;
	}
	/** 
	 * This method returns the speed of the character 
	 * @return returns the current speed of the character
	 */
	public int getSpeed(){
		return this.speed;
	}
	/** 
	 * This method moves the character upwards given the numberOfSteps in pixels
	 * @param steps is the number of steps to be moved in pixels 
	 */
	public void moveUp(int steps) {
		
		setPositionY(getPositionY()-steps);
	}
	/** 
	 * This method moves the character downwards given the numberOfSteps in pixels 
	 * @param steps is the number of steps to be moved in pixels 
	 */
	public void moveDown(int steps) {
		
		setPositionY(getPositionY()+steps);
	}
	/** 
	 * This method moves the character right given the numberOfSteps in pixels 
	 * @param steps is the number of steps to be moved in pixels 
	 */
	public void moveRight(int steps) {
		System.out.println("MOVING RIGHT11");
		setPositionX(getPositionX()+steps);
	}
	/** 
	 * This method moves the character left given the numberOfSteps in pixels 
	 * @param steps is the number of steps to be moved in pixels 
	 */
	public void moveLeft(int steps) {
		
		setPositionX(getPositionX()-steps);
	}
	/** 
	 * This method updates the speed of the character when bomberMan picks up a powerUp 
	 * for example. 
	 * @param speed is the required speed of the character 
	 */
	public void updateSpeed(int speed) {
		
		this.speed=speed;
	}
	
	/** 
	 * This method kills the character and updates the private variables accordingly. 
	 */
	public void die() {
		this.alive=false;
		this.numOfLives--;
	}
	/** 
	 * This method gets the boolean alive
	 * @return returns true if the character is alive and false if not 
	 */
	public boolean getIsAlive(){
		return this.alive;
	}
	/** 
	 * This method sets the boolean alive
	 * @param serLive is true if the character is alive and false if not 
	 */
	public void setIAlive(boolean setLive){
		this.alive = setLive;
	}
	/** 
	 * This method sets the last direction
	 * @param direction is the last direction
	 */
	public void setLastDirection(int direction){
		this.lastDirection = direction;
	}
	/** 
	 * This method gets the last direction
	 * @return the last direction
	 */
    public int getLastDirection(){
    	return this.lastDirection;
    }
	/** 
	 * This method sets the forbidden direction
	 * @param direction is the forbidden direction
	 */
    public void setForbiddenDirection(int direction){
    	this.forbiddenDirection = direction;
    }
	/** 
	 * This method gets the forbidden direction
	 * @return the forbidden direction
	 */
    public int getForbiddenDirection(){
    	return this.forbiddenDirection;
    }
	/** 
	 * This method return the number of lives
	 * @return number of lives of the character 
	 */
    public int getNumOfLives(){
    	return this.numOfLives;
    }
	/** 
	 * This method sets the number of lives
	 * @param numLives is the required number of lives of the character 
	 */
    public void setNumOfLives(int numLives){
    	this.numOfLives += numLives;
    }
}
