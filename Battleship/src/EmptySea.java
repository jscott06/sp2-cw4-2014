
public class EmptySea extends Ship {
	
	boolean shotAt;
	
	@Override 
	boolean shootAt(int row, int column){
		shotAt = true;
		return false;
		
	}
	@Override 
	public String toString(){
		if (shotAt) {
			return "-";
		}
		return ".";
		
	}
	@Override String getShipType() {
		return "EmptySea";
	}
}
