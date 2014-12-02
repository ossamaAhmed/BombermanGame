package bomberMan.Login.View;

import javax.swing.*;
import bomberMan.Login.Model.*;


public class BomberManMain {
	static JFrame game;
	static LoginView login;
	

	public static void main(String[] args) throws Exception
	{
		
		final int window_width=CONSTANTS.WINDOW_WIDTH;
		final int window_height=CONSTANTS.WINDOW_HEIGHT;
		game = new JFrame("BomberMan");
		MusicPlayer player=new MusicPlayer();
		login= new LoginView(game);
		String audioFilePath = "bomb.wav";
		SwingUtilities.invokeLater(new Runnable() 
	    {
	      @Override
	      public void run()  
	      {  	
	    		game.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    		game.setSize(window_width, window_height); 		
	    		login.setVisible(true);	
	    		game.add(login);		
	    		game.setVisible(true);
	    	
	      }
	    });
	}

}
