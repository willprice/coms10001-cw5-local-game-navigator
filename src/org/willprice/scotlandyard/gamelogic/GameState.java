package org.willprice.scotlandyard.gamelogic;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.Vector;

import org.willprice.scotlandyard.Reader;
import org.willprice.scotlandyard.gamelogic.graph.Edge;
import org.willprice.scotlandyard.gamelogic.graph.Graph;
import org.willprice.scotlandyard.gamelogic.graph.Node;
import org.willprice.scotlandyard.gamelogic.graph.Edge.EdgeType;
import org.willprice.scotlandyard.gamelogic.player.Detective;
import org.willprice.scotlandyard.gamelogic.player.MrX;
import org.willprice.scotlandyard.gamelogic.player.Player;
import org.willprice.scotlandyard.gamelogic.tickets.BlackTicket;
import org.willprice.scotlandyard.gamelogic.tickets.BusTicket;
import org.willprice.scotlandyard.gamelogic.tickets.TaxiTicket;
import org.willprice.scotlandyard.gamelogic.tickets.Ticket;
import org.willprice.scotlandyard.gamelogic.tickets.UndergroundTicket;
import org.willprice.scotlandyard.util.PersistentStore;

/**
 * Class that will hold the state of the game. This is the class that will need
 * to implement the interfaces that we have provided you with
 */
