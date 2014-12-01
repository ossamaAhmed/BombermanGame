/* 
 * File: BombermanController.java
 * -----------------------
 * This Class represents the control of the bomberman character on screen. Mainly for dropping and detonating bombs 
 * and catching power ups.
 */
/**
 * This class contains various methods for manipulating the bomberman movement and dropping normal bombs that are not going
 * to be detonated as well as for allowing the bomberman to pick up power ups. 
 * 
 * The documentation for the methods contained in this class includes
 * briefs descriptions of the implementations. Such descriptions
 * should be regarded as implementation notes, rather than parts of specifications
 * @author Andres Felipe Rincon Gamboa
 * 
 * 
 */
package bomberMan.gamePlay.Controller;
import bomberMan.gamePlay.Model.*;

import java.awt.event.KeyEvent;
import java.util.Calendar;

import bomberMan.gamePlay.Model.Character;
public class BomberManController extends CharacterController {

	Calendar timer;
	
	
/**
 * Constructs a BomberManController objects given a Gameboar object which represents a given map for 
 * the gameplay.	
 * @param myGameBoard the gameboard or map that contains the static objects as well as the enemies that are going to be displayed during a game session
 * @return N/A.
 */
public BomberManController(GameBoard myGameBoard){

super(myGameBoard);
}
/**
 * This functions checks if the bomberman next moves are going to cause a collision with other GameObjects.
 * 
 * 
 * @param character the object character represents a character on the game, like an enemy or the bomberman.
 * @param direction an integer that represents the direction that the character object is following
 * @return a boolean, tru if the character has a collision for the given direction, false otherwise.
 */
public boolean checkCollision(Character myCharacter, int direction)
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
 *
 *@param keyE an integer that represents the key typed by the user.
 *@return void
 */
 public void move(int keyE) 	 
 {
	 this.pickPowerUp(myGameBoard.getBomberMan().getPositionY()/CONSTANTS.TILE_SIDE_SIZE, myGameBoard.getBomberMan().getPositionX()/CONSTANTS.TILE_SIDE_SIZE);
	 if(this.myGameBoard.getBomberMan().getIsAlive()){
	 if(this.isCellSharedWithBOMB(this.myGameBoard.getBomberMan())){
		 this.moveInsideBomb(this.getDirection(keyE));
	 }
	 else if(!this.isCellSharedWithBOMB(this.myGameBoard.getBomberMan())){
	 boolean centeredXAxis = checkIfCharacterIsCenteredXAxis(myGameBoard.getBomberMan());
	 boolean centeredYAxis = checkIfCharacterIsCenteredYAxis(myGameBoard.getBomberMan());
	 if(keyE == KeyEvent.VK_RIGHT &&!(checkCollision(myGameBoard.getBomberMan(), 2)))
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
		 System.out.println("MOVING RIGHT");
		 myGameBoard.getBomberMan().moveRight(myGameBoard.getBomberMan().getSpeed());
	
	 }
	 else if(keyE == KeyEvent.VK_LEFT && !(checkCollision(myGameBoard.getBomberMan(), 1)))
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
	 else if(keyE== KeyEvent.VK_DOWN && !(checkCollision(myGameBoard.getBomberMan(), 4)))
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
	 else  if(keyE == KeyEvent.VK_UP && !(checkCollision(myGameBoard.getBomberMan(), 3)))
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
 /**
  * Returns the myGameBoard field variable of the BomberManController class
  * 
  * @return GameBoard the gameboard field variable of the BomberManController
  * */
 public GameBoard getmyGameBoard(){return this.myGameBoard;}
 /**
  * Checks if the Bomberman move to the right is going to cause a collision with a GameObject
  * 
  * @param character the character object for which the movement is going to be checked for a possible collision given the direction passed as input.
  * @param direction integer representing a direction that the character is going to follow
  * @return boolean representing true if the movement of the character given the direction is going to cause a collision, false otherwise.
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
/**
 * Checks if the Bomberman move to the left  is going to cause a collision with a GameObject
 * 
 * @param character the character object for which the movement is going to be checked for a possible collision given the direction passed as input.
 * @param direction integer representing a direction that the character is going to follow
 * @return boolean representing true if the movement of the character given the direction is going to cause a collision, false otherwise.
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
/**
 * Checks if the Bomberman move to the north is going to cause a collision with a GameObject
 * 
 * @param character the character object for which the movement is going to be checked for a possible collision given the direction passed as input.
 * @param direction integer representing a direction that the character is going to follow
 * @return boolean representing true if the movement of the character given the direction is going to cause a collision, false otherwise.
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
/**
 * Checks if the Bomberman move to the south is going to cause a collision with a GameObject
 * 
 * @param character the character object for which the movement is going to be checked for a possible collision given the direction passed as input.
 * @param direction integer representing a direction that the character is going to follow
 * @return boolean representing true if the movement of the character given the direction is going to cause a collision, false otherwise.
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
/**
 * Checks if the Bomberman is either between 2 GameObjects placed to its right and left if direction = 1 or direction = 2.
 * Checks if the Bomberman is either between 2 GameObjects placed to its north and south if direction = 3 or direction = 4.
 * 
 * @param character a Character object for which a collision is going to be verified given the direction passed as input
 * @param direction an integer representing the direction of the character
 * @return boolean true if the character has a collision given the input direction, false otherwise.
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
/**
 * Checks if the character is centered with the X axis = 0;
 * @param character the Character object that is going to be checked
 * @return boolean true if the character is  centered on the X axis, false otherwise.
 */
public boolean checkIfCharacterIsCenteredXAxis(Character character){

  boolean verify = character.getPositionX() % CONSTANTS.TILE_SIDE_SIZE == ((CONSTANTS.TILE_SIDE_SIZE/2)-(CONSTANTS.BOMBERMAN_WIDTH/2));
  return verify;	
}
/**
 * Checks if the character is centered with the y axis = 0;
 * @param character the Character object that is going to be checked
 * @return boolean true if the character is centered on the y axis, false otherwise.
 */
public boolean checkIfCharacterIsCenteredYAxis(Character character){

boolean verify = character.getPositionY() % CONSTANTS.TILE_SIDE_SIZE == ((CONSTANTS.TILE_SIDE_SIZE/2)-(CONSTANTS.BOMBERMAN_HEIGHT/2));
return verify;	
}
/**
 * returns the offset of the bomberman from the x axis.
 * @param character the Character object
 * @return integer representing the offset of the character from the x axis.
 */
public int computeOffsetFromCenterXaxis(Character character){
int offset = (character.getPositionX()%CONSTANTS.TILE_SIDE_SIZE)-(CONSTANTS.TILE_SIDE_SIZE/2);
return offset;	
}
/**
 * returns the offset of the bomberman from the y axis.
 * @param character the Character object
 * @return integer representing the offset of the character from the y axis
 */
public int computeOffsetFromCenterYaxis(Character character){
int offset =(character.getPositionY()%CONSTANTS.TILE_SIDE_SIZE)-(CONSTANTS.TILE_SIDE_SIZE/2);
return offset;	
}
/**
 * Verifies if the current bomberman cell is shared with a bomb.
 * @param the character that is going to be checked. 
 * @return boolean true if the character shares a cell or a tile with a bomb object. False otherwise.
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
/**
 *   When the bomberman, a Character object, shares a cell with a bomb, it means that it recently dropped it, so this functions manages
 * the bomberman moves inside a cell where there is also a bomb recently dropped.
 * 
 * @param directionMove integer representing the direction of the bomberman movement inside a cell that also has a Bomb object.
 * @return void
 */
public void moveInsideBomb(int directionMove){
	 boolean centeredXAxis = checkIfCharacterIsCenteredXAxis(myGameBoard.getBomberMan());
	 boolean centeredYAxis = checkIfCharacterIsCenteredYAxis(myGameBoard.getBomberMan());
	System.out.println("MOVING INSIDE A BOMB");
	 
	 int xPos =  myGameBoard.getBomberMan().getPositionX();
	 int yPos =  myGameBoard.getBomberMan().getPositionY();
	 int widthBMB =  CONSTANTS.BOMBERMAN_WIDTH;
	 int heightBMB =  CONSTANTS.BOMBERMAN_HEIGHT;
	 int width = CONSTANTS.TILE_SIDE_SIZE;
	 if(directionMove == 2){
		 
		 	 
		 
		 int displacement = myGameBoard.getBomberMan().getSpeed();
		 boolean right = this.myGameBoard.getCell(((yPos)/width),1+(xPos)/width).isEmptyPowerUpException();
		 boolean left = this.myGameBoard.getCell(((yPos)/width),-1+(xPos)/width).isEmptyPowerUpException();
		 boolean leftA = this.myGameBoard.getCell((( yPos+heightBMB)/ width),-1+(xPos)/width).isEmptyPowerUpException();
		 boolean rightA = this.myGameBoard.getCell(((yPos+heightBMB)/ width),1+(xPos)/width).isEmptyPowerUpException();
		 boolean rightB = this.myGameBoard.getCell(((yPos)/width),1+(xPos-widthBMB)/width).isEmptyPowerUpException();
		 boolean rightC = this.myGameBoard.getCell(((yPos+heightBMB)/ width),1+(xPos-widthBMB)/width).isEmptyPowerUpException();
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
			 if(right && rightA || rightB && rightC){
			 myGameBoard.getBomberMan().moveRight(displacement);}
			  }
	 }
	 if(directionMove == 1 ){
		
		 int displacement = myGameBoard.getBomberMan().getSpeed();
		 boolean right = this.myGameBoard.getCell(((yPos)/width),1+(xPos)/width).isEmptyPowerUpException();
		 boolean left = this.myGameBoard.getCell(((yPos)/width),-1+(xPos)/width).isEmptyPowerUpException();
		 boolean leftA = this.myGameBoard.getCell(((yPos+heightBMB)/ width),-1+(xPos)/width).isEmptyPowerUpException();
		 boolean rightA = this.myGameBoard.getCell(((yPos+heightBMB)/ width),1+(xPos)/width).isEmptyPowerUpException();
		 boolean leftB = this.myGameBoard.getCell(((yPos)/width),-1+(xPos+widthBMB)/width).isEmptyPowerUpException();
		 boolean leftC = this.myGameBoard.getCell(((yPos+heightBMB)/ width),-1+(xPos+widthBMB)/width).isEmptyPowerUpException();
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
			 if(left && leftA || leftB && leftC){
			 myGameBoard.getBomberMan().moveLeft(displacement);}
			  }
		 
	 }
	 if(directionMove == 4 ){

		
		 int displacement = myGameBoard.getBomberMan().getSpeed();
		 boolean down = this.myGameBoard.getCell((1+(yPos)/width),( ((xPos)/width))).isEmptyPowerUpException();
			boolean up = this.myGameBoard.getCell((-1+(yPos)/width),(((xPos)/width))).isEmptyPowerUpException();
			boolean downA = this.myGameBoard.getCell((1+(yPos)/width),( ((xPos+ widthBMB)/width))).isEmptyPowerUpException();
			boolean upA = this.myGameBoard.getCell((-1+(yPos)/width),( ((xPos+ widthBMB)/width))).isEmptyPowerUpException();
			boolean downB= this.myGameBoard.getCell((1+(yPos-widthBMB)/width),( ((xPos)/width))).isEmptyPowerUpException();
			boolean downC = this.myGameBoard.getCell((1+(yPos-widthBMB)/width),( ((xPos+ widthBMB)/width))).isEmptyPowerUpException();
			boolean sidesFree = (!up &&! down) || (!upA && !downA);
			  	
		   if(this.myGameBoard.getCell( (yPos+widthBMB)/width,(xPos)/width).isEmptyPowerUpException()|| !sidesFree){
			   if(!centeredXAxis){
					 int offset = computeOffsetFromCenterXaxis(myGameBoard.getBomberMan());
					 if(offset > 0){
					 myGameBoard.getBomberMan().moveLeft(offset+(CONSTANTS.BOMBERMAN_WIDTH/2));}				 
				 if(offset < 0){
					 myGameBoard.getBomberMan().moveRight(-offset -(CONSTANTS.BOMBERMAN_WIDTH/2));
				 }} 
			   if(downA && down || downB && downC){
			   myGameBoard.getBomberMan().moveDown(displacement);}
				  }
	 }
	 if(directionMove == 3 ){
		 
		 int displacement = myGameBoard.getBomberMan().getSpeed();
		 boolean down = this.myGameBoard.getCell((1+(yPos)/width),( ((xPos)/width))).isEmptyPowerUpException();
			boolean up = this.myGameBoard.getCell((-1+(yPos)/width),(((xPos)/width))).isEmptyPowerUpException();
			boolean downA = this.myGameBoard.getCell((1+(yPos)/width),( ((xPos+ widthBMB)/width))).isEmptyPowerUpException();
			boolean upA = this.myGameBoard.getCell((-1+(yPos)/width),( ((xPos+ widthBMB)/width))).isEmptyPowerUpException();
			boolean sidesFree = (!up &&! down) || (!upA && !downA);
			boolean upC = this.myGameBoard.getCell((-1+(yPos+heightBMB)/width),(((xPos)/width))).isEmptyPowerUpException() ;
			boolean upD = this.myGameBoard.getCell((-1+(yPos +heightBMB)/width),( ((xPos+ widthBMB)/width))).isEmptyPowerUpException();
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
			   if((upA && up) || (upC && upD)){
			   myGameBoard.getBomberMan().moveUp(displacement);}
				  }
	 }
		
}
/**
 *  * Drops a  bomb that is not going to be detonated
 *@return void
 */
