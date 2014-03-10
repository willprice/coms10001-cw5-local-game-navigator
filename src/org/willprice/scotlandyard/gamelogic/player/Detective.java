package org.willprice.scotlandyard.gamelogic.player;

import java.util.Stack;

import org.willprice.scotlandyard.gamelogic.graph.Edge;
import org.willprice.scotlandyard.gamelogic.tickets.BusTicket;
import org.willprice.scotlandyard.gamelogic.tickets.TaxiTicket;
import org.willprice.scotlandyard.gamelogic.tickets.UndergroundTicket;

public class Detective extends Player {

	public Stack<BusTicket> busTickets;
	public Stack<TaxiTicket> taxiTickets;
	public Stack<UndergroundTicket> undergroundTickets;

	public Detective() {
		busTickets = new Stack<>();
		taxiTickets = new Stack<>();
		undergroundTickets = new Stack<>();
		initTickets(11, taxiTickets, TaxiTicket.class);
		initTickets(8, busTickets, BusTicket.class);
		initTickets(4, undergroundTickets, UndergroundTicket.class);
	}

	public Detective(int playerId) {
		this();
		this.playerId = playerId;
	}

	public int getUndergroundTokens() {
		return undergroundTickets.size();
	}

	public int getBusTokens() {
		return busTickets.size();
	}

	public int getTaxiTokens() {
		return taxiTickets.size();
	}

	protected void useTaxiTicket() {
		taxiTickets.pop();
	}

	protected void useBusTicket() {
		busTickets.pop();
	}

	protected void useUndergroundTicket() {
		undergroundTickets.pop();
	}

	@Override
	public void move(Edge edge) {
		super.move(edge);
		removeTicket(edge);
	}

	private void removeTicket(Edge edge) {
		switch (edge.type()) {
			case Underground: 
				useUndergroundTicket();
				break;
			case Bus: 
				useBusTicket();
				break;
			case Taxi: 
				useTaxiTicket();
				break;
		}
	}
}