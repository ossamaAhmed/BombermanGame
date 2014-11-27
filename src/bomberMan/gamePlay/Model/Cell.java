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
	 * This method takes care of the initialization of the ArrayList of gameObjects which will hold 
	 * any gameObject present in this cell except characters.
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
	 * This method takes care of the initialization of the ArrayList of gameObjects which will hold 
	 * any gameObject present in this cell except characters. It takes as an argument an object
	 * to be inserted. This constructor is not used yet. 
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
	 */
	public void insert(GameObject myObject)
	{
		this.myObjects.add(myObject);
	}
	
	/** 
	 * This method removes the specified gameObject from the arraylist of the cell. This method
	 * is not used yet. 
	 */
	public void remove(GameObject myObject)
	{
		this.myObjects.remove(myObject);
	}
	/** 
	 * This method returns an arraylist of all the objects present in this cell.
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
	 */
	public BufferedImage getImage()
	{
			if(!(this.myObjects.get(0) instanceof Enemy))
				return this.myObjects.get(0).getImage();
			return null;
			
	}
	/** 
	 * This method returns a boolean depending if the cell is totally empty and there 
	 * are no gameObjects including characters present in this cell. Characters part should be 
	 * implemented soon. 
	 */
	
	public boolean isEmpty()
	{
		
		return this.myObjects.isEmpty();
	}
	/** 
	 * This method returns a boolean depending if the cell is totally empty and there 
	 * are no gameObjects including characters present in this cell. Characters part should be 
	 * implemented soon. However, it returns empty if a Brick is present 
	 */
	public boolean isEmptyBrickException()
	{
		
		if(this.searcHasABrickWall() == true){return true;}
		if(this.searcHasAConcreteWall() == true){return false;}
		return true;
		
	}
	public boolean isThereAnEnemy()
	{
		for(int i=0;i<myObjects.size();i++)
		{
			if(myObjects.get(i) instanceof Enemy)
				return true;
		}
		return false;
	}
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
	 * This method returns a boolean depending if the cell is totally empty and there 
	 * are no gameObjects including characters present in this cell. Characters part should be 
	 * implemented soon. However, it returns empty if a power up is present 
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
	public boolean isEmptyBombException(){
		
		if(this.getHasABomb() == true){return false;}
		return this.isEmpty();
	}
	
	//sets the boolean hasABomb of the cell
	public void setHasABomb(boolean set){this.hasABomb = set;}
	
	//sets the boolean hasADetonatorBomb of the cell
	public void setHasADetonatorBomb(boolean set){this.hasADetonateBomb = set;}
	//returns true if the cell has  a Bomb and false if not
	public boolean getHasABomb(){
		if(this.hasADetonateBomb == true){return false;}
		return this.hasABomb;}
	//returns true if the cell has  a Detonator Bomb and false if not
	public boolean getHasADetonateBomb(){return this.hasADetonateBomb;}
	public Bomb searchBomb(){
		int i = 0;
		for(i =0; i< this.myObjects.size(); i++){
			
			if(this.myObjects.get(i).getType().equals("Bomb")){
				System.out.println("BombFound");
				
				return (Bomb)this.myObjects.get(i);}}
		return new Bomb();
	}
	//deletes the element corresponding to the type String.
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
	//add a flame GameObject to the cell
	public void setFlameImages(){
		
		this.hasAFlame = true;
	    this.insert( new GameObject(this.yPos, this.xPos,CONSTANTS.Bomb_EXPLOSION, "Flame"));
	    
	    
	}
	//search for a flame object in the cell
	public int searchFlameObject(){
		int i = 0;
		if(this.myObjects.size() >0){
		for(i =0; i< this.myObjects.size(); i++){
			
			if(this.myObjects.get(i).getType().equals("Flame")){
				System.out.println("FlamesFound");
				
				return i;}}}
		return 0;
		
		
	}
	public boolean searchTheCell(String search)
	{
		if(search.equals("CONCRETE")&&searcHasAConcreteWall())
			return true;
		if(search.equals("BRICK")&&searcHasABrickWall())
			return true;
		if(search.equals("POWERUP")&&searcHasAPowerUp())
			return true;
		if(search.equals("BOMB")&&getHasABomb())
			return true;
		
		return false;
	}
	//search for a concrete wall in the cell
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
	//search for a brick wall in the cell
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
		//search for a powerup in the cell
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
				//return the powerup contained in the cell.
				public PowerUp searchAPowerUp(){
					int i = 0;
					if(this.myObjects.size() >0){
					for(i =0; i< this.myObjects.size(); i++){
						
						if(this.myObjects.get(i).getType().equals("PowerUp")){
							System.out.println("PowerUp");
							
							return (PowerUp) this.myObjects.get(i);}}}
					return new PowerUp();
				}
	//removes the flames from the cell
	public void removeFlames(){if(this.myObjects.size() > 0){this.myObjects.remove(searchFlameObject());}}

	
	//verifies if the cuttent cell has a flame
	public boolean hasAFlame(){return this.hasAFlame;}
	
	//changes
	//removes the powerup from the cell
	public void removePowerUp(){
		
		int i = 0;
		if(this.myObjects.size() >0){
		for(i =0; i< this.myObjects.size(); i++){
			
			if(this.myObjects.get(i).getType().equals("PowerUp")){
				System.out.println("PowerUp");
				
				 this.myObjects.remove(i);}}}
	
	}
	public BomberMan getBomberMan(){return this.myBomberMan;}
	
	//get set parent cell for astar search
	public void setParent(Cell cell){
		this.parent = cell;
	}
	//get parent to help return the path
	public Cell getParent(){
		return this.parent;
	}
	//set steps score
	public void setGscore(int i) {
		this.gscore = i;
	}
	public int getGScore() {
		return this.gscore;
	}
	//set f score, to determine astar pathfinding
	public void setFscore(int manhattan) {
		this.fscore = this.gscore + manhattan;
	}
	public int getFscore() {
		return this.fscore;
	}
	// get X, Y coordinates
	public int getX(){
		return yPos/CONSTANTS.TILE_SIDE_SIZE;
	}
	public int getY(){
		return xPos/CONSTANTS.TILE_SIDE_SIZE;
	}
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
