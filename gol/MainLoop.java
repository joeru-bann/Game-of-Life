package gol;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import gol.Game;
import java.awt.Dimension;

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
    boolean gameStarted;
    public MainLoop()
    {
        this.world = new World();
        this.timer = new Timer(Refer.refresh_rate, this);
        }
    

    public void start(){
        this.timer.start(); //timer begins
        gameStarted = true;
    }
    
    @Override //overide for actionlistener
    public void actionPerformed(ActionEvent e){
        Window.updateBoard(world);
        this.world.update();
          
    		}
    
}
