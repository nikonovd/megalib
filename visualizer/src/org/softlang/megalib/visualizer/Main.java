/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer;

import java.io.PrintWriter;
import org.java.megalib.checker.services.MegaModelLoader;
import org.java.megalib.models.MegaModel;
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

        if (!options.getFilePath().toFile().exists()) {
            System.out.println("Could not find any file at location " + options.getFilePath());
            System.exit(-1);
        }
        MegaModelLoader loader = new MegaModelLoader();
        loader.loadFile(options.getFilePath().toString());
        MegaModel model = loader.getModel();
        System.out.println(model);
    }

}
