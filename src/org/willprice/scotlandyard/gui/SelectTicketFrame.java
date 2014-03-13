package org.willprice.scotlandyard.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import org.willprice.scotlandyard.gamelogic.tickets.BusTicket;
import org.willprice.scotlandyard.gamelogic.tickets.TaxiTicket;
import org.willprice.scotlandyard.gamelogic.tickets.Ticket;
import org.willprice.scotlandyard.gamelogic.tickets.UndergroundTicket;

public class SelectTicketFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = -4367478327450231739L;

	private JButton taxiTicketButton;
	private JButton busTicketButton;
	private JButton tubeTicketButton;

	private GUI gui;

	public SelectTicketFrame(GUI gui) {
		this.gui = gui;
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		add(panel);

		taxiTicketButton = new JButton("");
		taxiTicketButton.setIcon(new ImageIcon(getClass().getResource(
				"/taxi_ticket.png")));
		taxiTicketButton.addActionListener(this);
		panel.add(taxiTicketButton);

		busTicketButton = new JButton(new ImageIcon(getClass().getResource(
				"/bus_ticket.png")));
		busTicketButton.addActionListener(this);
		panel.add(busTicketButton);

		tubeTicketButton = new JButton(new ImageIcon(getClass().getResource(
				"/tube_ticket.png")));
		tubeTicketButton.addActionListener(this);
		panel.add(tubeTicketButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object button = e.getSource();
		Ticket ticket = null;
		if (button.equals(taxiTicketButton)) {
			ticket = new TaxiTicket();
		} else if (button.equals(busTicketButton)) {
			ticket = new BusTicket();
		} else if (button.equals(tubeTicketButton)) {
			ticket = new UndergroundTicket();
		}
		setVisible(false);
		gui.getMapPanel().movePlayer(ticket);
	}
}
