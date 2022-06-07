package gol;

import java.awt.Dimension;
import javax.swing.JFrame;
public class Window {

    private static JFrame window;
    private static Board board;

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
		
		
	}
}
