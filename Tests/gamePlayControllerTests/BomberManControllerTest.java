package gamePlayControllerTests;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.event.KeyEvent;

import org.junit.*;

import bomberMan.gamePlay.Model.*;
import bomberMan.gamePlay.Model.Character;
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
 public void testGetMyBoard(){
	 assertSame(myGameBoard, bmCont.getmyGameBoard());
 }
 @Test
 public void testCheckCollisionRight(){
	 int xPos = bmb.getPositionX();
	 int yPos = bmb.getPositionY();
	 int speed = bmb.getSpeed();
	 int width = CONSTANTS.TILE_SIDE_SIZE;
	 if(!myGameBoard.getCell((yPos)/width,(xPos + speed + CONSTANTS.BOMBERMAN_WIDTH)/width).isEmptyPowerUpException()){
	 assertTrue(bmCont.checkCollisionRight(bmb,2));}
	 if(!this.myGameBoard.getCell((yPos+ CONSTANTS.BOMBERMAN_HEIGHT)/width,(xPos + speed + CONSTANTS.BOMBERMAN_WIDTH)/width).isEmptyPowerUpException()){
	 assertTrue(bmCont.checkCollisionRight(bmb,2)); 
	 }
	 else{
		 assertTrue(bmCont.checkCollisionRight(bmb,2)==false); 
	 }
	
 }

 @Test
 public void testCheckCollisionLeft(){
	 int xPos = bmb.getPositionX();
	 int yPos = bmb.getPositionY();
	 int speed = bmb.getSpeed();
	 int width = CONSTANTS.TILE_SIDE_SIZE;
	 if(!this.myGameBoard.getCell((yPos)/width,(xPos - speed)/width).isEmptyPowerUpException()){
	 assertTrue(bmCont.checkCollisionLeft(bmb,1));}
	 if(!this.myGameBoard.getCell((yPos+ CONSTANTS.BOMBERMAN_HEIGHT)/width,(xPos - speed)/width).isEmptyPowerUpException()){
	 assertTrue(bmCont.checkCollisionLeft(bmb,1)); 
	 }
	 else{
		 assertTrue(bmCont.checkCollisionLeft(bmb,1)==false); 
	 }
	
 }

 @Test
 public void testCheckCollisionUp(){
	 int xPos = bmb.getPositionX();
	 int yPos = bmb.getPositionY();
	 int speed = bmb.getSpeed();
	 int width = CONSTANTS.TILE_SIDE_SIZE;
	 if(!this.myGameBoard.getCell((yPos - speed)/width,(xPos)/width).isEmptyPowerUpException()){
	 assertTrue(bmCont.checkCollisionUp(bmb,3));}
	 if(!this.myGameBoard.getCell((yPos- speed)/width,(xPos+ CONSTANTS.BOMBERMAN_WIDTH)/width).isEmptyPowerUpException()){
	 assertTrue(bmCont.checkCollisionUp(bmb,3)); 
	 }
	 else{
		 assertTrue(bmCont.checkCollisionUp(bmb,3)==false); 
	 }
	
 }
 @Test
	public void testMove(){
		
		bmCont.move(KeyEvent.VK_RIGHT);
		assertEquals(bmCont.getGameBoard().getBomberMan().getPositionX(),50);
		bmCont.move(KeyEvent.VK_LEFT);
		assertEquals(bmCont.getGameBoard().getBomberMan().getPositionX(),50); 
		bmCont.move(KeyEvent.VK_UP);
		assertEquals(bmCont.getGameBoard().getBomberMan().getPositionY(),50); 
		bmCont.move(KeyEvent.VK_DOWN);
		assertEquals(bmCont.getGameBoard().getBomberMan().getPositionY(),50); 
	}
 @Test
 public void testCheckCollisionDown(){
	 int xPos = bmb.getPositionX();
	 int yPos = bmb.getPositionY();
	 int speed = bmb.getSpeed();
	 int width = CONSTANTS.TILE_SIDE_SIZE;
	 if(!this.myGameBoard.getCell((yPos + speed + CONSTANTS.BOMBERMAN_WIDTH)/width,(xPos)/width).isEmptyPowerUpException()){
	 assertTrue(bmCont.checkCollisionDown(bmb,4));}
	 if(!this.myGameBoard.getCell((yPos+ speed+ CONSTANTS.BOMBERMAN_WIDTH)/width,(xPos+ CONSTANTS.BOMBERMAN_WIDTH)/width).isEmptyPowerUpException()){
	 assertTrue(bmCont.checkCollisionDown(bmb,4)); 
	 }
	 else{
		 assertTrue(bmCont.checkCollisionDown(bmb,4)==false); 
	 }
	
 }
 @Test
 public void testCheckLeftAndRightUpDownTilesCollision(){
	 int xPos = bmb.getPositionX();
	 int yPos = bmb.getPositionY();
	 int speed = bmb.getSpeed();
	 int width = CONSTANTS.TILE_SIDE_SIZE;
	 int heightBMB = CONSTANTS.BOMBERMAN_HEIGHT;
	 int widthBMB = CONSTANTS.BOMBERMAN_WIDTH;
	 boolean right = this.myGameBoard.getCell(((yPos)/width),1+(xPos)/width).isEmptyPowerUpException();
	 boolean left = this.myGameBoard.getCell(((yPos)/width),-1+(xPos)/width).isEmptyPowerUpException();
	 boolean leftA = this.myGameBoard.getCell(((yPos+heightBMB)/ width),-1+(xPos)/width).isEmptyPowerUpException();
	 boolean rightA = this.myGameBoard.getCell(((yPos+heightBMB)/ width),1+(xPos)/width).isEmptyPowerUpException();
	 assertEquals(bmCont.checkLeftAndRightUpDownTilesCollision(bmb, 1),(!left &&! right) || (!leftA && !rightA));
	 assertEquals(bmCont.checkLeftAndRightUpDownTilesCollision(bmb, 2),(!left &&! right) || (!leftA && !rightA));
	 boolean down = this.myGameBoard.getCell((1+(yPos)/width),( ((xPos)/width))).isEmptyPowerUpException();
	 boolean up = this.myGameBoard.getCell((-1+(yPos)/width),(((xPos)/width))).isEmptyPowerUpException();
	 boolean downA = this.myGameBoard.getCell((1+(yPos)/width),( ((xPos+ widthBMB)/width))).isEmptyPowerUpException();
	 boolean upA = this.myGameBoard.getCell((-1+(yPos)/width),( ((xPos+ widthBMB)/width))).isEmptyPowerUpException();
     assertEquals(bmCont.checkLeftAndRightUpDownTilesCollision(bmb, 3),(!up && !down) || (!downA && !upA) );
     assertEquals(bmCont.checkLeftAndRightUpDownTilesCollision(bmb, 4),(!up && !down) || (!downA && !upA) );
 }
 @Test
 public void testCheckIfCharacterIsCenteredXAxis(){
      assertEquals(bmCont.checkIfCharacterIsCenteredXAxis(bmb), false);
	  assertEquals(bmb.getPositionX() % CONSTANTS.TILE_SIDE_SIZE == ((CONSTANTS.TILE_SIDE_SIZE/2)-(CONSTANTS.BOMBERMAN_WIDTH/2)),bmCont.checkIfCharacterIsCenteredXAxis(bmb));
	
	}
 @Test
 public void  testCheckIfCharacterIsCenteredYAxis(){
	 assertEquals(bmCont.checkIfCharacterIsCenteredYAxis(bmb), false);
	 assertEquals(bmb.getPositionY() % CONSTANTS.TILE_SIDE_SIZE == ((CONSTANTS.TILE_SIDE_SIZE/2)-(CONSTANTS.BOMBERMAN_HEIGHT/2)), bmCont.checkIfCharacterIsCenteredYAxis(bmb));
	 	
	 }
