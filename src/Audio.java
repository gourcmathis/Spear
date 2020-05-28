import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JOptionPane;

//gere l'ambiance sonore du jeu
public class Audio {
	//gere la musique du jeu
	void playMusic(String musicLocation)
	{
		try
		{
			//si le fichier audio existe alors on le lance
			File musicPath = new File(musicLocation);
			if(musicPath.exists())
			{
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				//on baisse le volume 
				gainControl.setValue(-30.0f); 
				//on fait tourner la musique en boucle
				clip.loop(clip.LOOP_CONTINUOUSLY);
				clip.start();
				
			}
			//si le fichier est introuvable
			else
			{
				System.out.println("Fichier introuvable");
			}
		}
		//on gere les exceptions
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	//gere les sons du jeu, meme fonctionnement que la musique sans la boucle
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
				//On veut que les bruitages soit un peu plus fort que la musique
				gainControl.setValue(-20.0f); 
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
	
	// on gere le son des fleche a part pour pouvoir l'augmenter
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
				gainControl.setValue(-10.0f); 
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
