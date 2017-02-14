package org.softlang.megalib.visualizer.transformation;

import org.softlang.megalib.visualizer.models.*;

/**
 *
 * @author gfial
 *
 */
class DOTTransformationRule implements VisualizationRule<Node, Edge> {

    @Override
    public String transformNode(Node node) {
        return "\"" + node.getName() + "\"" + " [label=\"" + node.getName() + "\"]";
    }

    @Override
    public String transformEdge(Edge edge) {
        return "\"" + edge.getOrigin().getName()
            + "\"" + " -> " + "\""
            + (edge.getDestination() == null ? "yet_undefined" : edge.getDestination().getName())
            + "\"" + " [label=\"" + edge.getLabel() + "\"]";
    }

}
