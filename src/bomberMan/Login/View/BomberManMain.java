package bomberMan.Login.View;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;


public class BomberManMain {
	static JFrame game;
	static LoginView login;
	private static final int BUFFER_SIZE = 4096;
    /**
     * Play a given audio file.
     * @param audioFilePath Path of the audio file.
     */
    public static void play(String audioFilePath) 
    {
        File audioFile = new File(audioFilePath);
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
 
            AudioFormat format = audioStream.getFormat();
 
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
 
            SourceDataLine audioLine = (SourceDataLine) AudioSystem.getLine(info);
 
            audioLine.open(format);
 
            audioLine.start();
             
            System.out.println("Playback started.");
             
            byte[] bytesBuffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            while ((bytesRead = audioStream.read(bytesBuffer)) != -1) 
	            {
	                audioLine.write(bytesBuffer, 0, bytesRead);
	            }
             
            audioLine.drain();
            audioLine.close();
            audioStream.close();
             
            System.out.println("Playback completed.");
             
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }      
    }

	public static void main(String[] args) throws Exception
	{
		final int window_width=900;
		final int window_height=506;
		game = new JFrame("BomberMan");
		login= new LoginView(game);
		 String audioFilePath = "bomb.wav";
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
		while(true)
		{
			play(audioFilePath);
		}
	}

}
