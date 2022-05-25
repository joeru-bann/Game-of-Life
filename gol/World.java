package gol;

 
import gol.Refer;
/**
 * The base rules for each "cell"
 * Joel Bannister
 * 16.05.22
 */


public class World 
{

    boolean neighbour = false;
    boolean alive = true;
    
    private int WORLDSIZE;
    private final int DEAD = 0;
    private final int ALIVE = 1;
    private int world[][];
    private int worldCopy[][];
    
    private Cell[][] grid;

    public void update() {
        int countAlive;
        Cell[][] gridCopy = new Cell[Refer.world_width][Refer.world_height]; 
        
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                countAlive = this.getNeighborCells(i, j);
            }
        }
    }

    public int getNeighborCells(int x, int y) {

        int aliveCount = 0;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {

                if (world[i][j] == ALIVE && (x != i || y != j))
                    aliveCount += 1;
            }
        }

        return aliveCount;
    }

}

