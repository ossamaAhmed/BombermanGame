package bomberMan.Login.Controller;

public class LoginController extends Controller {

	private String username;
	private String password;

	public void checkNextView(int buttonPressed) {
		if (buttonPressed == 1) {
			// checks if it logs in properly
			if (checkLogin(username, password) == true
					&& (username != null && password != null)) {

				// UserDatabase.setCurrentView("MainMenu");
				System.out.println("Set Current View: MainMenu");
				System.out.println("Set Current User: " + username);
				// UserDatabase.setCurrentUser(LoginView.getUsername());
			}

			else {

				System.out.println("Set Current View: Login");
				loginError();

				// UserDatabase.setNextView("Login");
			}

		}

		else if (buttonPressed == 2) {

			System.out.println("Set Current View: SignUp");
			// UserDatabase.setCurrentView("SignUp");
		}

		else if (buttonPressed == 3) {
			Controller.exitGame();
			// ExitController.exitGame();
		}
	}

	public boolean checkLogin(String username, String password) {
		if (username == "bob" && password == "abc123") {
			return true;
		}

		else {
			return false;
		}
	}

	public void loginError() {
		System.out.println("Login Error");
		// call some method in the view to display error message.
	}

	public void setUserName(String userName) {
		username = userName;
	}

	public void setPassword(String passWord) {
		password = passWord;
	}
}
