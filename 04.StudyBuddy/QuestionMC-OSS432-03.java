
public class QuestionMC implements Question {

	
	private String question;
	private String answer;
	private int points;
	
	public QuestionMC(String question, String choices, String answer, String points) {
		this.question = question;
		this.answer = answer.toLowerCase();
		this.points = Integer.parseInt(points.replaceAll("\\s", ""));
	}
	
	
	
	@Override
	public String getQuestion() {
		// TODO Auto-generated method stub
		return this.question;
	}

	@Override
	public boolean isCorrect(String ans) {
		if(this.answer.equals(ans)) {
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public String getCorrectAns() {
		// TODO Auto-generated method stub
		return this.answer;
	}

	@Override
	public int getPoints() {
		// TODO Auto-generated method stub
		return this.points;
	}

}
