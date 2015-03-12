public class Ocean {

  private Ship[][] ships = new Ship[10][10];
  private int shotsFired = 0;
  private int hitCount = 0;
  private int shipsSunk = 0;
  private int randomRow;
  private int randomColumn;
  private int randomPosition;
  private boolean randomBoolPosition;
  private int SIDE_LENGTH = 9;

  /**
   * The constructor Creates an empty ocean (fills the ships array with EmptySeas).
   * Also initialises any game variables, such as how many shots have been fired.
   */
  Ocean(){
    fillSea();
  }

  /**
   * fillSea method iterates through each element of the ocean bidimensional ships
   * and assigns it to an EmptySea class
   */
  private void fillSea(){
    for(int row = 0; row < 10; row++){
      for(int column = 0; column < 10; column++){
        ships[row][column] = new EmptySea();
      }
    }
  }

  /**
   * placeAllShipsRandomly method places all ten ships randomly on the
   * (initially empty) ocean. The methods places larger ships before
   * smaller ones in order to avoid to end up with no legal place
   * to put a large ship.
   */
  void placeAllShipsRandomly() {
    Ship ships[] = new Ship[10];
    ships[0] = new Battleship();
    ships[1] = new Cruiser();
    ships[2] = new Cruiser();
    ships[3] = new Destroyer();
    ships[4] = new Destroyer();
    ships[5] = new Destroyer();
    ships[6] = new Submarine();
    ships[7] = new Submarine();
    ships[8] = new Submarine();
    ships[9] = new Submarine();

    for(Ship ship : ships){
      generateRandomPositions();
      while(!ship.okToPlaceShipAt(randomRow, randomColumn, randomBoolPosition, this)) {
        generateRandomPositions();
      }
      while(ship.okToPlaceShipAt(randomRow, randomColumn, randomBoolPosition, this)) {
        ship.placeShipAt(randomRow, randomColumn, randomBoolPosition, this);
      }
    }
  }

  /**
   * generateRandomPositions method assigns a random value to each of the variables
   * used for placing Ships on the board
   */
  void generateRandomPositions(){
    randomRow = (int) (Math.random()*(SIDE_LENGTH + 1));
    randomColumn = (int) (Math.random()*(SIDE_LENGTH + 1));
    randomPosition = (int) (Math.random()*2);
    if (randomPosition == 1){
      randomBoolPosition = true;
    } else {
      randomBoolPosition = false;
    }
  }

  /**
   * isOccupied method receives @param row and @param column,
   * @return true if a cell at the given row and column is not occupied,
   * false otherwise.
   */
  boolean isOccupied(int row, int column) {
    if (getShipArray()[row][column].getShipType() == "EmptySea") {
      return false;
    } else {
      return true;
    }
  }

  /**
   * shootAt has requires @param row and @param column for shooting at cells
   * the Ship array. shoots at the correct cell by using the coordinates provided
   * in the parameters.
   * @return true when the method hits a un-hit ship cell.
   * @return false when hits an already hit cell, when hitting sea or a sunk ship
   * The method also takes charge of updating all the counters.
   * - Every time shootAt is called, fireShot method is called too.
   * - Every time shootAt is used to shoot on an un-hit section of a ship, it calls
   * the addHit method and the ship.shootAt method.
   * it also checks if has sunk a ship, in that case the relevant counter is updated
   */
  boolean shootAt(int row, int column)  {
    Ship ship = getShipArray()[row][column];
    fireShot();

    if (!isOccupied(row, column)){
      ship.shootAt(row, column);
      return false;
    } else if (ship.isASectionThatHasBeenAlreadyShot(row, column) || ship.isSunk()) {
      return false;
    } else { // shooting on a not hit section of a ship
      ship.shootAt(row, column);
      addHit();
      if (ship.isSunk()) {
        addSunk(); // add new sunk ship to counter
      }
      return true;
    }
  }

  /**
   * getShotsFired method @returns shotsFired variable
   */
  int getShotsFired(){
    return shotsFired;
  }

  /**
   * addHit method adds 1 to the hitCount variable
   */
  void addHit(){
    hitCount++;
  }

  /**
   * addSunk method adds 1 to the shipsSunk variable
   */
  void addSunk(){
    shipsSunk++;
  }

  /**
   * fireShot method adds 1 to the shotsFired variable
   */
  void fireShot(){
    shotsFired++;
  }

  /**
   * getHitCount is used to @return hitCount variable
   */
  int getHitCount() {
    return hitCount;
  }

  /**
   * getShipsSunk is used to @return shipsSunk variable
   * @return
   */
  int getShipsSunk(){
    return shipsSunk;
  }

  /**
   * isGameOver @returns false when sunk ships are less than ten,
   * false otherwise
   */
  boolean isGameOver() {
    if (getShipsSunk() < 10){
      return false;
    } else {
      return true;
    }
  }

  /**
   * getShipArray is used to @return ships variable
   */
  Ship[][] getShipArray() {
    return ships;
  }

  /**
   * print is used to print the ocean.
   * Row numbers are displayed along the left edge of the array and column numbers
   * are displayed along the top.
   * Row and column numbers go from 0 to 9.
   * - 'S' is used to indicate a location on which user has fired upon a real Ship but
   * did not sunk it yet.
   * - '-' is used to indicate a location on which the user has fired upon and found nothing
   * there
   * - 'x' is used to indicate a location containing a sunk ship
   * - '.' is used to indicate a location that has never been fired upon
   */
  void print() {
    System.out.println("\n   0  1  2  3  4  5  6  7  8  9 "); // prints columns
    for(int row = 0; row < 10; row++){
      System.out.print(row + " "); // prints rows
      for(int column = 0; column < 10; column++){
        Ship ship = getShipArray()[row][column];

        if (isHitSection(row, column) && !ship.isSunk()){
          System.out.print(" S ");
        } else {
          System.out.print(" " + ship + " ");
        }
      }
      System.out.println();
    }
    System.out.println();
  }

  /**
   * isHitSection uses @param row and @param column to check if a cell is part
   * of a unsunk ship but has already been fired upon
   * @return true if the above case is true, false otherwise
   */
  boolean isHitSection(int row, int column){
    Ship ship = getShipArray()[row][column];

    if (isOccupied(row, column)){
      for(int i = 0; i < ship.getHits().length; i++){
        if (ship.isHorizontal()){
          if  (ship.getHits()[i] == true){
            if (ship.getBowColumn() + i == column){
              return true;
            }
          }
        } else {
          if  (ship.getHits()[i] == true){
            if (ship.getBowRow() + i == row){
              return true;
            }
          }
        }
      }
    }
    return false;
  }

}
