import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class Buttonlistener implements ActionListener{
	private Container c;						// ContentPane
	private JButton play;						// Playbutton
	private JButton highscore;					// Highscorebutton
	private JFrame frame;						// MainFenster
	private int xBreite = 10;
	private int yHoehe = 20; 
	private Steinlabel[][] feld = new Steinlabel[yHoehe][xBreite];
	private Spielfeld spielfeld = new Spielfeld(feld);
	private Details details = new Details();
	private Spielschleife s;		// erstellt die Spielschleife
	private HighscorePanel hp;

	
	public Buttonlistener(Container c, JButton play, JButton highscore, JFrame frame){ 	// Konstruktor
		this.c = c;
		this.play = play;
		this.highscore = highscore;
		this.frame = frame;
		s = new Spielschleife(feld, spielfeld, details, c, frame);
	}
	
	public Buttonlistener(Container c, JFrame frame, JButton button){
		this.c = c;
		this.frame = frame;
		if(button.getText().equals("Play") || button.getText().equals("Replay")){
			this.play = button;
		}else if(button.getText().equals("Highscore")){
			this.highscore = button;
		}
//		this.play = button;
		s = new Spielschleife(feld, spielfeld, details, c, frame);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == play){
			buildInterface();			// Initialisieren
			startGame();				// Spiel starten
		}
		else if(e.getSource() == highscore)
			showScore();
		
	}

	
	
	
	
	
	
	public void buildInterface(){	// Methode um c zu initialisieren
		c.removeAll();				// alle Komponenten von c entfernen
		c.setLayout(null);			// c freies Layout zuweisen
		spielfeld.setBounds(1, 2, 250, 500);
		details.setBounds(251, 1, 141, 500);
		details.setBorder(new LineBorder(Color.BLACK));
		frame.addKeyListener(new Steuerung(s, feld));			// FUNKTIONIERT NICHT
		frame.requestFocus();
		c.add(spielfeld);
		c.add(details);
		c.repaint();
		c.validate();
		}
	
	public void startGame(){
		s.start(); // startet die Spielschleife
		
	}
	
	public void showScore(){
		c.removeAll();
		c.setLayout(new BorderLayout());
		hp  = new HighscorePanel(c, frame);
		c.add(hp);
		c.repaint();
		c.revalidate();
	}
}
