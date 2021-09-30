import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Gamemaster is the king of this game, it does all the dice rolling and score
 * keeping. It is also mostly responsible for interacting with the user.
 * 
 * 
 * @author adrianaarland
 *
 */
public class GameMaster {
	/**
	 * Random instance variable
	 */
	private final Random rand = new Random();
	/**
	 * ArrayList that holds type Die, holds all the different dices used in the game
	 */
	private final ArrayList<Die> dices;
	/**
	 * ArrayList that holds type Player, holds the current players
	 */
	private final ArrayList<Player> players;
	/**
	 * ArrayList that holds type Integer, used to keep score for each player.
	 */
	private final ArrayList<Integer> score;
	/**
	 * Scanner instance variable
	 */
	private Scanner input;
	/**
	 * Creates the winner player and sets it to null, changes if anyone wins
	 */
	private Player winner = null;
	/**
	 * Used to keep track of the current user turn
	 */
	private int turn;

	/**
	 * The constructor of GameMaster builds the dice list, player, and score It also
	 * calls createDice() which creates the dices one time.
	 */
	public GameMaster() {
		dices = new ArrayList<Die>();
		players = new ArrayList<Player>();
		score = new ArrayList<Integer>();
		createDices();
	}

	/**
	 * Register a player takes a player object and adds it to a list of players
	 * 
	 * @param player is the player object, takes all types of Player
	 * @return true if player is created
	 */
	public boolean registerPlayer(final Player player) {
		
		if (players.add(player)) {
			return true;// returns true if it manage to add the player.
		}
		return false;

	}

