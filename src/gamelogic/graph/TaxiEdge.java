package gamelogic.graph;

public class TaxiEdge extends Edge {

	public TaxiEdge(String id1, String id2, double weight, EdgeType type) {
		super(id1, id2, weight, Edge.EdgeType.Taxi);
	}
	
	public TaxiEdge(String id1, String id2, double weight) {
		this(id1, id2, weight, null);
	}

}
