package org.willprice.scotlandyard.gui;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.willprice.scotlandyard.gamelogic.Controllable;
import org.willprice.scotlandyard.gamelogic.GameState;
import org.willprice.scotlandyard.gamelogic.GameVisualiser;
import org.willprice.scotlandyard.gamelogic.MapVisualisable;
import org.willprice.scotlandyard.gamelogic.PlayerVisualisable;
import org.willprice.scotlandyard.gamelogic.Visualisable;

import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

/**
 * Main visualising class
 * 
 */
public class GUI extends GameVisualiser {
	private JFrame window;
	JPanel panel;
	private MapPanel mapPanel;
	private Integer currentPlayerId;
	int targetNodeId;
	private InformationPanel informationPanel;
	private MrXMovesPanel mrXMovesPanel;

	public GUI(GameState state) {
		registerMapVisualisable(state);
		registerPlayerVisualisable(state);
		registerControllable(state);
		registerVisualisable(state);
	}

	@Override
	public void run() {
		setCurrentPlayerId(getVisualisable().getMrXIdList().get(0));

		initializeWindow();
		drawMapPanel();
		drawInformationPanel();
		drawMrXMovesPanel();
		displayWindow();
	}

	public void updateGlobalStateAndUpdateCurrentPlayer() {
		setCurrentPlayerId(getVisualisable().getNextPlayerToMove());
		updateGlobalState();
	}

	public void updateGlobalState() {
		mapPanel.redraw();
		mrXMovesPanel.drawTickets(getVisualisable().getMoveList(getMrXId()));
		informationPanel.updateAndRedraw();
		if (getVisualisable().isGameOver()) {
			winGame();
		}
	}

	private void winGame() {
		Integer winner = getVisualisable().getWinningPlayerId();
		JFrame winningWindow = new JFrame();
		String message;
		if (winner == getMrXId()) {
			message = "Well Done Mr X. You outwitted the detectives!";
		} else {
			message = "You clever players!, you got Mr X!";
		}
		winningWindow.add(new JLabel(message));
		winningWindow.pack();
		winningWindow.setVisible(true);
		winningWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	private void drawMrXMovesPanel() {
		mrXMovesPanel = new MrXMovesPanel();
		panel.add(mrXMovesPanel, new CC().growX().growY().spanY(2).width("20%"));
	}

	private void drawInformationPanel() {
		informationPanel = new InformationPanel(this);
		informationPanel.updateAndRedraw();
		panel.add(informationPanel, new CC().growX().height("20%"));
	}

	void drawMapPanel() {
		try {
			setMapPanel(new MapPanel(getMapVisualisable().getMapFilename(), this));
			drawMapScrollPane();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void drawMapScrollPane() {
		JScrollPane scrollPane = new JScrollPane(getMapPanel());
		scrollPane.setMaximumSize(getMapPanel().getMapSize());
		panel.add(scrollPane, new CC().height("80%"));
	}

	private void displayWindow() {
		window.add(panel);
		window.pack();
	}

	private void initializeWindow() {
		window = new JFrame();

		MigLayout layout = new MigLayout(new LC().flowY().wrapAfter(2),
				new AC().grow().fill(), new AC().grow().fill());
		panel = new JPanel(layout);

		window.add(panel);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public int getCurrentPlayerId() {
		return currentPlayerId;
	}

	public void setCurrentPlayerId(int currentPlayerId) {
		this.currentPlayerId = currentPlayerId;
	}

	public void updateCurrentPlayer() {
		int numberOfPlayers = getVisualisable().getDetectiveIdList().size() + getVisualisable().getMrXIdList().size();
		Integer nextPlayerToMove = getVisualisable().getNextPlayerToMove();
		if (nextPlayerToMove == 1) {
			currentPlayerId = numberOfPlayers;
		} else {
			currentPlayerId = nextPlayerToMove - 1;
		}
	}

	public Controllable getControllable() {
		return controllable;
	}

	public MapPanel getMapPanel() {
		return mapPanel;
	}

	public void setMapPanel(MapPanel mapPanel) {
		this.mapPanel = mapPanel;
	}

	public Visualisable getVisualisable() {
		return visualisable;
	}

	public MapVisualisable getMapVisualisable() {
		return mapVisualisable;
	}

	public PlayerVisualisable getPlayerVisualisable() {
		return playerVisualisable;
	}

	public List<Integer> getPlayers() {
		List<Integer> players = new ArrayList<>();
		players.addAll(getVisualisable().getDetectiveIdList());
		players.addAll(getVisualisable().getMrXIdList());
		return players;
	}

	public int getMrXId() {
		return getVisualisable().getMrXIdList().get(0);
	}

	public static Image getBusTicketImage() {
		return readImage("/bus_ticket.png");
	}

	public static Image getTaxiTicketImage() {
		return readImage("/taxi_ticket.png");
	}

	public static Image getUndergroundTicketImage() {
		return readImage("/tube_ticket.png");
	}

	public static Image getDoubleMoveTicketImage() {
		return readImage("/double_move_ticket.png");
	}

	public static Image getBlackTicketImage() {
		return readImage("/black_ticket.png");
	}

	private static Image readImage(String imagePath) {
		try {
			return ImageIO.read(GUI.class.getResource(imagePath));
		} catch (IOException e) {
			System.err.println("Could not find " + imagePath);
			e.printStackTrace();
		}
		return null;
	}

	public JFrame getWindow() {
		return window;
	}

}
