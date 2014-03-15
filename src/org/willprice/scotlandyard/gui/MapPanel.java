package org.willprice.scotlandyard.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.willprice.scotlandyard.gamelogic.Controllable;
import org.willprice.scotlandyard.gamelogic.tickets.Ticket;

public class MapPanel extends JPanel implements MouseListener {
	private static final long serialVersionUID = 8872624079855647816L;

	private SelectTicketFrame selectTicketFrame;
	private BufferedImage map;
	private Dimension mapSize;
	private BufferedImage mrXImage;
	private BufferedImage detectiveImage;
	private List<Point> detectiveLocations;
	private Point mrXPosition;
	private GUI gui;

	private Integer targetNodeId;

	public MapPanel(String filename, GUI gui) throws IOException {
		this.gui = gui;
		readImages(filename);
		setPreferredSize(mapSize);
		selectTicketFrame = new SelectTicketFrame(gui);
		addMouseListener(this);
	}

	private void readImages(String mapFilename) throws IOException {
		File imageFile = new File(mapFilename);
		detectiveImage = ImageIO.read(new File("resources/red_detective.png"));
		mrXImage = ImageIO.read(new File("resources/mr_x.png"));
		map = ImageIO.read(imageFile);
		mapSize = new Dimension(map.getWidth(), map.getHeight());
	}

	public void draw() {
		redraw();
	}

	public void redraw() {
		revalidate();
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintMap(g);
		paintCurrentPlayerCircle(g);
		paintDetectives(g);
		paintMrX(g);
	}

	private void paintCurrentPlayerCircle(Graphics g) {
		g = (Graphics2D) g;
		int currentPlayer = gui.getCurrentPlayerId();
		int width = 0, height = 0, x = 0, y = 0;
		for (int player : gui.getPlayers()) {
			if (player == currentPlayer) {
				Point position = getPlayerLocation(player);
				x = position.x;
				y = position.y;
				if (player != gui.getVisualisable().getMrXIdList().get(0)) {
					width = detectiveImage.getWidth();
					height = detectiveImage.getHeight();
				} else {
					width = mrXImage.getWidth();
					height = mrXImage.getHeight();
				}
				x -= width/2;
				y -= height/2;
			}
		}
		g.setColor(new Color(255,0,0));
		g.drawArc(x, y, width, height, 0, 360);

	}


	private void paintMrX(Graphics g) {
		Integer mrX = gui.getVisualisable().getMrXIdList().get(0);
		this.mrXPosition = getPlayerLocation(mrX);
		if (gui.getVisualisable().isVisible(mrX)) {
			paintPlayer(g, mrXPosition, mrXImage);
		}
	}

	private void paintDetective(Graphics g, Point detectiveLocation) {
		paintPlayer(g, detectiveLocation, detectiveImage);
	}

	private void paintPlayer(Graphics g, Point playerLocation,
			BufferedImage playerImage) {
		int width = playerImage.getWidth();
		int height = playerImage.getHeight();
		int x = playerLocation.x - playerImage.getWidth()/2;
		int y = (int) (playerLocation.y - playerImage.getHeight() * 1.2);
		g.drawImage(playerImage, x, y, width, height, this);
	}

	private void paintMap(Graphics g) {
		if (map != null) {
			g.drawImage(map, 0, 0, getWidth(), getHeight(), this);
		}
	}

	private void paintDetectives(Graphics g) {
		this.detectiveLocations = getPlayerLocations();
		for (Point detectiveLocation : detectiveLocations) {
			paintDetective(g, detectiveLocation);
		}
	}

	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		targetNodeId = gui.getControllable().getNodeIdFromLocation(x, y);

		if (targetNodeId != null) {
			selectTicketFrame.setVisible(true);
		}
	}

	public void movePlayer(Ticket ticket) {
		Boolean move = gui.getControllable().movePlayer(
				gui.getCurrentPlayerId(), targetNodeId, ticket.getTicketType());
		if (move) {
			gui.updateGlobalState();
		} else {
			new MoveErrorFrame();
		}
	}

	public Dimension getMapSize() {
		return mapSize;
	}

	public List<Point> getPlayerLocations() {
		List<Point> listOfPlayerLocations = new ArrayList<>();
		for (Integer detective : gui.getPlayerVisualisable().getDetectiveIdList()) {
			Point playerPosition = getPlayerLocation(detective);
			listOfPlayerLocations.add(playerPosition);
		}
		return listOfPlayerLocations;
	}

	Point getPlayerLocation(Integer playerId) {
		Integer node = gui.getPlayerVisualisable().getNodeId(playerId);
		int x = gui.getPlayerVisualisable().getLocationX(node);
		int y = gui.getPlayerVisualisable().getLocationY(node);
		Point point = new Point(x, y);
		return point;
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}
