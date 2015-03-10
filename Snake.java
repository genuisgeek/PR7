//name: Wei Han ; Thomas 
//id: AXXXXXXXX; AXXXXXXXX
//email: lee@ucsd.edu; huo@ucsd.edu

public class Snake
{
  private Coord[] snakeBody;
  private int snakeSize;

  public Snake(Coord start, int maxSize) //from "the snake as a llist of.. paragraph
  {
    snakeBody = new Coord[maxSize]; //the body should be an array of coordinates?
    snakeBody[0] = new Coord(start); //snake head fills 'start' coordinate

    snakeSize = 1; //snakeSize = 1 cause of head
  }

  public boolean move(int n, int m)
  {
    if (n > 1 || n < -1)
    {
      return false; //can only move 1 space left or right
    }
    if (m > 1 || m < -1)
    {
      return false; //can only move 1 space up or down
    }
    if (n != 0 && m != 0)
    {
      return false; //says valid mvoement cannot be diagonal
    }
    if (n == 0 && m == 0)
    {
      return false; //says valid movement has to actually move lol
    }
    //FIX FIX FIX FIX FIX V
    Coord moveHead = new Coord(getHeadCoord().getX() + n, getHeadCoord().getY() + m);//is there a getter for x and y in Coord? 
    
    if (moveHead.equals(snakeBody[1]))
    {
      return false; //check if this stops from going backwards?
      //should be cause moving makes head index 0; so behind it is index 1?
    }

    for (int i = snakeSize - 1; i > 0; --i)
    {
      snakeBody[i] = snakeBody[i - 1]; //snake body takes value of the one after it
      //e.g. index 99 would become index 98 if snake moves right?
    }

    snakeBody[0] = moveHead;
    return true;
  }

  public boolean grow(int n, int m)
  {
    Coord tail = getTailCoord(); //gets old tail coordinates and saves them in temp
	if ( !this.move(n, m)) 
	{
		return false;
	}
    if (snakeSize < snakeBody.length) //if snakeSize is less than max Size
    {
      snakeBody[snakeSize] = tail; //tail is the same head increases
	  snakeSize += 1;
      return true;
    }
    return false;
  }

  public Coord getHeadCoord()
  {
    return snakeBody[0];
  }

  public Coord getTailCoord()
  {
    return snakeBody[snakeSize - 1];
  }
  
  public boolean checkIntersect(Coord c)
  {
	for (int i = 0; i < snakeBody.length; ++i)
	{
		if (c == snakeBody[i])
		{
			return true;
		}
	}
	return false;
  }
  
  public Coord [] getWholeSnake()
  {
	Coord[] copySnakeBody = new Coord[snakeBody.length];
	for (int i = 0; i < snakeBody.length; ++i)
	{
		copySnakeBody[i] = snakeBody[i];
	}
	return copySnakeBody;
  }
  
  public String toString()
  {
	String printSnakeBody = "";
	for (int i = 0; i < snakeBody.length; ++i) {
		printSnakeBody += snakeBody[i] + ", ";
	}
	return printSnakeBody;
  }
}
