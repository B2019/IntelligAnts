import java.awt.Component;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;


public class Game {

	private Match match;
	private int noOfPlayers = 0;
	private int seed;
	private Player[] players;
	private MenuGUI gui;
	private GameGUI gameGUI;
	private int[][] matchPairings;
	private World world;
	private int currentMatch;
	private int winner;
	private Boolean halfTime;
	
	public Game() {
		//Load menu GUI
		this.gui = new MenuGUI(this);
	}
	
	public void setSeed(int seed){
		this.seed = seed;
	}
	
	public void createNewPlayers(String[] names, String[] brains){
		//Create each player and give them a brain
		this.players = new Player[noOfPlayers];
		for(int i = 0; i < noOfPlayers; i++){
			try {
				this.players[i] = new Player(names[i], new Brain(brains[i]));
			} catch (BrainCompilerException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void setUpMatchPairings() {
		int noOfMatchPairings = ((this.noOfPlayers - 1) * this.noOfPlayers) / 2;
		this.matchPairings = new int[noOfMatchPairings][2];
		
		int pair = 0;
		for (int i = 0; i < noOfPlayers; i++) {
			for (int j = i + 1; j < noOfPlayers; j++) {
				this.matchPairings[pair][0] = i;
				this.matchPairings[pair][1] = j;
				pair++;
			}
		}
	}
	
	public void startGame(){
		Collections.shuffle(Arrays.asList(matchPairings)); 
		this.currentMatch = 0;
		this.halfTime = true;
		
		Player playerA = players[matchPairings[currentMatch][0]];
		Player playerB = players[matchPairings[currentMatch][1]];
		
		this.match = new Match(world, seed, playerA.getName(), playerB.getName(), playerA.getBrain(), playerB.getBrain());
		new Thread(new GameGUI(match, this)).start();
		
			
	}
	
	/*
	public void setWinner(int winner){
		this.winner = winner;
	}*/
	
	
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
		if(!this.halfTime){
			this.currentMatch++;
		}
		System.out.println("currentMatch: " + this.currentMatch);
		System.out.println("num of match pairings: " + this.matchPairings.length);
		if(this.currentMatch == this.matchPairings.length){
			finishTournament();
		} else{
			Player playerA = players[matchPairings[currentMatch][0]];
			Player playerB = players[matchPairings[currentMatch][1]];
			this.halfTime = !this.halfTime;
			this.match = new Match(world, seed, playerA.getName(), playerB.getName(), playerA.getBrain(), playerB.getBrain());

			new Thread(new GameGUI(match, this)).start();
		}
	}
	
	
	public void finishTournament(){
		//Work out winner
		Player winner = players[0];
		for (int i = 1; i < noOfPlayers; i++){
			if ((winner.getWins() * 2) + (winner.getDraws()) < (players[i].getWins() * 2) + (players[i].getDraws())) {
				winner = players[i];
			}
		}
		
		this.gui.finishTournament(winner);
		
	}

	public int getNoOfPlayers() {
		return noOfPlayers;
	}
	
	public void setNoOfPlayers(int noOfPlayers){
		this.noOfPlayers = noOfPlayers;
	}
	
    public static void main(String[] args) {
    	//Start up game
    	new Game();  
    }

	public int[][] getMatchPairings() {
		return matchPairings;
	}



	public Player getPlayer(int i) {
		return players[i];
	}

	public void createWorld() {
		try {
			this.world = new World(this.seed); //Create new world using WorldGen
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (WorldGenException e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadWorld(String text) {
		try {
			this.world = new World(text, this.seed); //Load world from file
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		} catch (WorldGenException e) {
			e.printStackTrace();
		}	
	}

	public int getCurrentMatch() {
		return currentMatch;
	}
}
