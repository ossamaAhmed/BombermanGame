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
	
	
	
}
/*
 *This functions detonates bombs that dont have a detonator.
 * **/
 
public void detonateRegularBombs(){
	

	int iCell = this.gameBoard.getBomberMan().getPositionY()/CONSTANTS.TILE_SIDE_SIZE;
	int jCell = this.gameBoard.getBomberMan().getPositionX()/CONSTANTS.TILE_SIDE_SIZE;
	if(gameBoard.getBombs().size()>0){
	for(int k=0;k<gameBoard.getBombs().size();k++)
	 {
		     System.out.println("TRYONG TO DETONATE BOMBS");
		     if(this.gameBoard.getBombs().size() > 0){
		     int j = gameBoard.getBombs().get(k).getPositionX()/ CONSTANTS.TILE_SIDE_SIZE;
		     int i = gameBoard.getBombs().get(k).getPositionY()/CONSTANTS.TILE_SIDE_SIZE;
			 if(this.gameBoard.getCell(i, j).getHasABomb()){
				
				 
				 if(this.gameBoard.getCell(i, j).searchBomb().getExplodeFast() == true ||this.gameBoard.getCell(i, j).searchBomb().getDetonationTime() <= this.gameBoard.getCell(i, j).searchBomb().getBombTimer()){
					 System.out.println("TimeExplosionA: "+this.gameBoard.getCell(i, j).searchBomb().getDetonationTime());
					 System.out.println("TimeExplosionB: "+this.gameBoard.getCell(i, j).searchBomb().getBombTimer());
					 System.out.println("TimeExplosionC: "+this.gameBoard.getCell(i, j).searchBomb().getBombTimer());
					 System.out.println("TimeExplosionD: "+this.gameBoard.getCell(i, j).searchBomb().getBombTimer());
					 System.out.println("EXPLODE");
					System.out.println(i+ " " +j);
					
					
					int counter1 = 1;
					boolean right = true;
					boolean left = true;
					boolean down = true;
					boolean up = true;
					this.destroyBricks(i, j);
					this.gameBoard.getCell(i,j).setFlameImages();
					
					while(counter1 <= gameBoard.getBomberMan().getBombRange()){
						
							if(j +counter1  < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right == true){
								
								if(this.gameBoard.getCell(i, j+counter1).isEmptyBrickException()== false){right = false;}
							}
							if(right == true && j +counter1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES  && this.gameBoard.getCell(i, j+counter1).isEmptyBrickException()){
						this.gameBoard.getCell(i, j+counter1).setFlameImages();
						if(this.gameBoard.getCell(i, j+counter1).getHasABomb()){this.gameBoard.getCell(i, j+counter1).searchBomb().setImage(CONSTANTS.Bomb_EXPLOSION);}
						}
						
						if(j +counter1 + 1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right == true){
							
							if(this.gameBoard.getCell(i, j+counter1+1).isEmptyBrickException()== false){right = false;}}
							
						
						if(j -counter1 >=0 && left == true ){
							if(j -counter1  >=0 && left== true){
                               
								if(this.gameBoard.getCell(i, j-counter1).isEmptyBrickException()== false){left = false;}}
							if(left == true && j -counter1 >= 0 && this.gameBoard.getCell(i, j-counter1).isEmptyBrickException()){
							this.gameBoard.getCell(i, j-counter1).setFlameImages();
							if(this.gameBoard.getCell(i, j-counter1).getHasABomb()){this.gameBoard.getCell(i, j-counter1).searchBomb().setImage(CONSTANTS.Bomb_EXPLOSION);}}
							}
						    
							if(j -counter1 - 1 >=0 && left == true){
								
								if(this.gameBoard.getCell(i, j-counter1-1).isEmptyBrickException()== false){left = false;}
						
							}
					   if(i -counter1 >=0 && up == true ){
								if(i -counter1  >=0 && up== true){
									
									if(this.gameBoard.getCell(i-counter1, j).isEmptyBrickException()== false){up = false;}}
								if(up == true && i -counter1 >= 0 && this.gameBoard.getCell(i-counter1, j).isEmptyBrickException()){
								this.gameBoard.getCell(i-counter1, j).setFlameImages();
								if(this.gameBoard.getCell(i-counter1, j).getHasABomb()){this.gameBoard.getCell(i-counter1, j).searchBomb().setImage(CONSTANTS.Bomb_EXPLOSION);}}
								}
							    
								if(i -counter1 - 1 >=0 && left == true){
                                       
									if(this.gameBoard.getCell(i-counter1-1, j).isEmptyBrickException()== false){up = false;}
							
								}
					 if(i +counter1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && down == true ){
									if(i +counter1  < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down== true){
										
										if(this.gameBoard.getCell(i+counter1, j).isEmptyBrickException()== false){down = false;}
									}}
									if(down == true && i +counter1 < CONSTANTS.NUMBER_OF_VERTICAL_TILES  && this.gameBoard.getCell(i+counter1, j).isEmptyBrickException()){
								this.gameBoard.getCell(i+counter1, j).setFlameImages();
									if(this.gameBoard.getCell(i+counter1, j).getHasABomb()){this.gameBoard.getCell(i+counter1, j).searchBomb().setImage(CONSTANTS.Bomb_EXPLOSION);}}
								if(i+counter1 + 1 < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down == true){
									
									if(this.gameBoard.getCell(i+counter1 +1, j).isEmptyBrickException()== false){down = false;}}
									
						counter1++;
					}
					
					 this.gameBoard.getBombs().remove(k);
						
					this.gameBoard.getCell(i, j).deleteElement("Bomb");
					
					
					
						int counter2 = 1;
						boolean right2 = true;
						boolean left2 = true;
						boolean down2 = true;
						boolean up2 = true;
						while(counter2 <=gameBoard.getBomberMan().getBombRange()){
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
						
						this.killBomberman1(i, j);
						this.destroyEnemies(i, j);
					    this.destroyBombsAround(i, j);
						 
					
					 
				 
		 }
	 }
}}}}
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
	while(counter1 <= gameBoard.getBomberMan().getBombRange()){
		// checking the right range of the bomb; if bomberman is present and there is no concrete walls at bomb range
		if(j +counter1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right == true ){
			if(j +counter1  < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right == true){
				if(this.gameBoard.getCell(i, j+counter1).searcHasAConcreteWall()== true){right = false;}
			}}
			if(right == true && j +counter1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES  && this.gameBoard.getCell(i, j+counter1).searcHasAConcreteWall() == false){
			    if(this.gameBoard.getCell(i, j + counter1).getHasABomb()){
			    
			    }
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
		while(counter1 <= gameBoard.getBomberMan().getBombRange()){
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
	public void destroyEnemies(int i, int j){
		boolean up = true;
		boolean down = true;
		boolean right = true;
		boolean left = true;
	    ArrayList<Enemy> killedEnemies=new ArrayList<Enemy>();
		
		int counter1 = 1;
		while(counter1 <= CONSTANTS.BOMB_RANGE1){
			// checking the right range of the bomb; if a brick is present and there is no concrete walls at bomb range
			if(j +counter1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right == true ){
					if(this.gameBoard.getCell(i, j+counter1).searcHasAConcreteWall()== true){right = false;}
				}
				if(right == true && j +counter1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES  && this.gameBoard.getCell(i, j+counter1).searcHasAConcreteWall() == false){
					if(this.gameBoard.getCell(i, j+ counter1).isThereAnEnemy() == true){
						System.out.println("DESTROYING Enemy");
						for(int first=0;first<this.gameBoard.getCell(i, j+ counter1).getEnemies().size();first++)
						{
							killedEnemies.add(this.gameBoard.getCell(i, j+ counter1).getEnemies().get(first));
						}
						this.gameBoard.getCell(i, j+ counter1).deleteEnemies();
						}			
					
		         	}
			
			if(j +counter1 + 1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right == true){
				if(this.gameBoard.getCell(i, j+counter1+1).searcHasAConcreteWall()== true){right = false;}}
			// checking the left range of the bomb; if a brick is present and there is no concrete walls at bomb range
			if(j -counter1 >=0 && left == true ){
					if(this.gameBoard.getCell(i, j-counter1).searcHasAConcreteWall()== true){left = false;}
				}
				if(left == true && j -counter1 >=0  && this.gameBoard.getCell(i, j-counter1).searcHasAConcreteWall() == false){
					if(this.gameBoard.getCell(i, j- counter1).isThereAnEnemy() == true){
						System.out.println("DESTROYING Enemy");
						for(int first=0;first<this.gameBoard.getCell(i, j- counter1).getEnemies().size();first++)
						{
							killedEnemies.add(this.gameBoard.getCell(i, j- counter1).getEnemies().get(first));
						}
						this.gameBoard.getCell(i,  j- counter1).deleteEnemies();}
									
			}
			
			if(j -counter1 - 1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && left == true){
				if(this.gameBoard.getCell(i, j-counter1-1).searcHasAConcreteWall()== true){left = false;}}
			// checking the south range of the bomb;if a brick is present and there is no concrete walls at range
			if(i +counter1 < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down == true ){
					if(this.gameBoard.getCell(i + counter1, j).searcHasAConcreteWall()== true){down = false;}
				}
				if(down == true && i +counter1 < CONSTANTS.NUMBER_OF_VERTICAL_TILES  && this.gameBoard.getCell(i+counter1, j).searcHasAConcreteWall() == false){
					if(this.gameBoard.getCell(i +counter1,j).isThereAnEnemy() == true){
						System.out.println("DESTROYING Enemy");
						for(int first=0;first<this.gameBoard.getCell(i+counter1, j).getEnemies().size();first++)
						{
							killedEnemies.add(this.gameBoard.getCell(i+counter1, j).getEnemies().get(first));
						}
						this.gameBoard.getCell(i+counter1, j).deleteEnemies();}
					
					
			}
			
			if(i +counter1 + 1 < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down == true){
				if(this.gameBoard.getCell(i + counter1 + 1, j).searcHasAConcreteWall()== true){down = false;}}
			
			// checking the north range of the bomb;if a brick is present and there is no concrete walls at range
			
			if(i -counter1 >= 0 && up == true ){
				if(i - counter1  >= 0 && up == true){
					if(this.gameBoard.getCell(i - counter1, j).searcHasAConcreteWall()== true){up = false;}
				}}
				if(up == true && i -counter1 >= 0  && this.gameBoard.getCell(i-counter1, j).searcHasAConcreteWall() == false){
					if(this.gameBoard.getCell(i-counter1,j).isThereAnEnemy() == true){
						System.out.println("DESTROYING Enemy");
						for(int first=0;first<this.gameBoard.getCell(i-counter1,j).getEnemies().size();first++)
						{
							killedEnemies.add(this.gameBoard.getCell(i-counter1, j).getEnemies().get(first));
						}
						this.gameBoard.getCell(i-counter1, j).deleteEnemies();}
					
					
			}
			
			if(i -counter1 - 1 >= 0 && up == true){
				if(this.gameBoard.getCell(i - counter1 - 1, j).searcHasAConcreteWall()== true){up = false;}}
			
				
			
			counter1++;
		}
		this.gameBoard.getScore().calculateMyScore(killedEnemies);
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
		this.gameBoard.getBomberMan().removeBomb(jCell*CONSTANTS.TILE_SIDE_SIZE, iCell*CONSTANTS.TILE_SIDE_SIZE);
		
		 }
		
	}
	
	/*
	 * Drops a  bomb that is  going to be detonated
	 */
	public void dropBombDetonator(){
		 if(this.gameBoard.getBomberMan().getIsAlive() && this.gameBoard.getBomberMan().getHasDetonator()&& this.gameBoard.getBomberMan().getNumBombsToDrop() >= this.gameBoard.getBomberMan().getQteOfBombsDropped()){
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
   
   public void destroyBombsAround(int i, int j){
	  
	   boolean up = true;
		boolean down = true;
		boolean right = true;
		boolean left = true;
		
		
		int counter1 = 1;
		while(counter1 <= gameBoard.getBomberMan().getBombRange()){
			// checking the right range of the bomb; if a brick is present and there is no concrete walls at bomb range
			if(j +counter1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right == true ){
				if(j +counter1  < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right == true){
					if(this.gameBoard.getCell(i, j+counter1).searcHasAConcreteWall()== true){right = false;}
				}}
				if(right == true && j +counter1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES  && this.gameBoard.getCell(i, j+counter1).searcHasAConcreteWall() == false){
					if(this.gameBoard.getCell(i, j+ counter1).getHasABomb() == true){
						System.out.println("DESTROYING BOMB AROUNd");
						 if(this.gameBoard.getCell(i, j +counter1).getHasADetonateBomb() == false){
						       this.gameBoard.getCell(i, j+counter1).searchBomb().setExplodeFast(true);
						   // to complete with power ups and limited numebr of bombs placed..
						   }
						
						}
					 if(this.gameBoard.getCell(i, j +counter1).getHasADetonateBomb() == true){
					       this.gameBoard.getCell(i, j+counter1).searchBomb().setExplodeFast(true);
					       this.gameBoard.getCell(i, j + counter1).setHasADetonatorBomb(false);
							this.gameBoard.getBomberMan().removeBomb((j+counter1)*CONSTANTS.TILE_SIDE_SIZE, (i)*CONSTANTS.TILE_SIDE_SIZE);
					   // to complete with power ups and limited numebr of bombs placed..
					   }
					
		         	}
			
			if(j +counter1 + 1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && right == true){
				if(this.gameBoard.getCell(i, j+counter1+1).searcHasAConcreteWall()== true){right = false;}}
			// checking the left range of the bomb; if a brick is present and there is no concrete walls at bomb range
			if(j -counter1 >=0 && left == true ){
				if(j -counter1  >=0 && left == true){
					if(this.gameBoard.getCell(i, j-counter1).searcHasAConcreteWall()== true){left = false;}
				}}
				if(left == true && j -counter1 >=0  && this.gameBoard.getCell(i, j-counter1).searcHasAConcreteWall() == false){
					 if(this.gameBoard.getCell(i, j-counter1).getHasADetonateBomb() == false){
					       this.gameBoard.getCell(i, j-counter1).searchBomb().setExplodeFast(true);
					   // to complete with power ups and limited numebr of bombs placed..
					   }
					 if(this.gameBoard.getCell(i, j -counter1).getHasADetonateBomb() == true){
					       this.gameBoard.getCell(i, j-counter1).searchBomb().setExplodeFast(true);
					       this.gameBoard.getCell(i, j +-counter1).setHasADetonatorBomb(false);
							this.gameBoard.getBomberMan().removeBomb((j-counter1)*CONSTANTS.TILE_SIDE_SIZE, (i)*CONSTANTS.TILE_SIDE_SIZE);
					   // to complete with power ups and limited numebr of bombs placed..
					   }
					
									
			}
			
			if(j -counter1 - 1 < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES && left == true){
				if(this.gameBoard.getCell(i, j-counter1-1).searcHasAConcreteWall()== true){left = false;}}
			// checking the south range of the bomb;if a brick is present and there is no concrete walls at range
			if(i +counter1 < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down == true ){
				if(i +counter1  < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down == true){
					if(this.gameBoard.getCell(i + counter1, j).searcHasAConcreteWall()== true){down = false;}
				}}
				if(down == true && i +counter1 < CONSTANTS.NUMBER_OF_VERTICAL_TILES  && this.gameBoard.getCell(i+counter1, j).searcHasAConcreteWall() == false){
					 if(this.gameBoard.getCell(i+counter1, j).getHasADetonateBomb() == false){
					       this.gameBoard.getCell(i+counter1, j).searchBomb().setExplodeFast(true);
					   // to complete with power ups and limited numebr of bombs placed..
					   }
					 if(this.gameBoard.getCell(i+counter1, j).getHasADetonateBomb() == true){
					       this.gameBoard.getCell(i+counter1, j).searchBomb().setExplodeFast(true);
					       this.gameBoard.getCell(i+counter1, j).setHasADetonatorBomb(false);
							this.gameBoard.getBomberMan().removeBomb(j*CONSTANTS.TILE_SIDE_SIZE, (i+counter1)*CONSTANTS.TILE_SIDE_SIZE);
					   // to complete with power ups and limited numebr of bombs placed..
					   }
					
					
					
			}
			
			if(i +counter1 + 1 < CONSTANTS.NUMBER_OF_VERTICAL_TILES && down == true){
				if(this.gameBoard.getCell(i + counter1 + 1, j).searcHasAConcreteWall()== true){down = false;}}
			
			// checking the north range of the bomb;if a brick is present and there is no concrete walls at range
			
			if(i -counter1 >= 0 && up == true ){
				if(i - counter1  >= 0 && up == true){
					if(this.gameBoard.getCell(i - counter1, j).searcHasAConcreteWall()== true){up = false;}
				}}
				if(up == true && i -counter1 >= 0  && this.gameBoard.getCell(i-counter1, j).searcHasAConcreteWall() == false){
					 if(this.gameBoard.getCell(i-counter1, j).getHasADetonateBomb() == false){
					       this.gameBoard.getCell(i-counter1, j).searchBomb().setExplodeFast(true);
					   // to complete with power ups and limited numebr of bombs placed..
					   }
					 if(this.gameBoard.getCell(i-counter1, j).getHasADetonateBomb() == true){
					       this.gameBoard.getCell(i-counter1, j).searchBomb().setExplodeFast(true);
					       this.gameBoard.getCell(i-counter1, j).setHasADetonatorBomb(false);
							this.gameBoard.getBomberMan().removeBomb(j*CONSTANTS.TILE_SIDE_SIZE, (i-counter1)*CONSTANTS.TILE_SIDE_SIZE);
					   // to complete with power ups and limited numebr of bombs placed..
					   }
					
			}
			
			if(i -counter1 - 1 >= 0 && up == true){
				if(this.gameBoard.getCell(i - counter1 - 1, j).searcHasAConcreteWall()== true){up = false;}}
			
				
			
			counter1++;
		}
		
   
   }
   public void deleteInvisibilityPowerUp(){
	 if(this.gameBoard.getBomberMan().getInvisibilibityPowerUp()){
		if( this.gameBoard.getBomberMan().getCreationInvisibilityPowerUp() <= this.gameBoard.getBomberMan().getEliminationInvisibilityPowerUp() ){
			this.gameBoard.getBomberMan().addTimeCreationInvisibilitPowerUp();
			System.out.println("TIME ELIMINATION " +  this.gameBoard.getBomberMan().getCreationInvisibilityPowerUp() );
			System.out.println("TIME CREATION " +  this.gameBoard.getBomberMan().getEliminationInvisibilityPowerUp() );
			
		}
		else if(this.gameBoard.getBomberMan().getCreationInvisibilityPowerUp() > this.gameBoard.getBomberMan().getEliminationInvisibilityPowerUp()){
	       this.gameBoard.getBomberMan().setInvisibilityPowerUp(false);
		}}}
   
   

public GameBoard getBoard(){return this.gameBoard;}
   
}
