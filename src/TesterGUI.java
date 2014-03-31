import javax.swing.JFrame; //imports JFrame library
import javax.swing.JLabel; //imports JButton library
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout; //imports GridLayout library
import java.awt.Insets;
 
public class TesterGUI {
	
	int width;
	int length;
	
    JFrame frame=new JFrame(); //creates frame
    JPanel panel;
    JLabel[][] grid; //names the grid of buttons
 
    public TesterGUI(int width, int length){ //constructor
    	
    	this.width = width;
    	this.length = length;
    	
    	//setup panel
    	panel = new JPanel();
    	frame.setContentPane(panel);
    	//setup game
    	Game game = new Game();
    	game.createMatch();
    	
		//frame.setLayout(new GridLayout(width,length)); //set layout
        createButtons(game);
        
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
        
        for (int i = 0; i < 200; i++) {
	        try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        for (int a = 0; a < 254; a++) {
	        	Ant ant = game.getMatch().getWorld().getAnt(a);
		        game.getMatch().getWorld().move(ant);
		        
	        }
	        updateButtons(game);
	        panel.repaint();
	        panel.revalidate();
	        
        }
        
    }
    
    public void createButtons(Game game) {

    	grid=new JLabel[width][length]; //allocate the size of grid
        int i = 0;
        for(int y=0; y<length; y++){
                for(int x=0; x<width; x++){
                    grid[x][y]=new JLabel("" + game.getMatch().getWorld().getCell(i)); //creates new button
                    grid[x][y].setPreferredSize(new Dimension(5,5));
                    grid[x][y].setFont(new Font("sansserif",Font.PLAIN,6));
                    
                    panel.add(grid[x][y]); //adds button to grid
                    i++;
                }
        }
    }
    public void updateButtons(Game game) {
    	
        int i = 0;
        for(int y=0; y<length; y++){
                for(int x=0; x<width; x++){
                	
                    grid[x][y].setText("" + game.getMatch().getWorld().getCell(i)); //creates new button
                    i++;
                    
                }
        }
    }
    
    public static void main(String[] args) {
            new TesterGUI(150,150);//makes new ButtonGrid with 2 parameters
    }
}
	