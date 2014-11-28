package gamePlayControllerTests;

import static org.junit.Assert.*;

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
 public void checkCollisionRight(){
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
 public void checkCollisionLeft(){
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
 public void checkCollisionUp(){
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
 /*if(direction == 4){
	
	if(!this.myGameBoard.getCell((yPos + speed + CONSTANTS.BOMBERMAN_WIDTH)/width,(xPos)/width).isEmptyPowerUpException()){
		collision = true;
		System.out.println("COLLISION 1");
		
	}
	if(!this.myGameBoard.getCell((yPos+ speed+ CONSTANTS.BOMBERMAN_WIDTH)/width,(xPos+ CONSTANTS.BOMBERMAN_WIDTH)/width).isEmptyPowerUpException()){
		collision = true;
		System.out.println("COLLISION 2");	
	}*/
 @Test
 public void checkCollisionDown(){
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
 public void checkLeftAndRightUpDownTilesCollision(){
	 
 }
 /*public boolean checkLeftAndRightUpDownTilesCollision(Character character, int direction){
int xPos = character.getPositionX();
int yPos =character.getPositionY();
boolean collision = false;
int heightBMB = CONSTANTS.BOMBERMAN_HEIGHT;
int widthBMB = CONSTANTS.BOMBERMAN_WIDTH;
int width = CONSTANTS.TILE_SIDE_SIZE;
if(direction == 1 || direction == 2){
boolean right = this.myGameBoard.getCell(((yPos)/width),1+(xPos)/width).isEmptyPowerUpException();
boolean left = this.myGameBoard.getCell(((yPos)/width),-1+(xPos)/width).isEmptyPowerUpException();
boolean leftA = this.myGameBoard.getCell(((yPos+heightBMB)/ width),-1+(xPos)/width).isEmptyPowerUpException();
boolean rightA = this.myGameBoard.getCell(((yPos+heightBMB)/ width),1+(xPos)/width).isEmptyPowerUpException();
if((!left &&! right) || (!leftA && !rightA)){collision = true;}
}

if(direction == 3 || direction == 4){
	boolean down = this.myGameBoard.getCell((1+(yPos)/width),( ((xPos)/width))).isEmptyPowerUpException();
	boolean up = this.myGameBoard.getCell((-1+(yPos)/width),(((xPos)/width))).isEmptyPowerUpException();
	boolean downA = this.myGameBoard.getCell((1+(yPos)/width),( ((xPos+ widthBMB)/width))).isEmptyPowerUpException();
	boolean upA = this.myGameBoard.getCell((-1+(yPos)/width),( ((xPos+ widthBMB)/width))).isEmptyPowerUpException();
if((!up && !down) || (!downA && !upA)){
	collision = true;}}
return collision;*/
}
