/* 
 * File: Cell.java
 * -----------------------
 * This Class represents each call on the game board/Grid. Which will carry the various game
 * objects. We will implement all game objects in a cell-based grid except the characters
 * (bomberMan/Enemies) 
 */


package bomberMan.gamePlay.Model;

import java.util.ArrayList;
import java.util.Calendar;
import  java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * This class implements a set of methods that allow the creation of a Cell
 * object in a game board.
 * 
 * 
 * The documentation for the methods contained in this class includes
 * briefs descriptions of the implementations. Such descriptions
 * should be regarded as implementation notes, rather than parts of specifications
 * @author Andres Felipe Rincon Gamboa
 * 
 */



public class Cell implements java.io.Serializable {
	
	/*Instance Variables*/
	boolean hasABomb = false;
	int xPos;
	int yPos;
	boolean hasAFlame = false;
	boolean hasABrick = false;
	boolean isEmpty = true;
	boolean hasADetonateBomb = false;
	boolean hasAPowerUp = false;
	boolean hasADestructibleBrick = false;
	transient BufferedImage  image;
	Calendar timer;
	BomberMan myBomberMan;
	private ArrayList<GameObject> myObjects;
	private Cell parent;
	private int gscore;
	private int fscore;
	
	/**
	 * Constructor
	 * This method takes care of the creation of a cell object.
	 * 
	 * @param xPos integer position on the x axis of the game board.
	 * @param yPos integet position on the y axis of the game board.
	 * @param myBomberman
	 */
	public Cell(int xPos, int yPos, BomberMan myBomberman)
	{   this.myBomberMan = myBomberman;
		this.xPos = xPos;
	    this.yPos = yPos;
	    image=null;
		this.myObjects=new ArrayList<GameObject>();
		this.isEmpty = true;
		timer = Calendar.getInstance();
		this.parent = null;

	}
	/** 
	 * Constructor
	 * This method creates a cell object with a GameObject that is going to be placed inside
	 * the cell
	 * @param myObject , the GameObject that is going to be placed in the cell.
	 */
	public Cell(GameObject myObject)
	{
		timer = Calendar.getInstance();
		this.myObjects=new ArrayList<GameObject>();
		this.myObjects.add(myObject);
		image=null;
		this.isEmpty = true;
	}
	
	
	/** 
	 * This method inserts an object of type GameObject in the Arraylist of the cell
	 * @param myObject, the GameObject that is going to be inserted in the cell.
	 */
	public void insert(GameObject myObject)
	{
		this.myObjects.add(myObject);
	}
	
	/** 
	 * This method removes the specified gameObject from the arraylist of the cell. This method
	 * is not used yet. 
	 * @param myObject the GameObject that is going to be removed from the cell
	 */
	public void remove(GameObject myObject)
	{
		this.myObjects.remove(myObject);
	}
	/** 
	 * This method returns an arraylist of all the objects present in this cell.
	 * @return ArrayList representing all the GameObjects currently placed in the cell.
	 */
	public ArrayList<GameObject> getObjects()
	{
		return this.myObjects;	
	}
	/** 
	 * This method returns the image of the most Visible gameObject in the cell
	 *  currently to be drawn on the screen.
	 * This method is missing as it should implement the priorities of the appearence, 
	 * like the wall should appear instead of a powerup if it exists 
	 * This function throws an exception that should be collected  in a log file.
	 * @return BufferedImage the image of the most visible gameObject in the cell.
	 */
	public BufferedImage getImage()
	{
			if(!(this.myObjects.get(0) instanceof Enemy))
				return this.myObjects.get(0).getImage();
			return null;
			
	}
	/** 
	 * This method returns a boolean depending if the cell is totally empty and there 
	 * are no gameObjects including characters present in this cell. 
	 * @return boolean, true if the cell is completely empty, false otherwise.
	 * 
	 */
	
