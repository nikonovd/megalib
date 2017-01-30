/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer;

import java.io.PrintWriter;
import org.softlang.megalib.visualizer.cli.CommandLine;
import org.softlang.megalib.visualizer.utils.VisualizerType;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class Main {

    public static void main(String[] args) {
        CommandLine cli = new CommandLine(new PrintWriter(System.out, true), VisualizerType.names())
            .parse(args);

        if (!cli.isValid()) {
            cli.printHelp();
            System.exit(-1);
        }

        VisualizerOptions options = VisualizerOptions.of(cli.getRequiredArguments());
    }

}
