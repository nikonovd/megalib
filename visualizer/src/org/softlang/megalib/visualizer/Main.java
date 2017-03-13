/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer;

import org.java.megalib.parser.ParserException;
import org.softlang.megalib.visualizer.cli.CommandLine;
import org.softlang.megalib.visualizer.models.Graph;
import org.softlang.megalib.visualizer.models.GraphFactory;
import org.softlang.megalib.visualizer.transformation.Transformer;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class Main {

    public static void main(String[] args) throws ParserException {

        try {
            CommandLine cli = new CommandLine(Transformer.getRegisteredTransformerNames())
                .parse(args);
            VisualizerOptions options = VisualizerOptions.of(cli.getRequiredArguments());

            Graph graph = new GraphFactory(options).create();
            
            FileVisualizer visualizer = new FileVisualizer(options);
            visualizer.create(graph);
            
            System.out.println("Visualization complete.");
        } catch (MegaModelVisualizerException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

}
