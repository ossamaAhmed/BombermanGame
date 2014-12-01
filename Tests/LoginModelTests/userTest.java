package LoginModelTests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import bomberMan.Login.Model.User;
import bomberMan.gamePlay.Model.Enemy;
import bomberMan.gamePlay.Model.GameBoard;

public class userTest {
	
	User user = new User("Natalie Portman","natalieportman","Blackswan1@", 0, 1);

	String[] savedGame1 = {"game1","2014_11_26_20_24_59"};
	String[] savedGame2 = {"game2","2014_11_26_21_21_10"};
	String[] savedGame3 = {"game3","2014_11_27_21_21_07"};
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	private File gameListFile;

	@Before
	public void createTestData() throws IOException {

		folder.newFolder(user.getUserName());
		gameListFile = folder.newFile(user.getUserName() + File.separator + "savedGames.txt");
		user.setRootFolder(folder.getRoot().getAbsolutePath());

		// Open up the database file in write append
		CSVWriter writer = new CSVWriter(new FileWriter(gameListFile));
		//writer.writeNext(userData1);
		writer.writeNext(new String[]{"Game Name","Date/Time"});
		writer.writeNext(savedGame1);
		writer.close();
	}

	@Test
	public void testCreateSaveGameDB() throws IOException {
		//savedGameDB is a private function so test it by calling
		// the public function addSavedGame for a new user
		File rootFolder = folder.getRoot();
		User user2 = new User("N P","tempuser","temppass", 0, 1);
		user2.setRootFolder(rootFolder.getAbsolutePath());
		
		// Create a user folder, otherwise createSaveGameDB will fail
		folder.newFolder(user2.getUserName());
		
		// Add a temporary save game. We don't care about  it as long as
		// this function causes the save game database file to be created
		boolean check = user2.addSavedGame("testgame", "testdate");
		assertTrue(check);
		
		// Get handle to the database file name
		File tempDBFile = new File (rootFolder.getAbsolutePath() + File.separator + user2.getUserName() + 
				File.separator + "savedGames.txt");
		
		assertTrue(tempDBFile.exists());
	}
	
	@Test
	public void testAddSavedGame() {
		boolean gameFound = false;
		String[] record = {};

		boolean check = user.addSavedGame(savedGame2[User.GAMENAME_IND], savedGame2[User.DT_IND]);
		assertTrue(check);

		try {
			CSVReader reader = new CSVReader(new FileReader(gameListFile));
			List<String[]> allRecords = reader.readAll();
			reader.close();
		
			allRecords.remove(0);

			// Go through each user in the database
			for (int i=1; i<allRecords.size(); i++) {
				record = allRecords.get(i);
				if (record[User.GAMENAME_IND].equals(savedGame2[User.GAMENAME_IND])) {
					gameFound = true;
					break;
				}
			}

			// Make sure the added game was found
			assertTrue(gameFound);

			// Make sure added game data is correct
			assertEquals(record[User.GAMENAME_IND],savedGame2[User.GAMENAME_IND]);
			assertEquals(record[User.DT_IND],savedGame2[User.DT_IND]);

		} catch (IOException e) {
				System.out.println(e.getMessage());
			}
	}
	
	@Test
	public void testSaveGame() {
		int[] stages = new int[]{1,2,3,4,5,6,7,8,9};
		GameBoard myBoard = new GameBoard(1, stages, new int[]{1,2,3},1, user, (long)1);
		
		String saveGameTime = user.saveGame(myBoard, savedGame3[User.GAMENAME_IND]);
		
	   String gameFileName = folder.getRoot().getAbsolutePath() + File.separator + 
	   user.getUserName() + File.separator + savedGame3[User.GAMENAME_IND] + 
	   "_" + saveGameTime + ".ser";
	   
	   File gameFile = new File(gameFileName);
	   assertTrue(gameFile.exists());
	}
	
	@Test
	public void testGameExists() {
		String game = user.gameExists(savedGame1[User.GAMENAME_IND]);
		assertNotEquals(game, " ");
	}
	
	@Test
	public void testLoadSavedGame() {
		String gameFileName = user.loadSavedGame(savedGame1[User.GAMENAME_IND]);
		assertEquals(gameFileName, folder.getRoot() + File.separator + user.getUserName() + 
				File.separator + savedGame1[User.GAMENAME_IND] + "_" + savedGame1[User.DT_IND] + ".ser");
	}
	
	@Test
	public void testLoadSavedGames() {
		ArrayList<String> loadedGames = user.loadSavedGamesList(user.getUserName());
		assertTrue(loadedGames != null);
	}
	
	@Test
	public void testCalculateMyScore() {
		ArrayList<Enemy> enemiesList = new ArrayList<Enemy>();
		Enemy myEnemy = new Enemy(0,0,1,"enemy1");
		myEnemy.setScore(10);
		Enemy myEnemy2 = new Enemy(0,0,1,"enemy2");
		myEnemy2.setScore(20);
		Enemy myEnemy3 = new Enemy(0,0,1,"enemy3");
		myEnemy3.setScore(40);
		
		enemiesList.add(myEnemy);
		enemiesList.add(myEnemy2);
		enemiesList.add(myEnemy3);
		
		int oldScore = user.getMyScore();
		user.calculateMyScore(enemiesList);
		int newScore = user.getMyScore();
		assertEquals(newScore-oldScore,170); // (10*1) + (20*2) + (40*3)
	}
	
	
    @After
    public void cleanUp() {
       assertTrue(gameListFile.exists());
    }
}