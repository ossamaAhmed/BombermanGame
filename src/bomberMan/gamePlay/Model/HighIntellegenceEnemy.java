package bomberMan.gamePlay.Model;

public class HighIntellegenceEnemy extends Enemy
{
	
	public HighIntellegenceEnemy(int xPos,int yPos,int movmentDirection,String enemyName)
	{
		super(xPos,yPos,movmentDirection,enemyName);
		
		if(enemyName.equals("Kondoria"))
		{	this.updateSpeed(CONSTANTS.SPEEDENEMY_SLOWEST);
			this.setWallPass(true);
			this.setScore(1000);
		}
		else if (enemyName.equals("Pontan"))
			{this.updateSpeed(CONSTANTS.SPEEDENEMY_FAST);
				this.setWallPass(true);
				this.setScore(8000);
		}
		else if(enemyName.equals("Pass"))
		{
			this.updateSpeed(CONSTANTS.SPEEDENEMY_FAST);
			this.setWallPass(false);
			this.setScore(4000);
		}
		
	}

}
