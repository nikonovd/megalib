package org.softlang.megalib.visualizer.transformation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.softlang.megalib.visualizer.models.*;
import org.softlang.megalib.visualizer.models.configuration.ConfigItem;
import org.softlang.megalib.visualizer.models.configuration.DOTConfigurationBuilder;
import org.softlang.megalib.visualizer.models.configuration.TransformerConfiguration;

/**
 *
 * @author gfial
 *
 */
class DOTTransformationRule implements VisualizationRule<Node, Edge> {

    private static final ConfigItem<String, String> DEFAULT_CONFIG = new ConfigItem<String, String>()
        .put("color", "black")
        .put("shape", "oval");

    private TransformerConfiguration config = new DOTConfigurationBuilder().buildConfiguration();

    @Override
    public String transformNode(Node node) {
        return "\"" + node.getName() + "\"" + " [shape=\"" + getConfigValue(node, "shape") + "\" color=\"" + getConfigValue(node, "color") + "\" label=\"" + node.getName() + "\"" + (node.getLink().isEmpty() ? "" : (" URL=\"" + node.getLink() + "\"")) + "];";
    }

    private String getConfigValue(Node node, String attribute) {
        return getConfigItem(node, attribute).get(attribute);
    }

    @Override
    public String transformEdge(Edge edge) {
        return "\"" + edge.getOrigin().getName()
            + "\"" + " -> " + "\""
            + (edge.getDestination() == null ? "yet_undefined" : edge.getDestination().getName())
            + "\"" + " [label=\"" + edge.getLabel() + "\"];";
    }

    private ConfigItem<String, String> getConfigItem(Node node, String attribute) {
        for(String key : getKeyHierarchy(node)) {
            if(config.contains(key) && config.get(key).contains(attribute)) {
                return config.get(key);
            }
        }
        return DEFAULT_CONFIG;
    }

    private List<String> getKeyHierarchy(Node node) {
        return Stream.concat(
            Stream.of(node.getName(), node.getType()), 
            node.getInstanceHierarchy().stream()
        ).collect(Collectors.toList());
    }

}
