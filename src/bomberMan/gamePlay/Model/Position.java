/* 
 * File: Position.java
 * -----------------------
 * This Class represents the interface of any object that idenifies by a position on the screem
 * The position is tend to store the x and y position not relative to the screen in pixels
 */
package bomberMan.gamePlay.Model;

public interface Position {
	
	/*Functions to get and set the position of any game object*/
	public int getPositionY();
	public int getPositionX();
	public void setPosition(int xPos,int yPos);
	public void setPositionX(int xPos);
	public void setPositionY(int yPos);
	
}
