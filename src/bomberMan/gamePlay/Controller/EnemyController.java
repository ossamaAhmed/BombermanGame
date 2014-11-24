package bomberMan.gamePlay.Controller;

import java.util.Random;

import bomberMan.gamePlay.Model.CONSTANTS;
import bomberMan.gamePlay.Model.Enemy;
import bomberMan.gamePlay.Model.GameBoard;
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
	 
		public int medAIchase(Enemy myEnemy) 
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

}
