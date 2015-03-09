import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.io.IOException;
import java.lang.Thread;
import java.util.concurrent.TimeUnit;
import java.awt.event.*;

public class GraphicsGrid 
{
	public static void main(String[] args) {
		int i; 
		int gridArray[] = new int[3];
		int width = 0;
		int height = 0;
		int pixel = 0;
		MyWindow window = null;
		String usageStatement = "Usage: CoreControl [ width height pixels ]";
		
		try {
			if (args.length == 0) {
				width = 400;
				height = 400;
				pixel = 10;
				window = new MyWindow(width, height, pixel);
			}
			
			else if(args.length == 3) {
				for (i = 0; i < args.length; ++i) {
					int changeArgString = Integer.parseInt(args[i]);
					gridArray[i] = changeArgString;
				}
				
				if (gridArray[0] >= gridArray[2] && gridArray[1] >= gridArray[2]) {
					if (gridArray[0] > 0 && gridArray[1] > 0) {
						if (gridArray[2] > 0) {
							width = gridArray[0];
							height = gridArray[1];
							pixel = gridArray[2];
						}
						
						else {
							System.out.println(usageStatement);
							System.exit(0);
						}
					}
					
					else {
						System.out.println(usageStatement);
						System.exit(0);
					}
				}
				window = new MyWindow(width, height, pixel);
			}
			
			else {
				System.out.println(usageStatement);
				System.exit(0);
			}
		}
		
		catch (NumberFormatException e){
			System.out.println(usageStatement);
			System.exit(0);
		}
		
		try{
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
	private static Grid copyGrid;
	private static Point center;
	private int gridWidth, gridHeight, gridPixels;
	
	/**Constructor
	 * default constructor for MyWindow class 
	 * @param width - width of grid
	 * @param height - height of grid
	 * @param pixels - pixel size of each cell 
	 */
	public MyWindow(int width, int height, int pixels) 
	{
		super();
		gridWidth = width;
		gridHeight = height;
		gridPixels = pixels;
		Grid grid = new Grid(gridWidth, gridHeight, gridPixels);
		this.copyGrid = grid;
		setSize(gridWidth, gridHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(grid);
		setVisible(true);
		center = new Point ((gridWidth / gridPixels / 2), (gridHeight / gridPixels / 2));
	}
	
	public static Grid getGrid() 
	{
		return copyGrid;
	}

	public static Point getCenter()
	{
		Point newCenter = center;
		return newCenter;
	}
	
	public void run() {
	}
}

class Grid extends JPanel 
{
	private ArrayList<Point> fillCells; // keeps track of points 
	private int panelWidth, panelHeight, panelPixels;
	static int arrayIndex = 0;

	/**Constructor
	 * default constructor for Grid class 
	 * @param width - width of grid
	 * @param height - height of grid
	 * @param pixels - pixel size of each cell 
	 */
	public Grid(int width, int height, int pixels) {
		panelWidth = width;
		panelHeight = height;
		panelPixels = pixels;
		fillCells = new ArrayList<Point>();
	}

	@Override
	protected synchronized void paintComponent(Graphics g) { // initializing grid in main accesses all of paintComponent
		super.paintComponent(g);
		for (Point fillCell : fillCells) {
			int cellX = (fillCell.x * panelPixels);
			int cellY = (fillCell.y * panelPixels);
			g.setColor(Color.RED);
			g.fillRect(cellX, cellY, panelPixels, panelPixels);
		}
		
		g.setColor(Color.BLACK); // setColor sets color to be used within environment

		if ((panelWidth % panelPixels == 0) && (panelHeight % panelPixels == 0)) {
			g.drawRect(0, 0, panelWidth, panelHeight); // draws large rectangle
			for (int i = 0; i < panelWidth; i += panelPixels) { // draws vertical lines 
				g.drawLine(i, 0, i, panelHeight);
			}

			for (int i = 0; i < panelHeight; i += panelPixels) { // draws horizontal lines
				g.drawLine(0, i, panelWidth, i);
			}
		}
		
		else {
			int newWidth = 0;
			int newHeight = 0;
			if((panelWidth % panelPixels == 0) && (panelHeight % panelPixels != 0)) {
				newWidth = panelWidth;
				newHeight = (panelHeight / panelPixels) * panelPixels;
				g.drawRect(0, 0, newWidth, newHeight);
			}
			
			if((panelWidth % panelPixels != 0) && (panelHeight % panelPixels == 0)) {
				newWidth = (panelWidth - panelPixels) * panelPixels;
				newHeight = panelHeight;
				g.drawRect(0, 0, newWidth, newHeight);
			}
			
			if((panelWidth % panelPixels != 0) && (panelHeight % panelPixels != 0)) {
				newWidth = (panelWidth / panelPixels) * panelPixels;
				newHeight = (panelHeight / panelPixels) * panelPixels;
				g.drawRect(0, 0, newWidth, newHeight);
			}
			
			for (int i = 0; i < newWidth; i += panelPixels) { 
				g.drawLine(i, 0, i, newHeight);
			}

			for (int i = 0; i < newHeight; i += panelPixels) { 
				g.drawLine(0, i, newWidth, i);
			}
		}
	}
	
	/**
	 * method to fill specified cell
	 * synchronized with clearCell with ArrayList modification
	 * @param x - specifies coordinate x of cell
	 * @param y - specifies coordinate y of cell
	 */
	public synchronized void fillCellRed(int x, int y) {
		fillCells.add(new Point(x, y));
		repaint();
	}
	
	public synchronized void fillCellBlue(int x, int y) {
		fillCells.add(new Point(x, y));
		repaint();
	}
	/**
	 * method to clear specified cell
	 * synchronized with clearCell with ArrayList modification
	 * @param x - specifies coordinate x of cell
	 * @param y - specifies coordinate y of cell
	 */
	public synchronized void clearCell(int x, int y) {
		Point checkCell = new Point(x, y);
		for (int i = 0; i < fillCells.size(); ++i) {
			if (fillCells.get(i).equals(checkCell)) { 
				fillCells.remove(i);
			}
		}
		repaint();
	}
}
