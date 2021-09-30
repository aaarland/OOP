import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * Board class creates the board and fills it with Cells, Cell is a nested class in Board.
 * It is also responsible for moving elements of type Boardable around the table.
 * Also it has a print method that prints the current board, with the help of Cell.toString()
 * @author Adrian Aarland
 *
 */
public class Board {
	
	/**
	 * The Cell class is nested in Board, it takes the current 
	 * row and column. These are final so when they are set they cannot be changed.
	 * Each cell holds a list of Boardable elements, in our case a Stylus.
	 * @author Adrian Aarland
	 *
	 */
	public class Cell {
		/**
		 * row is set in the constructor and cannot change
		 */
		private final int row;
		/**
		 * col is set in the constructor and cannot change.
		 */
		private final int col;
		/**
		 * isVisible is used to print ' ', '#' or whichever toString method
		 * the Boardable object has.
		 */
		private boolean isVisible;
		/**
		 * elements is used to hold the different Boardable elements.
		 */
		private List<Boardable> elements;
		
		/**
		 * the constructor builds the cell
		 * @param row the current row number
		 * @param col the current colmn number
		 */
		public Cell(int row, int col) {
			this.row = row;
			this.col = col;
			this.isVisible = false;
			elements = new ArrayList<Boardable>();
		}
		
		/**
		 * sets isVisible to true if the elements isVisible() method
		 * returns true. Adds the element to the list.
		 * @param elem any Boardable element
		 */
		public void addElement(Boardable elem) {
			if(elem.isVisible()) {
				this.isVisible = true; //set isVisible to true, never change back.
			}
			
			if(elem instanceof Player || elem instanceof Jarvis) { //always player on top and Jarvis can never go on the player
				this.elements.add(0, elem);
			}else {
				this.elements.add(elem);
			}
			
		}
		
		
		/**
		 * used to remove an element from the cell
		 * @param elem any Boardable element
		 * @return true if the element was removed from elements, false if it was not.
		 */
		public boolean removeElement(Boardable elem) {
			return this.elements.remove(elem); //returns true if the element was removed, false if the element wasn`t found
		}
		
		/**
		 * the toString method returns " " if the cell isVisible and elements is empty.
		 * returns the elements toString method if it is visible and elements contains any element.
		 * returns "#" in all other cases
		 */
		public String toString() {
			
			if(isVisible && elements.isEmpty()) {
				return " ";
			}else if(isVisible && !elements.isEmpty()) {
				return elements.get(0).toString();
			}else {
				return "#";
			}
			
		}
	} //end of Cell class
	
	/**
	 * A 2-D array of the board
	 * Contains the type Cell.
	 */
	private Cell[][] board;
	/**
	 * the height of the board
	 * used for the amount of rows in the array
	 */
	private int height;
	/**
	 * the width of the board
	 * used for the amount of columns in the array
	 */
	private int width;
	/**
	 * the hashmap keeps track of the element by placeing the
	 * element as a key and updateing the Cell when it moves
	 */
	private HashMap<Boardable, Cell> elementPlace;
	/**
	 * hugged is the variable that keeps the game running, when this is changed to true the game ends.
	 */
	private boolean hugged = false;
	
