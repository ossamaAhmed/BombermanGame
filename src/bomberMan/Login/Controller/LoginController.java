package bomberMan.Login.Controller;

import bomberMan.Login.Model.UserDatabase;

/**
 * This class contains a method for checking whether the login is successful or
 * not.
 * 
 * <p>
 * Integers are used as the returned item, to help differentiate different
 * errors in all login controller class.
 * 
 * @author Tong Yi
 *
 */

public class LoginController {
	private UserDatabase DB;

	public LoginController(UserDatabase database) {
		this.DB = database;
	}

	/**
	 * Checks whether or not the login was successful, using the given username
	 * and password.
	 * 
	 * @param username
	 *            player typed username
	 * @param password
	 *            player typed password
	 * @return integer 0 if the login is successful; otherwise 1.
	 */
	public int login(String username, String password) {
		if (DB.login(username, password) == true) {
			return 0;
		}

		else {
			return 1;
		}
	}

}