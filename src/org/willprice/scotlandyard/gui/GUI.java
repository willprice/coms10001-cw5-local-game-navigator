package org.willprice.scotlandyard.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import org.willprice.scotlandyard.gamelogic.Controllable;
import org.willprice.scotlandyard.gamelogic.GameVisualiser;
import org.willprice.scotlandyard.gamelogic.Visualisable;
import org.willprice.scotlandyard.gamelogic.tickets.Ticket;

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
	private JPanel panel;
	private MapPanel mapPanel;
	private Integer currentPlayerId;
	int targetNodeId;
	private InformationPanel informationPanel;

	@Override
	public void run() {
		setCurrentPlayerId(visualisable.getNextPlayerToMove());
		initializeWindow();
		drawMap();
		drawPlayers();
		drawInformationPanel();
		drawMrXMovesPanel();
		displayWindow();

	}

	public void redraw() {
		drawPlayers();
		getMapPanel().revalidate();
		getMapPanel().repaint();
		informationPanel.setCurrentPlayer(getCurrentPlayerId());
	}

	private void drawMrXMovesPanel() {
		JPanel mrXMoves = new JPanel();
		mrXMoves.setBackground(new Color(0, 0, 244));
		panel.add(mrXMoves, new CC().growX().growY().spanY(2).width("20%"));
	}

	private void drawInformationPanel() {
		informationPanel = new InformationPanel(this);
		informationPanel.setBackground(new Color(244, 0, 0));
		panel.add(informationPanel, new CC().growX().height("20%"));
	}

	private void drawPlayers() {
		drawDetectives();
		drawMrX();
	}

	private void drawDetectives() {
		List<Point> listOfPlayerLocations = getPlayerLocations();
		getMapPanel().drawDetectives(listOfPlayerLocations);
	}

	private List<Point> getPlayerLocations() {
		List<Point> listOfPlayerLocations = new ArrayList<>();
		for (Integer detective : playerVisualisable.getDetectiveIdList()) {
			Point playerPosition = getPlayerPosition(detective);
			listOfPlayerLocations.add(playerPosition);
		}
		return listOfPlayerLocations;
	}

	private Point getPlayerPosition(Integer playerId) {
		Integer node = playerVisualisable.getNodeId(playerId);
		int x = playerVisualisable.getLocationX(node);
		int y = playerVisualisable.getLocationY(node);
		Point point = new Point(x, y);
		return point;
	}

	private void drawMrX() {
		Integer mrX = playerVisualisable.getMrXIdList().get(0);
		Point mrXLocation = getPlayerPosition(mrX);
		getMapPanel().drawMrX(mrXLocation);

	}

	private void drawMap() {
		try {
			setMapPanel(new MapPanel("resources/"
					+ mapVisualisable.getMapFilename(), this));

			JScrollPane scrollPane = new JScrollPane(getMapPanel());
			scrollPane.setMaximumSize(getMapPanel().getMapSize());
			panel.add(scrollPane, new CC().height("80%"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void displayWindow() {
		window.add(panel);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
	}

	private void initializeWindow() {
		window = new JFrame();

		MigLayout layout = new MigLayout(new LC().flowY().wrapAfter(2),
				new AC().grow().fill(), new AC().grow().fill());
		panel = new JPanel(layout);

		window.add(panel);
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
}
