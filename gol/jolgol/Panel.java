package jolgol;
/**
* The main fundamentals and interactive features of the game.
*
* @author Joel Bannister
* @version 29/02/22
*/
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;  

//adding mouse input listeners
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

//adding input from the keyBoard
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//saving and getting progress
import java.io.BufferedReader;
import java.io.FileReader;

import java.io.File;

public class Panel extends JPanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener {

    private static final long serialVersionUID = 1L;
    //dimensions of the panel:
    int xPanel = 800;
    int yPanel = 800;

    int size = 7; //size of each "cell"/square

    int xWidth = xPanel/size;  //xPanel/size gives us the number of squares:

    int yHeight = yPanel/size;

    int[][] Board = new int[xWidth][yHeight];         

    int[][] beforeBoard = new int[xWidth][yHeight]; //adding another array - this will be the "future" board

    boolean starts = true; //at the start of the program, all equals true
    boolean paused = false;
    boolean begun = false; //indicated wheteher "B" key has been pressed to begin game
    int totalGens; //amount of generations run through
    int generations; //user-inputted desired amount of gens
    boolean gensComplete; 
    boolean cellsDrawn = false; //to determine whether there are cells for the game to start with
    int aliveCells = 0;

    int initial = -1; //indicates if the mouse is clicked

    Timer time;

    int timeInput = 0;

    //creating a file for the progress and a bufferedWriter to write inside the file
    BufferedWriter writer; 
    FileWriter file;

    Scanner sc;     //scanner to read back in the progress
    Scanner input = new Scanner(System.in);
    File filename = new File("progress.bin");;
    BufferedReader reader;
    FileReader fileReader;
    {
        try {
            file = new FileWriter(filename, true);  // true = append, false = overwrite
            writer = new BufferedWriter(file);
            fileReader = new FileReader(filename);
            reader = new BufferedReader(fileReader);
            sc = new Scanner(fileReader);

        }    catch (FileNotFoundException e) {}
        catch(IOException e){ }
    }   

    //creating the panel:
    public Panel() {
        setSize (xPanel, yPanel);        
        setLayout(null);    

        //mouse movement
        addMouseMotionListener(this);
        addMouseListener(this);

        addKeyListener(this);
        setFocusable(true);

        setBackground(Color.BLACK); //contrasting colour 

        inputTime(input);    //adding game speed):

        if(timeInput == 0 || timeInput <= 0) {
            time = new Timer(80, this);    //default
        }else if(timeInput >= 0){
            time = new Timer(timeInput, this);
        }    
    }

    public int getTimeInput() {
        return timeInput;
    }

    public void setTimeInput(int timeInput) {
        this.timeInput = timeInput;
    }

    //for aesthetics/ease, allows player to read line by line easily instead of all at once
    public static void timedPrint(String output) { 
        for (int i = 0; i<output.length(); i++) {
            char c = output.charAt(i);
            System.out.print(c);
            try {
                TimeUnit.MILLISECONDS.sleep(13);
            }
            catch (Exception e) {}
        }
    }

    public static void sleep(int time) { // function to have smoother experience by pausing for a certain amount of time
        try {
            Thread.sleep(time);
        } catch (Exception e) {}
    }

    //I got this clearScreen method from stackoverflow, and it is not my own piece of code.
    //credit goes to Abhishek Kashyap 
    //https://stackoverflow.com/questions/2979383/how-to-clear-the-console
    public static void clearScreen() {   //flushing terminal (clearing)
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }

    //calling the creation methods for the elements
    public void paintComponent (Graphics g) {
        //we are calling the super to the Graphics class:
        super.paintComponent(g);
        grid(g); 
        display(g);  //calling the methods for the squares:
    }

    
    //creating a grid with lines (gray for ease on eyes)
    private void grid(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        for(int i=0; i<Board.length; i++) { //drawing the grid lines
            g.drawLine(0, i*size, xPanel, i*size);  //row
            g.drawLine(i*size, 0, i*size, yPanel);  //columns
        }
    }

    private void spawn() {  //choosing random squares to be on/alive
        for (int x=0; x<Board.length; x++) {
            for (int y=0; y<(yHeight); y++) {
                if ((int)(Math.random()*5) == 0) { //density of the alive cells, smaller number = more alive cells, 
                    beforeBoard[x][y] = 1;
                    cellAmount((int)(Math.random()*5)); //counting alive cells
                }
            }
        }
    }

