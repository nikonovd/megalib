/*
 *  All rights reserved.
 */
package main.org.softlang.megalib.visualizer.transformation;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import main.org.softlang.megalib.visualizer.VisualizerOptions;
import main.org.softlang.megalib.visualizer.models.Graph;
import main.org.softlang.megalib.visualizer.models.Node;
import main.org.softlang.megalib.visualizer.models.configuration.ConfigItem;
import main.org.softlang.megalib.visualizer.models.configuration.TransformerConfiguration;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class DOTTransformer extends Transformer<String> {

    private static final ConfigItem<String, String> DEFAULT_CONFIG = new ConfigItem<String, String>()
        .put("color", "black")
        .put("shape", "oval");

    private TransformerConfiguration config = new DOTConfigurationBuilder().buildConfiguration();

    public DOTTransformer(VisualizerOptions options) {
        super(options);
    }

    @Override
    public String transform(Graph g) {
        if (g == null) {
            throw new IllegalArgumentException();
        }
        ManifestationDetacher detacher = new ManifestationDetacher();

        detacher.processGraph(g);

        return process(g);
    }

    private String process(Graph g) {
        STGroup templateGroup = new STGroupFile("graphviz.stg");
        ST template = templateGroup.getInstanceOf("graph");

        List<DOTNode> nodes = new LinkedList<>();

        g.forEachNode(n -> nodes.add(createDOTNode(n)));

        template.add("name", options.getModelName());
        template.add("nodes", nodes);
        template.add("edges", g.getEdges());

        return template.render();
    }

    private String getConfigValue(Node node, String attribute) {
        return getConfigItem(node, attribute).get(attribute);
    }

    private ConfigItem<String, String> getConfigItem(Node node, String attribute) {
        for (String key : getKeyHierarchy(node)) {
            if (config.contains(key) && config.get(key).contains(attribute)) {
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

    private DOTNode createDOTNode(Node node) {
        return new DOTNode(node, getConfigValue(node, "color"), getConfigValue(node, "shape"));
    }

}
