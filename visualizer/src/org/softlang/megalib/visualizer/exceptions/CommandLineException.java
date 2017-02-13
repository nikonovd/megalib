/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer.exceptions;

import org.softlang.megalib.visualizer.MegaModelVisualizerException;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class CommandLineException extends MegaModelVisualizerException {

    public CommandLineException(String message) {
        super(message);
    }

    public CommandLineException(Throwable cause) {
        super(cause);
    }
}