    private void cellAmount(int b) {
		if (b == 1) { //b = true cells / "alive cells"
			aliveCells++;
		} else{}
	}

    private void display(Graphics g) {
        g.setColor(Color.GREEN);  //choosing color:

        copyArray();

        for (int x = 0; x < Board.length; x++) {
            for (int y = 0; y < (yHeight); y++) { //yPanel/size gives us the number of squares vertically

                if (Board[x][y] == 1) {  //and if that spot is = 1, we want to display a color filled cell

                    g.fillRect(x * size, y * size, size, size); //coloring the cells
                }
            }
        }
    }

    // we are coping one array to another
    private void copyArray() {
        for (int x = 0; x < Board.length; x++) {
            for (int y = 0; y < (yHeight); y++) { 
                Board[x][y] = beforeBoard[x][y]; //updating current array from previous
            }
        }
    }

    //algorithm:
    //checks every one of the 8 neighours of each cell (x and y are the cell positions)
    //every time it finds a neighbour cell, it increases the total count of alive
    private int check (int x, int y) {
        int alive = 0;

        //we are going down and right
        alive += Board[(x + xWidth -1) % xWidth][(y-1 + yHeight) % yHeight];  //top left
        alive += Board[(x + xWidth) % xWidth][(y-1 + yHeight) % yHeight];

        alive += Board[(x + xWidth +1) % xWidth][(y-1 + yHeight) % yHeight]; 
        alive += Board[(x + xWidth -1) % xWidth][(y + yHeight) % yHeight];

        alive += Board[(x + xWidth +1) % xWidth][(y + yHeight) % yHeight];
        alive += Board[(x + xWidth -1) % xWidth][(y+1 + yHeight) % yHeight];

        alive += Board[(x + xWidth) % xWidth][(y+1 + yHeight) % yHeight];
        alive += Board[(x + xWidth +1) % xWidth][(y+1 + yHeight) % yHeight];
        //counting the number of "neighbours" around the cell

        return alive;
    }

    
    //method for clearing the random input -> set back to 0
    private void clear() {
        for (int x = 0; x < Board.length; x++) {
            for (int y = 0; y < (yHeight); y++) {
                //when it equals 0, it is turned off
                beforeBoard[x][y] = 0;
            }
        }
    }

    public void actionPerformed(ActionEvent e) {    // implementing the rules of conways game of Board
        int alive;
        for (int x = 0; x < Board.length; x++) {
            for (int y = 0; y < (yHeight); y++) {     // yPanel/size gives us the number of squares vertically

                alive = check(x, y);        //when we call the check() method, alive increases every time there is an alive neighbor

                if (alive == 3) {             
                    beforeBoard[x][y] = 1;
                } else if ((alive == 2) && (Board[x][y] == 1)) {
                    beforeBoard[x][y] = 1;
                } else {
                    beforeBoard[x][y] = 0;
                }
            }
        }
        repaint(); // "refreshes" the page 

        totalGens++;
        System.out.println("generations: " + totalGens);
       // sleep(50);
        clearScreen();

        if (totalGens >= generations) { //pausing game when generations complete
            gensComplete = true;
            time.stop();
            paused = true;
            System.out.println("generations complete");

            completed(); //send to method
        }
        else if (totalGens <= generations) {
            gensComplete = false;
        }
    }

            
    public void completed() { //when all the generations have run 
        String endState;
        boolean end = false;
        cellsDrawn = false;

        System.out.println("play until reach end-state? (type y/n)");
       // input.next(); 
        while (!end) {
            endState = input.nextLine(); 

            if (endState.equals("y")) {
                timedPrint("\npress \"B\" to play until end \n");
                endState();
                end = true;
            }
            else if(endState.equals("n")) {
                timedPrint("Thanks for Playing! \n");
                timedPrint("Created by: JB \n");
                timedPrint("Concept: Conways Game of Life \n \n");
                sleep(7000);
                timedPrint("leaving in 3, \n"); 
                sleep(1000);
                timedPrint("leaving in 2, \n");
                sleep(1000);
                timedPrint("leaving in 1, ");
                sleep(1000);
                
                System.exit(0);
                input.close();
            }
            else {
                System.out.print("");
            }

        }         
    }                                    

