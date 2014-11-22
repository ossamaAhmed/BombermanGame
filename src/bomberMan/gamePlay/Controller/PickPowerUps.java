package bomberMan.gamePlay.Controller;

import bomberMan.gamePlay.Model.GameBoard;

public class PickPowerUps implements Runnable {
	 GameBoard gameBoard;
	    GameBoardController controller;
	    public PickPowerUps(GameBoard board, GameBoardController controller){
			this.gameBoard = board;
			this.controller = controller;
		}
	@Override
	public void run() {
		while(gameBoard.getBomberMan().getIsAlive()){
			this.controller.pickPowerUp();}
	}

}
