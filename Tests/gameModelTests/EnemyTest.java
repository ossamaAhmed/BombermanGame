package gameModelTests;

import static org.junit.Assert.*;

import org.junit.Test;

import bomberMan.gamePlay.Model.CONSTANTS;
import bomberMan.gamePlay.Model.Enemy;
import bomberMan.gamePlay.Model.Wall;
import bomberMan.gamePlay.Model.WallType;

public class EnemyTest {

	@Test
	public void testChangeDirection() {
		Enemy myEnemy=new Enemy(20,30,CONSTANTS.RIGHT,"Balloom");
		int expected = CONSTANTS.LEFT;
		myEnemy.changeDirection();
		int actual =myEnemy.getMovmentDirection();
		assertEquals(expected, actual);
		myEnemy.setExpectedMovmentDirection(CONSTANTS.LEFT);
		expected = CONSTANTS.RIGHT;
		myEnemy.changeDirection();
		actual =myEnemy.getMovmentDirection();
		assertEquals(expected, actual);
		myEnemy.setExpectedMovmentDirection(CONSTANTS.UP);
		expected = CONSTANTS.DOWN;
		myEnemy.changeDirection();
		actual =myEnemy.getMovmentDirection();
		assertEquals(expected, actual);
		myEnemy.setExpectedMovmentDirection(CONSTANTS.DOWN);
		expected = CONSTANTS.UP;
		myEnemy.changeDirection();
		actual =myEnemy.getMovmentDirection();
		assertEquals(expected, actual);

	}

}
