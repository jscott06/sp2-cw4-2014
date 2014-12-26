
public class Ship {
	private int bowRow;
	private int bowColumn;
	private int length;
	private boolean horizontal = false;
	private boolean [] hit;
	
	void initializeHitArray(){	
		hit = new boolean[getLength()];
		for(int i = 0; i < hit.length; i++){
			hit[i] = false;
		}
	}
	boolean [] getHits(){
		return hit;
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
	
	void setLength(int length){
		this.length = length;
	}
	
	void setHorizontal(boolean horizontal){
		this.horizontal = horizontal;
	}
	
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean){
		/*
		Returns true if it is okay to put a ship of this length with its bow in this location,
		with the given orientation, and returns false otherwise. The ship must not overlap
		another ship, or touch another ship (vertically, horizontally, or diagonally), and it
		must not stick out beyond the array. Does not actually change either the ship or
		the Ocean, just says whether it is legal to do so.
		 */
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
	
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean){
		/* 
		Puts the ship in the ocean. This involves giving values to the bowRow, bowColumn, and
		horizontal instance variables in the ship, and it also involves putting a reference
		to the ship in each of 1 or more locations (up to 4) in the ships array in the Ocean
		object. (Note: This will be as many as four identical references; you can’t refer to
		a part of a ship, only to the whole ship.)
		*/
		for(int i = 0; i < this.getLength(); i++){
			if (horizontal == true){
				ocean.getShipArray()[row][column + i] = this;
			} else {
				ocean.getShipArray()[row + i][column] = this;
			}
		}
	}
	
	boolean shootAt(int row, int column)  {
		// TODO
		/* 
		If a part of the ship occupies the given row
		and column, and the ship hasn’t been sunk, mark that part of the ship as hit (in
		the hit array, 0 indicates the bow) and return true, otherwise return false.
		*/
		return false;
	}
	
	boolean isSunk(){
		// TODO
		// Return true if every part of the ship has been hit, false otherwise.
		return false;
	}
}
