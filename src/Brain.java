import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Brain {

	private ArrayList<Instruction> instructions; //Need to initalise
	
	public Brain(String fileName) {
		String line = "";
		instructions = new ArrayList<Instruction>();

		//Load file
		BufferedReader file = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream (fileName)));
		try {
			while ((line = file.readLine()) != null) {
<<<<<<< HEAD
				line = line.toLowerCase(); //Make lower case
=======
>>>>>>> FETCH_HEAD
				line = line.split(";")[0]; //Remove comments
				String[] strings = line.split(" ");
				switch (strings[0]) {
					case "move" :
						try {
							int st1 = Integer.parseInt(strings[1]);
							int st2 = Integer.parseInt(strings[2]);
							if (strings.length == 3 && st1 >= 0 && st1 <= 9999 && st2 >= 0 && st2 <= 9999) {
								instructions.add(new Move(st1, st2));
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case "turn" :
						try {
							String lr = strings[1];
							int st = Integer.parseInt(strings[2]);
							if (strings.length == 3 && (lr.equals("left") || lr.equals("right")) && st >= 0 && st <= 9999) {
								instructions.add(new Turn(lr, st));
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case "mark" :
						try {
							int i = Integer.parseInt(strings[1]);
							int st = Integer.parseInt(strings[2]);
							if (strings.length == 3 && i >= 0 && i <= 5 && st >= 0 && st <= 9999) {
								instructions.add(new Mark(i,st));
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case "unmark" :
						try {
							int i = Integer.parseInt(strings[1]);
							int st = Integer.parseInt(strings[2]);
							if (strings.length == 3 && i >= 0 && i <= 5 && st >= 0 && st <= 9999) {
								instructions.add(new Unmark(i,st));
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case "pickup" :
						try {
							int st1 = Integer.parseInt(strings[1]);
							int st2 = Integer.parseInt(strings[2]);
							if (strings.length == 3 && st1 >= 0 && st1 <= 9999 && st2 >= 0 && st2 <= 9999) {
								instructions.add(new PickUp(st1, st2));
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case "drop" :
						try {
							int st = Integer.parseInt(strings[1]);
							if (strings.length == 2 && st >= 0 && st <= 9999) {
								instructions.add(new Drop(st));
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case "sense" :
						try {
							String sensedir = strings[1];
							int st1 = Integer.parseInt(strings[2]);
							int st2 = Integer.parseInt(strings[3]);
							String cond = strings[4];
							if ((sensedir.equals("here") || sensedir.equals("ahead") || sensedir.equals("leftahead") || sensedir.equals("rightahead")) && st1 >= 0 && st1 <= 9999 && st2 >= 0 && st2 <= 9999){
								if((cond.equals("friend") || cond.equals("foe") || cond.equals("friendwithfood") || cond.equals("foewithfood") || cond.equals("food") || cond.equals("rock") || cond.equals("foemarker") ||
								cond.equals("home") || cond.equals("foehome") && strings.length == 5)){
									instructions.add(new Sense(sensedir, st1, st2, cond));
								}
								else if(cond.equals("marker") && strings.length == 6){
									int i = Integer.parseInt(strings[5]);
									if(i >= 0 && i <= 5){
										instructions.add(new Sense(sensedir, st1, st2, cond, i));
									}
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case "flip" :
						try {
							int p = Integer.parseInt(strings[1]);
							int st1 = Integer.parseInt(strings[2]);
							int st2 = Integer.parseInt(strings[3]);
							if (strings.length == 4 && p >= 1 && st1 >= 0 && st1 <= 9999 && st2 >= 0 && st2 <= 9999) {
								instructions.add(new Flip(p,st1,st2));
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					default :
						System.out.println("error");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//printBrain();
	}
	
	public Instruction getInstruction(int i) {
		return instructions.get(i);
	}
	
	public void printBrain() {
		for (int i = 0; i < instructions.size(); i++) {
			switch (instructions.get(i).getClass().getName()) {
				case "Move" :
					System.out.print("Move - ");
					System.out.print(((Move)instructions.get(i)).getSt1() + " - ");
					System.out.print(((Move)instructions.get(i)).getSt2());
					System.out.println();
					break;
				case "Turn" :
					System.out.print("Turn - ");
					System.out.print(((Turn)instructions.get(i)).getLr() + " - ");
					System.out.print(((Turn)instructions.get(i)).getSt());
					System.out.println();
					break;
				case "Mark" :
					System.out.print("Mark - ");
					System.out.print(((Mark)instructions.get(i)).getI() + " - ");
					System.out.print(((Mark)instructions.get(i)).getSt());
					System.out.println();
					break;
				case "Unmark" :
					System.out.print("Unmark - ");
					System.out.print(((Unmark)instructions.get(i)).getI() + " - ");
					System.out.print(((Unmark)instructions.get(i)).getSt());
					System.out.println();
					break;
				case "PickUp" :
					System.out.print("PickUp - ");
					System.out.print(((PickUp)instructions.get(i)).getSt1() + " - ");
					System.out.print(((PickUp)instructions.get(i)).getSt2());
					System.out.println();
					break;
				case "Drop" :
					System.out.print("Drop - ");
					System.out.print(((Drop)instructions.get(i)).getSt());
					System.out.println();
					break;
				case "Sense" :
					System.out.print("Sense - ");
					System.out.print(((Sense)instructions.get(i)).getSensedir() + " - ");
					System.out.print(((Sense)instructions.get(i)).getSt1() + " - ");
					System.out.print(((Sense)instructions.get(i)).getSt2() + " - ");
					System.out.print(((Sense)instructions.get(i)).getCond() + " - ");
					System.out.print(((Sense)instructions.get(i)).getI());
					System.out.println();
					break;
				case "Flip" :
					System.out.print("Flip - ");
					System.out.print(((Flip)instructions.get(i)).getP() + " - ");
					System.out.print(((Flip)instructions.get(i)).getSt1() + " - ");
					System.out.print(((Flip)instructions.get(i)).getSt2());
					System.out.println();
					break;
			}
			
			
		}
	}

}

//Make an exception
