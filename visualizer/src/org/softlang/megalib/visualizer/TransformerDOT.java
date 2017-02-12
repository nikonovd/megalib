package org.softlang.megalib.visualizer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;

import org.softlang.megalib.visualizer.models.*;

/**
 * 
 * @author gfial
 *
 */
public class TransformerDOT implements VisualizationTransformer<Node, Edge> {
	
	private PrintWriter writer;
	
	public TransformerDOT(Path path) throws FileNotFoundException, UnsupportedEncodingException {
		
		// TODO Create the file at the given path.
		
	    writer = new PrintWriter("megal-visualization.dot", "UTF-8");
	    writer.println("graph megal {");
	}
	
	@Override
	public void transformNode(Node node) {

		// TODO make a node in dot language
		writer.println(node.getName() + " [label=\"" + node.getName() + "\"]");
		
	}

	@Override
	public void transformEdge(Edge edge) {
		
		// TODO make an edge in dot language
		writer.println(edge.getOrigin() + "->" + edge.getDestination() + " [label=\"" + edge.getLabel() + "\"]");
	}
	
	/**
	 * To be called when the transforming is over.
	 */
	public void close() {
		writer.println("}");
		writer.close();
	}

}
