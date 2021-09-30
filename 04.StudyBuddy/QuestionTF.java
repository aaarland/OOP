
public class QuestionTF implements Question {

	private String question;
	private String answer;
	private int points;

	/**
	 * the constructor builds the question with inputs from question maker
	 * 
	 * @param question the question that is being asked
	 * @param answer   the answer to the question
	 * @param points   amount of points the question is worth.
	 */
	public QuestionTF(String question, String answer, String points) {
		this.question = question;
		this.answer = answer.toLowerCase();
		this.points = Integer.parseInt(points);

	}

	/**
	 * returns a string with the question.
	 */
	@Override
	public String getQuestion() {
		// TODO Auto-generated method stub
		return "True/False :" + this.question;
	}

	/**
	 * check if the answer is correct returns true if it is
	 */
	@Override
	public boolean isCorrect(String ans) {
		return this.answer.toLowerCase().equals(ans);
	}

	/**
	 * returns the a string with the correct answer
	 */
	@Override
	public String getCorrectAns() {
		// TODO Auto-generated method stub
		return this.answer;
	}

	/**
	 * returns the point value
	 */

	@Override
	public int getPoints() {
		// TODO Auto-generated method stub
		return this.points;
	}

}
