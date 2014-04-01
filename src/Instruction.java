
public interface Instruction {
}

class Move implements Instruction{
	int st1;
	int st2;

	public Move(int st1, int st2) {
		this.st1 = st1;
		this.st2 = st2;
	}

	public int getSt1() {
		return st1;
	}

	public int getSt2() {
		return st2;
	}
}

class Turn implements Instruction{
	String lr;
	int st;

	public Turn(String lr, int st) {
		super();
		this.lr = lr;
		this.st = st;
	}
	
	public String getLr() {
		return lr;
	}

	public int getSt() {
		return st;
	}
}

class Mark implements Instruction{
	int i;
	int st;
	public Mark(int i, int st) {
		super();
		this.i = i;
		this.st = st;
	}
	public int getI() {
		return i;
	}
	public int getSt() {
		return st;
	}
}

class Unmark implements Instruction{
	int i;
	int st;
	public Unmark(int i, int st) {
		super();
		this.i = i;
		this.st = st;
	}
	public int getI() {
		return i;
	}
	public int getSt() {
		return st;
	}
}

class PickUp implements Instruction{
	int st1;
	int st2;
	public PickUp(int st1, int st2) {
		super();
		this.st1 = st1;
		this.st2 = st2;
	}
	public int getSt1() {
		return st1;
	}
	public int getSt2() {
		return st2;
	}
}

class Drop implements Instruction{
	int st;

	public Drop(int st) {
		super();
		this.st = st;
	}

	public int getSt() {
		return st;
	}
}

class Sense implements Instruction{
	int sensedir;
	int st1;
	int st2;
	String cond;
	int i;
	
	public Sense(int sensedir, int st1, int st2, String cond) {
		super();
		this.sensedir = sensedir;
		this.st1 = st1;
		this.st2 = st2;
		this.cond = cond;
	}
	
	public Sense(int sensedir, int st1, int st2, String cond, int i) {
		super();
		this.sensedir = sensedir;
		this.st1 = st1;
		this.st2 = st2;
		this.cond = cond;
		this.i = i;
	}

	public int getSensedir() {
		return sensedir;
	}

	public int getSt1() {
		return st1;
	}

	public int getSt2() {
		return st2;
	}

	public String getCond() {
		return cond;
	}

	public int getI() {
		return i;
	}
}

class Flip implements Instruction{
	int p;
	int st1;
	int st2;
	
	public Flip(int p, int st1, int st2) {
		super();
		this.p = p;
		this.st1 = st1;
		this.st2 = st2;
	}

	public int getP() {
		return p;
	}

	public int getSt1() {
		return st1;
	}

	public int getSt2() {
		return st2;
	}
}