public class GameState implements MapVisualisable, PlayerVisualisable,
		Visualisable, Controllable {

	/**
	 * Variable that will hold the filename for the map
	 */
	private String mapFilename = "map.jpg";
	private List<Detective> detectives = new ArrayList<>();
	private MrX mrX;
	private List<Integer> mrXIdList;
	private List<Player> players;
	private Integer numberOfDetectives;
	private Graph graph;
	private Map<Node, Point> nodeLocations;
	private int currentPlayerId = 1;
	private PersistentStore store;
	private Stack<BusTicket> busTicketDiscardStack = new Stack<>();
	private Stack<TaxiTicket> taxiTicketDiscardStack = new Stack<>();
	private Stack<UndergroundTicket> undergroundTicketDiscardStack = new Stack<>();
	private int round = 1;

	public GameState() throws IOException {
		Reader reader = new Reader();
		reader.read("resources/graph.txt");
		reader.readNodeLocations();
		graph = reader.graph();
		nodeLocations = reader.getNodeLocations();
		store = new PersistentStore(this);
	}

	public GameState(int numberOfDetectives) throws IOException {
		this();
		initialiseGame(numberOfDetectives);
	}

	public List<Detective> getDetectives() {
		return detectives;
	}

	public void setDetectives(List<Detective> detectives) {
		this.detectives = detectives;
	}

	public MrX getMrX() {
		return mrX;
	}

	public void setMrX(MrX mrX) {
		this.mrX = mrX;
	}

	public void setMrXIdList(List<Integer> mrXIdList) {
		this.mrXIdList = mrXIdList;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Integer getNumberOfDetectives() {
		return numberOfDetectives;
	}

	public void setNumberOfDetectives(Integer numberOfDetectives) {
		this.numberOfDetectives = numberOfDetectives;
	}

	public int getCurrentPlayerId() {
		return currentPlayerId;
	}

	public void setCurrentPlayerId(int currentPlayerId) {
		this.currentPlayerId = currentPlayerId;
	}

	/**
	 * Concrete implementation of the MapVisualisable getMapFilename function
	 * 
	 * @return The map filename
	 */
	public String getMapFilename() {
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
		List<Integer> detectiveIdList = new ArrayList<>();
		for (Player detective : getDetectives()) {
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
		this.setNumberOfDetectives(numberOfDetectives);
		initialisePlayers();
		return true;
	}

	private void initialisePlayers() {
		initialiseDetectives();
		initialiseMrX();
		initialisePlayerList();
	}

	private void initialisePlayerList() {
		setPlayers(new ArrayList<Player>());
		getPlayers().addAll(getDetectives());
		getPlayers().add(getMrX());
	}

	private void initialiseDetectives() {
		for (int playerId = 2; playerId <= getNumberOfDetectives() + 1; ++playerId) {
			Detective detective = createDetective(playerId);
			getDetectives().add(detective);
		}

	}

	private Detective createDetective(int playerId) {
		Detective detective = new Detective(playerId);
		detective.setPosition(graph.getRandomUnpickedNode());
		return detective;
	}

	private void initialiseMrX() {
		setMrX(new MrX(this));
		getMrX().setPosition(graph.getRandomUnpickedNode());
		setMrXIdList(new ArrayList<Integer>());
		getMrXIdList().add(getMrX().getPlayerId());
	}

	@Override
	public Integer getNumberOfTickets(TicketType type, Integer playerId) {
		// TODO: Fix
		Player player = getPlayer(playerId);
		return -1;
	}

	private Player getPlayer(Integer playerId) {
		for (Player player : getPlayers()) {
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
		if (currentPlayerId == 1) {
			return true;
		} else {
			switch(round) {
			case 3:
			case 8:
			case 13:
			case 18:
			case 24:
				return true;
			default:
				return false;
			}
		}

	}

	@Override
	public Boolean isGameOver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getNextPlayerToMove() {
		final int numberOfPlayers = numberOfDetectives + 1;

		int playerId = getCurrentPlayerId();
		int nextPlayerId = (playerId % numberOfPlayers) + 1;
		setCurrentPlayerId(nextPlayerId);
		return playerId;
	}

	@Override
	public Integer getWinningPlayerId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean movePlayer(Integer playerId, Integer targetNodeId,
			TicketType ticketType) {
		Player player = getPlayer(playerId);

		if (!player.hasTicket(Ticket.newTicket(ticketType)) && round != 1) {
			return false;
		}
		List<Edge> currentlyConnectedEdges = graph.edges(player.getPosition()
				.name());
		Edge traversableEdge = findTraversableEdge(targetNodeId, ticketType,
				currentlyConnectedEdges);

		if (traversableEdge != null) {
			Node node = graph.find(targetNodeId);
			player.setPosition(node);
			updateDiscardStacks(ticketType, player);
			currentPlayerId = getNextPlayerToMove();
			if (currentPlayerId == numberOfDetectives + 1) {
				round++;
			}
			return true;
		}
		return false;
	}

	private void updateDiscardStacks(TicketType ticketType, Player player) {
		if (player.getClass() != MrX.class) {
			switch (ticketType) {
			case Bus:
				busTicketDiscardStack.push(new BusTicket());
				break;
			case Taxi:
				taxiTicketDiscardStack.push(new TaxiTicket());
				break;
			case Underground:
				undergroundTicketDiscardStack.push(new UndergroundTicket());
				break;
			default:
				break;
			}
		}
	}

	private Edge findTraversableEdge(Integer targetNodeId,
			TicketType ticketType, List<Edge> currentlyConnectedEdges) {
		System.out.println(currentlyConnectedEdges.size());
		for (Edge edge : currentlyConnectedEdges) {
			String nodeId = Integer.toString(targetNodeId);
			if (edge.connectsNode(nodeId)
					&& edgeCanBeTraversedByTicket(edge.type(), ticketType)) {
				return edge;
			}
		}
		return null;
	}

	private boolean edgeCanBeTraversedByTicket(EdgeType edgeType,
			TicketType ticketType) {
		System.out.println(edgeType.toString() + " " + ticketType.toString());
		return edgeType.toString().equals(ticketType.toString());
	}

	@Override
	public Integer getNodeIdFromLocation(Integer xPosition, Integer yPosition) {
		Point clickPosition = new Point(xPosition, yPosition);
		for (Entry<Node, Point> nodeLocationPair : nodeLocations.entrySet()) {
			if (clickIsWithinNodeFuzzyBounds(clickPosition, nodeLocationPair)) {
				Node node = nodeLocationPair.getKey();
				Integer nodeId = Integer.parseInt(node.name());
				return nodeId;
			}
		}
		return null;
	}

	private boolean clickIsWithinNodeFuzzyBounds(Point clickPoint,
			Entry<Node, Point> entry) {
		Point nodePoint = entry.getValue();
		if (clickIsWithinNodeXBound(clickPoint, nodePoint)
				&& clickIsWithinNodeYBound(clickPoint, nodePoint)) {
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
		return store.saveGame(filename);
	}

	@Override
	public Boolean loadGame(String filename) {
		return store.loadGame(filename);
	}

	public int getBusDiscardStackSize() {
		return busTicketDiscardStack.size();
	}

	public int getTaxiDiscardStackSize() {
		return taxiTicketDiscardStack.size();
	}

	public int getUndergroundDiscardStackSize() {
		return undergroundTicketDiscardStack.size();
	}
}
