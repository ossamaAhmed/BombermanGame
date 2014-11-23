package bomberMan.gamePlay.Controller;
import java.util.*;
import java.lang.Runnable;

import bomberMan.gamePlay.Model.*;

public class DestroyBomb extends Thread {
    GameBoard gameBoard;
    GameBoardController controller;
	public DestroyBomb(GameBoard board, GameBoardController controller){
		this.gameBoard = board;
		this.controller = controller;
	}
	@Override
	public void run() {
		while(gameBoard.getBomberMan().getIsAlive())
		{
				
			this.controller.detonateRegularBombs();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}

}
