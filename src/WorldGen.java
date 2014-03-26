import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Random;

public class WorldGen {

	int x;
	int y;
	char[] worldArray;

	public WorldGen(int x, int y) {
		this.x = x;
		this.y = y;
		newWorld();
		setUpPerim();
		antHill(true);
		antHill(false);
		food();
		rocks();
		printWorld();
	}

	public void newWorld() {
		worldArray = new char[x * y];

	}

	public int coordConv(int X, int Y) {
		return X + (Y * x);
	}

	public int[] indexConv(int index) {
		int[] coord = new int[2];
		if (index < 150) {
			coord[1] = 0;
			coord[0] = index;
		} else {
			coord[1] = index / x;
			coord[0] = index % (x * coord[1]);
		}
		return coord;
	}

	public void setUpPerim() {
		int i = 0;
		for (; i < x; i++) {
			worldArray[i] = '#';
		}
		for (; i < ((x * y) - x); i++) {
			if (i % x == 0) {
				worldArray[i] = '#';
				worldArray[i + 149] = '#';
			} else {
				if (!(i % x == (x - 1))) {
					worldArray[i] = '.';
				}
			}
		}
		for (; i < x * y; i++) {
			worldArray[i] = '#';
		}
	}

	
	public void rocks(){
		char s2 = '.';
		Random rand = new Random();
		int indexMax = (x * y);
		int index = rand.nextInt(indexMax);
		int rocksPlaced = 0;
		while (rocksPlaced < 14) {
			
			if(worldArray[index] == s2)	{
				worldArray[index] = '#';
				rocksPlaced++;

			} else {
				index = rand.nextInt(indexMax);
			}
			
		}
		
		
		
	}
	
	public void food(){
		int foodSize = 5;
		char s2 = '.';
		Random rand = new Random();
		int indexMax = (x * y);
		int index = rand.nextInt(indexMax);
		Boolean happy = false;
		while (happy == false) {
			int[] coord = indexConv(index);
			if ((coord[0] < (x - 14)) && coord[1] < (y - 14) && coord[0] != 0 && coord[1] != 0) {
					
					int topleftCorner = coordConv((coord[0]), (coord[1]));
					int toprightCorner = coordConv((coord[0] + 4), (coord[1]));
					int botleftCorner = coordConv((coord[0] + 2), coord[1] + 4);
					int botrightCorner = coordConv((coord[0] + 6), coord[1] + 4);
					
					if(worldArray[topleftCorner] != s2 && worldArray[toprightCorner] != s2 && worldArray[botleftCorner] != s2 && worldArray[botrightCorner] != s2){
						happy = true;
					}
							

			}
			index = rand.nextInt(indexMax);
		}
//		int dir = rand.nextInt(1);
//		if(dir < 0.5){
//			use this function to decide directional orientation of food blob ^^
//		}
		boolean newLine = false;
		for(int i = 0; i < 5; i++){
			
			for(int j = 0; j < 5; j++){
				worldArray[index + j] = '5';
				
			}
			if(newLine == true){
				
				index = index+x+1;
				newLine = false;
			}
			else{
				index += x;
				newLine = true;
			}
		}
	}
	
	public void antHill(Boolean red) {
		char s;
		char s2;
		if (red == true) {
			s = '+';
			s2 = '*';
		} else {
			// change to -
			s = '*';
			s2 = '+';
		}
		Random rand = new Random();
		int indexMax = (x * y);
		int index = rand.nextInt(indexMax);
		Boolean happy = false;
		while (happy == false) {
			int[] coord = indexConv(index);
			if ((coord[0] < (x - 14)) && coord[1] < (y - 14) && coord[0] != 0 && coord[1] != 0) {
					
					int topleftCorner = coordConv((coord[0] + 3), (coord[1]));
					int toprightCorner = coordConv((coord[0] + 10), (coord[1]));
					int midleftCorner = coordConv(coord[0], (coord[1] + 6));
					int midrightCorner = coordConv((coord[0] + 12), (coord[1] + 6));
					int botleftCorner = coordConv((coord[0] + 3), coord[1] + 12);
					int botrightCorner = coordConv((coord[0] + 10), coord[1] + 12);
					
					if(worldArray[topleftCorner] != s2 && worldArray[toprightCorner] != s2 && worldArray[midleftCorner] != s2 && 
							worldArray[midrightCorner] != s2 && worldArray[botleftCorner] != s2 && worldArray[botrightCorner] != s2){
						happy = true;
					}
							

			}
			index = rand.nextInt(indexMax);
		}

		int oddEvenLine = (index / x) % 2;
		Boolean leftSide;
		if (oddEvenLine == 1) {
			leftSide = false;
		} else {
			leftSide = true;
		}
		int left = 3;
		int mid = 7;
		int right = 3;
		int i = index; 
		while (mid != 14) {
			i += left;
			for (int j = 0; j < mid; j++) {
				worldArray[i + j] = s; //occassionally going out of bounds - not sure why

			}
			i += mid;
			i += right;
			if (leftSide == false) {
				right--;
				mid++;
				leftSide = !leftSide;
				i += (150 - 13);
			} else {
				left--;
				mid++;
				leftSide = !leftSide;
				i += (150 - 13);
			}

		}
		leftSide = !leftSide;
		while (mid != 6) {
			i += left;
			for (int j = 0; j < mid; j++) {
				worldArray[i + j] = s;

			}
			i += mid;
			i += right;
			if (leftSide == false) {
				right++;
				mid--;
				leftSide = !leftSide;
				i += (150 - 13);
			} else {
				left++;
				mid--;
				leftSide = !leftSide;
				i += (150 - 13);
			}

		}

	}

	public void printWorld() {
		for (int i = 0; i < worldArray.length; i++) {
			if (i % x == 0) {
				System.out.println();
				if ((i / x) % 2 == 1) {
					//System.out.print(' ');
				}
			}
			System.out.print(worldArray[i]);
			//System.out.print(" ");
		}
	}

	public static void main(String[] args) {
		WorldGen test = new WorldGen(150, 150);
		try {
			test.exportWorld("test1");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public char[] getWorldArray() {
		return worldArray;
	}
	
	public void exportWorld(String filename) throws FileNotFoundException, UnsupportedEncodingException{
		String file = filename + ".world";
		PrintWriter writer = new PrintWriter(file, "UTF-8");
	
		writer.println(x);
		writer.println(y);
		
		for(int i = 0; i < worldArray.length; i++){
			if(i%150 == 0 && i != 0){
				writer.println();
			}
			writer.print(worldArray[i]);
			
		}
		
		writer.close();
	}
	
}
