import java.util.Random;
/**
 * ExtraCredit bomb is made to make it easier for the user to catch jarvis,
 * it opens an area around the bomb either 2x2 or 1x1 when the user lands on it.
 * 
 * @author Adrian Aarland
 *
 */
public class ExtraCreditBomb implements Boardable {

	/**
	 * The board that we are playing on
	 */
	private Board board;
	/**
	 * Random number generator
	 */
	private Random rand;
	/**
	 * changes to true when the area is clearing
	 */
	private boolean isVisible;
	/**
	 * Constructor
	 * @param board
	 */
	public ExtraCreditBomb(Board board) {
		this.board = board;
		rand = new Random();
		this.isVisible = false;
	}
	@Override
	/**
	 * 
	 * @return isVisible returns false, unless the area is being cleared
	 */
	public boolean isVisible() {
			return isVisible;
		
	}
	/**
	 * If the Boardable element is a user the bomb will remove itself from the board and places itself around the old position.
	 * 
	 *@param any Boardable element that is being checked
	 *@return false if elem is HomeworkTrap, true in all other cases
	 */
	@Override
	public boolean share(Boardable elem) {
		if(elem instanceof HomeworkTrap) {
			return false;
		}
		this.isVisible = true;
		if(elem instanceof Player) {
			int col = board.getColumn(this);
			int row = board.getRow(this);
			int rand = this.rand.nextInt(2)+1;
			
			for(int i = 0-rand; i <= rand; i++) {
				for(int j = 0-rand; j <= rand; j++) {
					//Boardable bomb = new ExtraCreditBomb(this.board);
					board.removeElement(this);
					board.placeElement(this, (row+j), (col+i));
				}
			}
			this.isVisible = false;
		}
		return true;
		
	}
	/**
	 * A period represents the ExtraCreditBomb
	 * @return .
	 */
	public String toString() {
		return ".";
	}

}
