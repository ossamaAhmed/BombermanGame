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
	public static final int GAMENAME_IND = 0;
	public static final int DT_IND=1;
	
	public User(String fullName, String userName, String password, int score, int unlockedLevel)
	{
		this.name = fullName;
		this.userName=userName;
		this.password=password;
		this.score = score;
		this.unlockedLevel = unlockedLevel;
	}
	
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
	public void saveGame(GameBoard myBoard, String saveGameName)
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
			   String gameFileName = "CSVfiles" + File.separator + 
					   userName+ File.separator + saveGameName + 
					   "_" + saveGameTime + ".ser";

			   addSavedGame(saveGameName, saveGameTime);
			   
			   OutputStream fileOut = new FileOutputStream(gameFileName); //"./CSVfiles/TRIAL2.ser");
			   OutputStream buffer = new BufferedOutputStream(fileOut);
			   ObjectOutputStream out = new ObjectOutputStream(buffer);
		       out.writeObject(myBoard);
		       out.flush();
		       out.close();
		       fileOut.close();
		       System.out.println("Serialized data is saved in " + gameFileName);
			}
		       catch(IOException i)
		    {
		        i.printStackTrace();
		    }

	}
	
	/*
	 * Add a saved game to the saved game list
	 * 
	 */
	public boolean addSavedGame(String gameName, String dateTime) {
		
		
		String fileName = "CSVfiles" + File.separator + userName + File.separator + "savedGames.txt";

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
		public String gameExists(String loadGameName) {
		
		String fileName = "CSVfiles" + File.separator +this.userName + File.separator + "savedGames.txt";
		
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
		/*
		 * Load a saved game from the user's saved game list
		 */
		public String loadSavedGame(String loadGameName) {
			
			String AppendedGameName = gameExists(loadGameName);
			System.out.println("Got game:");
			System.out.println("CSVfiles" + File.separator + this.userName + File.separator + AppendedGameName + ".ser");
			   return ("CSVfiles" + File.separator + this.userName + File.separator + AppendedGameName + ".ser");
			   		
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
	

}
