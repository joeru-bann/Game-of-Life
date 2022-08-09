package jolgol;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

	public class Sound {
		void playMusic(String musicLocation) {

			try {
				File musicPath = new File(musicLocation);
				if(musicPath.exists()) 
				{		
					AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
					Clip clip = AudioSystem.getClip();
					clip.open(audioInput);
					clip.start();
					
					//clip.loop(0); //plays the music track once
					clip.loop(Clip.LOOP_CONTINUOUSLY); //indefinitely loops the music
	
				}
				else {
					System.out.println("Encountered error while attempting to play music");
				}	
			}
				
		catch(Exception ex) {
				ex.printStackTrace();		
			}
		}
	}	