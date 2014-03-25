/**
 * A Token that is a condition
 * @V 0.0.1
 * @Author 35769
 */

public class T_condition extends TokenParent{
	
	private String condition;
	private int ID;
	
	/**The constructor for the 'Condition' Token
     * @Param String inCond - The conditional statement
     * @Param int ID - The identification number for the token
     */
	public T_condition(String inCond, int inID){
		super(inID);
		this.ID=inID;
		this.condition = inCond;
	}
	
	/**A method to return the 'condition' of this token in correct syntax
     * @Return String - a specific condition in brain syntactic form.
     */
	public String toString(){
		return "T_condition: "+this.condition;
	}

	/**A method to return the plain string condition of this token
     * @Return String condition - a specific string condition(pass/fail).
     */
	public String getCondition() {
		return condition;
	}
	
	/**A method to return the identification number for this token
     * @Return int ID - The unique identification number
     */
	public int getID() {
		return ID;
	}
}
