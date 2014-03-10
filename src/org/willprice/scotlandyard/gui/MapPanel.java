package org.willprice.scotlandyard.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MapPanel extends JPanel {
	private static final long serialVersionUID = 8872624079855647816L;

	private BufferedImage map;
	private Dimension mapSize;
	private BufferedImage mrXImage;
	private BufferedImage detectiveImage;
	private List<Point> detectiveLocations;
	private Point mrXPosition;
	private double xScaleFactor;
	private double yScaleFactor;
	
	public MapPanel(String filename) throws IOException {
		File imageFile = new File(filename);
		map = ImageIO.read(imageFile);
		mapSize = new Dimension(map.getWidth(), map.getHeight());
		setPreferredSize(mapSize);
		setMinimumSize(mapSize);
        detectiveImage = ImageIO.read(new File("resources/red_detective.png"));
        mrXImage = ImageIO.read(new File("resources/mr_x.png"));
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

	private void paintPlayer(Graphics g, Point playerLocation, BufferedImage playerImage) {
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


	private int calculateScaledImageYPosition(Point point,
			int height) {
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
		this.mrXPosition = mrXPosition;
		redrawPanel();
	}
}
