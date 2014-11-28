package gameModelTests;

import static org.junit.Assert.*;
import bomberMan.gamePlay.Model.*;

import org.junit.*;

public class PowerUpTest {
	private PowerUp pwup;	
	private PowerUp pwup2;
	private PowerUp pwup3;
	private PowerUp pwup4;
	private PowerUp pwup5;
	private PowerUp pwup6;
	private PowerUp pwup7;
	private PowerUp pwup8;
	@Before
	public void setUp(){
		pwup = new PowerUp (10,10,CONSTANTS.SPEED_POWERUP ,PowerUpType.SPEED,"SPEED");
		pwup2 = new PowerUp (10,10,CONSTANTS.BOMBS_POWERUP ,PowerUpType.BOMBS,"BOMBS");
		pwup3 = new PowerUp (10,10,CONSTANTS.FLAME_POWERUP ,PowerUpType.FLAMES,"FLAMES");
		pwup4 = new PowerUp (10,10,CONSTANTS.BOMB_DETONATOR_POWERUP ,PowerUpType.DETONATOR,"DETONATOR");
		pwup5 = new PowerUp (10,10,CONSTANTS.BOMB_PASS_POWERUP ,PowerUpType.BOMBPASS,"BOMBPASS");
		pwup6 = new PowerUp (10,10,CONSTANTS.IMMUNITY_FLAME_POWERUP ,PowerUpType.FLAMEPASS,"FLAMEPASS");
		pwup7 = new PowerUp (10,10,CONSTANTS.WALLPASS_POWERUP ,PowerUpType.WALLPASS,"WALLPASS");
		pwup8 = new PowerUp (10,10,CONSTANTS.MYSTERY_POWERUP ,PowerUpType.MYSTERY,"MYSTERY");
	}
	
	
	@Test
	public void testSpeedPowerUp(){
		assertEquals(PowerUpType.SPEED ,pwup.getPowerUpType());
		assertEquals(10, pwup.getPositionX());
		assertEquals(10, pwup.getPositionY());
		assertEquals(CONSTANTS.SPEED_POWERUP, pwup.getImageLocation());
		assertEquals("SPEED", pwup.getType());
	}
	@Test
	public void testBombsPowerUp(){
		assertEquals(PowerUpType.BOMBS ,pwup2.getPowerUpType());
		assertEquals(10, pwup2.getPositionX());
		assertEquals(10, pwup2.getPositionY());
		assertEquals(CONSTANTS.BOMBS_POWERUP, pwup2.getImageLocation());
		assertEquals("BOMBS", pwup2.getType());
	}
	@Test
	public void testFlamesPowerUp(){
		assertEquals(PowerUpType.FLAMES ,pwup3.getPowerUpType());
		assertEquals(10, pwup3.getPositionX());
		assertEquals(10, pwup3.getPositionY());
		assertEquals(CONSTANTS.FLAME_POWERUP, pwup3.getImageLocation());
		assertEquals("FLAMES", pwup3.getType());
	}
	@Test
	public void testDetonatorPowerUp(){
		assertEquals(PowerUpType.DETONATOR,pwup4.getPowerUpType());
		assertEquals(10, pwup4.getPositionX());
		assertEquals(10, pwup4.getPositionY());
		assertEquals(CONSTANTS.BOMB_DETONATOR_POWERUP, pwup4.getImageLocation());
		assertEquals("DETONATOR", pwup4.getType());
	}
	@Test
	public void testBombPassPowerUp(){
		assertEquals(PowerUpType.BOMBPASS,pwup5.getPowerUpType());
		assertEquals(10, pwup5.getPositionX());
		assertEquals(10, pwup5.getPositionY());
		assertEquals(CONSTANTS.BOMB_PASS_POWERUP, pwup5.getImageLocation());
		assertEquals("BOMBPASS", pwup5.getType());
	}
	@Test
	public void testFlamePassPowerUp(){
		assertEquals(PowerUpType.FLAMEPASS,pwup6.getPowerUpType());
		assertEquals(10, pwup6.getPositionX());
		assertEquals(10, pwup6.getPositionY());
		assertEquals(CONSTANTS.IMMUNITY_FLAME_POWERUP, pwup6.getImageLocation());
		assertEquals("FLAMEPASS", pwup6.getType());
	}
	@Test
	public void testWallPassPowerUp(){
		assertEquals(PowerUpType.WALLPASS,pwup7.getPowerUpType());
		assertEquals(10, pwup7.getPositionX());
		assertEquals(10, pwup7.getPositionY());
		assertEquals(CONSTANTS.WALLPASS_POWERUP, pwup7.getImageLocation());
		assertEquals("WALLPASS", pwup7.getType());
	}
	@Test
	public void testMysteryPowerUp(){
		assertEquals(PowerUpType.MYSTERY,pwup8.getPowerUpType());
		assertEquals(10, pwup8.getPositionX());
		assertEquals(10, pwup8.getPositionY());
		assertEquals(CONSTANTS.MYSTERY_POWERUP, pwup8.getImageLocation());
		assertEquals("MYSTERY", pwup8.getType());
	}
	
	

}
