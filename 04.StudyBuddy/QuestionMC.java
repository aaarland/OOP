public class QuestionMC implements Question {

	/**
	 * Holds the question
	 */
	private String question;
	/**
	 * Holds the answer for the question
	 */
	private String answer;
	/**
	 * holds the amount of points for the question
	 */

	private int points;
	/**
	 * holds the choices for the question
	 */
	private String choices;

	/**
	 * the constructor builds the question with inputs from question maker
	 * 
	 * @param question the question that is being asked
	 * @param choices  the choices the user have
	 * @param answer   the answer to the question
	 * @param points   amount of points the question is worth.
	 */
	public QuestionMC(String question, String choices, String answer, String points) {
		this.question = question;
		this.answer = answer.toLowerCase(); // keep all answer lower case.
		this.points = Integer.parseInt(points.replaceAll("\\s", ""));
		this.choices = choices;
	}

	@Override
	/**
	 * returns a string with the question and choices.
	 */
	public String getQuestion() {
		// TODO Auto-generated method stub
		return "Multiple choice :" + this.question + "\n" + this.choices;
	}

	/**
	 * check if the answer is correct returns true if it is
	 */
	@Override
	public boolean isCorrect(String ans) {
		return this.answer.toLowerCase().equals(ans); // returns true if they match, ans have also been converted to lowerCase.

	}

	/**
	 * returns the correct answer
	 */
	@Override
	public String getCorrectAns() {
		// TODO Auto-generated method stub
		return this.answer.toUpperCase(); //A B C, should always be displayed in uppercase
	}

	/**
	 * returns the points the question is worth.
	 */
	@Override
	public int getPoints() {
		// TODO Auto-generated method stub
		return this.points;
	}

}
