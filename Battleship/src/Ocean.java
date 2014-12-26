public class Ocean {
	
	private Ship[][] ships = new Ship[10][10];
	private int shotsFired;
	private int hitCount;
	private int shipsSunk;
	
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
				ships[column][row] = new EmptySea();
			}
		}
	}
	
	void placeAllShipsRandomly() {
		/* TODO
		Place all ten ships randomly on the (initially empty) ocean. 
		Place larger ships before smaller ones, or you may end up with 
		no legal place to put a large ship. 
		*/
	}
	
	boolean isOccupied(int row, int column) {
		/*
		Returns true if the given location contains
		a ship, false if it does not.
		*/
		System.out.println(getShipArray()[row][column].getShipType()); // for debugging TO REMOVE
		if (getShipArray()[row][column].getShipType() == "EmptySea") {
			return false;
		} else { 
			return true;
		}
	}
	
	boolean shootAt(int row, int column)  {
		
		/* TODO
		Returns true if the given location contains
		a real ship, still afloat, (not an EmptySea), false if it does not. In addition, this
		method updates the number of shots that have been fired, and the number of hits.
		Note: If a location contains a real ship, shootAt should return true every time
		the user shoots at that same location. Once a ship has been sunk, additional shots
		at its location should return false.
		*/
		return false;
	}
	
	int getShotsFired(){
		return shotsFired;
	}
	int getHitCount() {
		return hitCount;
	}
	int getShipsSunk(){
		return shipsSunk;
	}
	boolean isGameOver() {
		// TODO
		// Returns true if all ships have been sunk, otherwise false.
		return false;
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
	}

}
