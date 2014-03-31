<<<<<<< HEAD
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
=======
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class Game {

	Match match;
	GUI gui;
	
	public Game() {
		gui = new GUI(this);
	}
	
	public void createMatch() {
		
		//Load world
		World world = null;
		try {
			//world = new World("1.world"); //Load world from file
			world = new World(150, 150); //Create new world using WorldGen
			world.printWorld();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}	
		
		int seed = 12345;
		String redName = "Team Red";
		String blackName = "Team Black";
		Brain redBrain = new Brain("testbrain.brain");
		Brain blackBrain = new Brain("testbrain.brain");
		
		
		//Create match
		this.match = new Match(world, seed, redName, blackName, redBrain, blackBrain);
		
		
		/*
		Scanner in = new Scanner(System.in);
		//Get world
				World world;
				System.out.println("Please choose if you would like to load an old world or create a new one:");
				System.out.println("1: Load World");
				System.out.println("2: Create New Random World");
				int i = in.nextInt();
				if (i == 1) {
					try {
						world = loadWorld(fileName);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				} else if (i == 2) {
					//Create random world
					WorldGen worldGen = new WorldGen();
					world = worldGen.newWorld(150, 150);
				}
				*/
	}


	
	public static void main(String[] args) {
		Game game = new Game();
	}
}
>>>>>>> FETCH_HEAD
