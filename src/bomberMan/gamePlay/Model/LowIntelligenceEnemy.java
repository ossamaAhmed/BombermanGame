package bomberMan.gamePlay.Model;

public class LowIntelligenceEnemy extends Enemy implements java.io.Serializable
{
	public LowIntelligenceEnemy(int xPos,int yPos,int movmentDirection,String enemyName)
	{
		super(xPos,yPos,movmentDirection,enemyName);
		
		if(enemyName.equals("Balloom"))
		{	this.updateSpeed(CONSTANTS.SPEEDENEMY_SLOW);
			this.setWallPass(false);
			this.setScore(100);
		}
		else if (enemyName.equals("Doll"))
			{this.updateSpeed(CONSTANTS.SPEEDENEMY_NORMAL);
				this.setWallPass(false);
				this.setScore(400);
		}
		
	}

}
