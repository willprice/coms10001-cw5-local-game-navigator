package gamelogic;

import gamelogic.graph.Edge;
import gamelogic.graph.Edge.EdgeType;
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
import java.util.Map.Entry;
import java.util.Random;

import scotlandyard.MapVisualisable;
import scotlandyard.Reader;

/**
 * Class that will hold the state of the game. This is the class that will need
 * to implement the interfaces that we have provided you with
 */
public class GameState implements MapVisualisable, PlayerVisualisable, Visualisable, Controllable {
	
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
	private int currentPlayerId = -1;
	
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
		for (int playerId = 2; playerId <= numberOfDetectives + 1; ++playerId) {
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
		mrX = new MrX(this);
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
		if (currentPlayerId == -1) {
			currentPlayerId = 2;
		} else {
			++currentPlayerId;
		}
		return currentPlayerId;
	}


	@Override
	public Integer getWinningPlayerId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean movePlayer(Integer playerId, Integer targetNodeId, TicketType ticketType) {
		Player player = getPlayer(playerId);
		
		List<Edge> currentlyConnectedEdges = graph.edges(player.getPosition().name());
		Edge edgeToTravelAlong = findNodeToTravelAlong(targetNodeId, ticketType, currentlyConnectedEdges);
		
		// TODO: Implement actually moving player
		return true;
	}
	
	private Edge findNodeToTravelAlong(Integer targetNodeId, TicketType ticketType, List<Edge> currentlyConnectedEdges) {
		for (Edge edge : currentlyConnectedEdges) {
			if (edge.connectsNode(Integer.toString(targetNodeId)) && edgeTypeEqualsTicketType(edge.type(), ticketType)){
				return edge;
			}
		}
		return null;
	}
	
	private boolean edgeTypeEqualsTicketType(EdgeType edgeType, TicketType ticketType) {
		return (edgeType.toString().equals(ticketType.toString()));
	}

	@Override
	public Integer getNodeIdFromLocation(Integer xPosition, Integer yPosition) {
		Point point = new Point(xPosition, yPosition);
		for (Entry<Node, Point> entry : nodeLocations.entrySet()) {
			if (clickIsWithinNodeFuzzyBounds(point, entry)) {
				Node node = entry.getKey();
				int nodeId = Integer.parseInt(node.name());
				System.out.println(nodeId);
				return nodeId;
			}
		}
		return null;
	}

	private boolean clickIsWithinNodeFuzzyBounds(Point clickPoint, Entry<Node, Point> entry) {
		Point nodePoint = entry.getValue();
		if (clickIsWithinNodeXBound(clickPoint, nodePoint) && clickIsWithinNodeYBound(clickPoint, nodePoint)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean clickIsWithinNodeXBound(Point clickPoint, Point nodePoint) {
		return ((clickPoint.x >= (nodePoint.x - 10)) && (clickPoint.x <= (nodePoint.x + 10)));
	}

    private boolean clickIsWithinNodeYBound(Point clickPoint, Point nodePoint) {
		return ((clickPoint.y >= (nodePoint.y - 10)) && (clickPoint.y <= (nodePoint.y + 10)));
	}

	@Override
	public Boolean saveGame(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean loadGame(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

}
