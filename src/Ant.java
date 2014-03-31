/* Ant.java
 * Dumb ant object 
 * v0.0.4 - 6/3/14 
 *
 * intelligAnts
 * Adam Pearce and Francis Poole
 * 5/3/14
 */

public class Ant {

	Boolean alive; //whether the ant is alive or not
	int antID; //the id of the ant
	int teamID; //the team id of the ant.
	int direction; //the direction of the ant (0-5)
	int cooldown; //the cooldown of the ant from its last move action
	Food food; //the Food object the ant is carrying
	Cell cell; //current cell ant is in. 
<<<<<<< HEAD
=======
	
	//need to add current instruction
>>>>>>> FETCH_HEAD
	Instruction instruction;
	
	//Constructor
	public Ant(int antID, int teamID, Cell cell){
		//initialise variables 
		alive = true;
		this.antID = antID;
		this.teamID = teamID;
		direction = 0;
		cooldown = 0;
		food = null;
		this.cell = cell;
		instruction = null;
	}
	
	//Getters and Setters
	//Alive
	public boolean getAlive() {
		return alive;
	}
	
	public void kill() {
		alive = false;
	}
	
	//AntID
	public int getAntID() {
		return antID;
	}

	//TeamID
	public int getTeamID(){
		return teamID;
	}
	
	//Direction
	public int getDirection() {
		return direction;
	}
	
	//Instruction
	public Instruction getInstruction(){
		return instruction;
	}
	
	public void setInstruction(Instruction newInstruction){
		instruction = newInstruction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	//Cooldown
	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	//Food
	public Food getFood() {
		return food;
	}
	
	public void setFood(Food food) {
		this.food = food;
	}
	
	//Cell
	public Cell getCell(){
		return cell;
	}
	
	public void setCell(Cell cell){
		this.cell = cell;
	}

	public String toString(){
		return "" + antID;
	}




	
	
}
