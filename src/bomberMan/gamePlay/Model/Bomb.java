/* 
 * File: Bomb.java
 * -----------------------
 * This Class represents a Bomb on the game board which is dropped by
 * BomberMan. It should eventually be able to perform any function of a bomb. 
 */
package bomberMan.gamePlay.Model;
import java.util.*;
/**
 * This class implements a set of methods that allow the insertion of  Bomb object in game board during a game session
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
public class Bomb extends GameObject implements java.io.Serializable {
	
	/*Instance Variables*/
	private Calendar timer; 
	private long  timeToExplode;
	private int explosionRange;
	private boolean detonatorActivated;
	private boolean explodeFast;
	public long detonationTime;
	
	/** 
	 * Constructor
	 * This is the default Bomb object constructor that creates a bomb object. This constructor
	 *  takes care of any initialization needed for the Bomb private variables.
	 */
	public Bomb() 
	{
		super("Bomb");
		this.timeToExplode = CONSTANTS.BOMB_TIMER;
		this.timer=Calendar.getInstance();
		this.explosionRange=CONSTANTS.DEFAULT_BOMB_RANGE;
		this.detonatorActivated=false;
	}
	
	/**
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the Bomb private variables. 
	 * @param xPos integer that represents the bomb position on the x axis
	 * @param yPos integer that represents the bomb position on the y axis
	 * @param timerTime is the time given for the bomb to explode.
	 * @param explosionRange is the range of the flames of the bomb, this can be altered by powerUps
	 * @param detonatorActivated is a boolean that indicated if bomberMan is the one who controls the bomb.
	 * @param typeBomb string that represents the type of bomb created. This should always be "Bomb".
	 */
	public Bomb(int xPos, int yPos, long timerTime, int explosionRange,boolean detonatorActivated, String typeBomb) {
		super(xPos,yPos,CONSTANTS.Bomb_IMAGE,typeBomb);
		this.explodeFast = false;
		this.timeToExplode=timerTime;
		long creationTime = 0;
		this.timer=Calendar.getInstance();
		if(detonatorActivated == false){
		 creationTime = timer.getTimeInMillis();
		this.detonationTime = creationTime+timerTime;}
		if(detonatorActivated == true){
		    creationTime = timer.getTimeInMillis();
			this.detonationTime = creationTime;}
		this.explosionRange=explosionRange;
		this.detonatorActivated=detonatorActivated;
		System.out.println("CreationTime: "+creationTime);
		System.out.println("TimerTime: "+timerTime);
		System.out.println("detonationTime: "+detonationTime);
		if(detonatorActivated == false){
		this.setType("Bomb");}
		if(detonatorActivated == true){
			this.setType("Bomb");}
			
		
	}
	/** 
	 * This method return a boolean if the detonator is activated which indicates that bomberMan should 
	 * give the signal to explode the bomb. 
	 * @return boolean true if the bomb explodes with a detonator, false otherwise.
	 */
	public boolean isDetonatorActivated() {
		return detonatorActivated;
	}
	/**
	 * This method activates the detonator of a bomb that is going to be detonated by the bomberman.
	 * @return void
	 * 
	 */
	public void activateDetonator() {
		this.detonatorActivated = true;
	}
	/**
	 * This method desactivates the detonator of a bomb that is going to be detonated by the bomberman.
	 * @return void
	 * 
	 */
	public void desactivateDetonator() {
		this.detonatorActivated = false;
	}
	/**
	 * This method sets the time at which the bomb is going to explode.
	 * @param time a long integer representing the bomb's time of explosion
	 * @return void
	 * 
	 */
    public void setTimeToExplode(long time){this.timeToExplode = time;}
    /**
	 * This method gets the time at which the bomb is going to explode.
	 * @param time when the bomb is going to explode.
	 *@return long the time of explosion of the bomb
	 * 
	 */
    public long getTimeToExplode(){return this.timeToExplode;}
    /**
	 * This method sets the detonation time at which the bomb is going to explode.
	 * @param time a long integer representing the bomb's detonation time.
	 * @return void
	 * 
	 */
    public void setDetonationTime(long time){this.detonationTime = time;}
    /**
   	 * This method gets the detonation time at which the bomb is going to explode.
   	 * @param time  the detonation time when the bomb is going to explode.
   	 *@return long the detonation time of the bomb
   	 * 
   	 */
    public long getDetonationTime(){return this.detonationTime;}
    /**
   	 * This method gets the current time of the bomb timer.
   	 *@return long the current time of the bomb's timer
   	 * 
   	 */
    public long getBombTimer(){return Calendar.getInstance().getTimeInMillis();}
    /**
   	 * This method gets the explosion range of the bomb.
   	 *@return int the explosion range of the bomb.
   	 * 
   	 */
    public int getBombRange(){return this.explosionRange;}
    /**
   	 * This method sets the explosion range of the bomb.
   	 *@return void
   	 * 
   	 */
    public void setBombRange(int range){this.explosionRange = range;}
    /**
   	 * This method gets a boolean that makes the bomb explode suddenly.
   	 * 
   	 *@return boolean true if the bomb has to explode now, false otherwise.
   	 * 
   	 */
    public boolean getExplodeFast(){return this.explodeFast;}
    /**
   	 * This method sets a boolean that makes the bomb explode suddenly.
   	 * @param set true if the bomb has to explode now, false otherwise.
   	 *@return void
   	 * 
   	 */
    public void setExplodeFast(boolean set){this.explodeFast = set;}
    

}
