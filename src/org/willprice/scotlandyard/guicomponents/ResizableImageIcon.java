package org.willprice.scotlandyard.guicomponents;

import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ResizableImageIcon extends ImageIcon implements ComponentListener{
	private JFrame window;
	static Image originalImage;
	private JLabel image;

	public ResizableImageIcon(URL mapFilename, JFrame window) {
		super(mapFilename);
		this.window = window;
		image = new JLabel(this);
		this.window.add(image);
		this.window.addComponentListener(this);
		try {
			originalImage = ImageIO.read(mapFilename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void componentHidden(ComponentEvent e) {}
	public void componentMoved(ComponentEvent e) {}
	public void componentShown(ComponentEvent e) {}

	@Override
	public void componentResized(ComponentEvent e) {
		double heightScaleFactor = image.getHeight()/(double) image.getWidth();
		int width = window.getWidth();
		int height = (int) (width * heightScaleFactor);
		Image scaledMapImage = originalImage.getScaledInstance(width, height, Image.SCALE_REPLICATE);
		this.setImage(scaledMapImage);
	}

	
}
