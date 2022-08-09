package jolgol;


import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class golIntro {
	
    String modeChoice;
    String intro;
    boolean introChosen = false;
    boolean gameModePicked = false;
    boolean fileBoard = false;
    int choice = 0;

    
   
    public static void sleep(int time) { // function to help reading process be smoother experience by pausing
        try {
            Thread.sleep(time);
        } catch (Exception e) {}
    }
    
    public static void timedPrint(String output) { //for aesthetics, allows player to read line by line easily instead of all at once
        for (int i = 0; i<output.length(); i++) {
        	char c = output.charAt(i);
            System.out.print(c);
            try {
                TimeUnit.MILLISECONDS.sleep(20);
              }
              catch (Exception e) {}
        }
    }
    
    
	public void Intro() {
		
	   Scanner keyin = new Scanner(System.in); // reading inout from the user

	 System.out.println("read introduction? or skip:  type r/read, or s/skip");
     while (!introChosen){
         intro = keyin.nextLine().toLowerCase(); 
         keyin.close(); //preventing input leak
         
         if(intro.equals("read") || (intro.equals("r"))) {
             introChosen = true;
             timedPrint("Heeeelloo!.. today, I introduce to you Joel's Game of Life! \n" 
                 + "How it goes: \n"
                 +"You can choose randomized, which spawns a random map of cells; either dead or alive, \n"
                 +"Board 1/2/3, all of which are pre-made boards created for you that look pretty cool \n"
                 +"Potentially, you can also load-up your own map of 1's and 0's. This will turn into a board of cells \n" );
            sleep(3000);
            timedPrint("What do you feel like playing? \n");
            sleep(200);
            timedPrint("Randomized type \"r\" or \"random\",\n");
            sleep(200);
            timedPrint("Your own map seed? type \"File\" \n");
         } 
         
         else if (intro.equals("skip") || (intro.equals("s"))) {
             introChosen = true;
         
             timedPrint("What do you feel like playing? \n");
             sleep(200);
             timedPrint("Randomized: type \"r\" or \"random\"\n");
             sleep(200);
             timedPrint("Your own map seed: type \"File\" \n");
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
         }
         else if (modeChoice.equals("file") || (modeChoice.equals("f"))){ 
             gameModePicked = true;
             fileBoard = true;
             choice = 2;
         }
         else {
             System.out.println("Please choose a valid option from above");
         }

     }
     switch (choice){
         case 1: //random generation
         	
         System.out.println("what alive-cell colour do you want?");
         String aColour = keyin.nextLine().toLowerCase();
         System.out.println("what dead-cell colour do you want?");
         String dColour = keyin.nextLine().toLowerCase();
         
              new golFrame();
         }
 }
}
