import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MainMenu extends JFrame {
	private JPanel toppanel = new JPanel();
	private JPanel container = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JButton accountManager = new JButton("Account Managment (under Construction)");
	private JButton tournament = new JButton("Play A New Tournament (Under Construction)");
	private JButton quit = new JButton("Quit");
	private JButton brainDesigner = new JButton("Design A Brain");
	public final int WIDTH;
	public final int HEIGHT;


	public MainMenu(){
		super("IntelligAnt");

		//position in center of screen
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension tempDimension = tk.getScreenSize();
		WIDTH = tempDimension.width;
		HEIGHT = tempDimension.height;
		
		//set up button panel	
		buttonPanel.add(accountManager);
		buttonPanel.add(tournament);
		buttonPanel.add(brainDesigner);
		buttonPanel.add(quit);
		buttonPanel.setLayout(new GridLayout(5,1));
		
		accountManager.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Account Manager goes here
				dispose();
			}								
		});
		quit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		tournament.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//New Game goes here
				dispose();
			}
		});
		brainDesigner.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent exp){
				BrainDesigner brainDesign = new BrainDesigner();
				dispose();
			}
		});	
		
		//set up image panel
		ImageIcon pic=new ImageIcon("antslogo1.png");  
		JLabel top = new JLabel(pic);	
		this.toppanel.add(top);

		//set up content pane
		this.getContentPane().add(container);
		this.container.add("North",toppanel);
		this.container.add("South",buttonPanel);
		this.setLocation(((WIDTH/4)), ((HEIGHT/4)));
		this.setIconImage(new ImageIcon("antslogo1.gif").getImage());
		this.setResizable(false);
		this.setVisible(true);
		this.pack();
	}
}
