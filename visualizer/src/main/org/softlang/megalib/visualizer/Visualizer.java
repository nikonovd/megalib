/*
 *  All rights reserved.
 */
package main.org.softlang.megalib.visualizer;

import main.org.softlang.megalib.visualizer.models.Graph;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 * @param <T> The type of the target where the graph shall be written into
 */
public interface Visualizer<T> {

    public T create(Graph graph);

}
