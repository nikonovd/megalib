/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer.models;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class Node {

    private String type;

    private String name;

    private String link;
    
    private LinkedList<String> instanceHierarchy = new LinkedList<>();

    private Map<String, Edge> edges = new LinkedHashMap<>();

    Node(String type, String name, String link) {
        this.type = type;
        this.name = name;
        this.link = link;
    }

    public void forEachEdge(Consumer<? super Edge> consumer) {
        edges.values().forEach(consumer);
    }

    public Edge getEdge(String relation) {
        return edges.get(relation);
    }

    /**
     * Creates a connection between this node and the destination node as an edge between these nodes.
     *
     * @param relation the relation in which the two nodes are standing
     * @param destination the destination node on which the edge will point
     * @return the current node
     */
    public Node connect(String relation, Node destination) {
        this.edges.put(relation, new Edge(this, destination, relation));
        return this;
    }

    public Optional<Node> disconnect(String relation) {
        Edge toRemove = this.edges.values().stream().filter(edge -> edge.getLabel().equals(relation)).findFirst().orElse(null);
        if (toRemove == null) {
            return Optional.empty();
        }
        edges.remove(relation, toRemove);
        
        return Optional.of(toRemove.getDestination());
    }

    public void setType(String type) {
        this.type = type;
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

    public String getType() {
        return type;
    }

    public LinkedList<String> getInstanceHierarchy() {
        return instanceHierarchy;
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

    public boolean isInstanceOf(String type) {
        return this.type.equals(type);
    }

}
