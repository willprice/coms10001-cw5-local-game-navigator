package org.willprice.scotlandyard.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.willprice.scotlandyard.gui.tickets.BlackTicketLabel;
import org.willprice.scotlandyard.gui.tickets.BusTicketLabel;
import org.willprice.scotlandyard.gui.tickets.DoubleMoveTicketLabel;
import org.willprice.scotlandyard.gui.tickets.TaxiTicketLabel;
import org.willprice.scotlandyard.gui.tickets.UndergroundTicketLabel;

public class InformationPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = -5187449302850290709L;
	private GUI gui;
	private JLabel currentPlayer;
	private DoubleMoveTicketLabel doubleMoveTickets;
	private BlackTicketLabel blackTickets;
	private BusTicketLabel busMoveTickets;
	private TaxiTicketLabel taxiTickets;
	private UndergroundTicketLabel undergroundTickets;

	public InformationPanel(GUI gui) {
		this.gui = gui;

		createPersistenceButtons();
		createCurrentPlayerLabel();
		createPlayerTicketsInformation();
	}

	private void createCurrentPlayerLabel() {
		currentPlayer = new JLabel("Current Player: " + gui.getCurrentPlayerId());
		add(currentPlayer);
	}

	private void createPersistenceButtons() {
		JButton save = new JButton("Save");
		JButton load = new JButton("Load");

		save.addActionListener(this);
		load.addActionListener(this);

		save.setActionCommand("save");
		load.setActionCommand("load");

		add(save);
		add(load);
	}
	
	private void createPlayerTicketsInformation() {
		// TODO: rename me!
		doubleMoveTickets = new DoubleMoveTicketLabel();
		blackTickets = new BlackTicketLabel();
		busMoveTickets = new BusTicketLabel();
		taxiTickets = new TaxiTicketLabel();
		undergroundTickets = new UndergroundTicketLabel();

		add(doubleMoveTickets);
		add(blackTickets);
		add(busMoveTickets);
		add(taxiTickets);
		add(undergroundTickets);
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer.setText("Current Player: " + currentPlayer);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("save")) {
			gui.getControllable().saveGame("save_game.sy");
		} else {
			gui.getControllable().loadGame("save_game.sy");
			gui.updateCurrentPlayer();
			gui.redraw();
		}
	}
}
