import java.io.IOException;
import java.util.Scanner;

public class Player extends Mobile {

	/**
	 * the input instance variable, used to take user input
	 */
	private Scanner input = new Scanner(System.in);
	/**
	 * delayTime is the amount of ms secound the player is going to be delayed
	 */
	private long delayTime = 0;
	/**
	 * uses super to create a board instance variable
	 * @param board is the board we are currently playing on
	 */
	public Player(Board board) {
		super(board);
	}
	@Override
	/**
	 * Nothing can share a cell with the player so it returns false
	 */
	public boolean share(Boardable elem) {
		return false;
	}

	@Override
	/**
	 * run is the method that is triggered when the thread is invoked.
	 * runs the functions delay(), board.printBoard(), move().
	 * prints the final board and a message that the user won.
	 */
	public void run() {
		this.board.printBoard();
		while(!this.board.beenHugged()) {
			this.delay();
			this.move();
			this.board.printBoard();
		}
		this.board.printBoard();
		System.out.println("Congratulations! You hugged jarvis");

	}

	@Override
	/**
	 * move() takes a user input 
	 * q, w, e, a, s, d, z, x, c
	 * it then sends itself to the board to be moved
	 */
	protected void move() {
		try {
				switch(this.input.nextLine()) {
				case "q":
					this.board.move(Direction.UP_LEFT, this);
					break;
				case "w":
					this.board.move(Direction.UP, this);
					break;
				case "e":
					this.board.move(Direction.UP_RIGHT, this);
					break;
				case "a":
					this.board.move(Direction.LEFT, this);
					break;
				case "d":
					this.board.move(Direction.RIGHT, this);
					break;
				case "z":
					this.board.move(Direction.DOWN_LEFT, this);
					break;
				case "x":
					this.board.move(Direction.DOWN, this);
					break;
				case "c":
					this.board.move(Direction.DOWN_RIGHT, this);
					break;
				case "s":
					System.out.println("Resting");
					break;
				default:
			
				}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * The Player object always returns true.
	 * @return true
	 */
	public boolean isVisible() {
		return true;
	}
	
	/**
	 * The Player is represented by an asterisk
	 * @return '*'
	 */
	public String toString() {
		return "*";
	}
	/**
	 * Sets the time the user gets delayed.
	 * @param time a <code>long</code> variable
	 */
	public void setDelay(long time) {
		this.delayTime = time;
	}
	/**
	 * Checks if this.delayTime is more than 0, then sleeps the Player for this.delayTime amout of ms.
	 * It then clears System.in so the Player input isn't looped through after the delay
	 * It then resets the delayTime
	 */
	private void delay() {
		try {
			
			if(this.delayTime > 0) {
				//this.wait(this.delayTime);
				this.board.printBoard();
				System.out.println("Do your homework! Wait 5 secounds" );
				Thread.sleep(this.delayTime);
				System.in.read(new byte[System.in.available()]); //Clears the system.in buffer.
			}
		
		}catch(InterruptedException ie) {
			ie.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.delayTime = 0;
	}
	
	/**
	 * Close the scanner
	 */
	public void finalize() {
		input.close();
	}

}
