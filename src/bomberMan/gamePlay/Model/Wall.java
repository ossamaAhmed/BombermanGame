/* 
 * File: Wall.java
 * -----------------------
 * This Class represents the game objects any wall in the gameboard; brick or concrete.
 */
package bomberMan.gamePlay.Model;

public class Wall extends GameObject{
	
	/*Instance Variables*/
	private WallType myWallType;
	
	/** 
	 * Constructor
	 * This method takes care of the initialization of the wall type. Not used till now
	 */
	public Wall() {
		this.myWallType= WallType.CONCRETE; 
		
		super.setImageLocation(CONSTANTS.CONCRETE_WALL_IMAGE);
	}
	/** 
	 * Constructor
	 * This method takes care of the initialization of the wall including position and wall type.
	 */
	public Wall(int x,int y,WallType myWallType) 
	{
		this.myWallType=myWallType;
		if(this.myWallType == WallType.CONCRETE){this.setType("Concrete");
		super.setPosition(x, y);
		super.setImageLocation(CONSTANTS.CONCRETE_WALL_IMAGE);
		}
		if(this.myWallType == WallType.BRICK){
			this.setType("Brick");
			super.setPosition(x, y);
			super.setImageLocation(CONSTANTS.BRICK_WALL_IMAGE);	
		}
		
	}

	/** 
	 * The following methods are getters and setters, most of them are not used till now.
	 *  should be reviewed. 
	 */
	public void setWallType(WallType myWallType) {
		this.myWallType=myWallType;
	}
	public boolean isBrick() {
		if(myWallType==WallType.BRICK)
			return true;
		else
			return false;
	}
	public boolean isConcrete() {
		if(myWallType==WallType.CONCRETE)
			return true;
		else
			return false;
	}
	
}