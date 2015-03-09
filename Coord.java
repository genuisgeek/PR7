/* Coordinate class for Snake game 
 * Name: Thomas Kuo & Wei Han Lee
 * ID: 
 * Email: 
 */
 
import java.awt.*;
import java.util.ArrayList;

 
 
 public final class Coord 
 {
	public ArrayList<Point> coordinateArray = new ArrayList<Point>();
	private boolean equal;
	private String printCoord = null;
	private int i; 
	private int x, y;
	
	/* Constructor for Coord class
	 * @param r - row 
	 * @param c - column
	 * 
	 */
	public Coord(int r, int c) {
		x = r;
		y = c;
		// coordinate = new Point(x, y);
		// coordinateArray.add(coordinate); // don't need to track point just need to match it later 
	}
	
	public Coord(Coord initial) {
		x = (int) initial.getX();
		y = (int) initial.getY();
		// coordinate = (int) initial.getX(), (int) initial.getY());
		// coordinateArray.add(coordinate);
	}
	
	public boolean isEqual(Coord p)
	{
		return equals(p);
	}
	
	public Coord equal() {
		return this;
	}
	
	public String toString() {
		printCoord = "[" + this.getX() + ", " + this.getY() + "]";
	/*
		for (i = 0; i < coordinateArray.size(); ++i) {
			printCoord += "/n" + coordinateArray.get(i);
			// System.out.println("Coordinate: " + coordinateArray[i]);
		}
		*/
		return printCoord;
	}
	/*
	public Point getCoordinate(int index) {
	Point returnPoint = null;
		if (index >= 0 && index < coordinateArray.size()) {
			returnPoint = coordinateArray.get(index);
		}
		return returnPoint;
	}*/
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public static void main (String args[])
	{
		ArrayList<Coord> arrayCoord = new ArrayList<Coord>();
		Coord newCoord = new Coord(1, 2); 
		arrayCoord.add(newCoord);
		Coord booCoord = new Coord(1, 3);
		arrayCoord.add(booCoord);
		System.out.println(arrayCoord.get(1));
		// System.out.println(newCoord.toString());
		
	}
 }