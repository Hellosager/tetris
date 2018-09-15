import java.awt.Color;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class Details extends JPanel{
	private JPanel nextStone = new JPanel();
	private JPanel punkteContainer = new JPanel();
	private JLabel punkteLabel = new JLabel("000000");
	private JPanel levelContainer = new JPanel();
	private JLabel levelLabel = new JLabel("00");
	private JPanel rowContainer = new JPanel();
	private JLabel rowLabel = new JLabel("000");
	public static final DecimalFormat levelFormat = new DecimalFormat("00");
	public static final DecimalFormat punkteFormat = new DecimalFormat("000000");
	public static final DecimalFormat rowFormat = new DecimalFormat("000");
	private String form;
	private Color c;
	private int points = 0;
	private int level = 0;
	private int direction;
	
	public Details(){
		setLayout(null);
		setBackground(Color.DARK_GRAY);

		nextStone.setBounds(21, 25, 100, 100);
		nextStone.setBorder(new LineBorder(Color.BLACK));
		nextStone.setBackground(Color.LIGHT_GRAY);
		
		punkteContainer.setBounds(21, 145, 100, 50);
		punkteContainer.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Punkte"));
		punkteContainer.setBackground(Color.LIGHT_GRAY);
		punkteLabel.setOpaque(false);
		punkteContainer.add(punkteLabel);
		
		levelContainer.setBounds(21, 215, 100, 50);
		levelContainer.setBorder(new TitledBorder(new LineBorder(Color.BLACK),"Level"));
		levelContainer.setBackground(Color.LIGHT_GRAY);
		levelLabel.setOpaque(false);
		levelContainer.add(levelLabel);
		
		rowContainer.setBounds(21, 285, 100, 50);
		rowContainer.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Reihen"));
		rowContainer.setBackground(Color.LIGHT_GRAY);
		rowLabel.setOpaque(false);
		rowContainer.add(rowLabel);
		
		
		add(nextStone);
		add(punkteContainer);
		add(levelContainer);
		add(rowContainer);
	}
	
	public void setNextStone(){
		Random r = new Random();
		switch(r.nextInt(7)){
		case 0: form = "block";	break;
		case 1: form = "i";	break;
		case 2: form = "iL"; break;
		case 3: form = "l"; break;
		case 4: form = "iZ"; break;
		case 5: form = "t"; break;
		case 6: form = "z"; break;
		}
		
		switch(r.nextInt(7)){
		case 0: c = Color.BLUE; break;
		case 1: c = new Color(0, 153, 51); break; 	// Green
		case 2: c = new Color(255, 204, 51); break;	// Yellow
		case 3: c = new Color(255, 102, 0); break;	// Orange
		case 4: c = new Color(204, 0, 0); break;	// Red
		case 5: c = new Color(102, 0, 153); break;
		case 6: c = Color.BLACK; break;
		}
		
		switch(r.nextInt(4)){
			case 0: direction  = 0;	break;
			case 1: direction = 1;	break;
			case 2: direction = 2; 	break;
			case 3: direction = 3;	break;
		}
		
		
	}

	public void showNextStone(){		
		JLabel[][] matrix;
		
		switch(form){
		case "block": 
			matrix = newMatrix(4,4);
			matrix[1][1].setBackground(c);
			matrix[1][2].setBackground(c);
			matrix[2][1].setBackground(c);
			matrix[2][2].setBackground(c);
			break;
		case "i":
			switch(direction){
				case 0:
					matrix = newMatrix(5,6);
					matrix[2][1].setBackground(c);	matrix[2][2].setBackground(c);	matrix[2][3].setBackground(c);	matrix[2][4].setBackground(c);	break;
				case 1: 
					matrix = newMatrix(6,5);
					matrix[1][2].setBackground(c);	matrix[2][2].setBackground(c);	matrix[3][2].setBackground(c);	matrix[4][2].setBackground(c);	break;
				case 2:
					matrix = newMatrix(5,6);
					matrix[2][1].setBackground(c);	matrix[2][2].setBackground(c);	matrix[2][3].setBackground(c);	matrix[2][4].setBackground(c);	break;
				case 3: 
					matrix = newMatrix(6,5);
					matrix[1][2].setBackground(c);	matrix[2][2].setBackground(c);	matrix[3][2].setBackground(c);	matrix[4][2].setBackground(c);	break;
				}
			break;
		case "iL":
			switch(direction){
			case 0:
				matrix = newMatrix(5,4);
				matrix[1][2].setBackground(c);	matrix[2][2].setBackground(c);	matrix[3][2].setBackground(c);	matrix[3][1].setBackground(c);	break;
			case 1: 
				matrix = newMatrix(4,5);
				matrix[1][1].setBackground(c);	matrix[2][1].setBackground(c);	matrix[2][2].setBackground(c);	matrix[2][3].setBackground(c);	break;
			case 2:
				matrix = newMatrix(5,4);
				matrix[1][2].setBackground(c);	matrix[1][1].setBackground(c);	matrix[2][1].setBackground(c);	matrix[3][1].setBackground(c);	break;
			case 3: 
				matrix = newMatrix(4,5);
				matrix[1][1].setBackground(c);	matrix[1][2].setBackground(c);	matrix[1][3].setBackground(c);	matrix[2][3].setBackground(c);	break;
			}
			break;
		case "l":
			switch(direction){
			case 0:
				matrix = newMatrix(5,4);
				matrix[1][1].setBackground(c);	matrix[2][1].setBackground(c);	matrix[3][1].setBackground(c);	matrix[3][2].setBackground(c);	break;
			case 1: 
				matrix = newMatrix(4,5);
				matrix[2][1].setBackground(c);	matrix[1][1].setBackground(c);	matrix[1][2].setBackground(c);	matrix[1][3].setBackground(c);	break;
			case 2:
				matrix = newMatrix(5,4);
				matrix[1][1].setBackground(c);	matrix[1][2].setBackground(c);	matrix[2][2].setBackground(c);	matrix[3][2].setBackground(c);	break;
			case 3: 
				matrix = newMatrix(4,5);
				matrix[2][1].setBackground(c);	matrix[2][2].setBackground(c);	matrix[2][3].setBackground(c);	matrix[1][3].setBackground(c);	break;
			}
			break;
		case "iZ":		// klappt nicht
			switch(direction){
			case 0:
				matrix = newMatrix(4,5);
				matrix[2][1].setBackground(c);	matrix[2][2].setBackground(c);	matrix[1][2].setBackground(c);	matrix[1][3].setBackground(c);	break;
			case 1: 
				matrix = newMatrix(5,4);
				matrix[3][2].setBackground(c);	matrix[2][2].setBackground(c);	matrix[2][1].setBackground(c);	matrix[1][1].setBackground(c);	break;
			case 2:
				matrix = newMatrix(4,5);
				matrix[2][1].setBackground(c);	matrix[2][2].setBackground(c);	matrix[1][2].setBackground(c);	matrix[1][3].setBackground(c);	break;
			case 3: 
				matrix = newMatrix(5,4);
				matrix[3][2].setBackground(c);	matrix[2][2].setBackground(c);	matrix[2][1].setBackground(c);	matrix[1][1].setBackground(c);	break;
			}
			break;	
		case "t":		// klappt
			switch(direction){
			case 0:
				matrix = newMatrix(4,5);
				matrix[2][1].setBackground(c);	matrix[2][2].setBackground(c);	matrix[1][2].setBackground(c);	matrix[2][3].setBackground(c);	break;
			case 1: 
				matrix = newMatrix(5,4);
				matrix[1][1].setBackground(c);	matrix[2][1].setBackground(c);	matrix[3][1].setBackground(c);	matrix[2][2].setBackground(c);	break;
			case 2:
				matrix = newMatrix(4,5);
				matrix[1][1].setBackground(c);	matrix[1][2].setBackground(c);	matrix[1][3].setBackground(c);	matrix[2][2].setBackground(c);	break;
			case 3: 
				matrix = newMatrix(5,4);
				matrix[1][2].setBackground(c);	matrix[2][2].setBackground(c);	matrix[3][2].setBackground(c);	matrix[2][1].setBackground(c);	break;
			}
			break;		
		case "z":		// klappt
			switch(direction){
			case 0:
				matrix = newMatrix(4,5);
				matrix[1][1].setBackground(c);	matrix[1][2].setBackground(c);	matrix[2][2].setBackground(c);	matrix[2][3].setBackground(c);	break;
			case 1: 
				matrix = newMatrix(5,4);
				matrix[3][1].setBackground(c);	matrix[2][1].setBackground(c);	matrix[2][2].setBackground(c);	matrix[1][2].setBackground(c);	break;
			case 2:
				matrix = newMatrix(4,5);
				matrix[1][1].setBackground(c);	matrix[1][2].setBackground(c);	matrix[2][2].setBackground(c);	matrix[2][3].setBackground(c);	break;
			case 3: 
				matrix = newMatrix(5,4);
				matrix[3][1].setBackground(c);	matrix[2][1].setBackground(c);	matrix[2][2].setBackground(c);	matrix[1][2].setBackground(c);	break;
			}
			break;		
		}
		
	}

	public void updateNextStone(){
		nextStone.removeAll();
		nextStone.setBackground(Color.LIGHT_GRAY);
		nextStone.repaint();
		setNextStone();
		showNextStone();
		nextStone.revalidate();
	}
	
	public JLabel[][] newMatrix(int y, int x){
		JLabel[][] matrix = new JLabel[y][x];
		nextStone.setLayout(new GridLayout(y, x, 1,1));
		for(int i = 0; i<y; i++)
			for(int k = 0; k<x; k++){
				matrix[i][k] = new JLabel();	
				matrix[i][k].setOpaque(true);
				nextStone.add(matrix[i][k]);
				matrix[i][k].setBackground(Color.LIGHT_GRAY);
			}

		return matrix;
	}
	
	public Color getNextStoneColor(){
		return c;
		}

	public String getNextStoneForm(){
		return form;
	}

	public void addPoints(int rowPoints, int extraSpeedPoints){
		points += rowPoints;
		points += extraSpeedPoints;
		punkteLabel.setText(punkteFormat.format(points));
	}
	
	public void updateLevel(int level){
		this.level = level;
		levelLabel.setText(levelFormat.format(level));
	}
	
	
	public int getLevel(){
		return level;
	}
	
	public int getScore(){
		return points;
	}
	
	public void updateDeletedRows(int deletedRows){
		rowLabel.setText(rowFormat.format(deletedRows));
	}
	
	public int getNextStoneDirection(){
		return direction;
	}
	
}
