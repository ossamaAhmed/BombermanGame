/* 
 * File: HighIntellegenceEnemy.java
 * -----------------------
 * This Class represents the low intelligence enemy that extends the enemy class. No test 
 * class will be provided for this class. 
 */
package bomberMan.gamePlay.Model;

public class HighIntellegenceEnemy extends Enemy implements java.io.Serializable{
	
	/** 
	 * Constructor
	 * This method takes care of the initialization of the Medium intelligence enemy
	 *  including position, initial Movement direction and enemyName.
	 * @param xPos is the x position of the wall on the screen 
	 * @param yPos is the y position of the wall on the screen
	 * @param movmentDirection is the initial movment direction of the enemy.
	 * @param enemyName is the enemy name, it have to be Kondoria, Pontan or Pass
	 */
	public HighIntellegenceEnemy(int xPos,int yPos,int movmentDirection,String enemyName){
		super(xPos,yPos,movmentDirection,enemyName);
		
		if(enemyName.equals("Kondoria")){
			this.updateSpeed(CONSTANTS.SPEEDENEMY_SLOWEST);
			this.setWallPass(true);
			this.setScore(1000);
		}
		else if (enemyName.equals("Pontan")){
				this.updateSpeed(CONSTANTS.SPEEDENEMY_FAST);
				this.setWallPass(true);
				this.setScore(8000);
		}
		else if(enemyName.equals("Pass")){
			this.updateSpeed(CONSTANTS.SPEEDENEMY_FAST);
			this.setWallPass(false);
			this.setScore(4000);
		}
		
	}

}
