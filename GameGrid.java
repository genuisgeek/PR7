/* Name: Thomas Kuo & Wei Han Lee
 * ID: 
 * email: 
 *
 */


 import java.awt.*;
 import java.util.Random;

public class GameGrid
{
	private static final int MAX_SIZE = 15;
	private static int gridWidth = 400;
	private static int gridHeight = 400;
	private static int gridCell = 10;
	// private static int cellWidth = 0;
	// private static int cellHeight = 0;
	private char emptyCell = '.';
	private char snakeHeadCell = 'H';
	private char snakeBodyCell = '#';
	private char obstacleCell = 'O';
	private Random obstacleRand;
	public Snake aSnake;
	private Coord initial;
	private Coord [] snakeCopy;
	private Coord head;
	public char [][] charGrid;
	
	
	public GameGrid(int N, int M, int P)
	{
		obstacleRand = new Random();
		gridWidth = N;
		// cellWidth = N / P;
		// cellHeight = M / P;
		gridHeight = M;
		gridCell = P;
		initial = new Coord(gridWidth / 2, gridHeight / 2);
		aSnake = new Snake(initial, gridWidth * gridHeight);
		charGrid = new char [gridWidth][gridHeight];
	}
	
	public void drawGrid()
	{
		for (int i = 0; i < gridWidth; ++i)
		{
			for (int j = 0; j < gridHeight; ++j)
			{
				charGrid [i][j] = emptyCell;
			}
		}
	}
	
	public boolean moveSnake(int m, int n) 
	{
		snakeCopy = aSnake.getWholeSnake(); // make a copy of the current snake
		if (!aSnake.move(n, m))
		{
			return false;
		}
		
		if(!checkRules())
		{
			return false;
		}
		clearSnake(snakeCopy);
		redrawSnake(snakeCopy);
		return true;
	}
	
	public boolean growSnake(int m, int n)
	{
		snakeCopy = aSnake.getWholeSnake();
		if (!aSnake.grow(n, m))
		{
			return false;
		}
		
		if(!checkRules())
		{
			return false;
		}
		clearSnake(snakeCopy);
		redrawSnake(snakeCopy);
		return true;
	}
	
	private void clearSnake(Coord [] c) 
	{	
		try {
			for (int i = 0; i < c.length; ++i)
			{
				charGrid[c[i].getX()][c[i].getY()] = '.';
			}
		}
		
		catch (NullPointerException e) 
		{
			return;
		}
	}
	
	public void redrawSnake(Coord [] c)
	{
		try {
			charGrid[c[0].getX()][c[0].getY()] = 'H';
			for (int i = 1; i < c.length; ++i)
			{
				System.out.println("bitch");
				charGrid [c[i].getX()][c[i].getY()] = '#';
			}
		}
		
		catch (NullPointerException e)
		{
			return;
		}
	}
	
	public void addObstacle()
	{

	}
	
	// check within grid, check no intersection, check obstacle
	private boolean checkRules()
	{
		// check within grid
		head = new Coord(aSnake.getHeadCoord());
		if (head.getX() >= gridWidth || head.getX() < 0)
		{
			return false;
		}
		
		if (head.getY() >= gridHeight || head.getY() < 0)
		{
			return false;
		}
		
		//check if it intersects 
		if (aSnake.checkIntersect(head))
		{
			return false;
		}
		return true;
	}
	
	public static void main (String args[])
	{
		
	}
}