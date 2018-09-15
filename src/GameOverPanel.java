import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class GameOverPanel extends JPanel{
	private int points;
	private JLabel bildLabel = new JLabel(new ImageIcon(getClass().getResource("images/GameOver.png")));
	private JLabel scoreLabel;

	
	public GameOverPanel(int points){
		this.points = points;
		
		setOpaque(true);
		setBackground(Color.GRAY);
		setSize(199, 120);
		setLocation(26, 155);
		setLayout(new BorderLayout());
		
		scoreLabel = new JLabel("Your Score: " + points, JLabel.CENTER);
		scoreLabel.setForeground(Color.ORANGE);
		scoreLabel.setOpaque(false);
		bildLabel.setOpaque(false);

		add(bildLabel);
		add(scoreLabel, BorderLayout.SOUTH);

	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		repaint();
	}
}
