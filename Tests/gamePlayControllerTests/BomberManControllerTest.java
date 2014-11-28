package gamePlayControllerTests;

import static org.junit.Assert.*;

import org.junit.*;

import bomberMan.gamePlay.Model.*;
import bomberMan.gamePlay.Controller.*;
public class BomberManControllerTest {
	BomberManController bmCont;
	GameBoard myGameBoard;
	BomberMan bmb = new BomberMan(120,120);
	@Before
	public void setUp(){
		int powerUpsAcquired[] = new int [3] ;
		 powerUpsAcquired[0]= 0;
		 powerUpsAcquired[1] = 0;
		 powerUpsAcquired[2] = 0;
		 int stage[] = {0,0,0,0,0,0,0,0,1};
		myGameBoard = new GameBoard(1,stage, powerUpsAcquired, 3,null, 0);
		bmCont = new BomberManController(myGameBoard); 
		
	}

	@Test
	public void testConstructor() {
		assertSame(myGameBoard, bmCont.getGameBoard());
		assertTrue(bmCont.getTimer() != null);
		}
	@Test
	public void testCheckCollision(){
		if(bmCont.checkLeftAndRightUpDownTilesCollision(bmb, 1)){
			assertTrue(bmCont.checkCollision(bmb, 1));
		}
		else if(!bmCont.checkLeftAndRightUpDownTilesCollision(bmb, 1)){
			if(!bmCont.checkCollisionLeft(bmb, 1)){
			assertTrue(bmCont.checkLeftAndRightUpDownTilesCollision(bmb, 1)== false);}
		}
		if(bmCont.checkLeftAndRightUpDownTilesCollision(bmb, 2)){
			assertTrue(bmCont.checkCollision(bmb, 2));
		}
		else if(!bmCont.checkLeftAndRightUpDownTilesCollision(bmb, 2)){
			if(!bmCont.checkCollisionRight(bmb, 2)){
			assertTrue(bmCont.checkLeftAndRightUpDownTilesCollision(bmb, 2)== false);}
		}
		if(bmCont.checkLeftAndRightUpDownTilesCollision(bmb, 3)){
			assertTrue(bmCont.checkCollision(bmb, 3));
		}
		else if(!bmCont.checkLeftAndRightUpDownTilesCollision(bmb, 3)){
			if(!bmCont.checkCollisionUp(bmb, 3)){
			assertTrue(bmCont.checkLeftAndRightUpDownTilesCollision(bmb, 3)== false);}
		}
		
		if(bmCont.checkLeftAndRightUpDownTilesCollision(bmb, 4)){
			assertTrue(bmCont.checkCollision(bmb, 4));
		}
		else if(!bmCont.checkLeftAndRightUpDownTilesCollision(bmb, 4)){
			if(!bmCont.checkCollisionDown(bmb, 4)){
			assertTrue(bmCont.checkLeftAndRightUpDownTilesCollision(bmb, 4)== false);}
		}
	}
 @Test
 public void testMove(){}
}
