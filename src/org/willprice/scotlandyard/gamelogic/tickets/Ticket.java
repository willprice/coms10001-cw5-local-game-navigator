package org.willprice.scotlandyard.gamelogic.tickets;

import org.willprice.scotlandyard.gamelogic.Initialisable;
import org.willprice.scotlandyard.gamelogic.Initialisable.TicketType;

public class Ticket {
	protected Initialisable.TicketType type;

	public TicketType getTicketType() {
		return type;
	}

	public static Ticket newTicket(Initialisable.TicketType type) {
		switch (type) {
		case Bus:
			return new BusTicket();
		case Taxi:
			return new TaxiTicket();
		case Underground:
			return new UndergroundTicket();
		case DoubleMove:
			return new DoubleMoveTicket();
		case SecretMove:
			return new BlackTicket();
		}
		return null;
	}

}
