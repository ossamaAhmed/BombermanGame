/* 
 * File: GameBoard.java
 * -----------------------
 * This Class represents the game board which is a grid of cells. The information stored here will
 * be used to draw the gameboard for the player
 */
package bomberMan.gamePlay.Model;
import java.util.Random;
import java.util.ArrayList;

import bomberMan.Login.Model.User;

public class GameBoard implements java.io.Serializable {
	
	/*Instance Variables*/
	Cell board[][];
	BomberMan myBomberMan;
	private ArrayList<Enemy> myEnemies;
	private ArrayList<Bomb> myBombs;
	private PowerUp myPowerUp;
	int iCellPowerUp;
	int jCellPowerUp;
	private int numLives;
	private int stageNumber;
	private transient User myUser;
	private long remainingTime = 0;
	private Exit myExit;
	/** 
	 * Constructor
	 * This method takes care of the initialization of the grid for the stage specified
	 * @param stageNumber is the stage number you want
	 * @param stage is a one dimensional array that have the number of enemies, type of powerup..etc
	 * @param powersKeptAfterDeath is an array which corresponds to the powerups that bomberman doesn't lose after death
	 * @param nLives are the lives of bomberman left
	 * @param user is the user playing this game now
	 * @param remTime is the remaining time for the game  
	 */
	public GameBoard(int stageNumber, int[] stage, int [] powerUpsKeptAfterDeath, int nLives,User user , long remTime){  
		myBombs  = new ArrayList <Bomb>(); 
		myExit = new Exit(0*CONSTANTS.TILE_SIDE_SIZE,0*CONSTANTS.TILE_SIDE_SIZE,CONSTANTS.EXIT_IMAGE, "ExitDoor");
	    this.remainingTime = remTime;
	    numLives = nLives;
	    this.stageNumber=stageNumber;
	    this.myUser=user;
		myBomberMan=new BomberMan(CONSTANTS.INITIAL_BOMBERMAN_X_POS,CONSTANTS.INITIAL_BOMBERMAN_Y_POS);
		board=new Cell[CONSTANTS.NUMBER_OF_VERTICAL_TILES][CONSTANTS.NUMBER_OF_HORIZONTAL_TILES];
		for(int i=0;i<CONSTANTS.NUMBER_OF_VERTICAL_TILES;i++){
			for(int j=0;j<CONSTANTS.NUMBER_OF_HORIZONTAL_TILES;j++){
				board[i][j]=new Cell(i*CONSTANTS.TILE_SIDE_SIZE, j*CONSTANTS.TILE_SIDE_SIZE, myBomberMan);
			}
		}
		myEnemies=new ArrayList<Enemy>();
		//starting position of bomberman is hard coded here to start from the first cell
		myBomberMan=new BomberMan(CONSTANTS.INITIAL_BOMBERMAN_X_POS,CONSTANTS.INITIAL_BOMBERMAN_Y_POS);
		myBomberMan.setNumBombsToDrop1(powerUpsKeptAfterDeath[0]);
		myBomberMan.setBombRange1(powerUpsKeptAfterDeath[1]);
		myBomberMan.updateSpeed(powerUpsKeptAfterDeath[2]);
		buildSurroundingWall();
		buildConcreteWalls();
	    buildRandomMap(CONSTANTS.maximumBrickMAP, this.getPowerUpType(stage[8]) , this.getPowerUpImage(stage[8]));
	    //initializing enemies according to the stage passed 
		initializeEnemiesPosition(stage[0],"Balloom","Low");
		initializeEnemiesPosition(stage[1],"Oneal","Medium");
		initializeEnemiesPosition(stage[2],"Doll","Low");
		initializeEnemiesPosition(stage[3],"Minvo","Medium");
		initializeEnemiesPosition(stage[4],"Kondoria","High");
		initializeEnemiesPosition(stage[5],"Ovapi","Medium");
		initializeEnemiesPosition(stage[6],"Pass","High");
		initializeEnemiesPosition(stage[7],"Pontan","High");
		
	}
	/** 
	 * This method executes the worst penality, by spawning a lot of enemies in the board
	 * @param stage is the current stage to search for the next highest enemy
	 */
	public void worstPenality(int[] stage){
		int highestEnemy = 0;
		int size = myEnemies.size();

		for(int i = 0; i < stage.length - 1; i++){
			if(stage[i]!= 0);
				highestEnemy = i;
		}
		for(int i = 0; i< size; i++){
			myEnemies.get(i).die();
		}
		
		if(highestEnemy == 0)
			initializeEnemiesPosition(8,"Doll","Low");
		if(highestEnemy == 1)
			initializeEnemiesPosition(8,"Minvo","Medium");
		if(highestEnemy == 2)
			initializeEnemiesPosition(8,"Oneal","Medium");
		if(highestEnemy == 3)
			initializeEnemiesPosition(8,"Ovapi","Medium");
		if(highestEnemy == 4)
			initializeEnemiesPosition(8,"Kondoria","High");
		if(highestEnemy == 5)
			initializeEnemiesPosition(8,"Pontan","High");
		if(highestEnemy >= 6)
			initializeEnemiesPosition(8,"Pass","High");
	}
	/** 
	 * This gets the cell in the board provided x and y 
	 * @param x is the vertical cell number required
	 * @param y is the horizontal cell number required
	 * @return returns the cell specified
	 */
	public Cell getCell(int x, int y){
		return board[x][y];
	}
	/** 
	 * This method pushes in the specified cell a new game object
	 * @param x is the vertical cell number required
	 * @param y is the horizontal cell number required
	 * @param gameObject is the game object that you want to push in the required cell
	 */
	public void setCell(int x, int y, GameObject gameObject){
		this.board[x][y].insert(gameObject);
	}
	/** 
	 * This method gets the score of the current user playing this game
	 * @return returns the current score of the user playing this game
	 */
	public int getScore(){
		return this.myUser.getMyScore();
	}
	/** 
	 * This method initializes enemies positions inside the game board by generating random positions.
	 * @param numberOfEnemies is the number of enemies that you want to spawn
	 * @param enemyName is the name of the enemy that you want to spawn 
	 * @param smartnessType is the type of intelligence of the enemy low, medium or high
	 */
	private void initializeEnemiesPosition(int numberOfEnemies, String enemyName,String smartnessType){
		Random randomGenerator = new Random();
		for(int i=0;i<numberOfEnemies;i++){
			int xCell=randomGenerator.nextInt(CONSTANTS.NUMBER_OF_VERTICAL_TILES);
			int yCell=randomGenerator.nextInt(CONSTANTS.NUMBER_OF_HORIZONTAL_TILES);
			//A cell should be empty and the row and column should be clear of concrete walls
			while (!board[xCell][yCell].isEmpty()||!isRowClear(xCell)||!isColumnClear(yCell)||((xCell==1)&&(yCell==1))){
				xCell=randomGenerator.nextInt(CONSTANTS.NUMBER_OF_VERTICAL_TILES);
				yCell=randomGenerator.nextInt(CONSTANTS.NUMBER_OF_HORIZONTAL_TILES);
			}
				int direction=randomGenerator.nextInt(4)+1;
				System.out.println(xCell+"     "+yCell);
				if(smartnessType.equals("Low")){
					LowIntelligenceEnemy myEnemy=new LowIntelligenceEnemy(yCell*CONSTANTS.TILE_SIDE_SIZE,xCell*CONSTANTS.TILE_SIDE_SIZE,direction,enemyName);
					myEnemies.add(myEnemy);
					this.board[xCell][yCell].insert(myEnemy);
				}
				else if(smartnessType.equals("Medium")){
					MediumIntelligenceEnemy myEnemy=new MediumIntelligenceEnemy(yCell*CONSTANTS.TILE_SIDE_SIZE,xCell*CONSTANTS.TILE_SIDE_SIZE,direction,enemyName);
					myEnemies.add(myEnemy);
					this.board[xCell][yCell].insert(myEnemy);
				}
				else {
					HighIntellegenceEnemy myEnemy=new HighIntellegenceEnemy(yCell*CONSTANTS.TILE_SIDE_SIZE,xCell*CONSTANTS.TILE_SIDE_SIZE,direction,enemyName);
					myEnemies.add(myEnemy);
					this.board[xCell][yCell].insert(myEnemy);
				}
		}
	}
	/** 
	 * This is a helper method to build the surrounding walls.Some numbers here are hard coded
	 * They should be removed and replaced by the constants
	 */
	private void buildSurroundingWall(){
		
		for(int j=0;j<CONSTANTS.NUMBER_OF_HORIZONTAL_TILES;j++){
			board[0][j].insert(new Wall(j*CONSTANTS.TILE_SIDE_SIZE,0,WallType.CONCRETE));
		}
		for(int i=1;i<CONSTANTS.NUMBER_OF_VERTICAL_TILES;i++){
			board[i][0].insert(new Wall(0,i*CONSTANTS.TILE_SIDE_SIZE,WallType.CONCRETE));
		}
		for(int i=1;i<CONSTANTS.NUMBER_OF_VERTICAL_TILES;i++){
			board[i][30].insert(new Wall(CONSTANTS.WINDOW_WIDTH-CONSTANTS.TILE_SIDE_SIZE,i*CONSTANTS.TILE_SIDE_SIZE,WallType.CONCRETE));
		}
		for(int j=0;j<CONSTANTS.NUMBER_OF_HORIZONTAL_TILES;j++){
			board[12][j].insert(new Wall(j*CONSTANTS.TILE_SIDE_SIZE,CONSTANTS.WINDOW_HEIGHT-CONSTANTS.TILE_SIDE_SIZE,WallType.CONCRETE));
		}
		
	}
	/** 
	 * This is a helper method to build the concrete walls.
	 */
	private void buildConcreteWalls(){
		
		 for(int i=2;i<CONSTANTS.NUMBER_OF_VERTICAL_TILES-1;i+=2) {
			 for(int j=2;j<CONSTANTS.NUMBER_OF_HORIZONTAL_TILES-1;j+=2){
				 board[i][j].insert(new Wall(j*CONSTANTS.TILE_SIDE_SIZE,i*CONSTANTS.TILE_SIDE_SIZE,WallType.CONCRETE));
			 }
		 }
	}
	/** 
	 * This is a helper method to see if the row is clear of concrete walls 
	 * @return returns true if the row is clear of concrete walls
	 */
	private boolean isRowClear(int x){
		if(x==0 || x==CONSTANTS.NUMBER_OF_VERTICAL_TILES-1)
			return false;
		for(int i=2;i<CONSTANTS.NUMBER_OF_VERTICAL_TILES-1;i+=2){
			if(x==i)
				return false;
		}
		return true;
	}
	/** 
	 * This is a helper method to see if the column is clear of concrete walls 
	 * @return returns true if the column is clear of concrete walls
	 */
	private boolean isColumnClear(int y){
		if(y==0 || y==CONSTANTS.NUMBER_OF_HORIZONTAL_TILES-1)
			return false;
		for(int j=2;j<CONSTANTS.NUMBER_OF_HORIZONTAL_TILES-1;j+=2){
			if(y==j)
				return false;
		}
		return true;
	}
	/** 
	 * This returns the BomberMan for any purpose. We just have one bomberMan in the gameBoard.]
	 * @return returns the bomberman 
	 */
	public BomberMan getBomberMan(){
		return this.myBomberMan;
	}
	/** 
	 * This returns the list of enemies in the game board currently
	 * @return returns an array list of the enemies currently in the game board
	 */
	public ArrayList<Enemy> getEnemies(){
		return this.myEnemies;
	}
	/** 
	 * This adds a bomb at the specified location
	 * @param xCellPos is the x position cell based not pixels
	 * @param yCellPos is the y position cell based not pixels
	 * @param objectBomb is the bomb asking to add to the game board
	 */
	public void addBomb(int xCellPos, int yCellPos, Bomb objectBomb){
		
		this.board[yCellPos][xCellPos].insert(objectBomb);
		this.myBombs.add(objectBomb);
	}
	/** 
	 * This places bricks, exit door, powerups randomly
	 * @param numberOfBricks is the number of brick walls you want in the grid
	 * @param powerUp is the powerUp you want to put in the game board
	 * @param powerUpImageLocation is the power up image location on the drive
	 */
	private void buildRandomMap(int numberOfBricks, PowerUpType powerUp, String powerUpImageLocation){
		
		int maxBricks = numberOfBricks;
		Random objectRandom = new Random();
		int brickNumber = 0;
		int exitNumberChosen = objectRandom.nextInt(numberOfBricks-1);
		int powerUpNumberChosen = objectRandom.nextInt(numberOfBricks-1);
		while(powerUpNumberChosen == exitNumberChosen){
			powerUpNumberChosen = objectRandom.nextInt(numberOfBricks-1); 
		}
		boolean exitAlreadyPlaced = false;
		boolean powerUpAlreadyPlaced = false;
		int counter = 0;
		int numberChosen;
		int i = objectRandom.nextInt(CONSTANTS.NUMBER_OF_VERTICAL_TILES-1);
		int j = objectRandom.nextInt(CONSTANTS.NUMBER_OF_HORIZONTAL_TILES-1);
		 
	   while(counter < numberOfBricks){
				 numberChosen = objectRandom.nextInt(9);
				 if(board[i][j].isEmpty()&& numberChosen == brickNumber && counter < maxBricks&& (i > 2 || j >  2) ){
				
				 board[i][j].insert(new Wall(j*CONSTANTS.TILE_SIDE_SIZE,i*CONSTANTS.TILE_SIDE_SIZE,WallType.BRICK));
				 if(exitAlreadyPlaced == false && counter == exitNumberChosen){
					 myExit = new Exit(j*CONSTANTS.TILE_SIDE_SIZE,i*CONSTANTS.TILE_SIDE_SIZE,CONSTANTS.EXIT_IMAGE, "ExitDoor");
					 board[i][j].insert(myExit);
				     exitAlreadyPlaced = true;
				     System.out.println("NUMBER CHOSEN FOR EXIT i j " + i + ", "+j);
				 }
				 if(powerUpAlreadyPlaced == false && counter == powerUpNumberChosen){
					 board[i][j].insert(new PowerUp(j*CONSTANTS.TILE_SIDE_SIZE,i*CONSTANTS.TILE_SIDE_SIZE,powerUpImageLocation, powerUp,"PowerUp"));
				     powerUpAlreadyPlaced = true;
				     System.out.println("NUMBER CHOSEN FOR power up i j " + i + ", "+j);
				 }
				 counter++;
				 }
				 i = objectRandom.nextInt(CONSTANTS.NUMBER_OF_VERTICAL_TILES-1);
				 j = objectRandom.nextInt(CONSTANTS.NUMBER_OF_HORIZONTAL_TILES-1);
				
	   }
	}
	/** 
	 * This gets a bomb list of the current bombs in the game board currently
	 * @return returns array list of bombs that are currently in the game board
	 */
	public ArrayList <Bomb> getBombs(){
		return this.myBombs;
	}
	/** 
	 * This removes a bomb from the specified x and y position
	 * @param x is the x position in the game board
	 * @param y is the y position in the game board
	 */
	public void removeBomb(int x, int y){
		int counter = 0;
		if(this.myBombs.size() > 0){
		for(counter = 0; counter < this.myBombs.size(); counter++){
		if(this.myBombs.get(counter).getPositionX() == x && this.myBombs.get(counter).getPositionY() == y ){
		this.myBombs.remove(counter);
		}
	   }
	 }
	}
	/** 
	 * This gets the power up present in the game board
	 * @return returns the power up currently located in the game board
	 */
	public PowerUp getPowerUpBoard(){
		return this.myPowerUp;
	}
	/** 
	 * This adds a power up to the game baord
	 * @param powerup is the power up you want to add to the game board
	 */
	public void addPowerUp(PowerUp powerup){
		this.myPowerUp = powerup;
	}
	/** 
	 * This removes a power up from the game board
	 * @param i is the x cell position
	 * @param j is the y cell position
	 */
	public void deletePowerUp(int i, int j){
		this.board[i][j].removePowerUp();
	}
	/** 
	 * This gets a power up image 
	 * @param number is the number representing the type of power up
	 * @return a file name according to the powerup required
	 */
	public String getPowerUpImage(int number){
		 String powerUpImage = "";
		 if(number == 1)
			 powerUpImage = CONSTANTS.BOMBS_POWERUP;
		 if(number == 2)
			 powerUpImage = CONSTANTS.FLAME_POWERUP;
		 if(number == 3)
			 powerUpImage = CONSTANTS.SPEED_POWERUP;
		 if(number == 4)
			 powerUpImage = CONSTANTS.WALLPASS_POWERUP;
		 if(number == 5)
			 powerUpImage = CONSTANTS.BOMB_DETONATOR_POWERUP;
		 if(number == 6)
			 powerUpImage = CONSTANTS.BOMB_PASS_POWERUP;
		 if(number == 7)
			 powerUpImage = CONSTANTS.IMMUNITY_FLAME_POWERUP;
		 if(number == 8)
			 powerUpImage = CONSTANTS.MYSTERY_POWERUP;
		 return powerUpImage;
	}
	/** 
	 * This gets a power up type 
	 * @param number is the number representing the type of power up
	 * @return a power up type corresponding to the number passes
	 */
	public PowerUpType getPowerUpType(int number){
		 PowerUpType powerUpType = PowerUpType.BOMBS;
		 if(number == 1)
			 powerUpType = PowerUpType.BOMBS;
		 if(number == 2)
			 powerUpType = powerUpType.FLAMES;
		 if(number == 3)
			 powerUpType = powerUpType.SPEED;
		 if(number == 4)
			 powerUpType = powerUpType.WALLPASS;
		 if(number == 5)
			 powerUpType = powerUpType.DETONATOR;
		 if(number == 6)
			 powerUpType = powerUpType.BOMBPASS;
		 if(number == 7)
			 powerUpType = powerUpType.FLAMEPASS;
		 if(number == 8)
		 	powerUpType = powerUpType.MYSTERY;
		 return powerUpType;
		
	}
	/** 
	 * This gets a stage number of the current game board
	 * @return a stage number of the current game baord
	 */
	public int getStage(){
		return this.stageNumber;
	}
	/** 
	 * This updates the current score of the user playing this game
	 * @param killedEnemies is an array list of enemies that were killed and you
	 * want to gain their points
	 */
	public void updateMyScore(ArrayList<Enemy> killedEnemies){
		this.myUser.calculateMyScore(killedEnemies);
		
	}
	/** 
	 * This gets the exit door
	 * @return the exit door in the game board
	 */
	public Exit getExit(){
		return this.myExit;
	}
	/** 
	 * This gets number of enemies in the game board
	 * @return the number of enemies in the game board
	 */
	public int getNumEnemies(){
		return this.myEnemies.size();
	}
	/** 
	 * This gets number of lives 
	 * @return the number of lives of bomberman
	 */
	public int getLives(){
		return this.numLives;
	}
	/** 
	 * This sets remaining time 
	 * @param seTime is the time remaining for the current game
	 */
	public void setRemainingTime(long setTime){
		this.remainingTime = setTime;
	}
	/** 
	 * This gets remaining time 
	 * @return returns the time remaining for the current game
	 */
	public long getRemainingTime(){
		return this.remainingTime;
	}
}