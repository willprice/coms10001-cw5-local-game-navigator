package GameLogic;

public class Detective {

	private int undergroundTickets = 4;
	private int busTickets = 8;

	public int getUndergroundTokens() {
		return undergroundTickets;
	}

	public void move(Edge edge) {
		undergroundTickets -= 1;
		busTickets = 7;
	}

	public int getBusTokens() {
		return busTickets;
	}

}
