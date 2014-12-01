
package LoginControllerTests;
import static org.junit.Assert.*;
import bomberMan.Login.Model.*;
import org.junit.Before;
import org.junit.Test;
 

import bomberMan.Login.Controller.LoginController;
 
public class LoginControllerTest {
	
	String databasePath = "CSVfiles/databaseLoginTest.csv";
	UserDatabase userdatabase = new UserDatabase(databasePath);
	boolean userDB = userdatabase.createDatabase(databasePath);
    LoginController loginController = new LoginController(null);
    private String correctUsername = "Demo01";
    private String wrongUsername = "qweqwewq";
    private String correctPW = "Dem@Us3R01";
    private String wrongPW = "qeqweqwe1";
    
    @Test
    public void LoginTest() {
        assertTrue(loginController.login(this.wrongUsername, this.wrongPW) == 1);
        assertTrue(loginController.login(this.correctUsername, this.correctPW) == 0);
        assertTrue(loginController.login(this.wrongUsername, this.correctPW) == 1);
        assertTrue(loginController.login(this.correctUsername, this.wrongPW) == 1);
    }
 
     
 
}