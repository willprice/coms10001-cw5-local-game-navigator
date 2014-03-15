package org.willprice.scotlandyard.gui;

import java.awt.Color;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

	@Override
	public void run() {
		setCurrentPlayerId(getVisualisable().getMrXIdList().get(0));

		initializeWindow();
		drawMapPanel();
		drawInformationPanel();
		drawMrXMovesPanel();
		displayWindow();
	}

	public void updateGlobalState() {
		Integer nextPlayerToMove = getVisualisable().getNextPlayerToMove();
		setCurrentPlayerId(nextPlayerToMove);
		mapPanel.draw();
		informationPanel.updateAndRedraw();
	}

	private void drawMrXMovesPanel() {
		JPanel mrXMoves = new JPanel();
		mrXMoves.setBackground(new Color(0, 0, 244));
		panel.add(mrXMoves, new CC().growX().growY().spanY(2).width("20%"));
	}

	private void drawInformationPanel() {
		informationPanel = new InformationPanel(this);
		informationPanel.setBackground(new Color(244, 0, 0));
		informationPanel.setCurrentPlayer(getCurrentPlayerId());
		informationPanel.updateAndRedraw();
		panel.add(informationPanel, new CC().growX().height("20%"));
	}

	void drawMapPanel() {
		try {
			setMapPanel(new MapPanel("resources/"
					+ getMapVisualisable().getMapFilename(), this));
	
			JScrollPane scrollPane = new JScrollPane(getMapPanel());
			scrollPane.setMaximumSize(getMapPanel().getMapSize());
			panel.add(scrollPane, new CC().height("80%"));
			mapPanel.draw();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		currentPlayerId = visualisable.getNextPlayerToMove();
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

	public boolean clickIsWithinNodeXBound(Point clickPoint, Point nodePoint, GameState gameState) {
		return ((clickPoint.x >= (nodePoint.x - 10)) && (clickPoint.x <= (nodePoint.x + 10)));
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

}
