public class Cruiser extends Ship{

  Cruiser(){
    length = 3;
    initializeHitArray();
  }

  @Override String getShipType() {
    return "cruiser";
  }

}
