package gameModelTests;

import static org.junit.Assert.*;

import org.junit.Test;

import bomberMan.gamePlay.Model.CONSTANTS;
import bomberMan.gamePlay.Model.Character;;

public class CharacterTest {

	Character myCharacter=new Character(20,30,CONSTANTS.BALLOON_IMAGE,"Enemy");
	@Test
	public void testMoveUp() {
		myCharacter.moveUp(5);
		int expected = 25;
		int actual =myCharacter.getPositionY();
		assertEquals(expected, actual);
	}
	@Test
	public void testMoveDown() {
		myCharacter.moveDown(5);
		int expected = 35;
		int actual =myCharacter.getPositionY();
		assertEquals(expected, actual);
	}
	@Test
	public void testMoveRight() {
		myCharacter.moveRight(5);
		int expected = 25;
		int actual =myCharacter.getPositionX();
		assertEquals(expected, actual);
	}
	@Test
	public void testMoveLeft() {
		myCharacter.moveLeft(5);
		int expected = 15;
		int actual =myCharacter.getPositionX();
		assertEquals(expected, actual);
	}
	@Test
	public void testDie() {
		myCharacter.die();
		boolean expected = false;
		boolean actual =myCharacter.getIsAlive();
		assertEquals(expected, actual);
	}

}
