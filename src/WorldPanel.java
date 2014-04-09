import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;


public class WorldPanel extends JPanel {


	private Cell[] cellArray;
	private float size;
	private int rectX;
	private int rectY;
	
	public WorldPanel(Cell[] cells, float f){
		this.cellArray = cells;
		this.size = f;
	}
	
	public void paint(Graphics g){
		int indexX = (int) (size*2);
		int indexY = (int) (size*2);
		int x = indexX;
		int y = indexY;
		for(int i = 0; i < 150*150; i++){
			if(i > 150){
			if((i%150) == 0){
				if((i/150)%2 == 0){
					indexY += (size*2);//-1;
					y = indexY;
					x = (int) (indexX - size);
				} else {
					indexY += (size*2);//-1;
					y = indexY;
					x = (int) (indexX + size);
				}
			}
			} else {
				if(i == 150){
					indexY += size*2;
					y = indexY;
					x = (int) (indexX + size);
				}
			}
			Polygon sprite = drawHexagon(x, y);
			g.setColor(findColour(cellArray[i]));
			g.fillPolygon(sprite);
			
			x += size*2;
			
		}
		Graphics2D g2d = (Graphics2D) g; 
		g2d.setColor(Color.YELLOW);
		g2d.draw(new Rectangle2D.Double(rectX, rectY,
                20,
				20));
		
		
	}

	public void setCells(Cell[] cells){
		cellArray = cells;
	}
	
	public void setcellsize(int size){
		this.size = size;
	}

	public Color findColour(Cell cell1){
		String cell = cell1.toString();

		if(Character.isDigit(cell.charAt(0))){	
			return new Color(122, 66, 182);
		} else {
			switch(cell){
			case ".": 
				return new Color(194, 129, 43);
			case "#":
				return new Color(126, 126, 126);
			case "R": 
				return new Color(150, 52, 49);
			case "B": 
				return new Color(57, 55, 131);
			case "-": 
				return new Color(103, 80, 102);
			case "+": 
				return new Color(160, 76, 40);
			}
		}
		return null;
	}

	public Polygon drawHexagon(int x, int y){


		Polygon sprite = new Polygon();

		for(int i=0; i<6; i++) {
			sprite.addPoint((int) (x + (size * Math.cos((1+i * 2) * Math.PI / 6))),
                    (int) (y + (size * Math.sin((1 + i * 2) * Math.PI / 6))));
		}

		return sprite;


	}
	
	public void setXY(int x, int y){
		rectX = x;
		rectY = y;
	}

}
