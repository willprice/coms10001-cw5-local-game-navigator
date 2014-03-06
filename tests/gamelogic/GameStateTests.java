package gamelogic;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameStateTests {

	@Test
	public void createFourPlayers() throws Exception {
		GameState gameState = new GameState(4);
		assertEquals(4, gameState.getDetectiveIdList().size());
	}
	
}
