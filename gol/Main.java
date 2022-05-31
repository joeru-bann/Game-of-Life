package gol;
import java.util.Scanner;

import javax.swing.JFrame;
import java.awt.Dimension;


/**
 * @Joel
 * @26/05/22
 */
public class Main {
	boolean end = false;
	 private static JFrame window;
		    	
		    	
    public static void main(String[] args) {
		 {
			new Game(); //immediately goes to Game class to start
		        
		       Scanner keyin = new Scanner (System.in);
               String halt = keyin.nextLine().toLowerCase(); //automatically makes lower case for ease
		       if (halt == "stop") {
		    	   window.dispose();
		    	   window.setVisible(false);
		       }
		       
		        			        		
		      }	
    	}
}
    	 	
         
