import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuGUI extends JFrame {

	private Game game;
	private JPanel playPanel;
	private JTextField[] names;
	private JTextField[] brains;

	public MenuGUI(Game game){
		//Initialize
		this.game = game;
		
		//Setup Menu GUI
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("intelligANTS");
		this.playPanel = new JPanel(new FlowLayout());
		this.add(playPanel);
		
		//Create PLAY button
		JButton playButton = new JButton("PLAY");
		playButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setUpPanel();
			}
		});
		this.playPanel.add(playButton);
		
		//Resize GUI
		this.playPanel.setSize(200, 200);
		this.pack();
	}
	
	
	public void setUpPanel(){
		//Clear GUI
		this.playPanel.removeAll();
		
		//Add label to GUI 
		JLabel q = new JLabel("How many players?");
		this.playPanel.add(q);
		
		//Add 4 player number buttons to GUI
		for(int i = 1; i < 4; i++){
			JButton button = new JButton((i+1)  + " Players");
			button.addActionListener(new PlayerListener(this, i+1));
			this.playPanel.add(button);
		}
		
		//Resize GUI
		this.pack();
	}
	
	public void setPlayers(int players){
		//Set games number of players
		this.game.setNoOfPlayers(players);

		createPlayerFields();
	}
	
	private void createPlayerFields() {
		//Clear GUI
		this.playPanel.removeAll();
		
		//Initialize name and brain fields
		this.names = new JTextField[this.game.getNoOfPlayers()];
		this.brains = new JTextField[this.game.getNoOfPlayers()];
		
		//Choose name and brain fields for each player
		for(int i = 0; i < this.game.getNoOfPlayers(); i++){
			JLabel nameLabel = new JLabel("Player " + (i+1) + " Name:");
			JTextField nameTextField = new JTextField((i+1 + ""));
			JLabel brainLabel = new JLabel("Player " + (i+1) + " Brain file:");
			JTextField brainTextField = new JTextField("sample.b"); //TO DO - Choose using file selector
			
			this.names[i] = nameTextField;
			this.brains[i] = brainTextField;
			
			//Add labels and text fields to GUI
			this.playPanel.add(nameLabel);
			this.playPanel.add(nameTextField);
			this.playPanel.add(brainLabel);
			this.playPanel.add(brainTextField);
		}
		
		//Add start button to GUI
		JButton playbutton = new JButton("Click here to start first match");
		playbutton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[] nameStrings = new String[game.getNoOfPlayers()];
				String[] brainStrings = new String[game.getNoOfPlayers()];
				for(int i = 0; i < game.getNoOfPlayers(); i++){
					nameStrings[i] = names[i].getText();
					brainStrings[i] = brains[i].getText();
				}
				game.createNewPlayers(nameStrings, brainStrings);
				listPlayers();
			}
		});
		playPanel.add(playbutton);
		
		this.pack();
		
	}

	
	public void listPlayers(){
		JPanel matchingsPanel = new JPanel();
		
		//Clear GUI
		this.playPanel.removeAll();
		
		this.game.setUpMatchPairings();
		
		//Add match pairings to GUI
		for(int i = 0; i < this.game.getMatchPairings().length; i++){
			JLabel match = new JLabel("|" + this.game.getPlayer(this.game.getMatchPairings()[i][0]).getName() + " VS " + this.game.getPlayer(game.getMatchPairings()[i][1]).getName() + "|");
			matchingsPanel.add(match);
		}
		matchingsPanel.setLayout(new FlowLayout());
		this.playPanel.add(matchingsPanel);
		
		JButton ranWorldButton = new JButton("Random World");
		ranWorldButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				game.createWorld();
				startSetUp();
			}

		});
		
		JButton loadWorldButton = new JButton("Load a World WIP");
		JTextField worldFileName = new JTextField("1.world");
		loadWorldButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				game.loadWorld("1.world");	//CHANGE!!!
				startSetUp();
			}

		});
		

		playPanel.add(ranWorldButton);
		playPanel.add(loadWorldButton);
		this.pack();
	}
	
	
	public void startSetUp(){
		//Clear GUI
		this.playPanel.removeAll();
		
		//Add start game button
		JButton playButton = new JButton("Start Game");
		playButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				game.startGame();
			}
		});
		playPanel.add(playButton);
		this.pack();
	}
	
	public void endMatch(Player winningPlayer){
		this.playPanel.removeAll();
		JLabel congrats;
		if(winningPlayer != null){
			congrats = new JLabel("Congratulations " + winningPlayer.getName() + "! You win!");
		} else {
			congrats = new JLabel("Oops! It was a draw!");
		}
		this.playPanel.add(congrats);
		
		JButton nextMatch = new JButton("Play Next Match");
		nextMatch.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				game.runNextMatch();
				//System.out.println(game.getCurrentMatch());
				//System.out.println(game.getMatchPairings().length);
			}

		});
		
		playPanel.add(nextMatch);
		this.pack();
		
		
	}
	

	public void finishTournament(Player winner){
		//Clear GUI
		this.playPanel.removeAll();
		
		JLabel congrats = new JLabel("Congratulations " + winner.getName() + "! You are the winner of the tournament!");
		this.playPanel.add(congrats);
		for(int i = 0; i < this.game.getNoOfPlayers(); i++){
			Player player = this.game.getPlayer(i);
			JLabel playerText = new JLabel(player.getName() + "|  Wins: " + player.getWins() + "  Losses: " + player.getLosses() + "Draws: " + player.getDraws());
			this.playPanel.add(playerText);
		}
		
		JButton newGame = new JButton("New Tourney?");
		newGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				playPanel.removeAll();
				setUpPanel();
			}

		});
		
		this.playPanel.add(newGame);
		this.pack();
		
	}
	

}


class PlayerListener implements ActionListener {
	
	private MenuGUI gui;
	int players;

	public PlayerListener(MenuGUI gui, int players){
		this.gui = gui;
		this.players = players;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gui.setPlayers(players);
	}
}