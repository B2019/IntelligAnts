import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;


public class Game {

	TesterGUI gui;
	Match match;
	
	public Game() {
		this.gui = new TesterGUI(150,150, this);
		createTournament();
	}
	
	public void createTournament() {
		
		
		//Create players
		int noOfPlayers = 4; //Get number of players in tournament
		Player[] players = new Player[noOfPlayers];
		for (int playerNo = 0; playerNo < noOfPlayers; playerNo++) {
			//Get player name
			String playerName = ("Player " + playerNo);
			//Get player brain
			Brain playerBrain = new Brain("testbrain.brain");
			//Create player
			players[playerNo] = new Player(playerName, playerBrain);
		}
		
		
		//Create seed
		int seed = 1234;
		
		
		//Choose world(s?)
		World world = null;
		//Create new random world (need to implement seed!)
		/*
		try {
			world = new World(150, 150); //Create new world using WorldGen
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}	
		*/
		//Load world
		try {
			world = new World("1.world"); //Load world from file
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		
		
		//Create matches list
		int noOfMatchPairings = ((noOfPlayers - 1) * noOfPlayers) / 2;
		int[][] matchPairings = new int[noOfMatchPairings][2];
		int pair = 0;
		for (int i = 0; i < noOfPlayers; i++) {
			for (int j = i + 1; j < noOfPlayers; j++) {
				matchPairings[pair][0] = i;
				matchPairings[pair][1] = j;
				pair++;
			}
		}
		
		
		//Shuffle matches
		Collections.shuffle(Arrays.asList(matchPairings)); 
		
		
		//Play Matches
		for (int i = 0; i < noOfMatchPairings; i++) {
			Player playerA;
			Player playerB;
			int winner;
			
			//Play match 1
			playerA = players[matchPairings[i][0]];
			playerB = players[matchPairings[i][1]];

			this.match = new Match(world, seed, playerA.getName(), playerB.getName(), playerA.getBrain(), playerB.getBrain());
			winner = this.match.runMatch(gui);
			if (winner == 1) {
				playerA.setWins(playerA.getWins() + 1);
				playerB.setLosses(playerB.getLosses() + 1);
			} else if (winner == 2) {
				playerB.setWins(playerB.getWins() + 1);
				playerA.setLosses(playerA.getLosses() + 1);
			} else {
				playerA.setDraws(playerA.getDraws() + 1);
				playerB.setDraws(playerB.getDraws() + 1);
			}
			
			//Play match 2
			playerA = players[matchPairings[i][1]];
			playerB = players[matchPairings[i][0]];
			
			this.match = new Match(world, seed, playerA.getName(), playerB.getName(), playerA.getBrain(), playerB.getBrain());
			winner = this.match.runMatch(gui);
			if (winner == 1) {
				playerA.setWins(playerA.getWins() + 1);
				playerB.setLosses(playerB.getLosses() + 1);
			} else if (winner == 2) {
				playerB.setWins(playerB.getWins() + 1);
				playerA.setLosses(playerA.getLosses() + 1);
			} else {
				playerA.setDraws(playerA.getDraws() + 1);
				playerB.setDraws(playerB.getDraws() + 1);
			}
		}
		
		//Get overall winner
		Player winner = players[0];
		for (int i = 1; i < noOfPlayers; i++){
			if ((winner.getWins() * 2) + (winner.getDraws()) < (players[i].getWins() * 2) + (players[i].getDraws())) {
				winner = players[i];
			}
		}
			
	}
	

    public static void main(String[] args) {
    	new Game();//makes new ButtonGrid with 2 parameters
    	
        
    }

}
