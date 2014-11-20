/* 
 * File: Character.java
 * -----------------------
 * This Class represents each character on the gameboard (i.e BomberMan and different types of enemies)
 * The characters will be moving on the screen on a pixel-based movement.
 */
package bomberMan.gamePlay.Model;

public class Character extends GameObject {
	
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
		super();
		this.alive=true;
		this.speed=CONSTANTS.DEFAULT_SPEED;
		this.numOfLives=0;
	}
	/** 
	 * Constructor
	 * This method takes care of the initialization of the different types of variables that 
	 * each character has, including the number of Lives, If the character is alive or dead,
	 * and the speed of movements(in pixels). The Constructor takes the position of the character 
	 * as arguments and the objectType which is bomberMan or enemy. 
	 */
	public Character(int xPos,int yPos,String location,String objectType)
	{
		super(xPos,yPos,location,objectType);
		this.alive=true;
		this.speed=CONSTANTS.DEFAULT_SPEED;
		this.numOfLives=0;
	}
	/** 
	 * This method returns the speed of the character 
	 */
	public int getSpeed()
	{
		return this.speed;
	}
	/** 
	 * This method moves the character upwards given the numberOfSteps in pixels 
	 */
	public void moveUp(int steps) {
		
		setPositionY(getPositionY()-steps);
	}
	/** 
	 * This method moves the character downwards given the numberOfSteps in pixels 
	 */
	public void moveDown(int steps) {
		
		setPositionY(getPositionY()+steps);
	}
	/** 
	 * This method moves the character right given the numberOfSteps in pixels 
	 */
	public void moveRight(int steps) {
		
		setPositionX(getPositionX()+steps);
	}
	/** 
	 * This method moves the character left given the numberOfSteps in pixels 
	 */
	public void moveLeft(int steps) {
		
		setPositionX(getPositionX()-steps);
	}
	/** 
	 * This method updates the speed of the character when bomberMan picks up a powerUp 
	 * for example. This method could be moved to the bomberMan class if the enemy didn't use it. 
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
	public boolean getIsAlive(){return this.alive;}
	public void setIAlive(boolean setLive){this.alive = setLive;}
	
	public void setLastDirection(int direction){this.lastDirection = direction;}
    public int getLastDirection(){return this.lastDirection;}
    public void setForbiddenDirection(int direction){this.forbiddenDirection = direction;}
    public int getForbiddenDirection(){return this.forbiddenDirection;}

}
