import javax.swing.JFrame;


public class Main {

	public static void main(String[] args) {
		EnterScreen f = new EnterScreen();
		f.setVisible(true);
		f.setTitle("Tetris");
		f.setSize(399, 531);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
	}

}
