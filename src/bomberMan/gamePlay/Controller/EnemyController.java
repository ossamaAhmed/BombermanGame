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
			if(!((Enemy) this.myGameBoard.getEnemies().get(i)).getIsAlive())
				this.myGameBoard.getEnemies().remove(i);
		}
    	for(int i=0;i<this.myGameBoard.getEnemies().size();i++)
    		{
    				moveEnemy(this.myGameBoard.getEnemies().get(i),this.myGameBoard.getEnemies().get(i).hasWallPass());		
    		}
    }
	 public void moveEnemy(Enemy enemy, boolean wallPass) 
	 {
		 if(enemy instanceof HighIntellegenceEnemy)
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
	            int moves[] = aStar(myEnemy.getPositionX(), myEnemy.getPositionY(), this.myGameBoard.getBomberMan().getPositionX()/CONSTANTS.TILE_SIDE_SIZE, this.myGameBoard.getBomberMan().getPositionY()/CONSTANTS.TILE_SIDE_SIZE, myEnemy);
	            myEnemy.setExpectedMovmentDirection(moves[0]);
	            
	        } else if (myEnemy.getPositionX() % 2 == 1 && myEnemy.getPositionY() % 2 == 1) {
	            if (random.nextInt(10) >= 5) {
	            	myEnemy.setExpectedMovmentDirection(myEnemy.getExpectedMovmentDirection());
	            }
	            while (ran == myEnemy.getExpectedMovmentDirection()) {
	                ran = random.nextInt(4) + 1;
	            }
	            myEnemy.setExpectedMovmentDirection(ran);
	        } else {
	        	myEnemy.setExpectedMovmentDirection(myEnemy.getExpectedMovmentDirection());
	        }
	    }
	 
	    public boolean bomberManInRangeHighAI(Enemy myEnemy) {
	 
	    	int dX = Math.abs((myEnemy.getPositionX()/CONSTANTS.TILE_SIDE_SIZE) - (this.myGameBoard.getBomberMan().getPositionX()/CONSTANTS.TILE_SIDE_SIZE));
			int dY = Math.abs((myEnemy.getPositionY()/CONSTANTS.TILE_SIDE_SIZE) - (this.myGameBoard.getBomberMan().getPositionY()/CONSTANTS.TILE_SIDE_SIZE));
	        if ((dX == 0 || dY == 0) && ((dX + dY) <= 2)) {
	            return true;
	        } else if (Math.sqrt(dX * dX + dY * dY) <= 2) {
	            return true;
	        }
	        return false;
	    }

	public int[] aStar(int eX, int eY, int bX, int bY, Enemy myEnemy) {
		ArrayList<Cell> openSet = new ArrayList<Cell>();
		ArrayList<Cell> closedSet = new ArrayList<Cell>();
		ArrayList<Cell> obstacles = new ArrayList<Cell>();
		Cell myCell = null;
		Cell successor = null;
		this.myGameBoard.getCell(eX, eY).setGscore(0);
		this.myGameBoard.getCell(eX, eY).setFscore(manhattan(eX, eY, bX, bY));

		openSet.add(this.myGameBoard.getCell(eX, eY));

		while (openSet.size() != 0) {
			myCell = getMinCell(openSet);
			//System.out.println(myCell.getX() + ", " + myCell.getY());
			openSet.remove(myCell);
			closedSet.add(myCell);

			for (int i = 0; i < 4; i++) {
				successor = null;
				if (i == 0) {
					if (myCell.getX() + 1 <= 14) {
						successor = this.myGameBoard.getCell(myCell.getX() + 1,
								myCell.getY());
						successor.setGscore(myCell.getGScore() + 1);
						successor.setFscore(manhattan(myCell.getX() + 1,
								myCell.getY(), bX, bY));
					}

				}
				if (i == 1) {
					if (myCell.getX() - 1 >= 0) {
						successor = this.myGameBoard.getCell(myCell.getX() - 1,
								myCell.getY());
						successor.setGscore(myCell.getGScore() + 1);
						successor.setFscore(manhattan(myCell.getX() - 1,
								myCell.getY(), bX, bY));
					}
				}
				if (i == 2) {
					if (myCell.getY() + 1 <= 13) {
						successor = this.myGameBoard.getCell(myCell.getX(),
								myCell.getY() + 1);
						successor.setGscore(myCell.getGScore() + 1);
						successor.setFscore(manhattan(myCell.getX(),
								myCell.getY() + 1, bX, bY));
					}

				}
				if (i == 3) {
					if (myCell.getY() - 1 >= 0) {
						successor = this.myGameBoard.getCell(myCell.getX(),
								myCell.getY() - 1);
						successor.setGscore(myCell.getGScore() + 1);
						successor.setFscore(manhattan(myCell.getX(),
								myCell.getY() - 1, bX, bY));
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
						successor.setParent(myCell);
						System.out.println(successor.getX()+" "+successor.getY());
						openSet.add(successor);
					}
					if (successor.getX() == bX && successor.getY() == bY) {
						//System.out.println(successor.getX() + ", "+ successor.getY());
						return returnRoute(successor);
					}
				}

			}

		}
		return null;
	}

	private static int[] returnRoute(Cell cell) {
		Cell currentCell = cell;
		Cell parent = currentCell.getParent();
		Stack<Integer> temp = new Stack<Integer>();
		int counter = 0;
		while (parent != null) {
			counter++;
			int dX = currentCell.getX() - parent.getX();
			int dY = currentCell.getY() - parent.getY();
			//System.out.println(dX + ", " + dY +" "+ counter);
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
		}
		return route;
	}

	private static Cell getMinCell(ArrayList<Cell> openSet) {
		Cell smallest = openSet.get(0);
		for (int i = 1; i < openSet.size(); i++) {
			if (openSet.get(i).getFscore() < smallest.getFscore())
				smallest = openSet.get(i);
		}

		return smallest;

	}

	private static boolean getObstacle(Cell cell, Enemy myEnemy) {
		// 1,2,3 are bricks, concrete, bombs. wallpass 0 is no wall pass
		ArrayList<GameObject> object = cell.getObjects();
		if (myEnemy.hasWallPass() == false && (object.contains("brick") || object.contains("brick") || object.contains("brick"))) {
			return true;
		} else if (myEnemy.hasWallPass() && ( object.contains("brick") || object.contains("brick"))) {
			return true;
		}
		return false;
	}

	private static int manhattan(int ePosX, int ePosY, int bManPosX,
			int bManPosY) {
		int dX = Math.abs(ePosX - bManPosX);
		int dY = Math.abs(ePosY - bManPosY);
		return (dY + dX);
	}


}
