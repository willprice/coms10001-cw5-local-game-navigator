package org.willprice.scotlandyard.gamelogic.graph;

/**
 * Class representing a node of the graph
 */
public class Node {
	private String name;

	/**
	 * Node constructor
	 * 
	 * @param n
	 *            Name that will be given to the node
	 */
	public Node(String n) {
		name = n;
	}

	/**
	 * Function that gets the name that has been assigned to the node
	 * 
	 * @return The name of the node
	 */
	public String name() {
		return name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}