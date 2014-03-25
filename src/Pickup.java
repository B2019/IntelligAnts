/**
 * An instruction OBJECT called 'Pickup'.
 * @V 0.0.1
 * @Author 35769
 */

public class Pickup extends InstructionParent {
	
	private int st1;
	private int st2;

	/**The constructor for the 'Pickup' Instruction
     * @Param int inSt1 - The pass state number
     * @Param int inSt2 - The fail state number
     */
	public Pickup(int inSt1, int inSt2) {
		super(4);
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
