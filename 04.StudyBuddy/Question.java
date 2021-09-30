/**
 * the Question interface, all the Question types implements this.
 * @author adrianaarland
 *
 */
public interface Question {

	
	public String getQuestion();
	
	public boolean isCorrect(String ans);
	
	public String getCorrectAns();
	
	public int getPoints();
	
	
}
