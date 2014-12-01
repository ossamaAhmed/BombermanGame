package bomberMan.gamePlay.Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * This class implements a set of methods that allow the creation of a PowerUp object
 * object in a game board.
 * 
 * 
 * The documentation for the methods contained in this class includes
 * briefs descriptions of the implementations. Such descriptions
 * should be regarded as implementation notes, rather than parts of specifications
 * @author Andres Felipe Rincon Gamboa
 * 
 */
public class PowerUp extends GameObject implements java.io.Serializable {
	
	//Private variables
	private PowerUpType type;
	
	//Constructor
	/**
	 * Default constructor for the PowerUp object.
	 * Calls the parent constructor of the PowerUp
	 */
	public PowerUp(){
		super("PowerUp");
		type=null;
	}
	/**
	 * This constructor creates a PowerUp object given the arguments passed as input.
	 * @param xPos integer position representing the x position of the power up on the x axis.
	 * @param yPos integer position representing the y position of the power up on the y axis.
	 * @param locationImage string representing the location of the image.
	 * @param namePowerUp PowerUpType object representing the power up type.
	 * @param typeObject String object representing the type of object. In this case, it should always be "PowerUp"
	 */
	public PowerUp(int xPos, int yPos, String locationImage, PowerUpType namePowerUp, String typeObject){
		super(xPos, yPos,locationImage,typeObject ); // type object is supposed to be equal to PowerUp
		this.type = namePowerUp;
		
	}
	
	//getters and setters
	/**
	 * This method sets the type of the power up.
	 * @param type a PowerUp type object that is used to set the power up type.
	 */
	public void setPowerUp(PowerUpType type){
		this.type = type;
	}
	/**
	 * This method returns the PowerUpType object of the power up.
	 * @return PowerUp type object representing the type of the power up.
	 */
	public PowerUpType getPowerUpType()
	{
		return this.type;
	}

}
