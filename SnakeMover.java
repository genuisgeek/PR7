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
	private Coord initialCoord;
	private Coord nextCoord;
	private int newTick = 0; 
	private int tick = MAX;
	private boolean move = true;
	// private SnakeGame;
	
	public SnakeMover(GameGrid gg, Coord c)
	{
		initialCoord  = c;
		gameGrid = gg;
		// snakeGame = sg;
		// start();
	}
	
	public void run() 
	{
	
		while(move)
		{
			move = gameGrid.moveSnake(initialCoord.getX(), initialCoord.getY());
			if(move)
			{
				gameGrid.addObstacle();
			}
			try {
				Thread.sleep(150);
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
			if (initialCoord.getX() == 0)
			{
				nextCoord = new Coord(-1 * initialCoord.getX(), 0);
				System.out.println(nextCoord);
			}
			
			else
			{	
				nextCoord = new Coord(0, 1 * initialCoord.getX());
				System.out.println(nextCoord);
			}
		}
		
		if (key == 'l') // right grows 1
		{
			if (initialCoord.getX() == 0)
			{
				nextCoord = new Coord(-1 * initialCoord.getX(), 0);
				System.out.println(nextCoord);
			}
			
			else
			{	
				nextCoord = new Coord(0, 1 * initialCoord.getX());
				System.out.println(nextCoord);
			}
		}
		
		move = gameGrid.growSnake(nextCoord.getX(), nextCoord.getY());
		initialCoord = nextCoord;
		ticks = 0;
		/*
		if (move)
		{
			snakeGame.goodMove();
		}
		*/
	}
}