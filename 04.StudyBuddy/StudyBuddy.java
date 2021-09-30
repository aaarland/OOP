import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * StudyBuddy is the main class of the program, it is what interacts with the
 * user and asks the Question interface about points, answer, and questions.
 * <br>
 * there are three main options a user gets, pass, delay, or guess. When the
 * user guesses a question it is validated and a score is calculated based on
 * whether or not the user guessed the right answer.
 * 
 * @author adrianaarland
 *
 */

public class StudyBuddy {
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		StudyBuddy sb = new StudyBuddy();

		System.out.println(
				"Welcome to StudyBubby! Press enter when you are ready to select the file holding your study questions.");
		scan.nextLine();
		sb.study();
	}

	/**
	 * study interacts with the user, takes inputs and evaluated them to the
	 * questions. it does error checking for user input. User can enter any type of
	 * string, but the loop will continue until it is an int. <br>
	 * 
	 * 
	 */
	public void study() {

		ArrayList<Question> questions = new ArrayList<Question>();
		QuestionMaker qm = new QuestionMaker();
		int input = -1; // set the user input to -1 so it will keep in the loop if the user doesn't
		// enter anything
		String userInput;
		int correct = 0;
		int score = 0;
		int totalPoints = 0;

		questions = qm.getQuestion(); // calls the arraylist from QuestionMaker
		Collections.shuffle(questions); //shuffles the questions
		System.out.println("Thank you!");

		do { // ask the user for how many questions until they enter an int
			System.out.println("How many questions would you like to enter?");
			userInput = scan.nextLine();
			if (isNum(userInput)) { // do if the user enters a number
				try {
					input = Integer.parseInt(userInput);
				} catch (Exception e) { // catches extremely large numbers, loop will continue as 0 questions are loaded
					System.out.println(
							"You tried an extremly large number! Please select a number from 0-" + questions.size());
				}
				if (input < 0 || input > questions.size()) {
					System.out.println("There are only " + questions.size() + " questions loaded.");
				}

			} else {
				System.out.println("That isn't a valid input");
			}
		} while (!isNum(userInput) || input > questions.size() || input < 0);
		
		
		//goes through the amount of questions the user specified.
		for (int index = 0; index < input; index++) {
			totalPoints = totalPoints + questions.get(index).getPoints();
			System.out.println("Points " + questions.get(index).getPoints());
			System.out.println(questions.get(index).getQuestion());
			userInput = scan.nextLine().toLowerCase(); // is converted to lower case so that A == a, B == b, etc.
			if (userInput.equals("pass")) {
				System.out.println("Ok, let's skip that one.");
			} else if (userInput.equals("delay")) {
				System.out.println("Ok, I will ask that one later.");
				questions.add(questions.remove(index)); // removes the question and adds it back on top of the stack.
				index--; // index have been shifted by 1.
			} else if (questions.get(index).isCorrect(userInput)) {
				System.out.println("Correct! You get " + questions.get(index).getPoints());
				score = score + questions.get(index).getPoints();
				correct++;
			} else {
				System.out.println("Incorrect! The answer was " + questions.get(index).getCorrectAns() + " You loose "
						+ questions.get(index).getPoints() + " points");
				score = score - questions.get(index).getPoints();
			}
			System.out.println();
		}

		System.out.println("Of the " + input + " you attempted you got " + correct + " correct");
		System.out.println("You got a total of " + score + " points");

		if (input == 0) { // prints yikes since the user entered nothing
			System.out.println("Yikes!");
		} else if (score < totalPoints / input) { // tells the user better luck next time if they got an average score
			// lower than all the points average
			System.out.println("Better Luck next time!");
		} else { // positive feedback
			System.out.println("Good job!");
		}

	}

	/**
	 * Checks if a string is a number/int
	 * 
	 * @param line any string to be checked
	 * @return true or false, true if the string contains a valid number.
	 */
	static public boolean isNum(String line) {
		if (line.length() == 0) {
			return false;
		}
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) > '9' || line.charAt(i) < '0') {
				return false;
			}
		}
		return true;

	}
}
