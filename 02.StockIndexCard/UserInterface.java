import java.util.Scanner;
/**
 * UserInterface interacts with the user and takes input from them and
 * sends it on to the Inventory class for storage.
 * 
 * @author Adrian Aarland
 */
public class UserInterface {
	/**
	 * The scanner, it is used to take the users input in this class.
	 */
	private Scanner input = new Scanner(System.in);
	/**
	 * The inventory, it is the object where all of our stock index cards are
	 * stored.
	 */
	private Inventory inventory = new Inventory();

	/**
	 * startProgram() Initiates the program, calls menu and user input for it. The
	 * while loop runs until initiateMenu returns true, only returns true on "10.
	 * Quit"
	 */
	public void startProgram() {
		do { // calls the print menu until quit is selected
			printMenu();
		} while (!initiateMenu(getInt()));
	}

	/**
	 * printMenu does exactly what you'd think, prints the menu The menu contains 10
	 * different options which are driven by initiateMenu(int)
	 */
	public void printMenu() {
		System.out.println("Please enter a number from the following options");
		System.out.println("1. Add new Stock Index Card");
		System.out.println("2. Remove Stock Index Card by SIC-ID");
		System.out.println("3. Increase Stock by SIC-ID");
		System.out.println("4. Decrease Stock by SIC-ID");
		System.out.println("5. Display Stock Index Card by SIC-ID");
		System.out.println("6. Display Stock Index Card by Author");
		System.out.println("7. Display Stock Index Card by Title");
		System.out.println("8. Display All Stock Index Cards");
		System.out.println("9. Change price by SIC-ID");
		System.out.println("10. Quit");
	}

	/**
	 * initiateMenu() is the driver for the switch statement that controls the menu.
	 * 
	 * @param userInput takes the output from getInput() and checks which statement
	 *                  it should run between 1-10
	 * @return false on every case except 10.
	 */
	public boolean initiateMenu(int userInput) {
		switch (userInput) {
		case 1:
			// Add new Stock Index Card function
			addNewSIC();
			System.out.println("\n");
			break;
		case 2: // Remove Stock Index Card by SIC-ID
			removeSicID();
			System.out.println("\n");
			break;
		case 3: // Increase Stock by SIC-ID
			increaseSicID();
			System.out.println("\n");
			break;
		case 4: // Decrease Stock by SIC-ID
			decreaseSicID();
			System.out.println("\n");
			break;
		case 5: // Display Stock Index Card by SIC-ID
			displaySicID();
			System.out.println("\n");
			break;
		case 6: // Display Stock Index Card by Author
			displayAuthor();
			System.out.println("\n");
			break;
		case 7: // Display Stock Index Card by Title
			displayTitle();
			System.out.println("\n");
			break;
		case 8: // Display all stock index cards
			displayAll();
			System.out.println("\n");
			break;
		case 9: // Change price by SIC-ID
			changePrice();
			System.out.println("\n");
			break;
		case 10: // quit
			System.out.println("\n");
			return true; // returns true if 10. quit is selected.

		default:
			System.out.println("No option was selected, please try again...\n");
			return false; // returns false if no option was selected
		}
		return false; // returns false after switch case has been broken out of.
	}

	/**
	 * addNewSIC takes input from the user and does the error checking before
	 * sending the information to inventory.addNewSIC
	 */
	public void addNewSIC() {
		int sicid;
		String title;
		String author;
		float price;
		int quantity;

		System.out.println("Making a new card: ");

		do {
			System.out.println("\tPlease enter the SIC-ID: ");
			sicid = getInt();
			if (inventory.containsSicID(sicid)) { // checks if the sic id exists
				System.out.println("ERROR: SIC ID already in deck");
			} else if (sicid < 0) { // check if the sic id is less than 0
				System.out.println("ERROR: SIC ID can't be less than 0");
			}
		} while ((inventory.containsSicID(sicid)) || sicid < 0);

		System.out.println("\tPlease enter the title of the book: \n");
		title = getLine();
		System.out.println("\tPlease enter the author of the book: \n");
		author = input.nextLine();

		do {
			System.out.println("\tPlease enter the price of the book: ");
			price = input.nextFloat();
		} while (price < 0.0);

		do {
			System.out.println("\tPlease enter the number of the books in inventory: ");
			quantity = getInt();
		} while (quantity < 1); // checks that the quantity is at least 1
		if (inventory.addNewSIC(sicid, price, quantity, title, author)) {// send the user information to inventory and
			// returns true on success
			System.out.println("Succsess! Card has been added");
		} else {
			System.out.println("ERROR: Something went wrong...");
		}
	}

