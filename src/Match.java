import java.io.IOException;
import java.util.Scanner;


public class Match {

	String accountAName;
	String accountBName;
	int turn; //current turn out of 300000
	int tick; //current tick for the turn. (ant thats taking a go)
	World world;
	

public Match(){
	turn = 0;
	tick = 0;
	
	Scanner in = new Scanner(System.in);
	//Get account 1
	System.out.println("Please type in player A's name:");
	accountAName = in.nextLine();
	//Choose account 1 brain WIP
		//Load brain or create new brain
	//Get account 2
	System.out.println("Please type in player B's name:");
	accountBName = in.nextLine();
	//Choose account 2 brain WIP
		//Load brain or create new brain
	//Get seed
	System.out.println("Please type in seed:");
	int seed = in.nextInt();
	
	//Get world
			System.out.println("Please choose if you would like to load an old world or create a new one:");
			System.out.println("1: Load World");
			System.out.println("2: Create New Random World");
			int i = in.nextInt();
			
			if (i == 1) {
			//Load World
				System.out.println("Please type in your world file name:");
				String fileName = in.nextLine();// CHANGE
				try {
				WorldGen worldGen = new WorldGen();
				world = worldGen.loadWorld(fileName);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (i == 2) {
				//Create random world
				WorldGen worldGen = new WorldGen();
				world = worldGen.newWorld(150, 150);
				
			}
}

	public static void main(String[] args) {
		Match test = new Match();
	}
	
}
