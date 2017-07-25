/*
    George Lim
    SplashAnimation2.java
    01/06/2013 v3.0
    Program purpose: Draws an cool simple Sudoku game animation as part of the splash screen for the Sudoku.java program which is programmed by George Lim.
    Instance variable dictionary:
	Name            Type                Description
	c               reference           Parameter pass from the Sudoku.java game that points to the Console class so the output window can be made.
*/
import java.awt.*;
import hsa.Console;
public class SplashAnimation2 implements Runnable
{
    private Console c;
    //  Class constructor purpose: Assigns c to the console object that was created in Sudoku.java as a parameter pass.
    public SplashAnimation2 (Console con)
    {
	c = con;
    }

    /*
	Method purpose: Draws the Sudoku animation to be seen by the user as the splash screen for the Sudoku.java game programmed by George Lim.
	Loop purpose: The first for-loop (x) and two nested for-loops (y) inside are used to animate the whole Sudoku board moving up to the top of the screen so the title of the Sudoku board animation becomes the title for the Sudoku.java program. The two nested for-loops are used to redraw the Sudoku grid graphics.
	Conditional Statement: In this method, the conditional try/catch statements are all used to execute the Thread.sleep() command so the animation can be delayed so the user can actually view it properly.
	Local variable dictionary:
	    Name    Type        Description
	    x       int         This loop variable is used for the animation for moving the whole board up to the top of the Screen.
	    y       int         This loop variable is used to draw the Sudoku grid lines while the Sudoku board itself is being animated upward towards the end of the animation.
	    small   reference   This variable points to the Font class and alters the font for text drawn with the drawString() method in the hsa.Console class so it is sans serif and 32 point font.
	    large   reference   This variable points to the Font class and alters the font for text drawn with the drawString() method in the hsa.Console class so it is sans serif and 92 point font.
    */
    private void draw ()
    {
	Font small = new Font ("Sans Serif", Font.PLAIN, 32),large = new Font ("Sans Serif", Font.PLAIN, 92);
	c.setFont (large);
	for (int x = 0 ; x < 391 ; x++)
	{
	    c.setColor (Color.white);
	    c.fillRect (140, 25 - x, 363, 400);
	    c.setColor (Color.black);
	    for (int y = 0 ; y < 361 ; y += 120)
	    {
		c.fillRect (140, 25 - x + y, 363, 3);
		c.fillRect (140 + y, 25 - x, 3, 363);
	    }
	    for (int y = 0 ; y < 281 ; y += 40)
	    {
		c.fillRect (140, 65 - x + y, 363, 1);
		c.fillRect (180 + y, 25 - x, 1, 363);
	    }
	    c.setFont (small);
	    c.setColor (Color.gray);
	    c.drawString ("1", 311, 57 - x);
	    c.drawString ("8", 151, 97 - x);
	    c.drawString ("9", 271, 97 - x);
	    c.drawString ("5", 191, 137 - x);
	    c.drawString ("2", 231, 137 - x);
	    c.drawString ("8", 311, 137 - x);
	    c.drawString ("3", 351, 137 - x);
	    c.drawString ("7", 391, 137 - x);
	    c.drawString ("4", 471, 137 - x);
	    c.drawString ("5", 151, 178 - x);
	    c.drawString ("6", 231, 178 - x);
	    c.drawString ("1", 271, 178 - x);
	    c.drawString ("2", 391, 178 - x);
	    c.drawString ("9", 471, 178 - x);
	    c.drawString ("7", 151, 258 - x);
	    c.drawString ("9", 231, 258 - x);
	    c.drawString ("4", 351, 258 - x);
	    c.drawString ("6", 391, 258 - x);
	    c.drawString ("8", 471, 258 - x);
	    c.drawString ("1", 151, 299 - x);
	    c.drawString ("7", 231, 299 - x);
	    c.drawString ("3", 271, 299 - x);
	    c.drawString ("4", 311, 299 - x);
	    c.drawString ("5", 391, 299 - x);
	    c.drawString ("9", 431, 299 - x);
	    c.drawString ("1", 351, 339 - x);
	    c.drawString ("7", 471, 339 - x);
	    c.drawString ("2", 311, 379 - x);
	    c.setColor (Color.black);
	    c.drawString ("6", 151, 57 - x);
	    c.drawString ("7", 191, 57 - x);
	    c.drawString ("3", 231, 57 - x);
	    c.drawString ("4", 271, 57 - x);
	    c.drawString ("2", 351, 57 - x);
	    c.drawString ("9", 391, 57 - x);
	    c.drawString ("8", 431, 57 - x);
	    c.drawString ("5", 471, 57 - x);
	    c.drawString ("4", 191, 97 - x);
	    c.drawString ("1", 231, 97 - x);
	    c.drawString ("5", 311, 97 - x);
	    c.drawString ("7", 351, 97 - x);
	    c.drawString ("3", 391, 97 - x);
	    c.drawString ("2", 431, 97 - x);
	    c.drawString ("6", 471, 97 - x);
	    c.drawString ("9", 151, 137 - x);
	    c.drawString ("6", 271, 137 - x);
	    c.drawString ("1", 431, 137 - x);
	    c.drawString ("3", 191, 178 - x);
	    c.drawString ("7", 311, 178 - x);
	    c.drawString ("8", 351, 178 - x);
	    c.drawString ("4", 431, 178 - x);
	    c.drawString ("4", 151, 218 - x);
	    c.drawString ("2", 191, 218 - x);
	    c.drawString ("8", 231, 218 - x);
	    c.drawString ("5", 271, 218 - x);
	    c.drawString ("6", 311, 218 - x);
	    c.drawString ("9", 351, 218 - x);
	    c.drawString ("1", 391, 218 - x);
	    c.drawString ("7", 431, 218 - x);
	    c.drawString ("3", 471, 218 - x);
	    c.drawString ("1", 191, 258 - x);
	    c.drawString ("2", 271, 258 - x);
	    c.drawString ("3", 311, 258 - x);
	    c.drawString ("5", 431, 258 - x);
	    c.drawString ("8", 191, 299 - x);
	    c.drawString ("6", 351, 299 - x);
	    c.drawString ("2", 471, 299 - x);
	    c.drawString ("2", 151, 339 - x);
	    c.drawString ("6", 191, 339 - x);
	    c.drawString ("5", 231, 339 - x);
	    c.drawString ("8", 271, 339 - x);
	    c.drawString ("9", 311, 339 - x);
	    c.drawString ("4", 391, 339 - x);
	    c.drawString ("3", 431, 339 - x);
	    c.drawString ("3", 151, 379 - x);
	    c.drawString ("9", 191, 379 - x);
	    c.drawString ("4", 231, 379 - x);
	    c.drawString ("7", 271, 379 - x);
	    c.drawString ("5", 351, 379 - x);
	    c.drawString ("8", 391, 379 - x);
	    c.drawString ("6", 431, 379 - x);
	    c.drawString ("1", 471, 379 - x);
	    c.drawString ("Sudoku", 268, 420 - x);
	    c.setFont (large);
	    c.drawString ("Solved", 183, 243 - x);
	    c.setColor (new Color (180, 0, 0));
	    c.drawString ("Solved", 180, 240 - x);
	    try
	    {
		Thread.sleep (3);
	    }
	    catch (Exception e)
	    {
	    }
	}
	c.setColor (Color.black);
    }

    //  Method purpose: This method controls the order of execution for the methods in this runnable thread. It is called first as the thread is running.
    public void run ()
    {
	draw ();
    }
}