    public void endState() { //playing until cells repeat
    	
    	while (totalGens >= generations) {
    		if (aliveCells == 0) { //if there are no more alive cells on board
    		System.out.println("No more cells left! Game is over, thanks for playing.");
    		}
    	}
        totalGens = 0;
        paused = true;
        generations = 2147483647;
        spawn();

        for (int x = 0; x < Board.length; x++) {
            for (int y = 0; y < (yHeight); y++) {     // yPanel/size gives us the number of squares vertically
                check(x, y);

            }
        }
    }

    private void saveProgress() {     
        try {
            for (int x = 0; x < Board.length; x++) { 
                for (int y = 0; y < (yHeight); y++) { 
                    writer.write(Board[x][y]); //add to output
                }
                writer.write("\n"); //new line
            }
            writer.close();
            file.close();
            sc.close();
            reader.close();
        }catch (FileNotFoundException e) {}
        catch (IOException e) {
        }
    }

    private void getProgress() {
        try {
            String line = reader.readLine();
            while (line != null) {    
                for (int x = 0; x < Board.length; x++) { // for each row
                    for (int y = 0; y < (yHeight); y++) { // for each column
                        line = reader.readLine();
                        beforeBoard[x][y] = reader.read(); // add to the output
                        System.out.println(beforeBoard[x][y]);
                    }
                }
            }
            writer.close();
            file.close();
            sc.close();
            reader.close();
        } catch (FileNotFoundException e) {}
        catch (IOException e) { }

        repaint();
    }    

    public void inputTime(Scanner input) { //getting the speed of the game from user
        boolean validTime = false;

        System.out.print("Enter game speed time in miliseconds:  "); 
        System.out.println("(20 - 700) recommended range \n");
        while(!validTime) {
            try {
                this.setTimeInput(input.nextInt());
                inputGens(input);
                validTime = true;

            }  catch (InputMismatchException e) {
                System.out.println("Invalid input, please re-enter");
                input.next();            
            }
        }
    }

    public void inputGens(Scanner input) {   //asking player for desired amount of generations
        boolean validGens = false;
        System.out.println("how many generations do you want?"); 

        while(!validGens) {
            try {    
                this.setGens(input.nextInt());
                clearScreen();

                timedPrint("random cells: Press \"R\" \n");
                timedPrint("\"B\" to begin, Spacebar to pause, \n\"L\" to load progress \n");
                timedPrint("draw/erase cells by clicking/dragging \n");
                timedPrint("Press \"1\" on keyboard to advanced a single generation \n");


                timedPrint("press \"Q\" to quit at any time\n");

                timedPrint("click window to focus, and drag to positon it as you wish \n");

                validGens = true;

            }catch (InputMismatchException e) {
                System.out.println("Invalid gens, please re-type");
                input.next();
            }
        }
    }

    public int getGens() {
        return generations;
    }

    public void setGens(int generations) {
        this.generations = generations;
    }

    // following methods detects the actions the mouse performs: dragging, holding, clicking etc.
    public void mouseDragged(MouseEvent e) {
        //we can click on a cell and if it is on, turn it off and vice versa
        int x = e.getX()/size;
        int y = e.getY()/size;

        if(Board[x][y] == 0  &&  initial == 0) {
            beforeBoard[x][y] = 1;
            aliveCells++;

        }
        else if (Board[x][y] == 1  &&  initial == -1) {
            beforeBoard[x][y] = 0;
            aliveCells--;

        }
        cellsDrawn = true;
        repaint(); 

    }

    public void mousePressed(MouseEvent e) {    
        int x = e.getX()/size;
        int y = e.getY()/size;

        if(Board[x][y] == 0) {
            initial = 0;

        }else{
            initial = -1;

        }
        repaint();
        cellsDrawn = true;

    }

    public void mouseReleased(MouseEvent e) {
		System.out.println(aliveCells + " alive cells"); //how many green squares there are
        //initial = -1;
        int x = e.getX()/size;
        int y = e.getY()/size;

        if(Board[x][y] == 0  &&  initial == 0) {
            beforeBoard[x][y] = 1; //turning off cell to on

        }
        else if (Board[x][y] == 1  &&  initial == -1) {
            beforeBoard[x][y] = 0; //turning on cell to off

        }
        cellsDrawn = true;
        repaint();
    }

    public void mouseEntered(MouseEvent e) {
        if(totalGens > 0 && paused == false) { //game unpauses if mouse enters window again (after exiting)
            time.start();
        }
        else {} //do nothing
    }

