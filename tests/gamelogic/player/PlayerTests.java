package gamelogic.player;

import org.junit.Before;
import org.willprice.scotlandyard.gamelogic.graph.BusEdge;
import org.willprice.scotlandyard.gamelogic.graph.Edge;
import org.willprice.scotlandyard.gamelogic.graph.TaxiEdge;
import org.willprice.scotlandyard.gamelogic.graph.UndergroundEdge;
import org.willprice.scotlandyard.gamelogic.player.Player;

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