import java.util.Arrays;

public class tester
{
	static GameGrid bitch = new GameGrid(5, 5, 10);
	public static void main (String args[])
	{
		bitch.drawGrid();
		System.out.println(Arrays.deepToString(bitch.charGrid));
		bitch.moveSnake(1, 0);
		System.out.println(Arrays.deepToString(bitch.charGrid));
		bitch.growSnake(0,1);
		System.out.println(Arrays.deepToString(bitch.charGrid));
		bitch.growSnake(0,1);
		System.out.println(Arrays.deepToString(bitch.charGrid));
		/*
		Coord testCoord = new Coord(0,1);
		Snake bitchFucker = new Snake(testCoord, MAX_SIZE);
		bitchFucker.move(0,1);
		System.out.println(bitchFucker.getHeadCoord());
		bitchFucker.move(1,0);
		System.out.println(bitchFucker.getHeadCoord());
		bitchFucker.move(0,0); // invalid
		System.out.println(bitchFucker.getHeadCoord());
		bitchFucker.move(1,1); //invalid
		System.out.println(bitchFucker.getHeadCoord());
		bitchFucker.move(0,2); // invalid
		System.out.println(bitchFucker.getHeadCoord());
		bitchFucker.move(2,0); //invlaid
		System.out.println(bitchFucker.getHeadCoord());
		bitchFucker.move(2,2); // invalid
		System.out.println(bitchFucker.getHeadCoord());
		bitchFucker.grow(0, 1);
		System.out.println(bitchFucker.getHeadCoord());
		System.out.println(bitchFucker.getTailCoord());
		System.out.println(bitchFucker.toString());
		*/
	}
}