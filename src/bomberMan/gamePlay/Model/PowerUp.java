package bomberMan.gamePlay.Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PowerUp extends GameObject implements java.io.Serializable {
	
	//Private variables
	private PowerUpType type;
	
	//Constructor
	public PowerUp()
	{
		super("PowerUp");
		type=null;
	}
	
	public PowerUp(int xPos, int yPos, String locationImage, PowerUpType namePowerUp, String typeObject){
		super(xPos, yPos,locationImage,typeObject ); // type object is supposed to be equal to PowerUp
		this.type = namePowerUp;
		
	}
	
	//getters and setters
	public void setPowerUp(PowerUpType type){
		this.type = type;
	}
	
	public PowerUpType getPowerUpType()
	{
		return this.type;
	}

}
