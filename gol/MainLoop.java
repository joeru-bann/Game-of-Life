	package gol;
	import javax.swing.JFrame;
	import javax.swing.Timer;
	import java.awt.event.ActionListener;
	import java.awt.event.ActionEvent;
	import java.util.Scanner;
	import java.io.IOException;
	
	/**
	 * Looping updates with delayed timer
	 *
	 * @Joel Bannister
	 * @26/05/22
	 */
	public class MainLoop implements ActionListener
	{
		boolean rateChosen = false;
	    private World world;
	    private Timer timer;
	    static boolean gameStarted;
	    private static JFrame window;
	    static boolean random = false; 
	    int cmd = 0;    
	    static int newRate;
	    boolean maxRev;
	    static boolean endTimer = false;
	    static boolean newGame = false;
	    static boolean chosenCell = false;
	    static int chooseRow;
	    static int chooseCol;
	    static String cellChange;
	    static boolean mainLoop;
	    
	    
	    public static void sleep(int time) { // function to help reading process be smoother experience by pausing
	    	try {
	    		Thread.sleep(time);
	    		} catch (Exception e) {}
	        }
	      
	    public MainLoop(){
	    	
 	        // this.timer = new Timer(Refer.newRate, this);
			// this.world = new World();  System.out.println("new world - mainloop");
			// this.timer.start();
	    }
	    
		 public void cellPause() { //to pause for cell selection
	 	        this.timer = new Timer(Refer.newRate, this);
	 	        this.world = new World();  System.out.println("new world - mainloop");
				this.timer.start();

			System.out.println("mainloop: cellpause");
			 Game.gens = 2; //forcing 1 generation for cell-choice option
             Window.random = true;
             endTimer = false;
		} 
		 
		 public static void cellChoose() {
	            Scanner kb = new Scanner (System.in);
           
                 System.out.println("would you like to change any of the cells?");
                 System.out.println("type yes, or no");
                 cellChange = kb.nextLine().toLowerCase();
                 
                 if (cellChange.equals("yes")) {
              	   System.out.println("which column?");
                     chooseCol = kb.nextInt();
                     System.out.println("column: " + chooseCol);

                     System.out.println("which row?");
                     chooseRow = kb.nextInt();
                     System.out.println("row: " + chooseRow);
                     
        	    	  World world2 = new World();
        	    	  world2.cellChange();

                     chosenCell = true;
                     
                     System.out.println("game initiating : cellChoose : mainloop");
                     Window.unPause();
                     
           
                 } 
                 else if (cellChange.equals("no")) { 
     				System.out.println("continuing with default grid..");
     				Window.unPause();
     				chosenCell = false;
                 }
                 else {
        				System.out.println("please enter either yes/no");
                  }
               } 			 
		
	    public void timerBegin() {
	        	 System.out.println("game has begun");
	        	 this.timer.start();
	         }
		  	
	
	    public void start(){
	    
	    	System.out.println("start - mainloop");
             Window.random = true;
	         maxRev = false;
             endTimer = false;
	         System.out.println(world.revolutions + " revolutions starting - mainloop start");
	         }
	    
	    public static void gameRestart () {
	    	if (Window.continueGame = true) {
            Scanner kb = new Scanner (System.in);
				System.out.println("How many more generations?");
				Game.gens = kb.nextInt();
				System.out.println("continuing with " + Game.gens + " generations..");
				mainLoop = false;
				Window.random = false;
				new MainLoop().continueStart(); System.out.println("started loop - mainloop");
				Window.continueGame = true; //ending loop
				gameStarted = true;
				Window.random = false;
			}
		}
	    
	    
	    public void continueStart() {
	    	System.out.println("continueStart - mainloop");
	         maxRev = false;
             endTimer = false;

		    	//.out.println(world.revolutions + " revolutions begin - mainloop");
	    	
	    }
	    
	    public static void gameInitiate () {
	    		    
	    	if (Window.continueGame = true) {
	            Scanner kb = new Scanner (System.in);
				Game.gens = 0; //reset total generations
					System.out.println(Game.gens + " game gens - gameInitiate,");

				System.out.println("How many generations? (do smaller amounts e.g 10-80 for refresh-rates over 300");
	                Game.gens = kb.nextInt();   
	                
	 				System.out.println("continuing with " + Game.gens + " generations..");
					//Window.create(); System.out.println("window create - gameinitiate");
					Window.continueGame = false; //ending loop
					gameStarted = true;
					Window.random = false;
					new MainLoop().initiateStart(); 

	    	}
	    }
	    	public void initiateStart() { //for starting a new game from option "new"
		    	System.out.println("initiateStart");
		    	this.timer.stop();
		         maxRev = false;
	             endTimer = true;
	             System.out.println(world.revolutions + " revolutions begin - mainloop");
	             new MainLoop().timerBegin();

	    	}
	    	public void timerStart () {
	    		this.timer.start();
	    		System.out.println("re-initate timer - timerStart");
	    	}
	
	    
	    @Override //overide for actionlistener
	    public void actionPerformed(ActionEvent e){
	        Window.updateBoard(world);
	        this.world.update();
          
	    		}
	    
	}
