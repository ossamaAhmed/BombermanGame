/* 
 * File: LeaderBoard.java
 * -----------------------
 * This Class takes care of arranging the users' records according to their scores in descending order
 */
package bomberMan.Login.Model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class LeaderBoard {


	private static ArrayList<Record> recordTopTenUsers = new ArrayList<Record>();
	private static Record myRecord;	

	private static String file = "./CSVfiles/trial.csv";
	
	/**
	 * This function returns an Arraylist of Record objects having all the Scores in descending order
	 * @return Arraylist of Record objects
	 */
	public static ArrayList<Record> getTopTenUsers() {
		try {
			CSVReader reader = new CSVReader(new FileReader(file));
			List<String[]> allRecords = reader.readAll();
			reader.close();

			if (allRecords.isEmpty()) {
				// File is empty for some reason. A valid file should have header.
				// Recreate the file with a header.
				//	        	createDatabase(accountFile);
				return recordTopTenUsers;
			}

			allRecords.remove(0); // Remove the header

			// Go through each user in the database
			for (String[] record : allRecords) {
				myRecord = new Record(Integer.parseInt(record[UserDatabase.SCORE_IND]), 
						record[UserDatabase.USER_IND]);
				recordTopTenUsers.add(myRecord);
			}
			// Sort all the records in descending order, by score
			Collections.sort(recordTopTenUsers, Record.COMPARE_BY_SCORE);

			// Delete all the records beyond 10 to only give top 10
			if (recordTopTenUsers.size() > 10) {
				recordTopTenUsers.subList(10, recordTopTenUsers.size()).clear();
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return recordTopTenUsers;
	}

	/**
	 * This setter function sets the filepath of the database file
	 * @param filePath
	 */
	public static void setDBFile(String filePath) {
		file = filePath;
	}


}
