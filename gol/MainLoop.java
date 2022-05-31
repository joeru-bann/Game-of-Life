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
	    private JFrame window;
	    int cmd = 0;    
	    static int newRate;
	    public MainLoop(){
	        this.world = new World();
	        this.timer = new Timer(Refer.newRate, this);
	        
	        }
	    
	
	    public void start(){
	        this.timer.start(); //timer begins
	         gameStarted = true;
	         while (gameStarted) {
	            Scanner kb = new Scanner (System.in);
	            	String endCmd = kb.nextLine();
		
	            	
	            	if (endCmd.equals("quit")) {
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
	            		System.out.println("error");
	            		System.out.println("quitting...");
	            		Window.dispose();
	            		gameStarted = false;
	            		break;
	            	}
	            	
	           
	            	
	             
	         }
	       }
	    
	    @Override //overide for actionlistener
	    public void actionPerformed(ActionEvent e){
	        Window.updateBoard(world);
	        this.world.update();
	        
	          
	    		}
	    
	}
