/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer.utils;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.softlang.megalib.visualizer.TransformerDOT;
import org.softlang.megalib.visualizer.TransformerGML;
import org.softlang.megalib.visualizer.VisualizationTransformer;
import org.softlang.megalib.visualizer.models.Edge;
import org.softlang.megalib.visualizer.models.Node;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public enum VisualizerType {

    GRAPHVIZ, YED;

    public static Collection<String> names() {
        return Collections.unmodifiableList(
            Stream.of(values()).map(type -> type.name().toLowerCase()).collect(Collectors.toList())
        );
    }
    
    /**
     * Get the transformer class that handles the format creation with this visualizer type.
     * @param path - the path to which the exported Graph file will be written.
     * @return A GraphExporter instance for the required visualizer type.
     */
    public VisualizationTransformer<Node, Edge> getTransformer(Path path) {
    	switch (this) {
    	case YED:
    		return new TransformerGML(path);
		default:
			break;
    	}
		return new TransformerDOT(path);
    }

}
