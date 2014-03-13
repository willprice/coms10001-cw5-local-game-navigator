package org.willprice.scotlandyard.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InformationPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = -5187449302850290709L;
	private GUI gui;
	private JLabel currentPlayer;

	public InformationPanel(GUI gui) {
		this.gui = gui;

		createPersistenceButtons();
		createCurrentPlayerLabel();
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

	public void updateLabel(int i) {
		currentPlayer.setText("Current Player: " + i);
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
