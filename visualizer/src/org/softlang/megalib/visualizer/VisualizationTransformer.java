/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 *
 * @param <N> The type of a node within the graph
 * @param <E> The type of an edge within the graph
 */
public interface VisualizationTransformer<N, E> {

    public void transformNode(N node);

    public void transformEdge(E edge);

}
