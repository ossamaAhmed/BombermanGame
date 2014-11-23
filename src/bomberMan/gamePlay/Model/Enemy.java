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
	private int expectedNextMovmentDirection;
	private BufferedImage image;
	private int score;
	
	
	public Enemy(int xPos,int yPos,int movmentDirection,String enemyName)
	{
		super(xPos,yPos,enemyName+".png",enemyName);
		this.movmentDirection=movmentDirection;
		this.expectedNextMovmentDirection=movmentDirection;
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
	public int getExpectedMovmentDirection()
	{
		return this.expectedNextMovmentDirection;
	}
	public void setExpectedMovmentDirection(int direction)
	{
		this.expectedNextMovmentDirection=direction;
	}
	public void setWallPass(boolean wallPass)
	{
		this.wallPass=wallPass;
	}
	public int getMovmentDirection()
	{
		return this.movmentDirection;
	}
	public void setScore(int score)
	{
		this.score=score;
	}
	public int getScore()
	{
		return this.score;
	}
	public void changeDirection()
	{
		if(this.getExpectedMovmentDirection()==2)
		{
			this.movmentDirection=1;
			this.expectedNextMovmentDirection=1;
		}
		else if(this.getExpectedMovmentDirection()==1)
		{
			this.movmentDirection=2;
			this.expectedNextMovmentDirection=2;
		}
		else if(this.getExpectedMovmentDirection()==3)
		{
			this.movmentDirection=4;
			this.expectedNextMovmentDirection=4;
		}
		else if(this.getExpectedMovmentDirection()==4)
		{
			this.movmentDirection=3;
			this.expectedNextMovmentDirection=3;
		}
	}
	

}
