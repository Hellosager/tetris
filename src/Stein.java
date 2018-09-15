import java.awt.Color;
import java.util.Random;


public class Stein {
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private int x3;
	private int y3;
	private int x4;
	private int y4;
	
	private static int nummer = 0;
	
	private Color color;
	private String art = "";
	private int direction;
	private Steinlabel[][] feld;
	private boolean isSolid = false;
	private Details details;
	private boolean isSpeeded = false;
	private int extraSpeedPoints = 0;
		// Variablen
	
	
	public Stein(Steinlabel[][] feld, Details details){		// Konstruktor
		this.feld = feld;
		this.details = details;
		createNewStone();
		if(!isOnTop()){
			
		feld[y1][x1].setBackground(color);
		feld[y2][x2].setBackground(color);
		feld[y3][x3].setBackground(color);
		feld[y4][x4].setBackground(color);
		feld[y1][x1].repaint();
		feld[y2][x2].repaint();
		feld[y3][x3].repaint();
		feld[y4][x4].repaint();
		nummer++;
		}
	}

	synchronized public void verschiebenL(){
		if(isVerschiebarNachLinks()){
		löschen();
		x1 -= 1;
		x2 -= 1;
		x3 -= 1;
		x4 -= 1;
		neuFärben();
		}
	}
	synchronized public void verschiebenR(){
		if(isVerschiebarNachRechts()){
		löschen();
		x1 += 1;
		x2 += 1;
		x3 += 1;
		x4 += 1;
		neuFärben();
		}
	}

	synchronized public void fallen(){
		if(isSetzbar()){
		löschen();
		y1 += 1;
		y2 += 1;
		y3 += 1;
		y4 += 1;
		neuFärben();
		}
		if(isSpeeded)
			extraSpeedPoints++;
	}
	
	synchronized public void drehen(){
		if(art != "block"){
			if(isRotatable()){
		löschen();
		int zentrumX = x1;		// ersten Stein drehen
		int zentrumY = y1;
		int xAlt = x2 - zentrumX;
		int yAlt = y2 - zentrumY;
		x2 = (yAlt*(-1)) + zentrumX;
		y2 = xAlt + zentrumY;
		
		zentrumX = x1;		// zweiten Stein drehen
		zentrumY = y1;
		xAlt = x3 - zentrumX;
		yAlt = y3 - zentrumY;
		x3 = (yAlt*(-1)) + zentrumX;
		y3 = xAlt + zentrumY;
		
		zentrumX = x1;		// dritten Stein drehen
		zentrumY = y1;
		xAlt = x4 - zentrumX;
		yAlt = y4 - zentrumY;
		x4 = (yAlt*(-1)) + zentrumX;
		y4 = xAlt + zentrumY;
		neuFärben();
			}
		}
	}
	synchronized public void löschen(){
		feld[y1][x1].setBackground(null);
		feld[y2][x2].setBackground(null);
		feld[y3][x3].setBackground(null);
		feld[y4][x4].setBackground(null);
		feld[y1][x1].repaint();
		feld[y2][x2].repaint();
		feld[y3][x3].repaint();
		feld[y4][x4].repaint();
	}
	
	synchronized public void neuFärben(){
		feld[y1][x1].setBackground(color);
		feld[y1][x1].repaint();
		feld[y2][x2].setBackground(color);
		feld[y2][x2].repaint();
		feld[y3][x3].setBackground(color);
		feld[y3][x3].repaint();
		feld[y4][x4].setBackground(color);
		feld[y4][x4].repaint();
	}
	
	public boolean isRotatable(){
		int zentrumX = x1;		// ersten Stein drehen
		int zentrumY = y1;
		int xAlt = x2 - zentrumX;
		int yAlt = y2 - zentrumY;
		int xNeu = (yAlt*(-1)) + zentrumX;
		int yNeu = xAlt + zentrumY;
		if(xNeu < 0 || xNeu > 9 || yNeu < 0 || yNeu > 19 || feld[yNeu][xNeu].isFixed)
			return false;
		zentrumX = x1;
		zentrumY = y1;
		xAlt = x3 - zentrumX;
		yAlt = y3 - zentrumY;
		xNeu = (yAlt*(-1)) + zentrumX;
		yNeu = xAlt + zentrumY;
		if(xNeu < 0 || xNeu > 9 || yNeu < 0 || yNeu > 19 || feld[yNeu][xNeu].isFixed)
			return false;
		zentrumX = x1;
		zentrumY = y1;
		xAlt = x4 - zentrumX;
		yAlt = y4 - zentrumY;
		xNeu = (yAlt*(-1)) + zentrumX;
		yNeu = xAlt + zentrumY;
		if(xNeu < 0 || xNeu > 9 || yNeu < 0 || yNeu > 19 || feld[yNeu][xNeu].isFixed)
			return false;
		return true;
	}
	
