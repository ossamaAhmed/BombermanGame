package bomberMan.gamePlay.Controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import bomberMan.gamePlay.Model.CONSTANTS;
import bomberMan.gamePlay.Model.Cell;
import bomberMan.gamePlay.Model.Enemy;
import bomberMan.gamePlay.Model.GameBoard;
import bomberMan.gamePlay.Model.GameObject;
import bomberMan.gamePlay.Model.HighIntellegenceEnemy;
import bomberMan.gamePlay.Model.MediumIntelligenceEnemy;

public class EnemyController extends CharacterController
{
	GameBoard myGameBoard;
	public EnemyController(GameBoard  board)
	{
		super(board);
		this.myGameBoard=board;
	}
    public void run()
    {
    	for(int i=0;i<this.myGameBoard.getEnemies().size();i++)
		{
			if(!(this.myGameBoard.getEnemies().get(i)).getIsAlive())
				this.myGameBoard.getEnemies().remove(i);
		}
    	for(int i=0;i<this.myGameBoard.getEnemies().size();i++)
    		{
    				synchronized(this.myGameBoard.getCell(this.myGameBoard.getEnemies().get(i).getPositionY()/CONSTANTS.TILE_SIDE_SIZE, this.myGameBoard.getEnemies().get(i).getPositionX()/CONSTANTS.TILE_SIDE_SIZE))
    				{
    					moveEnemy(this.myGameBoard.getEnemies().get(i),this.myGameBoard.getEnemies().get(i).hasWallPass());		
    				}
    		}
    }
	 public void moveEnemy(Enemy enemy, boolean wallPass) 
	 {
		 if(enemy instanceof HighIntellegenceEnemy)
		 {
			 highAI(enemy);
		 }
		 if(enemy instanceof MediumIntelligenceEnemy)
		 {
			 medAI(enemy);
		 }
		 
		 String collidingObject1="CONCRETE";
		 String collidingObject3="BRICK";
	     String collidingObject2="BOMB";
			 if(enemy.getExpectedMovmentDirection()==2 && !(checkCollision(enemy, 2,collidingObject1))&& !(checkCollision(enemy, 2,collidingObject2)) )
			 {
				 if(wallPass)
				 { 
					 this.myGameBoard.getCell(enemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, enemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE).getObjects().remove(enemy);
					 enemy.moveRight(enemy.getSpeed());
					 this.myGameBoard.getCell(enemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, enemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE).getObjects().add(enemy);
				 }
				 else if(!(checkCollision(enemy, 2,collidingObject3)))
				 {
					 this.myGameBoard.getCell(enemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, enemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE).getObjects().remove(enemy);
					 enemy.moveRight(enemy.getSpeed());
					 this.myGameBoard.getCell(enemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, enemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE).getObjects().add(enemy);
				 }
				 else
				 {
					 this.myGameBoard.getCell(enemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, enemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE).getObjects().remove(enemy);
					 enemy.changeDirection();
					 this.myGameBoard.getCell(enemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, enemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE).getObjects().add(enemy);
				 }
			 }
			 else if(enemy.getExpectedMovmentDirection()==1 && !(checkCollision(enemy, 1,collidingObject1))&&!(checkCollision(enemy, 1,collidingObject2)) )
			 {
				 if(wallPass)
				 {
					 this.myGameBoard.getCell(enemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, enemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE).getObjects().remove(enemy);
					 enemy.moveLeft(enemy.getSpeed());
					 this.myGameBoard.getCell(enemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, enemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE).getObjects().add(enemy);
				 }
				 else if(!(checkCollision(enemy, 1,collidingObject3)))
				 {
					 this.myGameBoard.getCell(enemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, enemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE).getObjects().remove(enemy);
					 enemy.moveLeft(enemy.getSpeed());
					 this.myGameBoard.getCell(enemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, enemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE).getObjects().add(enemy);
				 }
				 else
				 {
					 this.myGameBoard.getCell(enemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, enemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE).getObjects().remove(enemy);
					 enemy.changeDirection();
					 this.myGameBoard.getCell(enemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, enemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE).getObjects().add(enemy);
				 }
			
			 }
			 else if(enemy.getExpectedMovmentDirection()==4 && !(checkCollision(enemy, 4,collidingObject1))&&!(checkCollision(enemy, 4,collidingObject2)))
			 {
				 if(wallPass)
				 {
					 
					 this.myGameBoard.getCell(enemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, enemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE).getObjects().remove(enemy);
					 enemy.moveDown(enemy.getSpeed());
					 this.myGameBoard.getCell(enemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, enemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE).getObjects().add(enemy);
				 }
				 else if(!(checkCollision(enemy, 4,collidingObject3)))
				 {
					 this.myGameBoard.getCell(enemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, enemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE).getObjects().remove(enemy);
					 enemy.moveDown(enemy.getSpeed());
					 this.myGameBoard.getCell(enemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, enemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE).getObjects().add(enemy);
				 }
				 else
				 {
					 this.myGameBoard.getCell(enemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, enemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE).getObjects().remove(enemy);
					 enemy.changeDirection();
					 this.myGameBoard.getCell(enemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, enemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE).getObjects().add(enemy);
				 }
			 }
			 else  if(enemy.getExpectedMovmentDirection()==3 && !(checkCollision(enemy, 3,collidingObject1))&&!(checkCollision(enemy, 3,collidingObject2)))
			 {
				 if(wallPass)
				 {
					 this.myGameBoard.getCell(enemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, enemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE).getObjects().remove(enemy);
					 enemy.moveUp(enemy.getSpeed());
					 this.myGameBoard.getCell(enemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, enemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE).getObjects().add(enemy);
				 }
				 else if(!(checkCollision(enemy, 3,collidingObject3)))
				 {
					 this.myGameBoard.getCell(enemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, enemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE).getObjects().remove(enemy);
					 enemy.moveUp(enemy.getSpeed());
					 this.myGameBoard.getCell(enemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, enemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE).getObjects().add(enemy);
				 }
				 else
				 {
					 this.myGameBoard.getCell(enemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, enemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE).getObjects().remove(enemy);
					 enemy.changeDirection();
					 this.myGameBoard.getCell(enemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, enemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE).getObjects().add(enemy);
				 }
			 }
			 else
			 {
				 this.myGameBoard.getCell(enemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, enemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE).getObjects().remove(enemy);
				 enemy.changeDirection();
				 this.myGameBoard.getCell(enemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, enemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE).getObjects().add(enemy);
			 }
			 if((enemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE)==(this.myGameBoard.getBomberMan().getPositionY()/CONSTANTS.TILE_SIDE_SIZE) &&(enemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE)==(this.myGameBoard.getBomberMan().getPositionX()/CONSTANTS.TILE_SIDE_SIZE))
				 this.myGameBoard.getBomberMan().die();
	 }
	 private void medAI(Enemy myEnemy) {

			int ran = myEnemy.getMovmentDirection();
			Random random = new Random();
			boolean enemyAtEdgeOfCell=(myEnemy.getPositionX())==((myEnemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE)*CONSTANTS.TILE_SIDE_SIZE)
					&&(myEnemy.getPositionY())==((myEnemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE)*CONSTANTS.TILE_SIDE_SIZE);

			if (bomberManInRangeMedAI(myEnemy) == true && this.myGameBoard.getBomberMan().getIsAlive()&&enemyAtEdgeOfCell&&!this.myGameBoard.getBomberMan().getInvisibilibityPowerUp()) 
			{
				myEnemy.setExpectedMovmentDirection(medAIchase(myEnemy)); ;
			} else if (((myEnemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE) % 2 == 1 )&& ((myEnemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE) % 2 == 1)&&enemyAtEdgeOfCell) 
			{
				ran= random.nextInt(10);
				if (ran == 1) 
				{
					System.out.println(ran);
					 ran = myEnemy.getMovmentDirection();
					while (ran == myEnemy.getMovmentDirection()) 
					{
						ran = random.nextInt(4) + 1;
					}
					myEnemy.setExpectedMovmentDirection(ran);
				}
			}
		}
	 
