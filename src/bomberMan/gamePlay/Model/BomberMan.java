/* 
 * File: BomberMan.java
 * -----------------------
 * This Class represents the BomberMan character on the game board. Which is controlled by the 
 * Player/User of the game using the keyboard 
 */
package bomberMan.gamePlay.Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class BomberMan extends Character {
	
	/*Instance Variables*/
	private ArrayList<PowerUp> myPowerUps;
	private ArrayList<Bomb> myBombs;
	boolean canBrickPass = false;
	boolean canFlamePass = false;
	int currentBombRange = CONSTANTS.BOMB_RANGE1;
	int currentSpeed = CONSTANTS.DEFAULT_SPEEDBOMBERMAN;
	boolean hasDetonator = false ;
	boolean canBombPass = false;
	int numBombsAllowToDrop = 0;
	int bombsDropped = 0;
	
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the BomberMan private variables. BomberMan has a list of powerUps where it gets
	 * updated everytime he picks a powerUp and also we keep track of the bombs bomberMan
	 * drops for detonating purposes.
	 */
	public BomberMan(int xPos,int yPos)
	{
		super(xPos,yPos,CONSTANTS.BomberMan_IMAGE,"BomberMan");
		this.myPowerUps=new ArrayList<PowerUp>();
		this.myBombs=new ArrayList<Bomb>();
	}
	/** 
	 * This method prints out the position of bomberMan for debugging purposes.
	 * It should be deleted at the end of the project. When everything is tested out, or 
	 * moved to testing package.
	 */
	public void infoToString() {
		System.out.print("My Position(x,y) is: ");
		System.out.print(getPositionX());
		System.out.print("  "+getPositionY());
	}
	/** 
	 * This method returns the image of the bomberMan currently to be drawn on the screen.
	 * This method is missing as it should implement the changes of bomberman pictures 
	 * corresponding to each move he does. This function throws an exception that should be collected 
	 * in a log file.
	 */
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
	public void addBomb(Bomb objectBomb){this.myBombs.add(objectBomb);}
	public int getICell(){
		return (int)(this.getPositionY()/ CONSTANTS.TILE_SIDE_SIZE);
	}
	public int getJCell(){
		return (int)(this.getPositionX()/ CONSTANTS.TILE_SIDE_SIZE);
	}
	public int getICellBottomBomberman(){
		return (int)((this.getPositionY()+ CONSTANTS.BOMBERMAN_HEIGHT)/ CONSTANTS.TILE_SIDE_SIZE);
	}
	public int getJCellRightMostBomberman(){
		return (int)((this.getPositionX()+ CONSTANTS.BOMBERMAN_WIDTH)/ CONSTANTS.TILE_SIDE_SIZE);
	}
	public void removeOldestBomb(){
		if(this.myBombs.size() > 0){
		this.myBombs.remove(0);}
	}
	// returns the oldest detonator bomb dropped
	public Bomb getOldestBomb(){
		
		if(this.myBombs.size() > 0){
			return this.myBombs.get(0);
		}
		return new Bomb();
	}
	public void die(){
		super.die();
		this.setImageLocation(CONSTANTS.BMB_dead);
		
	}	
	
	
	public boolean getHasDetonator(){return this.hasDetonator;}
	public void addPowerUp(PowerUp powerUp){this.myPowerUps.add(powerUp);}
	public void setHasDetonator(boolean set){this.hasDetonator = set;}
	//changes
	public int getBombRange(){return this.currentBombRange;}
	public void setBombRange(int range){this.currentBombRange = range;}
	public int getNumBombsToDrop(){return this.numBombsAllowToDrop;}
	public void setNumBombsToDrop(int num){this.numBombsAllowToDrop += num;}
	public void setBrickPass(boolean set){this.canBrickPass = set;}
	public boolean getBrickPass(){return this.canBrickPass;}
	public void setBombPass(boolean set){this.canBombPass = set;}
	public boolean getBombPass(){return this.canBombPass;}
	public void setFlamePass(boolean set){this.canFlamePass = set;}
	public boolean getFlamePass(){return this.canFlamePass;}
	public int getQteOfBombsDropped(){return this.bombsDropped;}
	public void setQteOfBombsDropped(int num){ this.bombsDropped += num;}
	
	
}
