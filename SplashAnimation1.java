/*
    George Lim
    SplashAnimation1.java
    01/06/2013 v3.0
    Program purpose: Draws an cool simple Sudoku game animation as part of the complete splash screen for the Sudoku.java program which is programmed by George Lim.
    Instance variable dictionary:
	Name            Type                Description
	c               reference           Parameter pass from the Sudoku.java game that points to the Console class so the output window can be made.
*/
import java.awt.*;
import hsa.Console;
public class SplashAnimation1 implements Runnable
{
    private Console c;
    //  Class constructor purpose: Assigns c to the console object that was created in Sudoku.java as a parameter pass.
    public SplashAnimation1 (Console con)
    {
	c = con;
    }

    /*
	Method purpose: Draws the Sudoku animation to be seen by the user as the splash screen for the Sudoku.java game programmed by George Lim.
	Loop purpose: The first and second for-loops (x) are used to draw the Sudoku grid graphics.
		      The third for-loop (x) is used to draw the fade-in effect for many of the solved numbers on the Sudoku animation.
		      The fourth, fifth, sixth, seventh, and eighth for-loops (x) are used to draw the fade-in effect for the last five numbers to be added to Sudoku board before completion.
		      The ninth for-loop is used to draw the "Solved" text in a fade-in animation over the Sudoku board.
	Conditional Statement: In this method, the conditional try/catch statements are all used to execute the Thread.sleep() command so the animation can be delayed so the user can actually view it properly.
	Local variable dictionary:
	    Name    Type        Description
	    x       int         This loop variable is used throughout the method for drawing the Sudoku grid lines and all the fade-in animations for the numbers and text on the Sudoku board by modifying the colour of the text each time.
    */
    private void draw ()
    {
	for (int x = 0 ; x < 361 ; x += 120)
	{
	    c.fillRect (140, 25 + x, 363, 3);
	    c.fillRect (140 + x, 25, 3, 363);
	}
	for (int x = 0 ; x < 281 ; x += 40)
	{
	    c.fillRect (140, 65 + x, 363, 1);
	    c.fillRect (180 + x, 25, 1, 363);
	}
	c.setFont (new Font ("Sans Serif", Font.PLAIN, 32));
	c.drawString ("Sudoku", 268, 420);
	c.setColor (Color.gray);
	c.drawString ("1", 311, 57);
	c.drawString ("8", 151, 97);
	c.drawString ("9", 271, 97);
	c.drawString ("5", 191, 137);
	c.drawString ("2", 231, 137);
	c.drawString ("8", 311, 137);
	c.drawString ("3", 351, 137);
	c.drawString ("7", 391, 137);
	c.drawString ("4", 471, 137);
	c.drawString ("5", 151, 178);
	c.drawString ("6", 231, 178);
	c.drawString ("1", 271, 178);
	c.drawString ("2", 391, 178);
	c.drawString ("9", 471, 178);
	c.drawString ("7", 151, 258);
	c.drawString ("9", 231, 258);
	c.drawString ("4", 351, 258);
	c.drawString ("6", 391, 258);
	c.drawString ("8", 471, 258);
	c.drawString ("1", 151, 299);
	c.drawString ("7", 231, 299);
	c.drawString ("3", 271, 299);
	c.drawString ("4", 311, 299);
	c.drawString ("5", 391, 299);
	c.drawString ("9", 431, 299);
	c.drawString ("1", 351, 339);
	c.drawString ("7", 471, 339);
	c.drawString ("2", 311, 379);
	try
	{
	    Thread.sleep (1000);
	}
	catch (Exception e)
	{
	}
	c.setColor (Color.black);
	for (int x = 255 ; x > 0 ; x--)
	{
	    c.setColor (new Color (x, x, x));
	    c.drawString ("6", 151, 57);
	    c.drawString ("7", 191, 57);
	    c.drawString ("3", 231, 57);
	    c.drawString ("4", 271, 57);
	    c.drawString ("2", 351, 57);
	    c.drawString ("9", 391, 57);
	    c.drawString ("8", 431, 57);
	    c.drawString ("5", 471, 57);
	    c.drawString ("4", 191, 97);
	    c.drawString ("1", 231, 97);
	    c.drawString ("5", 311, 97);
	    c.drawString ("3", 391, 97);
	    c.drawString ("2", 431, 97);
	    c.drawString ("6", 471, 97);
	    c.drawString ("9", 151, 137);
	    c.drawString ("6", 271, 137);
	    c.drawString ("1", 431, 137);
	    c.drawString ("3", 191, 178);
	    c.drawString ("7", 311, 178);
	    c.drawString ("8", 351, 178);
	    c.drawString ("4", 431, 178);
	    c.drawString ("4", 151, 218);
	    c.drawString ("2", 191, 218);
	    c.drawString ("5", 271, 218);
	    c.drawString ("6", 311, 218);
	    c.drawString ("9", 351, 218);
	    c.drawString ("1", 391, 218);
	    c.drawString ("7", 431, 218);
	    c.drawString ("3", 471, 218);
	    c.drawString ("1", 191, 258);
	    c.drawString ("2", 271, 258);
	    c.drawString ("5", 431, 258);
	    c.drawString ("8", 191, 299);
	    c.drawString ("6", 351, 299);
	    c.drawString ("2", 471, 299);
	    c.drawString ("2", 151, 339);
	    c.drawString ("6", 191, 339);
	    c.drawString ("5", 231, 339);
	    c.drawString ("8", 271, 339);
	    c.drawString ("9", 311, 339);
	    c.drawString ("3", 431, 339);
	    c.drawString ("3", 151, 379);
	    c.drawString ("4", 231, 379);
	    c.drawString ("7", 271, 379);
	    c.drawString ("5", 351, 379);
	    c.drawString ("8", 391, 379);
	    c.drawString ("6", 431, 379);
	    c.drawString ("1", 471, 379);
	    try
	    {
		Thread.sleep (3);
	    }
	    catch (Exception e)
	    {
	    }
	}
	for (int x = 255 ; x > 0 ; x--)
	{
	    c.setColor (new Color (x, x, x));
	    c.drawString ("4", 391, 339);
	    try
	    {
		Thread.sleep (3);
	    }
	    catch (Exception e)
	    {
	    }
	}
	for (int x = 255 ; x > 0 ; x--)
	{
	    c.setColor (new Color (x, x, x));
	    c.drawString ("7", 351, 97);
	    try
	    {
		Thread.sleep (3);
	    }
	    catch (Exception e)
	    {
	    }
	}
	for (int x = 255 ; x > 0 ; x--)
	{
	    c.setColor (new Color (x, x, x));
	    c.drawString ("8", 231, 218);
	    try
	    {
		Thread.sleep (3);
	    }
	    catch (Exception e)
	    {
	    }
	}
	for (int x = 255 ; x > 0 ; x--)
	{
	    c.setColor (new Color (x, x, x));
	    c.drawString ("3", 311, 258);
	    try
	    {
		Thread.sleep (3);
	    }
	    catch (Exception e)
	    {
	    }
	}
	for (int x = 255 ; x > 0 ; x--)
	{
	    c.setColor (new Color (x, x, x));
	    c.drawString ("9", 191, 379);
	    try
	    {
		Thread.sleep (3);
	    }
	    catch (Exception e)
	    {
	    }
	}
	c.setFont (new Font ("Sans Serif", Font.PLAIN, 92));
	for (int x = 255 ; x > 0 ; x--)
	{
	    c.setColor (new Color (x, x, x));
	    c.drawString ("Solved", 183, 243);
	    c.setColor (new Color (180, x, x));
	    c.drawString ("Solved", 180, 240);
	    try
	    {
		Thread.sleep (3);
	    }
	    catch (Exception e)
	    {
	    }
	}
	try
	{
	    Thread.sleep (750);
	}
	catch (Exception e)
	{
	}
    }

    //  Method purpose: This method controls the order of execution for the methods in this runnable thread. It is called first as the thread is running.
    public void run ()
    {
	draw ();
    }
}
