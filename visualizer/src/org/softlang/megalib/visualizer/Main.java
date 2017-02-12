/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer;

import java.io.File;
import java.io.IOException;
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

            ModelLoader loader = new ModelLoader("../models/Prelude.megal");
            loader.loadFile(options.getFilePath().toString());
            MegaModel model = loader.getModel();
            Visualizer<File> visualizer = new FileVisualizer(model, options);
            
        } catch (MegaModelVisualizerException | IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

}
