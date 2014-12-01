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
/**
 * This class implements a set of methods that allow the insertion of  the Bomberman 
 * object in a game board.
 * 
 * 
 * 
 * The documentation for the methods contained in this class includes
 * briefs descriptions of the implementations. Such descriptions
 * should be regarded as implementation notes, rather than parts of specifications
 * @author Andres Felipe Rincon Gamboa
 * 
 * 
 */
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
	 * updated every time he picks a powerUp and also we keep track of the bombs bomberMan
	 * drops for detonating purposes.
	 * @parm xPos the x position of the bomberman on the x axis of the game board.
	 * @param yPos the y position of the bomberman on the x axis of the game board.
	 */
	public BomberMan(int xPos,int yPos)	{
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
	 * @return void
	 */
	public void infoToString() {
		System.out.print("My Position(x,y) is: ");
		System.out.print(getPositionX());
		System.out.print("  "+getPositionY());
	}
	/**
	 * This function adds a bomb object to the arraylist myBombs containing the bombs dropped by the bomberman.
	 * @param objectBomb the Bomb Object that is added to the myBombs arraylist.
	 */
	public void addBomb(Bomb objectBomb){this.myBombs.add(objectBomb);}
	/**
	 * This function returns the row i where the Bomberman is placed on the game board.
	 * @return int represents a row on the game board.
	 */
	public int getICell(){
		return (int)(this.getPositionY()/ CONSTANTS.TILE_SIDE_SIZE);
	}
	/**
	 * This function returns the column j where the Bomberman is placed on the game board.
	 * @return int represents a column on the game board.
	 */
	public int getJCell(){
		return (int)(this.getPositionX()/ CONSTANTS.TILE_SIDE_SIZE);
	}
	/**
	 * This function returns the row i where the bottom of thBomberman is placed on the game board.
	 * @return int represents a row on the game board.
	 */
	public int getICellBottomBomberman(){
		return (int)((this.getPositionY()+ CONSTANTS.BOMBERMAN_HEIGHT)/ CONSTANTS.TILE_SIDE_SIZE);
	}
	/**
	 * This function returns the rightmost column j where the bottom of thBomberman is placed on the game board.
	 * @return int represents a row on the game board.
	 */
	public int getJCellRightMostBomberman(){
		return (int)((this.getPositionX()+ CONSTANTS.BOMBERMAN_WIDTH)/ CONSTANTS.TILE_SIDE_SIZE);
	}
	/**
	 * This function removes the oldest bomb dropped by the bomberman
	 * @return void
	 */
	public void removeOldestBomb(){
		if(this.myBombs.size() > 0){
		this.myBombs.remove(0);}
	}
	/**
	 * This function removes a bomb dropped by the bomberman that has the (x,y) position
	 * @param x position on the x axis of the game board.
	 * @param y position on the y axis of the game board.
	 */
	public void removeBomb(int x, int y){
		int counter = 0;
		if(this.myBombs.size() > 0){
		for(counter = 0; counter < this.myBombs.size(); counter++){
		if(this.myBombs.get(counter).getPositionX() == x && this.myBombs.get(counter).getPositionY() == y ){
		this.myBombs.remove(counter);}}}
	}
	/**
	 * This method returns the oldest bomb dropped by the bomberman.
	 * @return Bomb object that represents the oldest bomb dropped by the Bomberman.
	 */
	public Bomb getOldestBomb(){
		
		if(this.myBombs.size() > 0){
			return this.myBombs.get(0);
		}
		return new Bomb();
	}
	/**
	 * This method makes the bomberman die.
	 * @return void
	 */
	public void die(){
		//if bomberm
		if(!this.invisibilityPowerUp)
		{
		super.die();
		this.setImage(CONSTANTS.BMB_dead);
		//this.setNumOfLives(-1);
		}
		
	}	
	/**
	 * 	This method checks if the bomberman has the detonator power up.
	 * @return a boolean true if the bomberman has the detonator powerup, false otherwise.
	 */
	public boolean getHasDetonator(){return this.hasDetonator;}
	/**
	 * This method adds the detonator power up to the bomberman.
	 * @param set true if the bomberman has the detonator power up, false otherwise
	 * @return void
	 */
	public void setHasDetonator(boolean set){this.hasDetonator = set;}
	/**
	 * This method gets the current bomb explosion range for bombs dropped by the bomberman.
	 *
	 * @return int the bomb's explosion range.
	 */
	public int getBombRange(){return this.currentBombRange;}
	
	/**
	 * This method adds the range value to the current bomb exlosion range for bombs dropped by the bomberman.
	 *@param range the bomb's explosion range added to the current bomb's range.
	 * @return void
	 */
	public void setBombRange(int range){this.currentBombRange += range;}
	/**
	 * This method sets  the current bomb exlosion range for bombs dropped by the bomberman.
	 *@param range the new bomb's explosion range
	 * @return void
	 */
	public void setBombRange1(int range){this.currentBombRange = range;}
	/**
	 * This method gets  the number of bombs that the bomberman can drop at a time.
	 *
	 * @return int the number of bombs that the bomberman can drop at a time.
	 */
	public int getNumBombsToDrop(){return this.numBombsAllowToDrop;}
	/**
	 * This method adds the num value to the current number of bombs that the bomberman can drop at a time.
	 *@param num the number of bombs added to the current number of bombs that the bomberman can drop at a time.
	 * @return void
	 */
	public void setNumBombsToDrop(int num){this.numBombsAllowToDrop += num;}
	/**
	 * This method sets  the number of bombs that the bomberman can drop at a time.
	 *@param num the number of bombs that the bomberman can drop at a time.
	 * @return void
	 */
	public void setNumBombsToDrop1(int num){this.numBombsAllowToDrop = num;}
	/**
	 * This method adds the wall pass power up to the bomberman.
	 * @param set true if the bomberman has the wall pass power up, false otherwise
	 * @return void
	 */
	public void setBrickPass(boolean set){this.canBrickPass = set;}
	/**
	 * This method gets the wall pass power up of the bomberman.
	 * @return boolean  true if the bomberman has the wall pass power up, false otherwise
	 */
	public boolean getBrickPass(){return this.canBrickPass;}
	/**
	 * This method adds the bomb pass power up to the bomberman.
	 * @param set true if the bomberman has the bomb pass power up, false otherwise
	 * @return void
	 */
	public void setBombPass(boolean set){this.canBombPass = set;}
	/**
	 * This method gets the bomb pass power up of the bomberman.
	 * @return boolean  true if the bomberman has the bomb pass power up, false otherwise
	 */
	public boolean getBombPass(){return this.canBombPass;}
	/**
	 * This method adds the flame pass power up to the bomberman.
	 * @param set true if the bomberman has the flame pass power up, false otherwise
	 * @return void
	 */
	public void setFlamePass(boolean set){this.canFlamePass = set;}
	/**
	 * This method gets the flame pass power up of the bomberman.
	 * @return boolean  true if the bomberman has the flame pass power up, false otherwise
	 */
	public boolean getFlamePass(){return this.canFlamePass;}
	/**
	 * This method gets the number of bombs dropped by the bomberman.
	 * @return int representing the number of bombs dropped by the bomberman
	 */
	public int getQteOfBombsDropped(){return this.bombsDropped;}
	/**
	 * This method add the num value to the number of bombs dropped by the bomberman.
	 * @param num integer value added to the number of bombs dropped by the bomberman.
	 * @return void
	 */
	public void setQteOfBombsDropped(int num){ this.bombsDropped += num;}
	/**
	 * This method gets the invisibility power up of the bomberman.
	 * @return boolean  true if the bomberman has the invisibility power up, false otherwise
	 */
	public boolean getInvisibilibityPowerUp(){return this.invisibilityPowerUp;}
	/**
	 * This method adds the invisibility power up to the bomberman.
	 * @param set true if the bomberman has the invisibility pass power up, false otherwise
	 * @return void
	 */
	public void setInvisibilityPowerUp(boolean set){
		this.invisibilityPowerUp = set;}
	/**
	 * This method gets the time  creation of the invisibility power up of the bomberman.
	 * @return int that represents the time creation of the invisibility power up.
	 */
	public long getCreationInvisibilityPowerUp(){return this.creationInvisibilityPowerup;}
	/**
	 * This method sets the time  creation of the invisibility power up of the bomberman.
	 *  @return void
	 */
	public void setCreationInvisibilityPowerUp(){
		this.creationInvisibilityPowerup = timer.getTimeInMillis();
		this.eliminationInvisibilityPowerup = CONSTANTS.delayINVISIBILITY + this.creationInvisibilityPowerup;
		
	}
	/**
	 * This method  updates the time  creation of the invisibility power up of the bomberman.
	 *  @return void
	 */
	public void addTimeCreationInvisibilitPowerUp(){this.creationInvisibilityPowerup=Calendar.getInstance().getTimeInMillis();}
	/**
	 * This method gets the elimination time of the invisibility power up of the bomberman.
	 * @return long that represents the elimination time of the invisibility power up.
	 */
	public long  getEliminationInvisibilityPowerUp(){return this.eliminationInvisibilityPowerup;}
	/**
	 * This method returns the power ups that are kepty by the bomberman after he dies, as long as
	 * he has more than 0 lives remaining. 
	 * @return
	 */
	public int[] getPowerUpsKeptAfterDeath(){
		int [] powerUps = new int [3];
		powerUps[0]= this.numBombsAllowToDrop;
		powerUps[1]= this.currentBombRange;
		powerUps[2] = this.getSpeed();
		return powerUps;
		
	}
	
}