	public boolean isSetzbar(){
		 
		if(y1 == 19 || y2 == 19 || y3 == 19 || y4 == 19){
			feld[y1][x1].setFixed(true);
			feld[y2][x2].setFixed(true);
			feld[y3][x3].setFixed(true);
			feld[y4][x4].setFixed(true);
			isSolid = true;
			return false;
		}else if(feld[y1+1][x1].isFixed || feld[y2+1][x2].isFixed || feld[y3+1][x3].isFixed || feld[y4+1][x4].isFixed){
			feld[y1][x1].setFixed(true);
			feld[y2][x2].setFixed(true);
			feld[y3][x3].setFixed(true);
			feld[y4][x4].setFixed(true);
			isSolid = true;
			return false;
		}
		return true;
	}
	
	public boolean isVerschiebarNachLinks(){
		if(x1 == 0 || x2 == 0 || x3 == 0 || x4 == 0)
			return false;
		else if(feld[y1][x1-1].isFixed || feld[y2][x2-1].isFixed || feld[y3][x3-1].isFixed || feld[y4][x4-1].isFixed)
			return false;
		
		return true;
	}
	
	public boolean isVerschiebarNachRechts(){
		if(x1 == 9 || x2 == 9 || x3 == 9 || x4 == 9)
			return false;
		else if(feld[y1][x1+1].isFixed || feld[y2][x2+1].isFixed || feld[y3][x3+1].isFixed || feld[y4][x4+1].isFixed)
			return false;
		
		return true;
	}
	
	public boolean isSolid(){
		return isSolid;
	}
	
	public void createNewStone(){
		direction = details.getNextStoneDirection();
		switch(details.getNextStoneForm()){
		case "block": 	// BLOCK
			x1 = 5;	y1 = 0;
			x2 = 6;	y2 = 0;
			x3 = 5;	y3 = 1;
			x4 = 6;	y4 = 1;
			art = "block";
			break;
			
		case "i":		// 4 in einer reihe
			if(direction == 0 || direction == 2){
				x1 = 5;	y1 = 0;
				x2 = 4;	y2 = 0;
				x3 = 6;	y3 = 0;
				x4 = 7;	y4 = 0; break;
			}
			if(direction == 1 || direction == 3){
				x1 = 5;	y1 = 1;
				x2 = 5;	y2 = 0;
				x3 = 5;	y3 = 2;
				x4 = 5;	y4 = 3; break;
			}
			art = "i";
			break;
			
		case "iL":		// umgerdrehtes L
			switch(direction){
			case 0:
				x1 = 6;	y1 = 1;
				x2 = 6;	y2 = 0;
				x3 = 6;	y3 = 2;
				x4 = 5;	y4 = 2;	break;
			case 1:
				x1 = 5;	y1 = 1;
				x2 = 4;	y2 = 0;
				x3 = 4;	y3 = 1;
				x4 = 6;	y4 = 1;	break;
			case 2:
				x1 = 5;	y1 = 1;
				x2 = 5;	y2 = 0;
				x3 = 6;	y3 = 0;
				x4 = 5;	y4 = 2;	break;
			case 3:
				x1 = 5;	y1 = 0;
				x2 = 4;	y2 = 0;
				x3 = 6;	y3 = 0;
				x4 = 6;	y4 = 1;	break;
			}
			art ="iL";
			break;
			
		case "l":		// L
			switch(direction){
			case 0:
				x1 = 5;	y1 = 1;
				x2 = 5;	y2 = 0;
				x3 = 5;	y3 = 2;
				x4 = 6;	y4 = 2;	break;
			case 1:
				x1 = 5;	y1 = 0;
				x2 = 4;	y2 = 1;
				x3 = 4;	y3 = 0;
				x4 = 6;	y4 = 0;	break;
			case 2:
				x1 = 6;	y1 = 1;
				x2 = 5;	y2 = 0;
				x3 = 6;	y3 = 0;
				x4 = 6;	y4 = 2;	break;
			case 3:
				x1 = 5;	y1 = 1;
				x2 = 4;	y2 = 1;
				x3 = 6;	y3 = 1;
				x4 = 6;	y4 = 0;	break;
			}
			art = "l";
			break;
			
		case "iZ":		// umgedrehtes Z
			if(direction == 0 || direction == 2){
				x1 = 5;	y1 = 1;
				x2 = 5;	y2 = 0;
				x3 = 6;	y3 = 0;
				x4 = 4;	y4 = 1;	
			}	
			if(direction == 1 || direction == 3){
				x1 = 6;	y1 = 1;
				x2 = 5;	y2 = 0;
				x3 = 5;	y3 = 1;
				x4 = 6;	y4 = 2;	
			}
			art = "iZ";
			break;
			
		case "t":		// T
			switch(direction){
			case 0:
				x1 = 5;	y1 = 1;
				x2 = 5;	y2 = 0;
				x3 = 4;	y3 = 1;
				x4 = 6;	y4 = 1;	break;
			case 1:
				x1 = 5;	y1 = 1;
				x2 = 5;	y2 = 0;
				x3 = 6;	y3 = 1;
				x4 = 5;	y4 = 2;	break;
			case 2:
				x1 = 5;	y1 = 0;
				x2 = 4;	y2 = 0;
				x3 = 6;	y3 = 0;
				x4 = 5;	y4 = 1;	break;
			case 3:
				x1 = 6;	y1 = 1;
				x2 = 6;	y2 = 0;
				x3 = 5;	y3 = 1;
				x4 = 6;	y4 = 2;	break;
			}
			art = "t";
			break;
		case "z":		// Z
			if(direction == 0 || direction == 2){
				x1 = 5;	y1 = 1;
				x2 = 5;	y2 = 0;
				x3 = 4;	y3 = 0;
				x4 = 6;	y4 = 1;
			}	
			if(direction == 1 || direction == 3){
				x1 = 5;	y1 = 1;
				x2 = 6;	y2 = 0;
				x3 = 6;	y3 = 1;
				x4 = 5;	y4 = 2;	
			}
			art = "z";
			break;
		}
		
		color = details.getNextStoneColor();
	}

