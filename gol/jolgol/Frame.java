package jolgol;
//JFrame -> the frame of the canvas
import javax.swing.JFrame;


public class Frame extends JFrame{
 private static final long serialVersionUID = 1L;

 	private static JFrame frame;

	public Frame() {
        frame = new JFrame("Joel's Game of Life");

		frame.add(new Panel());
		
		//setting the size: 
		frame.setSize(800, 800); //square dimensions
				
		frame.setLocationRelativeTo(null); //setting window to be middle by default
		
		frame.setVisible (true);	//we want to see the board, so we set it to visible - true:

		frame.setAlwaysOnTop(true); //setting the window to be the "top" tab/front tab
				
		//setting default: when we close our program, all things shut down:
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	}
}
