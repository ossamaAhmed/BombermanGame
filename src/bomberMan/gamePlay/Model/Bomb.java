/* 
 * File: Bomb.java
 * -----------------------
 * This Class represents a Bomb on the game board which is dropped by
 * BomberMan. It should eventually be able to perform any function of a bomb. 
 */
package bomberMan.gamePlay.Model;

public class Bomb extends GameObject {
	
	/*Instance Variables*/
	private double timer; 
	private int explosionRange;
	private boolean detonatorActivated;
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the Bomb private variables.
	 */
	public Bomb() {
		
		this.timer=CONSTANTS.BOMB_TIMER;
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
	public Bomb(double timer, int explosionRange,boolean detonatorActivated) {
		
		this.timer=timer;
		this.explosionRange=explosionRange;
		this.detonatorActivated=detonatorActivated;
	}
	/** 
	 * This method return a boolean if the detonator is activated which indicates that bomberMan should 
	 * give the signal to explode the bomb. 
	 */
	public boolean isDetonatorActivated() {
		return detonatorActivated;
	}

}
