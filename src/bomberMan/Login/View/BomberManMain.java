package bomberMan.Login.View;

import javax.swing.*;
import javax.swing.JFrame;


public class BomberManMain {
	static JFrame game;
	static LoginView login;

	public static void main(String[] args)
	{
		final int window_width=900;
		final int window_height=506;
		game = new JFrame("BomberMan");
		login= new LoginView(game);
		SwingUtilities.invokeLater(new Runnable() 
	    {
	      @Override
	      public void run()  
	      {
	
	    		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    		game.setSize(window_width, window_height); 		
	    		//gameView.setResizable(false); //Resizing Not working should look into it
	    		//login.setBackground(Color.white);	
	    		login.setVisible(true);	
	    		game.add(login);		
	    		game.setVisible(true);
	    	
	      }
	    });
	}

}
