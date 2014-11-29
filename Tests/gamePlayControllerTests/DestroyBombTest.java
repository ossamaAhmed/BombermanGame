package gamePlayControllerTests;

import static org.junit.Assert.*;

import org.junit.*;

import bomberMan.gamePlay.Controller.*;
import bomberMan.gamePlay.Model.*;

import org.junit.Test;
/*GameBoard gameBoard;
GameBoardController controller;
public DestroyBomb(GameBoard board, GameBoardController controller){
	this.gameBoard = board;
	this.controller = controller;
}*/
public class DestroyBombTest {
	GameBoard myGameBoard;
	GameBoardController gmController;
	DestroyBomb dBomb;
	@Before
	public void setUp(){
		int powerUpsAcquired[] = new int [3] ;
		 powerUpsAcquired[0]= 0;
		 powerUpsAcquired[1] = 0;
		 powerUpsAcquired[2] = 0;
		 int stage[] = {0,0,0,0,0,0,0,0,1};
		myGameBoard = new GameBoard(1,stage, powerUpsAcquired, 3,null, 0);
   	    gmController = new GameBoardController(myGameBoard);
   	    dBomb = new DestroyBomb(myGameBoard, gmController);
	}
	@Test
	public void testDestroyBombConstructor() {
		assertSame(gmController, dBomb.getGameBoardControlle());
		assertSame(myGameBoard, dBomb.getGameBoard());
	}

}
