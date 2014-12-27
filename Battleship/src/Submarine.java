
public class Submarine extends Ship{
	
	Submarine(){
		length = 1;
		initializeHitArray();
	}
	
	@Override String getShipType() {
		return "submarine";
	}
	@Override public String toString() {
		//TODO
		return "";
	}

}
