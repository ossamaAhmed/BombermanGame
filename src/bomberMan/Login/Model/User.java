/* 
 * File: User.java
 * -----------------------
 * This Class contains all the information unique to each user in the game
 */
/**
 * This class contains functions dealing with storing and loading the user's games
 * It also deals with calculating the user's score everytime an enemy or a number of enemies are killed by a bomb 
 * This class also has a csv file for saving games but this one is specific to each user and contains the
 * fields: "Game Name","Date/Time".
 * It is stored at CSVFiles\ userName\savedGames.txt where username stores the current user's username
 * These functions are described in detail below.
 * @author Mariam Khan
 * 
 */
package bomberMan.Login.Model;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import bomberMan.Login.View.LoginView;
import bomberMan.gamePlay.Model.Enemy;
import bomberMan.gamePlay.Model.GameBoard;


public class User 
{
	//private variables
	private ArrayList <GameBoard> mySavedGames=new ArrayList<GameBoard>();
	private ArrayList <Record> myRecords=new ArrayList<Record>();
	private String name;
	private String userName;
	private String password;
	private int score;
	private int unlockedLevel;
	private String rootFolder;
	public static final int GAMENAME_IND = 0;
	public static final int DT_IND=1;
	public static final int GAME_IND  = 0;
	
	public User(String fullName, String userName, String password, int score, int unlockedLevel)
	{
		this.name = fullName;
		this.userName=userName;
		this.password=password;
		this.score = score;
		this.unlockedLevel = unlockedLevel;
		this.rootFolder = "CSVFiles";
	}
	
	
	/** 
	 * This method creates a saved games database for new users in the directory CSVfiles\ username where username is 
	 * the player's created username
	 * @param String filename gives the location where the savedGames.txt database should be created 
	 * @return a boolean, true if the savedGames.txt database was created, false otherwise 
	 */
	private boolean createSaveGameDB(String fileName) 
	{
		// Write header in the following format:
		// Name,Password,Username,Score
		
		try {
			// Open up the database file for writing
	        CSVWriter writer = new CSVWriter(new FileWriter(fileName));
	        writer.writeNext(new String[]{"Game Name","Date/Time"});
	        writer.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());			
			// Unable to create the file
			return false;
		}        
		return true;        
	}
	
	//save game function
	
	/** 
	 * This method saves the current game of the user under the directory CSVfiles\ username where username is 
	 * the player's created username
	 * It saves it in the format of GameName_Date_Time where the Date_Time format is yyyy_MM_dd_HH_mm_ss
	 * @param String filename gives the location where the savedGames.txt database should be created 
	 * @param GameBoard myBoard is the current gameBoard of the user
	 * @return a boolean, true if the savedGames.txt database was created, false otherwise 
	 */
	public String saveGame(GameBoard myBoard, String saveGameName)
	{
		try{
			
			   DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			   //get current date time with Date()
			   Date date = new Date();
			  // String date2 Format.format(date);
			 
			   //get current date time with Calendar()
			   Calendar cal = Calendar.getInstance();
			   System.out.println(dateFormat.format(cal.getTime()));
				
			   // Display a dialog box asking user for the saved game name
			   String saveGameTime = dateFormat.format(cal.getTime());
			   String gameFileName = rootFolder + File.separator + 
					   userName+ File.separator + saveGameName + 
					   "_" + saveGameTime + ".ser";
			   
			   //This function is implemented below
			   addSavedGame(saveGameName, saveGameTime);
			   
			   OutputStream fileOut = new FileOutputStream(gameFileName);
			   OutputStream buffer = new BufferedOutputStream(fileOut);
			   ObjectOutputStream out = new ObjectOutputStream(buffer);
		       out.writeObject(myBoard);
		       out.flush();
		       out.close();
		       fileOut.close();
		       System.out.println("Serialized data is saved in " + gameFileName);
		       return saveGameTime;
			}
		       catch(IOException i)
		    {
		        i.printStackTrace();
		    }
		return "";
	}
	
	
	/** 
	 * This method adds the user's saved game name in the savedGames.txt database in the directory CSVfiles\ username
	 * It saves it under two separate columns "Game Name","Date/Time"
	 * @param String gameName is the user's inputted GameName
	 * @param String dateTime is the datestamp of when the Game was saved
	 * @return a boolean, true if the saved Game details were successfully appended to savedGames.txt, false otherwise
	 */
	public boolean addSavedGame(String gameName, String dateTime) {
		
	
		String fileName = rootFolder + File.separator + userName + File.separator + "savedGames.txt";

			// First try to read the database file and make sure it's not empty
			try {
				CSVReader reader = new CSVReader(new FileReader(fileName));
				} catch (FileNotFoundException e) {
					// File was not found so create it
					if(!createSaveGameDB(fileName)) {
						// Database creation failed
						return false;
					}
				} catch (IOException e) {
					System.out.println(e.getMessage());
					return false;
				}
			
			// Now that we know database file is there, append a new username to it
			try {
				// Open up the database file in write append
		        CSVWriter writer = new CSVWriter(new FileWriter(fileName, true));
		        writer.writeNext(new String[]{gameName, dateTime});
		        writer.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());			
				return false;
			}        

			return true;		
	}
	
	/** 
	 * This method checks if a game name already exists in the case when a user wants to create a new saved game name
	 * or when a user wants to load a saved game. The savedGames.txt file is checked under the directory
	 * CSVfiles\ username
	 * @param String loadGameName is the user's inputted GameName which is being checked for
	 * @return a String is returned if successful of the GameName appended with the date-time stamp, otherwise returns empty String
	 */
		public String gameExists(String loadGameName) {
		
		String fileName = rootFolder + File.separator +this.userName + File.separator + "savedGames.txt";
		
		try {
	        CSVReader reader = new CSVReader(new FileReader(fileName));
	        List<String[]> allRecords = reader.readAll();
        	reader.close();
        	
	        	
	        allRecords.remove(0); // Remove the header
	        
	        // Go through each game in the database
	        for (String[] record : allRecords) {
	            if (record[GAMENAME_IND].equals(loadGameName)) {
	            	return (record[GAMENAME_IND]+"_"+record[DT_IND]);
	            }
	        }
		}
		catch (IOException e) {
			System.out.println(e.getMessage());	
		}
		return " ";

	}

		/** 
		 * This method loads a saved game from the user's saved game list
		 * @param String loadGameName is the user's inputted GameName which is being checked for
		 * @return a String of the file location of the Game to be loaded
		 */
		public String loadSavedGame(String loadGameName) {
			
			String AppendedGameName = gameExists(loadGameName);
			System.out.println("Got game:");
			System.out.println(rootFolder + File.separator + this.userName + File.separator + AppendedGameName + ".ser");
			   return (rootFolder + File.separator + this.userName + File.separator + AppendedGameName + ".ser");
			   		
		}
		
		/** 
		 * This method returns the list of saved games of a user saved under savedGames.txt
		 * @param String username of the user whose saved game list needs to be returned
		 * @return an Arraylist of Type String with all the saved game names. Only returns the Game Names and not the
		 * Date/Time stamp
		 */
		public ArrayList<String> loadSavedGamesList(String user){

			ArrayList<String> savedGames = new ArrayList<String>();

			String fileName = rootFolder + File.separator + user + File.separator + "savedGames.txt";

			// First try to read the database file and make sure it's not empty
					try {
						CSVReader reader = new CSVReader(new FileReader(fileName));
			        
				        // Go through each user in the database
						List<String[]> allRecords = reader.readAll();

				        
				        for (String[] record : allRecords) {
							savedGames.add(record[GAME_IND]);		        	
				       
						} 
					}catch (FileNotFoundException e) {
							// File was not found so create it
							if(!createSaveGameDB(fileName)) {
								// Database creation failed
								System.out.println("No saved games exist");
							}
							return savedGames;
						} catch (IOException e) {
							System.out.println(e.getMessage());
							return savedGames;
						}


		return savedGames;
		}				
		
	public String getFullName() {
		return this.name;
	}
	
	public void setFullName(String name) {
		this.name = name;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getUnlockedLevel() {
		return unlockedLevel;
	}
	
	public void setUnlockedLevel(int unlockedLevel) {
		this.unlockedLevel = unlockedLevel;
	}
	
	/** 
	 * This method calculates the score of the current user based on the number of enemies killed with one bomb
	 * @param ArrayList of Enemy objects killed by the bomb 
	 * @return void
	 */
	public void calculateMyScore(ArrayList<Enemy> killedEnemies)
	{

		
		for(int i=0;i<killedEnemies.size();i++){
			this.score += (killedEnemies.get(i).getScore())*(i+1);
		}
		
	}
	public int getMyScore()
	{
		return this.score;
	}
	
	public void setRootFolder(String folderName) {
		this.rootFolder = folderName;
	}

}
