package org.willprice.scotlandyard.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
	private double xScaleFactor;
	private double yScaleFactor;
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

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		calculateScaleFactors();
		paintMap(g);
		paintDetectives(g);
		paintMrX(g);
	}

	private void paintMrX(Graphics g) {
		paintPlayer(g, mrXPosition, mrXImage);
	}

	private void paintDetective(Graphics g, Point detectiveLocation) {
		paintPlayer(g, detectiveLocation, detectiveImage);
	}

	private void paintPlayer(Graphics g, Point playerLocation,
			BufferedImage playerImage) {
		int width = calculateScaledImageWidth(playerImage);
		int height = calculateScaledImageHeight(playerImage);
		int x = calculateScaledImageXPosition(playerLocation, width);
		int y = calculateScaledImageYPosition(playerLocation, height);
		g.drawImage(playerImage, x, y, width, height, this);
	}

	private void calculateScaleFactors() {
		xScaleFactor = getWidth() / ((double) map.getWidth());
		yScaleFactor = getHeight() / ((double) map.getHeight());
	}

	private void paintMap(Graphics g) {
		if (map != null) {
			g.drawImage(map, 0, 0, getWidth(), getHeight(), this);
		}
	}

	private void paintDetectives(Graphics g) {
		for (Point detectiveLocation : detectiveLocations) {
			paintDetective(g, detectiveLocation);
		}
	}

	private int calculateScaledImageYPosition(Point point, int height) {
		return (int) (point.y * yScaleFactor - height / 2 - 20);
	}

	private int calculateScaledImageXPosition(Point point, int width) {
		return (int) (point.x * xScaleFactor - width / 2);
	}

	private int calculateScaledImageHeight(BufferedImage image) {
		return (int) (image.getHeight() * yScaleFactor);
	}

	private int calculateScaledImageWidth(BufferedImage image) {
		return (int) (image.getWidth() * xScaleFactor);
	}

	public void drawDetectives(List<Point> playerLocations) {
		this.detectiveLocations = playerLocations;
		redrawPanel();
	}

	private void redrawPanel() {
		revalidate();
		repaint();
	}

	public void drawMrX(Point mrXPosition) {
		int mrXPlayerId = 1;
		this.mrXPosition = mrXPosition;
		if (gui.getVisualisable().isVisible(mrXPlayerId)) {
			redrawPanel();
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
			System.out.println("Yes, we moved!");
			gui.redraw();
			gui.updateCurrentPlayer();
		} else {
			System.out.println("Can't move! :D");
		}

	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public Dimension getMapSize() {
		return mapSize;
	}

}
