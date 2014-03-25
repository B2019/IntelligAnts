/**
 * A Class to hold a brain that is used in game (as it has a team name).
 * @V 0.0.1
 * @Author 35769
 */

public class Brain {
	
	private InstructionParent[] brain;
	private String teamName;
	
	/**The constructor method for the Brain class
     * @Param InstructionParent[] inBrain - A full array of tokens provided by the brain reader
     * @Param String teamName - A team name (provided by the game match-up)
     */
	public Brain(InstructionParent[] inBrain, String teamName){
		
		this.brain = inBrain;
		this.teamName = teamName;
		
	}

    /**A method to return the brain
     * @Return InstructionParent[] brain - the whole array of instructions
     */
	public InstructionParent[] getBrain() {
		return brain;
	}
	
    /**A method to return a specific instruction from within the brain
     * @Param int instructToGet - An int that will identify a logical linear instruction
     * @Return brain[instructToGet] - a specific instruction.
     */
	public InstructionParent getInstruction(int instructToGet){
		return brain[instructToGet];
	}
	
	
	/**A method to return the brain team name
     * @Return String teamName - the team name
     */
	public String toString(){
		return this.teamName;
	}

}
