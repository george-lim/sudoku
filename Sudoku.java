/*
    George Lim
    Sudoku.java
    01/06/2013 v3.0
    Program purpose: This program allows the user to play either an easy or hard game of Sudoku using the mouse.
    Multiple screens: This program contains six screens: splashScreen, mainMenu, instructions, updateBoard, highScores, and goodBye.
			  The splashScreen screen displays a splash screen of Sudoku that introduces the program to the user.
			  The mainMenu screen allows the user to choose between viewing the high scores or instructions of the game, starting a game, or exiting the program.
			  The instructions screen displays the instructions for this game and how it will work to the user.
			  The updateBoard screen displays the visual elements of the game to the user. It draws the Sudoku board, the default given numbers, as well as the updated user inputted numbers on the board.
			  The highScores screen allows the user to view the high scores for each level that he/she has achieved.
			  The goodBye screen is used to display one final message to the user thanking them for using the program and telling them that it was programmed by George Lim.
    Program condition: The user must choose one of the four options provided by the program by their numeric value in the mainMenu screen to proceed.
    Instance variable dictionary:
	Name            Type                Description
	c               reference           This variable points to the Console class so the output window can be made.
	choice          char                This variable is used to store the choice that the user makes in the mainMenu() method.
	name            String              This variable stores the name of the player to be used for creating new high score records.
	level           int                 This variable stores the level that the user is currently either viewing for the high scores or playing on.
	RECORDS         static final int    This variable stores the number of high score records that the highScoreData[] array can store up to. The value is always 21 and cannot be modified.
	highScoreData   String array        This variable is used to store the high score data for both levels of this game. Its initial data comes from reading the "HighScores.lior" file in the readScores() method and is altered as the user plays more games and beats previous high scores. The goodBye() method prints all the elements in this array before the program exits to save any modifications to the high scores data.
*/
import java.awt.*;
import hsa.Console;
import java.io.*;
import javax.swing.JOptionPane;
import java.util.*;
public class Sudoku
{
    Console c;
    char choice;
    String name = "Player";
    int level;
    static final int RECORDS = 21;
    String[] highScoreData = new String [RECORDS];
    //  Class constructor purpose: Creates a new console Object window with the title "Sudoku".
    public Sudoku ()
    {
	c = new Console ("Sudoku");
    }


    //  Method purpose: Clears the screen and outputs the title of this program centered on the output window.
    private void title ()
    {
	c.setFont (new Font ("Sans Serif", Font.PLAIN, 32));
	c.clear ();
	c.drawString ("Sudoku", 268, 30);
	c.setCursor (4, 1);
    }


    /*
	Method purpose: Halts the program, displays a message to the user telling them what will happen when they press any key, and gets user input before resuming the program.
	Local variable dictionary:
	    Name        Type         Description
	    msg         String       Parameter pass that stores the specific message that tells the user what will happen when he/she presses any key.
    */
    private void pauseProgram (String msg)
    {
	c.println ();
	c.print ("Press any key to " + msg + "...");
	c.getChar ();
    }


    /*
	Method purpose: Calls two runnable threads to display a splash screen animation for the user.
	Local variable dictionary:
	    Name        Type         Description
	    s1          reference    This variable points to the SplashAnimation1 class so the methods inside the SplashAnimation1 class can be accessed.
	    s2          reference    This variable points to the SplashAnimation2 class so the methods inside the SplashAnimation2 class can be accessed.
    */
    public void splashScreen ()
    {
	SplashAnimation1 s1 = new SplashAnimation1 (c);
	SplashAnimation2 s2 = new SplashAnimation2 (c);
	s1.run ();
	s2.run ();
    }


    /*
	Method purpose: Parses information from the HighScores.lior extension file into the highScoreData[] array so that the user can view and alter previous high scores.
	Loop purpose: The first for-loop (x) runs to assign each element in the highScoreData[] array a value equal to a record being read by the BufferedReader class in the HighScores.lior file.
		      The second for-loop (x) runs to assign each element in the highScoreData[] array a value of nothing in the event that an IOException has occured. This is done to set all high score records to nothing instead of null.
	Conditional statements: The try/catch statement is used to catch any IOException that could be caused from using the BufferedReader class.
				The first if statement is used to check if there is a valid header in the HighScores.lior extension file. If there isn't one, the highScoresData[] array will store it as the first element to be printed in the goodBye() method.
				The second if statement is used to check to see if the value of the line that was read in the HighScores.lior file is null. If it is, it will be assigned a new value of nothing.
	Local variable dictionary:
	    Name        Type        Description
	    in          reference   This variable points to the BufferedReader class so the methods inside the BufferedReader class can be accessed. The file being read by this class is the HighScores.lior extension file.
	    x           int         This loop variable is used to control the amount of iterations the program will use to read the x amount of lines in the HighScores.lior file.
	    e           reference   This variable points to the BufferedReader class to prevent the program from crashing from any errors caused by the file IO stream.
    */
    public void readScores ()
    {
	BufferedReader in;
	try
	{
	    in = new BufferedReader (new FileReader ("HighScores.lior"));
	    highScoreData [0] = in.readLine ();
	    if (highScoreData [0] == null || !highScoreData [0].equals ("This file was created using the Sudoku program by George + Lior :)"))
		highScoreData [0] = "This file was created using the Sudoku program by George + Lior :)";
	    for (int x = 1 ; x < 21 ; x++)
	    {
		highScoreData [x] = in.readLine ();
		if (highScoreData [x] == null)
		    highScoreData [x] = "";
	    }
	}
	catch (IOException e)
	{
	    highScoreData [0] = "This file was created using the Sudoku program by George + Lior :)";
	    for (int x = 1 ; x < 21 ; x++)
		highScoreData [x] = "";
	}
    }


