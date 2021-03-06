import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/* Game.java
 * Game controller. Handles game actions and creation.
 * v0.0.4 - 6/3/14 
 *
 * intelligAnts
 * Adam Pearce and Francis Poole
 * 5/3/14
 */


public class World {
	private Ant[] ants;
	private Cell[] cells;
	private int dimensionX; //World X dimension
	private int dimensionY; //World Y dimension
	private int redScore;
	private int blackScore;
	private Random rand;
	
	public World(String fileName, int seed) throws NumberFormatException, IOException, WorldGenException {
		BufferedReader in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream (fileName)));
		
		//Get the dimensions from first two lines.
		this.dimensionX = Integer.parseInt(in.readLine());
		this.dimensionY = Integer.parseInt(in.readLine());
		//Setup Cells and Ants
		this.ants = new Ant[254];
		this.cells = new Cell[dimensionX * dimensionY];
		this.redScore = 0;
		this.blackScore = 0;
		this.rand = new Random(seed);
		
		char[] charCells = new char[dimensionX * dimensionY];
		int i = 0;
		int c;
		try {
			while((c = in.read()) != -1) {
				if ((char)c != ' ' && (char)c != '\n') {
					charCells[i] = (char)c;
					i++;
				}
			}
		} catch (Exception e) {
			throw new WorldGenException();
		}
		convCharCells(charCells);
		//printWorld();
	}
	
	public World(int seed) throws WorldGenException {
		//Create new random world using WorldGen
		this.dimensionX = 150;
		this.dimensionY = 150;
		//Setup Cells and Ants
		this.ants = new Ant[254];
		this.cells = new Cell[dimensionX * dimensionY];
		this.redScore = 0;
		this.blackScore = 0;
		this.rand = new Random(seed);
		
		WorldGen worldGen = new WorldGen(seed);
		convCharCells(worldGen.getWorldArray());
	}
	
	public void resetScores(){
		blackScore = 0;
		redScore = 0;
	}
	
	public void convCharCells(char[] charCells) throws WorldGenException {
		int antIndex = 0;
		int cellIndex = 0;
		char c = 0;
		while (cellIndex < charCells.length) {
			c = charCells[cellIndex];
			switch(c) {
			//Rocky cell
			case '#': 
				cells[cellIndex] = new Cell(false, 0, 0, cellIndex);
				cellIndex++;
				break;
			//Empty cell
			case '.': 
				cells[cellIndex] = new Cell(true, 0, 0, cellIndex);
				cellIndex++;
				break;
			//Red ant hill (team 1)
			case '+': 
				cells[cellIndex] = new Cell(true, 0, 1, cellIndex);
				Ant ant = new Ant(antIndex, 1, cells[cellIndex]);
				this.ants[antIndex] = ant;
				antIndex++;
				this.cells[cellIndex].setAnt(ant);
				cellIndex++;
				break;
			//Black ant hill (team 2)
			case '-': 
				cells[cellIndex] = new Cell(true, 0, 2, cellIndex);
				Ant ant2 = new Ant(antIndex, 2, cells[cellIndex]);
				ants[antIndex] = ant2;
				antIndex++;
				cells[cellIndex].setAnt(ant2);
				cellIndex++;
				break;
			//Food
			case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9':
				int digit = Character.getNumericValue(c);
				cells[cellIndex] = new Cell(true, digit, 0, cellIndex);
				cellIndex++;
				break;
			default :
				throw new WorldGenException(c);
					
			}
		}
	}

	
	
	/*Prints out the game in text format. 
	#: rock				1-9: amount of food 
	R: red ant			B: black ant
	+: red ant hill		-: black ant hill
	*/
	public void printWorld(){
		for(int i = 0; i < cells.length; i++){
			//Print new lines
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
	
	//Any instructions
	//Move
	public Boolean move(Ant ant){
		
		Cell newCell = getNeighborCell(ant.getCell(), ant.getDirection());
		
		if(ant.getCooldown() <= 0 && newCell != null && newCell.isPassable()){
			ant.getCell().setAnt(null);
			ant.setCell(newCell);
			newCell.setAnt(ant);
			ant.setCooldown(14);
			//Check if it gets attacked
			combatCheck(ant);
			//Check if it attacks
			for (int i = 0; i < 6; i++) {
				if (getNeighborCell(ant.getCell(), i).getAnt() != null) {
					combatCheck(getNeighborCell(ant.getCell(), i).getAnt());
				}
			}
			return true;
		} else {
			return false;
		}
	}
	
	//Turn
	public void turn(Ant ant, String lr) {
		int oldDirection = ant.getDirection();
		int dir = 0;
		if(lr.equals("left")){
			dir = (((oldDirection - 1) % 6) + 6) % 6;
		}else if(lr.equals("right")){
			dir = (oldDirection + 1)%6;
		}
		ant.setDirection(dir);
	}
	
	//Mark
	public void mark(Ant ant, int markerNo) {
		switch(ant.getTeamID()){
		case 1:
			//System.out.println(ant.getCell().getRedMarker(markerNo));
			ant.getCell().setRedMarker(markerNo, true);
			//System.out.println(ant.getCell().getRedMarker(markerNo));
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
	public boolean pickUp(Ant ant) {
		if (ant.getFood() == null) { //Check ant doesn't already have food
			Food food = ant.getCell().removeFood();
			if(food != null){
				ant.setFood(food);//Removes food from cell and gives it to ant
				return true;
			}
		}
		else{
			return (ant.getCell().getFoodSize() > 0);
		}
		return false;
	}
	
	//Drop
	public void drop(Ant ant) {
		if (ant.getFood() != null) { //Check ant has food
			ant.getCell().addFood(ant.getFood()); //
			if (ant.getCell().getAntHill() == 1) {
				redScore++;
			} else if (ant.getCell().getAntHill() == 2) {
				blackScore++;
			}
			ant.setFood(null);
		}
	}
	
	//Sense
	public boolean sense(Ant ant, String sensedir, String cond, int markerNo) {
		Cell sensedCell = null;
		//Get sensed cell
		if (sensedir.equals("here")) {
			sensedCell = ant.getCell();
		} else if (sensedir.equals("ahead")) {
			sensedCell = getNeighborCell(ant.getCell(), ant.getDirection());
		} else if (sensedir.equals("leftahead")) {
			int relativeDirection = ((((ant.getDirection() - 1) % 6) + 6) % 6);
			sensedCell = getNeighborCell(ant.getCell(), relativeDirection);
		} else if (sensedir.equals("rightahead")) {
			sensedCell = getNeighborCell(ant.getCell(), (ant.getDirection() + 1) % 6);
		}
		
		boolean result = false;
		
		if (cond.equals("friend")) {
			result = (sensedCell.getAnt() != null && sensedCell.getAnt().getTeamID() == ant.getTeamID());
		} else if (cond.equals("foe")) { 
			result = (sensedCell.getAnt() != null && sensedCell.getAnt().getTeamID() != ant.getTeamID());
		} else if (cond.equals("friendWithFood")) { 
			result = (sensedCell.getAnt() != null && sensedCell.getAnt().getTeamID() == ant.getTeamID() &&
					sensedCell.getAnt().getFood() != null);
		} else if (cond.equals("foeWithFood")) {
			result = (sensedCell.getAnt() != null && sensedCell.getAnt().getTeamID() != ant.getTeamID() &&
					sensedCell.getAnt().getFood() != null);
		} else if (cond.equals("food")) {
			result = (sensedCell.getFoodSize() > 0);
		} else if (cond.equals("rock")) {
			result = sensedCell.isRocky();
		} else if (cond.equals("marker")) {
			if (ant.getTeamID() == 1) {
				result = sensedCell.getRedMarker(markerNo);
			} else {
				result = sensedCell.getBlackMarker(markerNo);
			}
		} else if (cond.equals("foeMarker")) {
			if (ant.getTeamID() == 1) {
				result = sensedCell.containsBlackMarker();
			} else {
				result = sensedCell.containsRedMarker();
			}
		} else if (cond.equals("home")) {
			result = (sensedCell.getAntHill() == ant.getTeamID());
		} else if (cond.equals("foeHome")) {
			result = (sensedCell.getAntHill() != ant.getTeamID() &&
					sensedCell.getAntHill() != 0);
		}
		return result;
	}
	
	//Flip
	public boolean flip(Ant ant, int n) {
		//produces int between 0 & n-1
		int x = rand.nextInt(n);
		if(x ==0){
			//st1
			return true;
		}
		else{
			//st2
			return false;
		}
	}
	

	//Combat Check
	public void combatCheck(Ant ant) {
		int adjacentAnts = 0;
		for (int i = 0; i < 5; i++) {
			Ant ant2 = getNeighborCell(ant.getCell(), i).getAnt();
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
	
	//Get neighboring cell
	public Cell getNeighborCell(Cell cell, int dir) {
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
	
	public Ant getAnt(int i){
		return ants[i];
	}

	public Cell getCell(int i) {
		return cells[i];
	}
	
	public Cell[] getCells() {
		return cells;
	}
	
	public int getRedScore() {
		return redScore;
	}
	
	public int getBlackScore() {
		return blackScore;
	}
	
}
