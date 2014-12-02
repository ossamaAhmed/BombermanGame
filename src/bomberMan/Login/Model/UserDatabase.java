/* 
 * File: UserDatabase.java
 * -----------------------
 * This Class represents the General Database of user information for all the users of the game.
 */
/**
 * This class contains functions dealing with reading and writing to the CSVfiles/trial.csv.
 * The user information fields stored in this csv file are: "Name","Password","Username","Score","Level Unlocked"
 * These functions are described in detail below.
 * @author Mariam Khan
 * 
 */
package bomberMan.Login.Model;

import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Console;
import java.io.IOException;
import java.io.FileNotFoundException;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class UserDatabase 

{

	private User currentUser;
	private LeaderBoard currentLeaderBoard;
	private String accountFile;

	public static final int NAME_IND  = 0;
	public static final int PASS_IND  = 1;
	public static final int USER_IND  = 2;
	public static final int SCORE_IND = 3;
	public static final int LEVEL_IND = 4;
	
	/**
	 * Constructor
	 * This method takes care of giving the UserDatabase class the location of the userdatabase csv file and
	 *  the location of the userdatabase csv file and
	 * @param fileName of the location of userdatabase csv file: CSVfiles/trial.csv 
	 */
	public UserDatabase(String fileName) 
	{
		accountFile = fileName;		
	}

	/**
	 * Calling this Setter function sets current user
	 * @param user to be set as current user
	 */
	public void setCurrentUser(User user) {
		currentUser = user;
	}
	
	/**
	 * This method creates a proper database if existing one is empty or doesn't exist
	 * @param accountFile of the location and name of userdatabase csv file to be created: CSVfiles/trial.csv
	 * @return a boolean, true if userdatabase was successfully created, false otherwise 
	 */
	public boolean createDatabase(String accountFile) 
	{
		// Write header in the following format:
		// Name,Password,Username,Score,Level Unlocked

		try {
			// Check if the parent folder exists - create one otherwise
			File f = new File(accountFile);
			if (!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
			}
			// Open up the database file for writing
			CSVWriter writer = new CSVWriter(new FileWriter(accountFile));
			writer.writeNext(new String[]{"Name","Password","Username","Score", "Level Unlocked"});
			writer.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());			
			// Unable to create the file
			return false;
		}        
		return true;        
	}

	
	/**
	 * This method check if a given username already exists in the database on the event when a new user is trying to
	 * open an account in the Bomberman game.
	 * @param user
	 * @return a boolean, true if username already exists, false otherwise
	 */
	public boolean userExists(String user) {
		try {
			CSVReader reader = new CSVReader(new FileReader(accountFile));
			List<String[]> allRecords = reader.readAll();
			reader.close();

			if (allRecords.isEmpty()) {
				// File is empty for some reason. A valid file should have header.
				// Recreate the file with a header.
				createDatabase(accountFile);
				return false;
			}

			allRecords.remove(0); // Remove the header

			// Go through each user in the database
			for (String[] record : allRecords) {
				if (record[USER_IND].equals(user)) {
					return true;
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return false;
	}
	
	/**
	 * This method adds the new user information to the existing database.
	 * @param userData An array of type String containing user information inputted by user from Sign up page
	 * @return a boolean, true if user was successfully created, false otherwise
	 */
	public  boolean createUser(String[] userData) {
		// User data is passed as string array. Data is as follows:
		// * Name
		// * Password
		// * Username
		// * Score
		// * Level Unlocked

		// First try to read the database file and make sure it's not empty
		try {
			CSVReader reader = new CSVReader(new FileReader(accountFile));
		} catch (FileNotFoundException e) {
			// File was not found so create it
			if(!createDatabase(accountFile)) {
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
			CSVWriter writer = new CSVWriter(new FileWriter(accountFile, true));
			writer.writeNext(userData);
			currentUser = new User(userData[0], userData[2], userData[1], 
					Integer.parseInt(userData[3]), Integer.parseInt(userData[4]));
			writer.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());			
			return false;
		}        

		// Create a new directory for the user
		File f = new File(accountFile);
		String userDir = f.getParent() + File.separator + userData[2];
		if(!(new File(userDir).mkdir())) {
			// Unable to create the directory
			return false;
		}
		return true;
	}
	
	/**
	 * This method remove a user from the existing database on the request of the user.
	 * This function is called when the user would like to delete his account.
	 * @param user
	 * @return a boolean, true if user was successfully removed, false otherwise
	 */
	public boolean removeUser(User user) {
		boolean userFound = false;
		String[] record;

		try {
			CSVReader reader = new CSVReader(new FileReader(accountFile));
			List<String[]> allRecords = reader.readAll();

			if (allRecords.size() == 0) {
				// File is empty for some reason. A valid file should have header.
				// Recreate the file with a header.
				createDatabase(accountFile);
				reader.close();
				return false;
			}

			// Go through each user in the database
			for (int i=1; i<allRecords.size(); i++) {
				record = allRecords.get(i);
				if (record[USER_IND].equals(user.getUserName())) {
					allRecords.remove(i);
					userFound = true;
					break;
				}
			}

			reader.close();	        	    	
			if (userFound) {
				// Write the update record list into the file
				CSVWriter writer = new CSVWriter(new FileWriter(accountFile));
				writer.writeAll(allRecords);
				writer.close();
				return true;
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return false;		
	}
	
	/**
	 * This method allows a user to modify his real name and password. The user does not have the option to modify
	 * his username
	 * @param userData An array of type String containing the information the user would like modified
	 * @return a boolean, true if the user information was successfully modified, false otherwise 
	 */
	public boolean modifyProfile(String[] userData) {
		boolean userFound = false;
		String[] record;

		try {
			CSVReader reader = new CSVReader(new FileReader(accountFile));
			List<String[]> allRecords = reader.readAll();

			if (allRecords.size() == 0) {
				// File is empty for some reason. A valid file should have header.
				// Recreate the file with a header.
				createDatabase(accountFile);
				reader.close();
				return false;
			}

			// Go through each user in the database
			for (int i=1; i<allRecords.size(); i++) {
				record = allRecords.get(i);
				if (record[USER_IND].equals(userData[2])) {

					if(!record[NAME_IND].equals(userData[0]))
						record[NAME_IND]=userData[0];

					if(!record[PASS_IND].equals(userData[1]))
						record[PASS_IND]=userData[1];

					userFound = true;
					break;
				}
			}

			if (userFound) {
				// Write the update record list into the file
				CSVWriter writer = new CSVWriter(new FileWriter(accountFile));
				writer.writeAll(allRecords);
				writer.close();
				return true;
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return false;		
	}

	public boolean updateDatabase() {
		return true;
	}
	
	/**
	 * This method updates the current user's score in the user database and current
	 * user object when called.
	 * @param score An integer containing the score field to update
	 * @return a boolean, true if the score was successfully updated, false otherwise
	 */
	public boolean updateScore(int score) {
		boolean userFound = false;
		String[] record;

		try {
			CSVReader reader = new CSVReader(new FileReader(accountFile));
			List<String[]> allRecords = reader.readAll();
			reader.close();

			if (allRecords.size() == 0) {
				// File is empty for some reason. A valid file should have header.
				return false;
			}

			// Go through each user in the database
			for (int i=1; i<allRecords.size(); i++) {
				record = allRecords.get(i);
				if (record[USER_IND].equals(currentUser.getUserName())) {
					// Add to the existing score
					//	int newScore = Integer.parseInt(record[SCORE_IND]) + score;
					record[SCORE_IND] = Integer.toString(score);
					//	currentUser.setScore(newScore);
					userFound = true;
					break;
				}
			}

			if (userFound) {
				// Write the update record list into the file
				CSVWriter writer = new CSVWriter(new FileWriter(accountFile));
				writer.writeAll(allRecords);
				writer.close();
				return true;
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return false;		
	}

	
	/**
	 * This method updates the current user's number of Unlocked levels when called.
	 * @param unlockedLevel An integer containing the unlocked level field to update
	 * @return a boolean, true if the unlocked level was successfully updated, false otherwise 
	 */
	public boolean setUnlockedLevel(int unlockedLevel) {
		boolean userFound = false;
		String[] record;

		try {
			CSVReader reader = new CSVReader(new FileReader(accountFile));
			List<String[]> allRecords = reader.readAll();

			if (allRecords.size() == 0) {
				// File is empty for some reason. A valid file should have header.
				reader.close();
				return false;
			}

			// Go through each user in the database
			for (int i=1; i<allRecords.size(); i++) {
				record = allRecords.get(i);
				if (record[USER_IND].equals(currentUser.getUserName())) {
					// Update the unlocked level
					record[LEVEL_IND] = Integer.toString(unlockedLevel);
					currentUser.setUnlockedLevel(unlockedLevel);
					userFound = true;
					break;
				}
			}


			if (userFound) {
				// Write the update record list into the file
				CSVWriter writer = new CSVWriter(new FileWriter(accountFile));
				writer.writeAll(allRecords);
				writer.close();
				return true;
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return false;		
	}

	/**
	 * This getter function returns the current user
	 * @return User
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	
	/**
	 * This method searches the database csv file to check if the user trying to sign in currently holds an account.
	 * It matches the inputted username and password with the user information in the database.
	 * @param userName
	 * @param password
	 * @return a boolean, true if the login credentials matched, false otherwise 
	 */
	public  boolean login(String userName, String password) {
		try {
			CSVReader reader = new CSVReader(new FileReader(accountFile));
			List<String[]> allRecords = reader.readAll();
			reader.close();

			if (allRecords.isEmpty()) {
				// File is empty for some reason. A valid file should have header.
				// Recreate the file with a header.
				createDatabase(accountFile);
				return false;
			}

			allRecords.remove(0); // Remove the header

			// Go through each user in the database
			for (String[] record : allRecords) {
				if (record[USER_IND].equals(userName) && record[PASS_IND].equals(password)) {
					System.out.println("Score: " + record[SCORE_IND] + "LeveL: " + record[LEVEL_IND]);
					currentUser = new User(record[NAME_IND], record[USER_IND], record[PASS_IND],
							Integer.parseInt(record[SCORE_IND]), 
							Integer.parseInt(record[LEVEL_IND]));
					return true;
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return false;
	}
}
