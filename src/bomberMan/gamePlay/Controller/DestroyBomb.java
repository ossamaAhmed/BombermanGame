package bomberMan.gamePlay.Controller;
import java.util.*;
import java.lang.Runnable;
import bomberMan.gamePlay.Model.*;

public class DestroyBomb implements Runnable {
    GameBoard gameBoard;
    GameBoardController controller;
	public DestroyBomb(GameBoard board, GameBoardController controller){
		this.gameBoard = board;
		this.controller = controller;
	}
	@Override
	public void run() {
		while(gameBoard.getBomberMan().getIsAlive()){
			//this.controller.pickPowerUp();
			this.controller.detonateRegularBombs();}
		

	}

}
