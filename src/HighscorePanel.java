import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class HighscorePanel extends JPanel{
	private int[] highscores = new int[5];
	private int[] levels = new int[5];
	private HighscoreHandler hh = new HighscoreHandler();
	private JLabel ueberschrift = new JLabel("Highscores", JLabel.CENTER);
	private DecimalFormat df = new DecimalFormat("000000");
	private DecimalFormat levelFormat = new DecimalFormat("00");
	private JLabel addLabel;
	private JLabel levelLabel;
	
	private JPanel scorePanel = new JPanel();
	private JLabel currentScore;
	private PlayButton play = new PlayButton("Play");
	private Container c;
	private JFrame frame;
	
	public HighscorePanel(Container c, JFrame frame){
		this.highscores = hh.getHighscores();
		this.levels = hh.getLevels();
		this.c = c;
		this.frame = frame;
		
		
		setBackground(Color.BLACK);
		setLayout(null);
		ueberschrift.setBounds(this.getWidth()/2+(int)(125/2)+10, 10, 250, 70);
		ueberschrift.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		ueberschrift.setForeground(Color.RED);

		
		scorePanel.setBounds(this.getWidth()/2+(int)(125/2)+10, 120, 250, 250);
		scorePanel.setLayout(new GridLayout(6, 3, 0, 0));
		scorePanel.setBackground(Color.LIGHT_GRAY);
		scorePanel.add(new JLabel());
		scorePanel.add(addLabel = new JLabel("Highscore", JLabel.CENTER));
			addLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		scorePanel.add(addLabel = new JLabel("Level", JLabel.CENTER));
			addLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		for(int i = 0; i < 5; i++){
			scorePanel.add(addLabel = new JLabel((i+1) + "",JLabel.CENTER));
			addLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
			currentScore = new JLabel(df.format(highscores[i]),JLabel.CENTER);
			scorePanel.add(currentScore);
			scorePanel.add(levelLabel = new JLabel(levelFormat.format(levels[i]), JLabel.CENTER));

			if(hh.isNewHighscore() && hh.getNewHighNum() == i){
				addLabel.setForeground(Color.RED);
				currentScore.setForeground(Color.RED);
				levelLabel.setForeground(Color.RED);
				hh.setNewHighscore(false);
				}
			}
		
		play.setBounds(this.getWidth()/2+(int)(125/2)+40, 390, 190, 80);
		play.setBackground(Color.GRAY);
		play.setBorder(new LineBorder(Color.BLACK, 10));
		play.setForeground(Color.BLACK);
		play.addActionListener(new Buttonlistener(c, frame, play));

		add(ueberschrift);
		add(scorePanel);
		add(play);
		
	}
	
}
