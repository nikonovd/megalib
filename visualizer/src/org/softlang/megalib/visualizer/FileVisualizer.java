/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.softlang.megalib.visualizer.models.Graph;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class FileVisualizer implements Visualizer<File> {
    
    private StringVisualizer delegated;
    
    private VisualizerOptions options;
    
    public FileVisualizer(VisualizerOptions options) {
        this.options = options;
        delegated = new StringVisualizer(options);
    }
    
    @Override
    public File create(Graph graph) {
        String transformed = delegated.create(graph);
        
        String path = options.getModelName() + "." + options.getType().getFileExtension();
        
        try {
            return Files.write(Paths.get(path), transformed.getBytes(StandardCharsets.UTF_8)).toFile();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
