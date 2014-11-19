/* 
 * File: GameBoard.java
 * -----------------------
 * This Class represents the game board which is a grid of cells. The information stored here will
 * be used to draw the gameboard for the player
 */
package bomberMan.gamePlay.Model;
import java.util.Random;

public class GameBoard {
	
	/*Instance Variables*/
	public Cell board[][];
	public BomberMan myBomberMan;
	/** 
	 * Constructor
	 * This method takes care of the initialization of the grid as well as the addition of the 
	 * concrete walls. 
	 */
	public GameBoard(int numberOfWall)
	{
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
		
		buildSurroundingWall();
		buildConcreteWalls();
		buildRandomMap();
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
		for(int i=0;i<CONSTANTS.NUMBER_OF_VERTICAL_TILES;i++)
		{
			board[i][0].insert(new Wall(0,i*CONSTANTS.TILE_SIDE_SIZE,WallType.CONCRETE));
		}
		for(int i=0;i<CONSTANTS.NUMBER_OF_VERTICAL_TILES;i++)
		{
			board[i][30].insert(new Wall(CONSTANTS.WINDOW_WIDTH-CONSTANTS.TILE_SIDE_SIZE,i*CONSTANTS.TILE_SIDE_SIZE,WallType.CONCRETE));
		}
		for(int j=0;j<CONSTANTS.NUMBER_OF_HORIZONTAL_TILES;j++)
		{
			board[12][j].insert(new Wall(j*CONSTANTS.TILE_SIDE_SIZE,CONSTANTS.WINDOW_HEIGHT-CONSTANTS.TILE_SIDE_SIZE,WallType.CONCRETE));
		}
		board[2][1].insert(new PowerUp(CONSTANTS.TILE_SIDE_SIZE, 2*CONSTANTS.TILE_SIDE_SIZE, CONSTANTS.BOMB_POWERUP, PowerUpType.BOMBS, "PowerUp"));
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
	/** 
	 * This returns the BomberMan for any purpose. We just have one bomberMan in the gameBoard.
	 */
	public BomberMan getBomberMan()
	{
		return this.myBomberMan;
	}
	public void addBomb(int xCellPos, int yCellPos, Bomb objectBomb)
	{
		
		this.board[yCellPos][xCellPos].insert(objectBomb);
		this.myBomberMan.addBomb(objectBomb);
	}
	/*
	 * 
	 * This function builds a random map
	 */
	public void buildRandomMap(){
		
		int maxBricks = CONSTANTS.maximumBrickMAP;
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
	
}