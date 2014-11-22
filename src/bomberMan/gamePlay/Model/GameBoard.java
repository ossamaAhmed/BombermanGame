/* 
 * File: GameBoard.java
 * -----------------------
 * This Class represents the game board which is a grid of cells. The information stored here will
 * be used to draw the gameboard for the player
 */
package bomberMan.gamePlay.Model;
import java.util.Random;

import java.util.ArrayList;
import java.util.Random;

public class GameBoard {
	
	/*Instance Variables*/
	Cell board[][];
	BomberMan myBomberMan;
	private ArrayList<Enemy> myEnemies;
	private ArrayList<Bomb> myBombs;
	private PowerUp myPowerUp;
	int iCellPowerUp;
	int jCellPowerUp;
	/** 
	 * Constructor
	 * This method takes care of the initialization of the grid as well as the addition of the 
	 * concrete walls. 
	 */
	public GameBoard(int numberOfWall)
	
	{   myBombs  = new ArrayList <Bomb>();
		myBomberMan=new BomberMan(CONSTANTS.INITIAL_BOMBERMAN_X_POS,CONSTANTS.INITIAL_BOMBERMAN_Y_POS);
		board=new Cell[CONSTANTS.NUMBER_OF_VERTICAL_TILES][CONSTANTS.NUMBER_OF_HORIZONTAL_TILES];
		for(int i=0;i<CONSTANTS.NUMBER_OF_VERTICAL_TILES;i++)
		{
			for(int j=0;j<CONSTANTS.NUMBER_OF_HORIZONTAL_TILES;j++)
			{
				board[i][j]=new Cell(i*CONSTANTS.TILE_SIDE_SIZE, j*CONSTANTS.TILE_SIDE_SIZE, myBomberMan);
			}
		}
		/*The Starting position shouldn't be hard coded. 
		**The starting position will be added to constants
		*/
		myEnemies=new ArrayList<Enemy>();
		initializeEnemiesPosition(20);
		myBomberMan=new BomberMan(40,40);
		myBomberMan=new BomberMan(CONSTANTS.INITIAL_BOMBERMAN_X_POS,CONSTANTS.INITIAL_BOMBERMAN_Y_POS);
		buildSurroundingWall();
		buildConcreteWalls();
		buildRandomMap(CONSTANTS.maximumBrickMAP, PowerUpType.FLAMEPASS);
	}
	/** 
	 * This method returns the cell at the x and y position.
	 */
	public Cell getCell(int x, int y)
	{
		return board[x][y];
	}
	public void setCell(int x, int y, GameObject gameObject)
	{
		this.board[x][y].insert(gameObject);
	}
	public void initializeEnemiesPosition(int numberOfEnemies)
	{
		Random randomGenerator = new Random();
		for(int i=0;i<numberOfEnemies;i++)
		{
			int xCell=randomGenerator.nextInt(CONSTANTS.NUMBER_OF_VERTICAL_TILES);
			while(!isRowClear(xCell))
			{
				xCell=randomGenerator.nextInt(CONSTANTS.NUMBER_OF_VERTICAL_TILES);
			}
			int yCell=randomGenerator.nextInt(CONSTANTS.NUMBER_OF_HORIZONTAL_TILES);
			while(!isColumnClear(yCell))
			{
				yCell=randomGenerator.nextInt(CONSTANTS.NUMBER_OF_HORIZONTAL_TILES);
			}
			int direction=randomGenerator.nextInt(4)+1;
			System.out.println(xCell+"     "+yCell);
			myEnemies.add(new Enemy(yCell*CONSTANTS.TILE_SIDE_SIZE,xCell*CONSTANTS.TILE_SIDE_SIZE,direction));
		}
	}
	/** 
	 * This is a helper method to build the surrounding walls.Some numbers here are hard coded
	 * They should be removed and replaced by the constants
	 */
	public void buildSurroundingWall()
	{
		for(int j=0;j<CONSTANTS.NUMBER_OF_HORIZONTAL_TILES;j++)
		{
			board[0][j].insert(new Wall(j*CONSTANTS.TILE_SIDE_SIZE,0,WallType.CONCRETE));
		}
		for(int i=1;i<CONSTANTS.NUMBER_OF_VERTICAL_TILES;i++)
		{
			board[i][0].insert(new Wall(0,i*CONSTANTS.TILE_SIDE_SIZE,WallType.CONCRETE));
		}
		for(int i=1;i<CONSTANTS.NUMBER_OF_VERTICAL_TILES;i++)
		{
			board[i][30].insert(new Wall(CONSTANTS.WINDOW_WIDTH-CONSTANTS.TILE_SIDE_SIZE,i*CONSTANTS.TILE_SIDE_SIZE,WallType.CONCRETE));
		}
		for(int j=0;j<CONSTANTS.NUMBER_OF_HORIZONTAL_TILES;j++)
		{
			board[12][j].insert(new Wall(j*CONSTANTS.TILE_SIDE_SIZE,CONSTANTS.WINDOW_HEIGHT-CONSTANTS.TILE_SIDE_SIZE,WallType.CONCRETE));
		}
		board[2][1].insert(new PowerUp(CONSTANTS.TILE_SIDE_SIZE, 2*CONSTANTS.TILE_SIDE_SIZE, CONSTANTS.IMMUNITY_POWERUP , PowerUpType.FLAMEPASS, "PowerUp"));
	}
	/** 
	 * This is a helper method to build the concrete walls.Some numbers here are hard coded
	 * They should be removed and replaced by the constants
	 */
	public void buildConcreteWalls()
	{
		 for(int i=2;i<CONSTANTS.NUMBER_OF_VERTICAL_TILES-1;i+=2)
		 {
			 for(int j=2;j<CONSTANTS.NUMBER_OF_HORIZONTAL_TILES-1;j+=2)
			 {
				 board[i][j].insert(new Wall(j*CONSTANTS.TILE_SIDE_SIZE,i*CONSTANTS.TILE_SIDE_SIZE,WallType.CONCRETE));
			 }
		 }
	}
	
