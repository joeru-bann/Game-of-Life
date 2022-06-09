package gol; 
import gol.World;

/**
 * @author (Joel Bannister)
 * @version (10.5.22)
 * main intro/game picker options
 */
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;  

public class Game
{
    public static int gens = 0;
	public static int rows = 0;
    public static int cols = 0;
    //reading input from the user
    Scanner keyin = new Scanner(System.in);
    String modeChoice;
    boolean gameModePicked=false;
    boolean random;
    boolean rateChosen;
    boolean fileBoard;
    int choice = 0;
    static String num;
    static int refresh;
    private static int refresh_rate;
    public static int newRate = Game.refresh;
    //variables begin/set up gamne
    
    
    public static void sleep(int time) { // function to help reading process be smoother experience by pausing
    	try {
    		Thread.sleep(time);
    		} catch (Exception e) {}
    }
    public Game() 
    {
        System.out.println("Heeeelloo!.. today, I introduce to you Joel's Game of Life! \n" 
            + "How it goes: \n"
            +"You can choose randomized, which spawns a random map of cells; either dead or alive, \n"
            +"Board 1/2/3, all of which are pre-made boards created for you that look pretty cool \n"
            +"Potentially, you can also load-up your own map of 1's and 0's. This will turn into a board of cells \n" );

        System.out.println("Now, what do you feel like playing? \n");
        sleep(500);
        System.out.println("Randomized (type (r), or (random),\n");
        sleep(300);
        System.out.println("Board 1, Board 2, Board 3 (type either #1, #2, #3, respectively),\n");
        sleep(300);
        System.out.println("choose your own map from file? (type File) \n");
        sleep(300);
        System.out.println("If you want to stop the game at any time, type quit");

        while(!gameModePicked){
            modeChoice=keyin.nextLine().toLowerCase(); //lower case allows input to be non case-sensitive
            if(modeChoice.equals("random") || (modeChoice.equals("r"))){
                gameModePicked = true;
                choice = 1;

            }else if (modeChoice.equals("#1")){
                gameModePicked = true;
                choice = 2;
            }
            else if (modeChoice.equals("#2")){
                gameModePicked = true;
                choice = 3;
            }
            else if (modeChoice.equals("#3")){
                gameModePicked = true;
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

           
        }
       
        switch (choice){
            case 1: //random generation
                System.out.println("choose a cell refresh-rate from (10-1000), e.g : 10 is extremely fast, 1000 is really slow");
                MainLoop.newRate = Game.refresh_rate;

                while (!rateChosen) {
                    int rate = keyin.nextInt();

                    if (rate>=10 && rate<=1000) { //keeping the rate within reasonable limits
                        System.out.println("Your refresh rate is: " + rate
                            + "\n You can change this by typing rate \n");
                        refresh = rate;

                        cols = 300; //setting default window size
                        rows = 160;
                        
                		sleep(300); 

                       try { System.out.println("How many generations do you want? (do smaller amounts e.g 10-80 for refresh-rates over 300");
                        gens = keyin.nextInt();
                       } catch (InputMismatchException ex) {  
                           System.out.println("please choose a valid number");
                           break;
                        }  
                       
                       Window.create(); System.out.println("created window - game");//creates new board
            
                       new MainLoop().start(); // if the rate is within limits, start game
                       
                       rateChosen = true;  
                        random = true;
                    } 

                    else {
                        System.out.println("please choose a valid number");
                    }
                    
                } 
                break;

            case 5: //if using pre-made file
                System.out.println("please enter the exact name of your custom file (incl .txt - must be in game directory)");
               
                String fileName = keyin.nextLine();
                File customFile = new File (fileName);
                
        		sleep(600);

                System.out.println("how many rows is your custom grid?");
                rows = keyin.nextInt();
        		sleep(600);

                System.out.println("how many columns is your custom grid?");
                cols = keyin.nextInt();
        		sleep(600);

                
                System.out.println("choose a cell refresh-rate from (10-1000), e.g : 10 is extremely fast, 1000 is really slow");
                int rate = keyin.nextInt();

                try  {
                    System.out.println("Printing board...");

                	Scanner fileRead = new Scanner(customFile);
                    while(fileRead.hasNextLine()){
                       // System.out.println(fileRead.nextLine()); : troubleshooting
                        
                        Window.create();
                        new MainLoop().start();
                    }

                }

                catch(IOException e) {
                    //in case anything goes wrong
                    e.printStackTrace();
                    System.out.println("An error occured while trying to fetch the file name entered");
                }
                break;

            case 2: //premade board #1

        }

    }
}
