package gamelogic.player;

import gamelogic.graph.Edge;

public class Detective {

	private int undergroundTickets = 4;
	private int busTickets = 8;
	private int taxiTokens = 11;


	public void move(Edge edge) {
		switch (edge.type()) {
			case Underground: 
				undergroundTickets -= 1;
				break;
			case Bus: 
				busTickets -= 1;
				break;
			case Taxi: 
				taxiTokens -= 1;
				break;
		}
	}

	public int getUndergroundTokens() {
		return undergroundTickets;
	}

	public int getBusTokens() {
		return busTickets;
	}

	public int getTaxiTokens() {
		return taxiTokens;
	}

}
