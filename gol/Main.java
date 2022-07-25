package gol;

/**
 * @Joel
 * @26/05/22
 * starting and ending the game
 */
public class Main {		 
	
		    	
    public static void main(String[] args){ //beginning music and starting the Game class with intro to gol (game of life)
		 
			String filepath = "Street Party.wav"; 
  
			Sound musicObject = new Sound();
			musicObject.playMusic(filepath);
			
			new Game(); //redirecting to introduction
    
    	}
    }
       
