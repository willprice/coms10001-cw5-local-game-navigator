package org.willprice.scotlandyard.gui.tickets;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.willprice.scotlandyard.gui.GUI;

@SuppressWarnings("serial")
public abstract class TicketLabel extends JPanel {
	protected JLabel label;
	protected ImageIcon icon;

	public TicketLabel(String imageFilename) {
		icon = new ImageIcon(GUI.class.getResource("/" + imageFilename));
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