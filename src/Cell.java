/* Cell.java
 * Dumb cell object 
 * v0.0.4 - 6/3/14 
 *
 * intelligAnts
 * Adam Pearce and Francis Poole
 * 5/3/14
 */

import java.util.ArrayList;

public class Cell {
	boolean passable; //rocky or not
	ArrayList<Food> food; //array and thus number of food in the cell.
	Ant ant; //the ant that is in the cell.
	int antHill; //an integer that represents whether a cell is an anthill and what team it is for (0 = not anthill, 1 = team 1 anthill, 2 = team 2 anthill)
	boolean[] redMarkers; //an array from 0-5 for red team markers
	boolean[] blackMarkers; //an array from 0-5 for black team markers
	int cellID;
	
	public Cell(boolean passable, int numOfFood, int anthill, int cellID){
		//initialise variables and set up food objects in the food array.
		this.passable = passable;
		food = new ArrayList<Food>();
		initFood(numOfFood);
		this.antHill = anthill;
		redMarkers = new boolean[6];
		this.cellID = cellID;
		blackMarkers = new boolean[6];
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
	
	public Ant getAnt() {
		return ant;
	}
	
	public void setRedMarker(int markerNo, boolean state) {
		redMarkers[markerNo] = state;
	}
	
	public void setBlackMarker(int markerNo, boolean state) {
		blackMarkers[markerNo] = state;
	}
	
	public boolean getRedMarker(int markerNo) {
		return redMarkers[markerNo];
	}
	
	public boolean getBlackMarker(int markerNo) {
		return blackMarkers[markerNo];
	}
	
	public boolean containsRedMarker() {
		for (int i = 0; i < redMarkers.length; i++) {
			if (redMarkers[i] == true){
				return true;
			}
		}
		return false;
	}
	
	public boolean containsBlackMarker() {
		for (int i = 0; i < redMarkers.length; i++) {
			if (redMarkers[i] == true){
				return true;
			}
		}
		return false;
	}

	public int getCellID() {
		return cellID;
	}
	
	public boolean isPassable(){
		if(passable == false || ant != null){
			return false;
		} else {
			return true;
		}
	}
	

	public boolean isRocky() {
		return passable;
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

	public Food removeFood() {
		if (food.size() > 0) {
			return food.remove(food.size() - 1);
		} else {
			return null;
		}
	}

	public void addFood(Food food) {
		this.food.add(food);
		
	}

	public int getFoodSize() {
		return food.size();
	}

	public int getAntHill() {
		return antHill;
	}

	
	
	
}
