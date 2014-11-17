/* 
 * File: BombermanController.java
 * -----------------------
 * This Class represents the control of the bomberman character on screen. Mainly for dropping and detonating bombs 
 * and catching power ups.
 */

package bomberMan.gamePlay.Controller;
import bomberMan.gamePlay.Model.*;

import java.awt.event.KeyEvent;
import java.util.Calendar;

import bomberMan.gamePlay.Model.Character;
public class BomberManController extends CharacterController {

	Calendar timer;
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
  * Returns the myGameBoard field of the BomberManController class
  * */
 public GameBoard getmyGameBoard(){return this.myGameBoard;}
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
	
	if(!this.myGameBoard.getCell((yPos)/width,(xPos + speed + CONSTANTS.BOMBERMAN_WIDTH)/width).isEmptyPowerUpException()){
		collision = true;
		System.out.println("COLLISION 1");
		
	}
	if(!this.myGameBoard.getCell((yPos+ CONSTANTS.BOMBERMAN_HEIGHT)/width,(xPos + speed + CONSTANTS.BOMBERMAN_WIDTH)/width).isEmptyPowerUpException()){
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
	
	if(!this.myGameBoard.getCell((yPos)/width,(xPos - speed)/width).isEmptyPowerUpException()){
		collision = true;
		System.out.println("COLLISION 1");
		
	}
	if(!this.myGameBoard.getCell((yPos+ CONSTANTS.BOMBERMAN_HEIGHT)/width,(xPos - speed)/width).isEmptyPowerUpException()){
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
	
	if(!this.myGameBoard.getCell((yPos - speed)/width,(xPos)/width).isEmptyPowerUpException()){
		collision = true;
		System.out.println("COLLISION 1");
		
	}
	if(!this.myGameBoard.getCell((yPos- speed)/width,(xPos+ CONSTANTS.BOMBERMAN_WIDTH)/width).isEmptyPowerUpException()){
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
	
	if(!this.myGameBoard.getCell((yPos + speed + CONSTANTS.BOMBERMAN_WIDTH)/width,(xPos)/width).isEmptyPowerUpException()){
		collision = true;
		System.out.println("COLLISION 1");
		
	}
	if(!this.myGameBoard.getCell((yPos+ speed+ CONSTANTS.BOMBERMAN_WIDTH)/width,(xPos+ CONSTANTS.BOMBERMAN_WIDTH)/width).isEmptyPowerUpException()){
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
if(this.myGameBoard.getCell(yLeft/size, xLeft/size).getHasABomb() || this.myGameBoard.getCell(yLeft/size, xLeft/size).getHasADetonateBomb()){
	System.out.println("IS SHARED WITH A BOMB AND HAS A BOMB");
	return true;}
if(this.myGameBoard.getCell((yLeft)/size, (xLeft+ widthBMB)/size).getHasABomb() || this.myGameBoard.getCell((yLeft)/size, (xLeft+ widthBMB)/size).getHasADetonateBomb()){
	System.out.println("IS SHARED WITH A BOMB AND HAS A BOMB");
	return true;}
if(this.myGameBoard.getCell((yLeft+widthBMB)/size, (xLeft)/size).getHasABomb()|| this.myGameBoard.getCell((yLeft+widthBMB)/size, (xLeft)/size).getHasADetonateBomb()){
	System.out.println("IS SHARED WITH A BOMB AND HAS A BOMB");
	return true;}
if(this.myGameBoard.getCell((yLeft + widthBMB)/size, (xLeft+ widthBMB)/size).getHasABomb() || this.myGameBoard.getCell((yLeft + widthBMB)/size, (xLeft+ widthBMB)/size).getHasADetonateBomb()){
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
		 boolean right = this.myGameBoard.getCell(((yPos)/width),1+(xPos)/width).isEmptyPowerUpException();
		 boolean left = this.myGameBoard.getCell(((yPos)/width),-1+(xPos)/width).isEmptyPowerUpException();
		 boolean leftA = this.myGameBoard.getCell(((yPos+heightBMB)/ width),-1+(xPos)/width).isEmptyPowerUpException();
		 boolean rightA = this.myGameBoard.getCell(((yPos+heightBMB)/ width),1+(xPos)/width).isEmptyPowerUpException();
		 boolean sidesFree = (!left &&! right) || (!leftA && !rightA);
		 if(this.myGameBoard.getCell( (yPos)/width,(xPos+widthBMB)/width).isEmptyPowerUpException()|| !sidesFree){
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
		 boolean right = this.myGameBoard.getCell(((yPos)/width),1+(xPos)/width).isEmptyPowerUpException();
		 boolean left = this.myGameBoard.getCell(((yPos)/width),-1+(xPos)/width).isEmptyPowerUpException();
		 boolean leftA = this.myGameBoard.getCell(((yPos+heightBMB)/ width),-1+(xPos)/width).isEmptyPowerUpException();
		 boolean rightA = this.myGameBoard.getCell(((yPos+heightBMB)/ width),1+(xPos)/width).isEmptyPowerUpException();
		 boolean sidesFree = (!left &&! right) || (!leftA && !rightA);
		  	
		 if(this.myGameBoard.getCell( (yPos)/width,(xPos)/width).isEmptyPowerUpException()|| !sidesFree){
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
		 boolean down = this.myGameBoard.getCell((1+(yPos)/width),( ((xPos)/width))).isEmptyPowerUpException();
			boolean up = this.myGameBoard.getCell((-1+(yPos)/width),(((xPos)/width))).isEmptyPowerUpException();
			boolean downA = this.myGameBoard.getCell((1+(yPos)/width),( ((xPos+ widthBMB)/width))).isEmptyPowerUpException();
			boolean upA = this.myGameBoard.getCell((-1+(yPos)/width),( ((xPos+ widthBMB)/width))).isEmptyPowerUpException();
			boolean sidesFree = (!up &&! down) || (!upA && !downA);
			  	
		   if(this.myGameBoard.getCell( (yPos+widthBMB)/width,(xPos)/width).isEmptyPowerUpException()|| !sidesFree){
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
		 boolean down = this.myGameBoard.getCell((1+(yPos)/width),( ((xPos)/width))).isEmptyPowerUpException();
			boolean up = this.myGameBoard.getCell((-1+(yPos)/width),(((xPos)/width))).isEmptyPowerUpException();
			boolean downA = this.myGameBoard.getCell((1+(yPos)/width),( ((xPos+ widthBMB)/width))).isEmptyPowerUpException();
			boolean upA = this.myGameBoard.getCell((-1+(yPos)/width),( ((xPos+ widthBMB)/width))).isEmptyPowerUpException();
			boolean sidesFree = (!up &&! down) || (!upA && !downA);
			  	
		   if(this.myGameBoard.getCell( (yPos)/width,(xPos)/width).isEmptyPowerUpException()|| !sidesFree){
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
 * Drops a  bomb that is not going to be detonated
 */
public void dropBomb(){
	 
	 int posXBomb = this.myGameBoard.getBomberMan().getPositionX();
	 int posYBomb = this.myGameBoard.getBomberMan().getPositionY();
	 int celX = posXBomb / CONSTANTS.TILE_SIDE_SIZE ;
	 int celY = posYBomb / CONSTANTS.TILE_SIDE_SIZE ;
	 if(this.myGameBoard.getCell(celY,celX).isEmpty()){
	 this.myGameBoard.addBomb(celX, celY, new Bomb(celX*CONSTANTS.TILE_SIDE_SIZE , celY*CONSTANTS.TILE_SIDE_SIZE, CONSTANTS.BOMB_TIMER, CONSTANTS.BOMB_RANGE1, false, "Bomb"));
	 this.myGameBoard.getBomberMan().addBomb(new Bomb(celX*CONSTANTS.TILE_SIDE_SIZE , celY*CONSTANTS.TILE_SIDE_SIZE, CONSTANTS.BOMB_TIMER, CONSTANTS.BOMB_RANGE1, false, "Bomb"));
	 this.myGameBoard.getCell(celY, celX).setHasABomb(true);
	 
	 }
}
/*
 * Drops a  bomb that is  going to be detonated
 */
public void dropBombDetonator(){
	 
	 int posXBomb = this.myGameBoard.getBomberMan().getPositionX();
	 int posYBomb = this.myGameBoard.getBomberMan().getPositionY();
	 int celX = posXBomb / CONSTANTS.TILE_SIDE_SIZE ;
	 int celY = posYBomb / CONSTANTS.TILE_SIDE_SIZE ;
	 if(this.myGameBoard.getCell(celY,celX).isEmpty()){
	 this.myGameBoard.addBomb(celX, celY, new Bomb(celX*CONSTANTS.TILE_SIDE_SIZE , celY*CONSTANTS.TILE_SIDE_SIZE, CONSTANTS.BOMB_TIMER, CONSTANTS.BOMB_RANGE1, true, "BombDetonator"));
	 this.myGameBoard.getBomberMan().addBomb(new Bomb(celX*CONSTANTS.TILE_SIDE_SIZE , celY*CONSTANTS.TILE_SIDE_SIZE, CONSTANTS.BOMB_TIMER, CONSTANTS.BOMB_RANGE1, true, "BombDetonator"));
	 this.myGameBoard.getCell(celY, celX).setHasADetonatorBomb(true);
	 
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

/*This functions kills the bomberman if for (i,j) in the gameboard, the Bomberman is in the bombs range given by the cells at (i+BombRange, j), (i-Bombrange, j)
 * (i, j+BombRange), (i, j-Bombrange)
 * 
 */
public void killBomberman1(int i, int j){
	boolean up = true;
	boolean down = true;
	boolean right = true;
	boolean left = true;
	if(this.myGameBoard.getBomberMan().getICell() == i && this.myGameBoard.getBomberMan().getJCell() == j){
	System.out.println("KILLING BOMBERMAN");
	this.myGameBoard.getBomberMan().die();
	}
	
	int counter1 = 1;
	while(counter1 <= CONSTANTS.BOMB_RANGE1){
		// checking the right range of the bomb; if bomberman is present and there is no concrete walls at bomb range
		if(j +counter1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right == true ){
			if(j +counter1  < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right == true){
				if(this.myGameBoard.getCell(i, j+counter1).searcHasAConcreteWall()== true){right = false;}
			}}
			if(right == true && j +counter1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES  && this.myGameBoard.getCell(i, j+counter1).searcHasAConcreteWall() == false){
				if(this.myGameBoard.getBomberMan().getICell() == i && this.myGameBoard.getBomberMan().getJCell() == j+ counter1){
					System.out.println("KILLING BOMBERMAN");
					this.myGameBoard.getBomberMan().die();}
				if(this.myGameBoard.getBomberMan().getICellBottomBomberman() == i && this.myGameBoard.getBomberMan().getJCell() == j+ counter1){
					System.out.println("KILLING BOMBERMAN");
					this.myGameBoard.getBomberMan().die();}
				
		}
		
		if(j +counter1 + 1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right == true){
			if(this.myGameBoard.getCell(i, j+counter1+1).searcHasAConcreteWall()== true){right = false;}}
		// checking the left range of the bomb; if bomberman is present and there is no concrete walls at bomb range
		if(j -counter1 >=0 && left == true ){
			if(j -counter1  >=0 && left == true){
				if(this.myGameBoard.getCell(i, j-counter1).searcHasAConcreteWall()== true){left = false;}
			}}
			if(left == true && j -counter1 >=0  && this.myGameBoard.getCell(i, j-counter1).searcHasAConcreteWall() == false){
				if(this.myGameBoard.getBomberMan().getICell() == i && this.myGameBoard.getBomberMan().getJCell() == j- counter1){
					System.out.println("KILLING BOMBERMAN");
					this.myGameBoard.getBomberMan().die();}
				if(this.myGameBoard.getBomberMan().getICellBottomBomberman() == i && this.myGameBoard.getBomberMan().getJCell() == j- counter1){
					System.out.println("KILLING BOMBERMAN");
					this.myGameBoard.getBomberMan().die();}
				
		}
		
		if(j -counter1 - 1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && left == true){
			if(this.myGameBoard.getCell(i, j-counter1-1).searcHasAConcreteWall()== true){left = false;}}
		// checking the south range of the bomb;if bomberman is present and there is no concrete walls at range
		if(i +counter1 < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down == true ){
			if(i +counter1  < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down == true){
				if(this.myGameBoard.getCell(i + counter1, j).searcHasAConcreteWall()== true){down = false;}
			}}
			if(down == true && i +counter1 < CONSTANTS.NUMBER_OF_VERTICAL_TILES  && this.myGameBoard.getCell(i+counter1, j).searcHasAConcreteWall() == false){
				if(this.myGameBoard.getBomberMan().getICell() == i +counter1 && this.myGameBoard.getBomberMan().getJCell() == j){
					System.out.println("KILLING BOMBERMAN");
					this.myGameBoard.getBomberMan().die();}
				if(this.myGameBoard.getBomberMan().getICell() == i +counter1 && this.myGameBoard.getBomberMan().getJCellRightMostBomberman() == j){
					System.out.println("KILLING BOMBERMAN");
					this.myGameBoard.getBomberMan().die();}
				
		}
		
		if(i +counter1 + 1 < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down == true){
			if(this.myGameBoard.getCell(i + counter1 + 1, j).searcHasAConcreteWall()== true){down = false;}}
		
		// checking the north range of the bomb;if bomberman is present and there is no concrete walls at range
		
		if(i -counter1 >= 0 && up == true ){
			if(i - counter1  >= 0 && up == true){
				if(this.myGameBoard.getCell(i - counter1, j).searcHasAConcreteWall()== true){up = false;}
			}}
			if(up == true && i -counter1 >= 0  && this.myGameBoard.getCell(i-counter1, j).searcHasAConcreteWall() == false){
				if(this.myGameBoard.getBomberMan().getICell() == i-counter1 && this.myGameBoard.getBomberMan().getJCell() == j){
					System.out.println("KILLING BOMBERMAN");
					this.myGameBoard.getBomberMan().die();}
				if(this.myGameBoard.getBomberMan().getICell() == i -counter1 && this.myGameBoard.getBomberMan().getJCellRightMostBomberman() == j){
					System.out.println("KILLING BOMBERMAN");
					this.myGameBoard.getBomberMan().die();}
				
		}
		
		if(i -counter1 - 1 >= 0 && up == true){
			if(this.myGameBoard.getCell(i - counter1 - 1, j).searcHasAConcreteWall()== true){up = false;}}
		
			
		
		counter1++;
	}
}
/*
 * Destroys the bricks in the bombs range
 * 	
 */
	public void destroyBricks(int i, int j){
		boolean up = true;
		boolean down = true;
		boolean right = true;
		boolean left = true;
		
		
		int counter1 = 1;
		while(counter1 <= CONSTANTS.BOMB_RANGE1){
			// checking the right range of the bomb; if a brick is present and there is no concrete walls at bomb range
			if(j +counter1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right == true ){
				if(j +counter1  < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right == true){
					if(this.myGameBoard.getCell(i, j+counter1).searcHasAConcreteWall()== true){right = false;}
				}}
				if(right == true && j +counter1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES  && this.myGameBoard.getCell(i, j+counter1).searcHasAConcreteWall() == false){
					if(this.myGameBoard.getCell(i, j+ counter1).searcHasABrickWall() == true){
						System.out.println("DESTROYING BRICK");
						this.myGameBoard.getCell(i, j+ counter1).deleteElement("Brick");}			
					
		         	}
			
			if(j +counter1 + 1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right == true){
				if(this.myGameBoard.getCell(i, j+counter1+1).searcHasAConcreteWall()== true){right = false;}}
			// checking the left range of the bomb; if a brick is present and there is no concrete walls at bomb range
			if(j -counter1 >=0 && left == true ){
				if(j -counter1  >=0 && left == true){
					if(this.myGameBoard.getCell(i, j-counter1).searcHasAConcreteWall()== true){left = false;}
				}}
				if(left == true && j -counter1 >=0  && this.myGameBoard.getCell(i, j-counter1).searcHasAConcreteWall() == false){
					if(this.myGameBoard.getCell(i, j- counter1).searcHasABrickWall() == true){
						System.out.println("DESTROYING BRICK");
						this.myGameBoard.getCell(i, j-counter1).deleteElement("Brick");}
									
			}
			
			if(j -counter1 - 1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && left == true){
				if(this.myGameBoard.getCell(i, j-counter1-1).searcHasAConcreteWall()== true){left = false;}}
			// checking the south range of the bomb;if a brick is present and there is no concrete walls at range
			if(i +counter1 < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down == true ){
				if(i +counter1  < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down == true){
					if(this.myGameBoard.getCell(i + counter1, j).searcHasAConcreteWall()== true){down = false;}
				}}
				if(down == true && i +counter1 < CONSTANTS.NUMBER_OF_VERTICAL_TILES  && this.myGameBoard.getCell(i+counter1, j).searcHasAConcreteWall() == false){
					if(this.myGameBoard.getCell(i +counter1,j).searcHasABrickWall() == true){
						System.out.println("DESTROYING BRICK");
						this.myGameBoard.getCell(i+counter1,j).deleteElement("Brick");}
					
					
			}
			
			if(i +counter1 + 1 < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down == true){
				if(this.myGameBoard.getCell(i + counter1 + 1, j).searcHasAConcreteWall()== true){down = false;}}
			
			// checking the north range of the bomb;if a brick is present and there is no concrete walls at range
			
			if(i -counter1 >= 0 && up == true ){
				if(i - counter1  >= 0 && up == true){
					if(this.myGameBoard.getCell(i - counter1, j).searcHasAConcreteWall()== true){up = false;}
				}}
				if(up == true && i -counter1 >= 0  && this.myGameBoard.getCell(i-counter1, j).searcHasAConcreteWall() == false){
					if(this.myGameBoard.getCell(i-counter1,j).searcHasABrickWall() == true){
						System.out.println("DESTROYING BRICK");
						this.myGameBoard.getCell(i-counter1,j).deleteElement("Brick");}
					
					
			}
			
			if(i -counter1 - 1 >= 0 && up == true){
				if(this.myGameBoard.getCell(i - counter1 - 1, j).searcHasAConcreteWall()== true){up = false;}}
			
				
			
			counter1++;
		}
}
	/*
	 * Detonate a bomb which has a detonator
	 */
	public void detonateOldestBomb(){
		
		for(int i=0;i<CONSTANTS.NUMBER_OF_VERTICAL_TILES;i++)
		 {
			 for(int j=0;j<CONSTANTS.NUMBER_OF_HORIZONTAL_TILES;j++)
			 {
				 if(this.myGameBoard.getCell(i, j).getHasADetonateBomb()){
					
					 
					    System.out.println("EXPLODE");
						System.out.println(i+ " " +j);
						this.myGameBoard.getCell(i, j).setFlameImages();
						int counter1 = 1;
						boolean right = true;
						boolean left = true;
						boolean down = true;
						boolean up = true;
						while(counter1 <= CONSTANTS.BOMB_RANGE1){
							if(j +counter1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right == true ){
								if(j +counter1  < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right == true){
									if(this.myGameBoard.getCell(i, j+counter1).isEmpty()== false){right = false;}
								}}
								if(right == true && j +counter1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES  && this.myGameBoard.getCell(i, j+counter1).isEmpty()){
							this.myGameBoard.getCell(i, j+counter1).setFlameImages();
							}
							
							if(j +counter1 + 1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right == true){
								if(this.myGameBoard.getCell(i, j+counter1+1).isEmpty()== false){right = false;}}
								
							
							if(j -counter1 >=0 && left == true ){
								if(j -counter1  >=0 && left== true){
									if(this.myGameBoard.getCell(i, j-counter1).isEmpty()== false){left = false;}}
								if(left == true && j -counter1 >= 0 && this.myGameBoard.getCell(i, j-counter1).isEmpty()){
								this.myGameBoard.getCell(i, j-counter1).setFlameImages();}
								}
							    
								if(j -counter1 - 1 >=0 && left == true){
									if(this.myGameBoard.getCell(i, j-counter1-1).isEmpty()== false){left = false;}
							
								}
						   if(i -counter1 >=0 && up == true ){
									if(i -counter1  >=0 && up== true){
										if(this.myGameBoard.getCell(i-counter1, j).isEmpty()== false){up = false;}}
									if(up == true && i -counter1 >= 0 && this.myGameBoard.getCell(i-counter1, j).isEmpty()){
									this.myGameBoard.getCell(i-counter1, j).setFlameImages();}
									}
								    
									if(i -counter1 - 1 >=0 && left == true){
										if(this.myGameBoard.getCell(i-counter1-1, j).isEmpty()== false){up = false;}
								
									}
						 if(i +counter1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && down == true ){
										if(i +counter1  < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down== true){
											if(this.myGameBoard.getCell(i+counter1, j).isEmpty()== false){down = false;}
										}}
										if(down == true && i +counter1 < CONSTANTS.NUMBER_OF_VERTICAL_TILES  && this.myGameBoard.getCell(i+counter1, j).isEmpty()){
									this.myGameBoard.getCell(i+counter1, j).setFlameImages();}
									
									if(i+counter1 + 1 < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down == true){
										if(this.myGameBoard.getCell(i+counter1 +1, j).isEmpty()== false){down = false;}}
										
							counter1++;
						}
						this.myGameBoard.getCell(i, j).deleteElement("BombDetonator");
						this.killBomberman1(i, j);
						this.destroyBricks(i, j);
						this.timer = Calendar.getInstance();
						long start =this. timer.getTimeInMillis();
						long finish = this.timer.getTimeInMillis() + 10000000;
						while(start <= finish){
							start +=10;
							System.out.println("WAITING TO DELETE FLAMES OF OLDEST BOMB");
						}
							int counter2 = 1;
							boolean right2 = true;
							boolean left2 = true;
							boolean down2 = true;
							boolean up2 = true;
							while(counter2 <= CONSTANTS.BOMB_RANGE1){
								if(j +counter2 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right2 == true ){
									if(j +counter2  < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right2 == true){
										if(this.myGameBoard.getCell(i, j+counter2).hasAFlame()== false){right2 = false;}
									}}
									if(right2 == true && j +counter2 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES  && this.myGameBoard.getCell(i, j+counter2).hasAFlame()){
								this.myGameBoard.getCell(i, j+counter2).removeFlames();
								
									}
								
								if(j +counter2 + 1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right2 == true){
									if(this.myGameBoard.getCell(i, j+counter2+1).hasAFlame()== false){right2 = false;}}
									
								
								if(j -counter2 >=0 && left2 == true ){
									if(j -counter2  >=0 && left2== true){
										if(this.myGameBoard.getCell(i, j-counter2).hasAFlame()== false){left2 = false;}}
									if(left2 == true && j -counter2 >= 0 && this.myGameBoard.getCell(i, j-counter2).hasAFlame()){
									this.myGameBoard.getCell(i, j-counter2).removeFlames();}
									}
								    
									if(j -counter2 - 1 >=0 && left2 == true){
										if(this.myGameBoard.getCell(i, j-counter2-1).hasAFlame()== false){left2 = false;}
								
									}
							   if(i -counter2 >=0 && up2 == true ){
										if(i -counter2  >=0 && up2== true){
											if(this.myGameBoard.getCell(i-counter2, j).hasAFlame()== false){up2 = false;}}
										if(up2 == true && i -counter2 >= 0 && this.myGameBoard.getCell(i-counter2, j).hasAFlame()){
										this.myGameBoard.getCell(i-counter2, j).removeFlames();}
										}
									    
										if(i -counter2 - 1 >=0 && up2 == true){
											if(this.myGameBoard.getCell(i-counter2-1, j).hasAFlame()== false){up2 = false;}
									
										}
							 if(i +counter2 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && down2 == true ){
											if(i +counter2  < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down2== true){
												if(this.myGameBoard.getCell(i+counter2, j).hasAFlame()== false){down2 = false;}
											}}
											if(down2 == true && i +counter2 < CONSTANTS.NUMBER_OF_VERTICAL_TILES  && this.myGameBoard.getCell(i+counter2, j).hasAFlame()){
										this.myGameBoard.getCell(i+counter2, j).removeFlames();}
										
										if(i+counter2 + 1 < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down2 == true){
											if(this.myGameBoard.getCell(i+counter2 +1, j).hasAFlame()== false){down2 = false;}}
											
								counter2++;
							}
						
						  if(this.myGameBoard.getCell(i, j).hasAFlame()){
							this.myGameBoard.getCell(i, j).removeFlames();}
						 
						   
							 
						
						return;
					 
			 }
				
		 }
	}
		
	}
}
