package org.willprice.scotlandyard;

import java.io.IOException;

import javax.swing.SwingUtilities;

import org.willprice.scotlandyard.gamelogic.GameState;
import org.willprice.scotlandyard.gui.GUI;

public class Game {

	public static void main(String[] args) throws IOException {
		Game game = new Game();
		game.run(null);
	}

	public void run(GameState gameState) throws IOException {
		GameState state = new GameState(1);
		SwingUtilities.invokeLater(new GUI(state));
	}
}
