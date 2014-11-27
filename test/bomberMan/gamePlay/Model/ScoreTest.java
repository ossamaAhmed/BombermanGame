package bomberMan.gamePlay.Model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ScoreTest {
	Score score = new Score();
	
	@Test
	public void testCalculateScoreForOneEnemy() {
		ArrayList<Enemy> killed = new ArrayList<Enemy>();
		killed.add(new LowIntelligenceEnemy(0, 0, 0, "Balloom"));
		int expected = 100;
		score.calculateMyScore(killed);
		int actual = score.getMyScore();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testCalculateScoreForTwoDifferentEnemies() {
		
	}
}
