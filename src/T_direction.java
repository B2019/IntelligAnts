/**
 * A Token that is a direction Object
 * @V 0.0.1
 * @Author 35769
 */

public class T_direction extends TokenParent{
	private String direction;
	private int ID;
	
	/**The constructor for the 'Direction' Token
     * @Param String inDir - The Direction as a string
     * @Param int inID - The identification number for the token
     */
	public T_direction(String inDir, int inID){
		super(inID);
		this.ID = inID;
		this.direction = inDir;
	}
	
	/**A method to return the direction of this token in correct syntax
     * @Return String - a specific direction in brain syntactic form.
     */
	public String toString(){
		return "T_direction: "+this.direction;
	}

	/**A method to return the 'plain' direction of this token
     * @Return String instruction - a specific string direction.
     */
	public String getDirection() {
		return direction;
	}
	
	/**A method to return the identification number for this token
     * @Return int ID - The unique identification number
     */
	public int getID() {
		return ID;
	}
}
