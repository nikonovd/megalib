/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer;

import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.java.megalib.checker.services.ModelLoader;
import org.java.megalib.models.MegaModel;
import org.java.megalib.parser.ParserException;
import org.softlang.megalib.visualizer.exceptions.ModelReaderException;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class ModelReader {

    private VisualizerOptions options;

    private ModelLoader loader = new ModelLoader("../models/Prelude.megal");

    public ModelReader(VisualizerOptions options) {
        this.options = options;
    }

    public MegaModel read() throws ModelReaderException {
        return parse(readFile());
    }

    private MegaModel parse(String data) throws ModelReaderException {
        try {
            return loader.parse(data, new ModelParseListener()).getModel();
        } catch (ParserException | IOException ex) {
            throw new ModelReaderException(ex);
        }
    }

    private String readFile() throws ModelReaderException {
        try {
            return Files.lines(options.getFilePath()).collect(Collectors.joining("\n"));
        } catch (IOException ex) {
            throw new ModelReaderException(ex);
        }
    }
}
