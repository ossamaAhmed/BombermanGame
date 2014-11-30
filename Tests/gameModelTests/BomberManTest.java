package gameModelTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.*;

import bomberMan.gamePlay.Model.*;
import bomberMan.gamePlay.Model.*;
public class BomberManTest {
     private  BomberMan bmb1;
    @Before
    public void setUp(){
    	bmb1 = new BomberMan(80,80);
	}
	 @Test
	 public void testBomberManConstructor()
		{
			assertEquals(80, bmb1.getPositionX());
			assertEquals(80, bmb1.getPositionY());
			assertEquals(CONSTANTS.BomberMan_IMAGE, bmb1.getImageLocation());
			assertTrue(bmb1.getBombPass()== false);
			assertTrue(bmb1.getFlamePass()== false);
			assertTrue(bmb1.getBrickPass()== false);
			assertTrue(bmb1.getInvisibilibityPowerUp()== false);
			assertTrue(bmb1.getHasDetonator()== false);
			assertTrue(bmb1.getBombRange()== CONSTANTS.BOMB_RANGE1);
			assertTrue(bmb1.getSpeed()== CONSTANTS.DEFAULT_SPEED);
			assertTrue(bmb1.getNumBombsToDrop()== 0);
			assertTrue(bmb1.getQteOfBombsDropped()== 0);
			assertTrue(CONSTANTS.LIVESBOMBERMAN == bmb1.getNumOfLives());
		
			
		}
	
	@Test
	public void testAddBomb(){
		Bomb bmb = new Bomb();
		bmb1.addBomb(bmb);
		assertSame(bmb1.getOldestBomb(), bmb);
		bmb1.removeOldestBomb();
	}
	@Test
	public void testGetICell(){
			assertEquals(bmb1.getPositionY()/CONSTANTS.TILE_SIDE_SIZE, 80/CONSTANTS.TILE_SIDE_SIZE);
			
		}
	@Test
	public void testGetJCell(){
		assertEquals(bmb1.getJCell(), 80/CONSTANTS.TILE_SIDE_SIZE);
	}
	@Test
    public void testGetICellBottomBomberman(){
		assertEquals(bmb1.getICellBottomBomberman(),( (80+CONSTANTS.BOMBERMAN_HEIGHT)/CONSTANTS.TILE_SIDE_SIZE));
	}
	@Test
	public void  testGetJCellRightMostBomberman(){
		assertEquals(bmb1.getJCellRightMostBomberman(), (80+CONSTANTS.BOMBERMAN_WIDTH)/CONSTANTS.TILE_SIDE_SIZE);
	}
	@Test
	public void testRemoveOldestBomb(){
		Bomb bm = new Bomb();
		Bomb bm1 = new Bomb();
		bmb1.addBomb(bm);
		bmb1.addBomb(bm1);
		bmb1.removeOldestBomb();
		assertSame(bmb1.getOldestBomb(), bm1);
		bmb1.removeOldestBomb();
		assertEquals(bmb1.getOldestBomb().getPositionX(),-1);
		assertEquals(bmb1.getOldestBomb().getPositionY(),-1);
		
		}
	@Test
	public void testRemoveBomb(){
		Bomb bm = new Bomb(10,10, 1000, 0, false, "Bomb");
		bmb1.addBomb(bm);
		assertEquals(bmb1.getOldestBomb().getPositionX(),10);
		assertEquals(bmb1.getOldestBomb().getPositionY(),10);
		assertSame(bmb1.getOldestBomb(), bm);
		bmb1.removeBomb(10,10);
		assertEquals(bmb1.getOldestBomb().getPositionX(),-1);
		assertEquals(bmb1.getOldestBomb().getPositionY(),-1);
		
		}
	@Test	
	public void testGetOldestBomb(){
		Bomb bm = new Bomb(10,10, 1000, 0, false, "Bomb");
		bmb1.addBomb(bm);
		assertSame(bmb1.getOldestBomb(), bm);
		bmb1.removeOldestBomb();
		
	}
			
	@Test
	public void testDie()
		{
		BomberMan bmb = new BomberMan(10,10);
		bmb.die();
		assertEquals(bmb.getImageLocation(), CONSTANTS.BMB_dead);
		assertEquals(bmb.getNumOfLives(), bmb1.getNumOfLives()-1);
			
		}	
		
	@Test	
	public void testGetHasDetonator(){
		assertTrue(bmb1.getHasDetonator()==false);
		bmb1.setHasDetonator(true);
		assertTrue(bmb1.getHasDetonator());
		bmb1.setHasDetonator(false);
	}
	
