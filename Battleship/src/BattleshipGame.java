import java.util.Scanner;


public class BattleshipGame {
	
	// TODO
	// - Fix end game bug (does not count sunk ship properly)
	// - Add exceptions (ie, typing something that is not 0-9)
	
	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		ocean.print();

		while(!ocean.isGameOver()){
			ocean.shootAt(getRow(), getColumn());
			ocean.print();
			System.out.println("Fired shots: " + ocean.getShotsFired());
			System.out.println("Hits: " + ocean.getHitCount());
		}
		
		ocean.print();
		System.out.println("You won, you're the master");
		System.out.println("Fired shots: " + ocean.getShotsFired());
		System.out.println("Hits: " + ocean.getHitCount());
		
		in.close();
	}
	
	// BOTH TO REFACTOR
	static int getRow(){
		System.out.println("Please enter the row you want to shoot at (0-9): ");
		int row = in.nextInt();
		return row;
		
	}
	static int getColumn(){
		System.out.println("Please enter the column you want to shoot at (0-9): ");
		int column = in.nextInt();
		return column;
		
	}

}
