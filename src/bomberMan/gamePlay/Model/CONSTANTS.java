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
	public final static int BOMB_TIMER=2;
	public final static int DEFAULT_SPEED=5;
	public final static int NUMBER_OF_HORIZONTAL_TILES=31;
	public final static int NUMBER_OF_VERTICAL_TILES=13;
	public final static int TILE_SIDE_SIZE=40; //in pixels
	public final static String CONCRETE_WALL_IMAGE="concrete.png";
	public final static String BomberMan_IMAGE="Bomberman.png";
	public final static int WINDOW_WIDTH=NUMBER_OF_HORIZONTAL_TILES*TILE_SIDE_SIZE;
	public final static int WINDOW_HEIGHT=NUMBER_OF_VERTICAL_TILES*TILE_SIDE_SIZE;

}
