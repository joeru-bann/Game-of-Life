package jolgol;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;  
//graphics
//event is an abstract class
//it will allow us to animate the panel
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//adding abstract classes for the mouse, so we can draw
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
//adding input from the keyBoard
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//saving and getting progress
import java.io.BufferedReader;
import java.io.FileReader;
//libraries for the image
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Panel extends JPanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener {
                       
	private static final long serialVersionUID = 1L;
	//dimensions of the panel:
	int xPanel = 800;
	int yPanel = 800;
	
	int size = 7; //size of each "cell"/square

	int xWidth = xPanel/size;  //xPanel/size gives us the number of squares:

    int yHeight = yPanel/size;
    
	int[][] Board = new int[xWidth][yHeight]; 	    
	
	int[][] beforeBoard = new int[xWidth][yHeight]; 	//adding another array and set it equal to 1 later in a method

	
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
	
	Scanner sc; 	//scanner to read back in the progress
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

	}	catch (FileNotFoundException e) {}
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
		
		setBackground(Color.BLACK);
		
		inputTime(input);	//adding game speed):

		if(timeInput == 0 || timeInput <= 0) {
			System.out.println("entered value is below/equal to 0, setting as default: 80");
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
	
	public static void sleep(int time) { // function to help reading process be smoother experience by pausing
        try {
            Thread.sleep(time);
        } catch (Exception e) {}
    }
	
	public static void clearScreen() {   //flushing terminal (clearing)
	    System.out.print("\u000c");  
	    System.out.flush();  
	}  


	//calling the creation methods for the elements
	public void paintComponent (Graphics g) {
		     //we are calling the super to the Graphics class:
		     //it refreshes and shows the current position, thus it doesn't lag
		super.paintComponent(g);
		     //calling the method for creating the grid
		grid(g);
		     //calling the methods for the squares:
		display(g);
	}
	
	
	
	
	//creating a grid with lines (gray for ease on eyes)
	private void grid(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		for(int i=0; i<Board.length; i++) { //drawing the grid lines
			g.drawLine(0, i*size, xPanel, i*size);  //row
			g.drawLine(i*size, 0, i*size, yPanel);  //columns
		}
	}
	
	
		private void spawn() { 	// choosing random squares to be on/alive
			for (int x=0; x<Board.length; x++) {
				for (int y=0; y<(yHeight); y++) {
					if ((int)(Math.random()*5) == 0) { //density of the alive cells, smaller number = more alive cells, 
						beforeBoard[x][y] = 1;
						cellPrint((int)(Math.random()*5)); //counting alive cells
					}

				}
			}
	}
	
	private void cellPrint(int b) {
		
		if (b == 1) { //b = true cells / "alive cells"
			aliveCells++;
		}
		else{}
		
		System.out.println(aliveCells + " alive cells");
	}
	

	//displaying the squares
	private void display(Graphics g) {
		//choosing color:
		g.setColor(Color.GREEN);
		
		copyArray();
		
		//we are going through every array index 
		for (int x = 0; x < Board.length; x++) {
			//yPanel/size gives us the number of squares vertically
			for (int y = 0; y < (yHeight); y++) {
				//and if that spot is on(=1), we want to display a color filled cell
				if (Board[x][y] == 1) {
					//coloring the cells
					g.fillRect(x * size, y * size, size, size);
				}
			}
		}
	}
	
	
	// we are coping one array to another
	private void copyArray() {
		for (int x = 0; x < Board.length; x++) {
			for (int y = 0; y < (yHeight); y++) { //giving us amount of vertical squares
				Board[x][y] = beforeBoard[x][y]; //updating fcurrent array from previous

			}
		}
	}
	
	
	//algorithm:
    //checks every one of the 8 neighours of each cell
	//every time it finds a neighbour cell, it increases the total count of alive
	private int check (int x, int y) {
		int alive = 0;
		//we are going down and right
		//with xWidth and yHeight we are fixing the exception -> explained
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
	
	
	public void actionPerformed(ActionEvent e) {	// implementing the rules of conways game of Board

		int alive;
		//while(begun = true) {
		for (int x = 0; x < Board.length; x++) {
			for (int y = 0; y < (yHeight); y++) { 	// yPanel/size gives us the number of squares vertically
				alive = check(x, y);		//when we call the check() method, alive increases every time there is an alive neighbor
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
		
		if (totalGens >= generations) { //pausing game when generations complete
			gensComplete = true;
			time.stop();
			paused = true;
			System.out.println("generations complete");
			completed();
		}
		else if (totalGens <= generations) {
			gensComplete = false;
		}
	}
		

			
	public void completed() { //when all the generations have run 
		cellsDrawn = false;
		System.out.println("play until reach end-state?");

	}


	
	private void saveProgress() { 	// need to fix the closing of the streams
		try {
			for (int x = 0; x < Board.length; x++) { // for each row
				for (int y = 0; y < (yHeight); y++) { // for each column
					writer.write(Board[x][y]); // append to the output
				}
				writer.write("\n");       //new line
			}
			writer.close();
			file.close();
			sc.close();
			reader.close();
		}catch (FileNotFoundException e) {}
		 catch (IOException e) {
		}
	}
	
	
	// need to fix the closing of the streams
    private void getProgress() {
	    try {
		    String line = reader.readLine();
		    while (line != null) {	
			    for (int x = 0; x < Board.length; x++) { // for each row
				    for (int y = 0; y < (yHeight); y++) { // for each column
				    	line = reader.readLine();
				    	beforeBoard[x][y] = reader.read(); // append to the output
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
		System.out.print("Enter game speed time in miliseconds:  "); 	System.out.println("(20 - 700 - recommended range) \n");

		this.setTimeInput(input.nextInt());
		inputGens(input);
		
	}
	
	
	public void inputGens(Scanner input) {	
		System.out.println("how many generations do you want?");
		
		this.setGens(input.nextInt());
		
		clearScreen();
		timedPrint("Press \"R\" for random, \"B\" for begin, \"L\" to load progress \n");
		timedPrint("you can draw/erase cells by clicking/dragging");
		
		clearScreen();
		timedPrint("press \"Q\" at any time to quit the game \n");
		
		input.close();		
		} 	

	
	public int getGens() {
		return generations;
	}
	
	public void setGens(int generations) {
		this.generations = generations;
	}
	
	public void chooseColour(String color) {
		
		System.out.println("Choose a colour for alive-cells? type y/n");
		String change = input.nextLine();
		String colour;

		
		if (change.equals("y")) {
			
			System.out.println("type a colour for the alive ");
			 colour = input.nextLine();
		}
		
		else if (change.equals("n")) {
			 colour = "GREEN";
		}
	}
	
	
    void takePicture(Panel panel) {
    	  BufferedImage img = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
    	  // it draws the panel
    	  //so if we pass just the constructor, it will draw the background and the grid
    	  // but not the components, because it's a brand new panel
    	  // so when we pass a panel, we pass the current one (using 'this')
    	  panel.paint(img.getGraphics()); // or: panel.printAll(...);
    	  try {
    	    ImageIO.write(img, "png", new File("panel.png"));
    	  }
    	  catch (IOException e) {
    	    e.printStackTrace();
    	  }
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
		//initial = -1;
		int x = e.getX()/size;
		int y = e.getY()/size;
		
		if(Board[x][y] == 0  &&  initial == 0) {
			beforeBoard[x][y] = 1;

		}
		else if (Board[x][y] == 1  &&  initial == -1) {
			beforeBoard[x][y] = 0;

		}
		cellsDrawn = true;
		repaint();
	}
	
	public void mouseEntered(MouseEvent e) {
		if(begun == true && paused == false) {
			time.start();
		}
		else {}
	}
	
	public void mouseExited(MouseEvent e) { 
		time.stop();
	}
	
	//movement of the mouse
	public void  mouseMoved(MouseEvent e) { }
	
	//MouseListener methods:
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
		System.out.println(aliveCells);
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
			aliveCells = 0;
			cellsDrawn = false;
			begun = false;
			time.stop();
			
		}
		
		else if(code == KeyEvent.VK_B) {//"B" for begin -> start of the timer
			if (aliveCells > 0) {
			time.start();
			begun = true;

			}else if (aliveCells <= 0){
				System.out.println("before beginning: click to draw cells, or press \"r\" for random cells");
			}
		}
		else if(code == KeyEvent.VK_A) {	//"A" for abandon -> timer will stop
			clear();
			aliveCells = 0;
			time.stop();
			System.out.println("aborted");
			saveProgress();
		}
		else if(code == KeyEvent.VK_S) { 	//"S" for save progress

			System.out.println(filename.length());
			if(filename.length() == 0) {
				saveProgress();
			}else {
				System.out.println("else save");
				//filename.delete();
				//saveProgress();
			}	
		}
		else if(code == KeyEvent.VK_L) { // "L" for load progress
			getProgress();
			System.out.println("loaded previous save");
		}
		// "P" for pause
		else if (code == KeyEvent.VK_P || code == KeyEvent.VK_SPACE) {
			
			if(paused == true && aliveCells > 0 && begun == true) { //if paused already
				time.start();
				paused = false;
			}
			else {  //if not already paused
				time.stop();
				paused = true;
				System.out.println("paused, press \"B\" to begin");

			}
		}
		//"O" for continue
		else if (code == KeyEvent.VK_O  && begun == true) {
			time.start();

		}
		// "I" for Picture
		else if (code == KeyEvent.VK_I) {
			takePicture(this);

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
