package GameLogic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DetectiveTests {
	private Detective detective;

	@Before
	public void setUp() {
		detective = new Detective();
	}

	@Test
	public void detectiveHasFourUndergroundTokensOnCreation() {
		assertEquals(4, detective.getUndergroundTokens());
	}

	@Test
	public void detectiveHasThreeUndergroundTokensAfterMoveOnUnderground() throws Exception {
		moveDetective(1, createUndergroundEdge());
		assertEquals(3, detective.getUndergroundTokens());
	}

	@Test
	public void detectiveHasTwoUndergroundTokensAfterMovingTwiceOnUnderground() throws Exception {
		moveDetective(2, createUndergroundEdge());
		assertEquals(2, detective.getUndergroundTokens());
	}

	@Test
	public void detectiveHasEightBusTicketsOnCreation() throws Exception {
		assertEquals(8, detective.getBusTokens());
	}
	
	@Test
	public void detectiveHasSevenBusTicketsAfterMovingOnBus() throws Exception {
		moveDetective(1, createBusEdge());
		assertEquals(7, detective.getBusTokens());
	}
	
	@Test
	public void detectiveHasSevenBusTicketsAndThreeUndergroundTicketsAfterMovingByBusAndUnderground() throws Exception {
		moveDetective(1, createUndergroundEdge());
		moveDetective(1, createBusEdge());
		assertEquals(3, detective.getUndergroundTokens());
		assertEquals(7, detective.getBusTokens());
	}

	private void moveDetective(int numberOfMoves, Edge edge) {
		for (int i = 0; i < numberOfMoves; i++) {
			detective.move(edge);
		}
	}

	private UndergroundEdge createUndergroundEdge() {
		return new UndergroundEdge(null, null, 0, null);
	}

	private BusEdge createBusEdge() {
		return new BusEdge(null, null, 0, null);
	}
	
}
