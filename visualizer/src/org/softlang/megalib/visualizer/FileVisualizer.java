package org.softlang.megalib.visualizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import org.java.megalib.models.MegaModel;
import org.softlang.megalib.visualizer.models.Edge;
import org.softlang.megalib.visualizer.models.Node;

/**
 * This implementation of the visualizer allows the conversion of a MegaModel into a File object
 * <br/>formatted into the Visualizer type specified when constructing the class.
 * @author gfial
 *
 */
public class FileVisualizer implements Visualizer<File> {

	private MegaModel model;
	private VisualizationTransformer<Node, Edge> transformer;
	
	public FileVisualizer(MegaModel model, VisualizerOptions options) throws FileNotFoundException, UnsupportedEncodingException {
		
		this.model = model;
		transformer = options.getType().getTransformer(options.getFilePath());
	}
	
	/**
	 * Create a File that represents the MegaModel on the intended VisualizerType format.
	 * @return A File.
	 */
	public File create() {
		
		// TODO Read the MegaModel and interpret it into a models/Graph structure.
		
		// TODO	Convert the graph into the VisualizerType file format (dot, GraphML).
		
		return null;
	}

}
