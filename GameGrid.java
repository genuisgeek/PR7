/* Name: Thomas Kuo & Wei Han Lee
 * ID: 
 * email: 
 *
 */
 
 import java.awt.*;
 import java.util.Random;
 import java.lang.Object;
 
public class GameGrid
{
	private static int gridWidth;
	private static int gridHeight;
	private static int gridCell;
	private char emptyCell = '.';
	private char snakeHeadCell = 'H';
	private char snakeBodyCell = '#';
	private char obstacleCell = 'O';
	private Random obstacleRand;
	public Snake aSnake;
	private Coord initial;
	private Coord [] snakeCopy;
	private Coord head;
	private char [][] charGrid;
	
	
	public GameGrid(int M, int N) 
	{
		obstacleRand = new Random();
		gridWidth = N;
		gridHeight = M;
		initial = new Coord((gridWidth / 2), 0);
		aSnake = new Snake(initial, gridWidth * gridHeight);
		charGrid = new char [gridWidth][gridHeight];
	}
	
	public void drawGrid() // makes grid fill with . 
	{
		for (int i = 0; i < gridWidth; ++i)
		{
			for (int j = 0; j < gridHeight; ++j)
			{
				charGrid[i][j] = '.';
			}
		}
	}
	
	public boolean moveSnake(int n, int m) //n denotes left/ right , m denotes up/down
	{
		snakeCopy = aSnake.getWholeSnake(); // make a copy of the current snake
		if (!aSnake.move(n, m))
		{
			return false;
		}
		
		if(!checkRules())
		{
			System.out.println("broken rule");
			return false;
		}
		clearSnake(snakeCopy);
		redrawSnake(aSnake.getWholeSnake());
		return true;
	}
	
	public boolean growSnake(int n, int m) //n denotes left/ right , m denotes up/down
	{
		snakeCopy = aSnake.getWholeSnake();
		if (!aSnake.grow(n, m))
		{
			return false;
		}
		
		if(!checkRules())
		{
			System.out.println("broken rule");
			return false;
		}
		clearSnake(snakeCopy);
		redrawSnake(aSnake.getWholeSnake());
		return true;
	}
	
	private void clearSnake(Coord [] c) 
	{	
		try {
			int check = 0;
			for (int i = 0; i < c.length; ++i)
			{
				// System.out.println("It should be clearing this coordinate: " + c[i].getX() + " " + c[i].getY());
				charGrid[c[i].getX()][c[i].getY()] = '.';
			}
		}
		
		catch (NullPointerException e) // c[n] is null because snake is not full yet
		{
			return;
		}
	}
	
	private void redrawSnake(Coord [] c)
	{
		try 
		{
			int check = 0;
			charGrid [c[0].getX()][c[0].getY()] = 'H'; // H head is definitely moving
			for (int i = 1; i < c.length; ++i)
			{
				// System.out.println("It is changing this coordinate right now: " + c[i].getX() + " " + c[i].getY());
				// System.out.print("How many times is this going in?");
				// check++;
				// System.out.println(check);
				charGrid [c[i].getX()][c[i].getY()] = '#';
			}
		}
		
		catch (NullPointerException e)
		{
			return;
		}
	}
	
	// adds random obstacles into the grid 
	public void addObstacle()
	{
		int x, y;
		boolean loopRandom = true;
		while(loopRandom)
		{
			x = obstacleRand.nextInt(gridWidth);
			y = obstacleRand.nextInt(gridHeight);
			if ( charGrid[x][y] == '.')
			{
				charGrid[x][y] = 'O';
				loopRandom = false; // only let it add obstacle once
			}
		}
	}
	
	// check within grid, check no intersection, check obstacle
	private boolean checkRules()
	{
		// check within grid
		// checks the y-edge
		head = new Coord(aSnake.getHeadCoord());
		if (head.getX() >= gridWidth || head.getX() < 0)
		{
			// System.out.println("hit y edge");
			return false;
		}
		
		// checks the X-edge
		if (head.getY() >= gridHeight || head.getY() < 0)
		{
			// System.out.println("hit x-edge");
			return false;
		}
		
		//check if it intersects with itself
		if (aSnake.checkIntersect(head))
		{
			return false;
		}
		
		// check if hit obstacle 
		if (charGrid[head.getX()][head.getY()] == 'O')
		{
			System.out.println("HIT OBSTACLE");
			return false;
		}
		return true;
	}
	
	public boolean printFirstSnake()
	{
		snakeCopy = aSnake.getWholeSnake();
		if(!checkRules())
		{
			System.out.println("broken rule");
			return false;
		}
		clearSnake(snakeCopy);
		redrawSnake(aSnake.getWholeSnake());
		return true;
	}
	public char [][] getCharGrid() // returns charGrid 
	{
		return charGrid;
	}
	
	public void gridPrint() // method to print simple asci snake grid
	{
		for (int i = 0; i < gridWidth; ++i)
		{
			for (int j = 0; j < gridHeight; ++j)
			{
				System.out.print(charGrid[i][j] + " ");
			}
			System.out.println();
		}
	}
}