		private int medAIchase(Enemy myEnemy) 
		{
			int dY = (this.myGameBoard.getBomberMan().getPositionY()/CONSTANTS.TILE_SIDE_SIZE)- (myEnemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE);
			int dX = (this.myGameBoard.getBomberMan().getPositionX()/CONSTANTS.TILE_SIDE_SIZE)- (myEnemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE);
			boolean enemyAtEdgeOfCell=(myEnemy.getPositionX())==((myEnemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE)*CONSTANTS.TILE_SIDE_SIZE)
										&&(myEnemy.getPositionY())==((myEnemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE)*CONSTANTS.TILE_SIDE_SIZE);
			if (dY < 0&&enemyAtEdgeOfCell)
				return CONSTANTS.UP;
			if (dY > 0&&enemyAtEdgeOfCell)
				return CONSTANTS.DOWN;
			if (dX < 0&&enemyAtEdgeOfCell)
				return CONSTANTS.LEFT;
			else if(dX > 0&&enemyAtEdgeOfCell)
				return CONSTANTS.RIGHT;
			else
				return myEnemy.getMovmentDirection();
		}
		private boolean bomberManInRangeMedAI(Enemy myEnemy) {
			int dX = Math.abs((myEnemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE) - (this.myGameBoard.getBomberMan().getPositionX()/CONSTANTS.TILE_SIDE_SIZE));
			int dY = Math.abs((myEnemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE) - (this.myGameBoard.getBomberMan().getPositionY()/CONSTANTS.TILE_SIDE_SIZE));

			if (dX + dY <= 1) {
				return true;
			}
			return false;
		}
		
