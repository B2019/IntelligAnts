import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Game {

	//array of all cells in the map.
	Cell[] cells;
	//current turn out of 300000
	int turn;
	//current tick for the turn. (ant thats taking a go)
	int tick;
	//array of all ants in the game.
	Ant[] ants;
	
	public Game(){
		
		
		
		
	}
	
	public void setUpWorld(String fileName) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String worldtext = "";
		int c;
		while((c = in.read()) != -1){
			worldtext += (char) c;
		}
		System.out.println(worldtext);
	}
	
	public static void main(String [] args) throws IOException{
		Game game = new Game();
		game.setUpWorld("/Users/Adam/Documents/University/Year2/Term2/SE/IntelligAnts/IntelligAnts/src/1.txt");
	}
	
	
}
