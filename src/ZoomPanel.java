import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ZoomPanel extends JPanel {
	
	BufferedImage centerImage;
	BufferedImage neImage;
	BufferedImage nwImage;
	BufferedImage wImage;
	BufferedImage eImage;
	BufferedImage swImage;
	BufferedImage seImage;

	
	public ZoomPanel(){
	
	}
	
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g; 
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(nwImage, 47, 0, null);
		g.drawImage(neImage, 142, 0, null);
		g.drawImage(wImage, 0, 82, null);
		g.drawImage(centerImage, 95, 82, null);
		g.drawImage(eImage, 191, 82, null);
		g.drawImage(swImage, 47, 165, null);
		g.drawImage(swImage, 142, 165, null);
	}
	
	public void setCenter(BufferedImage center){
		centerImage = center;
	}
	
	public void setImages(BufferedImage center, BufferedImage east, BufferedImage southeast, BufferedImage southwest, BufferedImage west, BufferedImage northwest, BufferedImage northeast){
		centerImage = center;
		eImage = east;
		wImage = west;
		seImage = southeast;
		swImage = southwest;
		neImage = northeast;
		nwImage = northwest;
	}
	
	
}