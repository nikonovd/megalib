/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer.transformation.yed;

import org.softlang.megalib.visualizer.VisualizerOptions;
import org.softlang.megalib.visualizer.exceptions.MegaModelVisualizerException;
import org.softlang.megalib.visualizer.models.Graph;
import org.softlang.megalib.visualizer.transformation.Transformer;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class YEDTransformer extends Transformer<String> {

    static {
        Transformer.registerTransformer("yed", (VisualizerOptions options) -> new YEDTransformer(options));
    }
    
    public YEDTransformer(VisualizerOptions options) {
        super(options);
    }

    @Override
    public String transform(Graph g) {
        throw new MegaModelVisualizerException("Not supported yet.");
    }

}
