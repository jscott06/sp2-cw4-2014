/**
 * @author Jacopo Scotti
 * @since 19/12/2014
 *
 * Coursework4
 * - The objective of this coursework is building a Battleship game
 *   that can be played only in solo version
 * - In order to win the game the User must sink all the ships that
 *   have been automatically placed on the board by the program
 */

public class Ship {
  private int bowRow;
  private int bowColumn;
  protected int length;
  private boolean horizontal;
  private boolean [] hit;
  private boolean sunk = false;

  /**
   * initializeHitArray() method sets all the element in the hit array to
   * false (not hit).
   */
  void initializeHitArray(){
    hit = new boolean[getLength()];
    for(int i = 0; i < hit.length; i++){
      hit[i] = false;
    }
  }

  /**
   * getHits method is used to @return hit variable
   */
  boolean [] getHits(){
    return hit;
  }

  /**
   * @param indexOfSectionHit is used for marking the respective section of
   * the ship as hit
   */
  void setHit(int indexOfSectionHit){
    hit[indexOfSectionHit] = true;
  }

  /**
   * getBowRow method is used to @return bowRow variable
   */
  int getBowRow() {
    return bowRow;
  }

  /**
   * setBowRow method is used for setting a new value as bowRow
   */
  void setBowRow(int bowRow) {
    this.bowRow = bowRow;
  }

  /**
   * getBowColumn method is used to @return bowColumn variable
   */
  int getBowColumn() {
    return bowColumn;
  }
  /**
   * getBowColumn method is used for setting a new value as bowColumn
   */
  void setBowColumn(int bowColumn) {
    this.bowColumn = bowColumn;
  }

  /**
   * isHorizontal is used to @return horizontal variable value
   */
  boolean isHorizontal(){
    return horizontal;
  }

  /**
   * is used to set the value of this.horizontal with @param horizontal
   */
  void setHorizontal(boolean horizontal){
    this.horizontal = horizontal;
  }

  /**
   * getShipType is used to @return the type of the ship as a String value
   */
  String getShipType(){
    return "Ship";
  }

  /**
   * getLength is used to @return length value
   */
  int getLength(){
    return length;
  }

  /**
   * okToPlaceShipAt() method determines whether is okay to place a Ship in the
   * Ocean ( @param ocean) using the coordinates provided with @param bowRow,
   * @param bowColumn and @param horizontal.
   * The method, with the aid of fallsOutOfTheBoard and shipSectionHasImmediatelyAdjacentShip
   * methods, checks if:
   * - the Ship would fall over the board
   * - the Ship would be placed over other Ships
   * - the Ship would be placed immediately adjacent (horizontally, vertically
   *   or diagonally) to other ships
   * If any of the above conditions is true, the the method @return value will
   * be false, true otherwise.
   */
  boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean){
    if (fallsOutOfTheBoard(row, column, horizontal, ocean))
      return false;
    if (horizontal == true){
      for(int i = 0; i < this.getLength(); i++){
        if (shipSectionHasImmediatelyAdjacentShip(row, column + i, ocean)){
          return false;
        }
      }
    } else {
      for(int i = 0; i < this.getLength(); i++){
        if (shipSectionHasImmediatelyAdjacentShip(row + i, column, ocean)){
          return false;
        }
      }
    }
    return true;
  }

  /**
   * fallsOutOfTheBoard checks if the Ship with given @param bowRow
   * @param bowColumn, @param horizontal and @param ocean would fall over the board.
   * @return value is true when the Ship does not fall outside the board
   */
  boolean fallsOutOfTheBoard(int bowRow, int bowColumn, boolean horizontal, Ocean ocean){
    if (horizontal){
      if (this.getLength() + bowColumn > ocean.get_side_length())
        return true;
    } else {
      if (this.getLength() + bowRow > ocean.get_side_length())
        return true;
    }
    return false;
  }

  /**
   * shipSectionHasImmediatelyAdjacentShip given @param cellRow, @param cellColumn
   * and @param ocean, checks if a cell is surrounded by a Ship or if it would be placed
   * on a Ship.
   * @return value is false when the ship is not surrounded by a Ship, true otherwise.
   */
  boolean shipSectionHasImmediatelyAdjacentShip(int cellRow, int cellColumn, Ocean ocean){
    int tempRow;
    int tempColumn;
    for(int r = -1; r <= 1; r++){
      for(int c = -1; c <= 1; c++){
        tempRow = cellRow + r;
        tempColumn = cellColumn + c;
        if ((tempRow >= 0 && tempRow <= ocean.get_side_length() )&&(tempColumn >= 0 && tempColumn <= ocean.get_side_length())){
          if (ocean.isOccupied(tempRow, tempColumn)){
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * placeShipAt method places a Ship in the Ocean ( @param ocean) using the
   * coordinates provided with @param bowRow, @param bowColumn and @param horizontal.
   * and horizontal parameters. The method puts a reference to the ship
   * in each of 1 or more locations (up to 4) in the ships array in the Ocean
   * object.
   */
  void placeShipAt(int row, int column, boolean horizontal, Ocean ocean){
    setHorizontal(horizontal);
    setBowRow(row);
    setBowColumn(column);

    for(int i = 0; i < this.getLength(); i++){
      if (isHorizontal()){
        ocean.getShipArray()[row][column + i] = this;
      } else {
        ocean.getShipArray()[row + i][column] = this;
      }
    }
  }

  /**
   * shootAt method is used for shooting at cell in the ships array in the Ocean
   * Object. It will shoot at the cell with coordinates provided in @param row
   * and @param column.
   * When shooting on a valid Ship object, the method calls automatically the
   * setHit() checkHits() methods and the @returns true, false otherwise.
   */
  boolean shootAt(int row, int column)  {
    for(int i = 0; i < this.getLength(); i++){
      if (isHorizontal()){
        if (getBowColumn() + i == column){
          setHit(i);
          checkHits();
          return true;
        }
      } else {
        if (getBowRow() + i == row){
          setHit(i);
          checkHits();
          return true;
        }
      }
    }
    return false;
  }

  /**
   * isASectionThatHasBeenAlreadyShot receives @param row and @param column.
   * It searches through the Ships array in the Ocean object and @return true
   * if at the coordinates provided there is a section of a Ship that has been
   * hit already.
   */
  boolean isASectionThatHasBeenAlreadyShot(int row, int column)  {
    for(int i = 0; i < this.getLength(); i++){
      if (isHorizontal()){
        if (getBowColumn() + i == column){
          if (getHits()[i])
            return true;
        }
      } else {
        if (getBowRow() + i == row){
          if (getHits()[i])
            return true;
        }
      }
    }
    return false;
  }

  /**
   * isSunk is used to @return sunk value
   */
  boolean isSunk(){
    return sunk;
  }

  /**
   * checkHits method checks if all the sections of the Ship have been hit,
   * in that case it would sunk the Ship using the setSunk() method and @return true,
   * false otherwise.
   */
  private boolean checkHits() {
    for (boolean section : getHits()){
      if (!section){
        setSunk(false);
        return false;
      }
    }
    setSunk(true);
    return true;
  }

  /**
   * is used to set this.sunk to @param sunk value
   */
  private void setSunk(boolean sunk){
    this.sunk = sunk;
  }

  @Override public String toString(){
    if (isSunk()) {
      return "X";
    }
    return ".";
  }

}
