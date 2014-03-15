package org.willprice.scotlandyard.gamelogic.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.willprice.scotlandyard.gamelogic.Initialisable.TicketType;
import org.willprice.scotlandyard.gamelogic.graph.Edge.EdgeType;

/**
 * Class that represents a graph. This class is based around a List of nodes and
 * a List of edges. The nodes are very simple classes that only contain the name
 * of the node. The edges are more important as they define the structure of the
 * graph.
 */
public class Graph {
	private List<Node> nodes;
	private List<Edge> edges;
	private ArrayList<Node> initialNodes;

	/**
	 * Graph constructor. This initialises the lists that will hold the nodes
	 * and edges
	 */
	public Graph() {
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
		initialNodes = new ArrayList<Node>();
	}

	/**
	 * Function that returns the list of nodes from the graph
	 * 
	 * @return The list of nodes
	 */
	public List<Node> nodes() {
		return nodes;
	}

	/**
	 * Function that returns the list of edges from the graph
	 * 
	 * @return The list of edges
	 */
	public List<Edge> edges() {
		return edges;
	}

	/**
	 * Function to find a node in the graph given the nodes name. This function
	 * will search through the list of nodes and check each of their names. If
	 * it finds a matching node, it will be returned. If not, it will return
	 * null.
	 * 
	 * @param name
	 *            The name of the node that you wish to find
	 * @return The found node or null
	 */
	public Node find(String name) {
		for (Node n : nodes) {
			if (n.name().equals(name))
				return n;
		}
		return null;
	}

	/**
	 * Finds a node given an id. The id represents the position of the node in
	 * the list of nodes If the id is out of bounds, null is returned
	 * 
	 * @param id
	 *            - The index of the node - 1
	 * @return The desired node or null
	 */
	public Node find(int id) {
		if (id < 0 || id >= nodeNumber()) {
			return null;
		}
		return nodes.get(id - 1);
	}

	/**
	 * Returns the number of nodes in the graph
	 * 
	 * @return The number of nodes in the graph
	 */
	public int nodeNumber() {
		return nodes.size();
	}

	/**
	 * Function to add a new node to the graph
	 * 
	 * @param node
	 *            The node you wish to add
	 */
	public void add(Node node) {
		nodes.add(node);
		initialNodes.add(node);
	}

	/**
	 * Function to add a new edge to the graph
	 * 
	 * @param edge
	 *            The edge you wish to add
	 */
	public void add(Edge edge) {
		edges.add(edge);
	}

	/**
	 * Function to get the set of edges that blong to a given node
	 * 
	 * @param nodeName
	 *            The name of the node
	 * @return List of edges
	 */
	public List<Edge> edges(String nodeName) {
		List<Edge> output = new ArrayList<Edge>();
		for (Edge edge : edges) {
			if (edge.connectsNode(nodeName)) {
				output.add(edge);
			}
		}

		return output;
	}

	public Node getRandomUnpickedNode() {
		Random randomNumber = new Random();

		int numberOfNodes = initialNodes.size();
		int randomNodeIndex = randomNumber.nextInt(numberOfNodes);
		Node node = initialNodes.get(randomNodeIndex);
		initialNodes.remove(node);
		return node;
	}

	public Edge findTraversableEdge(Integer targetNodeId, TicketType ticketType, List<Edge> currentlyConnectedEdges) {
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

	public boolean edgeCanBeTraversedByTicket(EdgeType edgeType, TicketType ticketType) {
		System.out.println(edgeType.toString() + " " + ticketType.toString());
		return edgeType.toString().equals(ticketType.toString());
	}
}