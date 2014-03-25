/**
 * A Parent class for Instructions.
 * @V 0.0.1
 * @Author 35769
 */
public class InstructionParent {
	private int instructionID;
	
    /**
     * The constructor method for the Instruction 'parent' class
     * @Param int inID - takes the instruction id number and stores it.
     */
	public InstructionParent(int inID){
		this.instructionID = inID;
	}

    /**
     * A method to return the unique instruction identifier
     * @Return int inID - the identification number for the instruction
     */
	public int getInstructionID() {
		return instructionID;
	}
}
