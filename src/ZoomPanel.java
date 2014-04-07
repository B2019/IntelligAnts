import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ZoomPanel extends JPanel {
	
	BufferedImage[] images;
	

	
	public void paint(Graphics g){
		int offset = 35;
		int offsety = offset - 5;
		Graphics2D g2d = (Graphics2D) g; 
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		try{
		g2d.drawImage(images[5], 47 + offsety, 0 + offset, null);//NW
		g2d.drawImage(images[6], 142 + offsety, 0 + offset, null);//NE
		g2d.drawImage(images[4], 0 + offsety, 82 + offset, null);//W
		g2d.drawImage(images[0], 95 + offsety, 82 + offset, null);//C
		g2d.drawImage(images[1], 191 + offsety, 82 + offset, null);//E
		g2d.drawImage(images[3], 47 + offsety, 165 + offset, null);//SW
		g2d.drawImage(images[2], 142 + offsety, 165 + offset, null);//SE
		g2d.drawImage(images[7], 238 + offsety, 0 + offset, null);//NNEE
		g2d.drawImage(images[8], 285 + offsety, 82 + offset, null);//EE
		g2d.drawImage(images[9], 238 + offsety, 165 + offset, null);//SSEE
		g2d.drawImage(images[10], 190 + offsety, 247 + offset, null);//SSE
		g2d.drawImage(images[11], 95 + offsety, 247 + offset, null);//SSC
		g2d.drawImage(images[12], 0 + offsety, 247 + offset, null);//SSW
		g2d.drawImage(images[13], -47 + offsety, 165 + offset, null);//SSWW
		g2d.drawImage(images[14], -95 + offsety, 82 + offset, null);//WW
		g2d.drawImage(images[15], -47 + offsety, 0 + offset, null);//NNWW
		g2d.drawImage(images[16], 0 + offsety, -82 + offset, null);//NNW
		g2d.drawImage(images[17], 95 + offsety, -82 + offset, null);//NNC
		g2d.drawImage(images[18], 190 + offsety, -82 + offset, null);//NNE
		}
		catch(NullPointerException e){
			//do nothing
		}
		
	}
	
	
	
	
	public void setImages(BufferedImage[] imageArray){
		images = imageArray;
	}
	
	
}