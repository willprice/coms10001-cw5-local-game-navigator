package gui;
import java.awt.Component;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.willprice.scotlandyard.guicomponents.ResizableImageIcon;

import scotlandyard.GameVisualiser;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

/**
 * Main visualising class
 *
 */
public class GUI extends GameVisualiser {
	private JFrame window;
	private JPanel panel;

	@Override
	public void run() {
		initializeWindow();
		drawMap();
		drawPlayers();
		displayWindow();
	}

	private void drawPlayers() {
		drawDetectives();
		drawMrX();
	}

	private void drawDetectives() {
		
	}

	private void drawMrX() {
		// TODO Auto-generated method stub
		
	}


	private void drawMap() {
		URL mapUrl = getClass().getResource("../" + mapVisualisable.getMapFilename());
		if (mapUrl != null) {
			JLabel mapLabel = new JLabel(new ImageIcon(mapUrl));
			panel.add(mapLabel);
			
			mapLabel.getIcon();
		} else {
			System.err.println("Could not find map image");
			System.exit(1);
		}
	}

	private void displayWindow() {
		window.pack();
		window.setVisible(true);
	}

	private void initializeWindow() {
		window = new JFrame();
		MigLayout layout = new MigLayout(new LC(), new AC(), new AC());
		panel = new JPanel(layout);
		window.add(panel);
	}


}