	/**
	 * 
	 */
	private static Object sync = new Object();
	/**
	 * the constructor builds the board and fills it with empty cells
	 * it throws an IllegalArgumentException when height and width are out of bounds.
	 * 
	 * @param height the amount of rows the board should have
	 * @param width the amount of columns the board should have
	 */
	public Board(int height, int width) {
		if(height <= 0 || height > 100 || width <= 0 || width > 100) {
			throw new IllegalArgumentException("ERROR: Width: "+ width + " or height: " + height +" is out of bounds");
		} //throws an exception when the height or width is out of bounds.
		board = new Cell[height][width];
		elementPlace = new HashMap<Boardable, Cell>();
		for(int i = 0; i<height;i++) {
			for(int j = 0; j<width;j++) {
				board[i][j] = new Cell(i, j); //creates a new Cell with the same cordinates as the board
			}
		}
		this.height = height;
		this.width = width;
	}
	/**
	 * Moves the Boardable element around the board.
	 * @param dir the direction the object wants to take
	 * @param elem the element that is being moved around
	 * @return this.shares(elem, row, col) && this.removeElement(elem) && this.placeElement(elem, row, col) or false if the object tries to move off the board
	 */
	public boolean move(Direction dir, Boardable elem) throws IllegalArgumentException {
		Cell cell = elementPlace.get(elem);
		if(cell == null) {
			throw new IllegalArgumentException(elem + " is not on the board");
		}
		int row = cell.row;
		int col = cell.col;
		switch(dir) {
			case UP:
				row -= 1;
				break;
			case DOWN:
				row += 1;
				break;
			case LEFT:
				col -= 1;
				break;
			case RIGHT:
				col += 1;
				break;
			case UP_RIGHT:
				row -= 1;
				col += 1;
				break;
			case UP_LEFT:
				row -= 1;
				col -= 1;
				break;
			case DOWN_RIGHT:
				row += 1;
				col += 1;
				break;
			case DOWN_LEFT:
				row += 1;
				col -= 1;
				break;
			default:
				//return false;
		}
		
		
		if(row < this.height && col < this.width && row >= 0 && col >= 0) {
			return this.shares(elem, row, col) && this.removeElement(elem) && this.placeElement(elem, row, col);
		}
	
		return false;
	}
	
	
	/**
	 * Tests if the Boardable element shares a place with any element in the hashmap elementPlace</br>
	 * Synchronized so elementPlace can't be modified by removeElement or placeElement while it is checking
	 * @param elem Any Boardable object
	 * @param row used to get a Cell from the board
	 * @param col used to get a Cell from the board
	 * @return false when the element can't share place with the object
	 */
	private boolean shares(Boardable elem, int row, int col) {
		Cell cell = board[row][col];
		synchronized(sync) {
			if(!elementPlace.isEmpty()){ //only go into the loop if the hashmap contains any elements
				for(Boardable key : elementPlace.keySet()){ //check every element on the board.
					if(!elem.equals(key) && elementPlace.get(key).equals(cell)) { //check that the element is not the same object AND if the cells are the same
						if(elem instanceof Player || (elem instanceof ExtraCreditBomb && key instanceof Player == false) ) {
							return key.share(elem) && removeElement(key);
						}
						return key.share(elem);
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * place an element on the board based on the row and column.
	 * Checks if the row and col are inside of bounds.
	 * @param elem the element that is being placed
	 * @param row the row the element is being placed in
	 * @param col the column the element is being placed in
	 * @return false if the element is outside of range, true if it is successfully placed
	 */
	public boolean placeElement(Boardable elem, int row, int col) {
		synchronized(sync) {
			if(row >= this.height || col >= this.width || row < 0 || col < 0 || elementPlace.containsKey(elem)) {//returns false if the element is placed before or out of bounds
				return false;
			}
			Cell cell = board[row][col];
			if(elem instanceof HomeworkTrap && !this.shares(elem, row, col)) {
				return false;
			}
			if(elem instanceof ExtraCreditBomb && !this.shares(elem, row, col)) {
				return false;
			}
			
			cell.addElement(elem); //add the element to the cell
			elementPlace.put(elem, cell); //add the element to the hashmap
			return true;
		}
		
	}
	
	
	
	/**
	 * Removes an element from the board.
	 * The method is synchronized since there should only be one Thread at the time that edits the elements.
	 * @param elem any Boardable element
	 * @return
	 */
	public boolean removeElement(Boardable elem) {
		synchronized(sync) {
			if(elementPlace.containsKey(elem)) {
				Cell cell = elementPlace.get(elem);
				elementPlace.remove(elem);
				return cell.removeElement(elem);
			}
		}
		return false;
	}
	
	/**
	 * Gets the row of the object that is passed in.
	 * @param elem Any Boardable object
	 * @throws IllegalArgumentException if the Cell is null, i.e. when the element is not on the board.s
	 * @return cell.row, this is the cell row that is retrieved from the hashmap.
	 */
	public int getRow(Boardable elem) throws IllegalArgumentException {
		Cell cell = elementPlace.get(elem);
		if(cell == null) {
			throw new IllegalArgumentException("ERROR: the element is not on the board");
		}
		return cell.row;
	}
	
	/**
	 * Gets the column of the object that is passed in.
	 * @param elem Any Boardable object
	 * @throws IllegalArgumentException if the Cell is null, i.e. when the element is not on the board.s
	 * @return cell.col, this is the cell column that is retrieved from the hashmap.
	 */
	public int getColumn(Boardable elem) throws IllegalArgumentException {
		Cell cell = elementPlace.get(elem);
		if(cell == null) {
			throw new IllegalArgumentException("ERROR: the element is not on the board");
		}
		return cell.col;
	}
	
	/**
	 * creates a string that add each row and each column for that row.
	 * it then prints the board.
	 * Is synchronized to keep the board at current position when it's being printed
	 * 
	 */
	public void printBoard() {
		synchronized(sync) {
			String printBoard = "";
			for(int i = 0; i < this.height; i++) {
				for(int j = 0; j < this.width; j++) {
	
					printBoard += this.board[i][j].toString(); //calls the cells toString() method
				}
				printBoard += "\n"; //new line after every row.
			}
			System.out.print(printBoard);
			System.out.println();
		}
	}
	
	/**
	 * Changes this.hugged to true when the Player lands on Jarvis
	 * @param hugged true or false
	 */
	public void setHugged(boolean hugged) {
		this.hugged = hugged;
	}
	/**
	 * returns this.hugged, is false until Player lands on Jarvis
	 * @return this.hugged, true when Jarvis is hugged.
	 */
	public boolean beenHugged() {
		return this.hugged;
	}
	
	
}
