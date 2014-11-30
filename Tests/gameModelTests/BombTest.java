package gameModelTests;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.*;

import bomberMan.gamePlay.Model.*;
public class BombTest {
private Bomb bomb1;
private Bomb bomb2;
private Bomb bomb3;

    @Before
    public void setUp(){
    	
    	bomb1 = new Bomb();
    	bomb2 = new Bomb(10,10, 3000, 2, true,  "Bomb");
    	bomb3 = new Bomb(20, 20, 3000, 2, false, "Bomb");
    }
	
	@Test
	public void testBombContructorWithoutArguments(){
		
		assertEquals(bomb1.getPositionX(), -1);
		assertEquals(bomb1.getPositionY(), -1);
		assertEquals(bomb1.getType(), "Bomb");
		assertNull(bomb1.getImageLocation());
		assertEquals(bomb1.getTimeToExplode(), CONSTANTS.BOMB_TIMER);
		assertEquals(bomb1.getBombRange(), CONSTANTS.DEFAULT_BOMB_RANGE);
		assertEquals(bomb1.isDetonatorActivated(), false);
		
	
		
		
	}
    @Test
    public void testBombConstructorWithArguments(){
    	assertEquals(bomb2.getPositionX(), 10);
		assertEquals(bomb2.getPositionY(), 10);
		assertEquals(bomb2.getTimeToExplode(), 3000);
		assertEquals(bomb2.getBombRange(), 2);
		assertEquals(bomb2.isDetonatorActivated(), true);
		assertEquals(bomb2.getType(), "Bomb");
		assertEquals(bomb3.getPositionX(), 20);
		assertEquals(bomb3.getPositionY(), 20);
		assertEquals(bomb3.getTimeToExplode(), 3000);
		assertEquals(bomb3.getBombRange(), 2);
		assertEquals(bomb3.isDetonatorActivated(), false);
		assertEquals(bomb3.getType(), "Bomb");
    }
    @Test
    public void testIsDetonatorActivated() {
    	assertEquals(bomb2.isDetonatorActivated(), true);
    	assertEquals(bomb3.isDetonatorActivated(), false);
    	assertEquals(bomb1.isDetonatorActivated(), false);
	}
    @Test
	public void testActivateDetonator() {
    	bomb3.activateDetonator();
    	bomb1.activateDetonator();
    	assertEquals(bomb3.isDetonatorActivated(), true);
    	assertEquals(bomb1.isDetonatorActivated(), true);
	}
    @Test
	public void testDesactivateDetonator() {
    	bomb3.desactivateDetonator();
    	bomb1.desactivateDetonator();
    	assertEquals(bomb3.isDetonatorActivated(), false);
    	assertEquals(bomb1.isDetonatorActivated(), false);
	}
    @Test
    public void testSetTimeToExplode(){
    	bomb3.setTimeToExplode(10000);
    	bomb1.setTimeToExplode(10000);
    	bomb2.setTimeToExplode(10000);
    	assertEquals(bomb3.getTimeToExplode(), 10000);
    	assertEquals(bomb2.getTimeToExplode(), 10000);
    	assertEquals(bomb1.getTimeToExplode(), 10000);
   
    }
    @Test
    public void testGetTimeToExplode(){
    	bomb3.setTimeToExplode(20000);
    	bomb1.setTimeToExplode(20000);
    	bomb2.setTimeToExplode(20000);
    	assertEquals(bomb3.getTimeToExplode(), 20000);
    	assertEquals(bomb2.getTimeToExplode(), 20000);
    	assertEquals(bomb1.getTimeToExplode(), 20000);
    }
    @Test
    public void testSetDetonationTime(){
    	bomb3.setDetonationTime(30000);
    	bomb1.setDetonationTime(30000);
    	bomb2.setDetonationTime(30000);
    	assertEquals(bomb3.getDetonationTime(), 30000);
    	assertEquals(bomb2.getDetonationTime(), 30000);
    	assertEquals(bomb1.getDetonationTime(), 30000);
    
    	
    }
    @Test
    public void testGetDetonationTime(){
    	bomb3.setDetonationTime(40000);
    	bomb1.setDetonationTime(40000);
    	bomb2.setDetonationTime(40000);
    	assertEquals(bomb3.getDetonationTime(), 40000);
    	assertEquals(bomb2.getDetonationTime(), 40000);
    	assertEquals(bomb1.getDetonationTime(), 40000);
    }
    @Test
    public void testGetBombTimer(){
    	
    	assertEquals( bomb3.getBombTimer() , Calendar.getInstance().getTimeInMillis());
    }
    @Test   
    public void testGetBombRange(){
    	assertEquals(bomb2.getBombRange(), 2);
    	assertEquals(bomb1.getBombRange(), 2);
    	assertEquals(bomb3.getBombRange(), 2);
    }
    @Test
    public void testSetBombRange(){
    	bomb2.setBombRange(10);
    	bomb1.setBombRange(10);
    	bomb3.setBombRange(10);
    	assertEquals(bomb2.getBombRange(), 10);
    	assertEquals(bomb1.getBombRange(), 10);
    	assertEquals(bomb3.getBombRange(), 10);
    }
    @Test
    public void  testGetExplodeFast(){
    	assertEquals(bomb2.getExplodeFast(), false);
    	assertEquals(bomb1.getExplodeFast(), false);
    	assertEquals(bomb3.getExplodeFast(), false);
    }
    @Test
    public void testSetExplodeFast(){
    	bomb3.setExplodeFast(true);
    	bomb2.setExplodeFast(true);
    	bomb1.setExplodeFast(true);
    	assertEquals(bomb2.getExplodeFast(), true);
    	assertEquals(bomb1.getExplodeFast(), true);
    	assertEquals(bomb3.getExplodeFast(), true);
    }
}
