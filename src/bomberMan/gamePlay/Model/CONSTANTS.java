/*
 * File: CONSTANTS.java
 * --------------------------------
 * This file declares several constants that are shared by the
 * different modules in the bomberMan application.  Any class
 * that includes this class can use these constants.
 */

package bomberMan.gamePlay.Model;

public class CONSTANTS {
	public final static int maximumBrickMAP = 30; //max of bricks per map
	public  static int DEFAULT_BOMB_RANGE=2; // in number Of blocks
	public final static int INITIAL_BOMBERMAN_X_POS = 50;
	public final static int INITIAL_BOMBERMAN_Y_POS = 50;
	public final static int BOMBERMAN_WIDTH = 20;
	public final static int BOMBERMAN_HEIGHT = 20;
	public static int BOMB_TIMER=8000;//in milliseconds
	public static int BOMB_TIMER2=1000;//in milliseconds
	public final static int BOMB_EXPLOSION_SHOWING_TIME = 100000; // in milliseconds
	public final static int BOMB_EXPLOSION_SHOWING_TIME2 = 100000;
	public final static int BOMB_FLAME_SHOWING_TIME = 10000; // in milliseconds
	public static int BOMB_RANGE1 =3;
	public static int DEFAULT_SPEED=5;
	public static int DEFAULT_SPEEDBOMBERMAN=5;
	public static int DEFAULT_SPEEDENEMY=5;
	public static int DEFAULT_POWERUPSPEED_INCREASE=3;
	public final static int NUMBER_OF_HORIZONTAL_TILES=31;
	public final static int NUMBER_OF_VERTICAL_TILES=13;
	public final static int TILE_SIDE_SIZE=40; //in pixels
	public final static String CONCRETE_WALL_IMAGE="concrete.png";
	public final static String BRICK_WALL_IMAGE="Brick.png";
	public final static String BomberMan_IMAGE="Bomberman.png";
	public final static String Bomb_IMAGE ="Bomb.png";
	public final static String Bomb_EXPLOSION ="BombMainExplosionV1.png";
	public final static String Bomb_EXPLOSION2 ="BombMainExplosionV2.png";
	public final static String MYSTERY_POWERUP ="mysteryPowerup.png";
	public final static String IMMUNITY_POWERUP ="ImmunityPowerUp.png";
	public final static String FLAME_POWERUP ="FlamePowerUp.png";
	public final static String BOMB_RANGE_POWERUP ="BombRangePowerUp.png";
	public final static String BOMB_POWERUP ="BombPowerUp.png";
	public final static String BOMB_IMMUNITY_POWERUP ="BombImmunity.png";
	public final static String SPEED_POWERUP ="SpeedPowerUp.png";
	public final static String WALLPASS_POWERUP ="WallPassPowerUp.png";
	public final static String BMB_dead = "SkullBMB.png";
	
	public final static int WINDOW_WIDTH=NUMBER_OF_HORIZONTAL_TILES*TILE_SIDE_SIZE;
	public final static int WINDOW_HEIGHT=NUMBER_OF_VERTICAL_TILES*TILE_SIDE_SIZE;
    public void setBombRange(int range){CONSTANTS.BOMB_RANGE1 = range;}
    public void setSpeedBomberman(int speed){ CONSTANTS.DEFAULT_SPEEDBOMBERMAN = speed;}
    public void setBombTime(int timer){ CONSTANTS.BOMB_TIMER = timer;}
}
