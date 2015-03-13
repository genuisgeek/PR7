//name: Wei Han Lee; Thomas Kuo
//id: Axxxxxxxx; Axxxxxxxx
//email: 

import java.awt.*;
import javax.swing.*;
import java.lang.Thread;
import java.lang.Runnable;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.IOException;

public class GraphicsGrid
{
  
  
  public static void main(String[] a)
  {
    MyWindow window = new MyWindow();

    try
    {
      System.out.format("Hit Return to exit program");
      System.in.read();
    }
    catch (IOException e){}
    window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
    window.dispose();
  }
}
  class MyWindow extends JFrame
  {
    private Grid grid;
    int width, height, pixels;
	private GameGrid gGrid; 

    public MyWindow() // don't need two constructors 
    {
      super();
      Grid grid = new Grid();
      setSize(400, 400);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      add(grid);
      setVisible(true);
    }

    public MyWindow(int width, int height, int pixels)
    {
      super();
      Grid grid = new Grid(width, height, pixels);
	  gGrid = new GameGrid(width / pixels, height / pixels, grid);
      setSize(width, height);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      add(grid);
      setVisible(true);
    }
  }

  class Grid extends JPanel
  {
    int width, height, pixels;
    private ArrayList<Point> fillCells;

    public Grid()
    {
      fillCells = new ArrayList<Point>();
      width = 400;
      height = 400;
      pixels = 10;
    }

    public Grid(int width, int height, int pixels)
    {
      fillCells = new ArrayList<Point>();
      this.width = width;
      this.height = height;
      this.pixels = pixels;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
      super.paintComponent(g);
      
      //code for here when we finalize what color our snake right?
      //so everytime we move the head we repaint; or we make a new method for this?
      g.setColor(Color.BLACK);
      g.drawRect(0, 0 , width, height);

      for (int i = 0; i < width; i += pixels)
      {
        g.drawLine(i, 0, i, height);
      }

      for (int i = 0; i < height; i += pixels)
      {
        g.drawLine(0, i, width, i);
      }
    }

    public void fillCell(int x, int y)
    {
      fillCells.add(new Point(x, y));
      repaint();
    }

    public void clearCell(int idx)
    {
      fillCells.remove(idx);
      repaint();
    }
  }

