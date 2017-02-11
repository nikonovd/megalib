package org.softlang.megalib.visualizer;

import org.softlang.megalib.visualizer.models.*;
import org.softlang.megalib.visualizer.utils.GraphExporter;
import org.softlang.megalib.visualizer.utils.VisualizerType;

/**
 * 
 * @author gfial
 *
 */
public class TransformGraph implements VisualizationTransformer<Node, Edge> {

	private GraphExporter exporter;
	
	public TransformGraph(VisualizerType type) {
		
		exporter = type.getExporter();
	}
	
	@Override
	public void transformNode(Node node) {

		exporter.createNode(node.getName());
		
	}

	@Override
	public void transformEdge(Edge edge) {
		
		exporter.createEdge(edge.getLabel(), edge.getOrigin().getName(), edge.getDestination().getName());		
	}

}
