/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 * @param <T> The type of the target where the graph shall be written into
 */
public interface Visualizer<T> {

    public T create();

}
