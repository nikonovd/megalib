package org.softlang.megalib.visualizer.utils;

/**
 * 
 * @author gfial
 *
 */
public interface GraphExporter {
	
	/**
	 * Creates a node on the graph with the specified attributes.
	 * @param name - the name representing this node.
	 */	
	// TODO Pass preferences of color and shape (for diferent node types).
	public void createNode(String name);
	
	/**
	 * Creates an Edge between two nodes with the specified attributes.
	 * @param name - text/information on the edge.
	 * @param from - the node to which the edge comes from.
	 * @param to - the node to which the edge goes to.
	 */
	// TODO Pass preferences of color and shape (for diferent edge types).
	public void createEdge(String name, String from, String to);

}
