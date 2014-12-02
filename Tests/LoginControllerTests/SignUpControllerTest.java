package LoginControllerTests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import bomberMan.Login.Controller.SignUpController;
import bomberMan.Login.Model.User;
import bomberMan.Login.Model.UserDatabase;

public class SignUpControllerTest {

	/*
	 * SignUpTest checks for each scenario. The first which is the success
	 * scenario. Second which is the username already exists. Third which checks
	 * for password strength. Fourth checks for empty spaces. Fifth for matching
	 * passwords.
	 */

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	private File csvFile;
	private UserDatabase userdatabase;
	SignUpController signUpController;
	
	@Before
	public void createDatabase() throws IOException {

		csvFile = folder.newFile("trials_test.csv");
		userdatabase = new UserDatabase(csvFile.getAbsolutePath());
		System.out.println(csvFile.getAbsolutePath());
		// Open up the database file in write appendt
		CSVWriter writer = new CSVWriter(new FileWriter(csvFile));
		// writer.writeNext(userData1);
		String[] title = { "Name", "Password", "Username", "Score",
				"Level Unlocked" };
		String[] user = { "TY", "Qwerty1!", "yito", "0", "1" };

		writer.writeNext(title);

		writer.writeNext(user);

		writer.close();
		signUpController = new SignUpController(userdatabase);
	}

	 

	@Test
	public void SignUpTest() {
		int expected = 0;
		int actual = signUpController.signUp("T Y", "yito1", "Qwerty1!",
				"Qwerty1!");
		assertEquals(expected, actual);

		expected = 1;
		actual = signUpController.signUp("T Y", "yito", "Qwerty1!", "Qwerty1!");
		assertEquals(expected, actual);

		expected = 2;
		actual = signUpController.signUp("T Y", "yito2", "Qwert", "Qwert");
		assertEquals(expected, actual);

		expected = 3;
		actual = signUpController.signUp("", "yito3", "Qwerty1!", "Qwerty1!");
		assertEquals(expected, actual);

		expected = 4;
		actual = signUpController.signUp("T Y", "yito4", "Qwe", "Qwerty1!");
		assertEquals(expected, actual);

	}

	@After
	public void cleanUp() {
		assertTrue(csvFile.exists());
	}

}
