package gamelogic.player;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

import gamelogic.GameState;
import gamelogic.graph.Edge;
import gamelogic.graph.UndergroundEdge;
import gamelogic.tickets.BlackTicket;
import gamelogic.tickets.DoubleMoveTicket;
import gamelogic.tickets.Ticket;

public class MrX extends Player {

	private GameState gameState;
	private Stack<BlackTicket> blackTickets = new Stack<>();
	private Stack<DoubleMoveTicket> doubleMoveTickets = new Stack<>();
	private List<Edge> blackTicketEdges = Collections.synchronizedList(new ArrayList<Edge>());

	public MrX(GameState gameState) {
		this.gameState = gameState;
		initTickets(5, blackTickets, BlackTicket.class);
		initTickets(2, doubleMoveTickets, DoubleMoveTicket.class);
	}

	public MrX(GameState gameState, int playerId) {
		this(gameState);
	}

	public int getNumberOfBlackTickets() {
		return blackTickets.size();
	}

	public int getNumberOfDoubleMoveTickets() {
		return doubleMoveTickets.size();
	}
	
	public void move(Edge edge, Ticket ticket) {
		super.move(edge);
		switch (ticket.getTicketType()) {
			case SecretMove:
				blackTickets.pop();
				blackTicketEdges.add(edge);
		}
	}

	public void move(Edge firstEdge, Edge secondEdge, DoubleMoveTicket ticket) {
		super.move(firstEdge);
		super.move(secondEdge);
		doubleMoveTickets.pop();
	}

	public List<Edge> getBlackTicketEdges() {
		return blackTicketEdges;
	}
}