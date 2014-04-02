import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Container;
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
	JPanel panel;
	JPanel panel_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	public GUI(final Game game) {
		getContentPane().setBackground(UIManager.getColor("Desktop.background"));
		this.game = game;
		setSize(750, 750);
		setTitle("intelligANTS");
		setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    getContentPane().setLayout(null); 
	    
	    panel = new JPanel();
	    panel.setMinimumSize(new Dimension(750, 750));
	    panel.setMaximumSize(new Dimension(750, 750));
	    panel.setBounds(0, -11, 750, 750);
	    getContentPane().add(panel);
	    panel.setLayout(null);
	    JLabel lblIntelligants = new JLabel("intelligANTs");
	    lblIntelligants.setBounds(280, 24, 189, 38);
	    panel.add(lblIntelligants);
	    lblIntelligants.setForeground(Color.BLACK);
	    lblIntelligants.setHorizontalAlignment(SwingConstants.CENTER);
	    lblIntelligants.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 32));
	    
	    JButton btnNewMatch = new JButton("New Match");
	    btnNewMatch.setBounds(318, 119, 113, 29);
	    panel.add(btnNewMatch);
	    
	    panel_1 = new JPanel();
	    panel_1.setBounds(275, 160, 200, 271);
	    
	    JLabel lblPlayer = new JLabel("Player 1:");
	    panel_1.add(lblPlayer);
	    
	    textField = new JTextField();
	    panel_1.add(textField);
	    textField.setColumns(10);
	    
	    JLabel lblPlayer_1 = new JLabel("Player 2:");
	    panel_1.add(lblPlayer_1);
	    
	    textField_1 = new JTextField();
	    panel_1.add(textField_1);
	    textField_1.setColumns(10);
	    
	    JLabel lblPlayerBrain = new JLabel("Player 1 Brain:");
	    panel_1.add(lblPlayerBrain);
	    
	    textField_2 = new JTextField();
	    panel_1.add(textField_2);
	    textField_2.setColumns(10);
	    btnNewMatch.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		setUpMatch();
	    	}
	    });
	    setVisible(true);
	}
	
	public void setUpMatch() {
		
		
		
		panel.add(panel_1);
	}
}
	 
	
