/**
 * A Token for turning.
 * @V 0.0.1
 * @Author 35769
 */

public class T_turn extends TokenParent {

	private String turnDir;
	private int ID;
	
	/**The constructor for the 'Turn' Token
     * @Param String direction - The direction to turn
     * @Param int ID - The identification number for the token
     */
	public T_turn(String inDir, int inID){
		super(inID);
		this.turnDir = inDir;		
		this.ID = inID;
	}
	
	/**A method to return the direction of this token in correct syntax
     * @Return String - a specific direction in syntactic form.
     */
	public String toString(){
		return "T_turn: "+this.turnDir;
	}
	
	/**A method to return the direction of this token
     * @Return String turnDir - a specific string direction(left/right).
     */
	public String getTurnDir() {
		return turnDir;
	}
	
	/**A method to return the identification number for this token
     * @Return int ID - The unique identification number
     */
	public int getID() {
		return ID;
	}
}
