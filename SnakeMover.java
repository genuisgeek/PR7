/*Name: Wei Han Lee & Thomas Kuo
 * ID: 
 * Email: 
 *
 */
 
import java.lang.Runnable;
import java.util.concurrent.TimeUnit;
import java.awt.*;


public class SnakeMover implements KeyListener, Runnable
{
	private static int timeInterval;
	private GameGrid grid;
	private Coord initial;
	private Coord nextCoord;
	
	public SnakeMover()
	{
	}
	
	public void run()
	{
		
	}

	// private static Snake aSnake = new Snake
	public void keyPressed(KeyEvent e){}
	
	public void keyReleased(KeyEvent e){}
	
	public void keyTyped(KeyEvent e)
	{
		char key = e.getKeyChar();
		if (key.equals('j')) // left grows -1
		{
			newCoord = new Coord(initial.getX() * -1, 0);
		}
		
		if (key.equals('l')) // right grows 1
		{
		
		}
		
		if (key.equals('k')) // forward  move 1
		{
		
		}
	}
}