	/**
	 * Initiates the game. This is the method being called from the driver. The
	 * method takes care of user interaction. First it asks for the amount of human
	 * players and their names, after it ask how many of them should be automated.
	 * It then displays how many dies are in play and sends an array of how many
	 * sides each die have to Player. It then displays the turn number for each
	 * player. A for loop for 15 rounds is initiated and rollDice() is called for
	 * every time, and prints all the player score after every player have rolled
	 * the dice. If the winner is not determined after 15 rounds, the score is
	 * tested and the player with the score closest to 500 is assigned the winner.
	 * Finally it ends by announcing the winner or a draw.
	 * 
	 */
	public void playGame() {

		input = new Scanner(System.in);
		String name;
		int index; //index used outside of for loop to continue the order
		int amount; //the input value from the user.

		System.out.println("Welcome to Jarvis Dice!");
		System.out.println("How many human players?");
		amount = input.nextInt(); //stores the amount of human players

		input.nextLine(); //Used after .nextInt() because it will register newLine
		for (index = 0; index < amount; index++) { //goes through the loop for the amount of human players
			System.out.print("\nWhat would like to name player " + (index + 1) + "?"); //computers starts at 0, humans start at 1
			name = input.nextLine(); // whatever name that can fit in a String
			final Player player = new Player(name); //creates an object named player of typer Player, their index number is the turn
			this.score.add(index, 0); //adds the score of 0 to the same position as the player turn
			if (registerPlayer(player)) { //checks if the player could be added to the ArrayList
				System.out.println("Successfully added " + name); 
			} else {
				System.err.println("ERROR: Couldn't add " + name);
			}
		}

		System.out.println("\nHow many automated players?");
		amount = input.nextInt(); //amount of automated players to play in game.
		for (int i = 0; i < amount; i++) {

			final Player ai = new AutomatedPlayer("AI " + (i + 1)); //Creates new player with the name AI + (i+1)
			this.score.add(index, 0); //adds the score of 0 to the score ArrayList
			if (registerPlayer(ai)) { //Checks if the AI could be created.
				System.out.println("Successfully added AI " + (i + 1));
			} else {
				System.err.println("ERROR: Couldn't add AI ");
			}
			index = index + 1; //increase index by one, used in turn and score position
		}
		final int[] dieInfo = new int[dices.size()]; //dieInfor array is created to be sent to Player.
		for (final Die die : dices) {
			dieInfo[dices.indexOf(die)] = die.getNumberOfSides(); //adds the number of sides to an array
		}

		System.out.println("\nThere are currently " + dices.size() + " dices in play.");
		players.get(0).receiveDiceInfo(dieInfo); //prints dice information, how many sides each die has
		players.get(0).receiveNumbersOfPlayers(players.size()); //prints the amount of players in the game.

		for (final Player player : players) { //loops through every player in players arraylist
			player.receiveMyTurnNumber(players.indexOf(player)); //prints the turn number for each player.
			;
		}

		for (int turn = 0; turn < 15 && this.winner == null && players.size() > 0; turn++) { //loops through 15 rounds of the game, exits if the winner is changed
			players.get(0).receiveCurrentTurnNumber(turn); //call the current turn number.
			
			
			int score = 0;
			for (this.turn = 0; this.turn < players.size() && this.winner == null; this.turn++) { //loop as long as this.turn is less than the size of player or the winner is null
				players.get(this.turn).receiveMyTurnNumber(this.turn); //Shows whose turn it is
				score = rollPlayer(); //gets the score from rollPlayer()
				
				if (score == -1) { //if the score comes back -1 there is a winner.
					this.winner = players.get(this.turn);
				} else if (score == -2) { //if the score comes back -2 we lost a player, and someone replaces their turn.
					this.turn = this.turn - 1;
				} else {
					System.out.println("\nScore is: " + score + "\n");
					this.score.set(this.turn, (this.score.get(this.turn) + score)); //updates the score with the current + new
				}

			}
			
			for (final Player player : players) { //loops through every player
						//prints the name and their score.
				System.out.println(player.getName() + " has " + this.score.get(players.indexOf(player)) + " points");
			}
		}

		int temp = 0; //holds the result in a temp to be compared
		if (this.winner == null) { //if there is still no winner usually never happens, prime is often been rolled before 15 turns
			for (final Integer result : score) { //loop through every score.
				if (result <= 500 && result > temp) { //the winner should be closest to 500 withouth being over, and the result must be more than the temp value
					this.winner = players.get(score.indexOf(result)); //declare the winner.
					temp = result; //store the current result to compare the next
				} else {
					temp = result; //store the current result to compare the next
				}
			}
		}

		if (this.winner != null) { //if the winner is not null anymore, annonce the winner
			
			System.out.println(this.winner.getName() + " WON!");
		} else { //the game ends in a draw, highly unlikely.
			System.out.print("Draw!");
		}
	}


