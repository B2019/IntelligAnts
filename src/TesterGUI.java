import javax.swing.JFrame; //imports JFrame library
import javax.swing.JLabel; //imports JButton library
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
 
public class TesterGUI {
	
	int width;
	int length;
	
    JFrame frame=new JFrame(); //creates frame
    JPanel panel;
    JLabel[][] grid; //names the grid of buttons
 
    public TesterGUI(int width, int length, Game game) { //constructor
    	this.width = width;
    	this.length = length;
    	
    	//setup panel
    	panel = new JPanel(new GridBagLayout());
    	frame.setResizable(false);
    	frame.setContentPane(panel);
    	frame.setPreferredSize(new Dimension(1000,1000));
    	frame.setVisible(true); //makes frame visible
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    public void createCells(Match match) {
    	
    	panel.removeAll();
    	grid=new JLabel[width][length]; //allocate the size of grid
        int i = 0;
        for(int y=0; y<length; y++){
                for(int x=0; x<width; x++){
                	GridBagConstraints gbc = new GridBagConstraints();
                	gbc.gridx = x;
                	gbc.gridy = y;
                    grid[x][y]=new JLabel("" + match.getWorld().getCell(i)); //creates new button
                    grid[x][y].setPreferredSize(new Dimension(6,6));
                    grid[x][y].setFont(new Font("Serif",Font.PLAIN, 5));
                    
                    panel.add(grid[x][y], gbc); //adds button to grid
                    i++;
                }
        }
        frame.pack(); //sets appropriate size for frame
        
        
       
        /*
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    }
    
    
    public void updateCells(Match match) {
    	
        int i = 0;
        for(int y=0; y<length; y++){
            for(int x=0; x<width; x++){
            	
                grid[x][y].setText("" + match.getWorld().getCell(i)); //creates new button
                i++;
                
            }
        }
        frame.pack();

    }


	public Component getPanel() {
		return panel;
	}

}
	