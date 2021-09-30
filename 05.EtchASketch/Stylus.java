import java.util.Scanner;

public class Stylus implements Boardable {

	/**
	 * the board the stylus is currently on.
	 * 
	 */
	private Board board;
	/**
	 * the Scanner, used to take input from user
	 */
	private Scanner input;
	/**
	 * the constructor sets the board and the Scanner
	 * @param board the board that the stylus is currently on
	 */
	public Stylus(Board board) {
		this.board = board;
		input = new Scanner(System.in);
	}
	
	/**
	 * takes user input and sends a direction to board based on the key
	 * uses this keyword to refer to itself
	 * 
	 * @return true for q, w, e, a, d, z, x, c. false in all other cases.
	 */
	public boolean move() {
		try {
			switch(this.input.nextLine()) {
			case "q":
				return this.board.move(Direction.UP_LEFT, this);
			case "w":
				return this.board.move(Direction.UP, this);
			case "e":
				return this.board.move(Direction.UP_RIGHT, this);
			case "a":
				return this.board.move(Direction.LEFT, this);
			case "d":
				return this.board.move(Direction.RIGHT, this);
			case "z":
				return this.board.move(Direction.DOWN_LEFT, this);
			case "x":
				return this.board.move(Direction.DOWN, this);
			case "c":
				return this.board.move(Direction.DOWN_RIGHT, this);
			default:
					return false;
			}
			
		}catch(Exception e) { //input is supplied by the user
			e.printStackTrace();
		}
		return true;
	}
	
	
	/**
	 * isVisible returns true for the Stylus
	 */
	@Override
	public boolean isVisible() {
		return true;
	}
	
	/**
	 * asterisk represent the Stylus
	 */
	public String toString() {
		return "*";
	}

}
