/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer.transformation;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.softlang.megalib.visualizer.VisualizerOptions;
import org.softlang.megalib.visualizer.models.Graph;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 * @param <R> the return type of the transformer
 */
public abstract class Transformer<R> {

    private static final Map<String, Function<VisualizerOptions, ? extends Transformer>> TRANSFORMERS = new TreeMap<>();

    static {
        // Load all subclasses of it dynamically to call the class initializer that register the concrete Transformer subclass to this factory.
        FastClasspathScanner scanner = new FastClasspathScanner();
        scanner.addClassLoader(Transformer.class.getClassLoader());
        scanner.matchSubclassesOf(Transformer.class, (clazz) -> {
            try {
                Class.forName(clazz.getCanonicalName());
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        scanner.scan();
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
