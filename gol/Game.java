package gol; 

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
    public static int refresh_rate;
    public static final int rate = refresh_rate;
//    String rate;
	public static int gens = 0;
    public static int rows = 0;
    public static int cols = 0;
    //reading input from the user
    Scanner keyin = new Scanner(System.in);
    String intro;
    boolean introChosen = false;
    String modeChoice;
    boolean gameModePicked = false;
    static String cellChange;
    boolean random;
    boolean rateChosen;
    boolean fileBoard;
    int choice = 0;
    static String num;
    boolean cellSelect = false;
    
    //variables begin/set up gamne

    public static void sleep(int time) { // function to help reading process be smoother experience by pausing
        try {
            Thread.sleep(time);
        } catch (Exception e) {}
    }
    
    void charPrint() {
    	
    	
    }

    public Game() 
    {
    	
        System.out.println("read introduction? or skip:  type r/read, or s/skip");
        while (!introChosen){
            intro = keyin.nextLine().toLowerCase(); 
            if(intro.equals("read") || (intro.equals("r"))) {
                introChosen = true;
                System.out.println("Heeeelloo!.. today, I introduce to you Joel's Game of Life! \n" 
                    + "How it goes: \n"
                    +"You can choose randomized, which spawns a random map of cells; either dead or alive, \n"
                    +"Board 1/2/3, all of which are pre-made boards created for you that look pretty cool \n"
                    +"Potentially, you can also load-up your own map of 1's and 0's. This will turn into a board of cells \n" );
               sleep(3000);
               
               System.out.println("What do you feel like playing? \n");
               sleep(500);
               System.out.println("Randomized type r/random,\n");
               sleep(300);
               System.out.println("Board 1, Board 2, Board 3 (type either #1, #2, #3, respectively),\n");
               sleep(300);
               System.out.println("choose your own map from file? (type File) \n");
               sleep(300);
               System.out.println("If you want to stop the game at any time, type quit");
            } 
            
            else if (intro.equals("skip") || (intro.equals("s"))) {
                introChosen = true;
                
                System.out.println("What do you feel like playing? \n");
                sleep(500);
                System.out.println("Randomized type r/random,\n");
                sleep(300);
                System.out.println("Board 1, Board 2, Board 3 (type either #1, #2, #3, respectively),\n");
                sleep(300);
                System.out.println("choose your own map from file? (type File) \n");
                sleep(300);
                System.out.println("If you want to stop the game at any time, type quit"); 
            }
            else {
                System.out.print("please choose an intro option..");
                introChosen = false;
            }
        }

        while(!gameModePicked){
          

            modeChoice=keyin.nextLine().toLowerCase(); 
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
            else if (modeChoice.equals("file") || (modeChoice.equals("f"))){ 
                gameModePicked = true;
                fileBoard = true;
                choice = 5;
            }
            else {
                gameModePicked = false;
                System.out.println("Please choose a valid option from above");
                //choice = 0;
            }

        }
        switch (choice){
            case 1: //random generation
            	
            System.out.println("what alive-cell colour do you want?");
            String aColour = keyin.nextLine().toLowerCase();
            System.out.println("what dead-cell colour do you want?");
            String dColour = keyin.nextLine().toLowerCase();
            
            System.out.println("choose a cell refresh-rate from (10-1000), e.g : 10 is extremely fast, 1000 is really slow");
            
            String sRate = keyin.nextLine();
            int rate = Integer.parseInt(sRate);
            double rateValue;
            boolean validValue = false;
            
            while (!rateChosen) {
            		refresh_rate = rate;
            		
                
                if (rate>=10 && rate<=1000) { //keeping the rate within reasonable limits
                    System.out.println("Your refresh rate is: " + Refer.newRate
                        + "\n You can change this by typing rate \n");
                    sleep(300); 
                    
                    cols = 200;
                    rows = 200;   

                    System.out.println("created window - game");
                    gens = 1;
                    Window.create();//creates new board
                    new MainLoop().cellPause(); // if the rate is within limits, start game
                    validValue = true;
                    break;

                } 
            	
                else {
                    System.out.println("please choose a valid number");
                    validValue = false;
                    keyin.next();
                }             
            }
            

            case 5: //if using pre-made file of 1's and 0's
            while (choice == 5) {
                System.out.println("please enter the exact name of your custom file (incl .csv- must be in game directory)");

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
                rate = keyin.nextInt();

                try  {
                    System.out.println("Printing board...");

                    Scanner fileRead = new Scanner(customFile);
                    while(fileRead.hasNextLine()){
                        // System.out.println(fileRead.nextLine()); : troubleshooting

                        new Window();
                        sleep(2000);
                        new MainLoop().start();
                    }
                }
                catch(IOException e) {
                    //in case anything goes wrong
                    e.printStackTrace();
                    System.out.println("An error occured while trying to fetch the file name entered");
                }
                break;
            }

            case 2: //premade board #1
        }
    }
}
