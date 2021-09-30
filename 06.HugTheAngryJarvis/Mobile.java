
public abstract class Mobile implements Boardable, Runnable {

	protected Board board;
	
	public Mobile(Board board) {
		this.board = board;
		
	}
	
	/**
	 * Moves the Object around the board, calls board.move(Direction dir, Boardable elem)
	 */
	protected abstract void move();

}
