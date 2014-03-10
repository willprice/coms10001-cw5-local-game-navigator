package org.willprice.scotlandyard.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import org.willprice.scotlandyard.gamelogic.GameVisualiser;
import org.willprice.scotlandyard.gamelogic.tickets.Ticket;

import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

/**
 * Main visualising class
 *
 */
public class GUI extends GameVisualiser implements MouseListener, SelectTicketTypePanelDelegate {
	private JFrame window;
	private JPanel panel;
	private MapPanel mapPanel;
	private int currentPlayerId;
	private JFrame selectTicketFrame;
	private int targetNodeId;

	@Override
	public void run() {
		initializeWindow();
		initaliseSelectTicketWindow();
		drawMap();
		drawPlayers();
		drawInformationPanel();
		drawMrXMovesPanel();
		displayWindow();
		
		currentPlayerId = visualisable.getNextPlayerToMove();
	}

	private void drawMrXMovesPanel() {
		JPanel mrXMoves = new JPanel();
		mrXMoves.setBackground(new Color(0, 0, 244));
		panel.add(mrXMoves, new CC().growX().growY().spanY(2).width("20%"));
	}

	private void drawInformationPanel() {
		JPanel informationPanel = new InformationPanel();
		informationPanel.setBackground(new Color(244, 0, 0));
		panel.add(informationPanel, new CC().growX().height("20%"));
	}

	private void drawPlayers() {
		drawDetectives();
		drawMrX();
	}

	private void drawDetectives() {
		List<Point> listOfPlayerLocations = getPlayerLocations();
		mapPanel.drawDetectives(listOfPlayerLocations);
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
		Point point = new Point(x,y);
		return point;
	}

	private void drawMrX() {
		Integer mrX = playerVisualisable.getMrXIdList().get(0);
		Point mrXLocation = getPlayerPosition(mrX);
		mapPanel.drawMrX(mrXLocation);
		
	}

	private void drawMap() {
		try {
			mapPanel = new MapPanel("resources/" + mapVisualisable.getMapFilename());
			mapPanel.addMouseListener(this);
			
			JScrollPane scrollPane = new JScrollPane(mapPanel);
			panel.add(scrollPane, new CC().height("80%"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void displayWindow() {
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.pack();
		window.setVisible(true);
	}

	private void initializeWindow() {
		window = new JFrame();
		
		MigLayout layout = new MigLayout(new LC().flowY().wrapAfter(2), new AC().grow().fill(), new AC().grow().fill());
		panel = new JPanel(layout);

		window.add(panel);
	}

	private void initaliseSelectTicketWindow() {
		selectTicketFrame = new JFrame();
		SelectTicketTypePanel ticketPanel = new SelectTicketTypePanel();
		ticketPanel.setDelegate(this);
		selectTicketFrame.getContentPane().add(ticketPanel);
		selectTicketFrame.pack();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().equals(mapPanel)) {
			int x = e.getX();
			int y = e.getY();

			selectTicketFrame.setVisible(true);
			targetNodeId = controllable.getNodeIdFromLocation(x, y);
		}
	}

	@Override
	public void ticketTypePanelSelected(Ticket ticket) {
		selectTicketFrame.dispose();
		controllable.movePlayer(currentPlayerId, targetNodeId, ticket.getTicketType());
		System.out.println("Ticket type: " + ticket.getTicketType().toString());
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	
}
