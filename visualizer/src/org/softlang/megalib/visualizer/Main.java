/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer;

import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;
import org.java.megalib.checker.services.ModelLoader;
import org.java.megalib.models.MegaModel;
import org.java.megalib.parser.ParserException;
import org.softlang.megalib.visualizer.cli.CommandLine;
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

            ModelLoader loader = new ModelLoader("../models/Prelude.megal");
            
            String data = Files.lines(options.getFilePath()).collect(Collectors.joining("\n"));
            
            MegaModel model = loader.parse(data, new ModelParseListener()).getModel();
            
            System.out.println(model.getInstanceOfMap());
            
        } catch (MegaModelVisualizerException | IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

}
