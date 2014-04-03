import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

public class GameGUI extends JFrame {
	
	private JPanel mainWorld;
	private JPanel rightPanel;
	private JPanel zoomWorld;
	private JPanel stats;
	private Match match;
	private JLabel turnlabel;
	private JLabel ticklabel;
	private JLabel teamlabel;
	private JLabel team1Foodlabel;
	private JLabel team2Foodlabel;
	private JLabel[] grid;

	public GameGUI(Match match) {
		this.match = match;
		grid = new JLabel[150*150];
		//setResizable(false);
		
		setMaximumSize(new Dimension(1000, 750));
		
		
		setTitle("intelligANTS");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		getContentPane().setLayout(new GridBagLayout());	
		
		
		mainWorld = new JPanel();
//		mainWorld.setBackground(new Color(204, 153, 102));
//		mainWorld.setPreferredSize(new Dimension(650, 650));
//		mainWorld.setMaximumSize(new Dimension(650, 650));
		mainWorld.setLayout(new GridLayout(150, 150, 0, 0));
		
		
		rightPanel = new JPanel(new GridLayout(2, 1));
//		left.setMinimumSize(new Dimension(750, 750));
//		right.setMinimumSize(new Dimension(350, 750));
		
		zoomWorld = new JPanel(new GridLayout(3, 3));
		zoomWorld.setBackground(new Color(0, 204, 51));
//		zoomWorld.setPreferredSize(new Dimension(250, 500));
//		zoomWorld.setMinimumSize(new Dimension(250, 500));
		
		stats = new JPanel(new GridLayout(5,1));
		stats.setBackground(new Color(255, 51, 51));
//		stats.setPreferredSize(new Dimension(350, 250));
//		stats.setMinimumSize(new Dimension(350, 250));
		
		
		turnlabel = new JLabel("Turn: " + match.getTurn());
		stats.add(turnlabel);
		
		ticklabel = new JLabel("Tick: " + match.getTick());
		stats.add(ticklabel);
		
		teamlabel = new JLabel("Team: " + match.getCurrentTeam());
		stats.add(teamlabel);

		team1Foodlabel = new JLabel("Team 1 Food: " + 20);//match.getTeam1Points());
		stats.add(team1Foodlabel);
		
		team2Foodlabel = new JLabel("Team 2 Food: lhasdljkhaslkdhjdskjlhdlkshjldkjLHJKads" + 20);//match.getTeam2Points());
		stats.add(team2Foodlabel);
		
		//Create Main Board
		for(int i = 0; i < 150*150; i++) {
			String cell = match.getWorld().getCell(i).toString();
			if(Character.isDigit(cell.charAt(0))){
				grid[i] = new JLabel(" ");
				grid[i].setOpaque(true);
				grid[i].setBackground(new Color(122, 66, 182));
			} else {
			switch(cell){
			case ".": grid[i] = new JLabel(" ");
			grid[i].setOpaque(true);
			grid[i].setBackground(new Color(194, 129, 43));
			break;
			case "#": grid[i] = new JLabel(" ");
			grid[i].setOpaque(true);
			grid[i].setBackground(new Color(126, 126, 126));
			break;
			case "R": grid[i] = new JLabel(" ");
			grid[i].setOpaque(true);
			grid[i].setBackground(new Color(150, 52, 49));
			break;
			case "B": grid[i] = new JLabel(" ");
			grid[i].setOpaque(true);
			grid[i].setBackground(new Color(57, 55, 131));
			break;
			case "-": grid[i] = new JLabel(" ");
			grid[i].setOpaque(true);
			grid[i].setBackground(new Color(103, 80, 102));
			break;
			case "+": grid[i] = new JLabel(" ");
			grid[i].setOpaque(true);
			grid[i].setBackground(new Color(160, 76, 40));
			break;
			}
			}
			
			grid[i].setPreferredSize(new Dimension(4,4));
			mainWorld.add(grid[i]);
		}
		
		
		
		int zoomCell = 1750;
		Cell cell = match.getWorld().getCell(zoomCell);
		JLabel label = new JLabel("" + match.getWorld().getCell(zoomCell));
		for (int k = 0; k < 6; k++){
			BufferedImage myPicture;
			try {
				myPicture = ImageIO.read(new File("/Users/Adam/Documents/University/Year2/Term2/SE/IntelligAnts/IntelligAnts/src/Art/food1.png"));
				JLabel picLabel = new JLabel(new ImageIcon(myPicture));
				zoomWorld.add(picLabel);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		
		rightPanel.add(zoomWorld);
		rightPanel.add(stats);
		getContentPane().add(mainWorld);
		getContentPane().add(rightPanel);
		setSize(1000, 750);
	}
	
	public void updateWorldMap(){
		mainWorld = new JPanel();
		mainWorld.setLayout(new GridLayout(150, 150, 0, 0));
		for(int i = 0; i < 150*150; i++) {
			String cell = match.getWorld().getCell(i).toString();
			if(Character.isDigit(cell.charAt(0))){
				grid[i] = new JLabel(" ");
				grid[i].setOpaque(true);
				grid[i].setBackground(new Color(122, 66, 182));
			} else {
			switch(cell){
			case ".": grid[i] = new JLabel(" ");
			grid[i].setOpaque(true);
			grid[i].setBackground(new Color(194, 129, 43));
			break;
			case "#": grid[i] = new JLabel(" ");
			grid[i].setOpaque(true);
			grid[i].setBackground(new Color(126, 126, 126));
			break;
			case "R": grid[i] = new JLabel(" ");
			grid[i].setOpaque(true);
			grid[i].setBackground(new Color(150, 52, 49));
			break;
			case "B": grid[i] = new JLabel(" ");
			grid[i].setOpaque(true);
			grid[i].setBackground(new Color(57, 55, 131));
			break;
			case "-": grid[i] = new JLabel(" ");
			grid[i].setOpaque(true);
			grid[i].setBackground(new Color(103, 80, 102));
			break;
			case "+": grid[i] = new JLabel(" ");
			grid[i].setOpaque(true);
			grid[i].setBackground(new Color(160, 76, 40));
			break;
			}
			}
			
			grid[i].setPreferredSize(new Dimension(4,4));
			mainWorld.add(grid[i]);
		}
	}

	

}
