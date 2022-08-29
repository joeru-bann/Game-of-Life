package jolgol;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.IOException;

public class Intro {
    
    String intro;
    String c;
    boolean introChosen = false;
    String plainAnswer; //original user input
    String answer; //processed user input (excludes numbers)
    boolean chosen = false; //yes/no to music
    String continueC;
    Scanner keyin = new Scanner(System.in); // reading inout from the user
    
    public static void clearScreen() {   //flushing terminal (clearing)
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }
    
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
                TimeUnit.MILLISECONDS.sleep(13);
              }
              catch (Exception e) {}
        }
    }
    public static void main(String[] args) {
            new Intro();
    }
    
    public void musicChoice(String music) { //giving option to turn music on/off    
        while (!chosen) {
        try { 
           if (music.equals("y") || (music.equals("yes"))){
                String[] arr = {"Street Party", "Lost Woods", "Greenpath"}; //available song names
                Random rand = new Random();
                int select = rand.nextInt(arr.length); //randmly choosing a song
                String noWav = arr[select] + ".wav"; //adding .wav to recognize file in directory
                String filepath = noWav;
            
                Sound musicObject = new Sound();
                musicObject.playMusic(filepath);
                    
                System.out.println("now playing: " + arr[select]);
                sleep(200);
                chosen = true;
                
        } else if (music.equals("n") || (music.equals("no"))) {
                chosen = true;
            } else {
                timedPrint("choose a valid option \n");
                chooseMusic();
               } 
       
        } catch (InputMismatchException e) {
            timedPrint("error: please enter a valid option");
            chosen = false;
      }
    }
  }

    
    private void chooseMusic() {
            clearScreen();
            timedPrint("do you want music? enter y/n");
             plainAnswer = keyin.nextLine().toLowerCase();
            answer = plainAnswer.replaceAll("[0123456789]", ""); //removing any numbers from the string
             musicChoice(answer);
        }

    public Intro() {
    
    chooseMusic();

     while (chosen = true && (!introChosen)){
         System.out.println("read introduction? or skip:  type r/read, or s/skip");
         
         intro = keyin.nextLine().toLowerCase(); 

         if(intro.equals("read") || (intro.equals("r"))) {
             introChosen = true;
             timedPrint("Heeeelloo!.. Today, I introduce to you Joel's Game of Life! \n" 
                 + "How it goes: \n"
                 +"Pressing \"R\": will spawn a random map of cells; either dead or alive, \n"
                 +"Change the speed and how many generations (how many times the cells will change)");
          
                System.out.println("\npress \"c\" to continue");
                continueC = keyin.nextLine().toLowerCase();
                c = continueC.replaceAll("[0123456789]", "");
             try{
                 if(c.equals("c")) {
                     new Frame();
                 }
                 else{
                    System.out.println("c is the only way forward. Please press \"c\" on keyboard");
                    keyin.next();  
                    }
                
                }   catch (InputMismatchException e) {
                System.out.println("invalid response");
                keyin.next();            
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
 
