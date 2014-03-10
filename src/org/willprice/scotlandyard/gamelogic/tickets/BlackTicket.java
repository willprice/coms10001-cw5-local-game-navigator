package org.willprice.scotlandyard.gamelogic.tickets;

import org.willprice.scotlandyard.gamelogic.Initialisable.TicketType;

public class BlackTicket extends Ticket {
	public BlackTicket() {
		this.type = TicketType.SecretMove;
	}

}
