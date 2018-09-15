import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Steuerung implements KeyListener{
	private Spielschleife s;
	private Stein actualStone;
	private boolean isSchneller = false;
	private Steinlabel[][] feld;
	private String cheatcode = "";
	private boolean hasCheated = false;

	
	public Steuerung(Spielschleife s, Steinlabel[][] feld){		// Konstruktor
		this.s = s;
		this.feld = feld;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_M && !hasCheated && cheatcode.equals("") && s.isAlive()){
				cheatcode = "m";
		}else if(e.getKeyCode() == KeyEvent.VK_A && cheatcode.equals("m") && s.isAlive()){
				cheatcode = "ma";
		}else if(e.getKeyCode() == KeyEvent.VK_R && cheatcode.equals("ma") && s.isAlive()){
				cheatcode = "mar";
		}else if(e.getKeyCode() == KeyEvent.VK_V && cheatcode.equals("mar") && s.isAlive()){
				cheatcode = "marv";
		}else if(e.getKeyCode() == KeyEvent.VK_I && cheatcode.equals("marv") && s.isAlive()){
				cheatcode = "marvi";
		}else if(e.getKeyCode() == KeyEvent.VK_N && cheatcode.equals("marvi") && s.isAlive()){
				clearBoard();
				cheatcode = "";
				hasCheated = true;
		}else{
			cheatcode = "";
		}
		
		

	
		
		if(!s.getPaused() && e.getKeyCode() == KeyEvent.VK_A || !s.getPaused() && e.getKeyCode() == KeyEvent.VK_LEFT){
			actualStone = s.getAktStein();
			actualStone.verschiebenL();

		}
		if(!s.getPaused() && e.getKeyCode() == KeyEvent.VK_D || !s.getPaused() && e.getKeyCode() == KeyEvent.VK_RIGHT){
			actualStone = s.getAktStein();
			actualStone.verschiebenR();
		}
		if(!s.getPaused() && e.getKeyCode() == KeyEvent.VK_W || !s.getPaused() && e.getKeyCode() == KeyEvent.VK_UP){
			actualStone = s.getAktStein();
			actualStone.drehen();
		}
		if(!s.getPaused() && e.getKeyCode() == KeyEvent.VK_S || !s.getPaused() && e.getKeyCode() == KeyEvent.VK_DOWN){
				s.getAktStein().setIsSpeeded(true);
				if(!isSchneller){
					s.interrupt();
					s.setSpeed(50);
					}
				isSchneller = true;

			}
		}

	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN){
			s.setLevelSpeed();
			s.getAktStein().setIsSpeeded(false);
			isSchneller = false;
		}
		
		// Pausetaste
		if(e.getKeyCode() == KeyEvent.VK_P){
			if(!s.getPaused())
				s.setPaused(true);
			else if(s.getPaused()){
				s.setPaused(false);
			}
		}
		
	}
	
	
	
	public void keyTyped(KeyEvent e) {
		
	}

	public void clearBoard(){
		for(int row = 0; row < 20; row++)
			for(int cell = 0; cell < 10; cell++){
				feld[row][cell].setBackground(null);
				feld[row][cell].setFixed(false);
				feld[row][cell].repaint();
			}
		s.getAktStein().neuFärben();
			
	}

}
