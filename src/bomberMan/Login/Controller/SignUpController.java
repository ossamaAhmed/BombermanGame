package bomberMan.Login.Controller;

import bomberMan.Login.Model.UserDatabase;

public class SignUpController {
	
	//signup button was pressed, these strings are passed from the text fields
	public int signUp(String username, String password1, String password2,
			String firstName, String lastName) {
		//checks if password matches
		if (checkPassWordMatches(password1, password2) == true) {
			
			//success scenario, creates user
			if (checkUsernameNotTaken(username)
					&& checkPassWordStrength(password1) == true) {
				
				String userData[] = {firstName + " "+ lastName, password1, username};
				UserDatabase.createUser(userData);
				
				System.out.println("Go to login view");
				// also need to call methods from model to create the new
				// account
				return 0;
			} 
			
			//returns 1 to the view	if only username is taken
			else if (checkUsernameNotTaken(username) == false
					&& checkPassWordStrength(password1) == true) {
				return 1;
			} 
			
			//returns 2 if only password strenght is weak
			else if (checkUsernameNotTaken(username) == true
					&& checkPassWordStrength(password1) == false) {
				return 2;
			} 
			
			//if both are false return 3
			else {
				return 3;
			}
		} 
		
		//returns 4 if passwords do not match
		else {
			return 4;
		}
	}
	
	//check if username is not taken by calling method in userdatabase
	public boolean checkUsernameNotTaken(String username) {
		if (UserDatabase.userExists(username) != true) {
			return true;
		} else {
			return false;
		}
	}
	
	//checks if passwords match
	public boolean checkPassWordMatches(String password1, String password2) {
		if (password1 == password2) {
			return true;
		} else {
			return false;
		}
	}

	//checks password strength
	public boolean checkPassWordStrength(String password) {
		boolean hasUppercase = !password.equals(password.toLowerCase());
		boolean hasLowercase = !password.equals(password.toUpperCase());
		boolean isAtLeast6 = password.length() >= 6;
		boolean noSymbols = password.matches("[A-Za-z0-9 ]*");
		boolean hasNumber = password.matches("[0-9]*");

		if ((hasUppercase && hasLowercase && isAtLeast6 && noSymbols && hasNumber) == true) {
			return true;
		} else {
			return false;
		}

	}

}