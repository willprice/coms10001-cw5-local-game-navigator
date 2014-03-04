package gamelogic.player;

import java.util.Stack;

import gamelogic.graph.Edge;
import gamelogic.tickets.BusTicket;
import gamelogic.tickets.TaxiTicket;
import gamelogic.tickets.Ticket;
import gamelogic.tickets.UndergroundTicket;

public class Detective {

	private Stack<BusTicket> busTickets;
	private Stack<TaxiTicket> taxiTickets;
	private Stack<UndergroundTicket> undergroundTickets;

	public Detective() {
		busTickets = new Stack<>();
		taxiTickets = new Stack<>();
		undergroundTickets = new Stack<>();
		for (int numberOfTickets = 11; numberOfTickets > 0; numberOfTickets --) {
			taxiTickets.push(new TaxiTicket());
		}
		for (int numberOfTickets = 8; numberOfTickets > 0; numberOfTickets --) {
			busTickets.push(new BusTicket());
		}
		for (int numberOfTickets = 4; numberOfTickets > 0; numberOfTickets --) {
			undergroundTickets.push(new UndergroundTicket());
		}
	}

	public void move(Edge edge) {
		switch (edge.type()) {
			case Underground: 
				undergroundTickets.pop();
				break;
			case Bus: 
				busTickets.pop();
				break;
			case Taxi: 
				taxiTickets.pop();
				break;
		}
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

}
