
public class Destroyer extends Ship {
	
	Destroyer(){
		setLength(2);
		initializeHitArray();
	}
	
	@Override String getShipType() {
		return "destroyer";
	}
	@Override public String toString() {
		//TODO
		return "";
	}
}
