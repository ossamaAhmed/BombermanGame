/* 
 * File: GameBoardView.java
 * -----------------------
 * This Class represents takes care of the drawing of the gameboard on a graphics2D object
 * and also it takes care of key listening to direct it to the appropriate controller methods
 */

package bomberMan.gamePlay.View;

import bomberMan.Login.Model.UserDatabase;
import bomberMan.Login.View.LeaderBoardView;
import bomberMan.Login.View.PauseMenuView;
import bomberMan.gamePlay.Controller.CharacterController;
import bomberMan.gamePlay.Controller.BomberManController;
import bomberMan.gamePlay.Controller.EnemyController;
import bomberMan.gamePlay.Controller.GameBoardController;
import bomberMan.gamePlay.Model.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
/**
 * This class contains a set of methods that display the Game Objects contained in a GameBoard object.
 * Also, this class contains methods that display information about the current game session such as the 
 * current time session, the stage and the number of lives of the bomberman.
 * 
 * 
 *  The documentation for the methods contained in this class includes
 * briefs descriptions of the implementations. Such descriptions
 * should be regarded as implementation notes, rather than parts of specifications
 * 
 * @author Tong and Andres Felipe
 *
 */
public class GameBoardView extends JPanel implements KeyListener {

	/* Instance Variables */
	private GameBoard myBoard;

	private Graphics2D myCanvas;
	private BufferedImage image; //
	private Calendar timer1;
	private Timer timer2;
	private long creationTime;
	private JFrame myFrame;
	private BomberManController controller;
	private CharacterController characterC;
	private EnemyController enemyC;
	private int scrollRealtive;
	private JLabel scoreLabel;
	private JLabel timerLabel;
	private JLabel livesLabel;
	private JLabel stageLabel;
	private GameBoardController gmController;
	private int numLivesBomberman;
	private long currentTime;
	private UserDatabase DB;
	private Stage stage;
	private int curStage;
	private long timeRemaining;
	private long endingTime;
	private boolean worstPenality;

	/**
	 * Constructor This method takes care of the initialization of the different
	 * instance variables and adding a key listener to the canvas and painting
	 * the int board.
	 */
	/**
	 * 
	 * @param myFrame a Jframe object
	 * @param numLivesRemainingBomberMan an integer that represents the current number of lives of the bomberman.
	 * @param powerUpsAcquired an array of integers that represents the power ups kept by the bomberman.
	 * @param DB the UserDatabase containing all the  user's information.
	 * @param currentStage is an integer that represents the current stage of the game session.
	 */
	public GameBoardView(JFrame myFrame, int numLivesRemainingBomberMan,
			int powerUpsAcquired[], UserDatabase DB, int currentStage) {
		super();
		this.DB = DB;
		image = null;
		try {
			image = ImageIO.read(new File("BombFlames.png"));
		} catch (IOException e) {
			System.out.println("Not found");
		}

		this.myFrame = myFrame;
		this.curStage = currentStage;
		this.numLivesBomberman = numLivesRemainingBomberMan;
		this.myFrame.setSize(CONSTANTS.SCREEN_WIDTH,
				CONSTANTS.WINDOW_HEIGHT + 100);
		this.setLayout(null);
		scrollRealtive = 0;
		this.worstPenality = false;
		myBoard = new GameBoard(this.curStage, Stage.getStage(currentStage),
				powerUpsAcquired, numLivesRemainingBomberMan,
				this.DB.getCurrentUser(), 0);
		gmController = new GameBoardController(myBoard);
		gmController.start();
		controller = new BomberManController(myBoard);
		// characterC = new CharacterController(myBoard);
		this.addKeyListener(this);
		enemyC = new EnemyController(this.myBoard);
		timer2 = new Timer(50, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {

				updateGameBoardView();
				enemyC.run();
				updateScore();

				// gmController.detonateRegularBombs();
			}
		});

		timer2.start();

