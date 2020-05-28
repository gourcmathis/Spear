import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JOptionPane;

public class Audio {
	void playMusic(String musicLocation)
	{
		try
		{
			File musicPath = new File(musicLocation);
			if(musicPath.exists())
			{
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(-30.0f); // Reduce volume by 30 decibels.
				clip.loop(clip.LOOP_CONTINUOUSLY);
				clip.start();
				
				
			}
			else
			{
				System.out.println("Fichier introuvable");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	void playSong(String musicLocation)
	{
		try
		{
			File musicPath = new File(musicLocation);
			if(musicPath.exists())
			{
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(-20.0f); // Reduce volume by 20 decibels.
				clip.start();
				
				
			}
			else
			{
				System.out.println("Fichier introuvable");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	void playArrow(String musicLocation)
	{
		try
		{
			File musicPath = new File(musicLocation);
			if(musicPath.exists())
			{
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
				clip.start();
				
				
			}
			else
			{
				System.out.println("Fichier introuvable");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
