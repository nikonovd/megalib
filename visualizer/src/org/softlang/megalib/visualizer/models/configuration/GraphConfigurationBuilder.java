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
public class GraphConfigurationBuilder {
    
    private Map<String, NodeConfiguration> configs = new TreeMap<>();
    
    public NodeConfigurationBuilder createNodeConfiguration() {
        return new NodeConfigurationBuilder(this);
    }

    void addNodeConfiguration(String name, NodeConfiguration config) {
        configs.put(name, config);
    }
    
    public GraphConfiguration buildConfig() {
        return new GraphConfiguration(configs);
    }
    
}