    /*
	Method purpose: Gives the user a choice of either viewing the instructions of the game, starting the game, viewing the high scores for the game, or closing the program.
	Loop purpose: The while loop in this method is used as a means of error trapping so that the user will always be able to re-enter user input if their previous value was not valid.
	Conditional statements: The if statement in this method is used to check if the user entered a valid character that translates to a valid choice offered by the program. If this is true, it allows access out of the while loop.
    */
    public void mainMenu ()
    {
	title ();
	c.println ("1. Instructions");
	c.println ("2. Play Game");
	c.println ("3. High Scores");
	c.println ("4. Exit");
	c.setCursor (9, 1);
	c.print ("Please enter a number between 1-4...");
	while (true)
	{
	    choice = c.getChar ();
	    if (choice == '1' || choice == '2' || choice == '3' || choice == '4')
		break;
	    JOptionPane.showConfirmDialog (null, "You must enter a number between 1-4 to proceed.", "Input Error", JOptionPane.DEFAULT_OPTION);
	}
    }


    //  Method purpose: Displays the instructions of the game to the user. The user presses any key to return to the main menu.
    public void instructions ()
    {
	title ();
	c.setCursor (3, 32);
	c.print ("Game Instructions");
	c.setCursor (5, 1);
	c.println ("Welcome to Sudoku!\n\nTo start a new game, choose '2' from the main menu and select a level. You will then be presented with 3 options. 'New Game' will create a new board for you to play, 'Console' will allow you to access the console of this game and enter a\ncheat code if you have one, and finally 'Exit' will bring you back to the\nmain menu.\n\nSelect from the list of coordinateinates for boxes to solve for and select a value to\nrepresent the box. When you are done, choose the submit option when selecting a\ncoordinateinate at the bottom to have your board be evaluated.\n\nYou will be timed during your game, so good luck!");
	pauseProgram ("go back to main menu");
    }


