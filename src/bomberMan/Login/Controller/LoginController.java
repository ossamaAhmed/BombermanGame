package bomberMan.Login.Controller;

public class LoginController{
	//login button being pressed, returns 0 or 1 depending if it is successful
	public int login(String username, String password){
		if(checkLogin(username, password) == true){
			System.out.println("Goes into MainMenu");
			System.out.println("set current user: " + username);
			return 0;
		}
			
		else{
			return 1;
		}
	}
	//sign up button was pressed, goes into signup
	public void SignUp(){
		System.out.println("Goes into SignUp");
	}
	
	// login check not complete need to know how the information is being stored.
	public boolean checkLogin(String username, String password) {
		if (username == "bob" && password == "abc123") {
			return true;
		}

		else {
			return false;
		}
	}

}
