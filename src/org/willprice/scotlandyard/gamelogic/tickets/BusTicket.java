package org.willprice.scotlandyard.gamelogic.tickets;

import org.willprice.scotlandyard.gamelogic.Initialisable.TicketType;

public class BusTicket extends Ticket {
	public BusTicket() {
		this.type = TicketType.Bus;
	}

}
