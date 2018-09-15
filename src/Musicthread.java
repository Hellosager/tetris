import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


public class Musicthread extends Thread{
	private Player music;
	private Spielschleife s;
	private boolean loop = true;
	
	public Musicthread(Spielschleife s){
		this.s = s;
	}

	public void run(){
		do{
			try{
				music = new Player(getClass().getResourceAsStream("sounds/Tetris.mp3"));
				s.setMusic(music);
				music.play();
			}catch(JavaLayerException ex){
				System.out.println("LAYER");
			}

		}while(loop);
	}
	
	public void setLoop(boolean l){
		loop = l;
	}
}
