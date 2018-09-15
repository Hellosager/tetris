import java.awt.Container;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JFrame;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


public class Spielschleife extends Thread{
	private Steinlabel[][] feld;
	private Stein stone;
	private int speed = 1000;
	private int levelSpeed = 1000;
	private Spielfeld spielfeld;
	private Details details;
	private Container c;
	private JFrame frame;
	private boolean paused = false;
	private Player music;
	private Musicthread mt;


	public void run(){
		mt = new Musicthread(this);
		mt.start();
		
		
		details.setNextStone();
		stone = new Stein(feld, details);
		while(!stone.isOnTop()){	// AUFPASSEN
			if(stone.getNummer() != 0)
				stone = new Stein(feld, details);
			if(speed == 50)
				stone.setIsSpeeded(true);
				details.updateNextStone();
				while(!stone.isSolid()){
						try {
						sleep(speed);
						}catch(InterruptedException e) {
						}
					if(!paused){
						stone.fallen();
					}	
				}
				updateNachDurchlauf();	

		}
		mt.setLoop(false);
		music.close();
		new GameOverThread(feld, details.getScore(), c, frame, details.getLevel()).start();
		
	}
	
	public Spielschleife(Steinlabel[][] feld, Spielfeld spielfeld, Details details, Container c, JFrame frame){		// Konstruktor
		this.feld = feld;
		this.spielfeld = spielfeld;
		this.details = details;
		this.c = c;
		this.frame = frame;
	}
	
	public void updateNachDurchlauf(){
		spielfeld.updateRows();
		spielfeld.revalidate();
		details.addPoints(spielfeld.getRowPoints(), stone.getExtraSpeedPoints());
		details.updateDeletedRows(spielfeld.getDeletedRows());
		if(spielfeld.levelChanged()){
			details.updateLevel(spielfeld.getLevel());
			levelSpeed = spielfeld.getLevelSpeed();
		}
		
	}
	
	public Stein getAktStein(){
		return stone;
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public void setLevelSpeed(){
		speed = levelSpeed;
	}

	public void setPaused(boolean paused){
		this.paused = paused;
	}
	
	public boolean getPaused(){
		return paused;
	}
	
	
	
	public void setMusic(Player music){
		this.music = music;
	}
	
	
}
