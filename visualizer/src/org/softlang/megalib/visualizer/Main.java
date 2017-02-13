/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer;

import org.java.megalib.parser.ParserException;
import org.softlang.megalib.visualizer.cli.CommandLine;
import org.softlang.megalib.visualizer.models.Graph;
import org.softlang.megalib.visualizer.models.GraphFactory;
import org.softlang.megalib.visualizer.utils.VisualizerType;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class Main {

    public static void main(String[] args) throws ParserException {

        try {
            CommandLine cli = new CommandLine(VisualizerType.names())
                .parse(args);
            VisualizerOptions options = VisualizerOptions.of(cli.getRequiredArguments());

            Graph graph = new GraphFactory(options).create();
            
            System.out.println(graph);

        } catch (MegaModelVisualizerException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

}
