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
		// shoot on sea
		assertEquals(false, ocean.shootAt(0, 0));
		assertEquals(1, ocean.getShotsFired());
		
		// shoot and sink submarine
		assertEquals(true, ocean.shootAt(1, 0));
		assertEquals(2, ocean.getShotsFired());
		assertEquals(1, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());
		
		// shoot on sunk submarine
		assertEquals(false, ocean.shootAt(1, 0));
		assertEquals(3, ocean.getShotsFired());
		assertEquals(1, ocean.getHitCount());
		
		// shoot on battleship
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
		
		// sink submarines
		assertEquals(true, ocean.shootAt(0, 0));
		assertEquals(true, ocean.shootAt(1, 0));
		assertEquals(true, ocean.shootAt(2, 0));
		assertEquals(true, ocean.shootAt(3, 0));
		
		// sink destroyers
		assertEquals(true, ocean.shootAt(7, 0));
		assertEquals(true, ocean.shootAt(7, 1));
		assertEquals(true, ocean.shootAt(8, 0));
		assertEquals(true, ocean.shootAt(8, 1));
		assertEquals(true, ocean.shootAt(9, 0));
		assertEquals(true, ocean.shootAt(9, 1));
		
		// sink cruisers
		assertEquals(true, ocean.shootAt(5, 0));
		assertEquals(true, ocean.shootAt(5, 1));
		assertEquals(true, ocean.shootAt(5, 2));
		assertEquals(true, ocean.shootAt(6, 0));
		assertEquals(true, ocean.shootAt(6, 1));
		assertEquals(true, ocean.shootAt(6, 2));
		
		// sink battleship
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
		
		// horizontal ship
		assertEquals(false, ocean.isHitSection(5, 1));
		assertEquals(true, ocean.shootAt(5, 1));
		assertEquals(true, ocean.shootAt(5, 2));
		assertEquals(true, ocean.isHitSection(5, 1));
		assertEquals(true, ocean.isHitSection(5, 2));

		// vertical ship
		assertEquals(false, ocean.isHitSection(0, 0));
		assertEquals(true, ocean.shootAt(1, 0));
		assertEquals(true, ocean.shootAt(2, 0));
		assertEquals(true, ocean.isHitSection(1, 0));
		assertEquals(true, ocean.isHitSection(2, 0));
		
	}
		
}
