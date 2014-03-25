import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.*;

import javax.swing.*;

/**
 * A GUI Brain designer class.
 * 
 * @V 0.0.2
 * @Author 35769
 */

public class BrainDesigner extends JFrame {

	private JPanel topPanel = new JPanel();
	private JPanel westPanel = new JPanel();
	private JPanel eastPanel = new JPanel();
	private JTextArea textEditor = new JTextArea("");
	private JTabbedPane tabbedPane = new JTabbedPane();
	public final int WIDTH;
	public final int HEIGHT;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel panel5;
	private JPanel panel6;
	private JPanel panel7;
	private JPanel panel8;

	/**A method to create the JText box to view and edit the brain.
     * @Param String text - makes a text box with text or an empty text.
     */
	protected JComponent makeTextPanel(String text) {
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(filler);
		return panel;
	}

	/**A method to create a tabbed pane*/
	public void createPage1() {
		panel1 = new JPanel();
		panel1.add(sensePanel);
	}

	/**A method to create a tabbed pane*/
	public void createPage2() {
		panel2 = new JPanel();
		panel2.add(markPanel);
	}

	/**A method to create a tabbed pane*/
	public void createPage3() {
		panel3 = new JPanel();
		panel3.add(unMarkPanel);
	}

	/**A method to create a tabbed pane*/
	public void createPage4() {
		panel4 = new JPanel();
		panel4.add(movePanel);
	}

	/**A method to create a tabbed pane*/
	public void createPage5() {
		panel5 = new JPanel();
		panel5.add(dropPanel);
	}

	/**A method to create a tabbed pane*/
	public void createPage6() {
		panel6 = new JPanel();
		panel6.add(turnPanel);
	}

	/**A method to create a tabbed pane*/
	public void createPage7() {
		panel7 = new JPanel();
		panel7.add(pickupPanel);
	}

	/**A method to create a tabbed pane*/
	public void createPage8() {
		panel8 = new JPanel();
		panel8.add(flipPanel);
	}

	// sense
	private JPanel sensePanel = new JPanel();
	private JLabel senseLabel = new JLabel("SENSE: ");
	private String[] directionOptions = { "Here", "Ahead", "RightAhead",
			"LeftAhead" };
	private JComboBox senseDirections = new JComboBox(directionOptions);
	private String[] conditionOptions = { "Friend", "Foe", "FriendWithFood",
			"FoeWithFood", "Food", "Rock", "Marker", "FoeMarker", "Home",
			"FoeHome" };
	private JComboBox senseConditions = new JComboBox(conditionOptions);
	private JTextField st1 = new JTextField("PASS (ST1)");
	private JTextField st2 = new JTextField("FAIL (ST2)");
	private JTextField marker = new JTextField("MRK");
	private JButton senseOK;

	// mark
	private JPanel markPanel = new JPanel();
	private JLabel markLabel = new JLabel("MARK: ");
	private String[] markers = { "1", "2", "3", "4", "5", "6" };
	private JComboBox markerOptions = new JComboBox(markers);
	private JButton markOK;
	private JTextField stMark = new JTextField("PASS - ST");

	// unmark
	private JPanel unMarkPanel = new JPanel();
	private JLabel unMarkLabel = new JLabel("UNMARK: ");
	private JComboBox unMarkerOptions = new JComboBox(markers);
	private JButton unMarkOK;
	private JTextField stUnMark = new JTextField("PASS - ST");

	// pickup
	private JPanel movePanel = new JPanel();
	private JLabel moveLabel = new JLabel("MOVE: ");
	private JButton moveOK;
	private JTextField stMove1 = new JTextField("PASS (ST1)");
	private JTextField stMove2 = new JTextField("FAIL (ST2)");

	// drop
	private JPanel dropPanel = new JPanel();
	private JLabel dropLabel = new JLabel("DROP: ");
	private JButton dropOK;
	private JTextField dropState = new JTextField("PASS - ST");

	// turn
	private JPanel turnPanel = new JPanel();
	private JLabel turnLabel = new JLabel("TURN: ");
	private String[] directions = { "Left", "Right" };
	private JComboBox turnOptions = new JComboBox(directions);
	private JTextField turnState = new JTextField("PASS - ST");
	private JButton turnOK;

