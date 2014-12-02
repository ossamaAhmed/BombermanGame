package LoginModelTests;

import static org.junit.Assert.*;

import org.junit.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import bomberMan.Login.Model.*;

public class UserdatabaseTest {
	
	public static final int NAME_IND  = 0;
	public static final int PASS_IND  = 1;
	public static final int USER_IND  = 2;
	public static final int SCORE_IND = 3;
	public static final int LEVEL_IND = 4;
	
	String[] userExistsData = {"Natalie Portman","Blackswan1@","natalieportman","0","1"};
	String[] modifiedUserData = {"Neta-Lee Hershlag","Thor123$","natalieportman","0","1"};
	String[] userRemoveData = {"Keira Knightley","Beginagain2#","keiraknightley","0","1"};
	String[] userAddData = {"Sandra Bullock","Gravity!2","sandrab","0","1"};
		
    User userInfoUpdate = new User("", "natalieportman", "", 0, 1);
	User userToRemove = new User("Keira Knightley","keiraknightley","Beginagain2#",0,1);
	
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	private File csvFile;
	private UserDatabase userdatabase;
	
	@Before
	public void createTestData() throws IOException {
	
		csvFile = folder.newFile("trials_test.csv");
		 userdatabase = new UserDatabase(csvFile.getAbsolutePath());
			
// Open up the database file in write append
CSVWriter writer = new CSVWriter(new FileWriter(csvFile));
//writer.writeNext(userData1);
writer.writeNext(new String[]{"Name","Password","Username","Score", "Level Unlocked"});

writer.writeNext(userExistsData);
writer.writeNext(userRemoveData);

writer.close();
	}
	    
	@Test
	public void testCreateUserDatabase() throws IOException {
		// Since createDatabase is a private function, test it through
		// the public function createUser
		File rootFolder = folder.getRoot();
		String tempDBStr = rootFolder.getAbsolutePath() + File.separator + "db_test.csv";
		File tempDBFile = new File(tempDBStr);
		UserDatabase userDB = new UserDatabase(tempDBStr);
		
		userDB.createUser(userAddData);

	    assertTrue(tempDBFile.exists());
	}
	
	@Test
	public void testCreateUser(){
		boolean userFound = false;
		String[] record = {};
		boolean check = userdatabase.createUser(userAddData);
		assertTrue(check);

		try {
			CSVReader reader = new CSVReader(new FileReader(csvFile));
			List<String[]> allRecords = reader.readAll();
			reader.close();

			allRecords.remove(0);

			// Go through each user in the database
			for (int i=1; i<allRecords.size(); i++) {
				record = allRecords.get(i);
				if (record[USER_IND].equals(userAddData[USER_IND])) {
					userFound = true;
					break;
				}
			}

			// Make sure the added user was found
			assertTrue(userFound);

			// Make sure added user data is correct
			assertEquals(record[NAME_IND],userAddData[NAME_IND]);
			assertEquals(record[PASS_IND],userAddData[PASS_IND]);
			assertEquals(record[USER_IND],userAddData[USER_IND]);

			// Make sure the user directory was created
			String userDir = csvFile.getParent() + File.separator + userAddData[USER_IND];
			File f = new File(userDir);
			assertTrue(f.exists());

		} catch (IOException e) {
				System.out.println(e.getMessage());
			}


	}
	
	@Test
	public void testUserExists(){
		boolean check = userdatabase.userExists(userExistsData[USER_IND]);
		assertTrue(check);
		
	}
	
	@Test
	public void testRemoveUser(){
		boolean check = userdatabase.removeUser(userToRemove);
		assertTrue(check);
		
	}
	
	@Test
	public void testModifyProfile(){

		boolean check = userdatabase.modifyProfile(modifiedUserData);
		assertTrue(check);		
	}
	
	@Test
	public void testUpdateScore() {
		userdatabase.setCurrentUser(userInfoUpdate);
		boolean check = userdatabase.updateScore(100);
		assertTrue(check);
		
		String[] record;
		try {
		CSVReader reader = new CSVReader(new FileReader(csvFile));
		List<String[]> allRecords = reader.readAll();
		reader.close();

		allRecords.remove(0);

		// Go through each user in the database
		for (int i=1; i<allRecords.size(); i++) {
			record = allRecords.get(i);
			if (record[USER_IND].equals(userInfoUpdate.getUserName())) {
				assert(Integer.parseInt(record[SCORE_IND]) == 100);
				break;
			}
		}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		//this.DB.updateScore(this.DB.getCurrentUser().getMyScore());
		
	}
	
	@Test
	public void testSetUnlockedLevel(){
		
		userdatabase.setCurrentUser(userInfoUpdate);
		boolean check = userdatabase.setUnlockedLevel(10);
		assertTrue(check);
		
		String[] record;
	try {
		CSVReader reader = new CSVReader(new FileReader(csvFile));
		List<String[]> allRecords = reader.readAll();
		reader.close();

		allRecords.remove(0);

		// Go through each user in the database
		for (int i=1; i<allRecords.size(); i++) {
			record = allRecords.get(i);
			if (record[USER_IND].equals(userInfoUpdate.getUserName())) {
				assert(Integer.parseInt(record[LEVEL_IND]) == 100);
				break;
			}
		}
	} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	@Test
	public void testLogin(){
		//Test Login using existing username and password
		boolean check1 = userdatabase.login(userExistsData[USER_IND],userExistsData[PASS_IND]);
		assertTrue(check1);
		
		//Existing user but wrong password
		boolean check3 = userdatabase.login("natalieportman","wrongpassword1@");
		assertFalse(check3);
		
		
		
	}
	
    @After
    public void cleanUp() {
       assertTrue(csvFile.exists());
    }
}
