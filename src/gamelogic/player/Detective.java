package gamelogic.player;

import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import scotlandyard.Game;
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
		initTickets(11, taxiTickets, TaxiTicket.class);
		initTickets(8, busTickets, BusTicket.class);
		initTickets(4, undergroundTickets, UndergroundTicket.class);
	}

	private <T> void initTickets(int numberOfTickets, Stack<T> tickets, Class<T> ticket) {
		for (; numberOfTickets > 0; numberOfTickets --) {
				try {
					tickets.push(ticket.newInstance());
				} catch (InstantiationException | IllegalAccessException e) {
					Logger.getLogger(Game.class.getName()).log(Level.SEVERE, "Cannot add tickets to detective");
				}
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
