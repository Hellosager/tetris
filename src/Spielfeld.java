import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;


public class Spielfeld extends JPanel{
	private Steinlabel[][] feld;
	private int rowPoints;
	private int deletedRows = 0;
	private int level = 0;
	
	public Spielfeld(Steinlabel[][] feld){		// Konstruktor
		this.feld = feld;
		
		setLayout(new GridLayout(20,10, 1, 1));
		for(int i = 0; i<20;i++)
			for(int k = 0; k < 10; k++){
				feld[i][k] = new Steinlabel();
				feld[i][k].setOpaque(true);
				add(feld[i][k]);
		}
	}
	
	public void deleteRow(int row){			// löscht die Reihe row
		for(int i = 0; i < 10; i++){
			feld[row][i].setBackground(null);
			feld[row][i].setFixed(false);
			feld[row][i].repaint();
		}
		deletedRows++;
	}
	
	public void updateRows(){
		int numberRows = 0;
		rowPoints = 0;
		for(int i = 0; i < 4; i++){
			for(int row = 19; row >= 0; row--){
				if(rowIsFull(row)){
					deleteRow(row);
					lassFallen(row);
					numberRows++;
				}
			}
		}
		verrechnePunkte(numberRows);
	}
	
	public void lassFallen(int row){
		for(int i = row; i >= 0; i--)		// interiere durch jede Reihe bis zur gelöschten
			for(int k = 0; k < 10; k++){	// je Reihe interiere durch alle Labels
				if(i != 0){
				feld[i][k].setBackground(feld[i-1][k].getBackground());
				feld[i][k].setFixed(feld[i-1][k].isFixed);
				feld[i][k].repaint();
				}else{
					feld[i][k].setBackground(null);
					feld[i][k].setFixed(false);
					feld[i][k].repaint();
				}
			}
		}
		
	
	public boolean rowIsFull(int row){		// wenn alle Labels fixed sind = true
		for(int i = 0; i < 10; i++){		
			if(!feld[row][i].isFixed)
				return false;
		}
		return true;
	}
	
	public void verrechnePunkte(int numberRows){
		int addPunkte = 0;
		switch(numberRows){
		case 1: addPunkte = 40*(level+1); break;
		case 2: addPunkte = 100*(level+1); break;
		case 3: addPunkte = 300*(level+1); break;
		case 4: addPunkte = 1200*(level+1); break;
		}
		rowPoints += addPunkte;
		}

	public int getRowPoints(){
		return rowPoints;
	}
	
	public int getLevel(){
		return level;
	}

	public boolean levelChanged(){
			int levelV = deletedRows/10;
			if(levelV != level){
				level = levelV;	
				return true;
			}
			return false;
	}
	
	public int getLevelSpeed(){
		int speed = 1000;
		for(int i = 0; i<= level; i++)
			speed = (int) (speed*0.85);
		return speed;
	}
	
	public int getDeletedRows(){
		return deletedRows;
	}
	
}
