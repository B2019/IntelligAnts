import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;


public class Game {

	Match match;
	int noOfPlayers = 0;
	int seed;
	public Player[] players;
	MenuGUI2 gui;
	GameGUI gameGUI;
	int[][] matchPairings;
	int noOfMatchPairings;
	World world;
	int currentMatch;
	int winner;
	Boolean halfTime;
	
	public Game() {
		gui = new MenuGUI2(this);
	}
	
	public void setNoOfPlayers(int x){
		noOfPlayers = x;
	}
	
	public void setSeed(int seed){
		this.seed = seed;
	}
	
	
	public void createNewPlayers(String[] names, String[] brains){
		players = new Player[noOfPlayers];
		
		for(int i = 0; i < noOfPlayers; i++){
			players[i] = new Player(names[i], new Brain(brains[i]));
		}
		
	}
	
	public void createTournament() {
		
		
		noOfMatchPairings = ((noOfPlayers - 1) * noOfPlayers) / 2;
		matchPairings = new int[noOfMatchPairings][2];
		int pair = 0;
		for (int i = 0; i < noOfPlayers; i++) {
			for (int j = i + 1; j < noOfPlayers; j++) {
				matchPairings[pair][0] = i;
				matchPairings[pair][1] = j;
				pair++;
			}
		}
		System.out.println("Created");
	}
		
	public void setWorld(){
		world = new World(150, 150);
	}
		
		
	public void startGame(){
		Collections.shuffle(Arrays.asList(matchPairings)); 
		Player playerA;
		Player playerB;
		currentMatch = 0;
		
		playerA = players[matchPairings[currentMatch][0]];
		playerB = players[matchPairings[currentMatch][1]];

		halfTime = true;
		this.match = new Match(world, seed, playerA.getName(), playerB.getName(), playerA.getBrain(), playerB.getBrain());
		new Thread(new GameGUI(match, this)).start();
		
			
	}
	
	public void setWinner(int winner){
		this.winner = winner;
	}
	
	public void getWinner(int winner){

		Player playerA = players[matchPairings[currentMatch][0]];
		Player playerB = players[matchPairings[currentMatch][1]];
		
		if (winner == 1) {
			playerA.setWins(playerA.getWins() + 1);
			playerB.setLosses(playerB.getLosses() + 1);
			gui.endMatch(playerA);
		} else if (winner == 2) {
			playerB.setWins(playerB.getWins() + 1);
			playerA.setLosses(playerA.getLosses() + 1);
			gui.endMatch(playerB);
		} else {
			playerA.setDraws(playerA.getDraws() + 1);
			playerB.setDraws(playerB.getDraws() + 1);
			gui.endMatch(null);
		}
		
	}
	
	public void runNextMatch(){
		if(halfTime == false){currentMatch++;}
		System.out.println("currentMatch: " + currentMatch);
		System.out.println("num of match pairings: " + noOfMatchPairings);
		if(currentMatch == noOfMatchPairings){
			finishTournament();
		} else if(halfTime == false){
			Player playerA;
			Player playerB;
			
			playerA = players[matchPairings[currentMatch][0]];
			playerB = players[matchPairings[currentMatch][1]];
			halfTime = true;
			this.match = new Match(world, seed, playerA.getName(), playerB.getName(), playerA.getBrain(), playerB.getBrain());
			new Thread(new GameGUI(match, this)).start();
			
		} else if (halfTime == true){
			Player playerA;
			Player playerB;			
			playerA = players[matchPairings[currentMatch][1]];
			playerB = players[matchPairings[currentMatch][0]];
			halfTime = false;
			this.match = new Match(world, seed, playerA.getName(), playerB.getName(), playerA.getBrain(), playerB.getBrain());
			new Thread(new GameGUI(match, this)).start();
		}
	}
	
	public void finishTournament(){
		Player winner = players[0];
		for (int i = 1; i < noOfPlayers; i++){
			if ((winner.getWins() * 2) + (winner.getDraws()) < (players[i].getWins() * 2) + (players[i].getDraws())) {
				winner = players[i];
			}
		}
		
		gui.finishTournament(winner);
		
	}
	
	
	
//		
//		//Shuffle matches
//		
//		
//		
//		//Play Matches
//		for (int i = 0; i < noOfMatchPairings; i++) {
//			Player playerA;
//			Player playerB;
//			int winner;
//			
//			//Play match 1
//			playerA = players[matchPairings[i][0]];
//			playerB = players[matchPairings[i][1]];
//
//			this.match = new Match(world, seed, playerA.getName(), playerB.getName(), playerA.getBrain(), playerB.getBrain());
//			winner = this.match.runMatch();
//			if (winner == 1) {
//				playerA.setWins(playerA.getWins() + 1);
//				playerB.setLosses(playerB.getLosses() + 1);
//			} else if (winner == 2) {
//				playerB.setWins(playerB.getWins() + 1);
//				playerA.setLosses(playerA.getLosses() + 1);
//			} else {
//				playerA.setDraws(playerA.getDraws() + 1);
//				playerB.setDraws(playerB.getDraws() + 1);
//			}
//			
//			//Play match 2
//			playerA = players[matchPairings[i][1]];
//			playerB = players[matchPairings[i][0]];
//			
//			this.match = new Match(world, seed, playerA.getName(), playerB.getName(), playerA.getBrain(), playerB.getBrain());
//			winner = this.match.runMatch();
//			if (winner == 1) {
//				playerA.setWins(playerA.getWins() + 1);
//				playerB.setLosses(playerB.getLosses() + 1);
//			} else if (winner == 2) {
//				playerB.setWins(playerB.getWins() + 1);
//				playerA.setLosses(playerA.getLosses() + 1);
//			} else {
//				playerA.setDraws(playerA.getDraws() + 1);
//				playerB.setDraws(playerB.getDraws() + 1);
//			}
//		}
//		
//		//Get overall winner
//		Player winner = players[0];
//		for (int i = 1; i < noOfPlayers; i++){
//			if ((winner.getWins() * 2) + (winner.getDraws()) < (players[i].getWins() * 2) + (players[i].getDraws())) {
//				winner = players[i];
//			}
//		}
//			
//	}
	

	
	
    public static void main(String[] args) {
    	new Game();//makes new ButtonGrid with 2 parameters
    	
        
    }

}
