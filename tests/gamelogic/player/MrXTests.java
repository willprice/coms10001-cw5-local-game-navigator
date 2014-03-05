package gamelogic.player;


import static org.junit.Assert.*;
import gamelogic.graph.TaxiEdge;
import gamelogic.graph.UndergroundEdge;
import gamelogic.tickets.BlackTicket;
import gamelogic.tickets.DoubleMoveTicket;

import org.junit.Before;
import org.junit.Test;

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
		mrX().move(createUndergroundEdge(), createUndergroundEdge(), new DoubleMoveTicket());
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