	public boolean isEmpty()
	{
		
		return this.myObjects.isEmpty();
	}
	/** 
	 * This method returns a boolean depending if the cell has no concrete wall or if it has
	 * a brick .
	 * @return true if the cell a brick, false otherwise.
	 */
	public boolean isEmptyBrickException()
	{
		
		if(this.searcHasABrickWall() == true){return true;}
		if(this.searcHasAConcreteWall() == true){return false;}
		return true;
		
	}
	  /**
     * This method returns a boolean whether there is an enemy inside the cell or not.
     * @return boolean true if the cell has an enemy, false otherwise.
     */
	public boolean isThereAnEnemy()
	{
		for(int i=0;i<myObjects.size();i++)
		{
			if(myObjects.get(i) instanceof Enemy)
				return true;
		}
		return false;
	}
	/**
	 * This method deletes the enemies present inside the cell.
	 */
	public void deleteEnemies()
	{
		while(this.isThereAnEnemy())
		{
			for(int i=0;i<myObjects.size();i++)
			{
				if(myObjects.get(i) instanceof Enemy)
					{
						((Enemy) myObjects.get(i)).die();
						myObjects.remove(i);
					}
			}
		}

	}
	/**
	 * This method returns an ArrayList containing the enemies present inside the cell.
	 * @return ArrayList containing the enemies present inside the cell.
	 */
	public ArrayList<Enemy> getEnemies()
	{
		ArrayList<Enemy> myEnemies=new ArrayList<Enemy>();
		for(int i=0;i<myObjects.size();i++)
		{
			if(myObjects.get(i) instanceof Enemy)
				{
					myEnemies.add((Enemy) myObjects.get(i));
				}
		}
		return myEnemies;
	}
	/** 
	 * This method returns a boolean if the cell is empty depending on the power ups that
	 * the bomberman has.
	 * @return boolean true if the cell is set to be empty, false otherwise.
	 */
	public boolean isEmptyPowerUpException()
	{
		System.out.println(this.myBomberMan.getBrickPass()+"!");
		if(this.searcHasAExit() == true && this.searcHasABrickWall()== false){return true;}
		if(this.isThereAnEnemy() == true)
		{
			return true;
		}
		if(this.searcHasAPowerUp() == true && this.searcHasABrickWall()== false )
		{
			return true;
		}
		if(this.getHasABomb() == true &&  this.myBomberMan.getBombPass() == true )
		{
			return true;
		}
		if(this.searcHasABrickWall()== true && this.myBomberMan.getBrickPass() == true)
		{
			return true;
		}
		
		return this.isEmpty();
	}
	/** 
	 * This method returns a boolean if the cell has not a bomb object or not
	 * @return boolean true if the cell has a bomb, false otherwise.
	 */
	public boolean isEmptyBombException(){
		
		if(this.getHasABomb() == true){return false;}
		return this.isEmpty();
	}
	
	/**
	 * This method sets the boolean hasABomb of the cell.
	 * @param set true if the cell has a bomb, false otherwise.
	 */
	public void setHasABomb(boolean set){this.hasABomb = set;}
	
