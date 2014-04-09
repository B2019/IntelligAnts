
import java.awt.Panel;
import java.io.IOException;
import java.util.Scanner;

public class Match {
	
	World world;
	int seed;
	int turn; //current turn out of 300000
	int tick; //current tick for the turn. (ant thats taking a go)
	int noOfAnts = 254;
<<<<<<< HEAD
	int currentTeam;
	int speed;
	GameGUI gui;
=======
	int currentTeam;
	//GameGUI gui;
>>>>>>> BrainDesign
	
	String redName;
	String blackName;
	Brain redBrain;
	Brain blackBrain;

	public Match(World world, int seed, String redName, String blackName, Brain redBrain, Brain blackBrain) {
		//Setup match
		this.world = world;
		world.resetScores();
		this.seed = seed;
		this.turn = 0;
		this.tick = 0;
		this.redName = redName;
		this.blackName = blackName;
		this.redBrain = redBrain;
<<<<<<< HEAD
		this.blackBrain = blackBrain;
		speed = 50;
		//gui = new GameGUI(this);
		
=======
		this.blackBrain = blackBrain;
		//gui = new GameGUI(this);
>>>>>>> BrainDesign
		//Initalise ant brains
		for(int i = 0; i < noOfAnts; i++){
			int antBrainNo = world.getAnt(i).getTeamID();
			if(antBrainNo == 1){
				world.getAnt(i).setInstruction(redBrain.getInstruction(0));
			}else if(antBrainNo == 2){
				world.getAnt(i).setInstruction(blackBrain.getInstruction(0));
			}
		}
		
	}

<<<<<<< HEAD
	public Boolean canGameGo(){
		if(gui.go == true){
			return true;
		} else {
			return false;
		}
	}
	
	public void setGUI(GameGUI game){
		this.gui = game;
	}
	
	public int runMatch() { //Remove Panel when removing DEV GUI!!!
		
		//Loops through each turn
		System.out.println("Game is a go");
		gui.updateZoomMap(gui.getZoomCell());
		//System.out.println("Updating zoom map");
		gui.updateTurn();
		gui.updateWorldMap();
		gui.updateBlackScore();
		gui.updateRedScore();
		
		while(turn <= 300000){
			
			
			//System.out.println("Turn");
=======
	public int runMatch(TesterGUI gui) { //Remove Panel when removing DEV GUI!!!


		//SETUP GUI - REMOVE!
		gui.createCells(this);

		//Loops through each turn
		while(turn <= 300000){
>>>>>>> BrainDesign
			//Gets World to loop through ants and get them to act
			for(tick = 0; tick < noOfAnts; tick++){
				
				//do something
				Ant ant = world.getAnt(tick);
				int antBrainNo = ant.getTeamID();
				
				if(ant.getAlive() == true){

					
					if(ant.getCooldown() > 0){
						ant.setCooldown(ant.getCooldown() - 1);
					} else {
						//System.out.println(tick);
						Instruction instruction = ant.getInstruction();
						//check is move instruction 
						//if it is, do move
						//which will then return a boolean. next instruction will be based on this
						//get ants instruction, st1 if true st2 if false
						//then go to team brain, and set instruction to instruction at instruction no. st1 or st2
						int newInstructionNo = 0;
						boolean result;
						
						switch (instruction.getClass().getName()) {
							case "Move" :
								result = world.move(ant);
								if (result) {
									newInstructionNo = ((Move)instruction).getSt1();
								}
								else{
									newInstructionNo = ((Move)instruction).getSt2();
								}
								
								break;
							case "Turn" :
								String lr = ((Turn)instruction).getLr();
								world.turn(ant, lr);
								newInstructionNo = ((Turn)instruction).getSt();
								break;
							case "Mark" :
								int markerNo = ((Mark)instruction).getI();
								world.mark(ant, markerNo);
								newInstructionNo = ((Mark)instruction).getSt();
								break;
							case "Unmark" :
								markerNo = ((Unmark)instruction).getI();
								world.unmark(ant, markerNo);
								newInstructionNo = ((Unmark)instruction).getSt();
								break;
							case "PickUp" :
								boolean hasFood = world.pickUp(ant);
								if(hasFood){
									newInstructionNo = ((PickUp)instruction).getSt1();
								}
								else{
									newInstructionNo = ((PickUp)instruction).getSt2();
								}
								break;
							case "Drop" :
								world.drop(ant);
								newInstructionNo = ((Drop)instruction).getSt();
								break;
							case "Sense" :
								String sensedir = ((Sense)instruction).getSensedir();
								String cond = ((Sense)instruction).getCond();
								markerNo = ((Sense)instruction).getI();
								result = world.sense(ant, sensedir, cond, markerNo);
								System.out.println(result);
								if(result){
									newInstructionNo = ((Sense)instruction).getSt1(); 
								}
								else{
									newInstructionNo = ((Sense)instruction).getSt2();
								}
								break;
							case "Flip" :
								int n = ((Flip)instruction).getP();
								result = world.flip(ant, n);
								if (result) {
									newInstructionNo = ((Flip)instruction).getSt1();
								}
								else {
									newInstructionNo = ((Flip)instruction).getSt2();
								}
								break;
						}
						if (antBrainNo == 1) {
							ant.setInstruction(redBrain.getInstruction(newInstructionNo));
						} else if(antBrainNo == 2) {
							ant.setInstruction(blackBrain.getInstruction(newInstructionNo));
						}
						
					}
				}
				
<<<<<<< HEAD
			}
			turn += 1;
			
			if(turn%speed == 0){
			
			
				gui.updateZoomMap(gui.getZoomCell());
				gui.updateTurn();
				gui.updateWorldMap();
				gui.updateBlackScore();
				gui.updateRedScore();
			
			}
=======
			}
			turn += 1;
>>>>>>> BrainDesign
			
			gui.updateCells(this);
	        gui.getPanel().repaint();
	        gui.getPanel().revalidate();
		}
		//Get winner
		int redScore = world.getRedScore();
		int blackScore = world.getBlackScore();
		if (redScore > blackScore) {
			return 1;	//Red wins!
		} else if (blackScore > redScore) {
			return 2;	//Black wins!
		}
		return 0; //Its a draw!
	}

	public String getCurrentTeam(){
		return "Team " + currentTeam;
	}
	public World getWorld() {
		return world;
	}

	public int getSeed() {
		return seed;
	}

	public int getTurn() {
		return turn;
	}

	public int getTick() {
		return tick;
	}

	public String getRedName() {
		return redName;
	}

	public String getBlackName() {
		return blackName;
	}

	public Brain getRedBrain() {
		return redBrain;
	}

	public Brain getBlackBrain() {
		return blackBrain;
	}

//	public static void main(String[] args) {
////		Match match = new Match(new World(150, 150), 888, "a", "b", new Brain("testbrain.brain"), new Brain("testbrain.brain"));
//		match.runMatch();
//	}
	
}
