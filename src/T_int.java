/**
 * A Token that is a simple integer.
 * @V 0.0.1
 * @Author 35769
 */

public class T_int extends TokenParent{
	
	private int value;
	
	/**The constructor for the 'Integer' Token
     * @Param String inVal - The Integer as a string
     */
	public T_int(String inVal){
		super(Integer.parseInt(inVal));
		this.value = Integer.parseInt(inVal);
	}
	
	/**A method to return the value of this token in correct syntax
     * @Return String - a specific direction in syntactic form.
     */
	public String toString(){
		return "T_int: "+this.value;
	}

	/**A method to return the identification number for this token
     * @Return int value - The value of this integer token
     */
	public int getID() {
		return value;
	}
}
