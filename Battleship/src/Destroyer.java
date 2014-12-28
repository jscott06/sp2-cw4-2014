
public class Destroyer extends Ship {
	
	Destroyer(){
		length = 2;
		initializeHitArray();
	}
	
	@Override String getShipType() {
		return "destroyer";
	}
}
