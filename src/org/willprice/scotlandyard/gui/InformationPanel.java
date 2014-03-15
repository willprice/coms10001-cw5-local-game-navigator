package org.willprice.scotlandyard.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.willprice.scotlandyard.gamelogic.Initialisable;
import org.willprice.scotlandyard.gamelogic.Initialisable.TicketType;
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
	private BusTicketLabel busTickets;
	private TaxiTicketLabel taxiTickets;
	private UndergroundTicketLabel undergroundTickets;

	public InformationPanel(GUI gui) {
		this.gui = gui;

		createPersistenceButtons();
		createCurrentPlayerLabel();
		createPlayerTicketsInformation();
	}

	public void updateAndRedraw() {
		busTickets.setNumberOfTickets(getNumberOfTickets(Initialisable.TicketType.Bus));
		taxiTickets.setNumberOfTickets(getNumberOfTickets(Initialisable.TicketType.Taxi));
		undergroundTickets.setNumberOfTickets(getNumberOfTickets(Initialisable.TicketType.Underground));
		blackTickets.setNumberOfTickets(getNumberOfTickets(Initialisable.TicketType.SecretMove));
		doubleMoveTickets.setNumberOfTickets(getNumberOfTickets(Initialisable.TicketType.DoubleMove));
		setCurrentPlayer(gui.getCurrentPlayerId());
	}

	private Integer getNumberOfTickets(TicketType ticketType) {
		TicketType ticket = ticketType;
		Integer numberOfTickets = gui.getVisualisable().getNumberOfTickets(ticket, gui.getCurrentPlayerId());
		return numberOfTickets;
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
		busTickets = new BusTicketLabel();
		taxiTickets = new TaxiTicketLabel();
		undergroundTickets = new UndergroundTicketLabel();

		add(doubleMoveTickets);
		add(blackTickets);
		add(busTickets);
		add(taxiTickets);
		add(undergroundTickets);
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer.setText("Current Player: " + currentPlayer);
	}

	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		String filename;
		File file;
		int returnVal = -1;
		
		if (e.getActionCommand().equals("save")) {
			returnVal = fileChooser.showSaveDialog(gui.getWindow());
		} else {
			returnVal = fileChooser.showOpenDialog(gui.getWindow());
		}

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			filename = file.getAbsolutePath();
			if (e.getActionCommand().equals("save")) {
				gui.getControllable().saveGame(filename);
			} else {
				gui.getControllable().loadGame(filename);
				gui.updateCurrentPlayer();
				gui.updateGlobalState();
			}
		}
	}
}
