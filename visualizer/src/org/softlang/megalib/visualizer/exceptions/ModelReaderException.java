/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer.exceptions;

import org.softlang.megalib.visualizer.MegaModelVisualizerException;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class ModelReaderException extends MegaModelVisualizerException {

    public ModelReaderException(String message) {
        super(message);
    }

    public ModelReaderException(Throwable cause) {
        super(cause);
    }

}
