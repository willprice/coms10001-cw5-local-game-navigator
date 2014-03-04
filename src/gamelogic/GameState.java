package gamelogic;

import java.util.List;

import scotlandyard.MapVisualisable;

/**
 * Class that will hold the state of the game. This is the class that will need
 * to implement the interfaces that we have provided you with
 */
public class GameState implements MapVisualisable, PlayerVisualisable, Visualisable {
	
	/**
	 * Variable that will hold the filename for the map
	 */
	private String mapFilename = "map.jpg";
	
	
	/**
	 * Concrete implementation of the MapVisualisable getMapFilename function
	 * @return The map filename
	 */
	public String getMapFilename()
	{
		return mapFilename;
	}


	@Override
	public Integer getLocationX(Integer nodeId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer getLocationY(Integer nodeId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Integer> getDetectiveIdList() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Integer> getMrXIdList() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer getNodeId(Integer playerId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Boolean initialiseGame(Integer numberOfDetectives) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer getNumberOfTickets(TicketType type, Integer playerId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<TicketType> getMoveList(Integer playerId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Boolean isVisible(Integer playerId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Boolean isGameOver() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer getNextPlayerToMove() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Integer getWinningPlayerId() {
		// TODO Auto-generated method stub
		return null;
	}

}
