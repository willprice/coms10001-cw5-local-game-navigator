package org.willprice.scotlandyard.gamelogic.tickets;

import org.willprice.scotlandyard.gamelogic.Initialisable.TicketType;

public class TaxiTicket extends Ticket {
	public TaxiTicket() {
		this.type = TicketType.Taxi;
	}

}
