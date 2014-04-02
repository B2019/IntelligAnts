
public class Player {

	String name;
	Brain brain;
	int wins;
	int losses;
	int draws;
	
	public Player(String name, Brain brain) {
		this.name = name;
		this.brain = brain;
		this.wins = 0;
		this.losses = 0;
		this.draws = 0;
	}

	public Brain getBrain() {
		return brain;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public int getDraws() {
		return draws;
	}

	public void setDraws(int draws) {
		this.draws = draws;
	}

	public String getName() {
		return name;
	}
}