	public boolean isRowClear(int x)
	{
		if(x==0 || x==CONSTANTS.NUMBER_OF_VERTICAL_TILES-1)
			return false;
		for(int i=2;i<CONSTANTS.NUMBER_OF_VERTICAL_TILES-1;i+=2)
		{
			if(x==i)
				return false;
		}
		return true;
	}
	public boolean isColumnClear(int y)
	{
		if(y==0 || y==CONSTANTS.NUMBER_OF_HORIZONTAL_TILES-1)
			return false;
		for(int j=2;j<CONSTANTS.NUMBER_OF_HORIZONTAL_TILES-1;j+=2)
		{
			if(y==j)
				return false;
		}
		return true;
	}
	/** 
	 * This returns the BomberMan for any purpose. We just have one bomberMan in the gameBoard.
	 */
	public BomberMan getBomberMan()
	{
		return this.myBomberMan;
	}
	public ArrayList<Enemy> getEnemies()
	{
		return this.myEnemies;
	}
	public void addBomb(int xCellPos, int yCellPos, Bomb objectBomb)
	{
		
		this.board[yCellPos][xCellPos].insert(objectBomb);
		this.myBombs.add(objectBomb);
	}
	/*
	 * 
	 * This function builds a random map
	 */
	public void buildRandomMap(int numberOfBricks, PowerUpType powerUp){
		
		int maxBricks = numberOfBricks;
		Random objectRandom = new Random();
		int brickNumber = 0;
		int counter = 0;
		int numberChosen;
		 for(int i=0;i<CONSTANTS.NUMBER_OF_VERTICAL_TILES-1;i+=1)
		 {
			 for(int j=1;j<CONSTANTS.NUMBER_OF_HORIZONTAL_TILES-1;j+=1)
			 {
				 numberChosen = objectRandom.nextInt(9);
				 if(board[i][j].isEmpty()&& numberChosen == brickNumber && counter < maxBricks&& (i > 2 || j >  2) ){
				 board[i][j].insert(new Wall(j*CONSTANTS.TILE_SIDE_SIZE,i*CONSTANTS.TILE_SIDE_SIZE,WallType.BRICK));
				 counter++;
				 }
				 }
		 }
		
	}
	public ArrayList <Bomb> getBombs(){return this.myBombs;}
	//removes bomb with the specific x and y position
	public void removeBomb(int x, int y){
		int counter = 0;
		if(this.myBombs.size() > 0){
		for(counter = 0; counter < this.myBombs.size(); counter++){
		if(this.myBombs.get(counter).getPositionX() == x && this.myBombs.get(counter).getPositionY() == y ){
		this.myBombs.remove(counter);}}}}
	public PowerUp getPowerUpBoard(){return this.myPowerUp;}
	public void addPowerUp(PowerUp powerup){this.myPowerUp = powerup;}
	public void deletePowerUp(int i, int j){this.board[i][j].removePowerUp();}
	
}