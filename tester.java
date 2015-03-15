import java.util.Arrays;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class tester extends JFrame implements ActionListener, ChangeListener, Runnable
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
	private SnakeMover move;
	private JLabel gameOverLabel, scoLabel, scoreLab, hscoLabel, hscoreLab;
	private int moveCount = 0;
	private Coord initial = new Coord(0, 1);
	private int pixelSize = 10; 
	private JPanel centerPanel;
	private int speed;
	
	public tester()
	{
		run();
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
		int width = centerPanel.getPreferredSize().width;
		int height = centerPanel.getPreferredSize().height;
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.setSize(400, 400);
		graphGrid = new GraphicsGrid(width, height, pixelSize, centerPanel);
		graphGrid.gameGrid.printFirstSnake();
		graphGrid.fillCell();
		centerPanel.add(graphGrid, BorderLayout.CENTER);
		
		move = new SnakeMover(graphGrid.gameGrid, initial, this);
		centerPanel.addKeyListener(move);
		centerPanel.setFocusable(true);
		centerPanel.requestFocusInWindow();
	}
	
	public void run()
	{
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
		southPanel.setLayout(new GridLayout(1,3));

		newButton = new JButton("NEW GAME");
		newButton.setFocusable(false);
		resetButton = new JButton("RESET GAME");
		resetButton.setFocusable(false);
		
		speedSlide = new JSlider(MIN_SPEED, MAX_SPEED, MIN_SPEED);
		speedSlide.setMajorTickSpacing(10);
		speedSlide.setSnapToTicks(true);
		speedSlide.setPaintTicks(true);
		speedSlide.setFocusable(false);

		southPanel.add(newButton);
		southPanel.add(resetButton);
		southPanel.add(speedSlide);

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.setSize(400, 400);
		graphGrid = new GraphicsGrid(centerPanel.getSize().width, centerPanel.getSize().height, 10, centerPanel);
		graphGrid.gameGrid.printFirstSnake();
		centerPanel.add(graphGrid, BorderLayout.CENTER);
		move = new SnakeMover(graphGrid.gameGrid, initial, this);
		centerPanel.addKeyListener(move);
		centerPanel.setFocusable(true);
		centerPanel.requestFocusInWindow();
		
		setSize(400, 600);
		Container pane = getContentPane();
		pane.add(centerPanel, BorderLayout.CENTER);
		pane.add(northPanel, BorderLayout.NORTH);
		pane.add(southPanel, BorderLayout.SOUTH);
		validate();		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
  
  public void actionPerformed (ActionEvent event)
  {
    if (event.getSource() == newButton)
    {
	  startGame();
      graphGrid.gameGrid.printFirstSnake();
    }

    if (event.getSource() == resetButton)
    {
		highscore = 0;
		hscoreLab.setText("".format("%4d", highscore));
		scoreLab.setText("".format("%4d", score = 0));
		death();
		if (move != null) {
			move.stopMove();
		}
		speed = MIN_SPEED;
		speedSlide.setValue(speed);
    }
  }

private void adjustSpeed()
  {
    speed = speedSlide.getValue();
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
    if (move != null)
    {
      newSpeed = ((MAX_SPEED - newSpeed) / 10) + 1;
      move.setSpeed( 2 * newSpeed);
    }
    //System.out.println(2 * newSpeed);
  }

  public void stateChanged (ChangeEvent event)
  {
    changeSpeed();
    //gameSession();
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
    if (!playing)
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
    adjustSpeed();
  }

  public void death()
  {
    playing = false;
    gameOverLabel.setText("GAME OVER");
  }
	
	public static void main (String args[])
	{
		new tester();
		// SwingUtilities.invokeLater(new tester());
		while(true) graphGrid.fillCell();
	}
}
