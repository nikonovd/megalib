/*
 *  All rights reserved.
 */
package main.org.softlang.megalib.visualizer.transformation;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import main.org.softlang.megalib.visualizer.VisualizerOptions;
import main.org.softlang.megalib.visualizer.models.Graph;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 * @param <R> the return type of the transformer
 */
public abstract class Transformer<R> {

    private static final Map<String, Function<VisualizerOptions, ? extends Transformer>> TRANSFORMERS = new TreeMap<>();
    
    static {
        registerTransformer("graphviz", (options) -> new DOTTransformer(options));
    }
    
    public static void registerTransformer(String name, Function<VisualizerOptions, ? extends Transformer> creatingFunc) {
        TRANSFORMERS.put(name, creatingFunc);
    }
    
    public static <R> Transformer<R> getInstance(VisualizerOptions options) {
        return TRANSFORMERS.entrySet().stream().filter(e -> e.getKey().equals(options.getTransformationType())).map(Entry::getValue).findFirst().orElseThrow(IllegalStateException::new).apply(options);
    }
    
    public static List<String> getRegisteredTransformerNames() {
        return TRANSFORMERS.keySet().stream().collect(Collectors.toList());
    }
    
    protected VisualizerOptions options;

    public Transformer(VisualizerOptions options) {
        this.options = options;
    }

    public abstract R transform(Graph g);

}
