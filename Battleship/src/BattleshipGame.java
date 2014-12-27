import java.util.Scanner;


public class BattleshipGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		

	}
	
	// BOTH TO REFACTOR
	static int getRow(){
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter the row you want to shoot at (0-9): ");
		int row = in.nextInt();
		return row;
		
	}
	static int getColumn(){
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter the column you want to shoot at (0-9): ");
		int column = in.nextInt();
		return column;
		
	}

}
