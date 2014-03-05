import java.util.ArrayList;

public class Cell {

	//rocky or not
	Boolean passable;
	//array and thus number of food in the cell.
	ArrayList<Food> food;
	//the ant that is in the cell.
	Ant ant;
	//an integer that represents whether a cell is an anthill and what team it is for (0 = not anthill, 1 = team 1 anthill, 2 = team 2 anthill)
	int antHill;
	//an array from 0-5 for red team markers
	Boolean[] redMarkers;
	//an array from 0-5 for black team markers
	Boolean[] blackMarkers;
	int cellID;
	
	public Cell(Boolean passable, int numOfFood, int anthill, int cellID){
		//initialise variables and set up food objects in the food array.
		this.passable = passable;
		food = new ArrayList<Food>();
		initFood(numOfFood);
		this.antHill = anthill;
		redMarkers = new Boolean[6];
		this.cellID = cellID;
		blackMarkers = new Boolean[6];
	}
	
	public void initFood(int amountOfFood){
		//puts one food object in the array for the amount of food in the cell.
		for(int i = 0; i < amountOfFood; i++){
			food.add(new Food());
		}
	}
	
	public void setAnt(Ant ant){
		this.ant = ant;
	}
	
	
	public String toString(){
		if(ant != null){
			if(ant.getTeamID() == 1){
				return "R";
			} else {
				return "B";
			}		
		} else
		if (passable == false){
			return "#";
		} else 
		if (food.isEmpty() == false){
			return "" + food.size();
		} else 
		if (antHill == 1){
			return "+";
		} else
		if (antHill == 2){
			return "-";
		} else {
			return ".";
		}
		
	}

	public int getCellID() {
		return cellID;
	}
	
	public Boolean isPassable(){
		if(passable == false){
			return false;
		} else
		if(ant != null){
			return false;
		} else {
			return true;
		}
	}
	
	
	
}
