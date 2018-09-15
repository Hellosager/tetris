import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;


public class GameOverThread extends Thread{
	private Steinlabel[][] feld;
	private int points;
	private Container c;
	private GameOverPanel gameOver;
	private PlayButton replay = new PlayButton("Replay");
	private PlayButton highscore = new PlayButton("Highscore");
	private JFrame frame;
	private HighscoreHandler sh;
	private int level;

	
	public void run(){
		for(int row = 0; row < 20; row++)
			for(int cell = 0; cell < 10; cell++){
				feld[row][cell].setBackground(Color.BLACK);
				try {
					sleep(10);
				} catch (InterruptedException e) {
				}
			}
		replay.addActionListener(new Buttonlistener(c, frame, replay));
		highscore.addActionListener(new Buttonlistener(c, frame, highscore));
		
		sh.loadScore();
		sh.sortScore(points, level);
		sh.saveScore();
		
		c.add(gameOver);
		c.add(replay);
		c.add(highscore);
		replay.repaint();
		highscore.repaint();
		c.revalidate();
	}
	
	public GameOverThread(Steinlabel[][] feld, int points, Container c, JFrame frame, int level) {		// Konstruktor
		this.feld = feld;
		this.points = points;	
		this.c = c;
		this.frame = frame;
		this.level = level;
		gameOver = new GameOverPanel(points);
		sh  = new HighscoreHandler(points, level);
		}

}
