/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer;

import org.softlang.megalib.visualizer.models.Graph;
import org.softlang.megalib.visualizer.transformation.Transformer;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class StringVisualizer implements Visualizer<String> {

    private Transformer<? extends String> transformer;

    public StringVisualizer(VisualizerOptions options) {
        this.transformer = Transformer.getInstance(options, String.class);
    }

    @Override
    public String create(Graph graph) {
        return transformer.transform(graph);
    }

}
