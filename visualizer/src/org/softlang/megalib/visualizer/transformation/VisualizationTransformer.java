/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer.transformation;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 *
 * @param <N> The type of a node within the graph
 * @param <NR> The return type of the node transformation
 * @param <E> The type of an edge within the graph
 * @param <ER> The return type of the edge transformation
 */
public interface VisualizationTransformer<N, NR, E, ER> {

    public NR transformNode(N node);

    public ER transformEdge(E edge);

}
