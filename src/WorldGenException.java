
public class WorldGenException extends Exception {

	private char c;
	
	public WorldGenException(char c) {
		this.c = c ;
	}
	public WorldGenException() {
	}
	
	public String toString() {
		return "World Generation Exception on character: " + c;
	}
}
