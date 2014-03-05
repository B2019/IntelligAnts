public class Ant {

	//whether the ant is alive or not
	Boolean alive;
	//the id of the ant
	int antID;
	//the team id of the ant.
	int teamID;
	//the direction of the ant (0-5)
	int direction;
	//the cooldown of the ant from its last move action/
	int cooldown;
	//the Food object the ant is carrying
	Food food;
	//need to add current instruction
	//current cell ant is in. 
	Cell cell;
	
	public Ant(int antID, int teamID, Cell cell){
		//initialise variables 
		alive = true;
		this.antID = antID;
		this.teamID = teamID;
		direction = 0;
		cooldown = 0;
		food = null;
		this.cell = cell;
	}
	
	public int getTeamID(){
		return teamID;
	}
	
	public String toString(){
		return "" + antID;
	}
	
	public void setCell(Cell cell){
		this.cell = cell;
	}
	
	public Cell getCell(){
		return cell;
	}
	
	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	
}
