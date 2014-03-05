package gamelogic.player;

import gamelogic.graph.BusEdge;
import gamelogic.graph.Edge;
import gamelogic.graph.TaxiEdge;
import gamelogic.graph.UndergroundEdge;

import org.junit.Before;

public abstract class PlayerTests {

	protected Player player;

	public PlayerTests() {
		super();
	}

	@Before
	public abstract void setUp();


	protected void movePlayer(int numberOfMoves, Edge edge) {
		for (int i = 0; i < numberOfMoves; i++) {
			player.move(edge);
		}
	}

	protected TaxiEdge createTaxiEdge() {
		return new TaxiEdge(null, null, 0, null);
	}

	protected UndergroundEdge createUndergroundEdge() {
		return new UndergroundEdge(null, null, 0, null);
	}

	protected BusEdge createBusEdge() {
		return new BusEdge(null, null, 0, null);
	}

}