		private void highAI(Enemy myEnemy) {
			 
	        int ran = myEnemy.getMovmentDirection();
	        Random random = new Random();
	        
	        boolean enemyAtEdgeOfCell=(myEnemy.getPositionX())==((myEnemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE)*CONSTANTS.TILE_SIDE_SIZE)
					&&(myEnemy.getPositionY())==((myEnemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE)*CONSTANTS.TILE_SIDE_SIZE);
	        
	        if (bomberManInRangeHighAI(myEnemy) == true && this.myGameBoard.getBomberMan().getIsAlive()&&enemyAtEdgeOfCell&&!this.myGameBoard.getBomberMan().getInvisibilibityPowerUp()) {
	            System.out.println("entering astar");
	        	int moves[] = aStar( myEnemy);
	        	System.out.println("exiting astar");
	        	if(moves != null)
	        		myEnemy.setExpectedMovmentDirection(moves[0]);
	            
	        } else if ((myEnemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE) % 2 == 1 && (myEnemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE) % 2 == 1&&enemyAtEdgeOfCell) {
	            if (random.nextInt(10) >= 5) {
	            	myEnemy.setExpectedMovmentDirection(myEnemy.getExpectedMovmentDirection());
	            }
	            else{
	            while (ran == myEnemy.getExpectedMovmentDirection()) {
	                ran = random.nextInt(4) + 1;
	            }
	            myEnemy.setExpectedMovmentDirection(ran);
	            }
	        } else {
	        	myEnemy.setExpectedMovmentDirection(myEnemy.getExpectedMovmentDirection());
	        }
	    }
	 
	    public boolean bomberManInRangeHighAI(Enemy myEnemy) 
	    {
	 
	    	int dX = Math.abs((myEnemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE) - (this.myGameBoard.getBomberMan().getPositionX()/CONSTANTS.TILE_SIDE_SIZE));
			int dY = Math.abs((myEnemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE) - (this.myGameBoard.getBomberMan().getPositionY()/CONSTANTS.TILE_SIDE_SIZE));
	        if ((dX + dY) <= 2) 
	        {
	            return true;
	        } 
	        return false;
	    }

