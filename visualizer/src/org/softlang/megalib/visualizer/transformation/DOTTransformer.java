/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer.transformation;

import org.softlang.megalib.visualizer.VisualizerOptions;
import org.softlang.megalib.visualizer.models.Edge;
import org.softlang.megalib.visualizer.models.Graph;
import org.softlang.megalib.visualizer.models.Node;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class DOTTransformer extends Transformer<String> {

    private DOTTransformationRule rule = new DOTTransformationRule();

    public DOTTransformer(VisualizerOptions options) {
        super(options);
    }

    @Override
    public String transform(Graph g) {
        if (g == null) {
            throw new IllegalArgumentException();
        }
        ManifestationDetacher detacher = new ManifestationDetacher();

        g.forEachNode(n -> detacher.processNode(n));

        return process(g).toString();
    }

    private StringBuilder process(Graph g) {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph ")
            .append(options.getModelName())
            .append(" {")
            .append("\n")
            .append("\trankdir = LR;")
            .append("\n");

        g.forEachNode(n -> appendNode(sb, n));

        sb.append("\n\n");

        g.forEachEdge(e -> appendEdge(sb, e));

        sb.append("}");

        return sb;
    }

    private void appendNode(StringBuilder sb, Node node) {
        sb.append("\t")
            .append(rule.transformNode(node))
            .append("\n");
    }

    private void appendEdge(StringBuilder sb, Edge edge) {
        sb.append("\t")
            .append(rule.transformEdge(edge))
            .append("\n");
    }

}
