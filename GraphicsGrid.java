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
import java.util.Scanner;

public class GraphicsGrid extends JPanel
{
	private static String input;
	private static Scanner usrKey = new Scanner(System.in);
	private int width, height, pixels;
    private char[][] paintGrid;
	private GameGrid gameGrid; 
	private ArrayList<Coord> fillCells = new ArrayList<Coord>();

	/*
    public GraphicsGrid()
    { 
		width = 400;
		height = 400;
		pixels = 10;
    }
	*/

    public GraphicsGrid(int width, int height, int pixels)
    {
		gameGrid = new GameGrid(width / pixels, height / pixels);
		gameGrid.drawGrid(); // creates the array as having '.' or white 
		paintGrid = new char[width / pixels][height / pixels];
		for ( int i = 0; i < width / pixels; ++i)
		{
			for (int j = 0; j < height / pixels; ++j)
			{
				paintGrid[i][j] = gameGrid.getCharGrid()[i][j];
			}
		}
		this.width = width;
		this.height = height;
		this.pixels = pixels;
    }
	
	public static void main(String[] a)
	{
		/*
		while(true)
		{
			input = usrKey.next();
			window.gameGrid.drawGrid();
				
			if (input.equals("k")) // simple movement is a problem because it keeps the H value 
			{
				window.gameGrid.moveSnake(1, 0);
				window.gameGrid.addObstacle();
				window.grid.repaint();
			}	
			
			if (input.equals("j"))
			{
				window.gameGrid.growSnake(0, -1);
				window.gameGrid.addObstacle();
				window.grid.repaint();
			}
			
			if (input.equals("l"))
			{
				window.gameGrid.growSnake(0, 1);
				window.gameGrid.addObstacle();
				window.grid.repaint();
			}
			
			if (input.equals("q"))
			{
				System.exit(0);
			}
			*/
	}

	
    @Override
    protected void paintComponent(Graphics g)
    {
		super.paintComponent(g);
		for (Coord fillCell : fillCells)
		{
			//empty grid space is white
			if (paintGrid[fillCell.getX()][fillCell.getY()] == '.')
			{
				int cellX = fillCell.getX() * pixels;
				int cellY = fillCell.getY() * pixels;
				g.setColor(Color.white);
				g.fillRect(cellX, cellY, pixels, pixels);
			}
			
			// tail of snake is grey
			if (paintGrid[fillCell.getX()][fillCell.getY()] == '#')
			{
				int cellX = fillCell.getX() * pixels;
				int cellY = fillCell.getY() * pixels;
				g.setColor(Color.pink);
				g.fillRect(cellX, cellY, pixels, pixels);
			}
			
			// obstacle is black
			if (paintGrid[fillCell.getX()][fillCell.getY()] == 'O')
			{
				int cellX = fillCell.getX() * pixels;
				int cellY = fillCell.getY() * pixels;
				g.setColor(Color.black);
				g.fillRect(cellX, cellY, pixels, pixels);
			}
			
			// head is red 
			if (paintGrid[fillCell.getX()][fillCell.getY()] == 'H')
			{
				int cellX = fillCell.getX() * pixels;
				int cellY = fillCell.getY() * pixels;
				g.setColor(Color.red);
				g.fillRect(cellX, cellY, pixels, pixels);
			}
		}
		
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
		fillCells.add(new Coord(x, y));
		repaint();
    }

    public void clearCell(int idx)
    {
		fillCells.remove(idx);
		repaint();
    }
}
