package gameModelTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import bomberMan.gamePlay.Model.Wall;
import bomberMan.gamePlay.Model.WallType;

public class WallTest {

	@Test
	public void testIsBrick() {
		Wall myWall=new Wall(20,30,WallType.BRICK);
		boolean expected = true;
		boolean actual =myWall.isBrick();
		assertEquals(expected, actual);
		myWall=new Wall(20,30,WallType.CONCRETE);
		expected = false;
		actual =myWall.isBrick();
		assertEquals(expected, actual);
	}
	@Test
	public void testIsConcrete() {
		Wall myWall=new Wall(20,30,WallType.CONCRETE);
		boolean expected = true;
		boolean actual =myWall.isConcrete();
		assertEquals(expected, actual);
		myWall=new Wall(20,30,WallType.BRICK);
		expected = false;
		actual =myWall.isConcrete();
		assertEquals(expected, actual);
	}

}
