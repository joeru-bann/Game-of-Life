package gol;
import java.awt.Graphics;
import javax.swing.JPanel;
import gol.World;
/**
 * Board functionality
 *
 * Joel Bannister
 * 23/05/22
 */


public class Board extends JPanel
{
    private static final long serialVerisonUID = 1L;
    
    private World renderWorld;
    public void update(World renderWorld) {
        this.renderWorld = renderWorld;
    }
    
    
    @Override 
    protected void paintComponent(Graphics g){//adding grpahic painting for game visuals
        super.paintComponent(g);
        
            if(renderWorld != null)
                this.renderWorld.draw(g);
                
                this.repaint();
    }
    

} 
