/* 
 * File: GameObject.java
 * -----------------------
 * This Class represents the game objects, anything that can be displayed on the screen is 
 * a game object. A test class is not done for this class as it is an abstract class and 
 * all is done here is just setting and getting private variables fields. 
 */

package bomberMan.gamePlay.Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

public class GameObject implements Position, java.io.Serializable {
	
	/*Instance Variables*/
	private int xPosition;
	private int yPosition;
	private boolean allowCollisions = false;
	private String imageLocation;
	private String objectType;
	private transient BufferedImage image;
	
	/** 
	 * Constructor
	 * This method takes care of the initialization of the private variables
	 * @param  objectType is the name of the object type, wall, powerup, bomb..etc
	 */
	public GameObject(String objectType) {
		this.xPosition=-1;
		this.yPosition=-1;
		this.imageLocation=null;
		this.objectType=objectType;
	}
	/** 
	 * Constructor
	 * This method takes care of the initialization of the private variables given specific 
	 * arguments. 
	 * @param  xPos is the x position of the game object on the screen
	 * @param  yPos is the y position of the game object on the screen
	 * @param  location is the location of the image file on the drive
	 * @param  objectType is the name of the object type, wall, powerup, bomb..etc
	 */
	public GameObject(int xPos,int yPos,String location, String objectType) {
		this.xPosition=xPos;
		this.yPosition=yPos;
		this.imageLocation=location;
		this.objectType=objectType;
		this.allowCollisions = false;
	}
	
	/** 
	 * Get the Y position of the game object on the screen
	 * @return  gets the y position of the game object
	 */
	public int getPositionY() {
		return this.yPosition;
	}
	/** 
	 * Get the X position of the game object on the screen
	 * @return gets the x position of the game object
	 */
	public int getPositionX() {
		return this.xPosition;
	}
	/** 
	 * Get the type of the game object
	 * @return gets the type of the game object
	 */
	public String getType() {
		return this.objectType;
	}
	/** 
	 * Set the Y position of the game object on the screen
	 * @param yPos is the Y position of the game object on the screen
	 */
	public void setPositionY(int yPos) {
		this.yPosition=yPos;
	}
	/** 
	 * Set the X position of the game object on the screen
	 * @param xPos is the x position of the game object on the screen
	 */
	public void setPositionX(int xPos) {
		this.xPosition=xPos;
	}
	/** 
	 * Set the type of the game object 
	 * @param type is the type of the game object
	 */
	public void setType(String type) {
		this.objectType = type;
	}
	/** 
	 * Sets if collisions are allowed with this game object
	 * @param collisionsAllowed is the flag if collisionAllowed then it should be true
	 */
	public void setAllowCollisions(boolean collisionsAllowed){
		this.allowCollisions=collisionsAllowed;
	}
	/** 
	 * Get the image location of the gameobject
	 * @return the image location on the drive of the game object
	 */
	public String getImageLocation() {
		return this.imageLocation;
	}
	/** 
	 * Set the image location of the game object
	 * @param  location is the location of the image of the game object on the drive 
	 */
	public void setImageLocation(String location) {
		this.imageLocation=location;
	}
	/** 
	 * Gets if collisions are allowed with this game object
	 * @return gets if collisions are allowed with this game object
	 */
	public boolean getAllowCollisions(){
		return this.allowCollisions;
	}
	/** 
	 * Set the X and Y position of the game object
	 * @param xPos is the x position of the game object on the screen 
	 * @param yPos is the y position of the game object on the screen
	 */
	public void setPosition(int xPos,int yPos) {
		this.xPosition=xPos;
		this.yPosition=yPos;
	}
	/** 
	 * Returns the Image of the game object
	 * @return the image of the game object
	 */
	public BufferedImage getImage() {
		
		if(image==null){
			try {
				image = ImageIO.read(new File(GameObject.class.getResource("/image/" + this.getImageLocation()).toURI()));
			//	image = ImageIO.read(new File(resthis.getImageLocation()));
			}
			catch (Exception e) {
				System.out.println("Image Not found: " + this.getImageLocation());
			}
		}
		return image;
	}
	/** 
	 * Sets the image of the game object
	 * @param image is the image location on the drive
	 */
	public void setImage(String image){
		this.setImageLocation(image);
		try {
			this.image = ImageIO.read(new File(this.getImageLocation()));
		}
		catch (IOException e) {
			System.out.println("Image Not found");
		}
		
	}

}
