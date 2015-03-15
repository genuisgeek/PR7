import java.util.Arrays;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class tester implements ActionListener, ChangeListener
{
	private static Scanner usrKey = new Scanner(System.in);
	private static String input;
	private static GraphicsGrid graphGrid;
	private static int MIN_SPEED = 10;
	private static int MAX_SPEED = 100;
	private int moveCounter = 0;
	private static int score = 0;
	private int highscore = 0;
	private JButton newButton, resetButton;
	private boolean playing = true;
	private JSlider speedSlide;
	private SnakeMover sMove;
	private JLabel gameOverLabel, scoLabel, scoreLab, hscoLabel, hscoreLab;
	private int moveCount = 0;
	private Coord initial = new Coord(0, 1);
	
	public tester()
	{
		design();
		newButton.addActionListener(this);
		resetButton.addActionListener(this);
		speedSlide.addChangeListener(this);
	}
	
	public void startGame()
	{
		score = 0;
		moveCount = 0;
		playing = true;
		initial = new Coord(0, 1);
		// int width = 
		
	}
	
	public void design()
	{
		JFrame frame = new JFrame();

		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(2,3));

		scoLabel = new JLabel("Score: ");
		scoreLab = new JLabel("".format("%4d", score));
		gameOverLabel = new JLabel("");
		hscoLabel = new JLabel("High Score: ");
		hscoreLab = new JLabel("".format("%4d", highscore));

		northPanel.add(scoLabel);
		northPanel.add(scoreLab);
		northPanel.add(gameOverLabel);
		northPanel.add(hscoLabel);
		northPanel.add(hscoreLab);

		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridBagLayout());

		newButton = new JButton("NEW GAME");
		resetButton = new JButton("RESET GAME");
		speedSlide = new JSlider(MIN_SPEED, MAX_SPEED, MIN_SPEED);

		southPanel.add(newButton);
		southPanel.add(resetButton);
		southPanel.add(speedSlide);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1, 3));
		graphGrid = new GraphicsGrid(400, 400, 13, centerPanel);
		graphGrid.fillCell();
		centerPanel.add(graphGrid);
		
		frame.setSize(400, 600);
		frame.add(centerPanel);
		frame.add(northPanel, BorderLayout.NORTH);
		frame.add(southPanel, BorderLayout.SOUTH);
		
		sMove = new SnakeMover(graphGrid.gameGrid, initial, this);
		graphGrid.addKeyListener(sMove);
		graphGrid.setFocusable(true);
		graphGrid.requestFocusInWindow();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
  
  public void actionPerformed (ActionEvent event)
  {
    if (event.getSource() == newButton)
    {
      new tester();
      //needs to change once we can get it to become dynamic?
      graphGrid.gameGrid.printFirstSnake();
      /*
      graphGrid.fillCell();
      while (true)
      {
        graphGrid.fillCell();
      }
      */
    }

    if (event.getSource() == resetButton)
    {
      new tester();
      graphGrid.gameGrid.printFirstSnake();
    }
  }

  private void adjustSpeed()
  {
    int speed = speedSlide.getValue();
    int tempSpeed = ((score / 100) + 1) * MIN_SPEED;
    if (MAX_SPEED < tempSpeed)
    {
      tempSpeed = MAX_SPEED;
    }

    if (tempSpeed >= speed)
    {
      speedSlide.setValue(tempSpeed);
    }
    else if (speed > tempSpeed)
    {
      speedSlide.setValue(speed);
    }
  }

  private void changeSpeed()
  {
    int newSpeed = speedSlide.getValue();
    if (sMove != null)
    {
      newSpeed = ((MAX_SPEED - newSpeed) / 10) + 1;
      sMove.setSpeed( 2 * newSpeed);
    }
  }

  public void stateChanged (ChangeEvent event)
  {
    changeSpeed();
    gameSession();
  }

  public void moveSnake()
  {
    if (++moveCount >= 10)
    {
      graphGrid.gameGrid.addObstacle();
      moveCount = 0;
    }
  }
  
  public void gameSession()
  {
	if(!playing)
	{
		return;
	}
	
	this.moveSnake();
	score += 10;
    if (score >= highscore)
    {
      highscore = score;
    }
	
    scoreLab.setText("".format("%4d", score));
    hscoreLab.setText("".format("%4d", highscore));
    changeSpeed();
  }

  public void death()
  {
    playing = false;
    gameOverLabel.setText("GAME OVER");
  }
	
	public static void main (String args[])
	{
		new tester();
		graphGrid.gameGrid.printFirstSnake();
		graphGrid.fillCell();
		while(true)
		{
			graphGrid.fillCell();
		}
	}
}
