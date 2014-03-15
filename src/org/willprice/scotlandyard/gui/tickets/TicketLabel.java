package org.willprice.scotlandyard.gui.tickets;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class TicketLabel extends JPanel {
	protected JLabel label;
	protected ImageIcon icon;

	public TicketLabel(Image image) {
		icon = new ImageIcon(image);
		display();
	}

	public void display() {
		int padding = 10;
		label = new JLabel("0", icon, padding);
		add(label);
	}
	
	public void setNumberOfTickets(Integer number) {
		label.setText(number.toString());
	}
}