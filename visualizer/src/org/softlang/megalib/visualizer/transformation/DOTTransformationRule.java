package org.softlang.megalib.visualizer.transformation;

import org.softlang.megalib.visualizer.models.*;
import org.softlang.megalib.visualizer.models.configuration.GraphConfiguration;
import org.softlang.megalib.visualizer.models.configuration.NodeConfiguration;

/**
 *
 * @author gfial
 *
 */
class DOTTransformationRule implements VisualizationRule<Node, Edge> {

    private GraphConfiguration config;

    public DOTTransformationRule(GraphConfiguration config) {
        this.config = config;
    }

    @Override
    public String transformNode(Node node) {
        NodeConfiguration nodeConfig = config.getNodeConfiguration(node.getType());
        if (nodeConfig == null) {
            return "\"" + node.getName() + "\"" + " [label=\"" + node.getName() + "\"" + (node.getLink().isEmpty() ? "" : (" URL=\"" + node.getLink() + "\"")) + "];";
        }
        return "\"" + node.getName() + "\"" + " [shape=\"" + nodeConfig.getShape() + "\" color=\"" + nodeConfig.getColor() + "\" label=\"" + node.getName() + "\"" + (node.getLink().isEmpty() ? "" : (" URL=\"" + node.getLink() + "\"")) + "];";
    }

    @Override
    public String transformEdge(Edge edge) {
        return "\"" + edge.getOrigin().getName()
            + "\"" + " -> " + "\""
            + (edge.getDestination() == null ? "yet_undefined" : edge.getDestination().getName())
            + "\"" + " [label=\"" + edge.getLabel() + "\"];";
    }

}
