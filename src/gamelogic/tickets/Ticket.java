package gamelogic.tickets;

import gamelogic.Initialisable;
import gamelogic.Initialisable.TicketType;

public abstract class Ticket {
	protected Initialisable.TicketType type;
	
	public TicketType getTicketType() {
		return type;
	}

}
