/*
 *  All rights reserved.
 */
package main.org.softlang.megalib.visualizer;

import main.org.softlang.megalib.visualizer.exceptions.MegaModelVisualizerException;
import org.java.megalib.parser.ParserException;
import main.org.softlang.megalib.visualizer.cli.CommandLine;
import main.org.softlang.megalib.visualizer.models.Graph;
import main.org.softlang.megalib.visualizer.models.GraphFactory;
import main.org.softlang.megalib.visualizer.transformation.Transformer;

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
