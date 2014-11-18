package bomberMan.gamePlay.Controller;

public class EnemyController extends Thread
{
	CharacterController myController;
	public EnemyController(CharacterController myController)
	{
		this.myController=myController;
	}
    public void run()
    {
    	while(true)
    	{
    		myController.moveEnemy();
    		try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }

}
