/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer.models.configuration;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class NodeConfiguration {

    private String shape;

    private String color;

    public NodeConfiguration(String shape, String color) {
        this.shape = shape;
        this.color = color;
    }

    public String getShape() {
        return shape;
    }

    public String getColor() {
        return color;
    }

}
