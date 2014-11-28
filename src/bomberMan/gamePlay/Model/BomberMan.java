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
import java.util.Calendar;
public class BomberMan extends Character implements java.io.Serializable {
	
	/*Instance Variables*/
	private ArrayList<Bomb> myBombs;
	private ArrayList<PowerUp> myPowerUps;
	boolean canBrickPass = false;
	boolean canFlamePass = false;
	boolean invisibilityPowerUp=false;
	int currentBombRange = CONSTANTS.BOMB_RANGE1;
	int currentSpeed = CONSTANTS.DEFAULT_SPEEDBOMBERMAN;
	boolean hasDetonator = false;
	boolean canBombPass = false;
	int numBombsAllowToDrop = 0;
	int bombsDropped = 0;
	Calendar timer;
	long creationInvisibilityPowerup = 0;
	long eliminationInvisibilityPowerup =10;
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
		this.timer = Calendar.getInstance();
		this.setNumOfLives(CONSTANTS.LIVESBOMBERMAN);
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
	public void removeBomb(int x, int y){
		int counter = 0;
		if(this.myBombs.size() > 0){
		for(counter = 0; counter < this.myBombs.size(); counter++){
		if(this.myBombs.get(counter).getPositionX() == x && this.myBombs.get(counter).getPositionY() == y ){
		this.myBombs.remove(counter);}}}
	}
	// returns the oldest detonator bomb dropped
	public Bomb getOldestBomb(){
		
		if(this.myBombs.size() > 0){
			return this.myBombs.get(0);
		}
		return new Bomb();
	}
	public void die()
	{
		//if bomberm
		if(!this.invisibilityPowerUp)
		{
		super.die();
		this.setImage(CONSTANTS.BMB_dead);
		//this.setNumOfLives(-1);
		}
		
	}	
	
	
	public boolean getHasDetonator(){return this.hasDetonator;}
	//public void addPowerUp(PowerUp powerUp){this.myPowerUps.add(powerUp);}
	public void setHasDetonator(boolean set){this.hasDetonator = set;}
	//changes
	public int getBombRange(){return this.currentBombRange;}
	public void setBombRange(int range){this.currentBombRange += range;}
	public void setBombRange1(int range){this.currentBombRange = range;}
	public int getNumBombsToDrop(){return this.numBombsAllowToDrop;}
	public void setNumBombsToDrop(int num){this.numBombsAllowToDrop += num;}
	public void setNumBombsToDrop1(int num){this.numBombsAllowToDrop = num;}
	public void setBrickPass(boolean set){this.canBrickPass = set;}
	public boolean getBrickPass(){return this.canBrickPass;}
	public void setBombPass(boolean set){this.canBombPass = set;}
	public boolean getBombPass(){return this.canBombPass;}
	public void setFlamePass(boolean set){this.canFlamePass = set;}
	public boolean getFlamePass(){return this.canFlamePass;}
	public int getQteOfBombsDropped(){return this.bombsDropped;}
	public void setQteOfBombsDropped(int num){ this.bombsDropped += num;}
	public boolean getInvisibilibityPowerUp(){return this.invisibilityPowerUp;}
	public void setInvisibilityPowerUp(boolean set){
		this.invisibilityPowerUp = set;}
	public long getCreationInvisibilityPowerUp(){return this.creationInvisibilityPowerup;}
	public void setCreationInvisibilityPowerUp(){
		this.creationInvisibilityPowerup = timer.getTimeInMillis();
		this.eliminationInvisibilityPowerup = CONSTANTS.delayINVISIBILITY + this.creationInvisibilityPowerup;
		
	}
	public void addTimeCreationInvisibilitPowerUp(){this.creationInvisibilityPowerup=Calendar.getInstance().getTimeInMillis();}
	public long  getEliminationInvisibilityPowerUp(){return this.eliminationInvisibilityPowerup;}
	public int[] getPowerUpsKeptAfterDeath(){
		int [] powerUps = new int [3];
		powerUps[0]= this.numBombsAllowToDrop;
		powerUps[1]= this.currentBombRange;
		powerUps[2] = this.getSpeed();
		return powerUps;
		
	}
	
}