	// pickup
	private JPanel pickupPanel = new JPanel();
	private JLabel pickupLabel = new JLabel("PICKUP: ");
	private JButton pickupOK;
	private JTextField stPick1 = new JTextField("PASS (ST1)");
	private JTextField stPick2 = new JTextField("FAIL (ST2)");

	// flip
	private JPanel flipPanel = new JPanel();
	private JLabel flipLabel = new JLabel("FLIP: ");
	private JButton flipOK;
	private JTextField p = new JTextField("P");
	private JTextField stFlip1 = new JTextField("PASS (ST1)");
	private JTextField stFlip2 = new JTextField("FAIL (ST2)");

	// bottom panel
	private JPanel southPanel = new JPanel();
	private JButton checkSyntax = new JButton("Check Syntax");
	private JButton save = new JButton("Save");
	private JButton quit = new JButton("Back To Main");
	private JButton load = new JButton("Load");
	private JTextField syntaxOK = new JTextField("...");
	private JButton clear = new JButton("Clear");

	
	/**The CONSTRUCTOR for the BrainDesigner*/
	public BrainDesigner() {

		// The name of the JFRAME
		super("IntelligAnt - The Brain Builder");

		// set up south panel
		southPanel.add(syntaxOK);
		syntaxOK.setColumns(15);
		southPanel.add(checkSyntax);
		southPanel.add(clear);
		southPanel.add(save);
		southPanel.add(load);
		southPanel.add(quit);

		// Create the tab pages
		createPage1();
		createPage2();
		createPage3();
		createPage4();
		createPage5();
		createPage6();
		createPage7();
		createPage8();

		// naming all the tabs
		tabbedPane.addTab("Sense", panel1);
		tabbedPane.addTab("Mark", panel2);
		tabbedPane.addTab("UnMark", panel3);
		tabbedPane.addTab("Move", panel4);
		tabbedPane.addTab("Drop", panel5);
		tabbedPane.addTab("Turn", panel6);
		tabbedPane.addTab("Pickup", panel7);
		tabbedPane.addTab("Flip", panel8);
		topPanel.add(tabbedPane, BorderLayout.CENTER);

		// Initialisation of a few more variables
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension tempDimension = tk.getScreenSize();
		WIDTH = tempDimension.width;
		HEIGHT = tempDimension.height;

		// the button to put the command into the brain
		ImageIcon tick = new ImageIcon("antslogo1.png");

		/**Details to do with Sensing*/
		sensePanel.setLayout(new FlowLayout());
		sensePanel.add(senseLabel);
		sensePanel.add(senseDirections);
		sensePanel.add(st1);
		sensePanel.add(st2);
		sensePanel.add(senseConditions);
		sensePanel.add(marker);
		marker.setColumns(4);
		st1.setColumns(4);
		st2.setColumns(4);
		senseOK = new JButton(tick);
		senseOK.setSize(new Dimension(tick.getIconWidth(), tick.getIconHeight()));
		sensePanel.add(senseOK);
		senseOK.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				String newInstruction = "";
				boolean allowedToAdd = false;
				try {
					newInstruction += "Sense "
							+ (String) senseDirections.getSelectedItem();
					newInstruction += " " + Integer.parseInt(st1.getText())
							+ " ";
					newInstruction += "" + Integer.parseInt(st2.getText())
							+ " ";
					newInstruction += (String) senseConditions
							.getSelectedItem();
					newInstruction += "\n";
					allowedToAdd = true;
				} catch (Exception exp) {

				}
				if (allowedToAdd) {
					textEditor.setText(textEditor.getText() + newInstruction);
					allowedToAdd = false;
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});

