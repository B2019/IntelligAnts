import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MenuGUI2 extends JFrame {

	Game game;
	JPanel playPanel;
	JTextField[] names;
	JTextField[] brains;

	public MenuGUI2(Game game){
		this.game = game;
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("intelligANTS");
		playPanel = new JPanel(new FlowLayout());
		
		JButton playButton = new JButton("PLAY");

		playButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				playPanel.removeAll();
				setUpPanel();
			}

		});

		this.add(playPanel);
		playPanel.add(playButton);
		playPanel.setSize(200, 200);
		this.pack();


	}
	
	
	public void setUpPanel(){
		JLabel q = new JLabel("How many players?");
		playPanel.add(q);
		for(int i = 1; i < 4; i++){
			JButton button = new JButton((i+1)  + " Players");
			button.addActionListener(new PlayerListener(this, i+1));
			playPanel.add(button);
		}
		this.pack();
		
	}

	public void setPlayers(int players){
		game.setNoOfPlayers(players);
		createPlayerFields();
	}
	
	private void createPlayerFields() {
		playPanel.removeAll();
		names = new JTextField[game.noOfPlayers];
		brains = new JTextField[game.noOfPlayers];
		for(int i = 0; i < game.noOfPlayers; i++){
			JLabel nameLabel = new JLabel("Player " + (i+1) + " Name:");
			JTextField nameText = new JTextField((i+1 + ""));
			JLabel brainLabel = new JLabel("Player " + (i+1) + " Brain file:");
			JTextField brainText = new JTextField("testbrain.brain");
			names[i] = nameText;
			brains[i] = brainText;
			playPanel.add(nameLabel);
			playPanel.add(nameText);
			playPanel.add(brainLabel);
			playPanel.add(brainText);
		}
		
		JButton playbutton = new JButton("Click here to start first match");
		playbutton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[] nameStrings = new String[game.noOfPlayers];
				String[] brainStrings = new String[game.noOfPlayers];
				for(int i = 0; i < game.noOfPlayers; i++){
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
		playPanel.removeAll();
		JPanel matchingsPanel = new JPanel();
		game.createTournament();
		
		for(int i = 0; i < game.matchPairings.length; i++){
			JLabel match = new JLabel("|" + game.players[game.matchPairings[i][0]].getName() + " VS " + game.players[game.matchPairings[i][1]].getName() + "|");
			matchingsPanel.add(match);
		}
		matchingsPanel.setLayout(new FlowLayout());
		playPanel.add(matchingsPanel);
		JButton worldButton1 = new JButton("Random World");
		worldButton1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				worldSetUp();
			}

		});
		JButton worldButton2 = new JButton("Load a World (WIP)");
		playPanel.add(worldButton1);
		playPanel.add(worldButton2);
		this.pack();
	}
	
	
	public void worldSetUp(){
		
		playPanel.removeAll();
		game.setWorld();
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
	
	public void  playMatch(World world, int seed, Player playerA, Player playerB){
		
		Match match = new Match(world, seed, playerA.getName(), playerB.getName(), playerA.getBrain(), playerB.getBrain());
		Boolean matchGo = false;
		while (matchGo == false){
			matchGo = match.canGameGo();
		}
		
//		this.remove(playPanel);
//		setSize(1200, 1401);
//		
//		
//		
//		this.add(gui);
//		setSize(1201, 1401);
	}
	

//	public static void main(String[] args) {
//		Game game = new Game();
//		MenuGUI2 menu = new MenuGUI2(game);
//	}

}

class PlayerListener implements ActionListener {
	
	private MenuGUI2 gui;
	int players;

	public PlayerListener(MenuGUI2 gui, int players){
		this.gui = gui;
		this.players = players;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gui.setPlayers(players);
	}
    
}

class PlayListener implements ActionListener {
	
	private Match match;

	public PlayListener(Match match){
		this.match = match;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		match.runMatch();
	}
    
}



