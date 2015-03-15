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
	private int ticks = 0, score = 0;
	private GameGrid gameGrid;
	private Coord initialDirection;
	private Coord nextDirection;
	private int newTick = 0; 
	private int tick = MAX;
	private int obCounter = 0;
	private boolean move = true;
	private tester snakeGame;
	
	public SnakeMover(GameGrid gg, Coord c, tester sg)
	{
		initialDirection  = c;
		gameGrid = gg;
		snakeGame = sg;
		start();
	}

  public void setSpeed(int tickspeed)
  {
    if (tickspeed < MIN)
    {
      tickspeed = MIN;
    }
    if (tickspeed > MAX)
    {
      tickspeed = MAX;
    }
    tick = tickspeed;
  }
	
	public synchronized void run() 
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
						if (move)
						{
							snakeGame.moveSnake();
						}
						newTick = 0;
					}
				}
				Thread.sleep(50);
			}
			catch(InterruptedException e){}
		}
		snakeGame.death();	
		this.stopMove();
	}
	// we don't uppdate the score inside mover
  public int getScore() //get score??
  {
    return score;
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
		if (move)
		{
			if (key == 'j') // left grows 1
			{
				if (initialDirection.getX() == 0)
				{
					nextDirection = new Coord(-1 * initialDirection.getY(), 0);
				}
				
				else
				{	
					nextDirection = new Coord(0, 1 * initialDirection.getX());
				}
				
				move = gameGrid.growSnake(nextDirection.getX(), nextDirection.getY());
				initialDirection = nextDirection;
				newTick = 0;
				if (move)
				{
					snakeGame.gameSession();
				}
			}
			
			if (key == 'l') // right grows 1
			{
				if (initialDirection.getX() == 0)
				{
					nextDirection = new Coord(1 * initialDirection.getY(), 0);
				}
				
				else
				{	
					nextDirection = new Coord(0, -1 * initialDirection.getX());
				}
				
				move = gameGrid.growSnake(nextDirection.getX(), nextDirection.getY());
				initialDirection = nextDirection;
				newTick = 0;
				if (move)
				{
					snakeGame.gameSession();
				}
			}
		}
	}
}
