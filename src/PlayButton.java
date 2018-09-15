import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;


public class PlayButton extends JButton{
	String name;
	
	public PlayButton(String name){
		this.name = name;
		
		switch(name){
			case "Play": 
				setText(name);
				break;
			case "Replay": 
				setText(name);
				setBounds(51, 300, 149, 40);
				setBackground(Color.GRAY);
				setForeground(Color.BLACK);
				break;
			case "Highscore":
				setText(name);
				setBounds(51, 355, 149, 40);
				setBackground(Color.GRAY);
				setForeground(Color.BLACK);
				break;
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		repaint();
	}
	
}
