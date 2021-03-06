import java.util.Scanner;

public class BattleshipGame {

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
    System.out.println("You won, you're the master of Battleships!!");

    IN.close();
  }

  /**
   * getInput with the aid of @param columnOrRow is used to ask user input.
   * The method checks if the user input is not valid, in that case it will
   * prompt the user again until he/she will have input a single digit number.
   * If the input is valid, it converts it to an integer and @returns it
   */
  private static int getInput(String columnOrRow){
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
