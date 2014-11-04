/* 
 * File: GameObject.java
 * -----------------------
 * This Class represents the game objects, anything that can be displayed on the screen is 
 * a game object.
 */

package bomberMan.gamePlay.Model;

public class GameObject implements Position {
	
	/*Instance Variables*/
	private int xPosition;
	private int yPosition;
	private String imageLocation;
	private String objectType;
	
	/** 
	 * Constructor
	 * This method takes care of the initialization of the private variables
	 */
	public GameObject() {
		this.xPosition=-1;
		this.yPosition=-1;
		this.imageLocation=null;
		this.objectType=null;
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
	
	public void setPosition(int xPos,int yPos) {
		this.xPosition=xPos;
		this.yPosition=yPos;
	}
	
	public void setPositionX(int xPos) {
		this.xPosition=xPos;
	}
	
	public void setPositionY(int yPos) {
		this.yPosition=yPos;
	}
}
