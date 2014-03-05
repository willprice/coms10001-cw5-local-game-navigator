package scotlandyard;

import gamelogic.GameState;
import gui.GUI;

import javax.swing.SwingUtilities;


public class Game {

	public static void main(String[] args) {
		Game game = new Game();
		game.run();
	}
	
	
	public void run()
	{
		GameState state = new GameState();
		GUI gui = new GUI();
		gui.registerMapVisualisable(state);
		SwingUtilities.invokeLater(gui);
	}
}