public void dropBomb(){
	 
	 int posXBomb = this.myGameBoard.getBomberMan().getPositionX();
	 int posYBomb = this.myGameBoard.getBomberMan().getPositionY();
	 int celX = posXBomb / CONSTANTS.TILE_SIDE_SIZE ;
	 int celY = posYBomb / CONSTANTS.TILE_SIDE_SIZE ;
	 
	 if(this.myGameBoard.getBomberMan().getIsAlive() && this.myGameBoard.getCell(celY,celX).isEmpty() && this.myGameBoard.getBomberMan().getNumBombsToDrop() >= this.myGameBoard.getBomberMan().getQteOfBombsDropped() ){
	 this.myGameBoard.addBomb(celX, celY, new Bomb(celX*CONSTANTS.TILE_SIDE_SIZE , celY*CONSTANTS.TILE_SIDE_SIZE, CONSTANTS.BOMB_TIMER, myGameBoard.getBomberMan().getBombRange(), false, "Bomb"));
	 
	 this.myGameBoard.getCell(celY, celX).setHasABomb(true);
	 this.myGameBoard.getBomberMan().setQteOfBombsDropped(1);
	 }

}
/**
 * Converts a direction represented as an integer into either one of these directions 1= Left, 2 = Right, 3 = Up 4 = Down
 * @param key
 * @return integer representing one of the fourth directions 1(Left), 2(Right), 3(Up) or 4(Down).
 */
