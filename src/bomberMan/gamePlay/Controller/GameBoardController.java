package bomberMan.gamePlay.Controller;
import java.util.*;
import java.lang.Runnable;
import bomberMan.gamePlay.Model.*;


public class GameBoardController extends Thread{
Calendar timer = Calendar.getInstance();
Calendar timer2;
GameBoard gameBoard;
int bombRange;
int indexjCell;
int indexiCell;

DestroyBomb bombDestroyer;
public GameBoardController(){}
public GameBoardController(GameBoard boardGame){this.gameBoard = boardGame;}
/*
 * This function executes the GameBoardController thread, which detonates bombs without detonator 
 * each time a bomb explosion delay expires.
 */
public void run(){
	
	timer = Calendar.getInstance();
	bombDestroyer = new DestroyBomb(this.gameBoard, this);
	
	bombDestroyer.run();
	
	//while(gameBoard.getBomberMan().getIsAlive()){
		//this.detonateRegularBombs();
		
		//}
		
		
	
}
/*
 *This functions detonates bombs that dont have a detonator.
 * **/
 
public void detonateRegularBombs(){
	
	
	for(int i=0;i<CONSTANTS.NUMBER_OF_VERTICAL_TILES;i++)
	 {
		 for(int j=0;j<CONSTANTS.NUMBER_OF_HORIZONTAL_TILES;j++)
		 {
			
			 if(this.gameBoard.getCell(i, j).getHasABomb()){
				
				 
				 if(this.gameBoard.getCell(i, j).searchBomb().getDetonationTime() <= this.gameBoard.getCell(i, j).searchBomb().getBombTimer()){
					 System.out.println("TimeExplosionA: "+this.gameBoard.getCell(i, j).searchBomb().getDetonationTime());
					 System.out.println("TimeExplosionB: "+this.gameBoard.getCell(i, j).searchBomb().getBombTimer());
					 System.out.println("TimeExplosionC: "+this.gameBoard.getCell(i, j).searchBomb().getBombTimer());
					 System.out.println("TimeExplosionD: "+this.gameBoard.getCell(i, j).searchBomb().getBombTimer());
					 System.out.println("EXPLODE");
					System.out.println(i+ " " +j);
					this.gameBoard.getCell(i, j).setFlameImages();
					int counter1 = 1;
					boolean right = true;
					boolean left = true;
					boolean down = true;
					boolean up = true;
					this.destroyBricks(i, j);
					while(counter1 <= CONSTANTS.BOMB_RANGE1){
						if(j +counter1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right == true ){
							if(j +counter1  < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right == true){
								if(this.gameBoard.getCell(i, j+counter1).isEmptyBrickException()== false){right = false;}
							}}
							if(right == true && j +counter1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES  && this.gameBoard.getCell(i, j+counter1).isEmptyBrickException()){
						this.gameBoard.getCell(i, j+counter1).setFlameImages();
						}
						
						if(j +counter1 + 1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right == true){
							if(this.gameBoard.getCell(i, j+counter1+1).isEmptyBrickException()== false){right = false;}}
							
						
						if(j -counter1 >=0 && left == true ){
							if(j -counter1  >=0 && left== true){
								if(this.gameBoard.getCell(i, j-counter1).isEmptyBrickException()== false){left = false;}}
							if(left == true && j -counter1 >= 0 && this.gameBoard.getCell(i, j-counter1).isEmptyBrickException()){
							this.gameBoard.getCell(i, j-counter1).setFlameImages();}
							}
						    
							if(j -counter1 - 1 >=0 && left == true){
								if(this.gameBoard.getCell(i, j-counter1-1).isEmptyBrickException()== false){left = false;}
						
							}
					   if(i -counter1 >=0 && up == true ){
								if(i -counter1  >=0 && up== true){
									if(this.gameBoard.getCell(i-counter1, j).isEmptyBrickException()== false){up = false;}}
								if(up == true && i -counter1 >= 0 && this.gameBoard.getCell(i-counter1, j).isEmptyBrickException()){
								this.gameBoard.getCell(i-counter1, j).setFlameImages();}
								}
							    
								if(i -counter1 - 1 >=0 && left == true){
									if(this.gameBoard.getCell(i-counter1-1, j).isEmptyBrickException()== false){up = false;}
							
								}
					 if(i +counter1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && down == true ){
									if(i +counter1  < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down== true){
										if(this.gameBoard.getCell(i+counter1, j).isEmptyBrickException()== false){down = false;}
									}}
									if(down == true && i +counter1 < CONSTANTS.NUMBER_OF_VERTICAL_TILES  && this.gameBoard.getCell(i+counter1, j).isEmptyBrickException()){
								this.gameBoard.getCell(i+counter1, j).setFlameImages();}
								
								if(i+counter1 + 1 < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down == true){
									if(this.gameBoard.getCell(i+counter1 +1, j).isEmptyBrickException()== false){down = false;}}
									
						counter1++;
					}
					this.gameBoard.getCell(i, j).deleteElement("Bomb");
					this.killBomberman1(i, j);
					
						int counter2 = 1;
						boolean right2 = true;
						boolean left2 = true;
						boolean down2 = true;
						boolean up2 = true;
						while(counter2 <= CONSTANTS.BOMB_RANGE1){
							if(j +counter2 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right2 == true ){
								if(j +counter2  < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right2 == true){
									if(this.gameBoard.getCell(i, j+counter2).hasAFlame()== false){right2 = false;}
								}}
								if(right2 == true && j +counter2 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES  && this.gameBoard.getCell(i, j+counter2).hasAFlame()){
							this.gameBoard.getCell(i, j+counter2).removeFlames();
							
								}
							
							if(j +counter2 + 1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right2 == true){
								if(this.gameBoard.getCell(i, j+counter2+1).hasAFlame()== false){right2 = false;}}
								
							
							if(j -counter2 >=0 && left2 == true ){
								if(j -counter2  >=0 && left2== true){
									if(this.gameBoard.getCell(i, j-counter2).hasAFlame()== false){left2 = false;}}
								if(left2 == true && j -counter2 >= 0 && this.gameBoard.getCell(i, j-counter2).hasAFlame()){
								this.gameBoard.getCell(i, j-counter2).removeFlames();}
								}
							    
								if(j -counter2 - 1 >=0 && left2 == true){
									if(this.gameBoard.getCell(i, j-counter2-1).hasAFlame()== false){left2 = false;}
							
								}
						   if(i -counter2 >=0 && up2 == true ){
									if(i -counter2  >=0 && up2== true){
										if(this.gameBoard.getCell(i-counter2, j).hasAFlame()== false){up2 = false;}}
									if(up2 == true && i -counter2 >= 0 && this.gameBoard.getCell(i-counter2, j).hasAFlame()){
									this.gameBoard.getCell(i-counter2, j).removeFlames();}
									}
								    
									if(i -counter2 - 1 >=0 && up2 == true){
										if(this.gameBoard.getCell(i-counter2-1, j).hasAFlame()== false){up2 = false;}
								
									}
						 if(i +counter2 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && down2 == true ){
										if(i +counter2  < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down2== true){
											if(this.gameBoard.getCell(i+counter2, j).hasAFlame()== false){down2 = false;}
										}}
										if(down2 == true && i +counter2 < CONSTANTS.NUMBER_OF_VERTICAL_TILES  && this.gameBoard.getCell(i+counter2, j).hasAFlame()){
									this.gameBoard.getCell(i+counter2, j).removeFlames();}
									
									if(i+counter2 + 1 < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down2 == true){
										if(this.gameBoard.getCell(i+counter2 +1, j).hasAFlame()== false){down2 = false;}}
										
							counter2++;
						}
					
					  if(this.gameBoard.getCell(i, j).hasAFlame()){
						this.gameBoard.getCell(i, j).removeFlames();}
					 
					  this.gameBoard.getBomberMan().setQteOfBombsDropped(-1);
						 
					
					 
				 }
		 }
	 }
}}
/*This functions kills the bomberman if for (i,j) in the gameboard, the Bomberman is in the bombs range given by the cells at (i+BombRange, j), (i-Bombrange, j)
 * (i, j+BombRange), (i, j-Bombrange)
 * 
 */
public void killBomberman1(int i, int j){
	boolean up = true;
	boolean down = true;
	boolean right = true;
	boolean left = true;
	if(this.gameBoard.getBomberMan().getFlamePass() == false && this.gameBoard.getBomberMan().getICell() == i && this.gameBoard.getBomberMan().getJCell() == j){
	System.out.println("KILLING BOMBERMAN");
	this.gameBoard.getBomberMan().die();
	}
	
	int counter1 = 1;
	while(counter1 <= CONSTANTS.BOMB_RANGE1){
		// checking the right range of the bomb; if bomberman is present and there is no concrete walls at bomb range
		if(j +counter1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right == true ){
			if(j +counter1  < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right == true){
				if(this.gameBoard.getCell(i, j+counter1).searcHasAConcreteWall()== true){right = false;}
			}}
			if(right == true && j +counter1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES  && this.gameBoard.getCell(i, j+counter1).searcHasAConcreteWall() == false){
				if(this.gameBoard.getBomberMan().getFlamePass() == false && this.gameBoard.getBomberMan().getICell() == i && this.gameBoard.getBomberMan().getJCell() == j+ counter1){
					System.out.println("KILLING BOMBERMAN");
					this.gameBoard.getBomberMan().die();}
				if(this.gameBoard.getBomberMan().getFlamePass() == false && this.gameBoard.getBomberMan().getICellBottomBomberman() == i && this.gameBoard.getBomberMan().getJCell() == j+ counter1){
					System.out.println("KILLING BOMBERMAN");
					this.gameBoard.getBomberMan().die();}
				
		}
		
		if(j +counter1 + 1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right == true){
			if(this.gameBoard.getCell(i, j+counter1+1).searcHasAConcreteWall()== true){right = false;}}
		// checking the left range of the bomb; if bomberman is present and there is no concrete walls at bomb range
		if(j -counter1 >=0 && left == true ){
			if(j -counter1  >=0 && left == true){
				if(this.gameBoard.getCell(i, j-counter1).searcHasAConcreteWall()== true){left = false;}
			}}
			if(left == true && j -counter1 >=0  && this.gameBoard.getCell(i, j-counter1).searcHasAConcreteWall() == false){
				if(this.gameBoard.getBomberMan().getFlamePass() == false &&this.gameBoard.getBomberMan().getICell() == i && this.gameBoard.getBomberMan().getJCell() == j- counter1){
					System.out.println("KILLING BOMBERMAN");
					this.gameBoard.getBomberMan().die();}
				if(this.gameBoard.getBomberMan().getFlamePass() == false &&this.gameBoard.getBomberMan().getICellBottomBomberman() == i && this.gameBoard.getBomberMan().getJCell() == j- counter1){
					System.out.println("KILLING BOMBERMAN");
					this.gameBoard.getBomberMan().die();}
				
		}
		
		if(j -counter1 - 1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && left == true){
			if(this.gameBoard.getCell(i, j-counter1-1).searcHasAConcreteWall()== true){left = false;}}
		// checking the south range of the bomb;if bomberman is present and there is no concrete walls at range
		if(i +counter1 < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down == true ){
			if(i +counter1  < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down == true){
				if(this.gameBoard.getCell(i + counter1, j).searcHasAConcreteWall()== true){down = false;}
			}}
			if(down == true && i +counter1 < CONSTANTS.NUMBER_OF_VERTICAL_TILES  && this.gameBoard.getCell(i+counter1, j).searcHasAConcreteWall() == false){
				if(this.gameBoard.getBomberMan().getFlamePass() == false &&this.gameBoard.getBomberMan().getICell() == i +counter1 && this.gameBoard.getBomberMan().getJCell() == j){
					System.out.println("KILLING BOMBERMAN");
					this.gameBoard.getBomberMan().die();}
				if(this.gameBoard.getBomberMan().getFlamePass() == false &&this.gameBoard.getBomberMan().getICell() == i +counter1 && this.gameBoard.getBomberMan().getJCellRightMostBomberman() == j){
					System.out.println("KILLING BOMBERMAN");
					this.gameBoard.getBomberMan().die();}
				
		}
		
		if(i +counter1 + 1 < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down == true){
			if(this.gameBoard.getCell(i + counter1 + 1, j).searcHasAConcreteWall()== true){down = false;}}
		
		// checking the north range of the bomb;if bomberman is present and there is no concrete walls at range
		
		if(i -counter1 >= 0 && up == true ){
			if(i - counter1  >= 0 && up == true){
				if(this.gameBoard.getCell(i - counter1, j).searcHasAConcreteWall()== true){up = false;}
			}}
			if(up == true && i -counter1 >= 0  && this.gameBoard.getCell(i-counter1, j).searcHasAConcreteWall() == false){
				if(this.gameBoard.getBomberMan().getFlamePass() == false &&this.gameBoard.getBomberMan().getICell() == i-counter1 && this.gameBoard.getBomberMan().getJCell() == j){
					System.out.println("KILLING BOMBERMAN");
					this.gameBoard.getBomberMan().die();}
				if(this.gameBoard.getBomberMan().getFlamePass() == false &&this.gameBoard.getBomberMan().getICell() == i -counter1 && this.gameBoard.getBomberMan().getJCellRightMostBomberman() == j){
					System.out.println("KILLING BOMBERMAN");
					this.gameBoard.getBomberMan().die();}
				
		}
		
		if(i -counter1 - 1 >= 0 && up == true){
			if(this.gameBoard.getCell(i - counter1 - 1, j).searcHasAConcreteWall()== true){up = false;}}
		
			
		
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
					if(this.gameBoard.getCell(i, j+counter1).searcHasAConcreteWall()== true){right = false;}
				}}
				if(right == true && j +counter1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES  && this.gameBoard.getCell(i, j+counter1).searcHasAConcreteWall() == false){
					if(this.gameBoard.getCell(i, j+ counter1).searcHasABrickWall() == true){
						System.out.println("DESTROYING BRICK");
						this.gameBoard.getCell(i, j+ counter1).deleteElement("Brick");}			
					
		         	}
			
