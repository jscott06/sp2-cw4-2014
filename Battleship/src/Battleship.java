
public class Battleship extends Ship {
	
	Battleship(){
		setLength(4);
		initializeHitArray();
	}
	
	@Override 
	String getShipType() {
		return "battleship";
	}
	@Override 
	public String toString() {
		//TODO
		return "";
	}

}
