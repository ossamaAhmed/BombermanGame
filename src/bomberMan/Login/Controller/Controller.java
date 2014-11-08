package bomberMan.Login.Controller;

abstract class Controller {
	
	static void exitGame(){
		System.out.println("Exit Game");
	}
	
	abstract void checkNextView(int buttonPressed);

}
