package gol;

import java.awt.Dimension;
import java.util.Scanner;

import javax.swing.JFrame;

public class Window { 

    private static JFrame window;
    private static Board board;
    static String choice;
    static Scanner kb = new Scanner (System.in);
    static boolean newGame = false;
    static boolean continueGame = false;
    static boolean random = false;
    
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

        board = new Board(); System.out.println("created board - window");
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
		if (random = false) {
			System.out.println("random is false");
		}
		else {
			System.out.println("window random is true");
		}
		

		System.out.println("All " + Game.gens + " generations, have completed. \n");
 		System.out.println("new game? type (new), exit game: type (end) or (e), or conintue (c)");
 		choice = kb.nextLine().toLowerCase();
 		World.maxRev = true;
				
		while(World.maxRev = true) {
	
         		if (choice.equals("new")) {
    				Game.gens = 0; //reset total generations
                    random = true;
         			//window.dispose();
                    System.out.println("creating new game...");
                    sleep(1500);
         			Window.newGame();
     				MainLoop.random = true;
         			MainLoop.gameStarted = true;
     				continueGame = true;
         			break;
         		}
         			else if (choice.equals("end") || (choice.equals("e"))) {
             			System.out.print("quitting in: ");

             				System.out.print("3, ");
             				sleep(600);
             				System.out.print("2, ");
             				sleep(600);
             				System.out.print("1..");
             				sleep(600);
             				
         				Window.dispose();
 	            		System.exit(0);
 	            		window.setVisible(false);
 	            		MainLoop.endTimer = true;
         			}
         			else if (choice.equals("c")) {
         		    	MainLoop.endTimer = true;
         				continueGame = true;
         				Window.unPause();
         				random = false;
         				MainLoop.random = false;
         				
         				if (Window.random = false) {
         					System.out.println("random is false");
         				}
         				else {
         					System.out.println("window random is true/else");
         				}
         				break;
         			}	
         			else {
         				System.out.println("error: please choice an option from above");
         				World.maxRev = false;
         				continueGame = false;
             			break;
         			}
 	   }
	}
	public static void unPause () {
		System.out.println("unpause");
			World.maxRev = false;
			MainLoop.gameRestart();
			}
	
	public static void newGame () {
		System.out.println("newGame - window");
			World.maxRev = false;
			MainLoop.gameInitiate();
			
		}
}
