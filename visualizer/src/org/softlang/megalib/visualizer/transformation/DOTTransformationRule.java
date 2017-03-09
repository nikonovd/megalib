package org.softlang.megalib.visualizer.transformation;

import java.util.Optional;
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
        ConfigItem<String, String> config = getConfigItem(node);

        return "\"" + node.getName() + "\"" + " [shape=\"" + getConfigValue(config, "shape") + "\" color=\"" + getConfigValue(config, "color") + "\" label=\"" + node.getName() + "\"" + (node.getLink().isEmpty() ? "" : (" URL=\"" + node.getLink() + "\"")) + "];";
    }

    private String getConfigValue(ConfigItem<String, String> config, String key) {
        return config.get(key).map(Optional::of).orElse(DEFAULT_CONFIG.get(key)).get();
    }

    @Override
    public String transformEdge(Edge edge) {
        return "\"" + edge.getOrigin().getName()
            + "\"" + " -> " + "\""
            + (edge.getDestination() == null ? "yet_undefined" : edge.getDestination().getName())
            + "\"" + " [label=\"" + edge.getLabel() + "\"];";
    }

    private ConfigItem<String, String> getConfigItem(Node node) {
        Optional<String> key = getApplyingKey(node);
        if (!key.isPresent()) {
            return DEFAULT_CONFIG;
        }
        return key.flatMap(config::get).get();
    }

    private Optional<String> getApplyingKey(Node node) {
        if (config.contains(node.getName())) {
            return Optional.of(node.getName());
        }
        if (config.contains(node.getType())) {
            return Optional.of(node.getType());
        }
        // todo: upper type hierarchy
        return Optional.empty();
    }

}
