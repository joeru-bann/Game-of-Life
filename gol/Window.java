package gol;

import java.awt.Dimension;
import java.util.Scanner;

import javax.swing.JFrame;

public class Window {

    private static JFrame window;
    private static Board board;
    
    public static void sleep(int time) { // function to help reading process be smoother experience by pausing
    	try {
    		Thread.sleep(time);
    		} catch (Exception e) {}
    }

    public static void create() {
        window = new JFrame("Joel's Game of Life");
        window.setBounds(10, 10, 0, 0);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        board = new Board();
        board.setPreferredSize(new Dimension(Game.cols * Refer.cell_size, Game.rows * Refer.cell_size)); 
        //multiplying dimensions together, result in proportionalality
        board.setFocusable(true);
        window.setContentPane(board); // makes board the child component
        window.pack(); //window fits size of subcompenents
        window.setVisible(true);
        
    }
    

    public static void updateBoard(World renderWorld) {
        board.update(renderWorld);
    }

	public static void dispose() { // method to exit game 
		window.setVisible(false);
		window.dispose(); 
		System.exit(0);
	}
	
	
	
		
	public static void pause (){ //pausing game when generations have completed
		
	            Scanner kb = new Scanner (System.in);

         		System.out.println("All " + Game.gens + " generations, have completed.");
         		
         		
         		System.out.println("new game? type (new), exit game: type (quit) or (q)");
         		String choice = kb.nextLine().toLowerCase();
         	         		
         		if (choice.equals("new")) {
         			System.out.println("creating new game...");
         			sleep(3000);
         			new Main();
         		}
         			else if (choice.equals("quit") || choice.equals("q")){
             			System.out.println("quitting...");
         				Window.dispose();
 	            		System.exit(0);
 	            		window.setVisible(false);
 	            		MainLoop.endTimer = true;
         			}
         		
         			else if (!choice.equals("quit") || !choice.equals("new") || !choice.equals("q")){
         				System.out.println("please choice an option from above");
         				
         			}
 	 }
	
}
