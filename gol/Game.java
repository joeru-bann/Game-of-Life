package gol; 
/**
 * @author (Joel Bannister)
 * @version (10.5.22)
 */
import java.util.Scanner;
import java.io.File;
import java.util.Random;
import java.io.IOException;

public class Game
{
    //reading input from the user
    Scanner keyin = new Scanner(System.in);
    String modeChoice;
    boolean gameModePicked=false;
    boolean random;
    boolean board1;
    boolean board2;
    boolean board3;
    boolean fileBoard;
    int choice = 0;

    //variables begin/set up gamne
    public Game()
    {
        System.out.println("Heeeelloo!.. today, I introduce to you Joel's Game of Life! \n"
            + "This is the run down:\n");

        //Game's rules explanation
        System.out.println("Now, what do you feel like playing? \n"
            +"Randomized (type random),"
            +" Board 1 (type #1),"
            +" Board 2 (type #2),"
            +" Board 3 (type #3),"
            +" or choose your own map from file? (type File)");

        while(!gameModePicked){
            modeChoice=keyin.nextLine().toLowerCase();
            if(modeChoice.equals("random")){
                gameModePicked = true;
                random = true;
                choice = 1;

            }else if (modeChoice.equals("#1")){
                gameModePicked = true;
                board1 = true;
                choice = 2;
            }
            else if (modeChoice.equals("#2")){
                gameModePicked = true;
                board2 = true;
                choice = 3;
            }
            else if (modeChoice.equals("#3")){
                gameModePicked = true;
                board3 = true;
                choice = 4;
            }
            else if (modeChoice.equals("file")){
                gameModePicked = true;
                fileBoard = true;
                choice = 5;
            }
            else {
                gameModePicked = false;
                System.out.println("Please choose a valid option from above");
                choice = 0;
            }

            if(gameModePicked){
                new Board();

            }
        }

        switch (choice){
            case 1: //random gen
                while(choice == 1) {
                	new Main();
                }
                break;

            case 5: //if using pre-made file
                System.out.println("please enter the exact name of your custom file (incl .txt - must be in game directory)");
                File customFile=new File (keyin.nextLine());
                try{
                    Scanner fileRead = new Scanner(customFile);
                    while(fileRead.hasNextLine()){
                        //reading the 0's and 1's of custom file
                        int num;
                        num = fileRead.nextInt();

                        if (num == 1){
                            System.out.println("*");
                        }else if (num == 1){
                            System.out.println(".");
                        }else{
                            System.out.println("placeholder");
                        }

                    }
                }
                catch(IOException e) {
                    //in case anything goes wrong
                    e.printStackTrace();
                }
                break;

            case 2: //premade board #1

        }

    }                       
}                                