	/**
	 * The rollPlayer takes each players turn input
	 * The player gets a choice to guess which die is non
	 * repeating, declares the player the winner if they guess correct. And removes
	 * them and their score if they guess incorrectly. The dices are then rolled, if
	 * it is a prime number the player instantly wins and the game ends. They then
	 * get the chance to re-roll the die, if the re-roll is also a prime number the
	 * player wins. It then calculates the score.
	 * 
	 * @return The score of the player for the current round.
	 */
	private int rollPlayer() {
		int guess[]; //the local variable for guess
		int score = 0; //local variable to keep the score
		final int[] rolls = new int[dices.size()]; //keep the dice side in an array
		int[] rerolls = new int[dices.size()]; //used to replace the rolls

		System.out.println(players.get(this.turn).getName()
				+ " would you like to guess which die is the non-repeating? yes(1) or no(0)");
		if (players.get(this.turn).wantsToGuess()) { //returns true or false whether or not the player clicked 1
			System.out.println("Any number between 1 and " + dices.size());
			guess = players.get(this.turn).guess(); //store the player guess in local var
			
			
			while (guess[0]-1 < 0 || guess[0]-1 > dices.size() || guess[1]-1 < 0 || guess[1]-1 > dices.size()) { // error check if the die is in the arraylist
				System.out.println("The die you selected doesn't match any existing dice. Please select another die");
				guess = players.get(this.turn).guess();
			}
			for(int g : guess) {
				if (dices.get(g-1) instanceof NonRepeatingDie) { //check if the guess is the non repeating die
					System.out.println("Correct");
					this.winner = players.get(this.turn); //sets the winner of the game
					return -1; //returns -1 to exit and announce the winner.
				} else {
					players.remove(this.turn); //remove the user
					this.score.remove(this.turn); //remove the user score.
					return -2; //return -2 to announce that someone got booted from the game

				}
			}
			
		} else {
			for (final Die dice : dices) { //roll every dice in the dices arraylist
				System.out.println("Die " + dice.getId() + " is rolling...");  
				rolls[dices.indexOf(dice)] = dice.roll(); //add the rolls to a local array.
				System.out.println("Number is " + rolls[dices.indexOf(dice)]); 
				System.out.println();
				score = score + rolls[dices.indexOf(dice)]; //adds every roll to the score.
			} // for(Die dice : dices) {

			if (isPrime(score, 2)) { //check if the total was a prime.
				System.out.println("The total roll is a prime! " + score);
				this.winner = players.get(this.turn); //set the winner
				return -1; //exit the turn with -1 for winner.
			}
			System.out.println("Score is " + score + "\n");
			System.out.println("Would you like to re-roll? yes(1) or no (0)");
			if (players.get(this.turn).wantsToGuess()) {
				score = 0; //reset the score to 0
				System.out.println("How many of the dices would you like to re-roll? 0-" + dices.size());
				rerolls = players.get(this.turn).reroll(rolls.length); //gets an array of which dices to reroll

				for (int i = 0; i < rolls.length; i++) { //goes through every die in the roll
					for (int j = 0; j < rolls.length; j++) { //goes through the reroll array to check if it matches the id
						if (rerolls[j] == dices.get(i).getId()) { //if the array matches with the ID of the dice it will reroll
							System.out.println("Re-rolling dice " + dices.get(i).getId());
							rolls[i] = dices.get(i).roll(); //get the dice that matches with the one in the array
							System.out.println("Number is " + rolls[i]);
							}
						}
					score = score + rolls[i]; //add every roll to a new score.
					}
				if (isPrime(score, 2)) { //check if it is prime
					System.out.println("The total re-roll is a prime! " + score);
					this.winner = players.get(this.turn); //announce the winner.
					return -1;
				}
			}
		} // if(player.wantsToGuess())
		return score; //return the score
	}

	/**
	 * Adds a number of dices between 4-9 to an ArrayList. Then replaces two of the
	 * dices with a non repeating dice. Uses a random number and places the
	 * non-repeating die next to each other.
	 */
	private void createDices() {
		int index;
		int pos;
		int pos2;
		for (index = 0; index < (this.rand.nextInt(6) + 4); index++) {
			dices.add(new Die(index, this.rand.nextInt(17) + 5)); // adds a new die with the index as id and number
																		// of sides between 5 and 21
		}
		pos = this.rand.nextInt(index); //first pos
		pos2 = this.rand.nextInt(index);
		dices.set(pos, new NonRepeatingDie(dices.get(pos).getId(), this.rand.nextInt(17) + 5)); //changes one of the dies to a non repeating
		
		while(pos == pos2) { //change the random die placement if they are the same
			pos2 = this.rand.nextInt(index);
		}
		dices.set(pos2, new NonRepeatingDie(dices.get(pos2).getId(), this.rand.nextInt(17) + 5)); //changes another die in a different place
		
	}
	

	/**
	 * check if a number is prime
	 * 
	 * @param num any int
	 * @return false if it is not a prime. True for all other cases.
	 */

	public boolean isPrime(int num, int i) {
		
		if(i < num) { //goes through every number
			if(num%i != 0) { //check if num is divisible by i
				return isPrime(num, i+1); //returns the result of the next i
			}else { //returns false if it is
				return false;
			}	
		}
		return false; //return true when all numbers is accounted for
	}
}
