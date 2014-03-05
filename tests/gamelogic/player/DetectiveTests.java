package gamelogic.player;


import static org.junit.Assert.*;
import gamelogic.graph.Edge;
import gamelogic.graph.TaxiEdge;
import gamelogic.graph.UndergroundEdge;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static junitparams.JUnitParamsRunner.$;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class DetectiveTests extends PlayerTests {
	@Override
	public void setUp() {
		player = new Detective();
	}

	@Test
	@Parameters
	public void detectivesMovesChangesTheNumberOfTokens(int expectedUndergroundTokens,
													  int expectedBusTokens,
													  int expectedTaxiTokens,
													  int numberOfMoves, Edge edge) throws Exception {
		movePlayer(numberOfMoves, edge);
		assertEquals(expectedTaxiTokens, getTaxiTickets());
		assertEquals(expectedBusTokens, getBusTickets());
		assertEquals(expectedUndergroundTokens, getUndergroundTickets());
		
	}

	private int getUndergroundTickets() {
		return ((Detective) player).getUndergroundTokens();
	}

	private int getBusTickets() {
		return ((Detective) player).getBusTokens();
	}

	private int getTaxiTickets() {
		return ((Detective) player).getTaxiTokens();
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
		movePlayer(1, createBusEdge());
		movePlayer(1, createUndergroundEdge());
		assertEquals(7, getBusTickets());
		assertEquals(3, getUndergroundTickets());
	}
	
	@Test
	public void movingAlongTaxiEdgeIsRecorded() throws Exception {
		TaxiEdge taxiEdge = createTaxiEdge();
		movePlayer(1, taxiEdge);
		assertEquals(taxiEdge, player.getMoveList().get(0));
	}
	
	@Test
	public void movingAlongUndergroundEdgeIsRecorded() throws Exception {
		Edge undergroundEdge = createUndergroundEdge();
		movePlayer(1, undergroundEdge);
		assertEquals(undergroundEdge, player.getMoveList().get(0));
		
	}
	
	@Test
	public void edgesAreRecordedInOrderOfTraversal() throws Exception {
		UndergroundEdge undergroundEdge = createUndergroundEdge();
		TaxiEdge taxiEdge = createTaxiEdge();
		movePlayer(3, undergroundEdge);
		movePlayer(1, taxiEdge);
		assertEquals(undergroundEdge, player.getMoveList().get(0));
		assertEquals(taxiEdge, player.getMoveList().get(3));
	}
	
}
