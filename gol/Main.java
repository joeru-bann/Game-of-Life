package gol;
import java.io.File;
import java.util.Random;
import java.util.Scanner;
/**
 * @Joel
 * @26/05/22
 * starting game + with music
 */
public class Main {

		
    public static void main(String[] args){ //beginning music and starting the Game class with intro to gol (game of life)

    	String[] arr = {"Street Party", "Tropical Carnival", "Lost Woods"};
        Random rand = new Random();

        // randomly selects an index from the arr
        int select = rand.nextInt(arr.length); 
    	String noWav = arr[select] + ".wav";
    	
    	
    	String filepath = noWav;
    		
    	System.out.println("now playing: " + arr[select]);
			Sound musicObject = new Sound();
			musicObject.playMusic(filepath);
			
			new Game(); //redirecting to introduction
    	}
    	
    }	

       
