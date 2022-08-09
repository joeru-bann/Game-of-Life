package gol;

import java.awt.Dimension;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
        window.setSize(800,600);
        window.setBounds(10, 10, 0, 0);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        board = new Board(); System.out.println("created board - window:create");
        board.setPreferredSize(new Dimension(Game.cols , Game.rows)); 

       // board.setPreferredSize(new Dimension(Game.cols * Refer.cell_size, Game.rows * Refer.cell_size)); 
        //multiplying dimensions together, result in proportionalality
        board.setFocusable(true);
        window.setContentPane(board); 
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
		 if (Game.gens == 2){
				System.out.println("game gens = 2 : Window");
				MainLoop.chosenCell = false;
				  MainLoop.cellChoose();
			}
		
		 else {
	
		Scanner kb = new Scanner (System.in);
		if (World.maxRev = true && Game.gens != 2){
			Game.gens = World.finalRevs; 
			System.out.println("All " + Game.gens + " generations, have completed. \n");
	 		System.out.println("new game? type new, exit game: type end/q, or c/continue) \n");
			choice = kb.nextLine().toLowerCase();	 	
		}
         		if (choice.equals("new")) {
    				Game.gens = 0; //reset total generations
                    System.out.println("creating new game...");
                    sleep(1500);
         			Window.newGame();
         			
         		}
         			 if (choice.equals("end") || (choice.equals("q"))) {
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
         			else if (choice.equals("c") || (choice.equals("continue"))) {
         				Window.unPause();
         			}	
         			else if (choice.equals("rate")) {
    	            		System.out.println("Change cell rate to..?");
    	            		Game.refresh_rate = kb.nextInt();
    	            		System.out.println("The new refresh rate is: " + Refer.newRate + ", " + Game.rate);
         			}
         			else {
         				System.out.println("error: please choice an option from above");
         				
         			}
		    }
 	   }
	
	public static void unPause () { //continuing with the game
		System.out.println("unpause");
			World.maxRev = false;
			MainLoop.gameRestart();
			
		}
	public static void newGame () { //starting new game
		System.out.println("newGame - window");
			World.maxRev = false;
			MainLoop.gameInitiate();
			
		}
}
