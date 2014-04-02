/*TO DO
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class Game {

	Match match;
	
	public Game() {
		
	}
	
	public void createMatch() {
		
		int seed = 12345;
		
		//Create new random world (need to implement seed!)
		World world = null;
		try {
			world = new World(150, 150); //Create new world using WorldGen
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}	
		
		//Load world
		try {
			world = new World("1.world"); //Load world from file
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		
		
		
		String redName = "Team Red";
		String blackName = "Team Black";
		Brain redBrain = new Brain("testbrain.brain");
		Brain blackBrain = new Brain("testbrain.brain");
		
		//Create match
		this.match = new Match(world, seed, redName, blackName, redBrain, blackBrain);
		
	}
	
	public Match getMatch() {
		return match;
	}


	

}
