/**
 * A Parent class for Tokens.
 * @V 0.0.1
 * @Author 35769
 */
public class TokenParent {
	
	private int instructionID;
	
    /**The constructor method for the Token 'parent' class
     * @Param int inID - takes the instruction id number and stores it.
     */
	public TokenParent(int inID){
		this.instructionID = inID;
	}

    /**A method to return the unique instruction identifier
     * @Return int inID - the identification number for the instruction
     */
	public int getInstructionID() {
		return instructionID;
	}
}
