package gui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.willprice.scotlandyard.guicomponents.ResizableImageIcon;

import scotlandyard.GameVisualiser;
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

	@Override
	public void run() {
		initializeWindow();
		drawMap();
		drawPlayers();
		drawInformationPanel();
		drawMrXMovesPanel();
		displayWindow();
	}

	private void drawMrXMovesPanel() {
		JPanel mrXMoves = new JPanel();
		mrXMoves.setBackground(new Color(0, 0, 244));
		panel.add(mrXMoves, new CC().growX().growY().spanY(2).width("30%"));
	}

	private void drawInformationPanel() {
		JPanel informationPanel = new InformationPanel();
		informationPanel.setBackground(new Color(244, 0, 0));
		panel.add(informationPanel, new CC().growX().growY().height("20%"));
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
			panel.add(mapPanel, new CC().growX());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void displayWindow() {
		window.pack();
		window.setVisible(true);
	}

	private void initializeWindow() {
		window = new JFrame();
		MigLayout layout = new MigLayout(new LC().flowY().wrapAfter(2), new AC().grow().fill(), new AC().grow().fill());
		panel = new JPanel(layout);
		window.add(panel);
}


}
