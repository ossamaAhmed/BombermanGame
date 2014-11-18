package bomberMan.gamePlay.Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Enemy extends Character{
	private boolean wallPass;
	private int movmentDirection;
	
	public Enemy(int xPos,int yPos)
	{
		super(xPos,yPos,CONSTANTS.BALLOON_IMAGE,"Balloon");
		this.movmentDirection=3;
		this.wallPass=false;
	}
	
	public BufferedImage getImage()
	{
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(getImageLocation()));
		    }
		catch (IOException e) 
		{
			System.out.println("Not found");
		}

		return image;
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
