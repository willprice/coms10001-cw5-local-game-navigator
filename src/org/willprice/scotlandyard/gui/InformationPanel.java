package org.willprice.scotlandyard.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

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

		MigLayout layout = new MigLayout(new LC(), new AC(), new AC());
		setLayout(layout);
		
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
		add(currentPlayer, new CC().cell(0, 1));
	}

	private void createPersistenceButtons() {
		createAndAddPersistenceButton("Save");
		createAndAddPersistenceButton("Load");
		
	}
	
	private void createAndAddPersistenceButton(String name) {
		JButton button = new JButton(name);
		button.addActionListener(this);
		button.setActionCommand(name);
		add(button);
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

	public void actionPerformed(ActionEvent event) {
		JFileChooser fileChooser = new JFileChooser();

		int returnVal = showFileChooser(event, fileChooser);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			String filename = file.getAbsolutePath();
			if (event.getActionCommand().equals("Save")) {
				saveGame(filename);
			} else {
				loadGame(filename);
			}
		}
	}

	private void saveGame(String filename) {
		gui.getControllable().saveGame(filename);
	}

	private int showFileChooser(ActionEvent e, JFileChooser fileChooser) {
		int returnVal;
		if (e.getActionCommand().equals("Save")) {
			returnVal = fileChooser.showSaveDialog(gui.getWindow());
		} else {
			returnVal = fileChooser.showOpenDialog(gui.getWindow());
		}
		return returnVal;
	}

	private void loadGame(String filename) {
		gui.getControllable().loadGame(filename);
		gui.updateCurrentPlayer();
		gui.updateGlobalState();
	}
}
