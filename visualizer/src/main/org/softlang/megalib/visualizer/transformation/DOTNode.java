/*
 *  All rights reserved.
 */
package main.org.softlang.megalib.visualizer.transformation;

import main.org.softlang.megalib.visualizer.models.Node;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class DOTNode extends Node {

    private String color;

    private String shape;

    public DOTNode(Node original, String color, String shape) {
        super(original.getType(), original.getName(), original.getLink(), original.getInstanceHierarchy(), original.getEdges());
        this.color = color;
        this.shape = shape;
    }

    public String getColor() {
        return color;
    }

    public String getShape() {
        return shape;
    }

}