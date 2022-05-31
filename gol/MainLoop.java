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
    
    public MainLoop(){
        this.world = new World();
        this.timer = new Timer(Refer.refresh_rate, this);
        
        }
    

    public void start(){
        this.timer.start(); //timer begins
         gameStarted = true;
         while (gameStarted) {
            Scanner kb = new Scanner (System.in);
            	String endCmd = kb.nextLine();
            	if (endCmd == "quit") {
            		System.out.print("quit test");
            	}
            	else {
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
