import java.util.Random;

public class Jarvis extends Mobile{

	/**
	 * Random number generator.
	 */
	private Random rand;
	/**
	 * the counter is used to count Jarvis' moves
	 */
	private int counter = 0;
	
	
	public Jarvis(Board board) {
		super(board);
		rand = new Random();
	}
	 /**
	  * layTrap() gets this instance current position on the board, it then tries to place a HomeworkTrap in all directions until it manages to do so.
	  * 
	  */
	private void layTrap() {
		int num = rand.nextInt(8);
		int counter = 0;
		int[] choice = new int[2];
		do {
			choice = choose(num, board.getRow(this), board.getColumn(this));
			num++;
			if(num >= 8 && num > counter) {
				num = 0;
			}
			counter++;
		}while(!board.placeElement(new HomeworkTrap(), choice[0], choice[1]) && counter < 8); //loops through until the trap is placed, choice[0] is row, choice[1] is column
	}
	/**
	 * Changes the current row and column depending on the input.
	 * @param num the direction relative to the board row and col should change
	 * @param row the current row
	 * @param col the current column
	 * @return an <code>int</code> array [row, col]
	 */
	private int[] choose(int num, int row, int col) {
		
		switch(num) {
		case 0: //UP:
			row -= 1;
			break;
		case 1: // DOWN:
			row += 1;
			break;
		case 2: // LEFT:
			col -= 1;
			break;
		case 3: // RIGHT:
			col += 1;
			break;
		case 4: // UP_RIGHT:
			row -= 1;
			col += 1;
			break;
		case 5: // UP_LEFT:
			row -= 1;
			col -= 1;
			break;
		case 6: // DOWN_RIGHT:
			row += 1;
			col += 1;
			break;
		case 7: // DOWN_LEFT:
			row += 1;
			col -= 1;
			break;
		default:
			
		}
		return new int[] {row, col};
	}
	
	/**
	 * Place an ExtraCreditBomb on the map.
	 * Tries all directions until the bomb is placed
	 */
	private void layExtraCreditBomb() {
		int num = rand.nextInt(8);
		int counter = 0;
		int[] choice = new int[2];
		do {
			choice = choose(num, board.getRow(this), board.getColumn(this));
			num++;
			if(num >= 8 && num > counter) {
				num = 0;
			}
			counter++;
		}while(!board.placeElement(new ExtraCreditBomb(this.board), choice[0], choice[1]) && counter < 8); //loops through until the trap is placed
	}
	
	/**
	 * board.setHugged(<code>true</code>) is called if the object passed in is of type Player
	 * @param elem Any Boardable object
	 * @return true only when elem is of type Player
	 */
	public boolean share(Boardable elem) {
		if(elem instanceof Player) {
			board.setHugged(true);
			return true;
		}
		return false;
	}
	
	
	@Override
	public void run() {
		while(!board.beenHugged()) {
			this.move();
			//this.board.printBoard();
		}
	}

	
	/**
	 * Randomly pikcs a direction and tries to move Jarvis, repeats until Jarvis has moved.
	 * Lays a HomeworkTrap every 6th move and lays an ExtraCreditBomb randomly approximatly 1 in 10 moves.
	 * Then sleeps for 500ms.
	 */
	protected void move() {
		boolean run = false;
		while(!run) {
			switch (rand.nextInt(8)) {
			case 0:
				run = board.move(Direction.UP, this);
				break;
			case 1:
				run = board.move(Direction.DOWN, this);
				break;
			case 2:
				run = board.move(Direction.LEFT, this);
				break;
			case 3:
				run = board.move(Direction.RIGHT, this);
				break;
			case 4:
				run = board.move(Direction.UP_RIGHT, this);
				break;
			case 5:
				run = board.move(Direction.UP_LEFT, this);
				break;
			case 6:
				run = board.move(Direction.DOWN_RIGHT, this);
				break;
			case 7:
				run = board.move(Direction.DOWN_LEFT, this);
				break;
			default:
				
			}
		}
		
		
		if(counter%6 == 0) {
			this.layTrap();	
		}
		
		if(rand.nextInt(10) == 5) {
			this.layExtraCreditBomb();
		}
		
		counter ++;
		
		try {
			Thread.sleep(500); //waits 500ms between moves
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	/**
	 * Jarvis is always not visible.
	 * @return <code>false</code>
	 */
	@Override
	public boolean isVisible() {
		return false;
	}
	/**
	 * Jarvis is represented by a questionmark
	 * @return ?
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "?";
	}
	

}
