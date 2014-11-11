/* 
 * File: Bomb.java
 * -----------------------
 * This Class represents a Bomb on the game board which is dropped by
 * BomberMan. It should eventually be able to perform any function of a bomb. 
 */
package bomberMan.gamePlay.Model;
import java.util.*;
public class Bomb extends GameObject {
	
	/*Instance Variables*/
	private Calendar timer; 
	private long  timeToExplode;
	private int explosionRange;
	private boolean detonatorActivated;
	public long detonationTime;
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the Bomb private variables.
	 */
	public Bomb() {
		this.timeToExplode = CONSTANTS.BOMB_TIMER;
		this.timer=Calendar.getInstance();
		this.explosionRange=CONSTANTS.DEFAULT_BOMB_RANGE;
		this.detonatorActivated=false;
	}
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the Bomb private variables. Timer is the time given for the bomb to explode. 
	 * Explosion Range is the range of the flames of the bomb, this can be altered by powerUps
	 * detonatorActivated is a boolean that indicated if bomberMan is the one who controls the bomb or 
	 * should the bomb explodes with the timer counting down to 0.
	 */
	public Bomb(int xPos, int yPos, long timerTime, int explosionRange,boolean detonatorActivated) {
		super(xPos,yPos,CONSTANTS.Bomb_IMAGE,"Bomb");
		this.timeToExplode=timerTime;
		
		this.timer=Calendar.getInstance();
		long creationTime = timer.getTimeInMillis();
		this.detonationTime = creationTime+timerTime;
		this.explosionRange=explosionRange;
		this.detonatorActivated=detonatorActivated;
		System.out.println("CreationTime: "+creationTime);
		System.out.println("TimerTime: "+timerTime);
		System.out.println("detonationTime: "+detonationTime);
		this.setType("Bomb");
	}
	/** 
	 * This method return a boolean if the detonator is activated which indicates that bomberMan should 
	 * give the signal to explode the bomb. 
	 */
	public boolean isDetonatorActivated() {
		return detonatorActivated;
	}
	public void activateDetonator() {
		this.detonatorActivated = true;
	}
	public void desactivateDetonator() {
		this.detonatorActivated = false;
	}
    public void setTimeToExplode(long time){this.timeToExplode = time;}
    public long getTimeToExplode(){return this.timeToExplode;}
    public void setDetonationTime(long time){this.detonationTime = time;}
    public long getDetonationTime(){return this.detonationTime;}
    public long getBombTimer(){return Calendar.getInstance().getTimeInMillis();}
    public int getBombRange(){return this.explosionRange;}
    public void setBombRange(int range){this.explosionRange = range;}
    
    

}
