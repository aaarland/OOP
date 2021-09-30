import java.util.Arrays;
import java.util.Scanner;

public class Player {
	
	/**
	 * The name of the player
	 */
	private String name;
	/**
	 * The scanner instance variable
	 */
	private Scanner input = new Scanner(System.in);
	
	
	/**
	 * The player constructor
	 * @param name the name of the player
	 * @param turn the player turn.
	 */
	public Player(String name) {
		this.name = name;
	}
	
	/**
	 * Takes the users turn number and prints out the name and turn + 1, since the turn is indexed at 0
	 * @param turn the current user turn
	 */
	public void receiveMyTurnNumber(int turn) {
		System.out.println();
		System.out.println(getName() + " has turn number " + (turn+1));
	}
	
	/**
	 * Receives the current round number and prints it out.
	 * @param turn the current round/turn
	 */
	public void receiveCurrentTurnNumber(int turn) {
		System.out.println();
		System.out.println("The current round is " + (turn+1));
	}
	
	/**
	 * Takes the numbers of players in the game and prints it
	 * @param pNum
	 */
	public void receiveNumbersOfPlayers(int pNum) {
		System.out.println();
		System.out.println("There are currently " + pNum + " players playing");
	}
	
	/**
	 * Takes all the sides of the die and prints it out
	 * @param info
	 */
	public void receiveDiceInfo(int[] info) {
		
		for(int index = 0; index < info.length; index++) {
		System.out.println("Dice " + (index+1) + " have " + info[index] + " sides");
		}
	}
	
	/**
	 * Takes the score and prints it out.
	 * Does this after each round.
	 * @param results The score of each player.
	 */
	public void receiveRollResults(int[] results) {
		System.out.println("Results for " + getName() + " was ");
		for(int result : results) {
			System.out.print(result);
		}
		
	}
	
	/**
	 * Ask the user for input == 1 if they wants to guess.
	 * 
	 * @return true if input is 1
	 */
	public boolean wantsToGuess() {
		if(input.nextInt() == 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * Gets an input and adds it to the first position of an array.
	 * @return an array where only the first position is used
	 */
	public int[] guess() {
		int[] i = new int[2];
		i[0] = input.nextInt();
		i[1] = input.nextInt();
		return i ;
	}
	
	
	/**
	 * Reroll ask the user for how many dices they want to re-roll,
	 * then it takes each id of the die. It checks that the number is in the length of the die array
	 * It also remove all duplicates of the 
	 * @param length
	 * @return
	 */
	public int[] reroll(int length) {
		int input;
		int scan;
		input = this.input.nextInt();
		int[] dice = new int[length];
		int[] temp = new int[length];
		
		while(input < 0) {
			System.out.println("ERROR: can't be less than 0");
			input = this.input.nextInt();
		}
		
		if(input >= length) {
			for(int i = 0; i < length; i++) {
				dice[i] = i+1;
			}
			return dice;
		}
		System.out.println("Which die, enter one at the time.");
		for(int index = 0; index < input; index++) {
			do { //takes the input as long as it is above 0 and less than the length
			scan = this.input.nextInt();
			} while(scan < 0 || scan > length);
			temp[index] = scan; //adds the number to a temporary array
		}
		
		
		Arrays.sort(temp); //sorts the array
		int j = 0;
		for(int i = 0; i < temp.length-1; i++) {
			if(temp[i] != temp[i+1]) { //if they are not the same
				dice[j] = temp[i]; //add to dice[j]
				j = j+1;
			}
		}
		dice[j] = temp[temp.length-1]; //adds the last value to dice[j] which have been multiplied by i
		
		return dice; //return the dice array
	}
		
	
	/**
	 * 
	 * @return the name of the player
	 */
	public String getName() {
		return this.name;
	}

}
