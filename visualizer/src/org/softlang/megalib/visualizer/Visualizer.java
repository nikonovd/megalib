/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 * @param <N> The type of a node within the graph
 * @param <E> The type of an edge within the graph
 * @param <T> The type of the target where the graph shall be written into
 */
public interface Visualizer<N, E, T> {

    public void transformEdge(E edge);

    public void transformNode(N node);

    public void writeTo(T target);

}
