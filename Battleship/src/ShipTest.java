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

}
