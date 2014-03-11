package org.willprice.scotlandyard.gamelogic.player;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

import org.willprice.scotlandyard.gamelogic.GameState;
import org.willprice.scotlandyard.gamelogic.graph.Edge;
import org.willprice.scotlandyard.gamelogic.graph.UndergroundEdge;
import org.willprice.scotlandyard.gamelogic.tickets.BlackTicket;
import org.willprice.scotlandyard.gamelogic.tickets.DoubleMoveTicket;
import org.willprice.scotlandyard.gamelogic.tickets.Ticket;

public class MrX extends Player {

	private GameState gameState;
	private Stack<BlackTicket> blackTickets = new Stack<>();
	private Stack<DoubleMoveTicket> doubleMoveTickets = new Stack<>();
	private List<Edge> blackTicketEdges = new ArrayList<Edge>();

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