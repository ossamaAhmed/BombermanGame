package bomberMan.gamePlay.Model;

public class MediumIntelligenceEnemy extends Enemy
{
	public MediumIntelligenceEnemy(int xPos,int yPos,int movmentDirection,String enemyName)
	{
		super(xPos,yPos,movmentDirection,enemyName);
		
		if(enemyName.equals("Minvo"))
		{	this.updateSpeed(CONSTANTS.SPEEDENEMY_FAST);
			this.setWallPass(false);
			this.setScore(800);
		}
		else if (enemyName.equals("Oneal"))
			{this.updateSpeed(CONSTANTS.SPEEDENEMY_NORMAL);
				this.setWallPass(false);
				this.setScore(200);
		}
		else if (enemyName.equals("Ovapi"))
		{this.updateSpeed(CONSTANTS.SPEEDENEMY_SLOW);
			this.setWallPass(true);
			this.setScore(2000);
	}
		
	}

}
