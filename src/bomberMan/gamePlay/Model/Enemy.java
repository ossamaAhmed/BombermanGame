package bomberMan.gamePlay.Model;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Enemy extends Character{
	private boolean wallPass;
	private int movmentDirection;
	BufferedImage image;
	
	
	public Enemy(int xPos,int yPos,int movmentDirection, boolean wallPass,String enemyName)
	{
		super(xPos,yPos,enemyName+".png",enemyName);
		this.movmentDirection=movmentDirection;
		this.wallPass=wallPass;
		if(enemyName.equals("Kondoria"))
			{this.updateSpeed(2);
			this.wallPass=true;
			}
		else if (enemyName.equals("Balloon"))
			{this.updateSpeed(3);
			this.wallPass=false;
			}
		else if(enemyName.equals("Pass"))
		{
			this.updateSpeed(3);
			this.wallPass=false;
		}
		image = null;
	}
	
	public BufferedImage getImage()
	{
		if(image==null)
		{
			try {
				image = ImageIO.read(new File(getImageLocation()));
			    }
			catch (IOException e) 
			{
				System.out.println("Not found");
			}
		}
		return image;
	}
	public boolean hasWallPass()
	{
		return this.wallPass;
	}
	public void setWallPass(boolean wallPass)
	{
		this.wallPass=wallPass;
	}
	public int getMovmentDirection()
	{
		return this.movmentDirection;
	}
	public void changeDirection()
	{
		if(this.movmentDirection==2)
		{
			this.movmentDirection=1;
		}
		else if(movmentDirection==1)
		{
			this.movmentDirection=2;
		}
		else if(movmentDirection==3)
		{
			this.movmentDirection=4;
		}
		else if(movmentDirection==4)
		{
			this.movmentDirection=3;
		}
	}
	

}
