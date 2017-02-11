/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
     * Get the exporter class that handles the graph creation with this visualizer type.
     * @return the GraphExporter implementation for the type.
     */
    public GraphExporter getExporter() {
    	switch (this) {
    	case YED:
    		return new GraphExporterGML();
		default:
			break;
    	}
		return new GraphExporterDot();
    }

}
