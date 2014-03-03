import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.willprice.scotlandyard.guicomponents.ResizableImageIcon;

import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

/**
 * Main visualising class
 *
 */
public class GUI extends GameVisualiser implements ActionListener{
		private int windowHeight;
		private int windowWidth;
		private MigLayout layout = new MigLayout(
				new LC(),
				new AC(),
				new AC());
		private JPanel panel = new JPanel(layout);
		private JFrame window;
		
		public void run(){
			setUpWindow();
			getDesktopSizes();
			panel = new JPanel(layout);
			addComponents(panel);
			displayWindow();
		}

		private void addMapImageToWindow() {
			ResizableImageIcon map = new ResizableImageIcon(getClass().getResource("map.jpg"), window);
			
		}

		private void displayWindow() {
			window.pack();
			window.setLocationByPlatform(true);
			window.setVisible(true);
		}

		private void addComponents(JPanel panel) {
			window.add(panel);
			addMapImageToWindow();
		}

		private void setUpWindow() {
			window = new JFrame();
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		private void getDesktopSizes() {
			Rectangle windowBounds = window.getBounds();
			windowHeight = windowBounds.height;
			windowWidth = windowBounds.width;
					
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}

}
