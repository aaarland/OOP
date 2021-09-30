import java.util.Random;

public class Die {

	/**
	 * numSides is the amount of sides on the current die. Cannot be changed once
	 * set
	 */
	protected final int numSides;
	/**
	 * id is the id of the dice. Cannot change once set.
	 */
	private final int id;
	/**
	 * The random instance variable
	 */
	private final Random rand;

	/**
	 * The constructor for die sets the sides and id of the die.
	 * 
	 * @param id       the unique identifier of the die.
	 * @param numSides the number of sides the current die should have.
	 */
	public Die(final int id, final int numSides) {
		this.id = id;
		this.numSides = numSides;
		rand = new Random();
	}

	/**
	 * 
	 * @return numSides, returns the number of sides the current die has
	 */
	public int getNumberOfSides() {
		return numSides;
	}

	/**
	 * 
	 * @return id The unique identifier for the current die.
	 */

	public int getId() {
		return id;
	}

	/**
	 * 
	 * @return a random number between 1 and number of sides
	 *
	 */

	public int roll() {
		return this.rand.nextInt(this.numSides) + 1;
	}

}
