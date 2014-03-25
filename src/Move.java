/**
 * An instruction OBJECT called 'Move'.
 * @V 0.0.1
 * @Author 35769
 */

public class Move extends InstructionParent{
	private int st1;
	private int st2;
	
	/**The constructor for the 'Move' Instruction
     * @Param int inSt1 - The pass state
     * @Param int inSt2 - The fail state
     */
	public Move(int inSt1, int inSt2) {
		super(7);
		this.st1 = inSt1;
		this.st2 = inSt2;
	}

	/**A method to return the state of the current instruction
     * @Return int st1 - a specific instruction state number.
     */
	public int getSt1() {
		return st1;
	}

	/**A method to return the state of the current instruction
     * @Return int st2 - a specific instruction state number.
     */
	public int getSt2() {
		return st2;
	}
}