	/**
	 * removeSicID() removes a card based on the SIC-ID. Gives an error message if
	 * the removeSicID function in inventory returns false
	 */
	public void removeSicID() {
		int sicID;
		System.out.println("Enter the SIC-ID you would like to remove: ");
		do {
			sicID = getInt();
			if (sicID < 0) { // check that the sicID is more than 0
				System.out.println("ERROR: SIC-ID can't be less than 0");
			}
		} while (sicID < 0); // loop until sicID is more than 0

		if (inventory.removeSicID(sicID)) { // removes the card and returns true on success
			System.out.println("Successfully removed Stock Index Card " + sicID);
		} else {
			System.out.println("ERROR: Something went wrong...");
		}
	}

	/**
	 * increaseSicID() ask for an increased amount in quantity based on the SIC-ID.
	 * <p>
	 * Gives an error message if inventory.increaseSicID returns false. Returns
	 * false if there are no cards that match the sic-id or if the quantity is less
	 * than or equal to 0
	 * </p>
	 */
	public void increaseSicID() {
		int sicID;
		int quantity;

		System.out.println("Increasing stock:");
		System.out.println("\tPlease enter SIC-ID: ");
		sicID = getInt();
		System.out.println("\tPlease enter the amount you want to increase the stock by: ");
		quantity = getInt();
		if (inventory.increaseSicID(sicID, quantity)) { // increases the quantity and returns true on success
			System.out.println("Successfully increased by " + quantity);
		} else {
			System.out.println("ERROR: Something went wrong..");
		}
	}

	/**
	 * decreaseSicID() ask for a decreased amount in quantity based on the SIC-ID
	 * <p>
	 * Gives an error message if inventory.decreaseSicID() returns false. Returns
	 * false if there are no cards that match the sic-id, or the quantity is less
	 * than or equal to 0, or if the quantity is 0 or less after the decreased
	 * amount.
	 * </p>
	 */
	public void decreaseSicID() {
		int sicID;
		int quantity;

		System.out.println("Decreasing stock:");
		System.out.println("\tPlease enter SIC-ID: ");
		sicID = getInt();
		System.out.println("\tPlease enter the amount you want to decrease the stock by: ");
		quantity = getInt();
		if (inventory.decreaseSicID(sicID, quantity)) { // decreases the quantity and returns true on success
			System.out.println("Successfully decreased by " + quantity);
		} else {
			System.out.println("ERROR: Something went wrong...");
		}
	}

	/**
	 * changePrice() ask for a new price from the user and changes it based on the
	 * user.
	 * <p>
	 * Gives an error message if inventory.changePrice() returns false. Returns true
	 * if the new price have been set. Returns false if the card is not found or the
	 * price is less than 0
	 */
	public void changePrice() {
		int sicID;
		float price;

		System.out.println("Changing price: ");
		System.out.println("\t Please enter SIC-ID");
		sicID = getInt();
		System.out.println("\t Please enter the price you want to set");
		price = getInt();
		if (inventory.changePrice(sicID, price)) { // changes the price of the book and returns true on success.
			System.out.println("Successfully changed the price of Stock Index card " + sicID + " to " + "$"
					+ String.format("%.2f", price));
		} else {
			System.out.println("ERROR: Something went wrong...");
		}
	}

	/**
	 * displaySicID() asks the user for a SIC-ID and display the card based on the
	 * SIC-ID.
	 */
	public void displaySicID() {
		System.out.println("Displaying book by SIC-ID: ");
		System.out.println("\tPlease enter the SIC-ID");
		System.out.println(inventory.displaySicID(getInt())); //prints the card based on the sicID
	}

	/**
	 * displayAuthor() asks the user for an author and display the card based on the
	 * author. The search uses the contains() method and does not need to be
	 * complete.
	 */
	public void displayAuthor() {

		System.out.println("Displaying book by author ");
		System.out.println("\tPlease enter the author: ");
		System.out.println(inventory.displayAuthor(getLine())); //prints card based on user input matches any authors
	}

	/**
	 * displayTitle() asks the user for a title and display the card based on the
	 * title. The search uses the contains() method and does not need to be
	 * complete.
	 */
	public void displayTitle() {
		System.out.println("Displaying book by title ");
		System.out.println("\tPlease enter the title: ");
		System.out.println(inventory.displayTitle(getLine())); //prints card based on user input matches any titles
		
	}

	/**
	 * displayAll() displays all the cards.
	 */
	public void displayAll() {
		System.out.println("Displaying all books");
		System.out.println(inventory.toString()); //prints all the books in inventory
	}

	/**
	 * Gets the next integer the user inputs, made for the menu.
	 * 
	 * @return The choice of the user, any int
	 */
	public int getInt() {
		return input.nextInt();
	}

	/**
	 * Gets the next String from the user, returns enter with first call
	 * 
	 * @return the user input.
	 */
	public String getLine() {
		input.nextLine();
		return input.nextLine();
	}

	/**
	 * finalize closes the scanner.
	 */
	public void finalize() {
		input.close();
	}

}
