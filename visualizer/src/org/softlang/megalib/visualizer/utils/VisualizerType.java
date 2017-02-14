/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.softlang.megalib.visualizer.transformation.DOTTransformer;
import org.softlang.megalib.visualizer.transformation.Transformer;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public enum VisualizerType {

    GRAPHVIZ {
        @Override
        public String getFileExtension() {
            return "dot";
        }
    }, YED {
        @Override
        public String getFileExtension() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    public static Collection<String> names() {
        return Collections.unmodifiableList(
            Stream.of(values()).map(type -> type.name().toLowerCase()).collect(Collectors.toList())
        );
    }

    public abstract String getFileExtension();

    public Class<? extends Transformer<?>> getTransformerClass() {
        switch (this) {
            case GRAPHVIZ:
                return DOTTransformer.class;
            case YED:
                throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException();
    }
}
