import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Color;


public class GUI extends JFrame {
	private JTextField txtIntelligants;
	private Game game;
	
	public GUI(Game game) {
		getContentPane().setBackground(UIManager.getColor("Desktop.background"));
		this.game = game;
		setSize(750, 750);
		setTitle("intelligANTS");
		setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    getContentPane().setLayout(null);
	    
	    JPanel panel = new JPanel();
	    panel.setBounds(0, 167, 15, -167);
	    panel.setMaximumSize(new Dimension(750, 750));
	    getContentPane().add(panel);
	    
	    JLabel lblIntelligants = new JLabel("intelligANTs");
	    lblIntelligants.setForeground(Color.BLACK);
	    lblIntelligants.setHorizontalAlignment(SwingConstants.CENTER);
	    lblIntelligants.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 32));
	    lblIntelligants.setBounds(238, 37, 274, 65);
	    getContentPane().add(lblIntelligants);
	    
	    JButton btnNewMatch = new JButton("New Match");
	    btnNewMatch.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    	}
	    });
	    btnNewMatch.setBounds(316, 129, 117, 29);
	    getContentPane().add(btnNewMatch);
	    setVisible(true);
	}
}
	 
	
