package jolgol;

import java.util.Random;

//Joel game of life - frame


//An extended version of java.awt.Frame that adds support for the JFC/Swing component architecture:
//JFrama -> the frame of the canvas
import javax.swing.JFrame;

public class golFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	public golFrame() {
		add(new golPanel());
		
		setSize (1300, 700); //width, height
		
		setVisible (true); //board becomes visible
		
		//setting default: when we close our program, all things shut down:
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
	}
	public static void main(String[] args) {
		
//		String[] arr = {"Street Party", "Tropical Carnival", "Lost Woods"};
//        Random rand = new Random();
//
//        // randomly selects an index from the arr
//        int select = rand.nextInt(arr.length); 
//    	String noWav = arr[select] + ".wav";
//    	
//    	
//    	String filepath = noWav;
//    		
//    	System.out.println("now playing: " + arr[select]);
//			Sound musicObject = new Sound(); 
//			musicObject.playMusic(filepath);
			
			new golPanel(); //redirecting

    	}
	
}