	/**
	 * This method sets the boolean hasADetonatorBomb of the cell.
	 * @param set true if the cell has a bomb with a detonator, false otherwise.
	 */
	public void setHasADetonatorBomb(boolean set){this.hasADetonateBomb = set;}
	/**
	 *  Checks if the cell has a bomb object.
	 * @return boolean that represents if the cell has a bomb or not.
	 */
	public boolean getHasABomb(){
		if(this.hasADetonateBomb == true){return false;}
		return this.hasABomb;}
		/**
		 *  Checks if the cell has a bomb object with a detonator.
		 * @return boolean that represents if the cell has a bomb with a detonator or not.
		 */
	public boolean getHasADetonateBomb(){return this.hasADetonateBomb;}
	/**
	 * This method searches for the first bomb contained in the cell.
	 * @return Bomb object
	 */
	public Bomb searchBomb(){
		int i = 0;
		for(i =0; i< this.myObjects.size(); i++){
			
			if(this.myObjects.get(i).getType().equals("Bomb")){
				System.out.println("BombFound");
				
				return (Bomb)this.myObjects.get(i);}}
		return new Bomb();
	}
	/**
	 * This method deletes an specific GameObject given the type passed as a string.
	 * @param type string that represents a GameObject.
	 */
	public void deleteElement(String type){
		int i = 0;
		if(this.myObjects.size() >0){
		for(i =0; i< this.myObjects.size(); i++){
			
			if(this.myObjects.get(i).getType().equals(type)){
				if(this.myObjects.size() > 0){
				if(this.myObjects.get(i).getType().equals("Bomb")){
				    long start = timer.getTimeInMillis();
				    long finish = start + CONSTANTS.BOMB_EXPLOSION_SHOWING_TIME;
				    this.myObjects.get(i).setImage(CONSTANTS.Bomb_EXPLOSION);
				   System.out.println("Start showing bomb explosion "+ start);
				   System.out.println("Finish showing bomb explosion "+ finish);
				    while(start <= finish){
				    
				    System.out.println("Showing explosion " + type);
					start = start + 10;
					System.out.println("Time showing bomb explosion "+ start);
				    }
				    				
				    this.hasABomb = false;
					this.myObjects.remove(i);
				}}
				
				if(this.myObjects.size() >0){
				if(this.myObjects.get(i).getType().equals("Brick")){
					   this.hasADestructibleBrick = false;
					   this.myObjects.remove(i);
					  }
			
				System.out.println("Deleting " + type);
				}
				
			
		
			
		
				}
						}}
		
	}
	/**
	 * This method sets a boolean that represents if the cell has a flame or not.
	 */
	public void setFlameImages(){
		
		this.hasAFlame = true;
	    this.insert( new GameObject(this.yPos, this.xPos,CONSTANTS.Bomb_EXPLOSION, "Flame"));
	    
	    
	}
	/**
	 * This method searches a flame object and returns the index of this object on the ArrayList myObjects.
	 * 
	 * @return int representing the index of the flame object inside the ArrayList myObjects.
	 */
	public int searchFlameObject(){
		int i = 0;
		if(this.myObjects.size() >0){
		for(i =0; i< this.myObjects.size(); i++){
			
			if(this.myObjects.get(i).getType().equals("Flame")){
				
				return i;}}}
		return 0;
		
		
	}
	/**
	 * This method searches for a given GameObject type given as a string search. True is
	 * returned if the GameObject is inside the cell.
	 * @param search the GameObject typed passed as  a String.
	 * @return true if the GameObject is contained in the cell, false otherwise.
	 */
	public boolean searchTheCell(String search)
	{
		if(search.equals("CONCRETE")&&searcHasAConcreteWall())
			return true;
		if(search.equals("BRICK")&&searcHasABrickWall())
			return true;
		if(search.equals("POWERUP")&&searcHasAPowerUp())
			return true;
		if(search.equals("BOMB")&&(this.hasABomb||this.hasADetonateBomb))
			return true;
		return false;
	}
		/**
		 * This method searches for a concrete wall inside the cell.
		 * @return true if the cell has a concrete wall, false otherwise.
		 */
	public boolean searcHasAConcreteWall()
	{
		int i = 0;
		if(this.myObjects.size() >0){
		for(i =0; i< this.myObjects.size(); i++){
			
			if(this.myObjects.get(i).getType().equals("Concrete")){
				System.out.println("ConcreteFound");
				
				return true;}}}
		return false;
	}
		/**
		 * This method searches for a brick wall inside the cell.
		 * @return true if the cell has a brick wall, false otherwise.
		 */
		public boolean searcHasABrickWall()
		{
			int i = 0;
			if(this.myObjects.size() >0){
			for(i =0; i< this.myObjects.size(); i++){
				
				if(this.myObjects.get(i).getType().equals("Brick")){
					System.out.println("Brick");
					
					return true;}}}
			return false;
		}
		/**
		 * This method searches for a power up inside the cell.
		 * @return true if the cell has a power up, false otherwise.
		 */
				public boolean searcHasAPowerUp()
				{
					int i = 0;
					if(this.myObjects.size() >0){
					for(i =0; i< this.myObjects.size(); i++){
						
						if(this.myObjects.get(i).getType().equals("PowerUp")){
							System.out.println("PowerUp");
							
							return true;}}}
					return false;
				}
				 /**
				 * This method searches for a power up inside the cell and returns the PowerUp object.
				 * @return PowerUp object.
				 */
				public PowerUp searchAPowerUp(){
					int i = 0;
					if(this.myObjects.size() >0){
					for(i =0; i< this.myObjects.size(); i++){
						
						if(this.myObjects.get(i).getType().equals("PowerUp")){
							System.out.println("PowerUp");
							
							return (PowerUp) this.myObjects.get(i);}}}
					return new PowerUp();
				}
				/**
				 * This method remove the flames objects from the cell.	
				 */
	public void removeFlames(){if(this.myObjects.size() > 0){this.myObjects.remove(searchFlameObject());}}

	
	/**
	 * This method returns a boolean whether the cell has a flame object or not.
	 * @return true if the cell has a flame or not.
	 */
	public boolean hasAFlame(){return this.hasAFlame;}
	
