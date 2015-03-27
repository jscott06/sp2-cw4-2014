import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OceanTest {
  @Test
  public void testIsOccupiedAndPlaceShipAt() {
    Ocean ocean = new Ocean();
    Submarine submarine = new Submarine();
    Destroyer destroyer = new Destroyer();
    Cruiser cruiser = new Cruiser();
    Battleship battleship = new Battleship();

    assertEquals(false, ocean.isOccupied(0, 0));

    assertEquals(false, ocean.isOccupied(1, 0));

    assertEquals(false, ocean.isOccupied(5, 0));
    assertEquals(false, ocean.isOccupied(5, 1));

    assertEquals(false, ocean.isOccupied(5, 2));

    assertEquals(false, ocean.isOccupied(2, 5));
    assertEquals(false, ocean.isOccupied(3, 5));
    assertEquals(false, ocean.isOccupied(4, 5));

    assertEquals(false, ocean.isOccupied(5, 5));

    assertEquals(false, ocean.isOccupied(5, 7));
    assertEquals(false, ocean.isOccupied(6, 7));
    assertEquals(false, ocean.isOccupied(7, 7));
    assertEquals(false, ocean.isOccupied(8, 7));

    assertEquals(false, ocean.isOccupied(9, 7));

    submarine.placeShipAt(1, 0, true, ocean);
    destroyer.placeShipAt(5, 0, true, ocean);
    cruiser.placeShipAt(2, 5, false, ocean);
    battleship.placeShipAt(5, 7, false, ocean);

    assertEquals(false, ocean.isOccupied(0, 0));

    assertEquals(true, ocean.isOccupied(1, 0));

    assertEquals(true, ocean.isOccupied(5, 0));
    assertEquals(true, ocean.isOccupied(5, 1));

    assertEquals(false, ocean.isOccupied(5, 2));

    assertEquals(true, ocean.isOccupied(2, 5));
    assertEquals(true, ocean.isOccupied(3, 5));
    assertEquals(true, ocean.isOccupied(4, 5));

    assertEquals(false, ocean.isOccupied(5, 5));

    assertEquals(true, ocean.isOccupied(5, 7));
    assertEquals(true, ocean.isOccupied(6, 7));
    assertEquals(true, ocean.isOccupied(7, 7));
    assertEquals(true, ocean.isOccupied(8, 7));

    assertEquals(false, ocean.isOccupied(9, 7));
  }

  @Test
  public void shootAt() {

    Ocean ocean = new Ocean();
    Submarine submarine = new Submarine();
    Destroyer destroyer = new Destroyer();
    Cruiser cruiser = new Cruiser();
    Battleship battleship = new Battleship();

    submarine.placeShipAt(1, 0, true, ocean);
    destroyer.placeShipAt(5, 0, true, ocean);
    cruiser.placeShipAt(2, 5, false, ocean);
    battleship.placeShipAt(5, 7, false, ocean);

    assertEquals(0, ocean.getShotsFired());
    assertEquals(0, ocean.getShipsSunk());

    assertEquals(false, ocean.shootAt(0, 0));
    assertEquals(1, ocean.getShotsFired());

    assertEquals(true, ocean.shootAt(1, 0));
    assertEquals(2, ocean.getShotsFired());
    assertEquals(1, ocean.getHitCount());
    assertEquals(1, ocean.getShipsSunk());

    assertEquals(false, ocean.shootAt(1, 0));
    assertEquals(3, ocean.getShotsFired());
    assertEquals(1, ocean.getHitCount());

    assertEquals(true, ocean.shootAt(5, 7));
    assertEquals(true, ocean.shootAt(6, 7));
    assertEquals(true, ocean.shootAt(7, 7));
    assertEquals(true, ocean.shootAt(8, 7));
    assertEquals(7, ocean.getShotsFired());
    assertEquals(5, ocean.getHitCount());
    assertEquals(2, ocean.getShipsSunk());
  }

  @Test
  public void isGameOver() {
    Ocean ocean = new Ocean();

    Submarine submarine1 = new Submarine();
    Submarine submarine2 = new Submarine();
    Submarine submarine3 = new Submarine();
    Submarine submarine4 = new Submarine();

    Destroyer destroyer1 = new Destroyer();
    Destroyer destroyer2 = new Destroyer();
    Destroyer destroyer3 = new Destroyer();

    Cruiser cruiser1 = new Cruiser();
    Cruiser cruiser2 = new Cruiser();

    Battleship battleship = new Battleship();

    submarine1.placeShipAt(0, 0, true, ocean);
    submarine2.placeShipAt(1, 0, true, ocean);
    submarine3.placeShipAt(2, 0, true, ocean);
    submarine4.placeShipAt(3, 0, true, ocean);

    cruiser1.placeShipAt(5, 0, true, ocean);
    cruiser2.placeShipAt(6, 0, true, ocean);

    destroyer1.placeShipAt(7, 0, true, ocean);
    destroyer2.placeShipAt(8, 0, true, ocean);
    destroyer3.placeShipAt(9, 0, true, ocean);

    battleship.placeShipAt(0, 9, false, ocean);

    assertEquals(false, ocean.isGameOver());

    assertEquals(true, ocean.shootAt(0, 0));
    assertEquals(true, ocean.shootAt(1, 0));

    assertEquals(true, ocean.shootAt(2, 0));
    assertEquals(true, ocean.shootAt(3, 0));

    assertEquals(true, ocean.shootAt(7, 0));
    assertEquals(true, ocean.shootAt(7, 1));

    assertEquals(true, ocean.shootAt(8, 0));
    assertEquals(true, ocean.shootAt(8, 1));

    assertEquals(true, ocean.shootAt(9, 0));
    assertEquals(true, ocean.shootAt(9, 1));

    assertEquals(true, ocean.shootAt(5, 0));
    assertEquals(true, ocean.shootAt(5, 1));
    assertEquals(true, ocean.shootAt(5, 2));

    assertEquals(true, ocean.shootAt(6, 0));
    assertEquals(true, ocean.shootAt(6, 1));
    assertEquals(true, ocean.shootAt(6, 2));

    assertEquals(true, ocean.shootAt(0, 9));
    assertEquals(true, ocean.shootAt(1, 9));
    assertEquals(true, ocean.shootAt(2, 9));
    assertEquals(true, ocean.shootAt(3, 9));

    assertEquals(true, ocean.isGameOver());
  }

  @Test
  public void isHitSection() {
    Ocean ocean = new Ocean();

    Cruiser cruiser1 = new Cruiser();
    Cruiser cruiser2 = new Cruiser();
    cruiser1.placeShipAt(5, 0, true, ocean);
    cruiser2.placeShipAt(0, 0, false, ocean);

    assertEquals(false, ocean.isHitSection(5, 1));
    assertEquals(true, ocean.shootAt(5, 1));
    assertEquals(true, ocean.shootAt(5, 2));
    assertEquals(true, ocean.isHitSection(5, 1));
    assertEquals(true, ocean.isHitSection(5, 2));

    assertEquals(false, ocean.isHitSection(0, 0));
    assertEquals(true, ocean.shootAt(1, 0));
    assertEquals(true, ocean.shootAt(2, 0));
    assertEquals(true, ocean.isHitSection(1, 0));
    assertEquals(true, ocean.isHitSection(2, 0));
  }

}
