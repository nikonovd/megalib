/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.java.megalib.checker.services.ModelLoader;
import org.java.megalib.models.MegaModel;
import org.softlang.megalib.visualizer.cli.CommandLine;
import org.softlang.megalib.visualizer.utils.VisualizerType;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class Main {

    public static void main(String[] args) {

        try {
            CommandLine cli = new CommandLine(VisualizerType.names())
                .parse(args);
            VisualizerOptions options = VisualizerOptions.of(cli.getRequiredArguments());

            ModelLoader loader = new ModelLoader();
            loader.loadFile(options.getFilePath().toString());
            MegaModel model = loader.getModel();
            System.out.println(model);
        } catch (MegaModelVisualizerException | IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

}
