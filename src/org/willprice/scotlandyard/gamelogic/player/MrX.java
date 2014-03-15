package org.willprice.scotlandyard.gamelogic.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.willprice.scotlandyard.gamelogic.GameState;
import org.willprice.scotlandyard.gamelogic.Initialisable.TicketType;
import org.willprice.scotlandyard.gamelogic.graph.Edge;
import org.willprice.scotlandyard.gamelogic.tickets.BlackTicket;
import org.willprice.scotlandyard.gamelogic.tickets.BusTicket;
import org.willprice.scotlandyard.gamelogic.tickets.DoubleMoveTicket;
import org.willprice.scotlandyard.gamelogic.tickets.TaxiTicket;
import org.willprice.scotlandyard.gamelogic.tickets.Ticket;
import org.willprice.scotlandyard.gamelogic.tickets.UndergroundTicket;

public class MrX extends Player {

	private GameState gameState;
	private Stack<BlackTicket> blackTickets = new Stack<>();
	private Stack<DoubleMoveTicket> doubleMoveTickets = new Stack<>();
	private List<Edge> blackTicketEdges = new ArrayList<Edge>();
	public Stack<BusTicket> busTicketDiscardStack = new Stack<>();
	public Stack<TaxiTicket> taxiTicketDiscardStack = new Stack<>();
	public Stack<UndergroundTicket> undergroundTicketDiscardStack = new Stack<>();

	public MrX(GameState gameState) {
		this.gameState = gameState;
		playerId = 1;
		initTickets(5, blackTickets, BlackTicket.class);
		initTickets(2, doubleMoveTickets, DoubleMoveTicket.class);
	}

	public int getNumberOfBlackTickets() {
		return blackTickets.size();
	}

	public int getNumberOfDoubleMoveTickets() {
		return doubleMoveTickets.size();
	}

	public List<Edge> getBlackTicketEdges() {
		return blackTicketEdges;
	}

	@Override
	public boolean hasTicket(Ticket ticket) {
		switch (ticket.getTicketType()) {
		case DoubleMove:
			return doubleMoveTickets.size() > 0;
		case SecretMove:
			return blackTickets.size() > 0;
		case Bus:
			return busTicketDiscardStack.size() > 0;
		case Taxi:
			return taxiTicketDiscardStack.size() > 0;
		case Underground:
			return undergroundTicketDiscardStack.size() > 0;
		default:
			return false;
		}
	}

	@Override
	public int getNumberOfTickets(TicketType type) {
		switch (type) {
		case DoubleMove:
			return doubleMoveTickets.size();
		case SecretMove:
			return blackTickets.size();
		case Bus:
			return busTicketDiscardStack.size();
		case Taxi:
			return taxiTicketDiscardStack.size();
		case Underground:
			return undergroundTicketDiscardStack.size();
		default:
			return 0;
		}
	}

	@Override
	public void removeTicket(TicketType ticketType) {
		if (gameState.round == 1) {
			return;
		}
		switch (ticketType) {
		case DoubleMove:
			doubleMoveTickets.pop();
			break;
		case SecretMove:
			blackTickets.pop();
			break;
		case Bus:
			busTicketDiscardStack.pop();
			break;
		case Taxi:
			taxiTicketDiscardStack.pop();
			break;
		case Underground:
			undergroundTicketDiscardStack.pop();

		default:
			break;
		}
		
	}

	public void addTicketToDiscardStack(GameState gameState, TicketType ticketType) {
		switch (ticketType) {
		case Bus:
			busTicketDiscardStack.push(new BusTicket());
			break;
		case Taxi:
			taxiTicketDiscardStack.push(new TaxiTicket());
			break;
		case Underground:
			undergroundTicketDiscardStack.push(new UndergroundTicket());
			break;
		default:
			break;
		}
	}

}