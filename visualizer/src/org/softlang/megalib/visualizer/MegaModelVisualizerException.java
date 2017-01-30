/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public abstract class MegaModelVisualizerException extends Exception {

    public MegaModelVisualizerException(String message) {
        super(message);
    }

    public MegaModelVisualizerException(Throwable cause) {
        super(cause);
    }
}
