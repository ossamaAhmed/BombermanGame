package bomberMan.gamePlay.Model;

import java.util.*;

public class Score {
	
	public static Hashtable<String,Integer> affectedEnemies;
	
	static {
	affectedEnemies = new Hashtable<String,Integer>();
	affectedEnemies.put("Balloon", 100);
	affectedEnemies.put("Oneal", 200);
	affectedEnemies.put("Doll", 400);
	affectedEnemies.put("Minvo", 800);
	affectedEnemies.put("Kondoria", 1000);
	affectedEnemies.put("Ovapi", 2000);
	affectedEnemies.put("Pass", 4000);
	affectedEnemies.put("Pontan", 8000);
		
	}
	public static int calculateMyScore(String[] enemies, int currentScore){

		int newScore = currentScore;
		
		for(int i=0;i<enemies.length;i++){

			newScore += (affectedEnemies.get(enemies[i])*(i+1));
//			System.out.println("Getting: " + (affectedEnemies.get(enemies[i])*(i+1)));
		}
		
		return newScore;
	}
	
	public static void main (String[] args){

	String[] enemies = {"Balloon","Oneal", "Pontan"};
	System.out.println("New score: " + calculateMyScore(enemies,200));
		
	}

}
