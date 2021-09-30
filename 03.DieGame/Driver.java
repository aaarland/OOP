
/**
 * The driver class initiates the program.
 * @author adrianaarland
 *
 */
public class Driver {

	public static void main(String[] args) {
		
		
		Die repeatingDie = new RepeatingDie(1, 15);
		
		for(int i = 0; i < 100; i++) {
			System.out.println(repeatingDie.roll());
		}
		
		GameMaster gm = new GameMaster();
		gm.playGame();
	}

}
