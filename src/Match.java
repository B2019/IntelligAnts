import java.io.IOException;
import java.util.Scanner;


public class Match {
	
	World world;
	int seed;
	int turn; //current turn out of 300000
	int tick; //current tick for the turn. (ant thats taking a go)
	
	String redName;
	String blackName;
	Brain redBrain;
	Brain blackBrain;

	public Match(World world, int seed, String redName, String blackName, Brain redBrain, Brain blackBrain) {
		//Setup match
		this.world = world;
		this.seed = seed;
		this.turn = 0;
		this.tick = 0;
		this.redName = redName;
		this.blackName = blackName;
		this.redBrain = redBrain;
		this.blackBrain = blackBrain;
	}

	public void runMatch() {
		//Gets World to loop through ants and get them to act
	}

	
	public World getWorld() {
		return world;
	}

	public int getSeed() {
		return seed;
	}

	public int getTurn() {
		return turn;
	}

	public int getTick() {
		return tick;
	}

	public String getRedName() {
		return redName;
	}

	public String getBlackName() {
		return blackName;
	}

	public Brain getRedBrain() {
		return redBrain;
	}

	public Brain getBlackBrain() {
		return blackBrain;
	}

	
}