	public int[] aStar(Enemy myEnemy) {
		ArrayList<Cell> openSet = new ArrayList<Cell>();
		ArrayList<Cell> closedSet = new ArrayList<Cell>();
		ArrayList<Cell> obstacles = new ArrayList<Cell>();
		Cell myCell = this.myGameBoard.getCell(myEnemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, myEnemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE);
		myCell.setParent(null);
		openSet.add(myCell);
		Cell successor = null;
		this.myGameBoard.getCell(myEnemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, myEnemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE).setGscore(0);
		this.myGameBoard.getCell(myEnemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, myEnemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE).setFscore(manhattan(myEnemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, myEnemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE));

		openSet.add(this.myGameBoard.getCell(myEnemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, myEnemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE));
		while (openSet.size() != 0)
		{
			myCell = getMinCell(openSet);
			//myCell.setParent(null);
			System.out.println(myCell.getX() + ", " + myCell.getY());
			openSet.remove(myCell);
			closedSet.add(myCell);

			for (int i = 0; i < 4; i++) {
				successor = null;
				if (i == 0) {
					if (myCell.getX() + 1 <= 30) {
						successor = this.myGameBoard.getCell(myCell.getY(),
								myCell.getX()+1);
						successor.setGscore(myCell.getGScore() + 1);
						successor.setFscore(manhattan(myCell.getX() + 1,
								myCell.getY()));
					}

				}
				if (i == 1) {
					if (myCell.getX() - 1 >= 0) {
						successor = this.myGameBoard.getCell(myCell.getY(),
								myCell.getX()-1);
						successor.setGscore(myCell.getGScore() + 1);
						successor.setFscore(manhattan(myCell.getX() - 1,
								myCell.getY()));
					}
				}
				if (i == 2) {
					if (myCell.getY() + 1 <= 12) {
						successor = this.myGameBoard.getCell(myCell.getY()+1,
								myCell.getX());
						successor.setGscore(myCell.getGScore() + 1);
						successor.setFscore(manhattan(myCell.getX(),
								myCell.getY() + 1));
					}

				}
				if (i == 3) {
					if (myCell.getY() - 1 >= 0) {
						successor = this.myGameBoard.getCell(myCell.getY()-1,
								myCell.getX());
						successor.setGscore(myCell.getGScore() + 1);
						successor.setFscore(manhattan(myCell.getX(),
								myCell.getY() - 1));
					}
				}

				if (successor != null) {
					if (getObstacle(successor, myEnemy) == true
							&& obstacles.contains(successor) == false) {
						obstacles.add(successor);
					}

					if (obstacles.contains(successor) == false
							&& openSet.contains(successor) == false
							&& closedSet.contains(successor) == false
							&& successor != null) {
						System.out.println("seeting parent of "+successor.getX()+ " Y:"+successor.getY());
						successor.setParent(myCell);
						//System.out.println(successor.getX()+" "+successor.getY());
						
						openSet.add(successor);
					}
					if (successor.getX() == (this.myGameBoard.getBomberMan().getPositionX()/CONSTANTS.TILE_SIDE_SIZE) && successor.getY() == (this.myGameBoard.getBomberMan().getPositionY()/CONSTANTS.TILE_SIDE_SIZE)) {
						//System.out.println(successor.getX() + ", "+ successor.getY());
						System.out.println(successor.getX()+" "+successor.getY());
						successor.setParent(myCell);
						openSet.add(successor);
					}
					if (successor.getX() == (this.myGameBoard.getBomberMan().getPositionX()/CONSTANTS.TILE_SIDE_SIZE) && successor.getY() == (this.myGameBoard.getBomberMan().getPositionY()/CONSTANTS.TILE_SIDE_SIZE)) {
						System.out.println("a"+successor.getX() + ", "+ successor.getY());
						System.out.println("b" + successor.getParent().getX() +" "+successor.getParent().getY());
						successor.setParent(myCell);
						return returnRoute(successor);
					}
				}

			}

		}
		return null;
	}

	private  int[] returnRoute(Cell cell) {
		Cell currentCell = cell;
		Cell parent = currentCell.getParent();
		Stack<Integer> temp = new Stack<Integer>();
		int counter = 0;
		while (parent != null) {
			int dX = currentCell.getX() - parent.getX();
			int dY = currentCell.getY() - parent.getY();
			System.out.println(currentCell.getX() + ", " + currentCell.getY() +": "+ dX +"," +dY);
			if (dX == 1)
				temp.push(CONSTANTS.RIGHT);
			if (dX == -1)
				temp.push(CONSTANTS.LEFT);
			if (dY == 1)
				temp.push(CONSTANTS.DOWN);
			if (dY == -1)
				temp.push(CONSTANTS.UP);

			currentCell = parent;
			parent = currentCell.getParent();
		}
		int route[] = new int[temp.size()];
		for (int i = 0; i < route.length; i++) {
			
			route[i] = (int) temp.pop();
			System.out.println(route[i]+" ");
		}
		System.out.println(route[0]);
		return route;
	}

	private  Cell getMinCell(ArrayList<Cell> openSet) {
		Cell smallest = openSet.get(0);
		for (int i = 1; i < openSet.size(); i++) {
			if (openSet.get(i).getFscore() < smallest.getFscore())
				smallest = openSet.get(i);
		}

		return smallest;

	}

	private  boolean getObstacle(Cell cell, Enemy myEnemy) {
		// 1,2,3 are bricks, concrete, bombs. wallpass 0 is no wall pass
		ArrayList<GameObject> object = cell.getObjects();
		if (myEnemy.hasWallPass() == false && (cell.searcHasAConcreteWall() || cell.searcHasABrickWall() || cell.getHasABomb())) {
			return true;
		} else if (myEnemy.hasWallPass() && ( cell.searcHasAConcreteWall() || cell.getHasABomb())) {
			return true;
		}
		return false;
	}

	private  int manhattan(int cellPosX, int cellPosY) {
		int dX = Math.abs(cellPosX -(this.myGameBoard.getBomberMan().getPositionX()/CONSTANTS.TILE_SIDE_SIZE) );
		int dY = Math.abs(cellPosY - (this.myGameBoard.getBomberMan().getPositionY()/CONSTANTS.TILE_SIDE_SIZE));
		return (dY + dX);
	}


}