		/**Details to do with marking*/
		this.markPanel.add(markLabel);
		this.markPanel.add(markerOptions);
		this.markPanel.add(stMark);
		stMark.setColumns(4);
		markOK = new JButton(tick);
		this.markPanel.add(markOK);
		this.markPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		markOK.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				String newInstruction = "";
				boolean allowedToAdd = false;
				try {
					newInstruction += "Mark ";
					System.out.println("OK");
					newInstruction += Integer.parseInt((String) markerOptions
							.getSelectedItem()) + " ";
					System.out.println("OK");
					newInstruction += Integer.parseInt(stMark.getText()) + "\n";
					System.out.println("OK");
					allowedToAdd = true;
				} catch (Exception e) {
				}
				if (allowedToAdd) {
					textEditor.setText(textEditor.getText() + newInstruction);
				}
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

		/**Details to do with UNmarking*/
		this.unMarkPanel.add(unMarkLabel);
		this.unMarkPanel.add(unMarkerOptions);
		this.unMarkPanel.add(stUnMark);
		stUnMark.setColumns(4);
		unMarkOK = new JButton(tick);
		this.unMarkPanel.add(unMarkOK);
		this.unMarkPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		unMarkOK.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				String newInstruction = "";
				boolean allowedToAdd = false;
				try {
					newInstruction += "UnMark ";
					newInstruction += Integer.parseInt((String) unMarkerOptions
							.getSelectedItem()) + " ";
					newInstruction += Integer.parseInt(stUnMark.getText())
							+ "\n";
					allowedToAdd = true;
				} catch (Exception e) {
				}
				if (allowedToAdd) {
					textEditor.setText(textEditor.getText() + newInstruction);
				}
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

