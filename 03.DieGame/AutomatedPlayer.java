import java.util.Arrays;
import java.util.Random;

/**
 * <b>The automatedPlayer returns random decision for the Jarvis game, it
 * extends the Player class.</b> It functions like a normal player, however here
 * the computer makes all the decisions, which are psudo random.
 * 
 * @author adrianaarland
 *
 */

public class AutomatedPlayer extends Player {

	/**
	 * Random is declared as an instance variable to be accessed by the entire
	 * class.
	 */
	private final Random rand = new Random();

	/**
	 * same constructor as parent
	 * 
	 * @param name The name of the player, any string
	 * @param turn the turn number of the player.
	 */
	public AutomatedPlayer(final String name) {
		super(name);
	}

	/**
	 * Lets the gamemaster know if the AI wants to guess.
	 * 
	 * @return true when a random generated number between 0-9 is 0
	 */
	public boolean wantsToGuess() {
		if (this.rand.nextInt(8) == 4) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * randomly chooses how many dices to reroll, adds the index to the array for
	 * which die to reroll
	 * @return an array of random numbers.
	 */
	public int[] reroll(int length) {

		final int[] dice = new int[length];
		int[] temp = new int[length];
		for (int index = 0; index < length; index++) { //goes through the length of dices arraylist
			dice[index] = this.rand.nextInt(length)+1; //adds a random number between length and 1
		}
		
		Arrays.sort(dice); //sorts the numbers
		int j = 0;
		for(int i = 0; i < dice.length-1; i++) { 
			if(dice[i] != dice[i+1]) { 
				temp[j] = dice[i];
				j=j+1;
			}
		}
		temp[j] = dice[dice.length-1];
		
		return temp;
	}
	
	/**
	 * @return an array with a random number between 1 and 9 at index 0
	 */
	
	public int[] guess() {
		int[] i = new int[2];
		i[0] = this.rand.nextInt(9)+1; //maximum die is 9
		do {
			i[1] = this.rand.nextInt(9)+1;
		}while(i[0] == i[1]);
		return i;
	}

}
