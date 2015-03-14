import java.util.Arrays;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class tester implements ActionListener, ChangeListener
{
	// private static GameGrid bitch = new GameGrid(10, 30);
	private static Scanner usrKey = new Scanner(System.in);
	private static String input;
	// private static GameGrid gameGrid;
	private static GraphicsGrid graphGrid;
  private static int MIN_SPEED = 10;
  private static int MAX_SPEED = 100;
  private int score = 2;
  private int highscore = 3;
  private JButton newButton, resetButton;
  private JSlider speedSlide;
	
	public tester()
	{
		design();
    newButton.addActionListener(this);
    resetButton.addActionListener(this);
    speedSlide.addChangeListener(this);
	}
	
	public void design()
	{
		JFrame frame = new JFrame();

    JPanel northPanel = new JPanel();
    northPanel.setLayout(new GridLayout(2,3));

    JLabel inputs = new JLabel("NO");
    JLabel inputss = new JLabel("".format("%4d", score));
    JLabel scoresss = new JLabel("STOP IT");
    JLabel inputssss = new JLabel("STOP IT NOW");
    JLabel inputsssss = new JLabel("".format("%4d", highscore));

    northPanel.add(inputs);
    northPanel.add(inputss);
    northPanel.add(scoresss);
    northPanel.add(inputssss);
    northPanel.add(inputsssss);

    JPanel southPanel = new JPanel();
    southPanel.setLayout(new FlowLayout());

    newButton = new JButton("NEW GAME");
    resetButton = new JButton("RESET GAME");
    speedSlide = new JSlider(MIN_SPEED, MAX_SPEED, MIN_SPEED);

    southPanel.add(newButton);
    southPanel.add(resetButton);
    southPanel.add(speedSlide);

		graphGrid = new GraphicsGrid(400, 400, 13);
		// System.out.println(direction);
		graphGrid.fillCell();
		frame.setSize(400, 600);
		frame.add(graphGrid, BorderLayout.CENTER);
    frame.add(northPanel, BorderLayout.NORTH);
    frame.add(southPanel, BorderLayout.SOUTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
	}
  
  public void actionPerformed (ActionEvent event)
  {
    if (event.getSource() == newButton)
    {
      System.out.println("STUPID DIE");
    }

    if (event.getSource() == resetButton)
    {
      System.out.println("CRYING INTERNALLY");
    }
  }

  public void stateChanged (ChangeEvent event)
  {
    int speedTest = speedSlide.getValue();
    System.out.println(speedTest);
  }
	
	public static void main (String args[])
	{
		new tester();		
		
		// graphGrid.gameGrid.drawGrid();
		graphGrid.gameGrid.printFirstSnake();
		graphGrid.fillCell();
		while(true)
		{
			graphGrid.fillCell();
			input = usrKey.next();
			if (input.equals("k")) // simple movement is a problem because it keeps the H value 
			{
				graphGrid.gameGrid.moveSnake(0, 1);
				graphGrid.gameGrid.addObstacle();
				graphGrid.fillCell();
			}	
			
			if (input.equals("j"))
			{
				graphGrid.gameGrid.growSnake(-1, 0);
				graphGrid.gameGrid.addObstacle();
				graphGrid.fillCell();
			}
			
			if (input.equals("l"))
			{
				graphGrid.gameGrid.growSnake(1, 0);
				graphGrid.gameGrid.addObstacle();
				graphGrid.fillCell();
			}
			
			if (input.equals("q"))
			{
				System.exit(0);
			}
		}
		
		
		/*
		bitch.moveSnake(1, 0); //going in 
		bitch.gridPrint();
		bitch.addObstacle();
		System.out.println();
		bitch.gridPrint();
	
		bitch.growSnake(1,0); // going in 
		bitch.gridPrint();
		bitch.addObstacle();
		bitch.growSnake(0,1);  // good goes down one 
		bitch.gridPrint();
		bitch.growSnake(1, 0); //invalid rule 
		bitch.gridPrint();
		
		
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
