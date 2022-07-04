package gol;

/**
 * styling of cell
 * @Joel
 * @25/05/22
 */
import java.awt.Color;

public enum Cell {
    
    DEAD(Color.black),
    ALIVE(Color.red);
    
    private Color color;
    
    Cell(Color color) {
        this.color = color;	
    }
    
    public Color getColor() {
        return color;
    }
}

