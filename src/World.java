/* Game.java
 * Game controller. Handles game actions and creation.
 * v0.0.4 - 6/3/14 
 *
 * intelligAnts
 * Adam Pearce and Francis Poole
 * 5/3/14
 */


public class World {
	Ant[] ants; //array of all ants in the game.
	Cell[] cells; //array of all cells in the map.
	int dimensionX; //World X dimension
	int dimensionY; //World Y dimension
	int seed;
	
	
	public World(char[] worldArray){
		ants = new Ant[254];
		setUpWorld(worldArray);
	}
	
	

	//takes a filename/path, creates all the ant/cell objects for the game.
	public void setUpWorld(char[] worldArray){
		
		
		cells = new Cell[worldArray.length];
		int antIndex = 0;
		int index = 0;
		int c;
		
		for (int i = 0; i < worldArray.length; i++){
			Cell cell = null;
			switch(c = worldArray[i]){
			//Rocky cell
			case '#': 
				cell = new Cell(false, 0, 0, index);
				break;
			//Empty cell
			case '.': 
				cell = new Cell(true, 0, 0, index);
				break;
			//Red ant hill (team 1)
			case '+': 
				cell = new Cell(true, 0, 1, index);
				Ant ant = new Ant(antIndex, 1, cell);
				ants[antIndex] = ant;
				antIndex++;
				cell.setAnt(ant);
				break;
			//Black ant hill (team 2)
			case '-': 
				cell = new Cell(true, 0, 2, index);
				Ant ant2 = new Ant(antIndex, 2, cell);
				ants[antIndex] = ant2;
				antIndex++;
				cell.setAnt(ant2);
				break;
			//Food
			case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9':
				int digit = Character.getNumericValue(c);
				cell = new Cell(true, digit, 0, index);
			}
			
			//Add cell to world
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
	1-9: amount of food 
	+: red ant hill
	-: black ant hill
	*/
	public void printWorld(){
		for(int i = 0; i < cells.length; i++){
			if(i%dimensionX == 0){
				System.out.println();
				if ((i/dimensionX)%2 == 1){
					System.out.print(' ');
				}
			}
			System.out.print(cells[i]);
			System.out.print(" ");
		}
	}
	
	public Cell getNeighbourCell(Cell cell, int dir) {
		int cellID = cell.getCellID();
		//1 if odd
		int oddEvenLine = (cellID/dimensionX) % 2;
		switch(dir){
		case 0: 
			return cells[cellID + 1];
		case 1: 
			return cells[cellID + dimensionX + oddEvenLine];
		case 2: 
			return cells[cellID + dimensionX - 1 + oddEvenLine];
		case 3: 
			return cells[cellID - 1];
		case 4: 
			return cells[cellID - dimensionX - 1 + oddEvenLine];
		case 5: 
			return cells[cellID - dimensionX + oddEvenLine];
		}
		return null;
	}
	
	//Random Int
	public int randomInt(int n) {
		//Increment seed
		System.out.println();
		System.out.println(seed);
		seed = seed * 22695477 + 1;
		System.out.println(seed);
		seed = seed * 22695477 + 1;
		System.out.println(seed);
		seed = seed * 22695477 + 1;
		System.out.println(seed);
		seed = seed * 22695477 + 1;
		System.out.println(seed);
		seed = seed * 22695477 + 1;
		System.out.println(seed);
		int x = (seed / 65536) % n;
		
		System.out.println(x);
		return x;
	}
	
	//Any instructions
	//Move
	public Boolean move(Ant ant){
		
		Cell newCell = getNeighbourCell(ant.getCell(), ant.getDirection());
		
		if(newCell != null && newCell.isPassable()){
			ant.getCell().setAnt(null);
			ant.setCell(newCell);
			newCell.setAnt(ant);
			return true;
		} else {
			return false;
		}
	}
	
	//Turn
	public void turn(Ant ant, int dir) {
		ant.setDirection(dir);
	}
	
	//Mark
	public void mark(Ant ant, int markerNo) {
		switch(ant.getTeamID()){
		case 1:
			ant.getCell().setRedMarker(markerNo, true);
			break;
		case 2:
			ant.getCell().setBlackMarker(markerNo, true);
			break;
		}
	}
	
	//Unmark
	public void unmark(Ant ant, int markerNo) {
		switch(ant.getTeamID()){
		case 1:
			ant.getCell().setRedMarker(markerNo, false);
			break;
		case 2:
			ant.getCell().setBlackMarker(markerNo, false);
			break;
		}
	}
	
	//Pickup
	public void pickUp(Ant ant) {
		if (ant.getFood() == null) { //Check ant doesn't already have food
			ant.setFood(ant.getCell().removeFood()); //Removes food from cell and gives it to ant
		}
	}
	
	//Drop
	public void drop(Ant ant) {
		if (ant.getFood() != null) { //Check ant doesn't already have food
			ant.getCell().addFood(ant.getFood()); //
			ant.setFood(null);
		}
	}
	
	//Sense
	public void sense(Ant ant, int direction, String cond, int markerNo) {
		Cell neighbourCell = getNeighbourCell(ant.getCell(), ant.getDirection());
		boolean result = false;
		
		if (cond.equals("friend")) {
			result = (neighbourCell.getAnt().getTeamID() == ant.getTeamID());
		} else if (cond.equals("foe")) { 
			result = (neighbourCell.getAnt().getTeamID() != ant.getTeamID());
		} else if (cond.equals("friendWithFood")) { 
			result = (neighbourCell.getAnt().getTeamID() == ant.getTeamID() &&
				neighbourCell.getAnt().getFood() != null);
		} else if (cond.equals("foeWithFood")) {
			result = (neighbourCell.getAnt().getTeamID() != ant.getTeamID() &&
					neighbourCell.getAnt().getFood() != null);
		} else if (cond.equals("food")) {
			result = (neighbourCell.getFoodSize() > 0);
			
		} else if (cond.equals("rock")) {
			result = neighbourCell.isRocky();
		} else if (cond.equals("marker")) {
			if (ant.teamID == 1) {
				result = neighbourCell.getRedMarker(markerNo);
			} else {
				result = neighbourCell.getBlackMarker(markerNo);
			}
		} else if (cond.equals("foeMarker")) {
			if (ant.teamID == 1) {
				result = neighbourCell.containsRedMarker();
			} else {
				result = neighbourCell.containsBlackMarker();
			}
		} else if (cond.equals("home")) {
			result = (neighbourCell.getAntHill() == ant.getTeamID());
		} else if (cond.equals("foeHome")) {
			result = (neighbourCell.getAntHill() != ant.getTeamID() &&
					neighbourCell.getAntHill() != 0);
		}
		System.out.println(result);
	}
	
	//Combat Check
	public void combatCheck(Ant ant) {
		int adjacentAnts = 0;
		for (int i = 0; i < 5; i++) {
			Ant ant2 = getNeighbourCell(ant.getCell(), i).getAnt();
			if (ant2 != null && ant2.getTeamID() != ant.getTeamID()) {
				adjacentAnts ++;
			}
		}
		if (adjacentAnts >= 5) {
			ant.kill();
			for (int i = 0; i < 3; i++) {
				ant.getCell().addFood(new Food());
			}
		}
			
	}
	
	//Flip
	public boolean flip(Ant ant, int n) {
		return randomInt(n) == 0;
	}
	
	public void test(){
		Ant ant = ants[6];
		flip(ant,16384);
	}
}
