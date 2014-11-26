package bomberMan.gamePlay.Model;

public class ExitDoor extends GameObject 
{
	public ExitDoor() 
	{
		super("ExitDoor");
		super.setImageLocation(CONSTANTS.EXIT_IMAGE);
	}
	/** 
	 * Constructor
	 * This method takes care of the initialization of the wall including position and wall type.
	 */
	public ExitDoor(int x,int y) 
	{
		super("ExitDoor");
		super.setPosition(x, y);
		super.setImageLocation(CONSTANTS.EXIT_IMAGE);
	}
}
