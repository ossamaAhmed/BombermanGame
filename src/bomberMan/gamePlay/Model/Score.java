package bomberMan.gamePlay.Model;

import java.util.*;

public class Score implements java.io.Serializable
{
	
	private int myScore;
	
	public void calculateMyScore(ArrayList<Enemy> killedEnemies)
	{

		
		for(int i=0;i<killedEnemies.size();i++){

			this.myScore += (killedEnemies.get(i).getScore())*(i+1);
		}
		
	}
	public int getMyScore()
	{
		return this.myScore;
	}
	
	
}
