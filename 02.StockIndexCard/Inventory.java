import java.util.ArrayList;

/**
 * Inventory class does the calculations with the user inputs
 * it also stores all the cards it creates in an ArrayList
 * 
 * @author Adrian Aarland
 * 
 */
public class Inventory {

	/**
	 * The ArrayList that keeps all of our stock index cards it is private so it can
	 * only be accessed inside this class
	 */
	private ArrayList<Card> inventory = new ArrayList<>();

	/**
	 * addNewSIC creates a new card and adds it to the inventory ArrayList
	 * 
	 * @param sicID    The unique identifier for the Stock Index Cards
	 * @param price    the price per book
	 * @param quantity the amount of books in inventory
	 * @param title    the title of the books
	 * @param author   the author of the book
	 * @return <b>False</b> if the error checks falls through checks if the sicID
	 *         exists, if the sicID is less than 0, if the price is less then 0, or
	 *         if the quantity is less then 0.3<br/>
	 *         <b>True</b> a new card will be added to the inventory.
	 * 
	 */
	public boolean addNewSIC(int sicID, float price, int quantity, String title, String author) {

		if (containsSicID(sicID) || sicID < 0 || price < 0 || quantity < 1) {
			return false; // return false if it fails to add a card.
		} else {
			Card sic = new Card(sicID, price, quantity, title, author); // Stock Index Card is created
			inventory.add(sic); // Card sic is added to inventory
			return true; // returns true to confirm the card is added
		}
	}

	/**
	 * removeSicID takes the sicID and checks if it exist in the array list. If it
	 * does it removes it.
	 * 
	 * @param sicID unique identifier for Stock Index Card
	 * @return <b>False</b> when the sicID is not found in the inventory.
	 *         <b>True</b> if the sicID is found it will remove the card.
	 */
	public boolean removeSicID(int sicID) {

		if (indexOfSicID(sicID) == -1) {
			return false; // returns false when no card is found
		} else {
			inventory.remove(indexOfSicID(sicID)); // card is removed
			return true;// returns true after card is removed
		}
	}

	/**
	 * increaseSicID increases the quantity of sicID by <i>quantity</i> amount.
	 * 
	 * @param sicID    unique identifier for Stock Index Card
	 * @param quantity the amount of books to be added to inventory card
	 * @return <b>True</b> if the quantity successfully increased. <b>False</b> if
	 *         it didn't
	 */
	public boolean increaseSicID(int sicID, int quantity) {
		for (Card card : inventory) {
			if (card.getSicID() == sicID && quantity > 0) { //checks if the sicID matches and the quantity is more than 0
				card.setQuantity(card.getQuantity() + quantity); //sets the new quantity to current quantity plus quantity
				return true;
			}
		}
		return false;
	}

	/**
	 * decreaseSicID decreases the quantity of sicID by <i>quantity</i> amount.
	 * 
	 * @param sicID    unique identifier for Stock Index Card
	 * @param quantity the amount of books to be removed from inventory card
	 * @return
	 */
	public boolean decreaseSicID(int sicID, int quantity) {
		for (Card card : inventory) {
			if (card.getSicID() == sicID && quantity > 0 && card.getQuantity() - quantity > 0) { //checks if the sicID matches and the quantity is more than 0 and
																								//if the new quantity will be less than 0.
				card.setQuantity(card.getQuantity() - quantity); //sets the new quantity to the current quantity minus quantity
				return true;
			}
		}
		return false;
	}

	/**
	 * changePrice sets a new price for the Stock Index Card based on <b>sicID</b>.
	 * 
	 * @param sicID unique identifier for Stock Index Card
	 * @param price the new price set by the user
	 * @return<b>True</b> when the card in inventory is found and the price have
	 *                    been changed<br>
	 *                    <b>False</b> if it fails the check for index number and
	 *                    price is lower than 0.0.
	 */
	public boolean changePrice(int sicID, float price) {
		if (indexOfSicID(sicID) == -1 || price < 0.0) {
			return false;
		} else {
			for (Card card : inventory) {
				if (card.getSicID() == sicID) { //check if the cards sicID is sicID 
					card.setPrice(price); //set the new price
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * displaySicID checks for the sicID and then gets a string of the card that is
	 * related to the sicID
	 * 
	 * @param sicID unique identifier for Stock Index Card
	 * @return A string of the card that was found or No card was found
	 */
	public String displaySicID(int sicID) {
		if (indexOfSicID(sicID) == -1) { //check if there is a card with that sicID
			return "No card was found...";
		} else {

			return inventory.get(indexOfSicID(sicID)).toString(); //returns the card with the right sicID
		}
	}

	/**
	 * displayAuthor takes a string and checks if the card contains that author,
	 * works with partial words
	 * 
	 * @param author the string the user sends in
	 * @return a String of all the cards that contained the keyword in author.
	 * 
	 */
	public String displayAuthor(String author) {
		String temp = "\n";
		for (Card card : inventory) {
			if (card.getAuthor().contains(author)) { //check if the card contains the string author
				temp = temp + card.toString() + "\n"; //Concatenate the strings for every card that matches
			}
		}
		if (temp == "\n") { //if temp is unchanged there was no card.
			return "No card was found...";
		} else {
			return temp;
		}
	}

	/**
	 * displayTitle takes a string and checks if the card contains the title the
	 * user put in, works with partial words.
	 * 
	 * @param title the string the user sends in
	 * @return a string of all the cards that contained the keyword in the title.
	 */
	public String displayTitle(String title) {
		String temp = "\n";
		for (Card card : inventory) {
			if (card.getTitle().contains(title)) { //check if the card contains the string title
				temp = temp + card.toString() + "\n"; //Concatenate the strings for every card that matches
			}
		}
		if (temp == "\n") { //if temp is unchanged there was no card
			return "No card was found...";
		} else {
			return temp;
		}
	}

	/**
	 * toString returns all the cards in inventory. or the inventory is empty if
	 * nothing is there.
	 */
	public String toString() {
		String temp = "\n";
		for (Card card : inventory) {
			temp = temp + card.toString() + "\n"; //adds every card to a string
		}
		if (temp == "\n") { //if temp is unchanged the inventory is empty
			return "The inventory is empty...";
		} else {
			return temp;
		}
	}

	/**
	 * containsSicID checks every card if the sicID is already created. Vital method
	 * as every ID should be unique.
	 * 
	 * @param sicID the sicID that is being tested.
	 * @return <b>True</b>if it finds the sicID and false if it doesn't
	 */
	public boolean containsSicID(int sicID) {
		for (Card card : inventory) {
			if (card.getSicID() == sicID) { //check if the card matches the sicID
				return true; // Returns true when the card and sicID is found.
			}
		}
		return false; // Returns false when card is not found.
	}

	/**
	 * indexOfSicID finds the index of the sicID.
	 * 
	 * @param sicID which sic ID that should be found.
	 * @return the index number or -1 if no index number is found for that sic ID
	 */
	public int indexOfSicID(int sicID) {

		for (int index = 0; index < inventory.size(); index++) {// loops through every object in inventory
			if (inventory.get(index).getSicID() == sicID) { //gets the sicID on a specific index and checks if it is the same as sicID
				return index; 
			}
		}
		return -1;
	}
}
