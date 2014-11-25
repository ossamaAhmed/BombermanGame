package bomberMan.Login.Controller;

import bomberMan.Login.Model.UserDatabase;

public class SignUpController {
	private UserDatabase DB;

	public SignUpController(UserDatabase database) {
		this.DB = database;
	}

	// signup button was pressed, these strings are passed from the text fields
	public int signUp(String realName, String username, String password1,
			String password2) {
		if (realName.length() == 0 || username.length() == 0
				|| password1.length() == 0 || password2.length() == 0) {
			return 3;
		}
		// username taken or wrong format
		else if (DB.userExists(username) == true) {
			return 1;
		}
		// checks if password matches
		else if (checkPassWordMatches(password1, password2) == false) {
			return 4;
		}

		// success scenario, creates user
		else if (checkPassWordStrength(password1) == false) {
			return 2;
		}

		// if any text fields are missing

		else {
			String[] temp = { realName, password1, username, "0", "1"};
			DB.createUser(temp);
			return 0;
		}

	}

	// check if username is not taken by calling method in userdatabase
	public boolean checkUsernameNotTaken(String username) {

		boolean userNameGreater6 = false;
		boolean userExists = DB.userExists(username);

		if (username.length() >= 6) {
			userNameGreater6 = true;
		}

		
		
		if ((userNameGreater6 && userExists) == true) {
			return true;
		} else {
			return false;
		}
	}

	// checks if passwords match
	public boolean checkPassWordMatches(String password1, String password2) {
		if (password1.equals(password2)) {
			return true;
		} else {
			return false;
		}
	}

	// checks password strength
	public boolean checkPassWordStrength(String password) {
		boolean hasUppercase = false;
		boolean hasLowercase = false;
		boolean isAtLeast8 = password.length() >= 8;
		boolean hasNumber = false;

		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z'
					&& !hasUppercase)
				hasUppercase = true;
			if (password.charAt(i) >= 'a' && password.charAt(i) <= 'z'
					&& !hasLowercase)
				hasLowercase = true;
			if (password.charAt(i) >= '0' && password.charAt(i) <= '9'
					&& !hasNumber)
				hasNumber = true;
		}

		if ((hasUppercase && hasLowercase && isAtLeast8 && hasNumber) == true) {
			return true;
		} else {
			return false;
		}

	}

}