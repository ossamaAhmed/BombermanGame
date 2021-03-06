/*
 * File: CONSTANTS.java
 * --------------------------------
 * This file declares several constants that are shared by the
 * different modules in the bomberMan application.  Any class
 * that includes this class can use these constants.
 */

package bomberMan.gamePlay.Model;

public class CONSTANTS implements java.io.Serializable{
	public final static int ENDINGGAMEPLAYTIME = 200000; // ending game time
	public final static int maximumBrickMAP = 30; //max of bricks per map
	public  static int DEFAULT_BOMB_RANGE=2; // in number Of blocks
	public final static int INITIAL_BOMBERMAN_X_POS = 50;
	public final static int INITIAL_BOMBERMAN_Y_POS = 50;
	public final static int BOMBERMAN_WIDTH = 20;
	public final static int BOMBERMAN_HEIGHT = 20;
	public static int BOMB_TIMER=3000;//in milliseconds
	public static int BOMB_TIMER2=1000;//in milliseconds
	public final static int BOMB_EXPLOSION_SHOWING_TIME = 100000; // in milliseconds
	public final static int BOMB_EXPLOSION_SHOWING_TIME2 = 100000;
	public final static int BOMB_FLAME_SHOWING_TIME = 20000; // in milliseconds
	public final static int LEFT = 1; // in milliseconds
	public final static int RIGHT = 2; // in milliseconds
	public final static int UP = 3; // in milliseconds
	public final static int DOWN = 4; // in milliseconds
	public static int BOMB_RANGE1 =3;
	public static int DEFAULT_SPEED=6;
	public final static int LIVESBOMBERMAN = 3;
	public static int DEFAULT_SPEEDBOMBERMAN=6;
	public static int SPEEDENEMY_SLOWEST=1;
	public static int SPEEDENEMY_SLOW=2;
	public static int SPEEDENEMY_NORMAL=3;
	public static int SPEEDENEMY_FAST=4;
	public static int DEFAULT_POWERUPSPEED_INCREASE = 5;
	public static long delayINVISIBILITY = 40000;
	public final static int NUMBER_OF_HORIZONTAL_TILES=31;
	public final static int NUMBER_OF_VERTICAL_TILES=13;
	public final static int TILE_SIDE_SIZE=40; //in pixels
	public final static String EXIT_IMAGE = "ExitDoorImage.png";
	public final static String CONCRETE_WALL_IMAGE="Concrete.png";
	public final static String BRICK_WALL_IMAGE="Brick.png";
	public final static String BomberMan_IMAGE="Bomberman.png";
	public final static String BALLOON_IMAGE="balloon.png";
	public final static String Bomb_IMAGE ="Bomb.png";
	public final static String Bomb_EXPLOSION ="BombMainExplosionV1.png";
	public final static String Bomb_EXPLOSION2 ="BombMainExplosionV2.png";
	public final static String MYSTERY_POWERUP ="mysteryPowerup.png";
	public final static String IMMUNITY_FLAME_POWERUP ="ImmunityPowerUp.png";
	public final static String FLAME_POWERUP ="FlamePowerUp.png";
	public final static String BOMB_PASS_POWERUP ="BombPassPowerUp.png";
	public final static String BOMBS_POWERUP ="BombPowerUp.png";
	public final static String BOMB_DETONATOR_POWERUP ="BombDetonator.png";
	public final static String SPEED_POWERUP ="SpeedPowerUp.png";
	public final static String WALLPASS_POWERUP ="WallPassPowerUp.png";
	public final static String BMB_dead = "SkullBMB.png";
	public final static int WINDOW_WIDTH=NUMBER_OF_HORIZONTAL_TILES*TILE_SIDE_SIZE;
	public final static int SCREEN_WIDTH=15*TILE_SIDE_SIZE;
	public final static int WINDOW_HEIGHT=(NUMBER_OF_VERTICAL_TILES*TILE_SIDE_SIZE);
	public final static int SCORE_SCREEN_START_HEIGHT=WINDOW_HEIGHT+10; //in pixels
    public void setBombRange(int range){CONSTANTS.BOMB_RANGE1 = range;}
    public void setSpeedBomberman(int speed){ CONSTANTS.DEFAULT_SPEEDBOMBERMAN = speed;}
    public void setBombTime(int timer){ CONSTANTS.BOMB_TIMER = timer;}
}
