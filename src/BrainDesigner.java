//import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
//import javax.swing.SwingUtilities;
//import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
//import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.*;

/**
 * A GUI Brain designer class.
 * @V 0.0.1
 * @Author 35769
 */

public class BrainDesigner extends JFrame {

	private JPanel toppanel = new JPanel();
	private JPanel westPanel = new JPanel();
	private JPanel eastPanel = new JPanel();
	private JTextArea textEditor = new JTextArea("");
	public final int WIDTH;
	public final int HEIGHT;

	JTabbedPane tabbedPane = new JTabbedPane();
	ImageIcon icon = createImageIcon("antslogo1.gif");
	JComponent panel1 = makeTextPanel("Panel #1");

	// tabbedPane.addTab("Tab 1", icon, panel1, "Does nothing");
	// tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

	protected JComponent makeTextPanel(String text) {
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(filler);
		return panel;
	}

	// bottom panel
	private JPanel southPanel = new JPanel();
	private JButton checkSyntax = new JButton("Check Syntax");
	private JButton save = new JButton("Save");
	private JButton quit = new JButton("Back To Main");
	private JButton load = new JButton("Load");
	private JTextField syntaxOK = new JTextField("...");
	private JButton clear = new JButton("Clear");

	public BrainDesigner() {

		super("IntelligAnt - The Brain Builder");

		// set up south panel
		southPanel.add(syntaxOK);
		syntaxOK.setColumns(15);
		southPanel.add(checkSyntax);
		southPanel.add(clear);
		southPanel.add(save);
		southPanel.add(load);
		southPanel.add(quit);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension tempDimension = tk.getScreenSize();
		WIDTH = tempDimension.width;
		HEIGHT = tempDimension.height;

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
		ImageIcon pic = new ImageIcon("antslogo1.png");
		JLabel top = new JLabel(pic);
		this.toppanel.add(top);

		// set up content pane
		this.setLayout(new BorderLayout());
		this.add("North", toppanel);
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