		setScoreLabel();
		setTimerLabel();
		setLivesLabel();
		setStageLabel();
	}

	/**
	 * This constructor creates a GameBoardView.
	 * @param myBoard object that represents the game board of the current  session.
	 * @param myFrame a Jframe object
	 * @param numLivesRemainingBomberMan an integer that represents the current number of lives of the bomberman.
	 * @param powerUpsAcquired an array of integers that represents the power ups kept by the bomberman.
	 * @param DB the UserDatabase containing all the user's information.
	 * @param currentStage is an integer that represents the current stage of the game session.
	 */
	public GameBoardView(JFrame myFrame, GameBoard myBoard,
			int numLivesRemainingBomberMan, int[] powerUpsAcquired,
			UserDatabase DB, int currentStage) {
		super();
		this.DB = DB;
		this.myFrame = myFrame;
		this.curStage = currentStage;
		this.numLivesBomberman = numLivesRemainingBomberMan;
		this.myFrame.setSize(CONSTANTS.SCREEN_WIDTH,
				CONSTANTS.WINDOW_HEIGHT + 100);
		this.myBoard = myBoard;
		this.worstPenality = false;
		this.timeRemaining = myBoard.getRemainingTime();
		this.setLayout(null);
		gmController = new GameBoardController(myBoard);
		gmController.start();
		controller = new BomberManController(myBoard);
		characterC = new CharacterController(myBoard);
		this.addKeyListener(this);
		enemyC = new EnemyController(this.myBoard);
		timer2 = new Timer(50, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {

				updateGameBoardView();
				enemyC.run();
				updateScore();

			}
		});

		timer2.start();

		setScoreLabel();
		setTimerLabel();
		setLivesLabel();
	}
	/**
	 * Creates the label where the score will be displayed on the gameplay view
	 */
	public void setScoreLabel() {
		scoreLabel = new JLabel("Score :"
				+ this.DB.getCurrentUser().getMyScore());
		scoreLabel.setSize(200, 50);
		scoreLabel.setOpaque(true);
		scoreLabel.setForeground(Color.red);
		scoreLabel.setBackground(new Color(0, 0, 0, 0));
		scoreLabel.setLocation(10, CONSTANTS.SCORE_SCREEN_START_HEIGHT);
		scoreLabel.setFont(new Font(scoreLabel.getName(), Font.PLAIN, 20));
		this.add(scoreLabel);
	}
	/**
	 * Creates the label where the timer will be displayed
	 */
	public void setTimerLabel() {
		timer1 = Calendar.getInstance();
		creationTime = timer1.getTimeInMillis();
		timerLabel = new JLabel("Time : " + 0 + " s");
		timerLabel.setSize(200, 50);
		timerLabel.setOpaque(true);
		timerLabel.setForeground(Color.red);
		timerLabel.setBackground(new Color(0, 0, 0, 0));
		timerLabel.setLocation(200, CONSTANTS.SCORE_SCREEN_START_HEIGHT);
		timerLabel.setFont(new Font(timerLabel.getName(), Font.PLAIN, 20));
		endingTime = CONSTANTS.ENDINGGAMEPLAYTIME + creationTime
				- this.timeRemaining;
		this.add(timerLabel);

	}
	/**
	 * Creates label where lives will be displayed
	 */
	public void setLivesLabel() {
		livesLabel = new JLabel("Lives : " + this.numLivesBomberman);
		livesLabel.setSize(200, 50);
		livesLabel.setOpaque(true);
		livesLabel.setForeground(Color.red);
		livesLabel.setBackground(new Color(0, 0, 0, 0));
		livesLabel.setLocation(330, CONSTANTS.SCORE_SCREEN_START_HEIGHT);
		livesLabel.setFont(new Font(livesLabel.getName(), Font.PLAIN, 20));
		this.add(livesLabel);
	}

	/**
	 * Creates the label where the stage will be displayed
	 */
	public void setStageLabel() {
		livesLabel = new JLabel("Stage : " + this.curStage);
		livesLabel.setSize(200, 50);
		livesLabel.setOpaque(true);
		livesLabel.setForeground(Color.red);
		livesLabel.setBackground(new Color(0, 0, 0, 0));
		livesLabel.setLocation(430, CONSTANTS.SCORE_SCREEN_START_HEIGHT);
		livesLabel.setFont(new Font(livesLabel.getName(), Font.PLAIN, 20));
		this.add(livesLabel);
	}

	/**
	 * This method updates the lives label on the gameplay view
	 * 
	 * @param lives left over amount for bomberman
	 */
	public void updateLives(int lives) {
		livesLabel.setText("Lives : " + lives);
	}
	/**
	 * Updates the score label that is displayed on the screen of the user.
	 */
	public void updateScore() {
		if (this.DB.getCurrentUser().getUnlockedLevel() < curStage)
			this.DB.setUnlockedLevel(curStage);
		this.DB.updateScore(this.DB.getCurrentUser().getMyScore());
		scoreLabel.setText("Score :" + this.DB.getCurrentUser().getMyScore());
	}
	/**
	 * update and displays timer on the game play view
	 */
	public void updateTimer() {
		currentTime = endingTime - (Calendar.getInstance().getTimeInMillis());
		if (currentTime < 0) {
			currentTime = 0;
		}
		if (currentTime == 0 && worstPenality == false) {
			worstPenality = true;
			this.myBoard.worstPenality(Stage.getStage(this.curStage));
		}
		timerLabel.setText("Time : " + currentTime / 1000 + " s");
	}

	/**
	 * This method takes care of the key listening and calls the different
	 * methods of the controller according to the key pressed or the event
	 * happening
	 */
	public void keyPressed(KeyEvent keyE) {

		if (keyE.getKeyCode() == KeyEvent.VK_RIGHT
				|| keyE.getKeyCode() == KeyEvent.VK_LEFT
				|| keyE.getKeyCode() == KeyEvent.VK_DOWN
				|| keyE.getKeyCode() == KeyEvent.VK_UP) {
			controller.move(keyE.getKeyCode());
			this.updateGameBoardView();
		}
		if (keyE.getKeyCode() == KeyEvent.VK_Q) {
			controller.dropBomb();
		}
		if (keyE.getKeyCode() == KeyEvent.VK_SPACE) {
		
			pause();
			myFrame.remove(this);
			PauseMenuView x = new PauseMenuView(myFrame, this.myBoard, this.DB);
			myFrame.setFocusable(true);
			
			x.setBackground(Color.black);
			x.setVisible(true);
			myFrame.add(x);
			myFrame.validate();
			myFrame.repaint();
			x.requestFocusInWindow();
			myFrame.setVisible(true);
		}
		if (keyE.getKeyCode() == KeyEvent.VK_Z) {
			if (myBoard.getBomberMan().getHasDetonator() == true) {
				gmController.dropBombDetonator();
			}
			if (myBoard.getBomberMan().getHasDetonator() == false) {
				controller.dropBomb();
			}
		}
		
		if (keyE.getKeyCode() == KeyEvent.VK_X) {

			gmController.detonateOldestBomb();
			gmController.detonateOldestBomb();
		}
	}

	/**
	 * This method is required by the interface implemented. Not used yet.
	 */
	public void keyReleased(KeyEvent keyE) {
	}
	/**
	 * This method is required by the interface implemented. Not used yet.
	 */
	public void keyTyped(KeyEvent keyE) {
	}

	/**
	 * This function refreshes the screen. should be called if any changes
	 * happened to the gameBoard.
	 */
	public void updateGameBoardView() {

		this.repaint();

		this.updateTimer();
	
		if (gmController.getBoard().getBomberMan().getIsAlive() == false
				&& this.numLivesBomberman > 1) {
			this.startAgain();
			
		} else if (this.gmController.getBoard().getBomberMan().getIsAlive() == false
				&& this.numLivesBomberman == 1) {
			pause();
			this.updateLives(0);
			this.myFrame.remove(this);
			LeaderBoardView x = new LeaderBoardView(myFrame, this.DB, "Main");
			myFrame.setFocusable(true);
			
			x.setBackground(Color.black);
			x.setVisible(true);
			myFrame.add(x);
			myFrame.validate();
			myFrame.repaint();
			x.requestFocusInWindow();
			myFrame.setVisible(true);
		}
		this.runNextStage();
	}

	/**
	 * This function Draws the gameBoard on the screen. Renders the data only.
	 * Any changes shouldn't happen here.
	 * @param g a Graphics object used to pain the GameBoard view.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.myCanvas = (Graphics2D) g;

		int bombermanCellX = this.myBoard.getBomberMan().getPositionX();
		int bombermanX = this.myBoard.getBomberMan().getPositionX()
				/ CONSTANTS.TILE_SIDE_SIZE;
		
		if (bombermanCellX > (CONSTANTS.SCREEN_WIDTH / 2) && bombermanX < 23) {
			scrollRealtive = bombermanCellX - (CONSTANTS.SCREEN_WIDTH / 2);

		}
		for (int i = 0; i < CONSTANTS.NUMBER_OF_VERTICAL_TILES; i++) {
			for (int j = 0; j < CONSTANTS.NUMBER_OF_HORIZONTAL_TILES; j++) {
				if (j > -1 && j < 31) {
					if (!this.myBoard.getCell(i, j).isEmpty())
						myCanvas.drawImage(myBoard.getCell(i, j).getImage(),
								myBoard.getCell(i, j).getObjects().get(0)
										.getPositionX()
										- this.scrollRealtive,
								myBoard.getCell(i, j).getObjects().get(0)
										.getPositionY(), null);
				}
			}
		}
		myCanvas.drawImage(myBoard.getBomberMan().getImage(), myBoard
				.getBomberMan().getPositionX() - this.scrollRealtive, myBoard
				.getBomberMan().getPositionY(), null);
		for (int i = 0; i < myBoard.getEnemies().size(); i++) {
			myCanvas.drawImage(myBoard.getEnemies().get(i).getImage(), myBoard
					.getEnemies().get(i).getPositionX()
					- this.scrollRealtive, myBoard.getEnemies().get(i)
					.getPositionY(), null);
		}
	
	}

	/**
	 * Pauses the game play.
	 */
	public void pause() {
	
		timer2.stop();
		this.timeRemaining = this.currentTime;
		this.myBoard.setRemainingTime(this.timeRemaining);
	}

	/**
	  * Unpause the gameplay
	  */
	public void unpause() {
		
		timer2.start();
		this.endingTime = this.timeRemaining
				+ Calendar.getInstance().getTimeInMillis();

	}
	/**
	 * Starts the game board controller
	 */
	public void startController() {
		this.gmController.run();
	}
	/**
	 * Starts again a game session when the bomberman loses a life, but he still has more than 0 lives remaining.
	 */
	public void startAgain() {
		timer2.stop();
		myFrame.remove(this);
		GameBoardView x = new GameBoardView(myFrame,
				this.myBoard.getLives() - 1, this.myBoard.getBomberMan()
						.getPowerUpsKeptAfterDeath(), this.DB, curStage);
		myFrame.setFocusable(true);
	
		x.setBackground(Color.black);
		x.setVisible(true);
		myFrame.add(x);
		myFrame.validate();
		myFrame.repaint();
		x.requestFocusInWindow();
		myFrame.setVisible(true);
	}
	/**
	 * Loads the next stage that is going to be displayed once the user successfully completes a game stage.
	 */
	public void runNextStage() {
		int iExitCell = this.myBoard.getExit().getPositionX()
				/ CONSTANTS.TILE_SIDE_SIZE;
		int jExitCell = this.myBoard.getExit().getPositionY()
				/ CONSTANTS.TILE_SIDE_SIZE;
		int iBMBCell = this.myBoard.getBomberMan().getPositionX()
				/ CONSTANTS.TILE_SIDE_SIZE;
		int jBMBCell = this.myBoard.getBomberMan().getPositionY()
				/ CONSTANTS.TILE_SIDE_SIZE;

		if (iExitCell == iBMBCell && jBMBCell == jExitCell) {
		
			if (this.myBoard.getNumEnemies() == 0) {
				timer2.stop();
				myFrame.remove(this);
				GameBoardView x = new GameBoardView(myFrame,
						this.myBoard.getLives(), this.myBoard.getBomberMan()
								.getPowerUpsKeptAfterDeath(), this.DB,
						curStage + 1);
				myFrame.setFocusable(true);
				
				x.setBackground(Color.black);
				x.setVisible(true);
				myFrame.add(x);
				myFrame.validate();
				myFrame.repaint();
				x.requestFocusInWindow();
				myFrame.setVisible(true);

			}
		}

	}
}