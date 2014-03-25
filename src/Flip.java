/**
 * An instruction OBJECT called 'Flip'.
 * @V 0.0.1
 * @Author 35769
 */

public class Flip extends InstructionParent{
	private int p;
	private int st1;
	private int st2;
	
	/**The constructor for the 'Flip' Instruction
     * @Param int P - The state of the flip
     * @Param int inSt1 - The pass state
     * @Param int inSt2 - The fail state
     */
	public Flip(int inP, int inSt1, int inSt2) {
		super(8);
		this.p = inP;
		this.st1 = inSt1;
		this.st2 = inSt2;
	}

	/**A method to return the state of the current instruction
     * @Return int p - a specific instruction state number.
     */
	public int getP() {
		return p;
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
