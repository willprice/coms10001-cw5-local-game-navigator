package org.willprice.scotlandyard.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import org.willprice.scotlandyard.gamelogic.tickets.BusTicket;
import org.willprice.scotlandyard.gamelogic.tickets.TaxiTicket;
import org.willprice.scotlandyard.gamelogic.tickets.Ticket;
import org.willprice.scotlandyard.gamelogic.tickets.UndergroundTicket;

public class SelectTicketTypePanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = -4367478327450231739L;
	
	private SelectTicketTypePanelDelegate delegate;

	private JButton taxiTicketButton;
	private JButton busTicketButton;
	private JButton tubeTicketButton;

	public SelectTicketTypePanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		Box horizontalBox = Box.createHorizontalBox();
		add(horizontalBox);
		
		taxiTicketButton = new JButton("");
		taxiTicketButton.setIcon(new ImageIcon(getClass().getResource("/taxi_ticket.png")));
		taxiTicketButton.addActionListener(this);
		add(taxiTicketButton);
		
		busTicketButton = new JButton(new ImageIcon(getClass().getResource("/bus_ticket.png")));
		busTicketButton.addActionListener(this);
		add(busTicketButton);
		
		tubeTicketButton = new JButton(new ImageIcon(getClass().getResource("/tube_ticket.png")));
		tubeTicketButton.addActionListener(this);
		add(tubeTicketButton);
	}
	
	public SelectTicketTypePanel(SelectTicketTypePanelDelegate delegate) {
		this();
		setDelegate(delegate);
	}

	public void setDelegate(SelectTicketTypePanelDelegate delegate) {
		this.delegate = delegate;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		Ticket ticket = null;
		if (source.equals(taxiTicketButton)) {
			ticket = new TaxiTicket();
		} else if (source.equals(busTicketButton)) {
			ticket = new BusTicket();
		} else if (source.equals(tubeTicketButton)) {
			ticket = new UndergroundTicket();
		}
		
		delegate.ticketTypePanelSelected(ticket);
	}
}