			if(j +counter1 + 1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right == true){
				if(this.gameBoard.getCell(i, j+counter1+1).searcHasAConcreteWall()== true){right = false;}}
			// checking the left range of the bomb; if a brick is present and there is no concrete walls at bomb range
			if(j -counter1 >=0 && left == true ){
				if(j -counter1  >=0 && left == true){
					if(this.gameBoard.getCell(i, j-counter1).searcHasAConcreteWall()== true){left = false;}
				}}
				if(left == true && j -counter1 >=0  && this.gameBoard.getCell(i, j-counter1).searcHasAConcreteWall() == false){
					if(this.gameBoard.getCell(i, j- counter1).searcHasABrickWall() == true){
						System.out.println("DESTROYING BRICK");
						this.gameBoard.getCell(i, j-counter1).deleteElement("Brick");}
									
			}
			
			if(j -counter1 - 1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && left == true){
				if(this.gameBoard.getCell(i, j-counter1-1).searcHasAConcreteWall()== true){left = false;}}
			// checking the south range of the bomb;if a brick is present and there is no concrete walls at range
			if(i +counter1 < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down == true ){
				if(i +counter1  < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down == true){
					if(this.gameBoard.getCell(i + counter1, j).searcHasAConcreteWall()== true){down = false;}
				}}
				if(down == true && i +counter1 < CONSTANTS.NUMBER_OF_VERTICAL_TILES  && this.gameBoard.getCell(i+counter1, j).searcHasAConcreteWall() == false){
					if(this.gameBoard.getCell(i +counter1,j).searcHasABrickWall() == true){
						System.out.println("DESTROYING BRICK");
						this.gameBoard.getCell(i+counter1,j).deleteElement("Brick");}
					
					
			}
			