	/**
	//Removes the powerUp from the cell
	 */
	public void removePowerUp(){
		
		int i = 0;
		if(this.myObjects.size() >0){
		for(i =0; i< this.myObjects.size(); i++){
			
			if(this.myObjects.get(i).getType().equals("PowerUp")){
				System.out.println("PowerUp");
				
				 this.myObjects.remove(i);}}}
	
	}
	/**
	 * This method returns the bomberman object inside the cell.
	 * @return BomberMan object inside the cell.
	 */
	public BomberMan getBomberMan(){return this.myBomberMan;}
	/**
	 * This method sets the parent Cell type of the cell.
	 * @param cell , the Cell parent object.
	 */
	public void setParent(Cell cell){
		this.parent = cell;
	}
	/**
	 * Thus method returns the Cell parent object of the cell.
	 * @return Cell object
	 */
	public Cell getParent(){
		return this.parent;
	}
	/**
	 * This method sets the current Gscore of the cell, when an enemy has died in the cell.
	 * @param i an integer representing the Gscore.
	 */
	public void setGscore(int i) {
		this.gscore = i;
	}
	/**
	 * This method returns the current Gscore of the cell, when an enemy has died in the cell.
	 * @return an integer representing the Gscore.
	 */
	public int getGScore() {
		return this.gscore;
	}
	/**
	 * This method sets the Fscore given the manhattan distance passed as input.
	 * @param manhattan integer manhattan algorithm distance.
	 */
	public void setFscore(int manhattan) {
		this.fscore = this.gscore + manhattan;
	}
	/**
	 * This method gets the Fscore of the cell, when an enemy has died in the cell.
	 * @return integer representing the FScore of the cell.
	 */
	public int getFscore() {
		return this.fscore;
	}
	/**
	 * This method returns the x position of the cell on the x axis of the game board.
	 * @return integer representing the x position.
	 */
	public int getX(){
		return yPos/CONSTANTS.TILE_SIDE_SIZE;
	}
	/**
	 * This method returns the y position of the cell on the y axis of the game board.
	 * @return integer representing the y position.
	 */
	public int getY(){
		return xPos/CONSTANTS.TILE_SIDE_SIZE;
	}
	/**
	 * This method returns a boolean whether the cell has an ExitDoor1 object or nor.
	 * @return true if the cell has an ExitDoor1 object or not.
	 */
	public boolean searcHasAExit()
	{
		int i = 0;
		if(this.myObjects.size() >0){
		for(i =0; i< this.myObjects.size(); i++){
			
			if(this.myObjects.get(i).getType().equals("ExitDoor")){
				System.out.println("ExitDoor");
				
				return true;}}}
		return false;
	}
	
}