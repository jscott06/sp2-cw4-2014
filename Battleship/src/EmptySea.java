
public class EmptySea extends Ship {
	
	@Override boolean shootAt(int row, int column) {
		/*
		This method overrides shootAt(int row, int column) that is inherited from Ship, 
		and always returns false to indicate that nothing was hit
		 */
		return false;
	}
	@Override boolean isSunk() {
		/*
		This method overrides isSunk() that is inherited from Ship, 
		and always returns false to indicate that you didn’t sink anything.
		 */
		return false;
	}
	@Override public String toString() {
		//TODO 
		// Returns a single-character String to use in the Ocean’s print method
		return "";
	}
	
	@Override String getShipType() {
		return "EmptySea";
	}
}
