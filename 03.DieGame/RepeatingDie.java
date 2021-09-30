import java.util.Random;

public class RepeatingDie extends Die {

	private int[] oldDie;
	private int index;
	private Random rand;
	public RepeatingDie(int id, int numSides) {
		super(id, numSides);
		oldDie = new int[numSides];
		index = 0;
		rand = new Random();
		// TODO Auto-generated constructor stub
	}
	
	public int roll() {
		index++;
		if(index > numSides-1 || index < 0) {
			index = 0;
		}
		if(index%2==0) {
			System.out.println("If: "+ index);
			int random = rand.nextInt(super.numSides)+1;
			System.out.println(random);
			oldDie[index] = random;
			System.out.println(oldDie[index]);
		}else {
			System.out.println("Else: " + index);
			oldDie[index] = oldDie[index-1];
		}
		
		if(index > oldDie.length-1) {
			return oldDie[index-1];
		}
		
		
		return oldDie[index];
	}
	
	
	
	
	
}
