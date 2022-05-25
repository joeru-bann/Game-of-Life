 

/**
 * Board functionality
 *
 * Joel Bannister
 * 23/05/22
 */

import java.util.Random;

public class board
{
    public static void main(String[] args)
    {
        
        int row=10;
        int col= 15;

        int[][] mygrid = new int[row][col];

        // place random numbers into the array 'mygrid'
        for (int i=0; i<row; i++){
            for (int j=0; j<col; j++){
                //mygrid[i][j] = rand() % 2;
                Random rand = new Random(); 
                int int_random = rand.nextInt(2);
                // print the values out, just for debugging 
                System.out.print(int_random);
                mygrid[i][j] = int_random ;
            }
        }

        // ++++++++++++++++++++++++++++++++++++++++++++++++++
        // Displaying the grid  

        System.out.println();
        System.out.println("Original generation");
        for (int i=0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                if (mygrid[i][j] == 0)
                    System.out.print(".");
                else
                    System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();
        //nextGeneration(mygrid, row, col);

    }  

} 
