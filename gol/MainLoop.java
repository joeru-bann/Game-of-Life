package gol;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import gol.Window;
import gol.World;
import gol.Refer;
/**
 * Looping updates with delayed timer
 *
 * @Joel Bannister
 * @26/05/22
 */
public class MainLoop implements ActionListener
{
    private World world;
    private Timer timer;
    public MainLoop()
    {
        this.world = new World();
        this.timer = new Timer(Refer.TIMER_DELAY, this);
    }

    public void start(){
        this.timer.start(); //timer begins
    }
    
    @Override //overide for actionlistener
    public void actionPerformed(ActionEvent e){
        Window.updateBoard(world);
        this.world.update();
    }
}
