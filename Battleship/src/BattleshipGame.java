import java.util.Scanner;


public class BattleshipGame {
	
	// TODO
	// refactoring all around
	
	static Scanner IN = new Scanner(System.in);
	static String ALLOWED_INPUT = "\\d{1}";

	public static void main(String[] args) {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		ocean.print();

		while(!ocean.isGameOver()){
			ocean.shootAt(getInput("column"), getInput("row"));
			ocean.print();
			System.out.println("Fired shots: " + ocean.getShotsFired());
			System.out.println("Hits: " + ocean.getHitCount());
			System.out.println("Ships sunk:" + ocean.getShipsSunk());
		}
		
		ocean.print();
		System.out.println("You won, you're the master");
		System.out.println("Fired shots: " + ocean.getShotsFired());
		System.out.println("Hits: " + ocean.getHitCount());
		System.out.println("Ships sunk:" + ocean.getShipsSunk());
		IN.close();
	}
	
	static int getInput(String columnOrRow){
		boolean valid = false;
		String input = null;
		while (!valid){
			System.out.println("Please enter the " + columnOrRow + " number you want to shoot at (only 0-9 accepted): ");
			input = IN.nextLine();
			if (input.matches(ALLOWED_INPUT)){
				valid = true;
			}
		}
		return Integer.parseInt(input);
	}
}
