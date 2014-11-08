package bomberMan.Login.Controller;

public class SignUpController extends Controller {

	private String username;
	private String password;
	private String fullName;
	
	public void checkNextView(int buttonPressed) {
		// Sign up button
		if (buttonPressed == 1) {
			if (checkUsernameNotTaken(username) == true) {
				// need to call method to set new user name and password in
				// model
				System.out.println("You have successfully signed up!");
				System.out.println("Set current view: Login");
			} 
			
			else {
				System.out.println("Set current view: SignUp");
				usernameErrorDisplay();
			}

		}
		// login button
		else if (buttonPressed == 2) {
			System.out.println("Set current view: Login");
		}
		// Exit button
		else if (buttonPressed == 3) {
			Controller.exitGame();
		}
	}

	public boolean checkUsernameNotTaken(String username) {
		if (username == "Bob") {
			return false;
		} else {
			return true;
		}
	}

	public void usernameErrorDisplay() {
		// call some method in view to display message.
		System.out.println("Username already taken");

	}
	
	public void setUserName(String userName){
		username = userName;
	}
	
	public void setPassWord(String passWord){
		password = passWord;
	}
	
	public void setFullName(String fullname){
		fullName = fullname;
	}
}