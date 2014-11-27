/* 
 * File: Wall.java
 * -----------------------
 * This Class represents the game objects any wall in the gameboard; brick or concrete.
 */
package bomberMan.gamePlay.Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Wall extends GameObject implements java.io.Serializable{
	
	/*Instance Variables*/
	private WallType myWallType;
	transient BufferedImage  image;
	
	/** 
	 * Constructor
	 * This method takes care of the initialization of the wall type. Not used till now
	 */
	public Wall() 
	{
		super("Wall");
		this.myWallType= WallType.CONCRETE; 
		image=null;
		
		super.setImageLocation(CONSTANTS.CONCRETE_WALL_IMAGE);
	}
	/** 
	 * Constructor
	 * This method takes care of the initialization of the wall including position and wall type.
	 */
	public Wall(int x,int y,WallType myWallType) 
	{
		super("Wall");
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
	public BufferedImage getImage()
	{
		
		if(image==null)
		{
		try {
			image = ImageIO.read(new File(this.getImageLocation()));
		    }
		catch (IOException e) 
		{
			System.out.println("Not found");
		}
		}
		return image;
	}
	public boolean isConcrete() {
		if(myWallType==WallType.CONCRETE)
			return true;
		else
			return false;
	}
	
}