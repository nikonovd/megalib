/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer.models.configuration;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class GraphConfiguration {

    private Map<String, NodeConfiguration> nodeConfigurations;

    GraphConfiguration(Map<String, NodeConfiguration> nodeConfigurations) {
        this.nodeConfigurations = nodeConfigurations;
    }

    public NodeConfiguration getNodeConfiguration(String itemName) {
        return nodeConfigurations.get(itemName);
    }

}
