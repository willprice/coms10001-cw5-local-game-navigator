package org.willprice.scotlandyard.gamelogic.tickets;

import org.willprice.scotlandyard.gamelogic.Initialisable;
import org.willprice.scotlandyard.gamelogic.Initialisable.TicketType;

public abstract class Ticket {
	protected Initialisable.TicketType type;
	
	public TicketType getTicketType() {
		return type;
	}

}
