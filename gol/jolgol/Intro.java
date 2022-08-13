package jolgol;


import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Intro {
	
	//private static final long serialVersionUID = 1L;

	
    String modeChoice;
    String intro;
    String c;
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
                TimeUnit.MILLISECONDS.sleep(10);
              }
              catch (Exception e) {}
        }
    }
    
    public static void main(String[] args) {
		
		String[] arr = {"Street Party", "Tropical Carnival", "Lost Woods"};
        Random rand = new Random();

        // randomly selects an index from the arr
        int select = rand.nextInt(arr.length); 
    	String noWav = arr[select] + ".wav";
    	
    	String filepath = noWav;
    		
    	System.out.println("now playing: " + arr[select]);
			Sound musicObject = new Sound();
			musicObject.playMusic(filepath);
		
			new Intro();

	}
    
	public Intro() {
		
		   Scanner keyin = new Scanner(System.in); // reading inout from the user
	 System.out.println("read introduction? or skip:  type r/read, or s/skip");

     while (!introChosen){
         intro = keyin.nextLine().toLowerCase(); 

         if(intro.equals("read") || (intro.equals("r"))) {
             introChosen = true;
             timedPrint("Heeeelloo!.. today, I introduce to you Joel's Game of Life! \n" 
                 + "How it goes: \n"
                 +"You can choose randomized, which spawns a random map of cells; either dead or alive, \n"
                 +"You can also load-up your own map of 1's and 0's. This will turn into a board of cells \n" );
          
             System.out.println("press \"c\" to continue");
             c = keyin.nextLine().toLowerCase();
             	if(c.equals("c")) {
             		new Frame();
             	}
         } 
         
         else if (intro.equals("skip") || (intro.equals("s"))) {
             introChosen = true;
             
             new Frame();
         }
         else {
             System.out.print("please choose an intro option..");
         	}
      }   
	}
 }

