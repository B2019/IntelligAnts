import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Random;

public class WorldGen {

	int x;
	int y;
	String[] worldArray;

	public WorldGen(int x, int y) {
		this.x = x;
		this.y = y;
		newWorld();
		setUpPerim();
	}

	public void newWorld() {
		worldArray = new String[x * y];

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
			worldArray[i] = "#";
		}
		for (; i < ((x * y) - x); i++) {
			if (i % x == 0) {
				worldArray[i] = "#";
				worldArray[i + 149] = "#";
			} else {
				if (!(i % x == (x - 1))) {
					worldArray[i] = ".";
				}
			}
		}
		for (; i < x * y; i++) {
			worldArray[i] = "#";
		}
	}

	public void antHill(Boolean red) {
		String s = "";
		if (red == true) {
			s = "+";
		} else {
			// change to -
			s = "*";
		}
		Random rand = new Random();
		int indexMax = (x * y);
		int index = rand.nextInt(indexMax);
		Boolean happy = false;
		while (happy == false) {
			int[] coord = indexConv(index);
			if (coord[0] < (x - 14)) {
				if (coord[1] < (y - 14)) {

					if (red == false) {
						happy = true;
						
					} else {
						happy = true;
					}
				} else {
					index = rand.nextInt(indexMax);
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
					System.out.print(' ');
				}
			}
			System.out.print(worldArray[i]);
			System.out.print(" ");
		}
	}

	public static void main(String[] args) {
		WorldGen test = new WorldGen(150, 150);
		test.antHill(true);
		test.antHill(false);
		test.printWorld();
	}
}
