/* 
 * File: ExitDoor.java
 * -----------------------
 * This Class represents the Exit Door in each stage hidden behind one of the brick walls.
 * A test class is not done for this class as it is only have a constructor.
 */
package bomberMan.gamePlay.Model;

public class Exit extends GameObject 
{
	/** 
	 * Constructor
	 * This method takes care of the initialization of the exit Door 
	 */
	public Exit() {
		super("ExitDoor");
		super.setImageLocation(CONSTANTS.EXIT_IMAGE);
	}
	/** 
	 * Constructor
	 * This method takes care of the initialization of the exit door including position.
	 * @param x is the x position on the screen
	 * @param y is the y position on the screen
	 */
	public Exit(int x,int y) {
		super("ExitDoor");
		super.setPosition(x, y);
		super.setImageLocation(CONSTANTS.EXIT_IMAGE);
	}
	/** 
	 * Constructor
	 * This method takes care of the initialization of the exit door including position.
	 * @param xPos is the x position on the screen
	 * @param yPos is the y position on the screen
	 * @param location is the image location
	 * @param objectType should be Exitdoor
	 */
	public Exit(int xPos,int yPos,String location, String objectType) {
		super(xPos, yPos, location, objectType);
	}
}
