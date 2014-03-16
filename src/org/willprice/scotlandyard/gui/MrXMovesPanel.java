package org.willprice.scotlandyard.gui;

import java.awt.Image;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

import org.willprice.scotlandyard.gamelogic.Initialisable.TicketType;
import org.willprice.scotlandyard.gamelogic.tickets.Ticket;


public class MrXMovesPanel extends JPanel {
	private static final long serialVersionUID = 6177428739476056998L;
	
	public MrXMovesPanel() {
		super();
		setLayout(new MigLayout(new LC().flowX().wrapAfter(2), new AC(), new AC()));
	}
	
	public void drawTickets(List<TicketType> tickets) {
		removeAll();
		for (TicketType ticket : tickets) {
			Image image = getTicketImage(ticket).getScaledInstance(150, -1, Image.SCALE_SMOOTH);
			add(new JLabel(new ImageIcon(image)));
		}
	}

	private Image getTicketImage(TicketType ticket) {
		switch(ticket) {
		case Bus:
			return GUI.getBusTicketImage();
		case Taxi:
			return GUI.getTaxiTicketImage();
		case Underground:
			return GUI.getUndergroundTicketImage();
		case DoubleMove:
			return GUI.getDoubleMoveTicketImage();
		case SecretMove:
			return GUI.getBlackTicketImage();
		}
		return null;
	}

}
