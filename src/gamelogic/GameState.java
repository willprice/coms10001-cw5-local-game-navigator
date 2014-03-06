package gamelogic;

import gamelogic.graph.Graph;
import gamelogic.graph.Node;
import gamelogic.player.Detective;
import gamelogic.player.MrX;
import gamelogic.player.Player;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import scotlandyard.MapVisualisable;
import scotlandyard.Reader;

/**
 * Class that will hold the state of the game. This is the class that will need
 * to implement the interfaces that we have provided you with
 */
public class GameState implements MapVisualisable, PlayerVisualisable, Visualisable {
	
	/**
	 * Variable that will hold the filename for the map
	 */
	private String mapFilename = "map.jpg";
	private List<Detective> detectives = Collections.synchronizedList(new ArrayList<Detective>());
	private MrX mrX;
	private List<Integer> mrXIdList;
	private List<Player> players;
	private Integer numberOfDetectives;
	private Graph graph;
	private List<Node> initialNodes; 
	private Map<Node, Point> nodeLocations;
	
	public GameState() { }
	
	public GameState(int numberOfDetectives) throws IOException {
		Reader reader = new Reader();
		reader.read("resources/graph.txt");
		reader.readNodeLocations();
		graph = reader.graph();
		nodeLocations = reader.getNodeLocations();
		initialiseGame(numberOfDetectives);
	}

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
		Node node = graph.find(nodeId);
		return nodeLocations.get(node).x;
	}


	@Override
	public Integer getLocationY(Integer nodeId) {
		Node node = graph.find(nodeId);
		return nodeLocations.get(node).y;
	}


	@Override
	public List<Integer> getDetectiveIdList() {
		List<Integer> detectiveIdList = Collections.synchronizedList(new ArrayList<Integer>());
		for (Player detective : detectives) {
			detectiveIdList.add(detective.getPlayerId());
		}
		return detectiveIdList;
	}


	@Override
	public List<Integer> getMrXIdList() {
		return mrXIdList;
	}


	@Override
	public Integer getNodeId(Integer playerId) {
		Player player = getPlayer(playerId);
		return Integer.parseInt(player.getPosition().name());
	}


	@Override
	public Boolean initialiseGame(Integer numberOfDetectives) {
		this.numberOfDetectives = numberOfDetectives;
		initialisePlayers();
		return true;
	}

	private void initialisePlayers() {
		initialNodes = new ArrayList<Node>(graph.nodes());
		initialiseDetectives();
		initialiseMrX();
		initialisePlayerList();
	}

	private void initialisePlayerList() {
		players = new ArrayList<>();
		players.addAll(detectives);
		players.add(mrX);
	}

	private void initialiseDetectives() {
		for (int playerId=1; playerId <= numberOfDetectives; ++playerId) {
			Detective detective = createDetective(playerId);
			detectives.add(detective);
		}

	}

	private Detective createDetective(int playerId) {
		Detective detective = new Detective(playerId);
		detective.setPosition(getRandomNodeFromInitialNodes());
		return detective;
	}

	private Node getRandomNodeFromInitialNodes() {
		Random randomNumber = new Random();

		int numberOfNodes = initialNodes.size();
		int randomNodeIndex = randomNumber.nextInt(numberOfNodes);
		Node node = initialNodes.get(randomNodeIndex);
		initialNodes.remove(node);
		return node;
	}

	private void initialiseMrX() {
		mrX = new MrX(this, numberOfDetectives + 1);
		mrX.setPosition(getRandomNodeFromInitialNodes());
		mrXIdList = Collections.synchronizedList(new ArrayList<Integer>());
		mrXIdList.add(mrX.getPlayerId());
	}
	
	@Override
	public Integer getNumberOfTickets(TicketType type, Integer playerId) {
		Player player = getPlayer(playerId);
		return -1;
	}


	private Player getPlayer(Integer playerId) {
		for (Player player : players) {
			if (playerId.equals(player.getPlayerId())) {
				return player;
			}
		}
		return null;
	}

	@Override
	public List<TicketType> getMoveList(Integer playerId) {
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
