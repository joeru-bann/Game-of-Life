package gol;
import gol.utils.MathHelper;
import java.awt.Graphics;
import gol.Refer;
/**
 * Fundamental rules for grid checking location + neighbours
 * Joel Bannister
 * 16.05.22
 */

public class World 
{
    private final int DEAD = 0;
    private final int ALIVE = 1;
    private int world[][];
    private int worldCopy[][];

    private Cell[][] grid;
    public void draw(Graphics graphics){
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                graphics.setColor(grid[i][j].getColour());
                graphics.fillRect(i * Refer.cell_size, j * Refer.cell_size, Refer.cell_size, Refer.cell_size);
            }
        }
    }

    public World()  {
        this.grid = new Cell[Refer.world_width][Refer.world_height];
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[i].length;j++){
                this.grid[i][j] = MathHelper.randomBoolean() ? Cell.ALIVE : Cell.DEAD;
            }
        }
    }

    public void update() {
        int countAlive;
        Cell[][] worldCopy = new Cell[Refer.world_width][Refer.world_height];
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[i].length;j++){
                this.grid[i][j] = MathHelper.randomBoolean() ? Cell.ALIVE : Cell.DEAD;
            }
        }

        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                countAlive = this.getNeighborCells(i, j);
                //rules for deciding whether or not cell lives or dies
                if(grid[i][j]== Cell.ALIVE) {

                    if(countAlive > 3)
                        worldCopy[i][j] = Cell.DEAD;
                    else if (countAlive < 2)
                        worldCopy[i][j] = Cell.DEAD;
                    else
                        worldCopy[i][j] = Cell.ALIVE;
                }
                else {
                    if(countAlive == 3)
                        worldCopy[i][j] = Cell.ALIVE;
                    else
                        worldCopy[i][j] = Cell.DEAD;
                }                  
            }
        }
        this.grid = worldCopy;
    }

    public int getNeighborCells(int x, int y) {
        int countAlive = 0; 
        //counting cells amount of alive neighbours
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                try{
                    if (world[i][j] == ALIVE && (x != i || y != j))
                        countAlive += 1;
                } catch(ArrayIndexOutOfBoundsException e){
                    continue; //in case it counts anything out of bounds
                }

            } 
        }
        //subtract 1 to account for the cell itself (doesn't count as a neighbour)
        if(grid[x][y] == Cell.ALIVE)
            return countAlive - 1;
        else 
            return countAlive;
    }

}

