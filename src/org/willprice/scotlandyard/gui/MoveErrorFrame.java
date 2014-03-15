package org.willprice.scotlandyard.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import net.miginfocom.swing.MigLayout;

public class MoveErrorFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = -989976026621770488L;
	private JButton closeButton;
	private JLabel closeLabel;

	public MoveErrorFrame() {
		super();
		setupDefaultBehaviour();
		createCloseButton();
		createCloseLabel();
		addComponents();
		display();
	}

	private void setupDefaultBehaviour() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	private void addComponents() {
		JPanel panel = new JPanel(new MigLayout());
		panel.add(closeLabel);
		panel.add(closeButton);
		add(panel);
	}

	private void createCloseButton() {
		closeButton = new JButton("Close");
		closeButton.addActionListener(this);
		closeButton.setActionCommand("close");
	}

	private void createCloseLabel() {
		closeLabel = new JLabel("Failed To Move, pick a valid destination");
	}

	private void display() {
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "close") {
			dispose();
		}
	}

}
