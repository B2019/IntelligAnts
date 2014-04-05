import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	private ZoomPanel zoomWorld;
	private JPanel stats;
	private Match match;
	private JLabel turnlabel;
	private JLabel ticklabel;
	private JLabel team1Foodlabel;
	private JLabel team2Foodlabel;
	private JLabel[] grid;
	private JLabel lblC;

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
		mainWorld.setLayout(new GridLayout(150, 150, 0, 0));
		
		
		rightPanel = new JPanel(new GridLayout(2, 1));
		rightPanel.setPreferredSize(new Dimension(300, 600));
		
		zoomWorld = new ZoomPanel();
		zoomWorld.setBackground(new Color(0, 204, 51));
		zoomWorld.setPreferredSize(new Dimension(300, 300));
	
		
		stats = new JPanel(new GridLayout(5,1));
		stats.setBackground(new Color(255, 51, 51));
		
		
		turnlabel = new JLabel("Turn: " + match.getTurn());
		stats.add(turnlabel);
		
		ticklabel = new JLabel("Tick: " + match.getTick());
		stats.add(ticklabel);

		team1Foodlabel = new JLabel("Team 1 Food: " + match.getWorld().getRedScore());
		stats.add(team1Foodlabel);
		
		team2Foodlabel = new JLabel("Team 2 Food: " + match.getWorld().getBlackScore());
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
			grid[i].addMouseListener(new HoverListener(i, this));
			mainWorld.add(grid[i]);
		}
		
		
		
		
		rightPanel.add(zoomWorld);
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("/Users/Adam/Documents/University/Year2/Term2/SE/IntelligAnts/IntelligAnts/src/Art/ground1.png"));
		} catch (IOException e) {
		}
		
		zoomWorld.setCenter(myPicture);
		
		rightPanel.add(stats);
		getContentPane().add(mainWorld);
		getContentPane().add(rightPanel);
		setSize(1000, 750);
	}
	
	
	public BufferedImage determineImage(int zoomCell){
		BufferedImage myPicture;
		String cell = match.getWorld().getCell(zoomCell).toString();
		try {
			
			String image = "";
			
			if(Character.isDigit(cell.charAt(0))){
				int foodnum = Integer.parseInt(cell);
				switch(foodnum){
				case 1: 
					image = "food1.png";
					break;
				case 2: 
					image = "food2.png";
					break;
				case 3: 
					image = "food3.png";
					break;
				case 4: 
					image = "food4.png";
					break;
				default:
					image = "food5.png";
					break;
				}
			} else {
			switch(cell){
			case ".": 
			image = "ground4.png";	
			break;
			case "#":
			image = "rock1.png";		
			break;
			case "R": 
			
			image = "peri.png";	
				
			break;
			case "B": 
			
				image = "peri.png";	
				
			break;
			case "-": 
			
				image = "peri.png";	
				
			break;
			case "+": 
			
				image = "peri.png";	
				
			break;
			}
			}
			
			
			
			myPicture = ImageIO.read(new File("/Users/Adam/Documents/University/Year2/Term2/SE/IntelligAnts/IntelligAnts/src/Art/" + image));				
			
			return myPicture;
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void updateZoomMap(int zoomCell){
		BufferedImage[] images = new BufferedImage[7];
		
		images[0] = determineImage(zoomCell);
		for (int k = 0; k < 6; k++){
			images[k+1] = determineImage(match.getWorld().getNeighborCell(match.getWorld().getCell(zoomCell), k).getCellID());
			
			
		}
		
		zoomWorld.setImages(images[0], images[1], images[2], images[3], images[4], images[5], images[6]);
		zoomWorld.repaint();
	}
	
	
	public void updateWorldMap(int cellindex){
		
		String cell = match.getWorld().getCell(cellindex).toString();
		if(Character.isDigit(cell.charAt(0))){
			
			grid[cellindex].setOpaque(true);
			grid[cellindex].setBackground(new Color(122, 66, 182));
		} else {
		switch(cell){
		case ".": 
		grid[cellindex].setOpaque(true);
		grid[cellindex].setBackground(new Color(194, 129, 43));
		break;
		case "#":
		grid[cellindex].setOpaque(true);
		grid[cellindex].setBackground(new Color(126, 126, 126));
		break;
		case "R": 
		grid[cellindex].setOpaque(true);
		grid[cellindex].setBackground(new Color(150, 52, 49));
		break;
		case "B": 
		grid[cellindex].setOpaque(true);
		grid[cellindex].setBackground(new Color(57, 55, 131));
		break;
		case "-": 
		grid[cellindex].setOpaque(true);
		grid[cellindex].setBackground(new Color(103, 80, 102));
		break;
		case "+": 
		grid[cellindex].setOpaque(true);
		grid[cellindex].setBackground(new Color(160, 76, 40));
		break;
		}
		}
		
		grid[cellindex].setPreferredSize(new Dimension(4,4));
		try {
			Thread.sleep((long) 2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void updateTick(){
		ticklabel.setText("Tick: " + match.getTick());
	}
	
	public void updateTurn(){
		turnlabel.setText("Turn: " + match.getTurn());
	}
	
	public void updateRedScore(){
		team1Foodlabel.setText("Red Team Food: " + match.getWorld().getRedScore());
	}
	
	public void updateBlackScore(){
		team2Foodlabel.setText("Black Team Food: " + match.getWorld().getBlackScore());
	}
}

class HoverListener extends MouseAdapter {
	
	private int index;
	private GameGUI gui;
	
	public HoverListener(int index, GameGUI gui){
		this.index = index;
		this.gui = gui;
	}
	
    public void mouseEntered(MouseEvent e) {
        gui.updateZoomMap(index);
    }
}



