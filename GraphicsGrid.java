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
	public GameGrid gameGrid; 
	private ArrayList<Coord> fillCells = new ArrayList<Coord>();
	private SnakeMover snakeMove;
	private Coord direction = new Coord(0, 1);

    public GraphicsGrid(int width, int height, int pixels)
    {
		gameGrid = new GameGrid(height / pixels, width / pixels);
		gameGrid.drawGrid(); // creates the array as having '.' or white 
		paintGrid = new char[width / pixels][height / pixels];
		for ( int i = 0; i < width / pixels; ++i)
		{
			for (int j = 0; j < height / pixels; ++j)
			{
				paintGrid[i][j] = gameGrid.getCharGrid()[i][j];
				Coord arrayCoord = new Coord(i, j);
				fillCells.add(arrayCoord);
			}
		}
		snakeMove = new SnakeMover(gameGrid, direction);
		addKeyListener(snakeMove);
		setFocusable(true);
		requestFocusInWindow();
		this.width = width;
		this.height = height;
		this.pixels = pixels;
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
				g.setColor(Color.green);
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
		
		g.setColor(Color.BLACK);
		if ((width % pixels == 0) && (height % pixels == 0)) {
			g.drawRect(0, 0, width, height); // draws large rectangle
			for (int i = 0; i < width; i += pixels) { // draws vertical lines 
				g.drawLine(i, 0, i, height);
			}

			for (int i = 0; i < height; i += pixels) { // draws horizontal lines
				g.drawLine(0, i, width, i);
			}
		}
		
		else {
			int newWidth = 0;
			int newHeight = 0;
			if((width % pixels == 0) && (height % pixels != 0)) {
				newWidth = width;
				newHeight = (height / pixels) * pixels;
				g.drawRect(0, 0, newWidth, newHeight);
			}
			
			if((width % pixels != 0) && (pixels % pixels == 0)) {
				newWidth = (width - pixels) * pixels;
				newHeight = height;
				g.drawRect(0, 0, newWidth, newHeight);
			}
			
			if((width % pixels != 0) && (height % pixels != 0)) {
				newWidth = (width / pixels) * pixels;
				newHeight = (height / pixels) * pixels;
				g.drawRect(0, 0, newWidth, newHeight);
			}
			
			for (int i = 0; i < newWidth; i += pixels) { // draws vertical lines
				g.drawLine(i, 0, i, newHeight);
			}

			for (int i = 0; i < newHeight - 2; i += pixels) {  //draws horizontal lines 
				g.drawLine(0, i, newWidth, i);
			}
			
		}
    }

    public void fillCell()
    { 
		for ( int i = 0; i < width / pixels; ++i)
		{
			for (int j = 0; j < height / pixels; ++j)
			{
				paintGrid[i][j] = gameGrid.getCharGrid()[i][j];
			}
		}
		repaint();
    }

    public void clearCell(int idx)
    {
		repaint();
    }	
}
