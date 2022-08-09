package gol;
import gol.utils.MathHelper;

import java.awt.Color;
import java.awt.Graphics;
import gol.World;
/**
 * Fundamental rules for grid checking location + neighbours
 * Joel Bannister
 * 16.05.22
 */

public class World 
{
	int generations;
    boolean update;
    static int finalRevs;;
    int x;
    int y;
    static boolean maxRev = false;

    private Cell[][] grid;

    public void draw(Graphics graphics){
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                graphics.setColor(grid[i][j].getColor()); //colouring the cells
                graphics.fillRect(i * Refer.cell_size, j * Refer.cell_size, Refer.cell_size, Refer.cell_size);
            }
        }
    }

    public World()  {  //makes a random grid of either alive, or dead cells
    	if (Window.random = true) {
    		generations = 0;
    		System.out.println("window random true : world");
        this.grid = new Cell[Game.cols][Game.rows];
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[i].length;j++){
                this.grid[i][j] = MathHelper.randomBoolean() ? Cell.ALIVE : Cell.DEAD; 
              }
            }
        
        }
    }

    public void update() {
        int countAlive;

        Cell[][] worldCopy = new Cell[Game.cols][Game.rows];

        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[i].length;j++){

                countAlive = this.getNeighborCells(i, j);
                //rules for deciding whether or not cell lives or dies (based off original conway gol rules)

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
        ++generations;
        
        if (Game.gens != 2) { //to not print out first pause generations 
        	
        System.out.print('\u000C');
	    System.out.println("generations: " + generations);
	   
        }
        
	    if (generations >= Game.gens) { //stopping game when loop reaches specified amount of generations
	    	finalRevs = Game.gens;
    		Window.pause();
    		generations = 0;
    		System.out.println(" \n");
        	 } 

	    else if (generations <= Game.gens) {
	    	maxRev = false;
	    }

}
    private int getNeighborCells(int x, int y) {    //counting amount of alive cell neighbours

        int countAlive = 0; 
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                try{
                    if (grid [i][j] == Cell.ALIVE)
                        countAlive ++;
                } catch(ArrayIndexOutOfBoundsException e){
                    continue; //in case it counts anything out of bounds
                }

            } 
        }
        //subtract 1 to account for the cell itself (doesn't count as a neighbour)
        if(grid[x][y] == Cell.ALIVE) {
            return countAlive - 1;}
        else {
            return countAlive;
    }
 
   }
		public void cellChange() {	 //changing the state of a specific alive/dead cell 
//			int Col = MainLoop.chooseCol;
//			int Row = MainLoop.chooseRow;
			
			int Col = 1;
			int Row = 1;
			
				if(grid[Col][Row] == Cell.ALIVE) {
					(grid[Col][Row]) = Cell.DEAD;
					System.out.println("cell change from alive to dead");
				}	
				else if (grid[Col][Row] == Cell.DEAD) {
					(grid[Col][Row]) = Cell.ALIVE;

					
					System.out.println("cell change from dead to alive");

				}
				System.out.println();
				 
			}
}
