public class Ocean {

  private Ship[][] ships = new Ship[10][10];
  private int shotsFired;
  private int hitCount;
  private int shipsSunk;

  // to refactor/remove/move?
  private int randomRow;
  private int randomColumn;
  private int randomPosition;
  private boolean randomBoolPosition;
  private int sideLength = 9;

  Ocean(){
    /* TODO
    The constructor Creates an empty ocean (fills the ships array with EmptySeas).
    Also initialises any game variables, such as how many shots have been fired.
     */
    fillSea();
    shotsFired = 0;
    hitCount = 0;
    shipsSunk = 0;
  }

  private void fillSea(){
    for(int row = 0; row < 10; row++){
      for(int column = 0; column < 10; column++){
        ships[row][column] = new EmptySea();
      }
    }
  }

  void placeAllShipsRandomly() {
    /* UNTESTABLE in a conventional way?
    Place all ten ships randomly on the (initially empty) ocean.
    Place larger ships before smaller ones, or you may end up with
    no legal place to put a large ship.
     */
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
      generateRandomPosition();
      while(!ship.okToPlaceShipAt(randomRow, randomColumn, randomBoolPosition, this)) {
        generateRandomPosition();
      }
      while(ship.okToPlaceShipAt(randomRow, randomColumn, randomBoolPosition, this)) {
        ship.placeShipAt(randomRow, randomColumn, randomBoolPosition, this);
        System.out.println(
            "Placed a " + ship.getShipType() +
            " - Bow row: " + randomRow +
            " - Bow column: " + randomColumn +
            " - Horizontal: " + randomBoolPosition);
      }
    }
  }

  void generateRandomPosition(){
    randomRow = (int) (Math.random()*sideLength);
    randomColumn = (int) (Math.random()*sideLength);
    randomPosition = (int) (Math.random()*2);
    if (randomPosition == 1){
      randomBoolPosition = true;
    } else {
      randomBoolPosition = false;
    }
  }

  boolean isOccupied(int row, int column) {
    /*
    Returns true if the given location contains
    a ship, false if it does not.
     */
    if (getShipArray()[row][column].getShipType() == "EmptySea") {
      return false;
    } else {
      return true;
    }
  }

  boolean shootAt(int row, int column)  {
    /*
    Returns true if the given location contains
    a real ship, still afloat, (not an EmptySea), false if it does not. In addition, this
    method updates the number of shots that have been fired, and the number of hits.
    Note: If a location contains a real ship, shootAt should return true every time
    the user shoots at that same location. Once a ship has been sunk, additional shots
    at its location should return false.
     */
    Ship ship = getShipArray()[row][column];
    fireShot();

    if (!isOccupied(row, column)){ // shooting on sea
      ship.shootAt(row, column);
      return false;
    } else if (ship.isASectionThatHasBeenAlreadyShot(row, column) || ship.isSunk()) {
      return false;
    } else { // shooting on a not hit section of a ship
      ship.shootAt(row, column);
      hit();
      if (ship.isSunk()) {
        shipsSunk++; // add newly sunk ship to counter
      }
      return true;
    }
  }

  int getShotsFired(){
    return shotsFired;
  }

  void hit(){
    hitCount++;
  }

  void fireShot(){
    shotsFired++;
  }

  int getHitCount() {
    return hitCount;
  }
  int getShipsSunk(){
    return shipsSunk;
  }
  boolean isGameOver() {
    // Returns true if all ships have been sunk, otherwise false.
    if (getShipsSunk() < 10){
      return false;
    } else {
      return true;
    }
  }

  Ship[][] getShipArray() {
    return ships;
  }

  void print() {
    /* TODO
  Prints the ocean. To aid the user, row numbers should be displayed along
  the left edge of the array, and column numbers should be displayed along the top.
  Numbers should be 0 to 9, not 1 to 10. The top left corner square should be 0,
  0. Use ’S’ to indicate a location that you have fired upon and hit a (real) ship,
  ’-’ to indicate a location that you have fired upon and found nothing there, ’x’
  to indication location containing a sunken ship, and ’.’ to indicate a location
  that you have never fired upon.
  This is the only method in the Ocean class that does any input/output, and it is
  never called from within the Ocean class (except possibly during debugging), only
  from the BattleshipGame class.
     */
    System.out.println("   0  1  2  3  4  5  6  7  8  9 "); // prints columns
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
  }

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
