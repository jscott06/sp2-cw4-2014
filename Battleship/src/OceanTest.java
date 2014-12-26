import static org.junit.Assert.*;

import org.junit.Test;


public class OceanTest {
	@Test
	public void testIsOccupiedAndPlaceShipAt() {
		Ocean ocean = new Ocean();
		Submarine submarine = new Submarine();
		Destroyer destroyer = new Destroyer();
		Cruiser cruiser = new Cruiser();
		Battleship battleship = new Battleship();
		
		assertEquals(false, ocean.isOccupied(0, 0)); // sea
		assertEquals(false, ocean.isOccupied(1, 0)); // will be submarine H
		assertEquals(false, ocean.isOccupied(5, 0)); // will be destroyer H
		assertEquals(false, ocean.isOccupied(5, 1)); // will be destroyer H
		assertEquals(false, ocean.isOccupied(5, 2)); // sea
		assertEquals(false, ocean.isOccupied(2, 5)); // will be cruiser V
		assertEquals(false, ocean.isOccupied(3, 5)); // will be cruiser V
		assertEquals(false, ocean.isOccupied(4, 5)); // will be cruiser V
		assertEquals(false, ocean.isOccupied(5, 5)); // sea
		assertEquals(false, ocean.isOccupied(5, 7)); // will be battleship V
		assertEquals(false, ocean.isOccupied(6, 7)); // will be battleship V
		assertEquals(false, ocean.isOccupied(7, 7)); // will be battleship V
		assertEquals(false, ocean.isOccupied(8, 7)); // will be battleship V
		assertEquals(false, ocean.isOccupied(9, 7)); // sea
		
		submarine.placeShipAt(1, 0, true, ocean);
		destroyer.placeShipAt(5, 0, true, ocean);
		cruiser.placeShipAt(2, 5, false, ocean);
		battleship.placeShipAt(5, 7, false, ocean);
		
		assertEquals(false, ocean.isOccupied(0, 0)); // sea
		assertEquals(true, ocean.isOccupied(1, 0)); // submarine H
		assertEquals(true, ocean.isOccupied(5, 0)); // destroyer H
		assertEquals(true, ocean.isOccupied(5, 1)); // destroyer H
		assertEquals(false, ocean.isOccupied(5, 2)); // sea
		assertEquals(true, ocean.isOccupied(2, 5)); // cruiser V
		assertEquals(true, ocean.isOccupied(3, 5)); // cruiser V
		assertEquals(true, ocean.isOccupied(4, 5)); // cruiser V
		assertEquals(false, ocean.isOccupied(5, 5)); // sea
		assertEquals(true, ocean.isOccupied(5, 7)); // will be battleship V
		assertEquals(true, ocean.isOccupied(6, 7)); // will be battleship V
		assertEquals(true, ocean.isOccupied(7, 7)); // will be battleship V
		assertEquals(true, ocean.isOccupied(8, 7)); // will be battleship V
		assertEquals(false, ocean.isOccupied(9, 7)); // sea
		
	}
}
