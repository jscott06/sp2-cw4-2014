import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ShipTest {

  @Test
  public void testCorrectInstantiation() {
    Battleship battleship = new Battleship();
    assertEquals(4, battleship.getLength());
    assertEquals(4, battleship.getHits().length);
    assertEquals(false, battleship.getHits()[0]);
    assertEquals(false, battleship.getHits()[3]);

    Cruiser cruiser = new Cruiser();
    assertEquals(3, cruiser.getLength());
    assertEquals(false, battleship.getHits()[2]);

    Destroyer destroyer = new Destroyer();
    assertEquals(2, destroyer.getLength());
    assertEquals(false, battleship.getHits()[1]);

    Submarine submarine = new Submarine();
    assertEquals(1, submarine.getLength());
    assertEquals(false, battleship.getHits()[0]);
  }

  @Test
  public void testOkToPlaceShipAt(){

    Ocean ocean = new Ocean();
    Battleship battleship = new Battleship();

    assertEquals(true, battleship.okToPlaceShipAt(0, 0, true, ocean));

    assertEquals(false, battleship.okToPlaceShipAt(0, 9, true, ocean));
    assertEquals(false, battleship.okToPlaceShipAt(9, 0, false, ocean));

    Battleship battleship2 = new Battleship();
    assertEquals(true, battleship2.okToPlaceShipAt(0, 2, true, ocean));
    assertEquals(true, battleship2.okToPlaceShipAt(0, 2, false, ocean));

    battleship.placeShipAt(0, 0, true, ocean);

    assertEquals(false, battleship2.okToPlaceShipAt(0, 2, true, ocean));
    assertEquals(false, battleship2.okToPlaceShipAt(0, 2, false, ocean));
  }

  @Test
  public void TestFallsOutOfTheBoard(){
    Ocean ocean = new Ocean();
    Cruiser cruiser = new Cruiser();
    assertEquals(false, cruiser.fallsOutOfTheBoard(3, 5, false, ocean));
    assertEquals(true, cruiser.fallsOutOfTheBoard(9, 5, false, ocean));
    assertEquals(false, cruiser.fallsOutOfTheBoard(3, 5, true, ocean));
    assertEquals(true, cruiser.fallsOutOfTheBoard(3, 9, true, ocean));
  }

  @Test
  public void TestShipSectionHasImmediatelyAdjacentShip(){
    Ocean ocean = new Ocean();
    Cruiser cruiser1 = new Cruiser();
    cruiser1.placeShipAt(0, 0, true, ocean);
    Cruiser cruiser2 = new Cruiser();
    assertEquals(true, cruiser2.shipSectionHasImmediatelyAdjacentShip(1, 1, ocean));
    assertEquals(false, cruiser2.shipSectionHasImmediatelyAdjacentShip(5, 5, ocean));
  }

  @Test
  public void testPlaceShipAt(){
    Ocean ocean = new Ocean();
    Submarine submarine = new Submarine();
    submarine.placeShipAt(1, 0, true, ocean);
    assertEquals(1, submarine.getBowRow());
    assertEquals(0, submarine.getBowColumn());

    Cruiser cruiser = new Cruiser();
    cruiser.placeShipAt(3, 5, false, ocean);
    assertEquals(3, cruiser.getBowRow());
    assertEquals(5, cruiser.getBowColumn());
  }

  @Test
  public void testHitAndSunk(){
    Ocean ocean = new Ocean();

    Battleship battleship = new Battleship();
    battleship.placeShipAt(0, 0, true, ocean);

    Cruiser cruiser = new Cruiser();
    cruiser.placeShipAt(3, 5, false, ocean);

    assertEquals(false, battleship.isSunk());
    assertEquals(false, cruiser.isSunk());

    assertEquals(false, ocean.isOccupied(2, 5));
    assertEquals(false, battleship.shootAt(2, 5));

    assertEquals(true, battleship.shootAt(0, 0));
    assertEquals(true, battleship.shootAt(0, 1));
    assertEquals(true, battleship.shootAt(0, 2));
    assertEquals(true, battleship.shootAt(0, 3));
    assertEquals(true, battleship.isSunk());

    assertEquals(true, cruiser.shootAt(3, 5));
    assertEquals(true, cruiser.shootAt(4, 5));
    assertEquals(true, cruiser.shootAt(5, 5));
    assertEquals(true, cruiser.isSunk());
  }

  @Test
  public void testPrinting(){
    Ocean ocean = new Ocean();
    Battleship battleship = new Battleship();

    assertEquals(".", ocean.getShipArray()[0][0].toString());

    ocean.shootAt(0, 0);

    assertEquals("-", ocean.getShipArray()[0][0].toString());

    battleship.placeShipAt(0, 0, true, ocean);

    assertEquals(".", ocean.getShipArray()[0][0].toString());

    battleship.shootAt(0, 0);
    battleship.shootAt(0, 1);
    battleship.shootAt(0, 2);
    battleship.shootAt(0, 3);

    assertEquals("X", ocean.getShipArray()[0][0].toString());
  }

}
