package bomberMan.Login.Controller;

import bomberMan.Login.Model.UserDatabase;

public class SignUpController 
{
	private UserDatabase DB;

	public SignUpController(UserDatabase database)
	{
		this.DB=database;
	}

	//signup button was pressed, these strings are passed from the text fields
	public int signUp(String firstName, String lastName,String username, String password1, String password2) 
	{
		if (firstName.length()==0 || lastName.length()==0 || username.length()==0 || password1.length()==0 || password2.length()==0)
		{
			return 3;
		}
		else if (checkUsernameNotTaken(username) == false) {
			return 1;
		} 
		//checks if password matches
		else if (checkPassWordMatches(password1, password2) == false) 
		{
			return 4;
		}

		//success scenario, creates user
		else if (checkPassWordStrength(password1) == false) 
		{

			//DB.createUser(userData);

			return 2;
		} 

		//if any text fields are missing

		else
		{
			String [] temp={firstName+" "+lastName,password1,username};
			DB.createUser(temp);
			return 0;
		}

	}

	//check if username is not taken by calling method in userdatabase
	public boolean checkUsernameNotTaken(String username) {
		if (DB.userExists(username) != true) {
			return true;
		} else {
			return false;
		}
	}

	//checks if passwords match
	public boolean checkPassWordMatches(String password1, String password2) {
		if (password1.equals(password2)) {
			return true;
		} else {
			return false;
		}
	}

	//checks password strength
	public boolean checkPassWordStrength(String password) 
	{
		boolean hasUppercase = false;
		boolean hasLowercase =false;
		boolean isAtLeast6 = password.length() >= 6;
		boolean noSymbols = password.matches("[A-Za-z0-9 ]*");
		boolean hasNumber =false;

		for(int i=0;i<password.length();i++)
		{
			if(password.charAt(i)>='A'&&password.charAt(i)<='Z'&& !hasUppercase)
				hasUppercase=true;
			if(password.charAt(i)>='a'&&password.charAt(i)<='z'&& !hasLowercase)
				hasLowercase=true;
			if(password.charAt(i)>='0'&&password.charAt(i)<='9'&& !hasNumber)
				hasNumber=true;	
		}

		if ((hasUppercase && hasLowercase && isAtLeast6 && noSymbols && hasNumber) == true) {
			return true;
		} else {
			return false;
		}

	}

}