import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Random;

public class WorldGen {

	int x;
	int y;
	char[] worldArray;

	
	
	public WorldGen() {
		
	}

	public World loadWorld(String fileName) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream (fileName)));
		//get the dimensions from first two lines.
		x = Integer.parseInt(in.readLine());
		y = Integer.parseInt(in.readLine());
		char[] worldArray = new char[x * y];
		
		int c;
		int i = 0;
		while((c = in.read()) != -1) {
			worldArray[i] = (char) c; 
		}
		return new World(worldArray);
	}
	
	public World newWorld(int x, int y) {
		worldArray = new char[x * y];
		setUpPerim();
		antHill(true);
		antHill(false);
		for(int i = 0; i < 11; i++){
		food();
		}
		for(int i = 0; i < 14; i++){
			rocks();
		}
		return new World(worldArray);
	}

	private int coordConv(int X, int Y) {
		return X + (Y * x);
	}

	private int[] indexConv(int index) {
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

	private void setUpPerim() {
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

	
	private void rocks(){
		char s2 = '.';
		Random rand = new Random();
		int indexMax = (x * y);
		int index = rand.nextInt(indexMax);
		int k = 0;
		Boolean happy = false;
		Boolean innerHappy = true;
		while(happy == false){
			innerHappy = true;
			for(int i = 0; i < 3; i++){
				if(worldArray[(index)+i+(k*x)] != '.'){
					innerHappy = false;
					break;
				}
			}
			if (innerHappy == false){
			k = 0;
			index = rand.nextInt(indexMax);
			} else {
			k++;
			if(k > 3){
				happy = true;
			}
			}
		}
		worldArray[index + 1 + 150] = '#';
		
		
	}
	
	private void food(){
		int foodSize = 5;
		char s2 = '.';
		Random rand = new Random();
		int indexMax = (x * y);
		int index = rand.nextInt(indexMax);
		int k = 0;
		Boolean happy = false;
		Boolean innerHappy = true;
		while(happy == false){
			innerHappy = true;
			for(int i = 0; i < 9; i++){
				if(worldArray[(index)+(k*(x-1))+i] != '.'){
					innerHappy = false;
					break;
				}
			}
			if (innerHappy == false){
			k = 0;
			index = rand.nextInt(indexMax);
			} else {
			k++;
			if(k > 9){
				happy = true;
			}
			}
		}
		int dir = rand.nextInt(2);
		Boolean left = true;
		if(dir < 1){
			left = false;
		}
		
		if(left == true){
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
		} else {
			boolean newLine = false;
			for(int i = 0; i < 5; i++){
				
				for(int j = 0; j < 5; j++){
					worldArray[index + j] = '5';
					
				}
				if(newLine == true){
					
					index = index+x-1;
					newLine = false;
				}
				else{
					index += x;
					newLine = true;
				}
			}
			
			
		}
		
		
	}
	
	private void antHill(Boolean red) {
		char s;
		char s2;
		if (red == true) {
			s = '+';
			s2 = '-';
		} else {
			s = '-';
			s2 = '+';
		}
		Random rand = new Random();
		int indexMax = (x * y);
		int index = rand.nextInt(indexMax);
		
		
		int k = 0;
		Boolean happy = false;
		Boolean innerHappy = true;
		while(happy == false){
			innerHappy = true;
			for(int i = 0; i < 15; i++){
				if(worldArray[(index)+(k*(x-1))+i] != '.'){
					innerHappy = false;
					break;
				}
			}
			if (innerHappy == false){
			k = 0;
			index = rand.nextInt(indexMax);
			} else {
			k++;
			if(k > 15){
				happy = true;
			}
			}
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
				worldArray[i + j] = s;

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

	
	public void exportNewWorld(String filename) throws FileNotFoundException, UnsupportedEncodingException{
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
