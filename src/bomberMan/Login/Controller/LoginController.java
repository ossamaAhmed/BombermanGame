package bomberMan.Login.Controller;

import bomberMan.Login.Model.UserDatabase;

public class LoginController{
	//login button being pressed, returns 0 or 1 depending if it is successful
	public int login(String username, String password){
		if(UserDatabase.login(username, password) == true){
			System.out.println("Goes into MainMenu");
			System.out.println("set current user: " + username);
			return 0;
		}
			
		else{
			return 1;
		}
	}

}