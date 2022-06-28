package gol;
import gol.utils.MathHelper;
import java.awt.Graphics;
import gol.World;
/**
 * Fundamental rules for grid checking location + neighbours
 * Joel Bannister
 * 16.05.22
 */

public class World 
{
	int revolutions;
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

    public World()  {
    	if (Window.random = true) {
    		revolutions = 0;
        this.grid = new Cell[Game.cols][Game.rows];
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[i].length;j++){
                this.grid[i][j] = MathHelper.randomBoolean() ? Cell.ALIVE : Cell.DEAD; 
              }//makes a random grid of either alive, or dead cells
            }
        
        }
    }

    public void update() {
        int countAlive;
        
        Cell[][] worldCopy = new Cell[Game.cols][Game.rows];

        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[i].length;j++){

                countAlive = this.getNeighborCells(i, j);
                //rules for deciding whether or not cell lives or dies (based off original rules)

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
        ++revolutions;
        
        if (Game.gens != 2) { //to not print out first pause generations 
	    System.out.println("total revolutions: " + revolutions);
        }
        
	    if (revolutions >= Game.gens) { //stopping game when loop reaches specified amount of generations
	    	finalRevs = Game.gens;
	    	
	        Window.random = false;
	    	revolutions = finalRevs; 
	    	maxRev = true;
	    	MainLoop.endTimer = true;
	    	MainLoop.gameStarted = false;
    		Window.pause();
    		revolutions = 0;
    		System.out.println(" \n");
        	 } 

	    else if (revolutions <= Game.gens) {
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
		public void cellChange() {	
			int Col = MainLoop.chooseCol;
			int Row = MainLoop.chooseRow;
			
				System.out.println("chosen cell true : World : cellChange");
				if(grid[Col][Row] == Cell.ALIVE) {
					(grid[Col][Row]) = Cell.DEAD;
				}	
				else if (grid[Col][Row] == Cell.DEAD) {
					System.out.println("grid x,y = celldead : World : cellChange");
					(grid[Col][Row]) = Cell.ALIVE;
				}
				System.out.println("starting edited game");
				System.out.println();

				 
			}
		}

