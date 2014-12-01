package gameModelTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import bomberMan.Login.Model.User;
import bomberMan.gamePlay.Model.Bomb;
import bomberMan.gamePlay.Model.CONSTANTS;
import bomberMan.gamePlay.Model.Enemy;
import bomberMan.gamePlay.Model.GameBoard;
import bomberMan.gamePlay.Model.LowIntelligenceEnemy;
import bomberMan.gamePlay.Model.PowerUp;
import bomberMan.gamePlay.Model.PowerUpType;
import bomberMan.gamePlay.Model.Stage;

public class GameBoardTest {
	int[] powerUps={0,CONSTANTS.BOMB_RANGE1,CONSTANTS.DEFAULT_SPEEDBOMBERMAN};
	User myUser=new User("sam ahmed", "sam", "Monday12", 30, 1);
	GameBoard myBoard=new GameBoard(1, Stage.getStage(1), powerUps, 1, myUser, 20000);
	@Test
	public void testWorstPenality() {
		int expected = myBoard.getEnemies().size()+8;
		myBoard.worstPenality(Stage.getStage(1));
		int actual =myBoard.getEnemies().size();
		assertEquals(expected, actual);
	}
	@Test
	public void testAddBomb() {
		int expected = myBoard.getBombs().size()+1;
		Bomb myBomb=new Bomb();
		myBoard.addBomb(5, 5, myBomb);
		int actual =myBoard.getBombs().size();
		assertEquals(expected, actual);
	}
	@Test
	public void testRemoveBomb() {

		Bomb myBomb=new Bomb();
		myBoard.addBomb(5, 5, myBomb);
		int expected = myBoard.getBombs().size()-1;
		System.out.println(expected);
		myBoard.removeBomb(5*CONSTANTS.TILE_SIDE_SIZE, 5*CONSTANTS.TILE_SIDE_SIZE);
		int actual =myBoard.getBombs().size();
		System.out.println(actual);
		assertEquals(expected, actual);
	}
	@Test
	public void testDeletePowerUP() {

		PowerUp myPowerup=new PowerUp(40, 40, null, PowerUpType.BOMBPASS, "PowerUp");
		myBoard.getCell(1, 1).insert(myPowerup);
		boolean expected =!myBoard.getCell(1, 1).searcHasAPowerUp();
		System.out.println(expected);
		myBoard.deletePowerUp(1, 1);
		boolean actual =myBoard.getCell(1, 1).searcHasAPowerUp();
		assertEquals(expected, actual);
	}
	@Test
	public void testUpdateMyScore() {

		ArrayList<Enemy> killedEnemies=new ArrayList<Enemy>();
		killedEnemies.add(new LowIntelligenceEnemy(40,40,CONSTANTS.UP,"Balloom"));
		int expected =myBoard.getScore()+100;
		this.myBoard.updateMyScore(killedEnemies);
		int actual =myBoard.getScore();
		assertEquals(expected, actual);
		killedEnemies.add(new LowIntelligenceEnemy(40,40,CONSTANTS.UP,"Balloom"));
		expected=myBoard.getScore()+300;
		this.myBoard.updateMyScore(killedEnemies);
		actual=myBoard.getScore();
		assertEquals(expected, actual);
	}
}
