/*
 *  All rights reserved.
 */
package main.org.softlang.megalib.visualizer;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import main.org.softlang.megalib.visualizer.exceptions.MegaModelVisualizerException;
import main.org.softlang.megalib.visualizer.models.Graph;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class FileVisualizer implements Visualizer<File> {
    
    private StringVisualizer delegated;
    
    private VisualizerOptions options;
    
    public FileVisualizer(VisualizerOptions options) {
        this.options = options;
        this.delegated = new StringVisualizer(options);
    }
    
    @Override
    public File create(Graph graph) {
        String transformed = delegated.create(graph);
        
        String path = options.getModelName() + "." + FileExtensionFactory.get(options.getTransformationType());
        
        try {
            return Files.write(Paths.get(path), transformed.getBytes(StandardCharsets.UTF_8)).toFile();
        } catch (IOException ex) {
            throw new MegaModelVisualizerException(ex);
        }
    }

}
