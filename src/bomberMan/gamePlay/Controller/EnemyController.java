/* 
 * File: EnemyController.java
 * -----------------------
 * This Class represents the control of any enemy on screen. Mainly for movement 
 * and detecting collisions
 */
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

public class EnemyController extends CharacterController {
	/*Instance variables*/
	GameBoard myGameBoard;
	/** 
	 * Constructor
	 * This method takes care of the passing of the game board to the controller to have
	 * access to it
	 * @param board is the game board that the controller will use
	 */
	public EnemyController(GameBoard board) {
		super(board);
		this.myGameBoard = board;
	}
	/** 
	 * This method takes care of the moving all the enmies represented in the game board currently
	 * It will be called on a reqgular basis
	 */
	public void run() {
		for (int i = 0; i < this.myGameBoard.getEnemies().size(); i++) {
			if (!(this.myGameBoard.getEnemies().get(i)).getIsAlive())
				this.myGameBoard.getEnemies().remove(i);
		}
		for (int i = 0; i < this.myGameBoard.getEnemies().size(); i++) {
			synchronized (this.myGameBoard.getCell(this.myGameBoard
					.getEnemies().get(i).getPositionY()
					/ CONSTANTS.TILE_SIDE_SIZE, this.myGameBoard.getEnemies()
					.get(i).getPositionX()
					/ CONSTANTS.TILE_SIDE_SIZE)) {
				moveEnemy(this.myGameBoard.getEnemies().get(i),
						this.myGameBoard.getEnemies().get(i).hasWallPass());
			}
		}
	}
	/** 
	 * This method takes care of the moving one enemy on the screen
	 * @param wallPass is true if the enemy had a wall pass and false otehrwise
	 */
	public void moveEnemy(Enemy enemy, boolean wallPass) {
		
		//calculate the next expected movment for a high AI enemy
		if (enemy instanceof HighIntellegenceEnemy) {
			highAI(enemy);
		}
		//calculate the next expected movment for a medium AI enemy
		if (enemy instanceof MediumIntelligenceEnemy) {
			medAI(enemy);
		}

		String collidingObject1 = "CONCRETE";
		String collidingObject3 = "BRICK";
		String collidingObject2 = "BOMB";
		if (enemy.getExpectedMovmentDirection() == 2
				&& !(checkCollision(enemy, 2, collidingObject1))
				&& !(checkCollision(enemy, 2, collidingObject2))) {
			if (wallPass) {
				this.myGameBoard
				.getCell(
						enemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
						enemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
						.getObjects().remove(enemy);
				enemy.moveRight(enemy.getSpeed());
				this.myGameBoard
				.getCell(
						enemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
						enemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
						.getObjects().add(enemy);
			} else if (!(checkCollision(enemy, 2, collidingObject3))) {
				this.myGameBoard
				.getCell(
						enemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
						enemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
						.getObjects().remove(enemy);
				enemy.moveRight(enemy.getSpeed());
				this.myGameBoard
				.getCell(
						enemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
						enemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
						.getObjects().add(enemy);
			} else {
				this.myGameBoard
				.getCell(
						enemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
						enemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
						.getObjects().remove(enemy);
				enemy.changeDirection();
				this.myGameBoard
				.getCell(
						enemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
						enemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
						.getObjects().add(enemy);
			}
		} else if (enemy.getExpectedMovmentDirection() == 1
				&& !(checkCollision(enemy, 1, collidingObject1))
				&& !(checkCollision(enemy, 1, collidingObject2))) {
			if (wallPass) {
				this.myGameBoard
				.getCell(
						enemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
						enemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
						.getObjects().remove(enemy);
				enemy.moveLeft(enemy.getSpeed());
				this.myGameBoard
				.getCell(
						enemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
						enemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
						.getObjects().add(enemy);
			} else if (!(checkCollision(enemy, 1, collidingObject3))) {
				this.myGameBoard
				.getCell(
						enemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
						enemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
						.getObjects().remove(enemy);
				enemy.moveLeft(enemy.getSpeed());
				this.myGameBoard
				.getCell(
						enemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
						enemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
						.getObjects().add(enemy);
			} else {
				this.myGameBoard
				.getCell(
						enemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
						enemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
						.getObjects().remove(enemy);
				enemy.changeDirection();
				this.myGameBoard
				.getCell(
						enemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
						enemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
						.getObjects().add(enemy);
			}

		} else if (enemy.getExpectedMovmentDirection() == 4
				&& !(checkCollision(enemy, 4, collidingObject1))
				&& !(checkCollision(enemy, 4, collidingObject2))) {
			if (wallPass) {

				this.myGameBoard
				.getCell(
						enemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
						enemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
						.getObjects().remove(enemy);
				enemy.moveDown(enemy.getSpeed());
				this.myGameBoard
				.getCell(
						enemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
						enemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
						.getObjects().add(enemy);
			} else if (!(checkCollision(enemy, 4, collidingObject3))) {
				this.myGameBoard
				.getCell(
						enemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
						enemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
						.getObjects().remove(enemy);
				enemy.moveDown(enemy.getSpeed());
				this.myGameBoard
				.getCell(
						enemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
						enemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
						.getObjects().add(enemy);
			} else {
				this.myGameBoard
				.getCell(
						enemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
						enemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
						.getObjects().remove(enemy);
				enemy.changeDirection();
				this.myGameBoard
				.getCell(
						enemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
						enemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
						.getObjects().add(enemy);
			}
		} else if (enemy.getExpectedMovmentDirection() == 3
				&& !(checkCollision(enemy, 3, collidingObject1))
				&& !(checkCollision(enemy, 3, collidingObject2))) {
			if (wallPass) {
				this.myGameBoard
				.getCell(
						enemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
						enemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
						.getObjects().remove(enemy);
				enemy.moveUp(enemy.getSpeed());
				this.myGameBoard
				.getCell(
						enemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
						enemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
						.getObjects().add(enemy);
			} else if (!(checkCollision(enemy, 3, collidingObject3))) {
				this.myGameBoard
				.getCell(
						enemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
						enemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
						.getObjects().remove(enemy);
				enemy.moveUp(enemy.getSpeed());
				this.myGameBoard
				.getCell(
						enemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
						enemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
						.getObjects().add(enemy);
			} else {
				this.myGameBoard
				.getCell(
						enemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
						enemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
						.getObjects().remove(enemy);
				enemy.changeDirection();
				this.myGameBoard
				.getCell(
						enemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
						enemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
						.getObjects().add(enemy);
			}
		} else {
			this.myGameBoard
			.getCell(enemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
					enemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
					.getObjects().remove(enemy);
			enemy.changeDirection();
			this.myGameBoard
			.getCell(enemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
					enemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
					.getObjects().add(enemy);
		}
		if ((enemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE) == (this.myGameBoard
				.getBomberMan().getPositionY() / CONSTANTS.TILE_SIDE_SIZE)
				&& (enemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE) == (this.myGameBoard
						.getBomberMan().getPositionX() / CONSTANTS.TILE_SIDE_SIZE))
			this.myGameBoard.getBomberMan().die();
	}

	/**
	 * Automatically set the movement direction of a medium AI enemy depending
	 * on different scenarios.
	 *
	 * @param myEnemy
	 *            the enemy which will be controlled
	 */
	public void medAI(Enemy myEnemy) {

		int ran = myEnemy.getMovmentDirection();
		Random random = new Random();
		boolean enemyAtEdgeOfCell = (myEnemy.getPositionX()) == ((myEnemy
				.getPositionX() / CONSTANTS.TILE_SIDE_SIZE) * CONSTANTS.TILE_SIDE_SIZE)
				&& (myEnemy.getPositionY()) == ((myEnemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE) * CONSTANTS.TILE_SIDE_SIZE);

		if (bomberManInRangeMedAI(myEnemy) == true
				&& this.myGameBoard.getBomberMan().getIsAlive()
				&& enemyAtEdgeOfCell
				&& !this.myGameBoard.getBomberMan().getInvisibilibityPowerUp()) {
			myEnemy.setExpectedMovmentDirection(medAIchase(myEnemy));
			;
		} else if (((myEnemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE) % 2 == 1)
				&& ((myEnemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE) % 2 == 1)
				&& enemyAtEdgeOfCell) {
			ran = random.nextInt(10);
			if (ran == 1) {
				System.out.println(ran);
				ran = myEnemy.getMovmentDirection();
				while (ran == myEnemy.getMovmentDirection()) {
					ran = random.nextInt(4) + 1;
				}
				myEnemy.setExpectedMovmentDirection(ran);
			}
		}
	}

	/**
	 * Method that will return a direction that will chase bomberman for medium
	 * AI.
	 * 
	 * @param myEnemy
	 *            the enemy that will have its movement altered
	 * @return a constant that corresponds to up, down, left, or right movement.
	 */
	private int medAIchase(Enemy myEnemy) {
		int dY = (this.myGameBoard.getBomberMan().getPositionY() / CONSTANTS.TILE_SIDE_SIZE)
				- (myEnemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE);
		int dX = (this.myGameBoard.getBomberMan().getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
				- (myEnemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE);
		boolean enemyAtEdgeOfCell = (myEnemy.getPositionX()) == ((myEnemy
				.getPositionX() / CONSTANTS.TILE_SIDE_SIZE) * CONSTANTS.TILE_SIDE_SIZE)
				&& (myEnemy.getPositionY()) == ((myEnemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE) * CONSTANTS.TILE_SIDE_SIZE);
		if (dY < 0 && enemyAtEdgeOfCell)
			return CONSTANTS.UP;
		if (dY > 0 && enemyAtEdgeOfCell)
			return CONSTANTS.DOWN;
		if (dX < 0 && enemyAtEdgeOfCell)
			return CONSTANTS.LEFT;
		else if (dX > 0 && enemyAtEdgeOfCell)
			return CONSTANTS.RIGHT;
		else
			return myEnemy.getMovmentDirection();
	}

	/**
	 * Check whether or not the enemy is in range for medium AI, which is one
	 * block in up, down, left, and right directions.
	 * 
	 * @param myEnemy
	 *            the enemy that will have its position checked
	 * @return oolean on whether or not a medium AI enemy is in range of
	 *         bomberman.
	 */
	private boolean bomberManInRangeMedAI(Enemy myEnemy) {
		int dX = Math
				.abs((myEnemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
						- (this.myGameBoard.getBomberMan().getPositionX() / CONSTANTS.TILE_SIDE_SIZE));
		int dY = Math
				.abs((myEnemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE)
						- (this.myGameBoard.getBomberMan().getPositionY() / CONSTANTS.TILE_SIDE_SIZE));

		if (dX + dY <= 1) {
			return true;
		}
		return false;
	}

	/**
	 * Automatically set the movement for a high AI enemy, depending on the
	 * different scenarios.
	 * 
	 * @param myEnemy
	 *            the enemy that will have its movement altered
	 */
	public void highAI(Enemy myEnemy) {

		int ran = myEnemy.getMovmentDirection();
		Random random = new Random();

		boolean enemyAtEdgeOfCell = (myEnemy.getPositionX()) == ((myEnemy
				.getPositionX() / CONSTANTS.TILE_SIDE_SIZE) * CONSTANTS.TILE_SIDE_SIZE)
				&& (myEnemy.getPositionY()) == ((myEnemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE) * CONSTANTS.TILE_SIDE_SIZE);

		if (bomberManInRangeHighAI(myEnemy) == true
				&& this.myGameBoard.getBomberMan().getIsAlive()
				&& enemyAtEdgeOfCell
				&& !this.myGameBoard.getBomberMan().getInvisibilibityPowerUp()) {
			System.out.println("entering astar");
			int moves[] = aStar(myEnemy);
			System.out.println("exiting astar");
			if (moves != null)
				myEnemy.setExpectedMovmentDirection(moves[0]);

		} else if ((myEnemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE) % 2 == 1
				&& (myEnemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE) % 2 == 1
				&& enemyAtEdgeOfCell) {
			if (random.nextInt(10) >= 5) {
				myEnemy.setExpectedMovmentDirection(myEnemy
						.getExpectedMovmentDirection());
			} else {
				while (ran == myEnemy.getExpectedMovmentDirection()) {
					ran = random.nextInt(4) + 1;
				}
				myEnemy.setExpectedMovmentDirection(ran);
			}
		} else {
			myEnemy.setExpectedMovmentDirection(myEnemy
					.getExpectedMovmentDirection());
		}
	}

	/**
	 * Checks whether or not enemy is in range of bomberman, which is within two
	 * cell movements.
	 * 
	 * @param myEnemy
	 *            the enemy that will have its position checked
	 * @return boolean on whether or not a high AI enemy is in range of
	 *         bomberman.
	 */
	public boolean bomberManInRangeHighAI(Enemy myEnemy) {

		int dX = Math
				.abs((myEnemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
						- (this.myGameBoard.getBomberMan().getPositionX() / CONSTANTS.TILE_SIDE_SIZE));
		int dY = Math
				.abs((myEnemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE)
						- (this.myGameBoard.getBomberMan().getPositionY() / CONSTANTS.TILE_SIDE_SIZE));
		if ((dX + dY) <= 2) {
			return true;
		}
		return false;
	}

	/**
	 * Path finding algorithm for the high AI enemy, using a* search. Does this
	 * by evaluating each frontier cell and picking the best candidate. Each
	 * frontier cell gets placed into ArrayList<Cell> openSet, while searched
	 * cells are put into ArrayList<Cell> closedSet. Cells with obstacles are
	 * put into ArrayList<Cell> obstacles. Repeats these two steps until it
	 * finds the destination cell, which is bomberman.
	 * 
	 * @param myEnemy
	 *            high AI enemy
	 * @return int array of all the movements needed to reach bomberman.
	 */
	private int[] aStar(Enemy myEnemy) {
		ArrayList<Cell> openSet = new ArrayList<Cell>();
		ArrayList<Cell> closedSet = new ArrayList<Cell>();
		ArrayList<Cell> obstacles = new ArrayList<Cell>();
		Cell myCell = this.myGameBoard.getCell(myEnemy.getPositionY()
				/ CONSTANTS.TILE_SIDE_SIZE, myEnemy.getPositionX()
				/ CONSTANTS.TILE_SIDE_SIZE);
		myCell.setParent(null);
		openSet.add(myCell);
		Cell successor = null;
		this.myGameBoard.getCell(
				myEnemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
				myEnemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE).setGscore(0);
		this.myGameBoard.getCell(
				myEnemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
				myEnemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE).setFscore(
						manhattan(myEnemy.getPositionY() / CONSTANTS.TILE_SIDE_SIZE,
								myEnemy.getPositionX() / CONSTANTS.TILE_SIDE_SIZE));

		openSet.add(this.myGameBoard.getCell(myEnemy.getPositionY()
				/ CONSTANTS.TILE_SIDE_SIZE, myEnemy.getPositionX()
				/ CONSTANTS.TILE_SIDE_SIZE));
		while (openSet.size() != 0) {
			myCell = getMinCell(openSet);
			System.out.println(myCell.getX() + ", " + myCell.getY());
			openSet.remove(myCell);
			closedSet.add(myCell);

			for (int i = 0; i < 4; i++) {
				successor = null;
				if (i == 0) {
					if (myCell.getX() + 1 <= 30) {
						successor = this.myGameBoard.getCell(myCell.getY(),
								myCell.getX() + 1);
						successor.setGscore(myCell.getGScore() + 1);
						successor.setFscore(manhattan(myCell.getX() + 1,
								myCell.getY()));
					}

				}
				if (i == 1) {
					if (myCell.getX() - 1 >= 0) {
						successor = this.myGameBoard.getCell(myCell.getY(),
								myCell.getX() - 1);
						successor.setGscore(myCell.getGScore() + 1);
						successor.setFscore(manhattan(myCell.getX() - 1,
								myCell.getY()));
					}
				}
				if (i == 2) {
					if (myCell.getY() + 1 <= 12) {
						successor = this.myGameBoard.getCell(myCell.getY() + 1,
								myCell.getX());
						successor.setGscore(myCell.getGScore() + 1);
						successor.setFscore(manhattan(myCell.getX(),
								myCell.getY() + 1));
					}

				}
				if (i == 3) {
					if (myCell.getY() - 1 >= 0) {
						successor = this.myGameBoard.getCell(myCell.getY() - 1,
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
						System.out.println("seeting parent of "
								+ successor.getX() + " Y:" + successor.getY());
						successor.setParent(myCell);
						openSet.add(successor);
					}
					if (successor.getX() == (this.myGameBoard.getBomberMan()
							.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
							&& successor.getY() == (this.myGameBoard
									.getBomberMan().getPositionY() / CONSTANTS.TILE_SIDE_SIZE)) {
						successor.setParent(myCell);
						openSet.add(successor);
					}
					if (successor.getX() == (this.myGameBoard.getBomberMan()
							.getPositionX() / CONSTANTS.TILE_SIDE_SIZE)
							&& successor.getY() == (this.myGameBoard
									.getBomberMan().getPositionY() / CONSTANTS.TILE_SIDE_SIZE)) {

						successor.setParent(myCell);
						return returnRoute(successor);
					}
				}

			}

		}
		return null;
	}

	/**
	 * Method which returns the route of the path leading to bomberman. Does
	 * this by taking the last cell, where bomberman is located and repeatedly
	 * calling the parent cell until it reached the original enemy position.
	 * While calling the parent cell it notes which direction it need to travel
	 * from parent to child cell, storing it into a stack and finally
	 * transferring it into an integer array.
	 * 
	 * @param cell
	 *            the one which bomberman is located.
	 * @return integer array which contains the path for the enemy to reach
	 *         bomberman
	 */
	private int[] returnRoute(Cell cell) {
		Cell currentCell = cell;
		Cell parent = currentCell.getParent();
		Stack<Integer> temp = new Stack<Integer>();
		while (parent != null) {
			int dX = currentCell.getX() - parent.getX();
			int dY = currentCell.getY() - parent.getY();

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
			System.out.println(route[i] + " ");
		}
		System.out.println(route[0]);
		return route;
	}

	/**
	 * Searches through all the cells in the open set of cells, and get the cell
	 * with the lowest search algorithm score possible.
	 * 
	 * @param openSet
	 *            the cells that haven't been searched through yet in the path
	 *            finding algorithm
	 * @return the cell with the best score to reach bomberman.
	 */
	private Cell getMinCell(ArrayList<Cell> openSet) {
		Cell smallest = openSet.get(0);
		for (int i = 1; i < openSet.size(); i++) {
			if (openSet.get(i).getFscore() < smallest.getFscore())
				smallest = openSet.get(i);
		}

		return smallest;

	}

	/**
	 * Checks whether or not the cell in question has an obstacle in relation to
	 * if enemy has wall pass.
	 * 
	 * @param cell
	 *            current one being searched in path finding algorithm
	 * @param myEnemy
	 *            contains whether or not it has wall pass in determining if
	 *            certain things are obstacles or not for the particular enemy
	 * @return boolean whether or not there is an obstacle in the cell
	 */
	private boolean getObstacle(Cell cell, Enemy myEnemy) {
		if (myEnemy.hasWallPass() == false
				&& (cell.searcHasAConcreteWall() || cell.searcHasABrickWall() || cell
						.getHasABomb())) {
			return true;
		} else if (myEnemy.hasWallPass()
				&& (cell.searcHasAConcreteWall() || cell.getHasABomb())) {
			return true;
		}
		return false;
	}

	/**
	 * Takes future (x, y) positions of the enemy and returns the manhattan
	 * distance to bomberman.
	 * 
	 * @param cellPosX
	 *            a cell position X
	 * @param cellPosY
	 *            a cell position Y
	 * @return manhattan distance used in path finding algorithm.
	 */
	private int manhattan(int cellPosX, int cellPosY) {
		int dX = Math
				.abs(cellPosX
						- (this.myGameBoard.getBomberMan().getPositionX() / CONSTANTS.TILE_SIDE_SIZE));
		int dY = Math
				.abs(cellPosY
						- (this.myGameBoard.getBomberMan().getPositionY() / CONSTANTS.TILE_SIDE_SIZE));
		return (dY + dX);
	}

}
