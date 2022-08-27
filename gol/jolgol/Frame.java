package jolgol;
//JFrame -> the frame of the canvas
import javax.swing.JFrame;

public class Frame extends JFrame{
 private static final long serialVersionUID = 1L;

	public Frame() {
		add(new Panel());
		
		//setting the size: width=700; height=1300
		setSize (800, 800);		
				
		setLocationRelativeTo(null); //setting window to be middle by default
		
		setVisible (true);	//we want to see the board, so we set it to visible - true:

		setAlwaysOnTop(true); //setting the window to be the "top" tab/front tab
				
		//setting default: when we close our program, all things shut down:
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	}

}
