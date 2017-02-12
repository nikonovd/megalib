/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer.utils;

import java.nio.file.Path;
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
     * @param path The path to which the exported Graph will be written.
     * @return A GraphExporter instance for the required visualizer type.
     */
    public GraphExporter getExporter(Path path) {
    	switch (this) {
    	case YED:
    		return new GraphExporterGML(path);
		default:
			break;
    	}
		return new GraphExporterDot(path);
    }

}
