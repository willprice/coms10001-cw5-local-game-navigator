package org.willprice.scotlandyard.gamelogic.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.willprice.scotlandyard.Game;
import org.willprice.scotlandyard.gamelogic.Initialisable;
import org.willprice.scotlandyard.gamelogic.Initialisable.TicketType;
import org.willprice.scotlandyard.gamelogic.graph.Edge;
import org.willprice.scotlandyard.gamelogic.graph.Node;
import org.willprice.scotlandyard.gamelogic.tickets.Ticket;

public abstract class Player {

	protected int playerId;
	protected List<TicketType> moves = new ArrayList<>();
	private Node node;

	protected <T> void initTickets(int numberOfTickets, Stack<T> tickets,
			Class<T> ticket) {
		for (; numberOfTickets > 0; numberOfTickets--) {
			try {
				tickets.push(ticket.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				Logger.getLogger(Game.class.getName()).log(Level.SEVERE,
						"Cannot add tickets to detective");
			}
		}
	}

	public int getPlayerId() {
		return playerId;
	}

	public List<TicketType> getMoveList() {
		return moves;
	}

	public void move(Edge edge, TicketType ticketType) {
		moves.add(ticketType);
		setPosition(new Node(edge.connectedTo(node.name())));
		removeTicket(ticketType);
	}

	abstract public void removeTicket(TicketType ticketType);

	public void setPosition(Node node) {
		this.node = node;
	}

	public Node getPosition() {
		return node;
	}

	abstract public boolean hasTicket(Ticket ticket);

	abstract public int getNumberOfTickets(Initialisable.TicketType type);

	public int getNumberOfMoves() {
		return moves.size();
	} 
}