@Test
public void testComputeOffsetFromCenterXAxis(){
	BomberMan bmb1 = new BomberMan(130, 50);
	BomberMan bmb2 = new BomberMan(110, 30);
	BomberMan bmb3 = new BomberMan(140, 0);
	assertEquals(bmCont.computeOffsetFromCenterXaxis(bmb1), -10);
	assertEquals(bmCont.computeOffsetFromCenterXaxis(bmb2), 10);
	assertEquals(bmCont.computeOffsetFromCenterXaxis(bmb), -20);
	assertEquals(bmCont.computeOffsetFromCenterXaxis(bmb3), 0);
}
@Test
public void testComputeOffsetFromCenterYAxis(){
	BomberMan bmb1 = new BomberMan(130, 50);
	BomberMan bmb2 = new BomberMan(110, 30);
	BomberMan bmb3 = new BomberMan(140, 20);
	assertEquals(bmCont.computeOffsetFromCenterYaxis(bmb1), -10);
	assertEquals(bmCont.computeOffsetFromCenterYaxis(bmb2), 10);
	assertEquals(bmCont.computeOffsetFromCenterYaxis(bmb), -20);
	assertEquals(bmCont.computeOffsetFromCenterYaxis(bmb3), 0);
}
@Test
public void testIsCellSharedWithBOMB(){
	assertEquals(bmCont.isCellSharedWithBOMB(bmb), false);
    myGameBoard.getCell(1,1).insert(new Bomb(40,40,1000,0, false, "Bomb"));
    myGameBoard.getCell(1,1).setHasABomb(true);
    BomberMan bmb1 = new BomberMan(45, 45);
    BomberMan bmb2 = new BomberMan(105, 45);
    assertTrue(bmCont.isCellSharedWithBOMB(bmb1));
    assertTrue(bmCont.isCellSharedWithBOMB(bmb2) == false);

}
@Test
public void testMoveInsideABomb(){
	myGameBoard.getCell(1, 1).insert(new Bomb(1,1,1,0, false, "Bomb"));
	myGameBoard.getCell(1,1).setHasABomb(true);
	bmCont.moveInsideBomb(KeyEvent.VK_RIGHT);
	assertEquals(bmCont.getGameBoard().getBomberMan().getPositionX(),50);
	bmCont.moveInsideBomb(KeyEvent.VK_LEFT);
	assertEquals(bmCont.getGameBoard().getBomberMan().getPositionX(),50); 
	bmCont.moveInsideBomb(KeyEvent.VK_UP);
	assertEquals(bmCont.getGameBoard().getBomberMan().getPositionY(),50); 
	bmCont.moveInsideBomb(KeyEvent.VK_DOWN);
	assertEquals(bmCont.getGameBoard().getBomberMan().getPositionY(),50); 
}
@Test
public void testDropABomb(){
	assertTrue(bmCont.getGameBoard().getCell(1,1).getHasABomb()== false);
	bmCont.dropBomb();
	assertTrue(bmCont.getGameBoard().getCell(1,1).getHasABomb());
}
@Test
public void testGetDirection(){
	assertEquals(2, bmCont.getDirection(KeyEvent.VK_RIGHT));
	assertEquals(1, bmCont.getDirection(KeyEvent.VK_LEFT));
	assertEquals(4, bmCont.getDirection(KeyEvent.VK_DOWN));
	assertEquals(3, bmCont.getDirection(KeyEvent.VK_UP));
}
@Test
public void testPickAPowerUp(){
	bmCont.getGameBoard().getCell(1,1).insert(new PowerUp(40,40,CONSTANTS.BOMBS_POWERUP, PowerUpType.BOMBS, "PowerUp"));
    
	assertTrue(bmCont.getGameBoard().getCell(1,1).searcHasAPowerUp());
	bmCont.pickPowerUp(1,1);
	assertTrue(bmCont.getGameBoard().getCell(1,1).searcHasAPowerUp() == false);
}
}
