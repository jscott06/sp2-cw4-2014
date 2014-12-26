import static org.junit.Assert.*;

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
		
	}
	
	@Test
	public void testPlaceShipAt(){
		
		Ocean ocean = new Ocean();
		Submarine submarine = new Submarine();
		submarine.placeShipAt(0, 0, true, ocean);
		assertEquals(0, submarine.getBowColumn());
		assertEquals(0, submarine.getBowRow());
		
	}
	
	@Test
	public void testHit(){
		
	}
	
	@Test
	public void testIsSunk(){
		
	}

}
