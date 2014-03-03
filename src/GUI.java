import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Main visualising class
 *
 */
public class GUI extends GameVisualiser {
	private JFrame frame;
		
	/**
	 * @wbp.parser.entryPoint
	 */
	public void run() {
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(1000, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		//panel.setPreferredSize(new Dimension(600, 800));
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		Box horizontalBox = Box.createHorizontalBox();
		panel.add(horizontalBox, Component.CENTER_ALIGNMENT);
		
		Box verticalBox = Box.createVerticalBox();
		horizontalBox.add(verticalBox, JComponent.CENTER_ALIGNMENT);
		
		JLabel lblNewLabel = new JLabel("");
		URL url = getClass().getResource("resource/map.jpg");
		try {
			Image mapImage = ImageIO.read(getClass().getResource("/map.jpg"));
			mapImage = mapImage.getScaledInstance(mapImage.getWidth(null) / 2, mapImage.getHeight(null) / 2, Image.SCALE_DEFAULT);
			
			lblNewLabel.setIcon(new ImageIcon(mapImage));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		verticalBox.add(lblNewLabel);
		
		frame.pack();
		frame.setVisible(true);
	}
}
