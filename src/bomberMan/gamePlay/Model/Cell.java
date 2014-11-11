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

public class Cell {
	
	/*Instance Variables*/
	boolean hasABomb = false;
	boolean isEmpty = true;
	boolean hasADetonateBomb = false;
	boolean hasAPowerUp = false;
	boolean hasADestructibleBrick = false;
	Calendar timer;
	
	private ArrayList<GameObject> myObjects;
	
	/** 
	 * Constructor
	 * This method takes care of the initialization of the ArrayList of gameObjects which will hold 
	 * any gameObject present in this cell except characters.
	 */
	public Cell()
	{
		this.myObjects=new ArrayList<GameObject>();
		this.isEmpty = true;
		timer = Calendar.getInstance();
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
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(this.myObjects.get(0).getImageLocation()));
		    }
		catch (IOException e) 
		{
			System.out.println("Not found");
		}

		return image;
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
	public void setHasABomb(boolean set){this.hasABomb = set;}
	public boolean getHasABomb(){return this.hasABomb;}
	public Bomb searchBomb(){
		int i = 0;
		for(i =0; i< this.myObjects.size(); i++){
			
			if(this.myObjects.get(i).getType().equals("Bomb")){
				System.out.println("BombFound");
				
				return (Bomb)this.myObjects.get(i);}}
		return new Bomb();
	}
	public void deleteElement(String type){
		int i = 0;
		for(i =0; i< this.myObjects.size(); i++){
			
			if(this.myObjects.get(i).getType().equals(type)){
				if(this.myObjects.get(i).getType().equals("Bomb")){
				    long start = timer.getTimeInMillis();
				    long finish = start + CONSTANTS.BOMB_EXPLOSION_SHOWING_TIME;
				    this.myObjects.get(i).setImageLocation(CONSTANTS.Bomb_EXPLOSION);
				   System.out.println("Start showing bomb explosion "+ start);
				   System.out.println("Finish showing bomb explosion "+ finish);
				    while(start <= finish){
				
				    System.out.println("Showing explosion " + type);
					start = start + 10;
					System.out.println("Time showing bomb explosion "+ start);
				    }}
				System.out.println("Deleting " + type);
			this.myObjects.remove(i);
			
			this.hasABomb = false;
			this.hasADetonateBomb = false;
			this.hasAPowerUp = false;
			this.hasADestructibleBrick = false;
				}
						}
		
	}
	public boolean getIsEmpty(){return this.isEmpty;}
	public void setIsEmpty(boolean set){this.isEmpty = set;}
	
	
}
