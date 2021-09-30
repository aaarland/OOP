import java.util.Scanner;

/**
 * Etch a sketch This program asks the user for a height and width. It takes
 * these parameters and creates a Board from it It calls to move the Stylus
 * around the board. This is done with the q,w,e,a,d,z,x,c keys
 * 
 * @author adrianaarland
 *
 */

public class DrawingDriver {
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		int height;
		int width;
		do {
			System.out.println("Enter the height of your board [1-100]");
			height = getNum();
			if (height == -1) {
				System.out.println("Not a valid input!");
			}
		} while (height > 100 || height < 1);

		do { // same as the height loop
			System.out.println("Enter the width of your board [1-100]");
			width = getNum();
			if (width == -1) {
				System.out.println("Not a valid input!");
			}
		} while (width > 100 || width < 1);

		Board board = new Board(height, width);
		Stylus pen = new Stylus(board);
		if (board.placeElement(pen, height / 2, width / 2)) {
			do {
				board.printBoard();
			} while (pen.move());
		}

		System.out.println("Thanks for playing");
	}

	/**
	 * The getNum does all the calculations needed for the height and width request
	 * 
	 * @return -1 if the input is not valid, too long or not a number. Otherwise it
	 *         returns the user input
	 */
	private static int getNum() {
		String line = input.nextLine();
		if (line.toCharArray().length > 4) { // the number shouldn't be more than 100, returns before entering the loop
			return -1;
		}
		for (char c : line.toCharArray()) {
			if (c > '9' || c < '0') { //checks every character in the string if it is a number
				return -1;
			}
		}
		return Integer.parseInt(line);
	}

}
