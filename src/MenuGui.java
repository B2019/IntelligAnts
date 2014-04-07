

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MenuGui extends JFrame{
	JPanel playPanel = new JPanel();
	Container contentPane = getContentPane();
	int seed;
	String p1Name;
	String p2Name;
	String p3Name;
	String p4Name;
	String p1Brain;
	String p2Brain;
	String p3Brain;
	String p4Brain;
	JTextField player1Name;
	JTextField player2Name;
	JTextField player3Name;
	JTextField player4Name;
	JTextField player1Brain;
	JTextField player2Brain;
	JTextField player3Brain;
	JTextField player4Brain;
	GameGUI matchGUI;
	int players = 0;
	Game game;
	
	JTextField seedField = new JTextField("");
	
	public MenuGui(Game game){
		this.game = game;
		setTitle("intelligANTS");
		setResizable(false);
		setSize(100, 100);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setLayout(new BorderLayout());
		
		JButton playButton = new JButton("PLAY");
		
		
		JPanel logoPanel = new JPanel();
		
		BufferedImage logo;
		try {
			//file location needs to be changed
			logo = ImageIO.read(new File("/Users/ryancoughlan/Downloads/itelligantslogo1.png"));
			JLabel logoLabel = new JLabel(new ImageIcon(logo));
			logoPanel.add(logoLabel);
		} catch (IOException e) {
			e.printStackTrace();
		}
		playButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				playPanel.removeAll();
				addForm();
				setSize(1000, 750);
			}
			
		});
		
		
		playPanel.add(playButton);
		playPanel.setSize(200, 200);
		
		
		contentPane.setVisible(true);
		contentPane.add(playPanel, BorderLayout.SOUTH);
		contentPane.add(logoPanel, BorderLayout.NORTH);
		//look to avoid having to resize in order to update
		setSize(800, 400);
		
	}
	
	public void addForm(){
		JPanel playerPanel = new JPanel();
		playerPanel.setLayout(new GridLayout(4,1));
		
		JRadioButton onePlayer = new JRadioButton("One Player", true);
		onePlayer.setMnemonic(KeyEvent.VK_1);
		
		JRadioButton twoPlayer = new JRadioButton("Two Player");
		twoPlayer.setMnemonic(KeyEvent.VK_2);
		
		JRadioButton threePlayer = new JRadioButton("Three Player");
		threePlayer.setMnemonic(KeyEvent.VK_3);
		
		JRadioButton fourPlayer = new JRadioButton("Four Player");
		fourPlayer.setMnemonic(KeyEvent.VK_4);
		
		ButtonGroup playerGroup = new ButtonGroup();
		playerGroup.add(onePlayer);
		playerGroup.add(twoPlayer);
		playerGroup.add(threePlayer);
		playerGroup.add(fourPlayer);
		
		playerPanel.add(onePlayer);
		playerPanel.add(twoPlayer);
		playerPanel.add(threePlayer);
		playerPanel.add(fourPlayer);
		
		onePlayer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				playerForm(1);
				players = 1;
				setSize(1000, 750);
			}
			
		});
		
		twoPlayer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				playerForm(2);
				players = 2;
				setSize(1000, 750);
			}
			
		});
		
		threePlayer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				playerForm(3);
				players = 3;
				setSize(1000, 750);
			}
			
		});
		
		fourPlayer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				playerForm(4);
				players = 4;
				setSize(1000, 750);
			}
			
		});
		
		contentPane.add(playerPanel, BorderLayout.WEST);
	}

	public void playerForm(int noOfPlayers){
		JPanel seedPanel = new JPanel();
		JPanel nameBrainPanel = new JPanel();
		JPanel playPanel = new JPanel();
		nameBrainPanel.setLayout(new FlowLayout());
		
		//declaration of all labels and buttons
		JButton playGame = new JButton("PLAY");
		playPanel.add(playGame);
		JLabel player1Label = new JLabel("Player 1 Name: ");
		player1Name = new JTextField("");
		JLabel player2Label = new JLabel("Player 2 Name: ");
		player2Name = new JTextField("");
		JLabel player3Label = new JLabel("Player 3 Name: ");
		player3Name = new JTextField("");
		JLabel player4Label = new JLabel("Player 4 Name: ");
		player4Name = new JTextField("");
		JLabel player1BLabel = new JLabel("Player 1 Brain File: ");
		player1Brain = new JTextField("");
		JLabel player2BLabel = new JLabel("Player 2 Brain File: ");
		player2Brain = new JTextField("");
		JLabel player3BLabel = new JLabel("Player 3 Brain File: ");
		player3Brain = new JTextField("");
		JLabel player4BLabel = new JLabel("Player 4 Brain File: ");
		player4Brain = new JTextField("");
		JRadioButton yes = new JRadioButton("Yes");
		JRadioButton no = new JRadioButton("No");
		ButtonGroup seedGroup = new ButtonGroup();
		seedGroup.add(yes);
		seedGroup.add(no);
		
		//if statement adding textfields to the menu based on the number
		//of players
		int x = noOfPlayers;
		if(x == 1){
			nameBrainPanel.add(player1Label);
			nameBrainPanel.add(player1Name);
			nameBrainPanel.add(player1BLabel);
			nameBrainPanel.add(player1Brain);
			nameBrainPanel.revalidate();
		}else if(x == 2){
			nameBrainPanel.add(player1Label);
			nameBrainPanel.add(player1Name);
			nameBrainPanel.add(player2Label);
			nameBrainPanel.add(player2Name);
			nameBrainPanel.add(player1BLabel);
			nameBrainPanel.add(player1Brain);
			nameBrainPanel.add(player2BLabel);
			nameBrainPanel.add(player2Brain);
			nameBrainPanel.revalidate();
		}else if(x == 3){
			nameBrainPanel.add(player1Label);
			nameBrainPanel.add(player1Name);
			nameBrainPanel.add(player2Label);
			nameBrainPanel.add(player2Name);
			nameBrainPanel.add(player3Label);
			nameBrainPanel.add(player3Name);
			nameBrainPanel.add(player1BLabel);
			nameBrainPanel.add(player1Brain);
			nameBrainPanel.add(player2BLabel);
			nameBrainPanel.add(player2Brain);
			nameBrainPanel.add(player3BLabel);
			nameBrainPanel.add(player3Brain);
			nameBrainPanel.revalidate();
		}else{
			nameBrainPanel.add(player1Label);
			nameBrainPanel.add(player1Name);
			nameBrainPanel.add(player2Label);
			nameBrainPanel.add(player2Name);
			nameBrainPanel.add(player3Label);
			nameBrainPanel.add(player3Name);
			nameBrainPanel.add(player4Label);
			nameBrainPanel.add(player4Name);
			nameBrainPanel.add(player1BLabel);
			nameBrainPanel.add(player1Brain);
			nameBrainPanel.add(player2BLabel);
			nameBrainPanel.add(player2Brain);
			nameBrainPanel.add(player3BLabel);
			nameBrainPanel.add(player3Brain);
			nameBrainPanel.add(player4BLabel);
			nameBrainPanel.add(player4Brain);
			nameBrainPanel.revalidate();
		}
		//seedbuttons action listener
		yes.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				seedField.setVisible(true);
				setSeed(Integer.parseInt(seedField.getText()));
				//System.out.println("Please enter an integer");
			}
			
		});
		
		no.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Random rand = new Random();
				seed = rand.nextInt();
				seedField.setVisible(false);
			}
			
		});
		
		player1Name.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setP1Name(player1Name.getText());
				
			}
			
		});
		
		player2Name.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setP2Name(player2Name.getText());
				
			}
			
		});
		
		player3Name.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setP3Name(player3Name.getText());
				
			}
			
		});
		
		player4Name.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setP4Name(player4Name.getText());
				
			}
			
		});
		
		player1Brain.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setP1Brain(player1Brain.getText());
				
			}
			
		});
		
		player2Brain.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setP2Brain(player2Brain.getText());
				
			}
			
		});
		
		player3Brain.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setP3Brain(player3Brain.getText());
				
			}
			
		});
		
		player4Brain.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setP4Brain(player4Brain.getText());
				
			}
			
		});
		
		playGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				game.setNoOfPlayers(players); 
				game.setP1Name(p1Name);
				game.setP2Name(p2Name);
				game.setP3Name(p3Name);
				game.setP4Name(p4Name);
				game.setP1Brain(p1Brain);
				game.setP2Brain(p2Brain);
				game.setP3Brain(p3Brain);
				game.setP4Brain(p4Brain);
				game.setSeed(seed);
				game.createTournament();
				
			}
			
		});
		
		setSize(1000, 749);
		setSize(1000, 750);
		JLabel seed = new JLabel("Enter Seed: ");
		JLabel seedi = new JLabel("Seed: ");
		seedPanel.add(seed);
		seedPanel.add(yes);
		seedPanel.add(no);
		seedPanel.add(seedi);
		seedPanel.add(seedField);
		nameBrainPanel.revalidate();
		contentPane.add(playPanel, BorderLayout.EAST);
		contentPane.add(seedPanel, BorderLayout.SOUTH);
		contentPane.add(nameBrainPanel, BorderLayout.CENTER);
	}
	public void setSeed(int seed){
		this.seed = seed;
	}
	
	public int getSeed(){
		return seed;
	}
	
	public void setP1Name(String name){
		p1Name = name;
	}
	
	public String getP1Name(){
		return p1Name;
	}
	
	public void setP2Name(String name){
		p2Name = name;
	}
	
	public String getP2Name(){
		return p2Name;
	}

	public void setP3Name(String name){
		p3Name = name;
	}
	
	public String getP3Name(){
		return p3Name;
	}
	
	public void setP4Name(String name){
		p4Name = name;
	}
	
	public String getP4Name(){
		return p4Name;
	}
	
	public void setP1Brain(String brain){
		p1Brain = brain;
	}
	
	public String getP1Brain(){
		return p1Brain;
	}
	
	public void setP2Brain(String brain){
		p2Brain = brain;
	}
	
	public String getP2Brain(){
		return p2Brain;
	}
	
	public void setP3Brain(String brain){
		p3Brain = brain;
	}
	
	public String getP3Brain(){
		return p3Brain;
	}
	
	public void setP4Brain(String brain){
		p4Brain = brain;
	}
	
	public String getP4Brain(){
		return p4Brain;
	}
	
	public void changeGUI(){
		contentPane.removeAll();
		matchGUI = new GameGUI(game.match);
		contentPane.add(matchGUI);
		this.setSize(1400, 1200);
		
	}

//	public static void main(String[] args){
//		SwingUtilities.invokeLater(new Runnable(){
//			public void run(){
//				MenuGui menuGui = new MenuGui();
//			}
//		});
//		
//	}
	
}

