/**
 * An instruction OBJECT called 'Sense'.
 * @V 0.0.1
 * @Author 35769
 */

public class Sense extends InstructionParent {
	
	private int direction;
	private int st1;
	private int st2;
	private int cond;
	private int markerID;
	
	/**The constructor for the 'Sense' Instruction
     * @Param int inDirection - The direction in which the sense is to be performed
     * @Param int inSt1 - The state to go to after the instruction
     * @Param int inSt2 - The state to go to after the instruction
     * @Param int inCond - What is the ant looking for
     * @Param int inMarkerID - The identification marker to look for (if any)
     */
	public Sense(int inDirection, int inSt1, int inSt2, int inCond, int inMarkerID) {
		super(1);
		
		this.direction = inDirection;
		this.st1 = inSt1;
		this.st2 = inSt2;
		this.cond = inCond;
		this.markerID = inMarkerID;
	}

	/**A method to return the direction of the current instruction
     * @Return int direction - a specific direction (as an integer).
     */
	public int getDirection() {
		return direction;
	}

	/**A method to return what the current instruction is looking for
     * @Return int cond - a specific condition (what is being searched for).
     */
	public int getCond() {
		return cond;
	}

	/**A method to return the marker ID the current instruction is looking for (if at all)
     * @Return int markerID - a specific ant brain marker (1-6).
     */
	public int getMarkerID() {
		return markerID;
	}
	
	/**A method to return the state of the current instruction
     * @Return int st1 - a specific pass instruction state number.
     */
	public int getSt1() {
		return st1;
	}

	/**A method to return the state of the current instruction
     * @Return int st2 - a specific fail instruction state number.
     */
	public int getSt2() {
		return st2;
	}
}
