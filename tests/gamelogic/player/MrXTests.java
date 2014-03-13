package gamelogic.player;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.willprice.scotlandyard.gamelogic.graph.TaxiEdge;
import org.willprice.scotlandyard.gamelogic.graph.UndergroundEdge;
import org.willprice.scotlandyard.gamelogic.player.MrX;
import org.willprice.scotlandyard.gamelogic.tickets.BlackTicket;
import org.willprice.scotlandyard.gamelogic.tickets.DoubleMoveTicket;

public class MrXTests extends PlayerTests {
	@Override
	public void setUp() {
		player = new MrX(null);
	}

	@Test
	public void initializedWithCorrectTickets() {
		assertEquals(5, getNumberOfBlackTickets());
		assertEquals(2, getNumberOfDoubleMoveTickets());
	}

	private int getNumberOfDoubleMoveTickets() {
		return mrX().getNumberOfDoubleMoveTickets();
	}

	private int getNumberOfBlackTickets() {
		return mrX().getNumberOfBlackTickets();
	}

	@Test
	public void movingMrXAlongUndergroundEdgeByBlackTicket() throws Exception {
		mrX().move(new UndergroundEdge(null, null, 0), new BlackTicket());
		assertEquals(4, getNumberOfBlackTickets());
	}

	@Test
	public void movingMrXAlongUndergroundEdgesByDoubleMoveTicket() {
		mrX().move(createUndergroundEdge(), createUndergroundEdge(),
				new DoubleMoveTicket());
		assertEquals(1, getNumberOfDoubleMoveTickets());
	}

	@Test
	public void moveWithBlackTicketGetsRecorded() throws Exception {
		TaxiEdge taxiEdge = createTaxiEdge();
		mrX().move(taxiEdge, new BlackTicket());
		assertEquals(taxiEdge, mrX().getBlackTicketEdges().get(0));
	}

	private MrX mrX() {
		return (MrX) player;
	}

}
