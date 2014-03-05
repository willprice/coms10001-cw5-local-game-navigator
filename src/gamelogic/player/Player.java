package gamelogic.player;

import gamelogic.graph.Edge;
import gamelogic.graph.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import scotlandyard.Game;

public abstract class Player {

	protected int playerId;
	protected List<Edge> moves = Collections.synchronizedList(new ArrayList<Edge>());
	private Node node;

	protected <T> void initTickets(int numberOfTickets, Stack<T> tickets, Class<T> ticket) {
		for (; numberOfTickets > 0; numberOfTickets --) {
				try {
					tickets.push(ticket.newInstance());
				} catch (InstantiationException | IllegalAccessException e) {
					Logger.getLogger(Game.class.getName()).log(Level.SEVERE, "Cannot add tickets to detective");
				}
		}
	}

	public int getPlayerId() {
		return playerId;
	}

	public List<Edge> getMoveList() {
		return moves;
	}
	
	public void move(Edge edge) {
		moves.add(edge);
	}

	public void setPosition(Node node) {
		this.node = node;
	}
}