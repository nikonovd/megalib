/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer.models.configuration;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class NodeConfigurationBuilder {

    private GraphConfigurationBuilder backingConfig;

    private String itemName;

    private String shape;

    private String color;

    public NodeConfigurationBuilder(GraphConfigurationBuilder backingConfig) {
        this.backingConfig = backingConfig;
    }

    public NodeConfigurationBuilder forItem(String name) {
        this.itemName = name;
        return this;
    }

    public NodeConfigurationBuilder shape(String shape) {
        this.shape = shape;
        return this;
    }

    public NodeConfigurationBuilder color(String color) {
        this.color = color;
        return this;
    }
    
    public GraphConfigurationBuilder buildNode() {
        NodeConfiguration result = new NodeConfiguration(shape, color);
        backingConfig.addNodeConfiguration(itemName, result);
        
        return backingConfig;
    }

}
