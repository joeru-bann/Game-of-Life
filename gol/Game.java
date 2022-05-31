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
    public static int refresh_rate = Game.refresh;
	//reading input from the user
    Scanner keyin = new Scanner(System.in);
    String modeChoice;
    boolean gameModePicked=false;
    boolean random;
    boolean rateChosen = false;
    boolean fileBoard;
    int choice = 0;
    static int refresh;

    //variables begin/set up gamne
    public Game()
    {
        System.out.println("Heeeelloo!.. today, I introduce to you Joel's Game of Life! \n"
            + "How it goes: \n"
        	+"You can choose randomized, which spawns a random map of cells; either dead or alive, \n"
            +"Board 1/2/3, all of which are pre-made boards created for you that look pretty cool \n"
        	+"Potentially, you can also load-up your own map of 1's and 0's. This will turn into a board of cells \n" );

        //Game's rules explanation
        System.out.println("Now, what do you feel like playing? \n"
            +"Randomized (type random),\n"
            +" Board 1 (type #1),\n"
            +" Board 2 (type #2),\n"
            +" Board 3 (type #3),\n"
            +" or choose your own map from file? (type File) \n" 
            +"If you want to stop the game at any time, type stop");

        while(!gameModePicked){
            modeChoice=keyin.nextLine().toLowerCase();
            if(modeChoice.equals("random")){
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
            case 1: //random gen
            	System.out.println("choose a cell refresh-rate between (10-1000)");
            	int rate = keyin.nextInt();
            
            	while (!rateChosen)
            		
            	
            		if (rate<10 || rate>1000) {
            			System.out.println("please choose a valid number");
            			rateChosen = false;	
            			}
            		
            		else if (rate>10 && rate<1000) {
            			refresh = rate;
                		
                		Window.create();
    		               new MainLoop().start();
    		               rateChosen = true;
    		               
            		} else {
            		System.out.println("please choose a valid number");
            		rateChosen = false;
            	}
            		
            	
           
           
            
 
            		
                break;

            case 5: //if using pre-made file
                System.out.println("please enter the exact name of your custom file (incl .txt - must be in game directory)");
                File customFile=new File (keyin.nextLine());
                try{
                    try (Scanner fileRead = new Scanner(customFile)) {
						while(fileRead.hasNextLine()){
						    //reading the 0's and 1's of custom file
						    int num;
						    num = fileRead.nextInt();

						    if (num == 1){
						        System.out.println("*");
						    }else if (num == 0){
						        System.out.println(".");
						    }else{
						        System.out.println("error");
						    }

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
