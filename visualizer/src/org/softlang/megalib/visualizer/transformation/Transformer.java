/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer.transformation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.softlang.megalib.visualizer.VisualizerOptions;
import org.softlang.megalib.visualizer.models.Graph;
import org.softlang.megalib.visualizer.utils.Tuple;
import org.softlang.megalib.visualizer.utils.VisualizerType;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 * @param <R> the return type of the transformer
 */
public abstract class Transformer<R> {

    private static final Map<Tuple<VisualizerType, Class<?>>, Function<VisualizerOptions, ? extends Transformer>> TRANSFORMERS = new HashMap<>();
    
    static {
        TRANSFORMERS.put(new Tuple<>(VisualizerType.GRAPHVIZ, String.class), (options) -> new DOTTransformer(options));
    }
    
    public static void registerTransformer(VisualizerType type, Class<?> clazz, Function<VisualizerOptions, ? extends Transformer> creatingFunc) {
        TRANSFORMERS.put(new Tuple<>(type, clazz), creatingFunc);
    }
    
    public static <R> Transformer<R> getInstance(VisualizerOptions options, Class<R> transformerClazz) {
        return (Transformer<R>) TRANSFORMERS.get(new Tuple<>(options.getType(), transformerClazz)).apply(options);
    }
    
    protected VisualizerOptions options;

    public Transformer(VisualizerOptions options) {
        this.options = options;
    }

    public abstract R transform(Graph g);

}
