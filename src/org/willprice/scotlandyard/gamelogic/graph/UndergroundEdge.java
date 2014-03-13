package org.willprice.scotlandyard.gamelogic.graph;

public class UndergroundEdge extends Edge {
	public UndergroundEdge(String id1, String id2, double weight) {
		this(id1, id2, weight, null);
	}

	public UndergroundEdge(String id1, String id2, double weight, EdgeType type) {
		super(id1, id2, weight, Edge.EdgeType.Underground);
	}

}
