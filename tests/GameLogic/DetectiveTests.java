package GameLogic;

import static org.junit.Assert.*;
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
													  int numberOfMoves,
													  Edge edge) throws Exception {
		moveDetective(numberOfMoves, edge);
		assertEquals(expectedBusTokens, detective.getBusTokens());
		assertEquals(expectedUndergroundTokens, detective.getUndergroundTokens());
		
	}
	
	@SuppressWarnings("unused")
	private Object[] parametersForDetectivesMovesChangesTheNumberOfTokens() {
		return $(
				$(4, 8, 0, createUndergroundEdge()),
				$(3, 8, 1, createUndergroundEdge()),
				$(2, 8, 2, createUndergroundEdge()),
				$(4, 7, 1, createBusEdge())
				);
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
