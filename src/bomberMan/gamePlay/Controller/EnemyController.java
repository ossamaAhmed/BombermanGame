package bomberMan.gamePlay.Controller;

public class EnemyController 
{
	CharacterController myController;
	public EnemyController(CharacterController myController)
	{
		this.myController=myController;
	}
    public void run()
    {
    	for(int i=0;i<myController.getGameBoard().getEnemies().size();i++)
    		{
    			 myController.moveEnemy(myController.getGameBoard().getEnemies().get(i),myController.getGameBoard().getEnemies().get(i).hasWallPass());
    		}
    }

}
