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
  private boolean sunk;

  Ship(){
    setSunk(false);
  }

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
  boolean [] getHits(){
    return hit;
  }

  void setHit(int sectionHit){
    hit[sectionHit] = true;
  }

  int getBowRow() {
    return bowRow;
  }
  void setBowRow(int bowRow) {
    this.bowRow = bowRow;
  }
  int getBowColumn() {
    return bowColumn;
  }
  void setBowColumn(int bowColumn) {
    this.bowColumn = bowColumn;
  }

  boolean isHorizontal(){
    return horizontal;
  }

  String getShipType(){
    return "Ship";
  }

  int getLength(){
    return length;
  }

  void setHorizontal(boolean horizontal){
    this.horizontal = horizontal;
  }

  /**
   * okToPlaceShipAt() method determines whether is okay to place a Ship in the
   * Ocean ( @param ocean) using the coordinates provided with @param bowRow,
   * @param bowColumn and @param horizontal.
   * The methods checks if:
   * - the Ship would fall over the board
   * - the Ship would be placed over other Ships
   * - the Ship would be placed immediately adjacent (horizontally, vertically
   *   or diagonally) to other ships
   * If any of the above conditions is true, the the method @return value will
   * be false, true otherwise.
   */
  boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean){
    if (horizontal == true){
      if (this.getLength() + column > 9){ // over the border
        return false;
      } else {
        for(int i = 0; i < this.getLength(); i++){
          if (ocean.isOccupied(row, column + i)){
            return false;
          }
        }
      }
    } else {
      if (this.getLength() + row > 9){ // over the border
        return false;
      } else {
        for(int i = 0; i < this.getLength(); i++){
          if (ocean.isOccupied(row + i, column)){
            return false;
          }
        }
      }
    }
    return true;
  }


  boolean fallsOutOfTheBoard(int bowRow, int bowColumn, boolean horizontal){
    if (horizontal){
      if (this.getLength() + bowColumn > 9)
        return true;
    } else {
      if (this.getLength() + bowRow > 9)
        return true;
    }
    return false;
  }

  boolean shipSectionHasImmediatelyAdjacentShip(int cellRow, int cellColumn, Ocean ocean){
    int tempRow;
    int tempColumn;
    for(int r = -1; r <= 1; r++){
      for(int c = -1; c <= 1; c++){
        tempRow = cellRow + r;
        tempColumn = cellColumn + c;
        if ((tempRow >= 0 && tempRow < 9 )&&(tempColumn >= 0 && tempColumn < 9)){
          if (ocean.isOccupied(tempRow, tempColumn)){
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * placeShipAt() method places a Ship in the Ocean ( @param ocean) using the
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
   * shootAt() method is used for shooting at cell in the ships array in the Ocean
   * Object. It will shoot at the cell with coordinates provided in @param row
   * and @param column.
   * When shooting on a valid Ship object, the method calls automatically the
   * setHit() checkHits() methods and the @returns true, false otherwise.
   */
  boolean shootAt(int row, int column)  {
    for(int i = 0; i < this.getLength(); i++){
      if (isHorizontal()){
        if (getBowColumn() + i == column){
          setHit(i); // mark as hit
          checkHits();
          return true;
        }
      } else {
        if (getBowRow() + i == row){
          setHit(i); // mark as hit
          checkHits();
          return true;
        }
      }
    }
    return false;
  }

  /**
   * isASectionThatHasBeenAlreadyShot() receives @param row and @param column.
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

  boolean isSunk(){
    return sunk;
  }

  /**
   * checkHits() method checks if all the sections of the Ship have been hit,
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

  private void setSunk(boolean sunk){
    this.sunk = sunk;
  }

  @Override public String toString(){
    if (isSunk()) {
      return "X";
    }
    return "o"; // to be replaced with "."
  }

}
