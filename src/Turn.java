/**
 * An instruction OBJECT called 'Turn'.
 * @V 0.0.1
 * @Author 35769
 */

public class Turn extends InstructionParent{
	
	private int direction;
	private int st;
	
	
	/**The constructor for the 'Turn' Instruction
     * @Param int direction - The direction to turn
     * @Param int st - The state to go to after the instruction
     */
	public Turn(int inDirection, int inSt) {
		super(6);
		this.direction = inDirection;
		this.st = inSt;
	}

	/**A method to return the direction (as an int) that this instruction holds
     * @Return int direction - returns the direction as an intiger
     */
	public int getDirection() {
		return direction;
	}

	/**A method to return the state of the current instruction
     * @Return int st - a specific instruction state number.
     */
	public int getSt() {
		return st;
	}
	
}
