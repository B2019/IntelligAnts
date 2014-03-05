import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Game {

	//array of all cells in the map.
	Cell[] cells;
	//current turn out of 300000
	int turn;
	//current tick for the turn. (ant thats taking a go)
	int tick;
	//array of all ants in the game.
	Ant[] ants;
	int dimensionX;
	int dimensionY;
	
	public Game(){
		ants = new Ant[254];
		
		
		
	}
	
	//takes a filename/path, creates all the ant/cell objects for the game.
	public void setUpWorld(String fileName) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		//get the dimensions from first two lines.
		dimensionX = Integer.parseInt(in.readLine());
		dimensionY = Integer.parseInt(in.readLine());
		cells = new Cell[dimensionX*dimensionY];
		int antIndex = 0;
		int index = 0;
		int c;
		while((c = in.read()) != -1){
			Cell cell = null;
			switch((char) c){
			case '#': cell = new Cell(false, 0, 0, index);
			break;
			case '.': cell = new Cell(true, 0, 0, index);
			break;
			//red ant hill (1)
			case '+': cell = new Cell(true, 0, 1, index);
			Ant ant = new Ant(antIndex, 1, cell);
			ants[antIndex] = ant;
			antIndex++;
			cell.setAnt(ant);
			break;
			case '-': cell = new Cell(true, 0, 2, index);
			Ant ant2 = new Ant(antIndex, 2, cell);
			ants[antIndex] = ant2;
			antIndex++;
			cell.setAnt(ant2);
			break;
			case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9':
				int digit = Character.getNumericValue(c);
				cell = new Cell(true, digit, 0, index);
			}
			if(cell != null){
			cells[index] = cell;
			index++;
			}
		}
		
	}
	
	//prints out the game in text format. 
	/*
	B: black ant
	R: red ant
	#: rock
	digit: amount of food on 
	+: red ant hill
	-: black ant hill
	*/
	public void printGame(){
		for(int i = 0; i < cells.length; i++){
			if(i%dimensionX == 0){
				System.out.println();
				if ((i/dimensionX)%2 == 1){
					System.out.print(' ');
				}
			}
			/*if(((i/dimensionX) % 2) == 1){
				System.out.print(" ");
			}*/
			System.out.print(cells[i]);
		}
	}
	
	public static void main(String [] args) throws IOException{
		Game game = new Game();
		game.setUpWorld("/Users/Adam/Documents/University/Year2/Term2/SE/IntelligAnts/IntelligAnts/src/1.world.txt");
		game.printGame();
		game.testMoveAnt();
		
	}
	
	public Boolean moveAnt(Ant ant){
		
		//doesnt check if movable to YET.
		
		int dir = ant.getDirection(); 
		int currentCell = ant.getCell().getCellID();
		int newCell = 0;
		//1 if odd
		int oddEvenLine = (currentCell/dimensionX) % 2;
		switch(dir){
		case 0: newCell = (currentCell + 1);
		break;
		case 1: newCell = (currentCell + dimensionX + oddEvenLine);
		break;
		case 2: newCell = currentCell + dimensionX - 1 + oddEvenLine;
		break;
		case 3: newCell = currentCell - 1;
		break;
		case 4: newCell = currentCell - dimensionX - 1 + oddEvenLine;
		break;
		case 5: newCell = currentCell - dimensionX + oddEvenLine;
		}
		
		if(cells[newCell].isPassable()){
			ant.setCell(cells[newCell]);
			cells[currentCell].setAnt(null);
			cells[newCell].setAnt(ant);
			return true;
		} else {
			return false;
		}
		
		
	}

	public void testMoveAnt(){
		Ant testAnt = ants[6];
		testAnt.setDirection(0);
		moveAnt(testAnt);

		printGame();
		testAnt.setDirection(5);
		moveAnt(testAnt);

		printGame();
		testAnt.setDirection(4);
		moveAnt(testAnt);
		printGame();
		testAnt.setDirection(3);
		moveAnt(testAnt);
		printGame();
		testAnt.setDirection(2);
		moveAnt(testAnt);
		printGame();
		testAnt.setDirection(1);
		moveAnt(testAnt);
		printGame();
		
	}
}
