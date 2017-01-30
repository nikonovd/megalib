/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.softlang.megalib.visualizer.utils.VisualizerType;
import org.softlang.megalib.visualizer.cli.CommandLineArguments;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class VisualizerOptions {

    public static VisualizerOptions of(CommandLineArguments args) {
        Path filePath = Paths.get(args.getFilePath());
        return new VisualizerOptions(filePath.toAbsolutePath(), VisualizerType.valueOf(args.getType()));
    }

    private Path filePath;

    private VisualizerType type;

    VisualizerOptions(Path filePath, VisualizerType type) {
        this.filePath = filePath;
        this.type = type;
    }

    public Path getFilePath() {
        return filePath;
    }

    public VisualizerType getType() {
        return type;
    }

}