public int getDirection(int key){
	if(KeyEvent.VK_LEFT == key){return 1;}
	if(KeyEvent.VK_RIGHT == key){return 2;}
	if(KeyEvent.VK_UP == key){return 3;}
	if(KeyEvent.VK_DOWN == key){return 4;}
	return -1;
}
/**
 * Checks for a given cell position (i, j) if the bomnberman is inside the cell represented by the (i, j) position.
 * If the bomberman is in the cell position, the power up in the cell(i,j) is given to the bomberman
 * @param i integer representing a row on the 13x31 tiles game board
 * @param j integer representing a column on the 13x31 tiles game board.
 */
public void pickPowerUp(int i, int j){
	
	 
	 
	
	 if(myGameBoard.getCell(i, j).searcHasAPowerUp() == true && myGameBoard.getCell(i, j).searcHasAConcreteWall() == false ){
		 System.out.println("DOING THE RIGHT THING");
		 if(myGameBoard.getBomberMan().getICell() == i && myGameBoard.getBomberMan().getJCell() == j){
		 if(myGameBoard.getCell(i, j).searchAPowerUp().getPowerUpType() == PowerUpType.SPEED){
			 System.out.println("Getting speed powerUp");
			 myGameBoard.getBomberMan().updateSpeed(myGameBoard.getBomberMan().getSpeed()+CONSTANTS.DEFAULT_POWERUPSPEED_INCREASE);
			 myGameBoard.deletePowerUp(i, j);
			 System.out.println("DELETING POWER UP");
		 }
		 if(myGameBoard.getCell(i, j).searchAPowerUp().getPowerUpType() == PowerUpType.DETONATOR){
			 System.out.println("Getting detonator powerUp");
			 myGameBoard.getBomberMan().setHasDetonator(true);
			 myGameBoard.deletePowerUp(i, j);
			 System.out.println("DELETING POWER UP");
		 }
		 
		 if(myGameBoard.getCell(i, j).searchAPowerUp().getPowerUpType() == PowerUpType.BOMBPASS){
			 System.out.println("Getting bombpass powerUp");
			 myGameBoard.getCell(i,j).getBomberMan().setBombPass(true);
			 myGameBoard.deletePowerUp(i, j);
			 System.out.println("DELETING POWER UP");
		 }
		 
		 if(myGameBoard.getCell(i, j).searchAPowerUp().getPowerUpType() == PowerUpType.FLAMEPASS){
			 System.out.println("Getting flamepass powerUp");
			 myGameBoard.getBomberMan().setFlamePass(true);
			 myGameBoard.deletePowerUp(i, j);
			 System.out.println("DELETING POWER UP");
		 }
		 
		 if(myGameBoard.getCell(i, j).searchAPowerUp().getPowerUpType() == PowerUpType.WALLPASS){
			 System.out.println("Getting brickpass powerUp");
			 myGameBoard.getCell(i,j).getBomberMan().setBrickPass(true);
			 myGameBoard.deletePowerUp(i, j);
			 System.out.println("DELETING POWER UP");
		 }
		 
		 if(myGameBoard.getCell(i, j).searchAPowerUp().getPowerUpType() == PowerUpType.BOMBS){
			 System.out.println("Getting bombs powerUp");
			 myGameBoard.getBomberMan().setNumBombsToDrop(1);
			 myGameBoard.deletePowerUp(i, j);
			 System.out.println("DELETING POWER UP");
		 }
		 if(myGameBoard.getCell(i, j).searchAPowerUp().getPowerUpType() == PowerUpType.FLAMES){
			 System.out.println("Getting bombs powerUp");
			 myGameBoard.getBomberMan().setBombRange(1);
			 myGameBoard.deletePowerUp(i, j);
			 System.out.println("DELETING POWER UP");
		 }
		 if(myGameBoard.getCell(i, j).searchAPowerUp().getPowerUpType() == PowerUpType.MYSTERY){
			 System.out.println("Getting INVISIBILITY powerUp");
			 myGameBoard.getBomberMan().setInvisibilityPowerUp(true);
			 myGameBoard.getCell(i,j).getBomberMan().setInvisibilityPowerUp(true);
			 myGameBoard.getBomberMan().setCreationInvisibilityPowerUp();
			 myGameBoard.deletePowerUp(i, j);
			 System.out.println("DELETING POWER UP");
		 }
		 
		 }}}


		 


	
}
