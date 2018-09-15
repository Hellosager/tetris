import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class EnterScreen extends JFrame{
	private Container c = getContentPane();
	private PlayButton play = new PlayButton("Play");			// Der Playbutton
	private JButton highscore = new JButton("Highscore");// der Highscorebutton
	private backgroundPanelEntryScreen bg = new backgroundPanelEntryScreen(new ImageIcon(getClass().getResource("images/tetris.png")));// Hintergrund mit Bild machen(Panel)
	private JFrame frame = this;
	
	public EnterScreen(){		// Konstruktor
		add(bg);	// Hintergrundpanel hinzufuegen
		bg.setLayout(new GridLayout(6,3,0,30));	//6*3 Gitter
		
			for(int i = 0; i <10;i++)		// Leere Labels
				bg.add(new JLabel());
		
			bg.add(play);		//Playbutton auf Hintergrund 
				play.addActionListener(new Buttonlistener(c, play, highscore, frame));
				
			for(int i = 0; i < 2;i++)	// leere Labels
				bg.add(new JLabel());
						
			bg.add(highscore);	//Highscorebutton auf Hintergrund	
				highscore.addActionListener(new Buttonlistener(c, play, highscore, frame));
	}
}
