import javax.swing.JFrame; //imports JFrame library
import javax.swing.JLabel; //imports JButton library
import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
 
public class TesterGUI {
	
	int width;
	int length;
	
    JFrame frame=new JFrame(); //creates frame
    JPanel panel;
    JLabel[][] grid; //names the grid of buttons
 
    public TesterGUI(int width, int length, Game game){ //constructor
    	this.width = width;
    	this.length = length;
    	
    	//setup panel
    	panel = new JPanel();
    	frame.setContentPane(panel);
    	
    	
		//frame.setLayout(new GridLayout(width,length)); //set layout
        //createCells(game);
        
        
        
        
    }
    
    public void createCells(Match match) {

    	grid=new JLabel[width][length]; //allocate the size of grid
        int i = 0;
        for(int y=0; y<length; y++){
                for(int x=0; x<width; x++){
                    grid[x][y]=new JLabel("" + match.getWorld().getCell(i)); //creates new button
                    grid[x][y].setPreferredSize(new Dimension(5,5));
                    grid[x][y].setFont(new Font("sansserif",Font.PLAIN,6));
                    
                    panel.add(grid[x][y]); //adds button to grid
                    i++;
                }
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); //sets appropriate size for frame
        frame.setBounds(0,0,1500,1500);
        //frame.setResizable(false);
        frame.setVisible(true); //makes frame visible

        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    public void updateCells(Match match) {
    	
        int i = 0;
        for(int y=0; y<length; y++){
                for(int x=0; x<width; x++){
                	
                    grid[x][y].setText("" + match.getWorld().getCell(i)); //creates new button
                    i++;
                    
                }
        }
    }


	public Component getPanel() {
		return panel;
	}

}
	