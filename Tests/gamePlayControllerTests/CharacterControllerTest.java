package gamePlayControllerTests;

import static org.junit.Assert.*;

import java.awt.Button;
import java.awt.event.KeyEvent;

import org.junit.Test;

import bomberMan.Login.Model.User;
import bomberMan.gamePlay.Controller.CharacterController;
import bomberMan.gamePlay.Model.CONSTANTS;
import bomberMan.gamePlay.Model.Character;
import bomberMan.gamePlay.Model.GameBoard;
import bomberMan.gamePlay.Model.Stage;

public class CharacterControllerTest {
	int[] powerUps={0,CONSTANTS.BOMB_RANGE1,CONSTANTS.DEFAULT_SPEEDBOMBERMAN};
	User myUser=new User("sam ahmed", "sam", "Monday12", 30, 1);
	GameBoard myBoard=new GameBoard(1, Stage.getStage(1), powerUps, 1, myUser, 20000);
	CharacterController myController=new CharacterController(myBoard);
	@Test
	public void testCheckCollision() {
		boolean expected=true;
		this.myBoard.getBomberMan().updateSpeed(11);
		boolean actual=myController.checkCollision(this.myBoard.getBomberMan(), CONSTANTS.UP,"CONCRETE");
		assertEquals(actual,expected);
		actual=myController.checkCollision(this.myBoard.getBomberMan(), CONSTANTS.LEFT,"CONCRETE");
		assertEquals(actual,expected);
		expected=false;
		actual=myController.checkCollision(this.myBoard.getBomberMan(), CONSTANTS.RIGHT,"CONCRETE");
		assertEquals(actual,expected);
		actual=myController.checkCollision(this.myBoard.getBomberMan(), CONSTANTS.DOWN,"CONCRETE");
		assertEquals(actual,expected);
	}
	@Test
	public void testMove() {
		this.myBoard.getBomberMan().updateSpeed(11);
		int expected=this.myBoard.getBomberMan().getPositionX()+11;
		Button a = new Button("click");
	    KeyEvent e;
	    e = new KeyEvent(a, 1, 20, 10, KeyEvent.VK_RIGHT);
		this.myController.move(e);
		int actual=this.myBoard.getBomberMan().getPositionX();
		assertEquals(actual,expected);
		e = new KeyEvent(a, 1, 20, 10, KeyEvent.VK_LEFT);
		expected=this.myBoard.getBomberMan().getPositionX()-11;
		this.myController.move(e);
		actual=this.myBoard.getBomberMan().getPositionX();
		assertEquals(actual,expected);
		e = new KeyEvent(a, 1, 20, 10, KeyEvent.VK_DOWN);
		expected=this.myBoard.getBomberMan().getPositionY()+11;
		this.myController.move(e);
		actual=this.myBoard.getBomberMan().getPositionY();
		assertEquals(actual,expected);
		e = new KeyEvent(a, 1, 20, 10, KeyEvent.VK_UP);
		expected=this.myBoard.getBomberMan().getPositionY()-11;
		this.myController.move(e);
		actual=this.myBoard.getBomberMan().getPositionY();
		assertEquals(actual,expected);
	}

}
