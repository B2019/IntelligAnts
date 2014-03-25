/**
 * A token that is an instruction object.
 * @V 0.0.1
 * @Author 35769
 */

public class T_instruction extends TokenParent{
	private String instruction;
	private int ID;
	
	/**The constructor for the 'Instruction' Token
     * @Param String inInstruct - The instruction as a string
     * @Param int ID - The identification number for the token
     */
	public T_instruction(String inInstruct, int inID){
		super(inID);
		this.instruction = inInstruct;
		this.ID = inID;
	}
	
	/**A method to return the instruction of this token in correct syntax
     * @Return String - a specific instruction in syntactic form.
     */
	public String toString(){
		return "T_instruction: "+this.instruction;
	}

	/**A method to return the 'plain' instruction of this token
     * @Return String instruction - a specific string instruction.
     */
	public String getInstruction() {
		return instruction;
	}
	
	/**A method to return the identification number for this token
     * @Return int ID - The unique identification number
     */
	public int getID() {
		return ID;
	}
}
