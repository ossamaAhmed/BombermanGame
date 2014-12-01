package gamePlayControllerTests;

import static org.junit.Assert.*;

import org.junit.Test;

import bomberMan.Login.Model.User;
import bomberMan.gamePlay.Controller.CharacterController;
import bomberMan.gamePlay.Controller.EnemyController;
import bomberMan.gamePlay.Model.CONSTANTS;
import bomberMan.gamePlay.Model.Enemy;
import bomberMan.gamePlay.Model.GameBoard;
import bomberMan.gamePlay.Model.HighIntellegenceEnemy;
import bomberMan.gamePlay.Model.LowIntelligenceEnemy;
import bomberMan.gamePlay.Model.MediumIntelligenceEnemy;
import bomberMan.gamePlay.Model.Stage;

public class EnemyControllerTest {
	int[] powerUps={0,CONSTANTS.BOMB_RANGE1,CONSTANTS.DEFAULT_SPEEDBOMBERMAN};
	User myUser=new User("sam ahmed", "sam", "Monday12", 30, 1);
	GameBoard myBoard=new GameBoard(1, Stage.getStage(1), powerUps, 1, myUser, 20000);
	EnemyController myController=new EnemyController(myBoard);
	@Test
	public void testMoveEnemy() {
		myBoard.getCell(1, 2).getObjects().clear();
		myBoard.getCell(1, 3).getObjects().clear();
		myBoard.getCell(1, 4).getObjects().clear();
		myBoard.getCell(1, 5).getObjects().clear();
		Enemy myEnemy;
		myEnemy=new LowIntelligenceEnemy(90,41,CONSTANTS.UP,"Balloom");
		int expected=CONSTANTS.DOWN;
		myController.moveEnemy(myEnemy, myEnemy.hasWallPass());
		int actual=myEnemy.getExpectedMovmentDirection();
		assertEquals(actual,expected); //testing collision and rebounding
		myEnemy.setExpectedMovmentDirection(CONSTANTS.RIGHT);
		expected=CONSTANTS.RIGHT;
		myController.moveEnemy(myEnemy, myEnemy.hasWallPass());
		actual=myEnemy.getExpectedMovmentDirection();
		assertEquals(actual,expected); //testing collision and rebounding
		myEnemy=new MediumIntelligenceEnemy(80,40,CONSTANTS.RIGHT,"Minvo");
		expected=CONSTANTS.LEFT;
		myController.moveEnemy(myEnemy, myEnemy.hasWallPass());
		actual=myEnemy.getExpectedMovmentDirection();
		assertEquals(actual,expected); //chasing bomberman testng
	}

}