		/**Details to do with picking UP*/
		this.pickupPanel.add(pickupLabel);
		this.pickupPanel.add(stPick1);
		this.pickupPanel.add(stPick2);
		stPick1.setColumns(4);
		stPick2.setColumns(4);
		pickupOK = new JButton(tick);
		this.pickupPanel.add(pickupOK);
		this.pickupPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.textEditor.setRows(9999);
		this.textEditor.setColumns(14);
		this.pickupOK.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent e) {
				String newInstruction = "";
				boolean allowedToAdd = false;
				try {
					newInstruction += "Pickup ";
					newInstruction += Integer.parseInt(stPick1.getText()) + " ";
					newInstruction += Integer.parseInt(stPick2.getText())
							+ "\n";
					allowedToAdd = true;
				} catch (Exception exp) {
				}
				if (allowedToAdd) {
					textEditor.setText(textEditor.getText() + newInstruction);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});

		/**Details to do with dropping*/
		this.dropPanel.add(dropLabel);
		this.dropPanel.add(dropState);
		this.dropOK = new JButton(tick);
		this.dropPanel.add(dropOK);
		this.dropPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.dropState.setColumns(4);
		this.dropOK.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				String newInstruction = "";
				boolean allowedToAdd = false;
				try {
					newInstruction += "Drop ";
					newInstruction += Integer.parseInt(dropState.getText())
							+ "\n";
					allowedToAdd = true;
				} catch (Exception exp) {
				}
				if (allowedToAdd) {
					textEditor.setText(textEditor.getText() + newInstruction);
				}
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

		/**Details to do with turning*/
		this.turnPanel.add(turnLabel);
		this.turnPanel.add(turnOptions);
		this.turnPanel.add(turnState);
		turnState.setColumns(4);
		turnOK = new JButton(tick);
		this.turnPanel.add(turnOK);
		turnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		turnOK.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent e) {
				String newInstruction = "";
				boolean allowedToAdd = false;
				try {
					newInstruction += "Turn ";
					newInstruction += (String) (turnOptions.getSelectedItem())
							+ " ";
					newInstruction += Integer.parseInt(turnState.getText())
							+ "\n";
					allowedToAdd = true;
				} catch (Exception exp) {
				}
				if (allowedToAdd) {
					textEditor.setText(textEditor.getText() + newInstruction);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});

		
		/**Details to do with moving*/
		this.movePanel.add(moveLabel);
		this.movePanel.add(stMove1);
		this.movePanel.add(stMove2);
		stMove1.setColumns(4);
		stMove2.setColumns(4);
		moveOK = new JButton(tick);
		this.movePanel.add(moveOK);
		this.movePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		moveOK.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				String newInstruction = "";
				boolean allowedToAdd = false;
				try {
					newInstruction += "Move ";
					newInstruction += Integer.parseInt(stMove1.getText()) + " ";
					newInstruction += Integer.parseInt(stMove2.getText())
							+ "\n";
					allowedToAdd = true;
				} catch (Exception e) {

				}
				if (allowedToAdd) {
					textEditor.setText(textEditor.getText() + newInstruction);
				}
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

		
		
		/**Details to do with the 'flip'*/
		this.flipPanel.add(flipLabel);
		this.flipPanel.add(p);
		this.flipPanel.add(stFlip1);
		this.flipPanel.add(stFlip2);
		stFlip1.setColumns(4);
		stFlip2.setColumns(4);
		p.setColumns(4);
		flipOK = new JButton(tick);
		this.flipPanel.add(flipOK);
		this.flipPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.textEditor.setRows(9999);
		this.textEditor.setColumns(14);
		flipOK.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent e) {
				String newInstruction = "";
				boolean allowedToAdd = false;
				try {
					newInstruction += "Flip ";
					newInstruction += Integer.parseInt(p.getText()) + " ";
					newInstruction += Integer.parseInt(stFlip1.getText()) + " ";
					newInstruction += Integer.parseInt(stFlip2.getText())
							+ "\n";
					allowedToAdd = true;
				} catch (Exception eek) {

				}
				if (allowedToAdd) {
					textEditor.setText(textEditor.getText() + newInstruction);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});

		/**Details to do with syntax checking*/
		checkSyntax.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				File f = new File("temp2.file");
				PrintWriter pw = null;
				try {
					pw = new PrintWriter(f);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pw.print(textEditor.getText());
				pw.close();
				try {
					BrainBuilder.buildBrain(BrainReader.readBrain(f));
					syntaxOK.setText("Syntax OK");
				} catch (IOException e) {
					// TODO Auto-generated catch block
				} catch (BrainIncorrectException e) {
					// TODO Auto-generated catch block
					syntaxOK.setText("Syntax Error in Brain");
				}
			}
		});

		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainMenu newMenu = new MainMenu();
				dispose();
			}
		});

		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser c = new JFileChooser(".");
				File f = null;
				PrintWriter pw = null;
				int rVal = c.showSaveDialog(BrainDesigner.this);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					try {
						f = new File(c.getCurrentDirectory().toString() + "/"
								+ c.getSelectedFile().getName());
						pw = new PrintWriter(f);
					} catch (FileNotFoundException exp) {
					}
					pw.print(textEditor.getText());
					pw.close();
				}
			}
		});

		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser choseBrain = new JFileChooser(".");
				choseBrain.showOpenDialog(BrainDesigner.this);
				File f = choseBrain.getSelectedFile();
				BufferedReader lineReader = null;
				try {
					lineReader = new BufferedReader(new FileReader(f));
				} catch (FileNotFoundException e1) {
					syntaxOK.setText("File Not Found");
				}
				String s = "";
				try {
					textEditor.setText("");
					String newString = ("");
					while ((s = lineReader.readLine()) != null) {
						newString += s + "\n";
					}
					textEditor.setText(newString);
				} catch (IOException e1) {
				}
			}
		});

		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				textEditor.setText("");
			}
		});

		JScrollPane scrollPane = new JScrollPane(textEditor);
		scrollPane.setPreferredSize(new Dimension(220, 350));
		this.westPanel.add(scrollPane);

		// set up image panel
		ImageIcon pic = new ImageIcon("itelligantslogo1.jpg");
		JLabel pic1 = new JLabel(pic);
		this.eastPanel.add(pic1, BorderLayout.CENTER);

		// set up content pane
		this.setLayout(new BorderLayout());
		this.add("North", topPanel);
		this.add("West", westPanel);
		this.add("East", eastPanel);
		this.add("South", southPanel);
		this.setLocation(((WIDTH / 4) + 50), ((HEIGHT / 4)) - 100);
		this.setResizable(false);
		this.setVisible(true);
		this.pack();
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = BrainDesigner.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
}
