package bomberMan.Login.Controller;

import bomberMan.Login.Model.UserDatabase;

/**
 * Class which will create new user accounts once it reaches all the criteria
 * 
 * <p>
 * Integers are used as the returned item, to help differentiate different error
 * in all login controller classes.
 * 
 * @author Tong Yi
 *
 */
public class SignUpController {
	private UserDatabase DB;

	public SignUpController(UserDatabase database) {
		this.DB = database;
	}

	/**
	 * Signs ups a user with their specified realName, userName, password1, and
	 * password2. If the user meets the different requirements to create an
	 * account, the account will successfully be created. The different
	 * requirements are; existing user name, password strength, no empty text
	 * fields, and matching passwords.
	 * 
	 * @param realName
	 * @param username
	 * @param password1
	 * @param password2
	 * @return an integer corresponding to each scenario. 0 is a success
	 *         scenario; otherwise different failed scenarios. 1 corresponds to
	 *         user name already being taken. 2 corresponds to password does not
	 *         meet the minimum requirements. 3 corresponds to an empty text
	 *         field. 4 corresponds to mismatched passwords.
	 */
	public int signUp(String realName, String username, String password1,
			String password2) {
		if (realName.length() == 0 || username.length() == 0
				|| password1.length() == 0 || password2.length() == 0) {
			return 3;
		} else if (DB.userExists(username) == true) {
			return 1;
		} else if (checkPassWordMatches(password1, password2) == false) {
			return 4;
		} else if (checkPassWordStrength(password1) == false) {
			return 2;
		} else {
			String[] temp = { realName, password1, username, "0", "1" };
			DB.createUser(temp);
			return 0;
		}

	}

	/**
	 * Checks whether or not password matches.
	 * 
	 * @param password1
	 *            user first typed password
	 * @param password2
	 *            user second typed password
	 * @return boolean which depends on whether or not the password matches
	 */
	private boolean checkPassWordMatches(String password1, String password2) {
		if (password1.equals(password2)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks password strength, if it has upper and lower case, longer than 8
	 * characters, has a number, and has a symbol.
	 * 
	 * @param password
	 *            user typed password
	 * @return boolean depending on whether it reached the password strength
	 *         criteria
	 */
	private boolean checkPassWordStrength(String password) {
		boolean hasUppercase = false;
		boolean hasLowercase = false;
		boolean isAtLeast8 = password.length() >= 8;
		boolean hasNumber = false;
		boolean hasSymbol = false;

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
			if (((password.charAt(i) >= 33 && password.charAt(i) <= 47)
					|| (password.charAt(i) >= 58 && password.charAt(i) <= 64)
					|| (password.charAt(i) >= 91 && password.charAt(i) <= 96) || (password
					.charAt(i) >= 123 && password.charAt(i) <= 126))
					&& !hasSymbol)
				hasSymbol = true;
		}

		if ((hasUppercase && hasLowercase && isAtLeast8 && hasNumber && hasSymbol) == true) {
			return true;
		} else {
			return false;
		}

	}

}