package bomberMan.Login.View;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import bomberMan.gamePlay.Model.GameObject;
public class MusicPlayer 
{
	private static final int BUFFER_SIZE = 4096;
    /**
     * Play a given audio file.
     * audioFilePath Path of the audio file.
     */
	private static  AudioInputStream audioStream ;
	private static SourceDataLine audioLine;
	
	public MusicPlayer()
	{
		
	}
    public static void play(String audioFilePath) 
    {
       
        try {
           audioStream= AudioSystem.getAudioInputStream(GameObject.class.getResourceAsStream("/raw/" + audioFilePath));
 
            AudioFormat format = audioStream.getFormat();
 
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
 
             audioLine = (SourceDataLine) AudioSystem.getLine(info);
 
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
        } catch (Exception ex) {
            System.out.println("Error playing the audio file: " + audioFilePath);
            ex.printStackTrace();
        }      
    }

}
