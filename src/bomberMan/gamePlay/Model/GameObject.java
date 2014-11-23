/* 
 * File: GameObject.java
 * -----------------------
 * This Class represents the game objects, anything that can be displayed on the screen is 
 * a game object.
 */

package bomberMan.gamePlay.Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameObject implements Position {
	
	/*Instance Variables*/
	private int xPosition;
	private int yPosition;
	private boolean allowCollisions = false;
	private String imageLocation;
	private String objectType;
	private BufferedImage image;
	
	/** 
	 * Constructor
	 * This method takes care of the initialization of the private variables
	 */
	public GameObject(String objectType) {
		this.xPosition=-1;
		this.yPosition=-1;
		this.imageLocation=null;
		this.objectType=objectType;
		this.image=null;
	}
	/** 
	 * Constructor
	 * This method takes care of the initialization of the private variables given specific 
	 * arguments. This method is used in several other classes so far. 
	 */
	public GameObject(int xPos,int yPos,String location, String objectType) {
		this.xPosition=xPos;
		this.yPosition=yPos;
		this.imageLocation=location;
		this.objectType=objectType;
		this.allowCollisions = false;
	}
	
	/** 
	 * The following methods are getters and setters, most of them are not used till now.
	 *  should be reviewed. 
	 */
	public int getPositionY() {
		return this.yPosition;
	}
	
	public int getPositionX() {
		return this.xPosition;
	}
	public String getType() {
		return this.objectType;
	}
	
	public String getImageLocation() {
		return this.imageLocation;
	}
	public void setImageLocation(String location) {
		this.imageLocation=location;
	}
	public boolean getAllowCollisions(){
		return this.allowCollisions;
	}
	public void setPosition(int xPos,int yPos) {
		this.xPosition=xPos;
		this.yPosition=yPos;
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
	public void setImage(String image)
	{
		this.setImageLocation(image);
		
		try {
			this.image = ImageIO.read(new File(this.getImageLocation()));
		    }
		catch (IOException e) 
		{
			System.out.println("Not found");
		}
		
	}
	
	public void setPositionX(int xPos) {
		this.xPosition=xPos;
	}
	public void setAllowCollisions(boolean collisionsAllowed){
		this.allowCollisions=collisionsAllowed;
	}
	public void setPositionY(int yPos) {
		this.yPosition=yPos;
	}
	public void setType(String type) {
		this.objectType = type;
	}
}
