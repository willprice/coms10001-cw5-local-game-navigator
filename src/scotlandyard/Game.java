package scotlandyard;

import java.io.IOException;

import gamelogic.GameState;
import gui.GUI;

import javax.swing.SwingUtilities;


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
		SwingUtilities.invokeLater(gui);
	}
}
