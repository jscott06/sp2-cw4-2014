
public class Submarine extends Ship{
	
	Submarine(){
		length = 1;
		initializeHitArray();
	}
	
	@Override String getShipType() {
		return "submarine";
	}

}
