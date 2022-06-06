package gol; 
/**
 * @author (Joel Bannister)
 * @version (10.5.22)
 */
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Game
{
    //reading input from the user
    Scanner keyin = new Scanner(System.in);
    String modeChoice;
    boolean gameModePicked=false;
    boolean random;
    boolean rateChosen;
    boolean fileBoard;
    int choice = 0;
        static int refresh;
        private static int refresh_rate;
        public static int newRate = Game.refresh;
    //variables begin/set up gamne
    public Game()
    {
        System.out.println("Heeeelloo!.. today, I introduce to you Joel's Game of Life! \n"
            + "How it goes: \n"
            +"You can choose randomized, which spawns a random map of cells; either dead or alive, \n"
            +"Board 1/2/3, all of which are pre-made boards created for you that look pretty cool \n"
            +"Potentially, you can also load-up your own map of 1's and 0's. This will turn into a board of cells \n" );

        System.out.println("Now, what do you feel like playing? \n"
            +"Randomized (type (r), or (random),\n"
            +" Board 1, Board 2, Board 3 (type either #1, #2, #3, respectively),\n"
            +"choose your own map from file? (type File) \n" 
            +"If you want to stop the game at any time, type quit");

        while(!gameModePicked){
            modeChoice=keyin.nextLine().toLowerCase();
            if(modeChoice.equals("random") || (modeChoice.equals("r"))){
                gameModePicked = true;
                random = true;
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

            if(gameModePicked){
                new Board();

            }
        }

        switch (choice){
            case 1: //random generation
                System.out.println("choose a cell refresh-rate from (10-1000), e.g : 10 is extremely fast, 1000 is really slow");
                MainLoop.newRate = Game.refresh_rate;
            
                while (!rateChosen) {
                    int rate = keyin.nextInt();

                    if (rate>10 && rate<1000) { //keeping the rate within reasonable limits
                        System.out.println("Your refresh rate is: " + rate
                                + "\n You can change this by typing rate \n");
                                refresh = rate;
                        
                        Window.create();
                           new MainLoop().start(); // if the rate is within limits, start game
                        rateChosen = true;  
                        }
                     else {
                    System.out.println("please choose a valid number");
                }
            }
                break;
                
                
            case 5: //if using pre-made file
                System.out.println("please enter the exact name of your custom file (incl .txt - must be in game directory)");
                File customFile=new File (keyin.nextLine());
                try{
                    try (Scanner fileRead = new Scanner(customFile)) {
                        while(fileRead.hasNextLine()){
                            //reading the 0's and 1's of custom file
                            custom[][] = new [Refer.customWorld_width][Refer.customWorld_height];
                            long num;
                            num = fileRead.nextLong();
                            int cols;
                            int rows;

                            return custom;
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
