package bomberMan.gamePlay.Controller;
import java.util.*;

import bomberMan.gamePlay.Model.*;

public class GameBoardController  extends Thread{
Calendar timer;
Calendar timer2;
GameBoard gameBoard;
int bombRange;
int indexjCell;
int indexiCell;

public GameBoardController(GameBoard boardGame){this.gameBoard = boardGame;}
/*
 * This function executes the GameBoardController thread, which detonates bombs without detonator 
 * each time a bomb explosion delay expires.
 */
public void run(){
	
	timer =Calendar.getInstance();
	while(gameBoard.getBomberMan().getIsAlive()){
		this.detonateRegularBombs();
	}
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
					while(counter1 <= CONSTANTS.BOMB_RANGE1){
						if(j +counter1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right == true ){
							if(j +counter1  < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right == true){
								if(this.gameBoard.getCell(i, j+counter1).isEmpty()== false){right = false;}
							}}
							if(right == true && j +counter1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES  && this.gameBoard.getCell(i, j+counter1).isEmpty()){
						this.gameBoard.getCell(i, j+counter1).setFlameImages();
						}
						
						if(j +counter1 + 1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right == true){
							if(this.gameBoard.getCell(i, j+counter1+1).isEmpty()== false){right = false;}}
							
						
						if(j -counter1 >=0 && left == true ){
							if(j -counter1  >=0 && left== true){
								if(this.gameBoard.getCell(i, j-counter1).isEmpty()== false){left = false;}}
							if(left == true && j -counter1 >= 0 && this.gameBoard.getCell(i, j-counter1).isEmpty()){
							this.gameBoard.getCell(i, j-counter1).setFlameImages();}
							}
						    
							if(j -counter1 - 1 >=0 && left == true){
								if(this.gameBoard.getCell(i, j-counter1-1).isEmpty()== false){left = false;}
						
							}
					   if(i -counter1 >=0 && up == true ){
								if(i -counter1  >=0 && up== true){
									if(this.gameBoard.getCell(i-counter1, j).isEmpty()== false){up = false;}}
								if(up == true && i -counter1 >= 0 && this.gameBoard.getCell(i-counter1, j).isEmpty()){
								this.gameBoard.getCell(i-counter1, j).setFlameImages();}
								}
							    
								if(i -counter1 - 1 >=0 && left == true){
									if(this.gameBoard.getCell(i-counter1-1, j).isEmpty()== false){up = false;}
							
								}
					 if(i +counter1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && down == true ){
									if(i +counter1  < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down== true){
										if(this.gameBoard.getCell(i+counter1, j).isEmpty()== false){down = false;}
									}}
									if(down == true && i +counter1 < CONSTANTS.NUMBER_OF_VERTICAL_TILES  && this.gameBoard.getCell(i+counter1, j).isEmpty()){
								this.gameBoard.getCell(i+counter1, j).setFlameImages();}
								
								if(i+counter1 + 1 < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down == true){
									if(this.gameBoard.getCell(i+counter1 +1, j).isEmpty()== false){down = false;}}
									
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
							this.gameBoard.getCell(i, j+counter2).removeFlames();}
							
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
					 
					   
						 
					
					 
				 }
		 }
	 }
}
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
	if(this.gameBoard.getBomberMan().getICell() == i && this.gameBoard.getBomberMan().getJCell() == j){
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
				if(this.gameBoard.getBomberMan().getICell() == i && this.gameBoard.getBomberMan().getJCell() == j+ counter1){
					System.out.println("KILLING BOMBERMAN");
					this.gameBoard.getBomberMan().die();}
				if(this.gameBoard.getBomberMan().getICellBottomBomberman() == i && this.gameBoard.getBomberMan().getJCell() == j+ counter1){
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
				if(this.gameBoard.getBomberMan().getICell() == i && this.gameBoard.getBomberMan().getJCell() == j- counter1){
					System.out.println("KILLING BOMBERMAN");
					this.gameBoard.getBomberMan().die();}
				if(this.gameBoard.getBomberMan().getICellBottomBomberman() == i && this.gameBoard.getBomberMan().getJCell() == j- counter1){
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
				if(this.gameBoard.getBomberMan().getICell() == i +counter1 && this.gameBoard.getBomberMan().getJCell() == j){
					System.out.println("KILLING BOMBERMAN");
					this.gameBoard.getBomberMan().die();}
				if(this.gameBoard.getBomberMan().getICell() == i +counter1 && this.gameBoard.getBomberMan().getJCellRightMostBomberman() == j){
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
				if(this.gameBoard.getBomberMan().getICell() == i-counter1 && this.gameBoard.getBomberMan().getJCell() == j){
					System.out.println("KILLING BOMBERMAN");
					this.gameBoard.getBomberMan().die();}
				if(this.gameBoard.getBomberMan().getICell() == i -counter1 && this.gameBoard.getBomberMan().getJCellRightMostBomberman() == j){
					System.out.println("KILLING BOMBERMAN");
					this.gameBoard.getBomberMan().die();}
				
		}
		
		if(i -counter1 - 1 >= 0 && up == true){
			if(this.gameBoard.getCell(i - counter1 - 1, j).searcHasAConcreteWall()== true){up = false;}}
		
			
		
		counter1++;
	}
	
	
	
}


}
