package gamelogic.tickets;

import gamelogic.Initialisable.TicketType;

public class TaxiTicket extends Ticket {
	public TaxiTicket() {
		this.type = TicketType.Taxi;
	}

}
