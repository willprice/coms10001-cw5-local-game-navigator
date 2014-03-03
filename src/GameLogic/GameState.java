package GameLogic;

import java.util.List;

import ScotlandYard.MapVisualisable;

/**
 * Class that will hold the state of the game. This is the class that will need
 * to implement the interfaces that we have provided you with
 */
public class GameState implements MapVisualisable, PlayerVisualisable {
	
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

}
