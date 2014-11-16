/*
 * File: CONSTANTS.java
 * --------------------------------
 * This file declares several constants that are shared by the
 * different modules in the bomberMan application.  Any class
 * that includes this class can use these constants.
 */

package bomberMan.gamePlay.Model;

public class CONSTANTS {
	public final static int DEFAULT_BOMB_RANGE=2; // in number Of blocks
	public final static int INITIAL_BOMBERMAN_X_POS = 50;
	public final static int INITIAL_BOMBERMAN_Y_POS = 50;
	public final static int BOMBERMAN_WIDTH = 20;
	public final static int BOMBERMAN_HEIGHT = 20;
	public final static int BOMB_TIMER=8000;//in milliseconds
	public final static int BOMB_EXPLOSION_SHOWING_TIME = 100000; // in milliseconds
	public final static int BOMB_FLAME_SHOWING_TIME = 10000; // in milliseconds
	public final static int BOMB_RANGE1 =2;
	public final static int DEFAULT_SPEED=5;
	public final static int NUMBER_OF_HORIZONTAL_TILES=31;
	public final static int NUMBER_OF_VERTICAL_TILES=13;
	public final static int TILE_SIDE_SIZE=40; //in pixels
	public final static String CONCRETE_WALL_IMAGE="concrete.png";
	public final static String BomberMan_IMAGE="Bomberman.png";
	public final static String Bomb_IMAGE ="Bomb.png";
	public final static String Bomb_EXPLOSION ="BombMainExplosionV1.png";
	public final static String Bomb_EXPLOSION2 ="BombMainExplosionV2.png";
	public final static String BMB_dead = "SkullBMB.png";
	public final static int WINDOW_WIDTH=NUMBER_OF_HORIZONTAL_TILES*TILE_SIDE_SIZE;
	public final static int WINDOW_HEIGHT=NUMBER_OF_VERTICAL_TILES*TILE_SIDE_SIZE;

}
