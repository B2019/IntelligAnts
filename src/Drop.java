/**
 * An instruction OBJECT called 'Drop'.
 * @V 0.0.1
 * @Author 35769
 */

public class Drop extends InstructionParent{
	
	private int st;

	/**The constructor for the 'Drop' Instruction
     * @Param int st - The state to go to after the instruction
     */
	public Drop(int inSt) {
		super(5);
		this.st = inSt;
	}

	/**A method to return the state of the current instruction
     * @Return int st - a specific instruction state number.
     */
	public int getSt() {
		return st;
	}
}
