package org.softlang.megalib.visualizer.transformation;

import org.softlang.megalib.visualizer.models.*;

/**
 *
 * @author gfial
 *
 */
public class DOTTransformer implements VisualizationTransformer<Node, String, Edge, String> {

    @Override
    public String transformNode(Node node) {
        return node.getName() + "[label=\"" + node.getName() + "\"]";
    }

    @Override
    public String transformEdge(Edge edge) {
        return edge.getOrigin() + "->" + edge.getDestination() + " [label=\"" + edge.getLabel() + "\"]";
    }

}
