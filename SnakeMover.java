/*Name: Wei Han Lee & Thomas Kuo
 * ID: 
 * Email: 
 *
 */
 
import java.lang.Runnable;
import java.util.concurrent.TimeUnit;
import java.awt.*;
import java.awt.event.*;
import java.lang.Thread;

public class SnakeMover extends Thread implements KeyListener
{
	private static final int TIME = 50;
	private static final int MIN = 1;
	private static final int MAX = 20;
	private int ticks = 0;
	private GameGrid gameGrid;
	private Coord initialDirection;
	private Coord nextDirection;
	private int newTick = 0; 
	private int tick = MAX;
	private boolean move = true;
	// private SnakeGame;
	
	public SnakeMover(GameGrid gg, Coord c)
	{
		initialDirection  = c;
		gameGrid = gg;
		// snakeGame = sg;
		start();
	}
	
	public void run() 
	{
		while(move)
		{
			try{
				newTick++;
				if (newTick >= tick)
				{
					if(move)
					{
						move = gameGrid.moveSnake(initialDirection.getX(), initialDirection.getY());
						newTick = 0;
					}
				}
				Thread.sleep(50);
			}
			catch(InterruptedException e){}
		}
	}
	
	public void stopMove()
	{
		move = false; 
	}

	public void keyPressed(KeyEvent k){}
	
	public void keyReleased(KeyEvent k){}
	
	public void keyTyped(KeyEvent k)
	{ 
		char key = k.getKeyChar();
		if (key == 'j') // left grows -1
		{
			if (initialDirection.getX() == 0)
			{
				nextDirection = new Coord(-1 * initialDirection.getY(), 0);
				System.out.println(nextDirection);
			}
			
			else
			{	
				nextDirection = new Coord(0, 1 * initialDirection.getX());
				System.out.println(nextDirection);
			}
		}
		
		if (key == 'l') // right grows 1
		{
			if (initialDirection.getX() == 0)
			{
				nextDirection = new Coord(1 * initialDirection.getY(), 0);
				System.out.println(nextDirection);
			}
			
			else
			{	
				nextDirection = new Coord(0, -1 * initialDirection.getX());
				System.out.println(nextDirection);
			}
		}
		
		move = gameGrid.growSnake(nextDirection.getX(), nextDirection.getY());
		initialDirection = nextDirection;
		newTick = 0;
		/*
		if (move)
		{
			snakeGame.goodMove();
		}
		*/
	}
}