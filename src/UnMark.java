/**
 * An instruction OBJECT called 'UnMark'.
 * @V 0.0.1
 * @Author 35769
 */

public class UnMark extends InstructionParent{
	
	private int markerID;
	private int st;

	/**The constructor for the 'UnMark' Instruction
     * @Param int inMarker - The id number of the marker (six markers)
     * @Param int inSt - The state to go to after the instruction
     */
	public UnMark(int inMarker, int inSt){
		super(3);
		this.st = inSt;
		this.markerID = inMarker;
	}

	/**A method to return the marker ID of this instruction
     * @Return int markerID - the marker id of this instruction
     */
	public int getMarkerID() {
		return markerID;
	}

	/**A method to return the state of the current instruction
     * @Return int st - a specific instruction state number.
     */
	public int getSt() {
		return st;
	}
}
