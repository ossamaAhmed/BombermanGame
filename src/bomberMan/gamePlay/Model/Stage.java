
package bomberMan.gamePlay.Model;

/**
 * This class contains all the normal and bonus stage information stored as a
 * double integer array. The stages can be also be obtained through the get methods.
 * 
 * @author Tong Yi
 *
 */
public class Stage implements java.io.Serializable{
	
	private static int bonusStage[][] = {
			{1,0,0,0,0,0,0,0},
			{0,1,0,0,0,0,0,0},
			{0,0,1,0,0,0,0,0},
			{0,0,0,1,0,0,0,0},
			{0,0,0,0,0,1,0,0},
			
			{0,0,0,0,1,0,0,0},
			{0,0,0,0,0,0,1,0},
			{0,0,0,0,0,0,0,1},
			{0,0,0,0,0,0,0,1},
			{0,0,0,0,0,0,0,1},
	};
	
	private static int allStages[][] = {
			{6,0,0,0,0,0,0,0,2},
			{3,3,0,0,0,0,0,0,1},
			{2,2,2,0,0,0,0,0,5},
			{1,1,2,2,0,0,0,0,3},
			{0,4,3,0,0,0,0,0,1},
			
			{0,2,3,2,0,0,0,0,1},
			{0,2,3,0,2,0,0,0,2},
			{0,1,2,4,0,0,0,0,5},
			{0,1,1,4,1,0,0,0,6},
			{0,1,1,1,3,1,0,0,4},
			
			{0,1,2,3,1,1,0,0,1},
			{0,1,1,1,4,1,0,0,1},
			{0,0,3,3,3,0,0,0,5},
			{0,0,0,0,0,7,1,0,6},
			{0,0,1,3,3,0,1,0,2},
			
			{0,0,0,3,4,0,1,0,4},
			{0,0,5,0,2,0,1,0,1},
			{3,3,0,0,0,0,2,0,6},
			{1,1,3,0,0,1,2,0,1},
			{0,1,1,1,2,1,2,0,5},
			
			{0,0,0,0,4,3,2,0,6},
			{0,0,4,3,1,0,1,0,5},
			{0,0,2,2,2,2,1,0,1},
			{0,0,1,1,4,2,1,0,5},
			{0,1,1,1,2,2,1,0,6},
			
			{1,1,1,1,2,1,1,0,8},
			{1,1,0,0,5,1,1,0,2},
			{0,1,3,3,1,0,1,0,1},
			{0,0,0,0,2,5,2,0,5},
			{0,0,3,2,1,2,1,0,7},
			
			{0,2,2,2,2,2,0,0,4},
			{0,1,1,3,4,0,1,0,1},
			{0,0,2,2,3,1,2,0,5},
			{0,0,2,3,3,0,2,0,8},
			{0,0,2,1,3,1,2,0,6},
			
			{0,0,2,2,3,0,3,0,7},
			{0,0,2,1,3,1,3,0,5},
			{0,0,2,2,3,0,3,0,2},
			{0,0,1,1,2,2,4,0,4},
			{0,0,1,2,3,0,4,0,8},
			
			{0,0,1,1,3,1,4,0,5},
			{0,0,0,1,3,1,5,0,4},
			{0,0,0,1,2,1,6,0,6},
			{0,0,0,1,2,1,6,0,5},
			{0,0,0,0,2,2,6,0,8},
			
			{0,0,0,0,2,2,6,0,4},
			{0,0,0,0,2,2,6,0,6},
			{0,0,0,0,2,1,6,1,5},
			{0,0,0,0,1,2,6,1,7},
			{0,0,0,0,1,2,5,2,8}
	};

	/**
	 * Method which given a stage number will return the appropriate stage
	 * information stored in a integer array. The first eight positions
	 * corresponds to an enemy type, while each integer within each position
	 * corresponds to the number of enemies. In the ninth position is the power
	 * up type, with different integer representing different power ups.
	 * 
	 * @param x
	 *            an integer which corresponds to the stage
	 * @return integer array with its appropriate enemies and power ups to that
	 *         particular stage.
	 */
	public static int[] getStage(int x){
		int stage[] = new int[9];
		for(int i = 0; i < 9; i++){
			stage[i] = allStages[x-1][i];
		}
		return stage;
	}

	/**
	 * Method which given a bonus stage number will return the appropriate bonus
	 * stage in an integer array with each position representing an enemy, with
	 * 1 in the appropriate slot to tell the game to spawn infinite many
	 * enemies.
	 * 
	 * @param x
	 *            an integer which corresponds to the bonus stage
	 * @return integer array with zeroes in all other position except the one
	 *         where infinite enemies are supposed to appear.
	 */
	public static int[] getBonus(int x){
		int stage[] = new int[9];
		for(int i = 0; i < 9; i++){
			stage[i] = bonusStage[x-1][i];
		}
		return stage;
	}
}
