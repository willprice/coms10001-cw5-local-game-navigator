package org.willprice.scotlandyard.gamelogic;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.willprice.scotlandyard.Reader;
import org.willprice.scotlandyard.gamelogic.graph.Edge;
import org.willprice.scotlandyard.gamelogic.graph.Graph;
import org.willprice.scotlandyard.gamelogic.graph.Node;
import org.willprice.scotlandyard.gamelogic.graph.Edge.EdgeType;
import org.willprice.scotlandyard.gamelogic.player.Detective;
import org.willprice.scotlandyard.gamelogic.player.MrX;
import org.willprice.scotlandyard.gamelogic.player.Player;
import org.willprice.scotlandyard.gamelogic.tickets.Ticket;
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
	public int round = 1;
	private int numberOfRounds = 23;


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
		setMrX(new MrX());
		getMrX().setPosition(graph.getRandomUnpickedNode());
		setMrXIdList(new ArrayList<Integer>());
		getMrXIdList().add(getMrX().getPlayerId());
	}

	@Override
	public Integer getNumberOfTickets(TicketType type, Integer playerId) {
		Player player = getPlayer(playerId);
		return player.getNumberOfTickets(type);
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
		Player player = getPlayer(playerId);
		return player.getMoveList();
	}

	@Override
	public Boolean isVisible(Integer playerId) {
		if (currentPlayerId == getMrX().getPlayerId()) {
			return true;
		} else {
			switch(round) {
			case 3:
			case 8:
			case 13:
			case 18:
			case 24:
				return true;
			}
		}
		return false;
	}

	@Override
	public Boolean isGameOver() {
		if (round >= numberOfRounds) {
			return true;
		}
		for (Detective detective : detectives) {
			if (mrX.getPosition().equals(detective.getPosition())) {
				return true;				
			}
		}
		return false;
	}

	@Override
	public Integer getNextPlayerToMove() {
		final int numberOfPlayers = numberOfDetectives + 1;

		int playerId = getCurrentPlayerId();
		int nextPlayerId = (playerId % numberOfPlayers) + 1;
		setCurrentPlayerId(nextPlayerId);
		return nextPlayerId;
	}

	@Override
	public Integer getWinningPlayerId() {
		for (Detective detective : detectives) {
			if (detective.getPosition() == mrX.getPosition())
				return detective.getPlayerId();
		}
		return mrX.getPlayerId();
	}

	@Override
	public Boolean movePlayer(Integer playerId, Integer targetNodeId,
			TicketType ticketType) {
		Player player = getPlayer(playerId);
		System.out.println("Moving player: " + player);
		System.out.println("State MrX TaxiTickets: " + mrX.taxiTicketDiscardStack + "\n");
		System.out.println("Player has " + ticketType + "-type ticket? " + player.hasTicket(Ticket.newTicket(ticketType)));

		if (!player.hasTicket(Ticket.newTicket(ticketType))) {
			return false;
		}
		String currentNodeId = player.getPosition().name();
		Edge edge = graph.findTraversableEdge(currentNodeId, targetNodeId.toString(), ticketType);
		
		if (edge != null) {
			player.move(edge, ticketType);
			updateDiscardStacks(ticketType, player);
			updateRound();
			return true;
		}
		
		return false;
	}
	
	private void updateRound() {
		if (isEndOfRound()) {
			round++;
		}
	}

	private boolean isEndOfRound() {
		return currentPlayerId == numberOfDetectives + 1;
	}

	private void updateDiscardStacks(TicketType ticketType, Player player) {
		if (player.getClass() != MrX.class) {
			System.out.println("Adding tickets to MrX: " + getMrX());
			addTicketToDiscardStack(ticketType);
		}
	}

	private void addTicketToDiscardStack(TicketType ticketType) {
		getMrX().addTicketToDiscardStack(ticketType);
	}

	public boolean edgeCanBeTraversedByTicket(EdgeType edgeType,
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
		int delta = 10;
		return ((clickPoint.x >= (nodePoint.x - delta)) && (clickPoint.x <= (nodePoint.x + delta)));
	}

	private boolean clickIsWithinNodeYBound(Point clickPoint, Point nodePoint) {
		int delta = 10;
		return ((clickPoint.y >= (nodePoint.y - delta)) && (clickPoint.y <= (nodePoint.y + delta)));
	}

	@Override
	public Boolean saveGame(String filename) {
		return store.saveGame(filename);
	}

	@Override
	public Boolean loadGame(String filename) {
		return store.loadGame(filename);
	}

	public void setRound(Integer round) {
		this.round = round;
	}

	public Integer getRound() {
		return round;
	}
}
