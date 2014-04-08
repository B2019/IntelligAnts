import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.ImageIcon;

public class GameGUI extends JFrame implements Runnable {
	
	private WorldPanel mainWorld;
	private JPanel rightPanel;
	private ZoomPanel zoomWorld;
	private JPanel stats;
	public Match match;
	private JLabel turnlabel;
	private JLabel team1Foodlabel;
	private JLabel team2Foodlabel;
	private int zoomCell;
	private JPanel statsinner;
	public Boolean go;
	public Game game;

	public GameGUI(Match match1, Game game) {
		this.game = game;
		
		this.match = match1;
		
		zoomCell = 1500;
		
		this.setVisible(true);
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(1000, 1200));
		
		
		mainWorld = new WorldPanel(match.getWorld().getCells(), (float)2.8);
		mainWorld.setPreferredSize(new Dimension(775, 755));
		mainWorld.addMouseListener(new HoverListener(this));
		
		
		rightPanel = new JPanel(new GridLayout(2, 1));
		rightPanel.setPreferredSize(new Dimension(350, 700));
		
		zoomWorld = new ZoomPanel();
		zoomWorld.setPreferredSize(new Dimension(350, 350));
		
	
		
		stats = new JPanel(new BorderLayout(5, 5));
		
		statsinner = new JPanel(new GridLayout(3, 2));
		
		 Font font = new Font("Courier", Font.BOLD,16);
         
		
		JLabel turnlbl = new JLabel("Turn:");
	turnlbl.setFont(font);
		turnlabel = new JLabel("" + match.getTurn() + " / 300000");
		turnlabel.setFont(font);
		statsinner.add(turnlbl, BorderLayout.CENTER);
		statsinner.add(turnlabel, BorderLayout.CENTER);
		turnlbl.setHorizontalTextPosition(JLabel.RIGHT);

		team1Foodlabel = new JLabel("" + match.getWorld().getRedScore());
		team1Foodlabel.setFont(font);
		JLabel foodlbl = new JLabel("Team 1 Food:");
		foodlbl.setFont(font);
		statsinner.add(foodlbl, BorderLayout.CENTER);
		statsinner.add(team1Foodlabel, BorderLayout.CENTER);
		foodlbl.setHorizontalTextPosition(JLabel.RIGHT);
		
		team2Foodlabel = new JLabel("" + match.getWorld().getBlackScore());
		team2Foodlabel.setFont(font);
		JLabel foodlbl2 = new JLabel("Team 2 Food:");
		foodlbl2.setFont(font);
		statsinner.add(foodlbl2, BorderLayout.CENTER);
		statsinner.add(team2Foodlabel, BorderLayout.CENTER);
		foodlbl2.setHorizontalTextPosition(JLabel.RIGHT);
		
		
		stats.add(statsinner);
		JSlider scrollerSpeed = new JSlider();
		stats.add(scrollerSpeed, BorderLayout.PAGE_END);
		scrollerSpeed.setMajorTickSpacing( 100 );
		scrollerSpeed.setMinorTickSpacing( 10 );
		scrollerSpeed.setPaintTicks( true );
		scrollerSpeed.setPaintLabels( true );
		Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
	    table.put (0, new JLabel("Slow"));
	    table.put (100, new JLabel("Fast"));
	    scrollerSpeed.setLabelTable (table);
		scrollerSpeed.addChangeListener(new BoundedChangeListener(match));
		
//		JButton gameStart = new JButton("Start Game");
//		gameStart.addActionListener(new PlayListener(match));
//		stats.add(gameStart);
		
		rightPanel.add(zoomWorld);
		
