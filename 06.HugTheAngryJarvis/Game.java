
public class Game {	
	public static void main(String[] args) {
		int col = 30;
		int row = 15;
		
		Board board = new Board(row, col);
		Mobile player = new Player(board);
		Mobile jarvis = new Jarvis(board);
		System.out.println("Running game");
		board.placeElement(player, row/2, col-(col/3));
		board.placeElement(jarvis, row/2, col/3);
		Thread pl = new Thread(player, "Player");
		Thread ja = new Thread(jarvis, "Jarvis");
		pl.start();
		ja.start();
		try {
			pl.join();
			ja.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Ending game...");
	}
}
