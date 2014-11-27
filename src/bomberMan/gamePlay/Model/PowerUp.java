package bomberMan.gamePlay.Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PowerUp extends GameObject implements java.io.Serializable {
	
	//Private variables
	private PowerUpType type;
	private transient BufferedImage image;
	
	//Constructor
	public PowerUp()
	{
		super("PowerUp");
		type=null;
	}
	
	public PowerUp(int xPos, int yPos, String locationImage, PowerUpType namePowerUp, String typeObject){
		super(xPos, yPos,locationImage,typeObject ); // type object is supposed to be equal to PowerUp
		this.type = namePowerUp;
		image=null;
		
	}
	
	//getters and setters
	public void setPowerUp(PowerUpType type){
		this.type = type;
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
	
	public PowerUpType getPowerUpType()
	{
		return this.type;
	}

}
