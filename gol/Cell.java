package gol;

/**
 * styling of cell
 * @Joel
 * @25/05/22
 */
import java.awt.Color;

public enum Cell {
    
    DEAD(Color.BLACK),
    ALIVE(Color.GREEN);
    
    private Color color;
    
    Cell(Color color) {
        this.color = color;
    }
    
    public Color getColor() {
        return color;
    }
}