			if(i +counter1 + 1 < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down == true){
				if(this.gameBoard.getCell(i + counter1 + 1, j).searcHasAConcreteWall()== true){down = false;}}
			
			// checking the north range of the bomb;if a brick is present and there is no concrete walls at range
			
			if(i -counter1 >= 0 && up == true ){
				if(i - counter1  >= 0 && up == true){
					if(this.gameBoard.getCell(i - counter1, j).searcHasAConcreteWall()== true){up = false;}
				}}
				if(up == true && i -counter1 >= 0  && this.gameBoard.getCell(i-counter1, j).searcHasAConcreteWall() == false){
					if(this.gameBoard.getCell(i-counter1,j).searcHasABrickWall() == true){
						System.out.println("DESTROYING BRICK");
						this.gameBoard.getCell(i-counter1,j).deleteElement("Brick");}
					
					
			}
			
			if(i -counter1 - 1 >= 0 && up == true){
				if(this.gameBoard.getCell(i - counter1 - 1, j).searcHasAConcreteWall()== true){up = false;}}
			
				
			
			counter1++;
		}
}
	/*
	 * Detonate a bomb which has a detonator
	 */
	public void detonateOldestBomb(){
		 if(this.gameBoard.getBomberMan().getHasDetonator()){
		int xPos =this.gameBoard.getBomberMan().getOldestBomb().getPositionX();
		System.out.println("X POSITION "+ xPos );
		int yPos = this.gameBoard.getBomberMan().getOldestBomb().getPositionY();
		System.out.println("Y POSITION" + yPos);
		int jCell = xPos/CONSTANTS.TILE_SIDE_SIZE;
		System.out.println("jCELL " + jCell);
		int iCell = yPos/CONSTANTS.TILE_SIDE_SIZE;
		System.out.println("ICELL " + iCell);
		this.gameBoard.getCell(iCell, jCell).setHasADetonatorBomb(false);
		this.gameBoard.getBomberMan().removeOldestBomb();
		this.gameBoard.getCell(iCell, jCell).setHasADetonatorBomb(false);
		this.gameBoard.getBomberMan().removeOldestBomb();
		 this.gameBoard.getBomberMan().setQteOfBombsDropped(-1);
		 }
		
	}
	
	/*
	 * Drops a  bomb that is  going to be detonated
	 */
	public void dropBombDetonator(){
		 if(this.gameBoard.getBomberMan().getHasDetonator()&& this.gameBoard.getBomberMan().getNumBombsToDrop() >= this.gameBoard.getBomberMan().getQteOfBombsDropped()){
		 int posXBomb = this.gameBoard.getBomberMan().getPositionX();
		 int posYBomb = this.gameBoard.getBomberMan().getPositionY();
		 int celX = posXBomb / CONSTANTS.TILE_SIDE_SIZE ;
		 int celY = posYBomb / CONSTANTS.TILE_SIDE_SIZE ;
		 if(this.gameBoard.getCell(celY, celX).isEmpty()){
		 this.gameBoard.addBomb(celX, celY, new Bomb(celX*CONSTANTS.TILE_SIDE_SIZE , celY*CONSTANTS.TILE_SIDE_SIZE, CONSTANTS.BOMB_TIMER2, gameBoard.getBomberMan().getBombRange(), true, "Bomb"));
		 this.gameBoard.getBomberMan().addBomb(new Bomb(celX*CONSTANTS.TILE_SIDE_SIZE , celY*CONSTANTS.TILE_SIDE_SIZE, CONSTANTS.BOMB_TIMER2,gameBoard.getBomberMan().getBombRange(), true, "Bomb"));
		 this.gameBoard.getCell(celY, celX).setHasABomb(true);
		 
		 this.gameBoard.getCell(celY, celX).setHasADetonatorBomb(true);
		 this.gameBoard.getBomberMan().setQteOfBombsDropped(1);
		 }
		 
		 }
	}
	
	/*Function that gives if bomberman is in a power up, if true, the bomberman picks up the powerup
	 * 
	 * */
   public void pickPowerUp(int i, int j){
	
			 
			 if(this.gameBoard.getCell(i, j).searcHasAPowerUp() == true){
				 
				 if(this.gameBoard.getBomberMan().getICell() == i && this.gameBoard.getBomberMan().getJCell() == j){
				 if(this.gameBoard.getCell(i, j).searchAPowerUp().getPowerUpType() == PowerUpType.SPEED){
					 System.out.println("Getting speed powerUp");
					 this.gameBoard.getBomberMan().updateSpeed(this.gameBoard.getBomberMan().getSpeed()+CONSTANTS.DEFAULT_POWERUPSPEED_INCREASE);
				 }
				 if(this.gameBoard.getCell(i, j).searchAPowerUp().getPowerUpType() == PowerUpType.DETONATOR){
					 System.out.println("Getting speed powerUp");
					 this.gameBoard.getBomberMan().setHasDetonator(true);
			 if(gameBoard.getCell(i, j).searcHasAPowerUp() == true){
				 System.out.println("DOING THE RIGHT THING");
				 if(gameBoard.getBomberMan().getICell() == i && gameBoard.getBomberMan().getJCell() == j){
				 if(gameBoard.getCell(i, j).searchAPowerUp().getPowerUpType() == PowerUpType.SPEED){
					 System.out.println("Getting speed powerUp");
					 gameBoard.getBomberMan().updateSpeed(gameBoard.getBomberMan().getSpeed()+CONSTANTS.DEFAULT_POWERUPSPEED_INCREASE);
					 gameBoard.getCell(i,j).removePowerUp();
					 System.out.println("DELETING POWER UP");
				 }
				 if(gameBoard.getCell(i, j).searchAPowerUp().getPowerUpType() == PowerUpType.DETONATOR){
					 System.out.println("Getting detonator powerUp");
					gameBoard.getBomberMan().setHasDetonator(true);
					 gameBoard.getCell(i,j).removePowerUp();
					 System.out.println("DELETING POWER UP");
				 }
				 
				 if(gameBoard.getCell(i, j).searchAPowerUp().getPowerUpType() == PowerUpType.BOMBPASS){
					 System.out.println("Getting bombpass powerUp");
					gameBoard.getBomberMan().setBombPass(true);
					gameBoard.getCell(i,j).removePowerUp();
					 System.out.println("DELETING POWER UP");
				 }
				 
				 if(gameBoard.getCell(i, j).searchAPowerUp().getPowerUpType() == PowerUpType.FLAMEPASS){
					 System.out.println("Getting flamepass powerUp");
					gameBoard.getBomberMan().setFlamePass(true);
					gameBoard.getCell(i,j).removePowerUp();
					 System.out.println("DELETING POWER UP");
				 }
				 
				 if(gameBoard.getCell(i, j).searchAPowerUp().getPowerUpType() == PowerUpType.WALLPASS){
					 System.out.println("Getting brickpass powerUp");
					gameBoard.getBomberMan().setBrickPass(true);
					 gameBoard.getCell(i,j).removePowerUp();
					 System.out.println("DELETING POWER UP");
				 }
				 
				 if(gameBoard.getCell(i, j).searchAPowerUp().getPowerUpType() == PowerUpType.BOMBS){
					 System.out.println("Getting bombs powerUp");
					gameBoard.getBomberMan().setNumBombsToDrop(1);
					gameBoard.getCell(i,j).removePowerUp();
					 System.out.println("DELETING POWER UP");
				 }
				 
				 }}}
 
}
				 }
   }
}
