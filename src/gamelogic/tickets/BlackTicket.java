package gamelogic.tickets;

import gamelogic.Initialisable.TicketType;

public class BlackTicket extends Ticket {
	public BlackTicket() {
		this.type = TicketType.SecretMove;
	}

}
