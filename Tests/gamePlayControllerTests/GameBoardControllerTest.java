package gamePlayControllerTests;

import static org.junit.Assert.*;


import org.junit.*;

import bomberMan.gamePlay.Controller.*;
import bomberMan.gamePlay.Model.*;

public class GameBoardControllerTest {
    GameBoardController gmController;
    GameBoard myGameBoard;
    
    @Before
    public void setUp(){
    	int powerUpsAcquired[] = new int [3] ;
		 powerUpsAcquired[0]= 0;
		 powerUpsAcquired[1] = 0;
		 powerUpsAcquired[2] = 0;
		 int stage[] = {0,0,0,0,0,0,0,0,1};
		myGameBoard = new GameBoard(1,stage, powerUpsAcquired, 3,null, 0);
    	gmController = new GameBoardController(myGameBoard);
    	
    	
    }
	@Test
	public void testGameBoarControllerConstructoer() {
		assertSame(myGameBoard, gmController.getBoard());
	}
	@Test
	public void testDetonateRegularBombs(){
		Bomb b = new Bomb(80,40,10,10, false, "Bomb");
		myGameBoard.getCell(2, 1).insert(b);
		assertSame(gmController.getBoard().getCell(2,1).searchBomb(), b);
		gmController.detonateRegularBombs();
		assertSame(gmController.getBoard().getBombs().size(), 0);			
	}
	@Test
	public void testKillBomberMan(){
		assertTrue(gmController.getBoard().getBomberMan().getIsAlive());
		gmController.killBomberman1(1,1);
		assertTrue(gmController.getBoard().getBomberMan().getIsAlive() == false);
		}
	@Test
	public void testDestroyBricks(){
		gmController.getBoard().getCell(2, 1).insert(new Wall(80, 40, WallType.BRICK));
		gmController.getBoard().getCell(2, 1).deleteElement("Brick");
		gmController.destroyBricks(1, 1);
		assertTrue(gmController.getBoard().getCell(2, 1).isEmpty());
	}
	@Test
	public void testDestroyEnemies(){
		gmController.getBoard().getCell(2, 1).insert(new Enemy(80, 40,100, "Ballon"));	
		gmController.destroyEnemies(2, 1);
		assertTrue(gmController.getBoard().getEnemies().size() ==0);
	}
	@Test
	public void testDestroyBombsAround(){
		Bomb b = new Bomb(80,40,10,10, false, "Bomb");
		myGameBoard.getCell(2, 1).insert(b);
		assertSame(gmController.getBoard().getCell(2,1).searchBomb(), b);
		gmController.destroyBombsAround(4, 3);
		assertEquals(gmController.getBoard().getBombs().size(), 0);
	}
	@Test
	public void testDetonateOldestBomb(){
		 gmController.getBoard().getBomberMan().addBomb(new Bomb(80,40,10,10, false, "Bomb"));
	     gmController.getBoard().getCell(2,1).setHasADetonatorBomb(true);
	     gmController.getBoard().getBomberMan().setHasDetonator(true);
	     gmController.detonateOldestBomb();
	     assertTrue(gmController.getBoard().getCell(2,1).isEmpty());
	     
	}
	@Test
	public void testDropBombDetonator(){
		assertTrue(gmController.getBoard().getCell(1,1).isEmpty());
		gmController.getBoard().getBomberMan().setHasDetonator(true);
		gmController.dropBombDetonator();
		assertTrue(gmController.getBoard().getCell(1,1).isEmpty()==false);
	}
	@Test
	public void testDeleteInvisibilityPowerUp(){
		assertTrue(gmController.getBoard().getBomberMan().getInvisibilibityPowerUp()== false);
		gmController.getBoard().getBomberMan().setCreationInvisibilityPowerUp();
		gmController.deleteInvisibilityPowerUp();
		assertTrue(gmController.getBoard().getBomberMan().getInvisibilibityPowerUp()== false);
	}
	@Test
	public void testGetBoard(){
		assertSame(myGameBoard, gmController.getBoard());
	}
	
	}
	
	


