/* 
 * File: BombermanController.java
 * -----------------------
 * This Class represents the control of the bomberman character on screen. Mainly for dropping and detonating bombs 
 * and catching power ups.
 */

package bomberMan.gamePlay.Controller;
import bomberMan.gamePlay.Model.*;

import java.awt.event.KeyEvent;

import bomberMan.gamePlay.Model.Character;
public class BomberManController extends CharacterController {
/*
 * BombermanController constructor
 * */	
public BomberManController(GameBoard myGameBoard){

super(myGameBoard);
}
/*This functions checks if the bomberman next moves are going to cause a collision with other GameObjects.
 * 
 * 
 * */
private boolean checkCollision(Character myCharacter, int direction)
{
	
	if(direction == 1){
		if(this.checkLeftAndRightUpDownTilesCollision(myCharacter, 1)){return true;}
		return checkCollisionLeft(myCharacter, 1);}
	if(direction == 2){
		if(this.checkLeftAndRightUpDownTilesCollision(myCharacter, 2)){return true;}
		return checkCollisionRight(myCharacter, 2);}
	if(direction == 3){
		if(this.checkLeftAndRightUpDownTilesCollision(myCharacter, 3)){return true;}
		return checkCollisionUp(myCharacter, 3);}
	if(direction == 4){
		if(this.checkLeftAndRightUpDownTilesCollision(myCharacter, 4)){return true;}
		return checkCollisionDown(myCharacter, 4);}
	return false;
	
}
/** 
 * This method takes care of movement of a character if UP, DOWN, RIGHT OR LEFT key was pressed
 * it used the collision function to detect if a move should take place
 */
 public void move(KeyEvent keyE) 	 
 {
	 if(this.myGameBoard.getBomberMan().getIsAlive()){
	 if(this.isCellSharedWithBOMB(this.myGameBoard.getBomberMan())){
		 this.moveInsideBomb(keyE, this.getDirection(keyE),this.getDirectionForbidden(keyE) );
	 }
	 else if(!this.isCellSharedWithBOMB(this.myGameBoard.getBomberMan())){
	 boolean centeredXAxis = checkIfCharacterIsCenteredXAxis(myGameBoard.getBomberMan());
	 boolean centeredYAxis = checkIfCharacterIsCenteredYAxis(myGameBoard.getBomberMan());
	 if(keyE.getKeyCode() == KeyEvent.VK_RIGHT &&!(checkCollision(myGameBoard.getBomberMan(), 2)))
	 {
		
		 if(!centeredYAxis){
			 int offset = computeOffsetFromCenterYaxis(myGameBoard.getBomberMan());
			 if(offset > 0){
				 myGameBoard.getBomberMan().moveUp(offset + (CONSTANTS.BOMBERMAN_HEIGHT/2));
			 }
			 if(offset < 0){
				 myGameBoard.getBomberMan().moveDown(-offset - (CONSTANTS.BOMBERMAN_HEIGHT/2));
			 }
		 }
		 	 
		 myGameBoard.getBomberMan().moveRight(myGameBoard.getBomberMan().getSpeed());
	
	 }
	 else if(keyE.getKeyCode() == KeyEvent.VK_LEFT && !(checkCollision(myGameBoard.getBomberMan(), 1)))
	 {
		
		 if(!centeredYAxis){
			 int offset = computeOffsetFromCenterYaxis(myGameBoard.getBomberMan());
			 if(offset > 0){
				 myGameBoard.getBomberMan().moveUp(offset + (CONSTANTS.BOMBERMAN_HEIGHT/2));
			 }
			 if(offset < 0){
				 myGameBoard.getBomberMan().moveDown(-offset - (CONSTANTS.BOMBERMAN_HEIGHT/2));
			 }
		 }
		 myGameBoard.getBomberMan().moveLeft(myGameBoard.getBomberMan().getSpeed());
		// this.myGameBoard.getBomberMan().setLastDirection(1);
	 }
	 else if(keyE.getKeyCode() == KeyEvent.VK_DOWN && !(checkCollision(myGameBoard.getBomberMan(), 4)))
	 {
		
		 if(!centeredXAxis){
			 int offset = computeOffsetFromCenterXaxis(myGameBoard.getBomberMan());
			 if(offset > 0){
			 myGameBoard.getBomberMan().moveLeft(offset+(CONSTANTS.BOMBERMAN_WIDTH/2));}				 
		 if(offset < 0){
			 myGameBoard.getBomberMan().moveRight(-offset -(CONSTANTS.BOMBERMAN_WIDTH/2));
		 }}
		 
		 myGameBoard.getBomberMan().moveDown(myGameBoard.getBomberMan().getSpeed());
		 //this.myGameBoard.getBomberMan().setLastDirection(4);
	 }
	 else  if(keyE.getKeyCode() == KeyEvent.VK_UP && !(checkCollision(myGameBoard.getBomberMan(), 3)))
	 {
		 
		 if(!centeredXAxis){
			 int offset = computeOffsetFromCenterXaxis(myGameBoard.getBomberMan());
			 if(offset < 0){
				 myGameBoard.getBomberMan().moveRight(-offset -(CONSTANTS.BOMBERMAN_WIDTH/2));
			 }
			 if(offset > 0 ){
				 myGameBoard.getBomberMan().moveLeft(offset +(CONSTANTS.BOMBERMAN_WIDTH/2));
			 }
		}
		 myGameBoard.getBomberMan().moveUp(myGameBoard.getBomberMan().getSpeed());
		// this.myGameBoard.getBomberMan().setLastDirection(3);
	 }}
	 }
	 
 }
 /*
  * Returns the GameBoard field of the BomberManController class
  * */
 public GameBoard getGameBoard(){return this.myGameBoard;}
 /*
  * Checks if the Bomberman move to the right is going to cause a collision with a GameObject
  * */
 
public boolean checkCollisionRight(Character character, int direction){
int xPos = character.getPositionX();
int yPos =character.getPositionY();
System.out.println("xPos: "+ xPos);
System.out.println("yPos: "+ yPos);

int speed =character.getSpeed();
boolean collision = false;
int width = CONSTANTS.TILE_SIDE_SIZE;
if(direction == 2){
	
	if(!this.myGameBoard.getCell((yPos)/width,(xPos + speed + CONSTANTS.BOMBERMAN_WIDTH)/width).isEmpty()){
		collision = true;
		System.out.println("COLLISION 1");
		
	}
	if(!this.myGameBoard.getCell((yPos+ CONSTANTS.BOMBERMAN_HEIGHT)/width,(xPos + speed + CONSTANTS.BOMBERMAN_WIDTH)/width).isEmpty()){
		collision = true;
		System.out.println("COLLISION 2");	
	}
	
}
return collision;	
}
/*
 * Checks if the Bomberman move to the Left is going to cause a collision with a GameObject
 * */
public boolean checkCollisionLeft(Character character, int direction){
int xPos = character.getPositionX();
int yPos =character.getPositionY();
System.out.println("xPos: "+ xPos);
System.out.println("yPos: "+ yPos);

int speed =character.getSpeed();
boolean collision = false;
int width = CONSTANTS.TILE_SIDE_SIZE;
if(direction == 1){
	
	if(!this.myGameBoard.getCell((yPos)/width,(xPos - speed)/width).isEmpty()){
		collision = true;
		System.out.println("COLLISION 1");
		
	}
	if(!this.myGameBoard.getCell((yPos+ CONSTANTS.BOMBERMAN_HEIGHT)/width,(xPos - speed)/width).isEmpty()){
		collision = true;
		System.out.println("COLLISION 2");	
	}
	
}
return collision;	
}
/*
 * Checks if the Bomberman move to the North is going to cause a collision with a GameObject
 * */
public boolean checkCollisionUp(Character character, int direction){
int xPos = character.getPositionX();
int yPos =character.getPositionY();
System.out.println("xPos: "+ xPos);
System.out.println("yPos: "+ yPos);

int speed =character.getSpeed();
boolean collision = false;
int width = CONSTANTS.TILE_SIDE_SIZE;
if(direction == 3){
	
	if(!this.myGameBoard.getCell((yPos - speed)/width,(xPos)/width).isEmpty()){
		collision = true;
		System.out.println("COLLISION 1");
		
	}
	if(!this.myGameBoard.getCell((yPos- speed)/width,(xPos+ CONSTANTS.BOMBERMAN_WIDTH)/width).isEmpty()){
		collision = true;
		System.out.println("COLLISION 2");	
	}
	
}
return collision;	
}
/*
 * Checks if the Bomberman move to the South is going to cause a collision with a GameObject
 * */
public boolean checkCollisionDown(Character character, int direction){
int xPos = character.getPositionX();
int yPos =character.getPositionY();
System.out.println("xPos: "+ xPos);
System.out.println("yPos: "+ yPos);

int speed =character.getSpeed();
boolean collision = false;
int width = CONSTANTS.TILE_SIDE_SIZE;
if(direction == 4){
	
	if(!this.myGameBoard.getCell((yPos + speed + CONSTANTS.BOMBERMAN_WIDTH)/width,(xPos)/width).isEmpty()){
		collision = true;
		System.out.println("COLLISION 1");
		
	}
	if(!this.myGameBoard.getCell((yPos+ speed+ CONSTANTS.BOMBERMAN_WIDTH)/width,(xPos+ CONSTANTS.BOMBERMAN_WIDTH)/width).isEmpty()){
		collision = true;
		System.out.println("COLLISION 2");	
	}
	
}
return collision;	
}
/*
 * Checks if the Bomberman is either between 2 GameObjects placed to its right and left if direction = 1 or direction = 2.
 * Checks if the Bomberman is either between 2 GameObjects placed to its north and south if direction = 3 or direction = 4.
 * 
 * */
public boolean checkLeftAndRightUpDownTilesCollision(Character character, int direction){
int xPos = character.getPositionX();
int yPos =character.getPositionY();
boolean collision = false;
int heightBMB = CONSTANTS.BOMBERMAN_HEIGHT;
int widthBMB = CONSTANTS.BOMBERMAN_WIDTH;
int width = CONSTANTS.TILE_SIDE_SIZE;
if(direction == 1 || direction == 2){
boolean right = this.myGameBoard.getCell(((yPos)/width),1+(xPos)/width).isEmpty();
boolean left = this.myGameBoard.getCell(((yPos)/width),-1+(xPos)/width).isEmpty();
boolean leftA = this.myGameBoard.getCell(((yPos+heightBMB)/ width),-1+(xPos)/width).isEmpty();
boolean rightA = this.myGameBoard.getCell(((yPos+heightBMB)/ width),1+(xPos)/width).isEmpty();
if((!left &&! right) || (!leftA && !rightA)){collision = true;}
}

if(direction == 3 || direction == 4){
	boolean down = this.myGameBoard.getCell((1+(yPos)/width),( ((xPos)/width))).isEmpty();
	boolean up = this.myGameBoard.getCell((-1+(yPos)/width),(((xPos)/width))).isEmpty();
	boolean downA = this.myGameBoard.getCell((1+(yPos)/width),( ((xPos+ widthBMB)/width))).isEmpty();
	boolean upA = this.myGameBoard.getCell((-1+(yPos)/width),( ((xPos+ widthBMB)/width))).isEmpty();
if((!up && !down) || (!downA && !upA)){
	collision = true;}}
return collision;
}
/*Checks if the character is centered with the X axis = 0;
 * 
 */
public boolean checkIfCharacterIsCenteredXAxis(Character character){

  boolean verify = character.getPositionX() % CONSTANTS.TILE_SIDE_SIZE == ((CONSTANTS.TILE_SIDE_SIZE/2)-(CONSTANTS.BOMBERMAN_WIDTH/2));
  return verify;	
}
/*Checks if the character is centered with the y axis = 0;
 * 
 */
public boolean checkIfCharacterIsCenteredYAxis(Character character){

boolean verify = character.getPositionY() % CONSTANTS.TILE_SIDE_SIZE == ((CONSTANTS.TILE_SIDE_SIZE/2)-(CONSTANTS.BOMBERMAN_HEIGHT/2));
return verify;	
}
/*returns the offset of the bomberman from the x axis.
 * 
 */
public int computeOffsetFromCenterXaxis(Character character){
int offset = (character.getPositionX()%CONSTANTS.TILE_SIDE_SIZE)-(CONSTANTS.TILE_SIDE_SIZE/2);
return offset;	
}
/*returns the offset of the bomberman from the y axis.
 * 
 */
public int computeOffsetFromCenterYaxis(Character character){
int offset =(character.getPositionY()%CONSTANTS.TILE_SIDE_SIZE)-(CONSTANTS.TILE_SIDE_SIZE/2);
return offset;	
}
/* verifies if the current bomberman cell is shared with a bomb.
 * 
 */
public boolean isCellSharedWithBOMB(Character character){
int xLeft =character.getPositionX();

int yLeft = character.getPositionY();
int widthBMB = CONSTANTS.BOMBERMAN_WIDTH;
int size = CONSTANTS.TILE_SIDE_SIZE;
if(this.myGameBoard.getCell(yLeft/size, xLeft/size).getHasABomb()){
	System.out.println("IS SHARED WITH A BOMB AND HAS A BOMB");
	return true;}
if(this.myGameBoard.getCell((yLeft)/size, (xLeft+ widthBMB)/size).getHasABomb()){
	System.out.println("IS SHARED WITH A BOMB AND HAS A BOMB");
	return true;}
if(this.myGameBoard.getCell((yLeft+widthBMB)/size, (xLeft)/size).getHasABomb()){
	System.out.println("IS SHARED WITH A BOMB AND HAS A BOMB");
	return true;}
if(this.myGameBoard.getCell((yLeft + widthBMB)/size, (xLeft+ widthBMB)/size).getHasABomb()){
	System.out.println("IS SHARED WITH A BOMB AND HAS A BOMB");
	return true;}
System.out.println("IS not SHARED WITH A BOMB AND HAS NO BOMB");
return false;
}
/*  when the bomberman shares a cell with a bomb, it means that it recently dropped it, so this functions manages
 * the bomberman moves inside a cell where there is also a bomb recently dropped.
 * 
 */
public void moveInsideBomb(KeyEvent e, int directionMove, int directionForbidden){
	 boolean centeredXAxis = checkIfCharacterIsCenteredXAxis(myGameBoard.getBomberMan());
	 boolean centeredYAxis = checkIfCharacterIsCenteredYAxis(myGameBoard.getBomberMan());
	System.out.println("MOVING INSIDE A BOMB");
	 myGameBoard.getBomberMan().setForbiddenDirection(directionForbidden);
	 int xPos =  myGameBoard.getBomberMan().getPositionX();
	 int yPos =  myGameBoard.getBomberMan().getPositionY();
	 int widthBMB =  CONSTANTS.BOMBERMAN_WIDTH;
	 int heightBMB =  CONSTANTS.BOMBERMAN_HEIGHT;
	 int width = CONSTANTS.TILE_SIDE_SIZE;
	 if(directionMove == 2){
		 
		 	 
		 
		 int displacement = myGameBoard.getBomberMan().getSpeed();
		 boolean right = this.myGameBoard.getCell(((yPos)/width),1+(xPos)/width).isEmpty();
		 boolean left = this.myGameBoard.getCell(((yPos)/width),-1+(xPos)/width).isEmpty();
		 boolean leftA = this.myGameBoard.getCell(((yPos+heightBMB)/ width),-1+(xPos)/width).isEmpty();
		 boolean rightA = this.myGameBoard.getCell(((yPos+heightBMB)/ width),1+(xPos)/width).isEmpty();
		 boolean sidesFree = (!left &&! right) || (!leftA && !rightA);
		 if(this.myGameBoard.getCell( (yPos)/width,(xPos+widthBMB)/width).isEmpty()|| !sidesFree){
			 if(!centeredYAxis){
				 int offset = computeOffsetFromCenterYaxis(myGameBoard.getBomberMan());
				 if(offset > 0){
					 myGameBoard.getBomberMan().moveUp(offset + (CONSTANTS.BOMBERMAN_HEIGHT/2));
				 }
				 if(offset < 0){
					 myGameBoard.getBomberMan().moveDown(-offset - (CONSTANTS.BOMBERMAN_HEIGHT/2));
				 }
			 }
			 myGameBoard.getBomberMan().moveRight(displacement);
			  }
	 }
	 if(directionMove == 1 ){
		
		 int displacement = myGameBoard.getBomberMan().getSpeed();
		 boolean right = this.myGameBoard.getCell(((yPos)/width),1+(xPos)/width).isEmpty();
		 boolean left = this.myGameBoard.getCell(((yPos)/width),-1+(xPos)/width).isEmpty();
		 boolean leftA = this.myGameBoard.getCell(((yPos+heightBMB)/ width),-1+(xPos)/width).isEmpty();
		 boolean rightA = this.myGameBoard.getCell(((yPos+heightBMB)/ width),1+(xPos)/width).isEmpty();
		 boolean sidesFree = (!left &&! right) || (!leftA && !rightA);
		  	
		 if(this.myGameBoard.getCell( (yPos)/width,(xPos)/width).isEmpty()|| !sidesFree){
			 if(!centeredYAxis){
				 int offset = computeOffsetFromCenterYaxis(myGameBoard.getBomberMan());
				 if(offset > 0){
					 myGameBoard.getBomberMan().moveUp(offset + (CONSTANTS.BOMBERMAN_HEIGHT/2));
				 }
				 if(offset < 0){
					 myGameBoard.getBomberMan().moveDown(-offset - (CONSTANTS.BOMBERMAN_HEIGHT/2));
				 }
			 }
			 myGameBoard.getBomberMan().moveLeft(displacement);
			  }
		 
	 }
	 if(directionMove == 4 ){

		
		 int displacement = myGameBoard.getBomberMan().getSpeed();
		 boolean down = this.myGameBoard.getCell((1+(yPos)/width),( ((xPos)/width))).isEmpty();
			boolean up = this.myGameBoard.getCell((-1+(yPos)/width),(((xPos)/width))).isEmpty();
			boolean downA = this.myGameBoard.getCell((1+(yPos)/width),( ((xPos+ widthBMB)/width))).isEmpty();
			boolean upA = this.myGameBoard.getCell((-1+(yPos)/width),( ((xPos+ widthBMB)/width))).isEmpty();
			boolean sidesFree = (!up &&! down) || (!upA && !downA);
			  	
		   if(this.myGameBoard.getCell( (yPos+widthBMB)/width,(xPos)/width).isEmpty()|| !sidesFree){
			   if(!centeredXAxis){
					 int offset = computeOffsetFromCenterXaxis(myGameBoard.getBomberMan());
					 if(offset > 0){
					 myGameBoard.getBomberMan().moveLeft(offset+(CONSTANTS.BOMBERMAN_WIDTH/2));}				 
				 if(offset < 0){
					 myGameBoard.getBomberMan().moveRight(-offset -(CONSTANTS.BOMBERMAN_WIDTH/2));
				 }} 
			   myGameBoard.getBomberMan().moveDown(displacement);
				  }
	 }
	 if(directionMove == 3 ){
		 
		 int displacement = myGameBoard.getBomberMan().getSpeed();
		 boolean down = this.myGameBoard.getCell((1+(yPos)/width),( ((xPos)/width))).isEmpty();
			boolean up = this.myGameBoard.getCell((-1+(yPos)/width),(((xPos)/width))).isEmpty();
			boolean downA = this.myGameBoard.getCell((1+(yPos)/width),( ((xPos+ widthBMB)/width))).isEmpty();
			boolean upA = this.myGameBoard.getCell((-1+(yPos)/width),( ((xPos+ widthBMB)/width))).isEmpty();
			boolean sidesFree = (!up &&! down) || (!upA && !downA);
			  	
		   if(this.myGameBoard.getCell( (yPos)/width,(xPos)/width).isEmpty()|| !sidesFree){
			   if(!centeredXAxis){
					 int offset = computeOffsetFromCenterXaxis(myGameBoard.getBomberMan());
					 if(offset < 0){
						 myGameBoard.getBomberMan().moveRight(-offset -(CONSTANTS.BOMBERMAN_WIDTH/2));
					 }
					 if(offset > 0 ){
						 myGameBoard.getBomberMan().moveLeft(offset +(CONSTANTS.BOMBERMAN_WIDTH/2));
					 }
				} 
			   myGameBoard.getBomberMan().moveUp(displacement);
				  }
	 }
		
}
/*
 * Drops a bomb in the gameboard
 */
public void dropBomb(){
	 
	 int posXBomb = this.myGameBoard.getBomberMan().getPositionX();
	 int posYBomb = this.myGameBoard.getBomberMan().getPositionY();
	 int celX = posXBomb / CONSTANTS.TILE_SIDE_SIZE ;
	 int celY = posYBomb / CONSTANTS.TILE_SIDE_SIZE ;
	 if(this.myGameBoard.getCell(celY,celX).isEmpty()){
	 this.myGameBoard.addBomb(celX, celY, new Bomb(celX*CONSTANTS.TILE_SIDE_SIZE , celY*CONSTANTS.TILE_SIDE_SIZE, CONSTANTS.BOMB_TIMER, CONSTANTS.BOMB_RANGE1, false));
	 this.myGameBoard.getCell(celY, celX).setHasABomb(true);
	 //this.myGameBoard.getCell(celY,celX).searchBomb();
	 }
}
/*
 * gets a forbidden direction for the bomberman
 */
public int getDirectionForbidden(KeyEvent key){
	if(KeyEvent.VK_LEFT == key.getKeyCode()){return 2;}
	if(KeyEvent.VK_RIGHT == key.getKeyCode()){return 1;}
	if(KeyEvent.VK_UP == key.getKeyCode()){return 4;}
	if(KeyEvent.VK_DOWN == key.getKeyCode()){return 3;}
	return -1;
}
/*
 * gets a direction from the KeyEvent Input
 */
public int getDirection(KeyEvent key){
	if(KeyEvent.VK_LEFT == key.getKeyCode()){return 1;}
	if(KeyEvent.VK_RIGHT == key.getKeyCode()){return 2;}
	if(KeyEvent.VK_UP == key.getKeyCode()){return 3;}
	if(KeyEvent.VK_DOWN == key.getKeyCode()){return 4;}
	return -1;
}
/*
 * Detonate a bomb which has a detonator
 */
/*public void detonateBomb(){
	
	this.myGameBoard
	
}*/
}
