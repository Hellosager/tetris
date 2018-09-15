import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class HighscoreHandler {
	private int points;
	private int level;
	private File highscoreFile = new File("highscore.txt");
	private int[] highscores = new int[5];
	private int[] levels = new int[5];
	private static boolean newHighscore = false;
	public static int newHighNum = 0;
	
	public HighscoreHandler(int points, int level){
		this.points = points;
		this.level = level;
	}
	
	public HighscoreHandler(){
		loadScore();
	}
	
	public void sortScore(int points, int level){
		if(points > highscores[4]){
			newHighscore = true;
			highscores[4] = points;
			levels[4] = level;
			
			for(int i = 4; i != 0; i--){
				if(highscores[i-1] < points){
					highscores[i] = highscores[i-1];
					levels[i] = levels[i-1];
					highscores[i-1] = points;
					levels[i-1] = level;
				}else{
					newHighNum = i;
					break;
				}
			}
			
		}
		

	}
	
	public void loadScore(){
		BufferedReader in = null;
        try {
            // liest die Datei 
            in = new BufferedReader(new InputStreamReader(
                    new FileInputStream(highscoreFile)));
 
            // Benutzt eine For Schleife um die 5 Zahlen der Highscore zu ersetzen
            for (int i = 0; i < 5; i++) {
                highscores[i] = Integer.parseInt(in.readLine());
            }
            // Benutzt eine For Schleife um die 5 Level zu laden
            for(int i = 0; i < 5; i++){
            	levels[i] = Integer.parseInt(in.readLine());
            }
 
        } catch (IOException e) {
        } catch (NumberFormatException e) {
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
            }
        }
	}
	
	public void saveScore(){
		 BufferedWriter out = null;
	        try {
	 
	            // Erstellt eine Datei auf der Speicherkarte 
	        	highscoreFile.createNewFile();
	            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(highscoreFile)));
	 
	            // Benutzt eine For Schleife um 5 Highscores zur Datei hinzu zufügen
	            for (int i = 0; i < 5; i++) {
	                out.write(Integer.toString(highscores[i]));
	                out.write("\n");
	            }
	            //	Benutzt eine For Schleife um 5 Level zur Datei hinzu zufügen
	            for(int i = 0; i < 5; i++){
	            	out.write(Integer.toString(levels[i]));
	            	out.write("\n");
	            }
	 
	           // Hier werden Fehler beim Dateisystem gehandhabt
	 
	        } catch (IOException e) {
	        } finally {
	            try {
	                if (out != null)
	                    out.close();
	            } catch (IOException e) {
	            }
	        }
	}

	public boolean isNewHighscore(){
		if(newHighscore)
			return true;
		return false;
	}
	public int getNewHighNum(){
		return newHighNum;
	}
	
	public int[] getHighscores(){
//		loadScore();
		return highscores;
	}
	
	public int[] getLevels(){
		return levels;
	}
	
	public void setNewHighscore(boolean b){
		newHighscore = b;
	}
}
