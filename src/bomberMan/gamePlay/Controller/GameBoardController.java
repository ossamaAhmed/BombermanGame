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
					 
					this.gameBoard.getCell(i, j).deleteElement("Bomb");
					 this.killBomberman(i, j);
					int counter2 = 1;
					
					
					 
					 
					   
						 
					
					 
				 }
		 }
	 }
}
}
/*This functions kills the bomberman if for (i,j) in the gameboard, the Bomberman is in the bombs range given by the cells at (i+BombRange, j), (i-Bombrange, j)
 * (i, j+BombRange), (i, j-Bombrange)
 * 
 */
public void killBomberman(int i, int j){
	int counter = 0;
	while(counter <=CONSTANTS.BOMB_RANGE1 ){
	if(this.gameBoard.getBomberMan().getICell() == (i+counter) && this.gameBoard.getBomberMan().getJCell() == j){
	System.out.println("KILLING BOMBERMAN");
	this.gameBoard.getBomberMan().die();
	
	}
	if(this.gameBoard.getBomberMan().getICellBottomBomberman() == (i+counter) && this.gameBoard.getBomberMan().getJCell() == j){
		System.out.println("KILLING BOMBERMAN");
		this.gameBoard.getBomberMan().die();
		
		}
	if(this.gameBoard.getBomberMan().getICellBottomBomberman() == (i+counter) && this.gameBoard.getBomberMan().getJCellRightMostBomberman() == j){
		System.out.println("KILLING BOMBERMAN");
		this.gameBoard.getBomberMan().die();
		
		}
	if(this.gameBoard.getBomberMan().getICell() == (i+counter) && this.gameBoard.getBomberMan().getJCellRightMostBomberman() == j){
		System.out.println("KILLING BOMBERMAN");
		this.gameBoard.getBomberMan().die();
		
		}
	counter++;
	
	}
	counter = -1;
	while(counter >=-CONSTANTS.BOMB_RANGE1 ){
		if(this.gameBoard.getBomberMan().getICell() == (i+counter) && this.gameBoard.getBomberMan().getJCell() == j){
		System.out.println("KILLING BOMBERMAN");
		this.gameBoard.getBomberMan().die();
		}
		if(this.gameBoard.getBomberMan().getICellBottomBomberman() == (i+counter) && this.gameBoard.getBomberMan().getJCell() == j){
			System.out.println("KILLING BOMBERMAN");
			this.gameBoard.getBomberMan().die();
			
			}
		if(this.gameBoard.getBomberMan().getICellBottomBomberman() == (i+counter) && this.gameBoard.getBomberMan().getJCellRightMostBomberman() == j){
			System.out.println("KILLING BOMBERMAN");
			this.gameBoard.getBomberMan().die();
			}
		if(this.gameBoard.getBomberMan().getICell() == (i+counter) && this.gameBoard.getBomberMan().getJCellRightMostBomberman() == j){
			System.out.println("KILLING BOMBERMAN");
			this.gameBoard.getBomberMan().die();
			
			}
		counter--;}
	counter =1;
	while(counter <=CONSTANTS.BOMB_RANGE1 ){
		if(this.gameBoard.getBomberMan().getICell() == (i) && this.gameBoard.getBomberMan().getJCell() == (j+counter)){
		System.out.println("KILLING BOMBERMAN");
		this.gameBoard.getBomberMan().die();
		
		}
		if(this.gameBoard.getBomberMan().getICellBottomBomberman() == (i) && this.gameBoard.getBomberMan().getJCell() == (j+counter)){
			System.out.println("KILLING BOMBERMAN");
			this.gameBoard.getBomberMan().die();
			
			}
		if(this.gameBoard.getBomberMan().getICellBottomBomberman() == (i) && this.gameBoard.getBomberMan().getJCellRightMostBomberman() == (j+counter)){
			System.out.println("KILLING BOMBERMAN");
			this.gameBoard.getBomberMan().die();
			
			}
		if(this.gameBoard.getBomberMan().getICell() == (i) && this.gameBoard.getBomberMan().getJCellRightMostBomberman() == (j+counter)){
			System.out.println("KILLING BOMBERMAN");
			this.gameBoard.getBomberMan().die();
			
			}
		counter++;
		
	}
		counter = -1;
		while(counter >=-CONSTANTS.BOMB_RANGE1 ){
			if(this.gameBoard.getBomberMan().getICell() == (i) && this.gameBoard.getBomberMan().getJCell() == (j+counter)){
			System.out.println("KILLING BOMBERMAN");
			this.gameBoard.getBomberMan().die();
			}
			if(this.gameBoard.getBomberMan().getICellBottomBomberman() == (i) && this.gameBoard.getBomberMan().getJCell() == (j+counter)){
				System.out.println("KILLING BOMBERMAN");
				this.gameBoard.getBomberMan().die();
				
				}
			if(this.gameBoard.getBomberMan().getICellBottomBomberman() == (i) && this.gameBoard.getBomberMan().getJCellRightMostBomberman() == (j+counter)){
				System.out.println("KILLING BOMBERMAN");
				this.gameBoard.getBomberMan().die();
				
				}
			if(this.gameBoard.getBomberMan().getICell() == (i) && this.gameBoard.getBomberMan().getJCellRightMostBomberman() == (j+counter)){
				System.out.println("KILLING BOMBERMAN");
				this.gameBoard.getBomberMan().die();
				
				}
			counter--;}
		

	
	
}


}
