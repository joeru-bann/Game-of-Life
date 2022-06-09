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
	    
	    
	    public static void sleep(int time) { // function to help reading process be smoother experience by pausing
	    	try {
	    		Thread.sleep(time);
	    		} catch (Exception e) {}
	    }
	    

	    public MainLoop(){
 	         this.timer = new Timer(Refer.newRate, this);
			 this.world = new World(); 


			 System.out.println("new world - start mainloop");

	        
	    		if (Window.random = false) {
	    		System.out.println("mainloop random = false - mainloop");	
	    		}
	    		else if (Window.random = true) {
	    		}
	    		else {
		    		System.out.println("mainloop random = false - mainloop");	
	    		}
	          
	    	}
	    
	    public void timerStop() {
	         while (endTimer = true) {
	        	 System.out.println("game has ended");
	        	 this.timer.stop();
	        	 break;
	         }
		  } 	
	
	    public void start(){
	 	     this.timer.start(); //timer begins

	    	System.out.println("start - mainloop");
	    	 random = false;
             Window.random = true;
	    	
	         maxRev = false;
             endTimer = false;
	 	     this.timer.start(); //timer begins
		    	System.out.println(world.revolutions + " revolutions starting");

		   	
	         while (gameStarted = false) {	   
	            Scanner kb = new Scanner (System.in);
	            	String endCmd = kb.nextLine();
	            	
	            	if (endCmd.equals("quit") || endCmd.equals("q")) {
	            		System.out.println("exit inputted, quitting..");
	            		Window.dispose();
	            		gameStarted = false;
	            		break;
	            	}
	            	else if (endCmd.equals("rate")) {
	            		System.out.println("Change cell rate to..?");
	            		Refer.newRate = kb.nextInt();
	            		Game.refresh = newRate+Refer.newRate;
	            		System.out.println("The new refresh rate is: " + Refer.newRate + ", " + Game.refresh);
	
	            		gameStarted = true;	
	            	}
	            	else {
	            		System.out.println("error, quitting game");
	            		Window.dispose();
	            		gameStarted = false;
	            		break;
	            	}
	            	
	         }
	       }
	    public static void gameRestart () {
	    	if (Window.continueGame = true) {
            Scanner kb = new Scanner (System.in);
            	Game.gens = 0;
				System.out.println("How many more generations?");
				Game.gens =kb.nextInt();
				System.out.println("continuing with " + Game.gens + " more generations..");
	
				new MainLoop().continueStart(); System.out.println("started loop - mainloop");
				Window.continueGame = false; //ending loop
				gameStarted = true;
				Window.random = false;
			}
		}
	    
	    
	    public void continueStart() {
	    	System.out.println("continueStart - mainloop");
	    	
	         maxRev = false;
             endTimer = false;

		    	System.out.println(world.revolutions + " revolutions begin - mainloop");
	    	
	    }
	    
	    public static void gameInitiate () {
	    		    
	    	if (Window.continueGame = true) {
	            Scanner kb = new Scanner (System.in);
				Game.gens = 0; //reset total generations
					System.out.println(Game.gens + " game gens - gameInitiate,");

				System.out.println("How many generations? (do smaller amounts e.g 10-80 for refresh-rates over 300");
	                Game.gens = kb.nextInt();   
	                
	 				System.out.println("continuing with " + Game.gens + " more generations..");
					new MainLoop().initiateStart(); 
					//Window.create(); System.out.println("window create - gameinitiate");
					Window.continueGame = false; //ending loop
					gameStarted = true;
					Window.random = false;
	    	}
	    }
	    	public void initiateStart() { //for starting a new game from option "new"
		    	System.out.println("initiateStart - mainloop");
		    	
		         maxRev = false;
	             endTimer = false;
	             System.out.println(world.revolutions + " revolutions begin - mainloop");
	    	}
	
	    
	    @Override //overide for actionlistener
	    public void actionPerformed(ActionEvent e){
	        Window.updateBoard(world);
	        this.world.update();
          
	    		}
	    
	}
