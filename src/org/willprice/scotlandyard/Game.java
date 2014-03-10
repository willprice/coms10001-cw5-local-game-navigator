package org.willprice.scotlandyard;

import java.io.IOException;

import javax.swing.SwingUtilities;

import org.willprice.scotlandyard.gamelogic.GameState;
import org.willprice.scotlandyard.gui.GUI;


public class Game {

	public static void main(String[] args) throws IOException {
		Game game = new Game();
		game.run();
	}
	
	
	public void run() throws IOException
	{
		GameState state = new GameState(4);
		GUI gui = new GUI();
		gui.registerMapVisualisable(state);
		gui.registerPlayerVisualisable(state);
		gui.registerControllable(state);
		gui.registerVisualisable(state);
		SwingUtilities.invokeLater(gui);
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		state.loadGame("");
		gui.redraw();
	}
}
