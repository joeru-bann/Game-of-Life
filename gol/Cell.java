package gol;

/**
 * styling of cell
 * @Joel
 * @25/05/22
 */
import java.awt.Color;

public enum Cell {
    
    DEAD(Color.GRAY),
    ALIVE(Color.RED);
    
    private Color colour;
    
    Cell(Color color) {
        this.colour = colour;
    }
    
    public Color getColour() {
        return colour;
    }
}