	public boolean isOnTop(){
		switch(details.getNextStoneForm()){
		case "i":
			if(direction == 0 || direction == 2)
				if(feld[0][4].isFixed || feld[0][5].isFixed || feld[0][6].isFixed || feld[0][7].isFixed)
					return true;
			if(direction == 1 || direction == 3)
				if(feld[0][5].isFixed || feld[1][5].isFixed || feld[2][5].isFixed || feld[3][5].isFixed)
					return true;
			break;
		case "block":
			if(feld[1][5].isFixed || feld[1][6].isFixed || feld[0][5].isFixed || feld[0][6].isFixed)
				return true;
			break;
		case "iL":
			if(direction == 0)
				if(feld[0][6].isFixed || feld[1][6].isFixed || feld[2][6].isFixed || feld[2][5].isFixed)
					return true;
			if(direction == 1)
				if(feld[1][4].isFixed || feld[1][5].isFixed || feld[1][6].isFixed || feld[0][4].isFixed)
					return true;
			if(direction == 2)
				if(feld[0][5].isFixed || feld[0][6].isFixed || feld[1][5].isFixed || feld[2][5].isFixed)
					return true;
			if(direction == 3)
				if(feld[0][4].isFixed || feld[0][5].isFixed || feld[0][6].isFixed || feld[1][6].isFixed)
					return true;
			break;
		case "l":
			if(direction == 0)
				if(feld[0][5].isFixed || feld[1][5].isFixed || feld[2][5].isFixed || feld[2][6].isFixed)
					return true;
			if(direction == 1)
				if(feld[1][4].isFixed || feld[1][5].isFixed || feld[1][6].isFixed || feld[0][6].isFixed)
					return true;
			if(direction == 2)
				if(feld[0][5].isFixed || feld[0][6].isFixed || feld[1][6].isFixed || feld[2][6].isFixed)
					return true;
			if(direction == 3)
				if(feld[1][4].isFixed || feld[0][4].isFixed || feld[0][5].isFixed || feld[0][6].isFixed)
					return true;
			break;
		case "t":
			if(direction == 0)
				if(feld[1][4].isFixed || feld[1][5].isFixed || feld[1][6].isFixed || feld[0][5].isFixed)
					return true;
			if(direction == 1)
				if(feld[0][5].isFixed || feld[1][5].isFixed || feld[2][5].isFixed || feld[1][6].isFixed)
					return true;
			if(direction == 2)
				if(feld[0][4].isFixed || feld[0][5].isFixed || feld[0][6].isFixed || feld[1][5].isFixed)
					return true;
			if(direction == 3)
				if(feld[0][6].isFixed || feld[1][6].isFixed || feld[2][6].isFixed || feld[1][5].isFixed)
					return true;
			
			break;
		case "z":
			if(direction == 0 || direction == 2)
				if(feld[1][5].isFixed || feld[1][6].isFixed || feld[0][5].isFixed || feld[0][4].isFixed)
					return true;
			if(direction == 1 || direction == 3)
				if(feld[0][6].isFixed || feld[1][6].isFixed || feld[1][5].isFixed || feld[2][5].isFixed)
					return true;
			break;
		case "iZ":
			if(direction == 0 || direction == 2)
				if(feld[1][4].isFixed || feld[1][5].isFixed || feld[0][5].isFixed || feld[0][6].isFixed)
					return true;
			if(direction == 1 || direction == 3)
				if(feld[0][5].isFixed || feld[1][5].isFixed || feld[1][6].isFixed || feld[2][6].isFixed)
					return true;
			break;
		}
		return false;
		
	}
	

	public void setIsSpeeded(boolean isSpeeded){
		this.isSpeeded = isSpeeded;
	}

	public int getExtraSpeedPoints(){
		return extraSpeedPoints;
	}

	public void resetExtraSpeedPoints(){
		extraSpeedPoints = 0;
	}
	
	public int getNummer(){
		return nummer;
	}
}