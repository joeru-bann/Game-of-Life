package gol;

import java.awt.Dimension;
import javax.swing.JFrame;
import gol.Refer;
import gol.World;
public class Window {

    private static JFrame window;
    private static Board board;

    public static void create() {
        window = new JFrame("Joel's Game of Life");
        window.setBounds(10, 10, 0, 0);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        board = new Board();
        board.setPreferredSize(new Dimension(Refer.world_width * Refer.cell_size, Refer.world_height * Refer.cell_size));
        //board.setFocusable(true);
        window.setContentPane(board);
        window.pack();
        window.setVisible(true);
    }

    public static void updateBoard(World renderWorld) {
        board.update(renderWorld);
    }
}