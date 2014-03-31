import java.io.IOException;
import java.util.Scanner;


public class Match {
	
	World world;
	int seed;
	int turn; //current turn out of 300000
	int tick; //current tick for the turn. (ant thats taking a go)
	int noOfAnts = 254;
	
	String redName;
	String blackName;
	Brain redBrain;
	Brain blackBrain;

	public Match(World world, int seed, String redName, String blackName, Brain redBrain, Brain blackBrain) {
		//Setup match
		this.world = world;
		this.seed = seed;
		this.turn = 0;
		this.tick = 0;
		this.redName = redName;
		this.blackName = blackName;
		this.redBrain = redBrain;
		this.blackBrain = blackBrain;
		for(int i = 0; i < noOfAnts; i++){
			int antBrainNo = world.getAnt(i).getTeamID();
			if(antBrainNo == 1){
				world.getAnt(i).setInstruction(redBrain.getInstruction(0));
			}else if(antBrainNo == 2){
				world.getAnt(i).setInstruction(blackBrain.getInstruction(0));
			}
		}
	}

	public void runMatch() {
		//Loops through each turn
		while(turn <= 300000){
			//Gets World to loop through ants and get them to act
			for(tick = 0; tick < noOfAnts; tick++){
				//do something
				Ant ant = world.getAnt(tick);
				int antBrainNo = ant.getTeamID();
				if(ant.getAlive() == true){
					if(ant.getCooldown() != 0){
						ant.setCooldown(ant.getCooldown() - 1);
					}
					else{
						Instruction instruction = ant.getInstruction();
						//check is move instruction 
						//if it is, do move
						//which will then return a boolean. next instruction will be based on this
						//get ants instruction, st1 if true st2 if false
						//then go to team brain, and set instruction to instruction at instruction no. st1 or st2
						int newInstructionNo;
						boolean result;
						switch (instruction.getClass().getName()) {
						case "Move" :
							result = world.move(ant);
							if (result) {
								newInstructionNo = ((Move)instruction).getSt1();
								if(antBrainNo == 1){
									ant.setInstruction(redBrain.getInstruction(newInstructionNo));
								}
								else{
									ant.setInstruction(blackBrain.getInstruction(newInstructionNo));
								}
								ant.setCooldown(14);
							}
							else{
								newInstructionNo = ((Move)instruction).getSt2();
								if(antBrainNo == 1){
									ant.setInstruction(redBrain.getInstruction(newInstructionNo));
								}
								else{
									ant.setInstruction(blackBrain.getInstruction(newInstructionNo));
								}
							}
							break;
						case "Turn" :
							String lr = ((Turn)instruction).getLr();
							int oldDirection = ant.getDirection();
							int dir;
							if(lr == "left"){
								dir = (oldDirection - 1)%6;
							}else if(lr == "right"){
								dir = (oldDirection + 1)%6;
							}
							//print statement to make sure mod function is producing right direction
							System.out.println(dir);
							world.turn(ant, dir);
							newInstructionNo = ((Turn)instruction).getSt();
							if(antBrainNo == 1){
								ant.setInstruction(redBrain.getInstruction(newInstructionNo));
							}
							else{
								ant.setInstruction(blackBrain.getInstruction(newInstructionNo));
							}
							break;
						case "Mark" :
							int markerNo = ((Mark)instruction).getI();
							world.mark(ant, markerNo);
							newInstructionNo = ((Mark)instruction).getSt();
							if(antBrainNo == 1){
								ant.setInstruction(redBrain.getInstruction(newInstructionNo));
							}
							else{
								ant.setInstruction(blackBrain.getInstruction(newInstructionNo));
							}
							break;
						case "Unmark" :
							markerNo = ((Unmark)instruction).getI();
							world.unmark(ant, markerNo);
							newInstructionNo = ((Unmark)instruction).getSt();
							if(antBrainNo == 1){
								ant.setInstruction(redBrain.getInstruction(newInstructionNo));
							}
							else{
								ant.setInstruction(blackBrain.getInstruction(newInstructionNo));
							}
							break;
						case "PickUp" :
							boolean hasFood = world.pickUp(ant);
							if(hasFood){
								newInstructionNo = ((PickUp)instruction).getSt1();

								if(antBrainNo == 1){
									ant.setInstruction(redBrain.getInstruction(newInstructionNo));
								}
								else{
									ant.setInstruction(blackBrain.getInstruction(newInstructionNo));
								}
							}
							else{
								newInstructionNo = ((PickUp)instruction).getSt2();

								if(antBrainNo == 1){
									ant.setInstruction(redBrain.getInstruction(newInstructionNo));
								}
								else{
									ant.setInstruction(blackBrain.getInstruction(newInstructionNo));
								}
							}
							break;
						case "Drop" :
							world.drop(ant);
							newInstructionNo = ((Drop)instruction).getSt();
							if(antBrainNo == 1){
								ant.setInstruction(redBrain.getInstruction(newInstructionNo));
							}
							else{
								ant.setInstruction(blackBrain.getInstruction(newInstructionNo));
							}
							break;
						case "Sense" :
							System.out.print("Sense - ");
							System.out.print(((Sense)instructions.get(i)).getSensedir() + " - ");
							System.out.print(((Sense)instructions.get(i)).getSt1() + " - ");
							System.out.print(((Sense)instructions.get(i)).getSt2() + " - ");
							System.out.print(((Sense)instructions.get(i)).getCond() + " - ");
							System.out.print(((Sense)instructions.get(i)).getI());
							System.out.println();
							break;
						case "Flip" :
							int n = ((Flip)instruction).getP();
							result = world.flip(ant, n);
							if (result) {
								newInstructionNo = ((Flip)instruction).getSt1();
								if(antBrainNo == 1){
									ant.setInstruction(redBrain.getInstruction(newInstructionNo));
								}
								else{
									ant.setInstruction(blackBrain.getInstruction(newInstructionNo));
								}
							}
							else{
								newInstructionNo = ((Flip)instruction).getSt2();
								if(antBrainNo == 1){
									ant.setInstruction(redBrain.getInstruction(newInstructionNo));
								}
								else{
									ant.setInstruction(blackBrain.getInstruction(newInstructionNo));
								}
							}
							break;
					}
					}
				}
				
				if(antBrainNo == 1){
					ant.setInstruction(redBrain.getInstruction(0));
				}else if(antBrainNo == 2){
					ant.setInstruction(blackBrain.getInstruction(0));
				}
				
			}
			turn += 1;
		}
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

	
}
