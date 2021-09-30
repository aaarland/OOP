/**
 * Card is the object that create an object from all the information
 * passed through from Inventory.
 * @author Adrian Aarland
 *
 */
public class Card {

	/**
	 * sicID is the Stock Index Card ID and is unique to every card.
	 */
	private int sicID;
	/**
	 * title is the title of the book.
	 */
	private String title;
	/**
	 * author is the author of the book.
	 */
	private String author;
	/**
	 * price is the price of the book, created as a float to store decimals.
	 */
	private float price;
	/**
	 * quantity is the amount of books.
	 */
	private int quantity;

	public Card(int sicID, float price, int quantity, String title, String author) {// constructor

		// Set all the parameters to the instance variables.
		this.sicID = sicID;
		this.title = title;
		this.author = author;
		this.price = price;
		this.quantity = quantity;

	}

	/**
	 * returns the sicID number of the card.
	 * 
	 * @return sicID number of the card.
	 */
	public int getSicID() {
		return sicID;
	}

	/**
	 * returns the price of the book
	 * 
	 * @return the price of the book
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * sets the price of the book
	 * 
	 * @param price takes a float and assigns it to this.price
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * returns the quantity of the book.
	 * 
	 * @return an int quantity of the books.
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * sets the quantity of the book.
	 * 
	 * @param quantity takes an int and assigns it to this.quantity.
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * gets the title of the book
	 * 
	 * @return a string that contains the title of the book.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * gets the author of the book.
	 * 
	 * @return a string that contains the author of the book.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * returns a string of the Stock Index Card, Title, Author, Price, and
	 * Quantitiy.
	 */
	public String toString() {
		return "\nStock Index Card " + this.sicID + "\n\tTitle: " + this.title + "\n\tAuthor: " + this.author
				+ "\n\tPrice: $" + String.format("%.2f", this.price) + "\n\tQuantity: " + this.quantity;
	}

}
