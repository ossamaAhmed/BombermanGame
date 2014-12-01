/* 
 * File: Wall.java
 * -----------------------
 * This Class represents the game object wall in the gameboard; brick or concrete.
 */
package bomberMan.gamePlay.Model;


public class Wall extends GameObject implements java.io.Serializable{
	
	/*Instance Variables*/
	private WallType myWallType;
	
	/** 
	 * Constructor
	 * This method takes care of the initialization of the wall type. Not used till now
	 */
	public Wall() {
		super("Wall");
		this.myWallType= WallType.CONCRETE; 
		super.setImageLocation(CONSTANTS.CONCRETE_WALL_IMAGE);
	}
	/** 
	 * Constructor
	 * This method takes care of the initialization of the wall including position and wall type.
	 * @param x is the x position of the wall on the screen 
	 * @param y is the y position of the wall on the screen
	 * @param myWallType is the wall type; wither concrete or brick
	 */
	public Wall(int x,int y,WallType myWallType) {
		super("Wall");
		this.myWallType=myWallType;
		if(this.myWallType == WallType.CONCRETE){
			this.setType("Concrete");
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
	 * Set the wall type 
	 * @param myWallType is the wall type; either concrete or brick
	 */
	public void setWallType(WallType myWallType){
		this.myWallType=myWallType;
	}
	/** 
	 * Is the wall brick wall
	 * @return returns true if the wall is of brick type
	 */
	public boolean isBrick() {
		if(myWallType==WallType.BRICK)
			return true;
		else
			return false;
	}
	/** 
	 * Is the wall concrete wall
	 * @return returns true if the wall is of concrete type
	 */
	public boolean isConcrete() {
		if(myWallType==WallType.CONCRETE)
			return true;
		else
			return false;
	}
	
}