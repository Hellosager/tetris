import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class backgroundPanelEntryScreen extends JPanel{
	private ImageIcon bg;

	public backgroundPanelEntryScreen(ImageIcon bg){
		this.bg = bg;
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg.getImage(), 0, 0, this);
	}
}
