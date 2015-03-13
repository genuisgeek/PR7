import java.util.Arrays;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;

public class tester 
{
	// private static GameGrid bitch = new GameGrid(10, 30);
	private static Scanner usrKey = new Scanner(System.in);
	private static String input;
	private static GraphicsGrid graphGrid;
	// private static GameGrid gameGrid;
	
	public tester()
	{
		design();
	}
	public void design()
	{
		JFrame frame = new JFrame();
		graphGrid = new GraphicsGrid(233, 400, 13);
		graphGrid.fillCell();
		frame.setSize(400, 400);
		frame.add(graphGrid, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main (String args[])
	{
		new tester();		
		
		
		graphGrid.gameGrid.drawGrid();
		// bitch.gridPrint();
		while(true)
		{
			input = usrKey.next();
			
			if (input.equals("k")) // simple movement is a problem because it keeps the H value 
			{
				graphGrid.gameGrid.moveSnake(0, 1);
				graphGrid.gameGrid.addObstacle();
				graphGrid.fillCell();
			}	
			
			if (input.equals("j"))
			{
				graphGrid.gameGrid.growSnake(-1, 0);
				graphGrid.gameGrid.addObstacle();
				graphGrid.fillCell();
			}
			
			if (input.equals("l"))
			{
				graphGrid.gameGrid.growSnake(1, 0);
				graphGrid.gameGrid.addObstacle();
				graphGrid.fillCell();
			}
			
			if (input.equals("q"))
			{
				System.exit(0);
			}
		}
		
		
		/*
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