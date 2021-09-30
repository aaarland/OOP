import java.util.ArrayList;

public class NonRepeatingDie extends Die {

	/**
	 * Keeps track of all the sides that have been rolled
	 */
	private static ArrayList<Integer> oldDice = new ArrayList<>();

	/**
	 * Same constructor as parent
	 * 
	 * @param id 
	 * @param numSides
	 */
	public NonRepeatingDie(final int id, final int numSides) {
		super(id, numSides);

	}

	/**
	 * roll a die on a side that have not been rolled before. Checks if the
	 * ArrayList is not 0 and if the size of the arrayList is less than the sides of
	 * the die re-rolls the die until it gets a number that oldDice does not
	 * contain.
	 * 
	 * Re-creates the Arraylist and adds the roll to it.
	 * 
	 * @return the roll of a side that have not been.
	 */
	public int roll() {

		Integer roll; 
		roll = super.roll();

		if (oldDice.size() != 0 && oldDice.size() < super.getNumberOfSides()) { 
			while (oldDice.contains(roll)) { //check if the side have been rolled
				roll = super.roll(); //re-roll until it is a new side
			}
			oldDice.add(roll);
			return roll; //give the roll back
		} else {

			oldDice = new ArrayList<>(); //re-create the arraylist
			oldDice.add(roll); //add the roll for tracking
			return roll; //give the roll back
		}
	}
}
