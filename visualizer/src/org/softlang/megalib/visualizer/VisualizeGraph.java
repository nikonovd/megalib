package org.softlang.megalib.visualizer;

import org.java.megalib.models.MegaModel;
import org.softlang.megalib.visualizer.models.Graph;

/**
 * This implementation of the visualizer allows the conversion of a MegaModel into a Graph object.
 * @author gfial
 *
 */
public class VisualizeGraph implements Visualizer<Graph> {

	private MegaModel model;
	
	public VisualizeGraph(MegaModel model) {
		
		this.model = model;
	}
	
	/**
	 * Create an Object of type Graph that represents the MegaModel.
	 * @see org.softlang.megalib.visualizer.models.Graph
	 * @return A Graph object.
	 */
	public Graph create() {
		
		// TODO Read the MegaModel and interpret it into a Graph structure.
		
		return null;
	}

}
