import java.util.Random;

/**
 * the test driver test different aspects of the program and tries to make it
 * crash currently the program will crash if it is sent the wrong input in the
 * Board constructor and if the wrong input is sent to the placeElement()
 * method. These exceptions are both thrown by the program.
 * 
 * @author adrianaarland
 *
 */
public class TestDriver {

	public static void main(String[] args) {
		drawHeart();
		drawAll();
		randomDraw();
		Board board = new Board(10, 30);
		Boardable pen = new Stylus(board);
		board.printBoard();
		board.placeElement(pen, -1, 30);
		board.placeElement(pen, 0, 0); // cannot place the same pen twice, messes with the hashMap
		Boardable newPen = new Stylus(board); // create a new pen
		board.printBoard();
		board.placeElement(newPen, 5, 8); // add the pen
		board.move(Direction.DOWN, pen); // move one down
		board.move(Direction.UP, newPen); // and the other up
		board.printBoard();
		board.move(Direction.DOWN, newPen);
		board.printBoard();
		board.move(Direction.DOWN, newPen); // newPen is ontop of pen
		board.move(Direction.LEFT, pen); // move the pen to the left when they are on the same
		board.printBoard();
		board.move(Direction.DOWN, newPen);
		board.printBoard();
		board.move(Direction.DOWN, newPen);
		board.printBoard();

	}

	/**
	 * draws the pen over the length and height of the board. Everytime the pen has
	 * been over it goes back on the path below
	 */
	public static void drawAll() {
		Board board = new Board(15, 30);
		Boardable pen = new Stylus(board);
		board.placeElement(pen, 0, 0);

		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 30; j++) {
				board.move(Direction.RIGHT, pen);
				board.printBoard();
			}
			board.move(Direction.DOWN, pen);
			for (int j = 0; j < 30; j++) {
				board.move(Direction.LEFT, pen);
			}
			board.move(Direction.DOWN, pen);
			board.printBoard();
		}

	}

	/**
	 * draws a heart
	 */
	public static void drawHeart() {
		Board board = new Board(15, 30);
		Boardable pen = new Stylus(board);
		board.placeElement(pen, 7, 15);
		board.move(Direction.UP_LEFT, pen);

		board.move(Direction.LEFT, pen);
		board.move(Direction.DOWN_LEFT, pen);
		board.move(Direction.DOWN, pen);
		for (int i = 0; i < 3; i++) {
			board.move(Direction.DOWN_RIGHT, pen);
			board.printBoard();
		}
		for (int i = 0; i < 3; i++) {
			board.move(Direction.UP_RIGHT, pen);
			board.printBoard();
		}
		board.move(Direction.UP, pen);
		board.move(Direction.UP_LEFT, pen);
		board.move(Direction.LEFT, pen);
		board.move(Direction.DOWN_LEFT, pen);
		board.printBoard();
	}

	/**
	 * draws in all random directions of the board
	 */
	public static void randomDraw() {
		Board board = new Board(20, 50);
		Boardable stylus = new Stylus(board);
		Random rand = new Random();
		System.out.println(board.toString());
		board.placeElement(stylus, 9, 24);

		for (int i = 0; i < 1000; i++) {
			switch (rand.nextInt(8)) {
			case 0:
				board.move(Direction.UP, stylus);
				break;
			case 1:
				board.move(Direction.DOWN, stylus);
				break;
			case 2:
				board.move(Direction.LEFT, stylus);
				break;
			case 3:
				board.move(Direction.RIGHT, stylus);
				break;
			case 4:
				board.move(Direction.UP_RIGHT, stylus);
				break;
			case 5:
				board.move(Direction.UP_LEFT, stylus);
				break;
			case 6:
				board.move(Direction.DOWN_RIGHT, stylus);
				break;
			case 7:
				board.move(Direction.DOWN_LEFT, stylus);
				break;
			default:
				System.out.println();
			}
			board.printBoard();
			System.out.println();
		}
	}
}
