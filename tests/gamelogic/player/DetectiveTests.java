package gamelogic.player;


import static org.junit.Assert.*;
import gamelogic.graph.BusEdge;
import gamelogic.graph.Edge;
import gamelogic.graph.TaxiEdge;
import gamelogic.graph.UndergroundEdge;
import gamelogic.player.Detective;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static junitparams.JUnitParamsRunner.$;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class DetectiveTests {
	private Detective detective;

	@Before
	public void setUp() {
		detective = new Detective();
	}

	@Test
	@Parameters
	public void detectivesMovesChangesTheNumberOfTokens(int expectedUndergroundTokens,
													  int expectedBusTokens,
													  int expectedTaxiTokens,
													  int numberOfMoves, Edge edge) throws Exception {
		moveDetective(numberOfMoves, edge);
		assertEquals(expectedTaxiTokens, detective.getTaxiTokens());
		assertEquals(expectedBusTokens, detective.getBusTokens());
		assertEquals(expectedUndergroundTokens, detective.getUndergroundTokens());
		
	}
	
	@SuppressWarnings("unused")
	private Object[] parametersForDetectivesMovesChangesTheNumberOfTokens() {
		return $(
				$(4, 8, 11, 0, createUndergroundEdge()),
				$(3, 8, 11, 1, createUndergroundEdge()),
				$(2, 8, 11, 2, createUndergroundEdge()),
				$(4, 7, 11, 1, createBusEdge()),
				$(4, 8, 10, 1, createTaxiEdge()),
				$(4, 8, 9, 2, createTaxiEdge())
				);
	}


	@Test
	public void moveOnBusAndUndergroundRemovesTokens() throws Exception {
		moveDetective(1, createBusEdge());
		moveDetective(1, createUndergroundEdge());
		assertEquals(7, detective.getBusTokens());
		assertEquals(3, detective.getUndergroundTokens());
	}
	
	private TaxiEdge createTaxiEdge() {
		return new TaxiEdge(null, null, 0, null);
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