	@Test
	public void testSetHasDetonator(){
		assertTrue(bmb1.getHasDetonator()==false);
		bmb1.setHasDetonator(true);
		assertTrue(bmb1.getHasDetonator());
		bmb1.setHasDetonator(false);
	}
	@Test
	public void testGetBombRange(){
		assertEquals(bmb1.getBombRange(), CONSTANTS.BOMB_RANGE1);
	}
	@Test
	public void testSetBombRange(){
		bmb1.setBombRange(10);
		assertEquals(bmb1.getBombRange(), 10+CONSTANTS.BOMB_RANGE1);
		bmb1.setBombRange(-10);
		assertEquals(bmb1.getBombRange(), CONSTANTS.BOMB_RANGE1);
	}
	@Test
	public void testSetBombRange1(){
		bmb1.setBombRange1(10);
		assertEquals(bmb1.getBombRange(), 10);
		bmb1.setBombRange1(CONSTANTS.BOMB_RANGE1);
		assertEquals(bmb1.getBombRange(), CONSTANTS.BOMB_RANGE1);
	}
	@Test
	public void testGetNumBombsToDrop(){
		assertEquals(bmb1.getNumBombsToDrop(), 0);
	}
	@Test
	public void testSetNumBombsToDrop(){
		bmb1.setNumBombsToDrop(1);
		assertEquals(bmb1.getNumBombsToDrop(), 1);
		bmb1.setNumBombsToDrop(-1);
		assertEquals(bmb1.getNumBombsToDrop(), 0);
	}
	@Test
	public void testSetNumBombsToDrop1(){
		bmb1.setNumBombsToDrop1(1);
		assertEquals(bmb1.getNumBombsToDrop(), 1);
		bmb1.setNumBombsToDrop1(0);
		assertEquals(bmb1.getNumBombsToDrop(), 0);
	}
	@Test
	public void testSetBrickPass(){
	assertTrue(bmb1.getBrickPass()== false);
	bmb1.setBrickPass(true);
	assertTrue(bmb1.getBrickPass());
	bmb1.setBrickPass(false);
	assertTrue(bmb1.getBrickPass()== false);
	}
	@Test
	public void testGetBrickPass(){
		assertTrue(bmb1.getBrickPass()== false);
	}
	@Test
	public void testSetBombPass(){
		assertTrue(bmb1.getBombPass()== false);
		bmb1.setBombPass(true);
		assertTrue(bmb1.getBombPass());
		bmb1.setBombPass(false);
		assertTrue(bmb1.getBombPass()== false);
	}
	@Test
	public void testgetBombPass(){
		assertTrue(bmb1.getBombPass()== false);
	}
	@Test
	public void testSetFlamePass(){
		assertTrue(bmb1.getFlamePass()== false);
		bmb1.setFlamePass(true);
		assertTrue(bmb1.getFlamePass());
		bmb1.setFlamePass(false);
		assertTrue(bmb1.getFlamePass()== false);
	}
	@Test
	public void testGetFlamePass(){
		assertTrue(bmb1.getFlamePass()== false);
	}
	@Test
	public void testGetQteOfBombsDropped(){
		assertTrue(bmb1.getQteOfBombsDropped()== 0);
	}
	@Test
	public void testSetQteOfBombsDropped(){
		
		bmb1.setQteOfBombsDropped(2);
		assertTrue(bmb1.getQteOfBombsDropped()==2);
		bmb1.setQteOfBombsDropped(-2);
		assertTrue(bmb1.getQteOfBombsDropped()== 0);
	}
	@Test
	public void testGetInvisibilibityPowerUp(){}
	@Test
	public void testSetInvisibilityPowerUp(){
		assertTrue(bmb1.getInvisibilibityPowerUp()== false);
		bmb1.setInvisibilityPowerUp(true);
		assertTrue(bmb1.getInvisibilibityPowerUp());
		bmb1.setInvisibilityPowerUp(false);
		assertTrue(bmb1.getInvisibilibityPowerUp()==false);
			}
	@Test
	public void testGetCreationInvisibilityPowerUp(){
		assertEquals(0, bmb1.getCreationInvisibilityPowerUp());
	}
	@Test
	public void tesSetCreationInvisibilityPowerUp(){
		bmb1.setCreationInvisibilityPowerUp();
		assertTrue( bmb1.getCreationInvisibilityPowerUp() >0);
		
		}
	@Test
	public void testAddTimeCreationInvisibilitPowerUp(){
		long time = bmb1.getCreationInvisibilityPowerUp();
		bmb1.addTimeCreationInvisibilitPowerUp();
		assertEquals(bmb1.getCreationInvisibilityPowerUp(), time + Calendar.getInstance().getTimeInMillis());
	}
	@Test
	public void testGetEliminationInvisibilityPowerUp(){
		bmb1.setCreationInvisibilityPowerUp();
		assertEquals(bmb1.getEliminationInvisibilityPowerUp(),CONSTANTS.delayINVISIBILITY+bmb1.getCreationInvisibilityPowerUp());
	}
	@Test
	public void testGetPowerUpsKeptAfterDeath(){
			
			assertEquals(bmb1.getPowerUpsKeptAfterDeath()[0], 0);
			assertEquals(bmb1.getPowerUpsKeptAfterDeath()[1], CONSTANTS.BOMB_RANGE1);
			assertEquals(bmb1.getPowerUpsKeptAfterDeath()[2], CONSTANTS.DEFAULT_SPEED);
			bmb1.setNumBombsToDrop(3);
			bmb1.setBombRange1(4);
			bmb1.updateSpeed(100);
			assertEquals(bmb1.getPowerUpsKeptAfterDeath()[0], 3);
			assertEquals(bmb1.getPowerUpsKeptAfterDeath()[1], 4);
			assertEquals(bmb1.getPowerUpsKeptAfterDeath()[2], 100);
		}
		
	
	

}
