package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MapPanel extends JPanel {
	private BufferedImage map;
	
	public MapPanel(String filename) throws IOException {
		URL url = new URL(filename);
		map = ImageIO.read(url);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (map != null) {
			//g.drawImage(img, x, y, width, height, this);
		}
	}
}
