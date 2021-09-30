import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class QuestionMaker {

	/**
	 * This method loads a file and then checks every line of it, it then uses the
	 * delimiter ; to separate with StringTokenizer. It checks which type of
	 * question it is and delegates it. If the question doesn't match any format it
	 * falls through and prints an error message for it. it creates the question
	 * with a dedicated method for each type. then it adds it to the array list.
	 * 
	 * @return ArrayList<Question> returns an arrayList of true/false, multiple
	 *         choice, or fill in the blank questions.
	 * 
	 */
	public ArrayList<Question> getQuestion() {
		FileReader file = null;
		do {
			try {
				file = new FileReader(); // call the filereader
			} catch (Error e) {
				// TODO Auto-generated catch block
				file = null;
				System.err.println(e.getLocalizedMessage());
			}
		}while(file == null); //repeats the loop until user selects a file
	
		
		ArrayList<Question> questions = new ArrayList<Question>(); // create a new arraylist of type question
		int index = 0; // used for error checking, keeps track of the current line number.
		while (file.fileHasNextLine()) { // does this as long as the file has more lines
			index++;
			String line = file.getNextLineOfFile(); // add the line of the file to a string
			StringTokenizer st = new StringTokenizer(line, ";"); // splits the string with the delimiter ';'
			try {

				// string variables to make it more readable
				String question = "";
				String choices = "";
				String answer = "";
				String points = "";
				switch (st.nextToken().trim()) { // uses the first column to pick a question
				case "MC": // multiple choice
					question = st.nextToken().trim(); // gets the question and removes excess spaces
					char option = 'A';
					int numChoice = Integer.parseInt(st.nextToken().trim()); // turns the number of choices into an int
					for (int i = 0; i < numChoice; i++) {
						choices = choices + option + ") " + st.nextToken().trim() + "\n"; // creates a string with each
																							// choice and their
																							// respected character.
						option++; // adds to the character to turn it from A->B->C....
					}
					answer = st.nextToken().trim();
					points = st.nextToken().trim(); // exception will be thrown if the format for points is not an int
					questions.add(new QuestionMC(question, choices, answer, points)); // adds a new question of type
																						// QuestionMC to the arraylist
																						// with the info gathered
					break;
				case "FB":
					question = st.nextToken().trim();
					answer = st.nextToken().trim();
					points = st.nextToken().trim(); // exception will be thrown if the format for points is not an int
					questions.add(new QuestionFB(question, answer, points)); // adds a new question of type QuestionFB
																				// to the arraylist with the info
																				// gathered
					break;
				case "TF":
					question = st.nextToken().trim();
					answer = st.nextToken().trim();
					points = st.nextToken().trim(); // exception will be thrown if the format for points is not an int
					questions.add(new QuestionTF(question, answer, points)); // adds a new question of type QuestionTF
																				// to the arraylist with the info
																				// gathered
					break;
				default:
					System.err.println("ERROR: Couldn't figure out the question type, skipping question");
					System.err.println(index + "\t" + line); // prints the current line that the program is having an
																// issue with
				}
			} catch (NoSuchElementException e) { // catches an exception that is caused when st.nextToken() doesn't
													// exist ergo the format is wrong
				System.err.println("ERROR: Wrong format! Skipping question.");
				System.err.println(index + "\t" + line); // prints the current line that the program is having an issue
															// with
			} catch (NumberFormatException e) { // catches an exception that is caused when a non integer is assigned to
												// an int
				System.err.println("ERROR: need a number that specifies the amount of choices.");
				System.err.println(index + "\t" + line); // prints the current line that the program is having an issue
															// with
			} catch (Exception e) { // catches all other exceptions, keeps the program running and skipps to next
									// line.
				e.printStackTrace();
			}

		}
		return questions; // returns the arrayList
	}

}
