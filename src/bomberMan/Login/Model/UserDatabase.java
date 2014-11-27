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
	private Game currentGame;
	private LeaderBoard currentLeaderBoard;
	private String accountFile;

	public static final int NAME_IND  = 0;
	public static final int PASS_IND  = 1;
	public static final int USER_IND  = 2;
	public static final int SCORE_IND = 3;
	public static final int LEVEL_IND = 4;
	
	public UserDatabase(String fileName) 
	{
		accountFile = fileName;		
	}
	
	/*
	 * Create a proper database if existing one is empty or doesn't exist
	 */
	private boolean createDatabase(String accountFile) 
	{
		// Write header in the following format:
		// Name,Password,Username,Score
		
		try {
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
	
	/*
	 * Check if a given username already exists in the database
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
	/*
	 * Add a new user to the existing database
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
	        currentUser = new User(userData[0], userData[1], userData[2], 
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

	/*
	 * Remove a user from the existing database
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
	
	public boolean updateScore(int score) {
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
	            	// Add to the existing score
	            	int newScore = Integer.parseInt(record[SCORE_IND]) + score;
	            	record[SCORE_IND] = Integer.toString(newScore);
	            	currentUser.setScore(newScore);
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
	
	public User getCurrentUser() {
		return currentUser;
	}

	/*
	 * Search the database and return true if given username and password is found
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
