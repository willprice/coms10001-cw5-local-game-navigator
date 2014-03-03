package ScotlandYard;

import javax.swing.SwingUtilities;

import GUI.GUI;
import GameLogic.GameState;


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
