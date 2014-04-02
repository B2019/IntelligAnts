import java.awt.*;

import javax.swing.*;

public class GameGUI extends JFrame {
	private JPanel mainWorld;
	private JPanel zoomWorld;
	private JPanel stats;
	private Match match;
	private JLabel turnlabel;
	private JLabel ticklabel;
	private JLabel teamlabel;

	public GameGUI(Match match) {
		this.match = match;
		setSize(750, 750);
		setTitle("intelligANTS");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowWeights = new double[]{1.0, 1.0};
		gridBagLayout.columnWeights = new double[]{1.0};
		getContentPane().setLayout(gridBagLayout);
		
		mainWorld = new JPanel();
		GridBagConstraints gbc_mainWorld = new GridBagConstraints();
		gbc_mainWorld.gridheight = 2;
		gbc_mainWorld.anchor = GridBagConstraints.WEST;
		gbc_mainWorld.insets = new Insets(0, 0, 5, 0);
		gbc_mainWorld.gridx = 0;
		gbc_mainWorld.gridy = 0;
		gbc_mainWorld.fill = GridBagConstraints.VERTICAL;
		getContentPane().add(mainWorld, gbc_mainWorld);
		
		zoomWorld = new JPanel();
		GridBagConstraints gbc_zoomWorld = new GridBagConstraints();
		gbc_zoomWorld.anchor = GridBagConstraints.EAST;
		gbc_zoomWorld.insets = new Insets(0, 0, 5, 0);
		gbc_zoomWorld.fill = GridBagConstraints.VERTICAL;
		gbc_zoomWorld.gridx = 1;
		gbc_zoomWorld.gridy = 0;
		getContentPane().add(zoomWorld, gbc_zoomWorld);
		
		stats = new JPanel();
		GridBagConstraints gbc_stats = new GridBagConstraints();
		gbc_stats.anchor = GridBagConstraints.EAST;
		gbc_stats.fill = GridBagConstraints.VERTICAL;
		gbc_stats.gridx = 1;
		gbc_stats.gridy = 1;
		getContentPane().add(stats, gbc_stats);
		stats.setLayout(new FlowLayout());
		
		turnlabel = new JLabel("Turn: " + match.getTurn());
		stats.add(turnlabel);
		
		ticklabel = new JLabel("Tick: " + match.getTick());
		stats.add(ticklabel);
		
		teamlabel = new JLabel("Team: " + match.getCurrentTeam());
		stats.add(teamlabel);

		
		

	}

}
