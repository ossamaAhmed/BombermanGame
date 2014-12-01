/* 
 * File: LowIntelligenceEnemy.java
 * -----------------------
 * This Class represents the low intelligence enemy that extends the enemy class. No test 
 * class will be provided for this class. 
 */
package bomberMan.gamePlay.Model;

public class LowIntelligenceEnemy extends Enemy implements java.io.Serializable{
	
	/** 
	 * Constructor
	 * This method takes care of the initialization of the low intelligence enemy
	 *  including position, initial Movement direction and enemyName.
	 * @param x is the x position of the wall on the screen 
	 * @param y is the y position of the wall on the screen
	 * @param movmentDirection is the initial movment direction of the enemy.
	 * @param enemyName is the enemy name, it have to be Balloom or Doll
	 */
	public LowIntelligenceEnemy(int xPos,int yPos,int movmentDirection,String enemyName){
		super(xPos,yPos,movmentDirection,enemyName);
		
		if(enemyName.equals("Balloom")){
			this.updateSpeed(CONSTANTS.SPEEDENEMY_SLOW);
			this.setWallPass(false);
			this.setScore(100);
		}
		else if (enemyName.equals("Doll")){
				this.updateSpeed(CONSTANTS.SPEEDENEMY_NORMAL);
				this.setWallPass(false);
				this.setScore(400);
		}
		
	}

}
