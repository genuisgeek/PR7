import java.util.Arrays;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;

public class tester 
{
	// private static GameGrid bitch = new GameGrid(10, 30);
	private static Scanner usrKey = new Scanner(System.in);
	private static String input;
	private static GraphicsGrid fuckGraph;
	
	public static void main (String args[])
	{
		JFrame frame = new JFrame("Fuck");
		JPanel panel = new JPanel(new GridLayout(1,1));
		panel.add(fuckGraph.window.getGrid());
	
		/*
		// bitch.drawGrid();
		// bitch.gridPrint();
		while(true)
		{
			input = usrKey.next();
			
			if (input.equals("k")) // simple movement is a problem because it keeps the H value 
			{
				bitch.moveSnake(1, 0);
				bitch.gridPrint();
				bitch.addObstacle();
			}	
			
			if (input.equals("j"))
			{
				bitch.growSnake(0, -1);
				bitch.gridPrint();
				bitch.addObstacle();
			}
			
			if (input.equals("l"))
			{
				bitch.growSnake(0, 1);
				bitch.gridPrint();
				bitch.addObstacle();
			}
			
			if (input.equals("q"))
			{
				System.exit(0);
			}
		}
		
		
	
		bitch.moveSnake(1, 0); //going in 
		bitch.gridPrint();
		bitch.addObstacle();
		System.out.println();
		bitch.gridPrint();
	
		bitch.growSnake(1,0); // going in 
		bitch.gridPrint();
		bitch.addObstacle();
		bitch.growSnake(0,1);  // good goes down one 
		bitch.gridPrint();
		bitch.growSnake(1, 0); //invalid rule 
		bitch.gridPrint();
		
		
		Coord testCoord = new Coord(0,1);
		Snake bitchFucker = new Snake(testCoord, MAX_SIZE);
		bitchFucker.move(0,1);
		System.out.println(bitchFucker.getHeadCoord());
		bitchFucker.move(1,0);
		System.out.println(bitchFucker.getHeadCoord());
		bitchFucker.move(0,0); // invalid
		System.out.println(bitchFucker.getHeadCoord());
		bitchFucker.move(1,1); //invalid
		System.out.println(bitchFucker.getHeadCoord());
		bitchFucker.move(0,2); // invalid
		System.out.println(bitchFucker.getHeadCoord());
		bitchFucker.move(2,0); //invlaid
		System.out.println(bitchFucker.getHeadCoord());
		bitchFucker.move(2,2); // invalid
		System.out.println(bitchFucker.getHeadCoord());
		bitchFucker.grow(0, 1);
		System.out.println(bitchFucker.getHeadCoord());
		System.out.println(bitchFucker.getTailCoord());
		System.out.println(bitchFucker.toString());
		*/
	}
}