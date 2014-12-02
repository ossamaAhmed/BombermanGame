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
import bomberMan.gamePlay.Model.Wall;

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
		
		//bomberman located at 1,1
		//enemy uses pixel base, divide by 40 to get actual cell
		myEnemy=new MediumIntelligenceEnemy(80,40,CONSTANTS.RIGHT,"Ovapi");
		expected=CONSTANTS.LEFT;
		myController.moveEnemy(myEnemy, true);
		actual = myEnemy.getExpectedMovmentDirection();
		assertEquals(expected, actual);//chase bomberman med AI
		
		myEnemy=new MediumIntelligenceEnemy(40,120,CONSTANTS.RIGHT,"Kondoria");
		expected = CONSTANTS.RIGHT;
		myController.moveEnemy(myEnemy, true);
		actual = myEnemy.getExpectedMovmentDirection();
		assertEquals(expected, actual);//normal movement med AI
		
		myEnemy=new HighIntellegenceEnemy(120,160,CONSTANTS.DOWN,"Kondoria");
		expected = CONSTANTS.DOWN;
		myController.moveEnemy(myEnemy, true);
		actual = myEnemy.getExpectedMovmentDirection();
		assertEquals(expected, actual);//normal movement high AI
		
		
		myEnemy=new HighIntellegenceEnemy(120,39,CONSTANTS.RIGHT,"Kondoria");
		expected = CONSTANTS.LEFT;
		myController.moveEnemy(myEnemy, true);
		actual = myEnemy.getExpectedMovmentDirection();
		assertEquals(expected, actual);//chase bomberman high AI
			
	}


}
