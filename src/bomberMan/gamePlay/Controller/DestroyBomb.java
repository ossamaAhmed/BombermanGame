
package bomberMan.gamePlay.Controller;
import java.util.*;
import java.lang.Runnable;

import bomberMan.gamePlay.Model.*;
/**
 * This class implements a thread for always checking if there are bombs in the game board and detonating them, as well as for 
 * verifying if the bomberman has the invisibility power up and deleting this power up once the delay of possession of this power up expires.
 * 
 * 
 * The documentation for the methods contained in this class includes
 * briefs descriptions of the implementations. Such descriptions
 * should be regarded as implementation notes, rather than parts of specifications
 * @author Andres Felipe Rincon Gamboa
 * 
 * 
 */
public class DestroyBomb extends Thread {
	 GameBoard gameBoard;
	    GameBoardController controller;
	/**
	 * Constructor of the DestroyBomb class.
	 * @param board a GameBoard object representing the static objects on a gameplay session as well as the enemies.
	 * @param controller a GameBoardController object used to update the Gameboard object in particular when bombs detonate or the invisibility power up
	 * is acquired or lost.
	 */
	    public DestroyBomb(GameBoard board, GameBoardController controller){
			this.gameBoard = board;
			this.controller = controller;
		}
	    /**
	     * Method that runs the thread. This functions allows to detonate bombs and deleter the invisibility power up of the bomberman.
	     */
		@Override
		public void run() {
			while(true){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
				this.controller.deleteInvisibilityPowerUp();
				this.controller.detonateRegularBombs();
				}
				}
			
		
/**
 * This function gets the GameBoardController object and field variable of the class
 * @return GameBoardController object
 */
	
  public GameBoardController getGameBoardControlle(){return this.controller;}
 /**
  * This funciton gets the current GameBoard object and field variable of the class
  * @return GameBoard object
  */
  public GameBoard getGameBoard(){return this.gameBoard;}
}