    public void mouseExited(MouseEvent e) { 
        time.stop(); //pauses if mouse exits windo
        if (totalGens > 0 && paused == false){
        System.out.println("paused");
        }
    }

    public void mouseMoved(MouseEvent e) { }

    public void mouseClicked(MouseEvent e) {  //same as drag, but clicking individual cells for accuracy
        int x = e.getX()/size;
        int y = e.getY()/size;

        if(Board[x][y] == 0  &&  initial == 0) {
            beforeBoard[x][y] = 1;
            aliveCells++;
        }
        else if (Board[x][y] == 1  &&  initial == -1) {
            beforeBoard[x][y] = 0;
            aliveCells--;
        }
        cellsDrawn = true;
        repaint();
    }

    
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //if the given key is pressed
        //"R" for reset/random
        if(code == KeyEvent.VK_R) {
            spawn(); //method for "spawning"/drawing the cells:
            cellsDrawn = true;
            paused = true;
            System.out.println("Press \"B\" to begin");
        }
        //"C" for clear
        else if(code == KeyEvent.VK_C) {
            clear();
            System.out.println("reset generations to " + generations);
            aliveCells = 0;
            totalGens = 0; //resetting generations already passed
            cellsDrawn = false;
            begun = false;
            paused = true;
            time.stop();

        }
        else if (code == KeyEvent.VK_1) { //advancing a singular generation
        	time.stop();

        	if (code == KeyEvent.VK_1) {
        		int alive;
                for (int x = 0; x < Board.length; x++) {
                    for (int y = 0; y < (yHeight); y++) {     // yPanel/size gives us the number of squares vertically

                        alive = check(x, y);        //when we call the check() method, alive increases every time there is an alive neighbor

                        if (alive == 3) {             
                            beforeBoard[x][y] = 1;
                        } else if ((alive == 2) && (Board[x][y] == 1)) {
                            beforeBoard[x][y] = 1;
                        } else {
                            beforeBoard[x][y] = 0;
                        }
                    }
                }
                repaint(); // "refreshes" the page 

                totalGens++;
                System.out.println("generations: " + totalGens);
        	} 
        	else {
        		System.out.println("nah");
        	}
        }

        else if(code == KeyEvent.VK_B) {//"B" for begin -> start of the timer
            if (aliveCells > 0) { //if there are any cells on board
                begun = true;
                time.start();
                paused = false;

            }else if (aliveCells <= 0){
                System.out.println("before beginning: click to draw cells, or press \"r\" for random cells");
            }
        }
        else if(code == KeyEvent.VK_A) {    //"A" for abandon -> timer will stop
            clear();
            aliveCells = 0;
            time.stop();
            System.out.println("aborted");
            saveProgress();
        }
        else if(code == KeyEvent.VK_S) {     //"S" for save progress

            System.out.println("Saved progress" + filename.length());
            if(filename.length() == 0) {
                saveProgress();
            }else {
                filename.delete();
                saveProgress();
            }    
        }
        else if(code == KeyEvent.VK_L) { // "L" for load progress
            getProgress();
            System.out.println("loaded previous save");
        }
        // "P" for pause
        else if (code == KeyEvent.VK_SPACE) {

            if (begun = false){  //if game hasnt started: do nothing
                time.stop();
                paused = true;
                System.out.println("paused, press \"B\" to begin");
            }
            else if(totalGens > 0 && aliveCells >0 && paused == true) { //if paused already: unpause
                time.start();
                paused = false;
            }else {        //while game is already running: pause
                paused = true;
                time.stop();
            }
        }
        //"O" for continue
        else if (code == KeyEvent.VK_O  && begun == true) {
            time.start();

        }
        // "T" for save Time
        else if (code == KeyEvent.VK_T) {
            inputTime(input);
        }

        else if (code == KeyEvent.VK_UP) { //speed decrease
            timeInput++;
            setTimeInput(timeInput);
            System.out.println("speed: " + time);
        }
        else if (code == KeyEvent.VK_DOWN) { //speed increase

            timeInput--;
            setTimeInput(timeInput);
            System.out.println("speed: " + timeInput);

        }
        else if (code == KeyEvent.VK_Q) {
            System.out.println("quitting");
            sleep(150);
            System.exit(0);
        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }
}