    /*
	Method purpose: Starts the game by asking the user for level information, and then presenting them with a sub-menu for them to enter a cheat code in the built-in game console before starting a new game. This method also handles the game flow and gets all the user input required to play the game.
	Loop purpose: The first while loop is used to present to the user the sub-menu for the Sudoku game indefinitely until the user chooses to either start a new game or exit and return to the main menu.
		      The first for-loop (x) & nested for-loop (y) are used to cast the letters of the String "ILOVELIOR" into integer values for the puzzle if the best friend cheat code is active.
		      The second for-loop (x) & nested for-loop (y) are used in the hard level of Sudoku to remove specific boxes on the Sudoku grid to form a pattern that is solvable, thus completing the random board generation.
		      The third for-loop (x) is used to run and execute the generate black-box method multiple times if the demo cheat code is active and the level is one. This is done because the generate black-box method only generates three valid numbers for the easy level board at a time. Otherwise, if the demo cheat code is active and the level is two, the generate black-box method is only run once to generate the correct answers to the puzzle.
		      The fourth for-loop (x) & nested for-loop (y) are used to store the coordinates of the unsolved boxes for the puzzle into a String array called boxes. This allows the user to see and select which box to solve for using the x & y grid coordinates provided on the updateBoard screen.
		      The second (nested) while loop is used to indefinitely allow the user to choose a box to modify and assign a value to until the user chooses to leave the game or submit his/her answers to the game to be evaluated.
	Conditional statements: The first if statement is used to check if the user selected a level to play. If the user did not, the program returns to the main menu.
				The second if statement structure checks to see if the user chose to quit the sub-menu in the Sudoku game. If the user did, then the program returns to the main menu. Otherwise, if the selects the "New game" option in the sub-menu, the program will generate a new board for the user and allow the user to play a game of Sudoku. Finally, if the user chooses none of those options, the Sudoku console window will open up so the user may enter a cheat code.
				The third if statement is accessed when the user chooses "New Game" and checks to see if the best friend cheat code is active. If it is, then the program will draw a really wonderfully beautiful animation for my best friend ever <3. Otherwise, the game will randomly generate a board and allow the user to play a game of Sudoku.
				The fourth if statement & nested if statement are accessed when the user chooses "New Game" and checks to see if the difficulty is currently set to hard. If the user is running that difficulty, then the nested if statement will remove specific boxes on the Sudoku grid according to the x and y values of the nested for-loops to form a pattern that is solvable, thus completing the random board generation.
				The fifth if statement & nested if statement are accessed when the user chooses "New Game" and checks to see if the demo cheat code is active. If it is, then the program will execute the generating black-box method again for the usrInput[][] array so that the user input answers will always be correct as they are solved in that method. Otherwise, the nested if statement inside the nested for-loop checks to see if value of each box is equal to zero. If it is, then it stores the coordinates of that box into the String array boxes for the user to select and solve for later on.
				The sixth if statement & nested if statements are accessed when the user chooses "New Game" and are used to check if the user didn't choose a selection or chose to exit when presented with the menu to choose a box to solve for. If this is true, then the first nested if statement will check if the user chose the yes option in response to the confirmation message to quit the current game. Otherwise, if the user chose to submit their answers to be evaluated, the game will stop the timer and call the processing() private method to evaluate their answers. Finally, if the user chose a box, the second nested if statement will check if their selection for the number to go into the chosen box wasn't equal to null. If this is true, the program will assign their selected value into their selected box and update the board graphics.
				The seventh if statement & nested if statements are accessed when the user chooses "Console" and are used to check if the user entered a cheat code into the Sudoku Console. If he/she did, the first nested if statement will check if the user entered the cheat code "demo". If the user did, then a message will occur telling the user that he/she successfully entered the demo cheat code. Otherwise, the second nested if statement checks to see if the user entered the best friend cheat code. If the user did, a message will occur telling the user that he/she successfully entered the best friend cheat code.
	Local variable dictionary:
	    Name        Type            Description
	    demo        boolean         This boolean stores the logical value of whether or not the demo cheat code has been entered in the Sudoku console.
	    bestFriend  boolean         This boolean stores the logical value of whether or not the bestFriend cheat code has been entered in the Sudoku console.
	    selection   Object          This variable stores the chosen String that the user selected from the list provided in the new String array in JOptionPane.
	    GRID        final int       This variable stores the number of elements that the 2D int array puzzle and usrInput can store up to for each dimension. This variable is a final so the number of elements in both 2D arrays cannot be over the set amount for this variable.
	    puzzle      2D int array    This 2D int array stores the given numbers of the Sudoku puzzle before being solved.
	    usrInput    2D int array    This 2D int array stores the numbers of the boxes entered in by the user.
	    counter     int             This variable acts as a counter for the amount of elements that will go into the String array boxes.
	    start       long            This variable stores the current system time in milliseconds for the start time in the timer for the duration of the game.
	    boxes       String array    This String array stores the coordinates for the boxes the user has to solve for as well as the submit and exit options. It is used later on as the selection menu for the user so he/she may choose which box to solve for, submit his/her puzzle, or quit the game.
	    msg         int             This variable stores the resulting value of the JOptionPane dialog. It is used in if statements to control program flow based on user input.
	    options     Object          This variable stores the chosen String from the list of numbers that the user could use to represent the selected box.
	    console     String          This variable stores the input that the user typed in the JOptionPane input dialog in the Sudoku Console.
	    x           int             This loop variable is used various times throughout this method to manipulate the rows of either the puzzle[][] 2D int array or the usrInput[][] 2D int array.
	    y           int             This loop variable is used various times throughout this method to manipulate the columns of either the puzzle[][] 2D int array or the usrInput[][] 2D int array.
    */
    public void start ()
    {
	boolean demo = false, bestFriend = false;
	Object selection = JOptionPane.showInputDialog (null, "Select a level to play:", "Level Selection", JOptionPane.QUESTION_MESSAGE, null, new String[] {"Easy", "Hard"}, "Easy");
	if (selection == null)
	    return;
	level = (selection.equals ("Easy")) ? 1 : 2;
	while (true)
	{
	    selection = JOptionPane.showInputDialog (null, "What would you like to do?", "Sudoku Game Options", JOptionPane.QUESTION_MESSAGE, null, new String[] {"New Game", "Console", "Exit"}, "New Game");
	    if (selection == null || selection.equals ("Exit"))
		return;
	    else if (selection.equals ("New Game"))
	    {
		final int GRID = level * 6 - 3;
		int[] [] puzzle = new int [GRID] [GRID], usrInput = new int [GRID] [GRID];
		if (bestFriend)
		{
		    for (int x = 0 ; x < 3 ; x++)
			for (int y = 0 ; y < 3 ; y++)
			    puzzle [x] [y] = (int) "ILOVELIOR".charAt (x * 3 + y);
		    updateBoard (1, puzzle, usrInput, bestFriend);
		    c.getChar ();
		    bestFriend = false;
		}
		else
		{
		    int counter = 0;
		    long start = System.currentTimeMillis ();
		    generate (level, 0, 0, puzzle);
		    if (level == 2)
			for (int x = 0 ; x < 9 ; x++)
			    for (int y = 0 ; y < 9 ; y++)
				if (x == 0 && (y != 0 && y != 7) || (x == 1) && (y != 0 && y != 5 && y != 7) || x == 2 && (y != 3 && y != 6 && y != 8) || x == 3 && (y != 2 && y != 3 && y != 6 && y != 7) || x == 4 && (y != 3 && y != 4 && y != 5) || x == 5 && (y != 1 && y != 2 && y != 5 && y != 6) || x == 6 && (y != 0 && y != 2 && y != 5) || x == 7 && (y != 1 && y != 3 && y != 8) || x == 8 && (y != 1 && y != 8))
				    puzzle [x] [y] = 0;
		    String[] boxes = new String [level * 72 - 63];
		    if (demo)
			for (int x = 0 ; x < 3 - level ; x++)
			    generate (level, 0, 0, usrInput = puzzle);
		    else
			for (int x = 0 ; x < GRID ; x++)
			    for (int y = 0 ; y < GRID ; y++)
				if (puzzle [y] [x] == 0)
				{
				    usrInput [y] [x] = -1;
				    boxes [counter] = Character.toString ((char) ('A' + x)) + (y + 1) + "";
				    counter++;
				}
		    boxes [counter] = "Submit";
		    boxes [counter + 1] = "Exit";
		    updateBoard (level, puzzle, usrInput, bestFriend);
		    while (true)
		    {
			selection = JOptionPane.showInputDialog (null, "Please select a box:", "Sudoku", JOptionPane.QUESTION_MESSAGE, null, boxes, boxes [0]);
			if (selection == null || selection.equals ("Exit"))
			{
			    int msg = JOptionPane.showConfirmDialog (null, "Are you sure you would like to exit the current game?\nAll progress will be lost.", "Sudoku", JOptionPane.YES_NO_OPTION);
			    if (msg != JOptionPane.CANCEL_OPTION && msg == JOptionPane.YES_OPTION)
			    {
				demo = false;
				break;
			    }
			}
			else if (selection.equals ("Submit"))
			{
			    processing (puzzle, usrInput, (System.currentTimeMillis () - start) / 1000, demo);
			    c.setColor (Color.black);
			    return;
			}
			else
			{
			    Object options = JOptionPane.showInputDialog (null, "Please choose a number to represent '" + selection + "':", "Sudoku", JOptionPane.QUESTION_MESSAGE, null, new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"}, "1");
			    if (options != null)
			    {
				usrInput [(int) ((String) selection).charAt (1) - 49] [(int) ((String) selection).charAt (0) - 65] = (int) ((String) options).charAt (0) - 48;
				updateBoard (level, puzzle, usrInput, bestFriend);
			    }
			}
		    }
		}
	    }
	    else
	    {
		String console = JOptionPane.showInputDialog ("Sudoku Console [Version 1.0]", "Enter cheat here...");
		if (console != null)
		{
		    if (console.equalsIgnoreCase ("demo"))
			JOptionPane.showConfirmDialog (null, "Demo cheat activated. The next time you press \"New Game\",\nthe answers to the Sudoku will already be given to you.\nHave fun!", "Sudoku Console", JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
		    else
			if (console.equalsIgnoreCase ("best friend"))
			    JOptionPane.showConfirmDialog (null, "Best Friend cheat activated.\nPress \"New game\" to see what happens!", "Sudoku Console", JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
		    demo = (console.equalsIgnoreCase ("demo")) ? true : false;
		    bestFriend = (console.equalsIgnoreCase ("best friend")) ? true : false;
		}
	    }
	}
    }


    /*
	Method purpose: Using the value of the level, the 2D int array puzzle and usrInput, and the boolean for the best friend cheat code, this method will draw the board for the Sudoku for the level that the user has selected. This method will update the board with the numbers entered by the user, or will draw a lovely wonderful custom board for my best friend ever!
	Loop purpose: The first for-loop (x) is used to draw the x and y coordinate grid labels.
		      The second for-loop (x) is used to draw the main nine boxes of the Sudoku grid that are used in both levels.
		      The third for-loop (x) is used to draw the smaller boxes of the Sudoku grid that are used in the hard level.
		      The fourth for-loop & nested for-loop are used to draw the secret best friend animation that can only be viewed by entering the "best friend" cheat code.
		      The fifth for-loop & nested for-loop are used to draw the numbers of the puzzle on the board as well as the numbers entered by the user.
	Conditional statements: The first if statement is used to perform different actions based on the level of the user. If the level is one, the font is changed to a larger one. Otherwise, the program draws the smaller boxes that are found on the harder level of the Sudoku.
				The second if statement and nested if statement are used to check if the best friend boolean is true. If it is, then the code draws the really cool and sweet graphic that displays "I Love Lior" in honour of my best-est friend in the whole entire universe, Lior! The nested if statement is used to make the colour of the text being outputted red when the words are "I" or "Lior".
				The third if statement is used to check if the value of the coordinates x (row) and y (column) in the puzzle[][] array is a given number. If this is true, the program will output the puzzle given number.
				The fourth if statement is used to check if the user entered any number for the box in puzzle [x][y]. If he/she did, the program will draw the user inputted number in that box.
	Local variable dictionary:
	    Name        Type          Description
	    level       int           Parameter pass that stores the value of the level that the user is currently playing in Sudoku.
	    puzzle      2D int array  Parameter pass that stores the Sudoku puzzle values using the first dimension array as the row number and the second dimension array as the column number.
	    usrInput    2D int array  Parameter pass that stores the Sudoku user input puzzle values using the first dimension array as the row number and the second dimension array as the column number.
	    bestFriend  boolean       Parameter pass that stores the logical value of whether or not the best friend cheat code has been activated.
	    large       reference     This variable points to the Font class and alters the font for text drawn with the drawString() method in the hsa.Console class so it is sans serif and 72 point font.
	    small       reference     This variable points to the Font class and alters the font for text drawn with the drawString() method in the hsa.Console class so it is sans serif and 32 point font.
	    x           int           This loop variable is used to help draw all the animations as well as be used as the row coordinate for the puzzle[][] or usrInput[][] arrays.
	    y           int           This loop variable is used as the column coordinate for the puzzle[][] or usrInput[][] arrays so they can be drawn onto the Sudoku board.
    */
    private void updateBoard (int level, int[] [] puzzle, int[] [] usrInput, boolean bestFriend)
    {
	Font large = new Font ("Sans Serif", Font.PLAIN, 72), small = new Font ("Sans Serif", Font.PLAIN, 32);
	c.clear ();
	c.setColor (Color.black);
	c.setFont (new Font ("Sans Serif", Font.PLAIN, 24));
	for (int x = 3 - level ; x < 10 ; x += 5 - 2 * level)
	{
	    c.drawString (x * (level - 1) + (x + 1) / 3 * (2 - level) + "", 113 + x * 40, 48);
	    c.drawString ((char) (64 + x * (level - 1) + (x + 1) / 3 * (2 - level)) + "", 112 + x / 9 * 6, 46 + x * 40);
	}
	for (int x = 0 ; x < 361 ; x += 120)
	{
	    c.fillRect (140, 55 + x, 363, 3);
	    c.fillRect (140 + x, 55, 3, 363);
	}
	c.setFont (small);
	c.drawString ("Sudoku", 268, 450);
	if (level == 1)
	    c.setFont (large);
	else
	    for (int x = 0 ; x < 281 ; x += 40)
	    {
		c.fillRect (140, 95 + x, 363, 1);
		c.fillRect (180 + x, 55, 1, 363);
	    }
	if (bestFriend)
	{
	    for (int x = 0 ; x < 3 ; x++)
		for (int y = 0 ; y < 3 ; y++)
		{
		    c.setColor (Color.black);
		    c.drawString ((char) puzzle [y] [x] + "", 180 + x * 120, 148 + y * 120);
		    if (y * 3 + x == 0 || y * 3 + x > 4 && y * 3 + x < 9)
			c.setColor (Color.red);
		    else
			c.setColor (Color.pink);
		    c.drawString ((char) puzzle [y] [x] + "", 177 + x * 120, 145 + y * 120);
		}
	    c.setColor (Color.black);
	    return;
	}
	for (int x = 0 ; x < level * 6 - 3 ; x++)
	    for (int y = 0 ; y < level * 6 - 3 ; y++)
		if (puzzle [x] [y] != 0)
		{
		    c.setColor (Color.gray);
		    c.drawString (puzzle [x] [y] + "", 151 + 30 * (2 - level) + x * 40 * (5 - 2 * level), 87 + 58 * (2 - level) + y * 40 * (5 - 2 * level));
		}
		else
		    if (usrInput [x] [y] != -1)
		    {
			c.setColor (Color.black);
			c.drawString (usrInput [x] [y] + "", 151 + 30 * (2 - level) + x * 40 * (5 - 2 * level), 87 + 58 * (2 - level) + y * 40 * (5 - 2 * level));
		    }
    }


    /*
	Method purpose: Using the 2D int array puzzle and usrInput, the timed amount of seconds the user took to submit the puzzle, and the boolean for the demo cheat code, this method will check and see if the user has successfully completed the puzzle, calculate their final score including the time, and check eligibility for the user entering the high scores. If the user are eligible, then this method will save their high score.
	Loop purpose: The first for-loop (x) and three nested for-loops (y, z, and a) are used to compare the two 2D int arrays (puzzle and usrInput) and see if the values in the usrInput[][] array are all different from the values in the puzzle[][] array and if the values in the ustInput[][] array aren't repeated.
		      The second for-loop (x) and nested for-loop (y) are used in the hard level of Sudoku to compare the solved puzzle[][] array with the usrInput[][] array to calculate puzzle completion and score.
		      The while loop is used to display the "New High Score achieved! Would you like to save this score in the High Scores?" message indefinitely until the user either explicitly answers with no, or provides a name for the game.
		      The third for-loop (x) is used to reorganize the array element order to correspond to the position that the user's high score achieved.
	Conditional statements: The first if statement & five nested if statements are used to separate the score calculating and comparing algorithms between levels so that the program can check for puzzle completion in two different ways depending on the level. If the level is one, the first nested if statement is used to skip the comparison of numbers that are already given in the puzzle[][] 2D int array. The second nested if statement is used to skip comparison of numbers if the values z equals x and a equals y. This prevents the program from checking for repeated numbers for the same position. The third nested if statement is used to check if there is any repetition of numbers between the usrInput[][] array and the puzzle[][] array as well as between the elements inside the usrInput[][] array. If this is true, the solved boolean turns false because the user hasn't completed the puzzle. It also decreases their score by 500 and breaks out of the z loop so that the next element in the usrInput[][] array can be compared. Otherwise, if the level is two, the fourth if statement is used to check if the elements in the usrInput[][] array is not equal to the elements in the puzzle[][] array in any way. If this is true, the solved boolean is false. Otherwise, the fifth nested if statement checks to see if the number wasn't a given. If this is true, then the user gets 500 points in score.
				The second and third if statement structures are used to calculate the time in hours and minutes based on the parameter pass 'seconds'. They (re)assign the variables minute and second to correspond with the number of hours or minutes.
				The fourth if statement is used to check for total puzzle completion. The program displays different messages depending on whether or not the user won or not.
				The fifth if statement is used to check if the user used the demo cheat code. If this is true, a message will tell the user that their high score will not be saved due to using the cheat code.
				The sixth if statement is used to check again for total puzzle completion. If the user did not successfully complete the puzzle or is using the demo cheat code, the program will return the user back to the main menu. Otherwise, the code for saving the user's legitimate high score will execute.
				The seventh if statement is used for the StringTokenizer to compare and evaluate if the user's high score is greater than the high score that the StringTokenizer is currently reading. If this is true, then a while loop will be used to alert the user about their high score and will try to get their name. The program then reorganizes the highScoreData[] array to accommodate for the user's new high score.
				The eighth, ninth, tenth, and eleventh if statements are found inside the while loop structure and are used to control the JOptionPane message flow that is being directed to the user about their high score. The first if statement checks to see if the user chose not to save their high score. If this is true, the program returns to the main menu. The second if statement checks to see if the user already entered a name before and will display a message asking if he/she would like to change it if it is true. The third if statement is used to check if the user chose to change their name. If this is true, the program will display an input box for the user to enter their name. Finally the last if statement is used to check if the user entered a valid name. If this is true, the program breaks out of the while loop and saves their score in the highScoreData[] array.
	Local variable dictionary:
	    Name        Type          Description
	    puzzle      2D int array  Parameter pass that stores the Sudoku puzzle values using the first dimension array as the row number and the second dimension array as the column number.
	    usrInput    2D int array  Parameter pass that stores the Sudoku user input puzzle values using the first dimension array as the row number and the second dimension array as the column number.
	    seconds     long          Paremeter pass that stores the time in seconds it took for the user to solve/submit the puzzle.
	    demo        boolean       Parameter pass that stores the logical value of whether or not the demo cheat code has been entered in the Sudoku console.
	    score       int           Stores your final score after time deductions.
	    solved      boolean       Stores the logical value of whether or not you have successfully beaten the puzzle of Sudoku.
	    minutes     long          Stores the time in minutes it took for the user to solve/submit the puzzle.
	    hours       long          Stores the time in hours it took for the user to solve/submit the puzzle.
	    st          reference     This variable points to the StringTokenizer class so the methods inside the StringTokenizer class can be accessed. The String being manipulated is each of the elements inside the highScoreData[] String array.
	    msg         int           This variable stores the resulting value of the JOptionPane dialog. It is used in if statements to control program flow based on user input.
	    x           int           This loop variable is used various times throughout this method to manipulate the rows of either the puzzle[][] 2D int array or the usrInput[][] 2D int array. It is also used to control the number of times the StringTokenizer class will read and compare the different records inside the highScoreData[] String array.
	    y           int           This loop variable is used various times throughout this method to manipulate the columns of either the puzzle[][] 2D int array or the usrInput[][] 2D int array. It is also used to control the amount of times the program will reorganize the highScoreData[] String array based on the position of your high score.
	    z           int           This loop variable is used to compare the two 2D int arrays puzzle and usrInput and represents the rows of the usrInput array.
	    a           int           This loop variable is used to compare the two 2D int arrays puzzle and usrInput and represents the columns of the usrInput array.
    */
    private void processing (int[] [] puzzle, int[] [] usrInput, long seconds, boolean demo)
    {
	int score = 6000 - 3000 * level;
	boolean solved = true;
	if (level == 1)
	{
	    for (int x = 0 ; x < 3 ; x++)
		for (int y = 0 ; y < 3 ; y++)
		    if (puzzle [x] [y] == 0)
			loop : for (int z = 0 ; z < 3 ; z++)
			    for (int a = 0 ; a < 3 ; a++)
				if (z == x & a == y)
				    break;
				else
				    if (usrInput [x] [y] == puzzle [z] [a] || usrInput [x] [y] == usrInput [z] [a])
				    {
					solved = false;
					score -= 500;
					break loop;
				    }
	}
	else
	{
	    generate (level, 0, 0, puzzle);
	    for (int x = 0 ; x < 9 ; x++)
		for (int y = 0 ; y < 9 ; y++)
		    if (usrInput [x] [y] == -1 || usrInput [x] [y] != puzzle [x] [y] && usrInput [x] [y] != 0)
			solved = false;
		    else
			if (usrInput [x] [y] != 0)
			    score += 500;
	}
	score += 50 * Math.pow (10, level) - (int) seconds;
	long minutes = (seconds / 60 > 0) ? seconds / 60 : 0;
	long hours = (minutes / 60 > 0) ? minutes / 60 : 0;
	if (minutes > 0)
	    seconds %= 60;
	if (hours > 0)
	    minutes %= 60;
	if (solved)
	    JOptionPane.showConfirmDialog (null, "Congratulations, you have successfully solved this puzzle!", "Sudoku", JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
	else
	    JOptionPane.showConfirmDialog (null, "Unfortunately, you have not solved this puzzle. Better luck next time!", "Sudoku", JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
	JOptionPane.showConfirmDialog (null, "Time Elapsed: " + hours + "h " + minutes + "m " + seconds + "s\nFinal Score: " + score, "Sudoku", JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
	if (demo)
	    JOptionPane.showConfirmDialog (null, "Because you are currently in demo mode,\nyour score will not be saved.", "Sudoku", JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
	if (!solved || demo)
	    return;
	StringTokenizer st;
	for (int x = 10 * level - 9 ; x < 10 * level + 1 ; x++)
	{
	    st = new StringTokenizer (highScoreData [x]);
	    if (!st.hasMoreTokens () || score > Integer.parseInt (st.nextToken ()))
	    {
		while (true)
		{
		    int msg = JOptionPane.showConfirmDialog (null, "New High Score achieved! Would you like to save this score in the High Scores?", "New High Score!", JOptionPane.YES_NO_OPTION);
		    if (msg != JOptionPane.YES_OPTION)
			return;
		    if (!name.equalsIgnoreCase ("Player"))
			msg = JOptionPane.showConfirmDialog (null, "Your current name is " + name + ". Would you like to change your name for this high score?", "New High Score!", JOptionPane.YES_NO_OPTION);
		    if (msg == JOptionPane.YES_OPTION)
			name = JOptionPane.showInputDialog ("Please enter your name in the box below.");
		    if (name != null)
			break;
		    name = "Player";
		}
		for (int y = 10 * level ; y > x ; y--)
		    highScoreData [y] = highScoreData [y - 1];
		highScoreData [x] = score + " " + name + " " + level;
		break;
	    }
	}
    }


    /*
	Black-box method purpose: Using the value of the level, the current row, the current column, and the 2D int array puzzle, this method will use an algorithm to solve for each box in order, skipping through the boxes already solved for the hard level. By doing this, this method can both generate and solve a puzzle because generating a new board is treated as solving a 'blank' board. This black-box method first creates a random sequence of numbers to test into a box on the grid, and after checking to see if it is a valid number, it checks the next box. It uses recursion with altered values for the row and column so that backtracking is enabled. For the easy level, two new sequences of random numbers are introduced so that the numbers appear on the easy board in random positions.
	Loop purpose: The first for-loop (x) & nested for-loop (y) are used to generate a random sequence of numbers ranging from one to nine. The first for-loop runs for nine iterations and is used to control the amount of random numbers that are entered into the val[] int array sequence. The nested for-loop (y) runs for nine iterations and is used to control the amount of times the val[] int array is compared with the value of val[x].
		      The second for-loop (x) runs for three iterations and is only accessed if the current level is easy. It is used to insert three of the elements in the val[] int array into three random positions in the puzzle[] grid array.
		      The third for-loop (x) runs for nine iterations and is used to test each element in the val[] int array into the currently set row and column of the hard level of the Sudoku grid.
	Conditional statements: The first if statement & nested if statement are used to check if the value of row is equal to nine. If this is true, then it resets the row to zero and checks to see if the column value + one is equal to nine. If this is true, then this method stops recurring and returns true.
				The second if statement is used to check if the level is equal to two and if the value of the current row and column is not equal to zero. This makes the generate method skip the filled numbers and only solve for blank boxes if the level is two.
				The third if statement is used to check if the randomly generated number already exists in the sequence. If it does, then it decreases the value of 'x' by one so that the loop will run again.
				The fourth if statement & nested if statement are used to check if the currently set level is one. If it is, the nested if statement checks to see if the value for the row/column position in the puzzle array isn't already modified. If this is true, it assigns the random position a number from the val[] array sequence, otherwise it decreases 'x' by one to run the loop again.
				The fifth if statment & nested if statement are used to test if the number used from the random number sequence is valid in the current row and column position in the puzzle array. If the black-box method verify returns true, then it assigns the value of the number to the position that is valid. Then the nested if statement checks to see if recurring the method with an increment of the row returns true. If it does, then we know that the puzzle must be completely solved and thus the generate method returns true.
	Local variable dictionary:
	    Name        Type          Description
	    level       int           Parameter pass that stores the value of the level that the user is currently playing in Sudoku.
	    row         int           Parameter pass that stores the row number of the value that we are inserting into the Sudoku generated/generating puzzle.
	    col         int           Parameter pass that stores the column number of the value that we are inserting into the Sudoku generated/generating puzzle.
	    puzzle      2D int array  Parameter pass that stores the Sudoku puzzle values using the first dimension array as the row number and the second dimension array as the column number.
	    ELEMENTS    final int     This variable is used to prevent alteration of the number of elements that can enter the val[] int array.
	    val         int array     This array stores the sequence of numbers between one and nine in a random order to be added onto the board for generating or solving.
	    sequence    reference     This variable points to the Random class so the methods inside the Random class can be accessed.
	    x           int           This loop variable is used in the first for-loop to control the number of times a new number will be added to the val[] int array. It is then used to control the number of times a number will be added to the first level of Sudoku. Finally it is used to test each number in the random sequence into the current row and column in the verify method until there are no violations.
	    y           int           This loop variable is used to control the number of times the value of val[x] is compared to the value of val [y].
    */
    private boolean generate (int level, int row, int col, int[] [] puzzle)
    {
	if (row == 9)
	{
	    row = 0;
	    if (++col == 9)
		return true;
	}
	if (level == 2 && puzzle [row] [col] != 0)
	    return generate (level, row + 1, col, puzzle);
	final int ELEMENTS = 9;
	int[] val = new int [ELEMENTS];
	Random sequence = new Random ();
	for (int x = 0 ; x < 9 ; x++)
	{
	    val [x] = sequence.nextInt (9) + 1;
	    for (int y = 0 ; y < 9 ; y++)
		if (val [x] == val [y] && x != y)
		{
		    x--;
		    break;
		}
	}
	if (level == 1)
	{
	    for (int x = 0 ; x < 3 ; x++)
	    {
		row = sequence.nextInt (3);
		col = sequence.nextInt (3);
		if (puzzle [row] [col] == 0)
		    puzzle [row] [col] = val [row * 3 + col];
		else
		    x--;
	    }
	    return true;
	}
	for (int x = 0 ; x < 9 ; x++)
	    if (verify (row, col, val [x], puzzle))
	    {
		puzzle [row] [col] = val [x];
		if (generate (level, row + 1, col, puzzle))
		    return true;
	    }
	puzzle [row] [col] = 0;
	return false;
    }


    /*
	Black-box method purpose: Using the value for the current row, the current column, value of the row and column and the 2D int array puzzle, this black-box method will check if the value for the row and column is a legal move and isn't obstructing any rules for Sudoku. It returns true if there are no violations for the value in the row and column, and false if there is a violation.
	Loop purpose: The first for-loop (x) runs for nine iterations and is used to check if there is the same number being found on the same row or column on the Sudoku grid.
		      The second for-loop (x) & nested for-loop (y) is used to check for violations in the same box. The first for-loop runs for three iterations and is used to check if the value of 'val' is found within the same row of the box it is located in. The nested for-loop (y) runs for three iterations and is used to check if the value of 'val' is found within the same column of the box it is located in.
	Conditional statements: The first if statement is used to check for violations for whether or not the value of 'val' is found in the same row or column in the whole Sudoku puzzle. If it is true, then this black-box method returns false due to 'val' violating one of the three rules. Otherwise, it moves on to the next check.
				The second if statement is used to check if 'val' is found anywhere in the same box as it is located in. If it is true, then this black-box method returns false due to 'val' violating one of the three rules. Otherwise, it returns true because 'val' has passed all the checks and is a valid number.
	Local variable dictionary:
	    Name    Type          Description
	    row     int           Parameter pass that stores the row number of the value that we are inserting into the Sudoku generated/generating puzzle.
	    col     int           Parameter pass that stores the column number of the value that we are inserting into the Sudoku generated/generating puzzle.
	    val     int           Parameter pass that stores the number of the value that we are inserting into the Sudoku generated/generating puzzle to verify.
	    puzzle  2D int array  Parameter pass that stores the Sudoku puzzle values using the first dimension array as the row number and the second dimension array as the column number.
	    x       int           This loop variable is used to control the number of times the program will check for violations. For the box check, it is used to check if 'val' is found in the same row of its box position.
	    y       int           This loop variable is used to control the number of times the program will check for violations in the column if 'val' is found in the same column of the same box it is located in.
    */
    private boolean verify (int row, int col, int val, int[] [] puzzle)
    {
	for (int x = 0 ; x < 9 ; x++)
	    if (val == puzzle [x] [col] || val == puzzle [row] [x])
		return false;
	for (int x = 0 ; x < 3 ; x++)
	    for (int y = 0 ; y < 3 ; y++)
		if (val == puzzle [(row / 3) * 3 + x] [(col / 3) * 3 + y]) //See if I can use this for level 1
		    return false;
	return true;
    }


    /*
	Method purpose: Asks the user for the level in which he/she wants to see the high scores for, and then reads the specific elements in the highScoreData[] array and displays them for the user in a table so he/she can view the high scores for that level. The user then has the option to reset the high scores for that level specifically, or return to the main menu.
	Loop purpose: The first for-loop (x) in this method is used to control the amount of times the elements of highScoreData[] array are being manipulated via the StringTokenizer class and displayed.
		      The while loop in this method is used to indefinitely get user input until he/she does not type 'R' or 'r' to reset the high scores.
		      The second for-loop (x) is used to reset all elements that are affected by the chosen level of the user back to nothing thereby deleting the previous record for that high score for that level in Sudoku.
	Conditional statements: The first if statement is used to exit this method immediately if the user does not specify the level that he/she wishes to see the high scores for.
				The second if statement is used to check if the specific element being manipulated by the StringTokenizer class has a score as its next token so it can display that record. If there isn't, the program will skip that element and move on to the next.
				The third if statement is used to check if the user entered a character that is not equal to 'R' or 'r' so that the program can return to the main menu immediately. If this statement is false, the program will ask the user for confirmation that he/she would like to reset the high scores for that specific level and then reset them.
				The fourth if statement is used to check if the user chose the yes option in the confirmation JOptionPane message in regards to resetting the hihg scores for the user chosen level. If this statement is true, a for-loop is used to change all the affected elements to a value of nothing which resets the records.
	Local variable dictionary:
	    Name        Type        Description
	    selection   object      This variable stores the chosen string that the user selected from the list provided in the new string array in JOptionPane.
	    x           int         This loop variable is used in both for-loops as a means to control the amount of elements are manipulated and altered for either display the high scores or resetting them.
	    st          reference   This variable points to the StringTokenizer class so the methods inside the StringTokenizer class can be accessed. The String being manipulated is each of the elements inside the highScoreData[] String array.
	    msg         int         This variable stores the resulting value of the JOptionPane dialog. It is used in if statements to control program flow based on user input.
	    e           reference   This variable points to the BufferedReader class to prevent the program from crashing from any errors caused by the file IO stream.
    */
    public void highScores ()
    {
	Object selection = JOptionPane.showInputDialog (null, "Choose a level to show high scores:", "Level Selection", JOptionPane.QUESTION_MESSAGE, null, new String[] {"Easy", "Hard"}, "Easy");
	if (selection == null)
	    return;
	level = (selection.equals ("Easy")) ? 1 : 2;
	title ();
	c.setCursor (3, 30);
	c.print (selection + " Level (" + (level * 6 - 3) + "x" + (level * 6 - 3) + " Grid)");
	c.setCursor (5, 4);
	c.print ("Rank:");
	c.setCursor (5, 19);
	c.print ("Name:");
	c.setCursor (5, 58);
	c.print ("Score:");
	StringTokenizer st;
	for (int x = 7 ; x < 17 ; x++)
	{
	    st = new StringTokenizer (highScoreData [10 * level - 16 + x]);
	    c.setCursor (x, 4);
	    c.print (x - 6);
	    c.setCursor (x, 58);
	    if (st.hasMoreTokens ())
	    {
		c.print (Integer.parseInt (st.nextToken ()));
		c.setCursor (x, 19);
		c.print (st.nextToken ());
	    }
	}
	c.setCursor (18, 1);
	c.print ("Press \"R\" to reset high scores for this level,\nany other key to return to main menu...");
	while (true)
	{
	    choice = c.getChar ();
	    if (choice != 'R' && choice != 'r')
		return;
	    int msg = JOptionPane.showConfirmDialog (null, "Are you sure you want to reset high scores for this level?", "Reset High Scores", JOptionPane.YES_NO_OPTION);
	    if (msg == JOptionPane.YES_OPTION)
	    {
		for (int x = 10 * level - 9 ; x < 10 * level + 1 ; x++)
		    highScoreData [x] = "";
		break;
	    }
	}
    }


    /*
	Method purpose: Writes the records inside the highScoreData[] array into the HighScores.lior file and displays one final message to the user thanking them for playing and letting them know that the game was programmed by George Lim before closing the program.
	Loop purpose: The for-loop (x) runs to make print each element in the highScoreData[] array onto the HighScores.lior file.
	Conditional statements: The try/catch statement is used to catch any IOException that could be caused from using the PrintWriter class.
				The first if statement is used to check if the element in highScoreData[x] is equal to the value of nothing. If it is, then it prints a blank line using the PrintWriter class, otherwise it prints the value of highScoreData[x] into file.
	Local variable dictionary:
	    Name    Type        Description
	    out     reference   This variable points to the PrintWriter class so the methods inside the PrintWriter class can be accessed. The file being read by this class is the HighScores.lior extension file.
	    x       int         This loop variable is used to control the amount of iterations the program will use to print the x amount of lines onto the HighScores.lior file.
    */
    public void goodBye ()
    {
	try
	{
	    PrintWriter out = new PrintWriter (new FileWriter ("HighScores.lior"));
	    for (int x = 0 ; x < 21 ; x++)
		if (highScoreData [x].equals (""))
		    out.println ();
		else
		    out.println (highScoreData [x]);
	    out.close ();
	}
	catch (IOException e)
	{
	}
	title ();
	c.println ("Thank you for playing Sudoku which was programmed by George Lim!");
	c.println ("Special shout-out to my best-est friend in the world, Lior <3");
	pauseProgram ("close program");
	c.close ();
    }


    /*
	Main method purpose: Creates the Object of Sudoku class and controls the order of method execution inside the Sudoku class.
	Loop purpose: The do/while loop in this method is used to make mainMenu() method run infinitely thereby allowing the user to use the program endlessly until he/she decides to select the option to exit the program.
	Conditional Statements: In this method, the conditional if statements are used to control the program method executions based on the user input in the mainMenu() method. If the value of choice is equal to one, the program will execute the instructions() method. If the value is two, the start() method will be called, if the value is three, the highScores() method is called, otherwise the do/while loop breaks.
	Local variable dictionary:
	    Name    Type        Description
	    s       reference   This variable points to the Sudoku class so the methods inside the Sudoku class can be accessed.
    */
    public static void main (String[] args)
    {
	Sudoku s = new Sudoku ();
	s.splashScreen ();
	s.readScores ();
	do
	{
	    s.mainMenu ();
	    if (s.choice == '1')
		s.instructions ();
	    else if (s.choice == '2')
		s.start ();
	    else
		if (s.choice == '3')
		    s.highScores ();
	}
	while (s.choice != '4');
	s.goodBye ();
    }
}
