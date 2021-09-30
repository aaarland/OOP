
public class HomeworkTrap implements Boardable {

	
	@Override
	/**
	 * isVisible is used to change the cell, only returns false for HomeworkTrap
	 * 
	 */
	public boolean isVisible() {
		return false;
	}

	/**
	 * checks if a Boardable element has landed on the HomeworkTrap
	 * sets delay of a player to 5000 ms
	 * returns false if there is another homework trap so they don't stack
	 * returns true in all other cases
	 */
	@Override
	public boolean share(Boardable elem) {
		
		if(elem instanceof Player) {
			((Player) elem).setDelay(5000);
		}else if(elem instanceof HomeworkTrap) {
			return false;
		}
		return true;
	}
	
	/**
	 * this toString method returns a space, is invisible on the board
	 */
	public String toString() {
		return " ";
	}

}
