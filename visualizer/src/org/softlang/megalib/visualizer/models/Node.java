/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class Node {

    private String type;

    private String name;

    private String link;

    private Set<Edge> edges = new HashSet<>();

    Node(String type, String name, String link) {
        this.type = type;
        this.name = name;
        this.link = link;
    }

    /**
     * Creates a connection between this node and the destination node as an edge between these nodes.
     *
     * @param relation the relation in which the two nodes are standing
     * @param destination the destination node on which the edge will point
     * @return the current node
     */
    public Node connect(String relation, Node destination) {
        this.edges.add(new Edge(this, destination, relation));
        return this;
    }

    /**
     * Returns the name of this node.
     *
     * @return the name of this node
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the link of this node.
     *
     * @return the link of this node if present, or <tt>null</tt> otherwise
     */
    public String getLink() {
        return link;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Objects.hashCode(this.link);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.link, other.link)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t").append(name).append(" {\n");
        
        edges.stream().forEach(e -> sb.append("\t").append(e.toString()).append("\n"));
        
        sb.append("\t").append("}");
        
        return sb.toString();
    }

}