		rightPanel.add(stats);
		this.add(mainWorld);
		this.add(rightPanel);
		mainWorld.repaint();
		stats.repaint();
		zoomWorld.repaint();
		setSize(1200, 1000);
		//match.setGUI(this);
		go = true;
	}
	
	public JPanel getStats(){
		return statsinner;
	}
	
	public BufferedImage determineImage(int zoomCell){
		
		if(zoomCell < 0){
			return null;
		}
		
		BufferedImage myPicture;
		Cell cell = match.getWorld().getCell(zoomCell);
		String cellString = match.getWorld().getCell(zoomCell).toString();
		
		try {
			
			String image = "";
			int foodnum = cell.getFoodSize();
			BufferedImage antPicture = null;
			BufferedImage foodPickPicture = null;
			BufferedImage foodPicture = null;
			BufferedImage groundPicture = null;
			
			if(cell.getAnt() != null){
				if(cell.getAnt().getTeamID() == 1){
				image = "rant" + cell.getAnt().getDirection() + ".png";	
				antPicture = ImageIO.read(new File("/Users/Adam/Documents/University/Year2/Term2/SE/IntelligAnts/IntelligAnts/src/Art/" + image));				
				} else {
					image = "bant" + cell.getAnt().getDirection() + ".png";	
					antPicture = ImageIO.read(new File("/Users/Adam/Documents/University/Year2/Term2/SE/IntelligAnts/IntelligAnts/src/Art/" + image));				
				}
				if(cell.getAnt().getFood() != null){
					image = "foodpick" + cell.getAnt().getDirection() + ".png";	
					foodPickPicture = ImageIO.read(new File("/Users/Adam/Documents/University/Year2/Term2/SE/IntelligAnts/IntelligAnts/src/Art/" + image));	 						
				}
			}
			if((foodnum > 0)){
				if(foodnum < 5){
					image = "food" + foodnum + ".png";
					foodPicture = ImageIO.read(new File("/Users/Adam/Documents/University/Year2/Term2/SE/IntelligAnts/IntelligAnts/src/Art/" + image));				

				} else {
					image = "food" + 5 + ".png";
					foodPicture = ImageIO.read(new File("/Users/Adam/Documents/University/Year2/Term2/SE/IntelligAnts/IntelligAnts/src/Art/" + image));				

				}
			}
			if(cell.isRocky() == false){
				groundPicture = ImageIO.read(new File("/Users/Adam/Documents/University/Year2/Term2/SE/IntelligAnts/IntelligAnts/src/Art/" + "rock1.png"));	

			} else 
			if(cell.getAntHill() == 1){
				groundPicture = ImageIO.read(new File("/Users/Adam/Documents/University/Year2/Term2/SE/IntelligAnts/IntelligAnts/src/Art/" + "ranthill.png"));
			} else 
				if (cell.getAntHill() == 2){
					groundPicture = ImageIO.read(new File("/Users/Adam/Documents/University/Year2/Term2/SE/IntelligAnts/IntelligAnts/src/Art/" + "banthill.png"));
				}
				else {
					groundPicture = ImageIO.read(new File("/Users/Adam/Documents/University/Year2/Term2/SE/IntelligAnts/IntelligAnts/src/Art/" + "ground1.png"));

				}
			
			myPicture = groundPicture;
			Graphics2D g = myPicture.createGraphics();
	        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);
	        g.drawImage(myPicture, 0, 0, null);
			if(foodPicture != null){
		        g.drawImage(foodPicture, 0, 0, null);
		       
			}
			
				
			if(antPicture != null){
				g.drawImage(antPicture, 0, 0, null);
			}
			if(foodPickPicture != null){
				g.drawImage(foodPickPicture, 0, 0, null);
			}
			
			
			g.dispose();
			
			
			return myPicture;
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void updateZoomMap(int zoomCell){
		BufferedImage[] images = new BufferedImage[19];
		
		images[0] = determineImage(zoomCell);
		for (int k = 0; k < 6; k++){
			
			images[k+1] = determineImage(match.getWorld().getNeighborCell(match.getWorld().getCell(zoomCell), k).getCellID());
			Cell interCell;
			switch(k){
			
			case 0:
				interCell = match.getWorld().getNeighborCell(match.getWorld().getCell(zoomCell), 0);
				images[7] = determineImage(match.getWorld().getNeighborCell(interCell, 5).getCellID());
				images[8] = determineImage(match.getWorld().getNeighborCell(interCell, 0).getCellID());
				images[9] = determineImage(match.getWorld().getNeighborCell(interCell, 1).getCellID());
				break;
			case 1:
				interCell = match.getWorld().getNeighborCell(match.getWorld().getCell(zoomCell), 1);
				images[10] = determineImage(match.getWorld().getNeighborCell(interCell, 1).getCellID());
				images[11] = determineImage(match.getWorld().getNeighborCell(interCell, 2).getCellID());
				break;
			case 2:
				interCell = match.getWorld().getNeighborCell(match.getWorld().getCell(zoomCell), 2);
				images[12] = determineImage(match.getWorld().getNeighborCell(interCell, 2).getCellID());
				images[13] = determineImage(match.getWorld().getNeighborCell(interCell, 3).getCellID());
				break;
			case 3:
				interCell = match.getWorld().getNeighborCell(match.getWorld().getCell(zoomCell), 3);
				images[14] = determineImage(match.getWorld().getNeighborCell(interCell, 3).getCellID());
				images[15] = determineImage(match.getWorld().getNeighborCell(interCell, 4).getCellID());
				break;
			case 4:
				interCell = match.getWorld().getNeighborCell(match.getWorld().getCell(zoomCell), 4);
				images[16] = determineImage(match.getWorld().getNeighborCell(interCell, 4).getCellID());
				images[17] = determineImage(match.getWorld().getNeighborCell(interCell, 5).getCellID());
				break;
			case 5: 
				interCell = match.getWorld().getNeighborCell(match.getWorld().getCell(zoomCell), 5);
				images[18] = determineImage(match.getWorld().getNeighborCell(interCell, 5).getCellID());
				break;
			}
			
		}
		
		zoomWorld.setImages(images);
		zoomWorld.repaint();
	}
	
	
	public void updateWorldMap(){
		mainWorld.setCells(match.getWorld().getCells());
		mainWorld.repaint();
	}

	public void updateZoomBox(int x, int y){
		mainWorld.setXY(x, y);
	}

	
	public void updateTurn(){
		turnlabel.setText("" + match.getTurn() + " / 300000");
	}
	
	public void updateRedScore(){
		team1Foodlabel.setText("" + match.getWorld().getRedScore());
	}
	
	public void updateBlackScore(){
		team2Foodlabel.setText("" + match.getWorld().getBlackScore());
	}
	
	public void setZoomCell(int index){
		zoomCell = index;
	}
	
	public int getZoomCell(){
		return zoomCell;
	}

	@Override
	public void run() {
		match.setGUI(this);
		game.getWinner(match.runMatch());
		this.setVisible(false);
		this.dispose();
	}
	
//	public static void main(String[] args){
//		
//		Match match = new Match(new World(150, 150), 888, "a","b", new Brain("testbrain.brain"), new Brain("testbrain.brain"));
//		GameGUI gui = new GameGUI(match);
//		match.setGUI(gui);
//		
//	}
	
	
}

class HoverListener extends MouseAdapter {
	
	private GameGUI gui;

	public HoverListener(GameGUI gui){
		this.gui = gui;
	}
	
    public void mouseClicked(MouseEvent e) {
    	
    	int index = convertXY(e.getX(), e.getY());
        gui.updateZoomMap(index);
        gui.setZoomCell(index);
        gui.updateZoomBox(e.getX()-5, e.getY()-5);
    }
    
    public int convertXY(int x, int y){
    	int actX = x/5;
    	int actY = y/5;
    	
    	return actX + (actY * 150);
    }
    
}

class BoundedChangeListener implements ChangeListener {
	
	Match match;
	
	public BoundedChangeListener(Match match){
		this.match = match;
	}
	
	  public void stateChanged(ChangeEvent changeEvent) {
	    Object source = changeEvent.getSource();
	    if (source instanceof JSlider) {
	      JSlider theJSlider = (JSlider) source;
	      if (!theJSlider.getValueIsAdjusting()) {
	    	  if(theJSlider.getValue() == 0){
	    		  match.speed = 1;
	    	  } else {
	    	  match.speed = (int) (theJSlider.getValue() * 10);
	    	  }
	      }
	    }
	  }
	}




