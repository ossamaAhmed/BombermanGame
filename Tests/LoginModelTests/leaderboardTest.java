package LoginModelTests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import au.com.bytecode.opencsv.CSVWriter;
import bomberMan.Login.Model.LeaderBoard;
import bomberMan.Login.Model.Record;
import bomberMan.Login.Model.User;
import bomberMan.Login.Model.UserDatabase;

public class leaderboardTest {
	
	String[] user1 = {"Natalie Portman","Blackswan1@","natalieportman","500","1"};
	String[] user2 = {"Keira Knightley","Beginagain2#","keiraknightley","100","1"};
	String[] user3 = {"Sandra Bullock","Gravity!2","sandrab","1000","1"};
		
	
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	private File csvFile;
	private UserDatabase userdatabase;

@Before
	public void createTestData() throws IOException {
	
		csvFile = folder.newFile("trials_test.csv");
		LeaderBoard.setDBFile(csvFile.getAbsolutePath());
// Open up the database file in write append
CSVWriter writer = new CSVWriter(new FileWriter(csvFile));
//writer.writeNext(userData1);
writer.writeNext(new String[]{"Name","Password","Username","Score", "Level Unlocked"});

writer.writeNext(user1);
writer.writeNext(user2);
writer.writeNext(user3);

writer.close();
	}
	

	@Test
	public void testGetTopTenUsers() {
		ArrayList<Record> TopScores = LeaderBoard.getTopTenUsers();
		
		assertTrue(TopScores.get(0).getScore() == Integer.parseInt(user3[UserDatabase.SCORE_IND]));
		assertEquals(TopScores.get(0).getUser(), user3[UserDatabase.USER_IND]);
		
		assertTrue(TopScores.get(1).getScore() == Integer.parseInt(user1[UserDatabase.SCORE_IND]));
		assertEquals(TopScores.get(1).getUser(), user1[UserDatabase.USER_IND]);
		
		assertTrue(TopScores.get(2).getScore() == Integer.parseInt(user2[UserDatabase.SCORE_IND]));
		assertEquals(TopScores.get(2).getUser(), user2[UserDatabase.USER_IND]);
	}

}
