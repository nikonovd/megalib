/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer.models;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.java.megalib.models.Function;
import org.java.megalib.models.MegaModel;
import org.java.megalib.models.Relation;
import org.softlang.megalib.visualizer.MegaModelVisualizerException;
import org.softlang.megalib.visualizer.ModelReader;
import org.softlang.megalib.visualizer.VisualizerOptions;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class GraphFactory {

    private VisualizerOptions options;

    private ModelReader reader;

    public GraphFactory(VisualizerOptions options) {
        this.options = options;
        this.reader = new ModelReader(options);
    }

    public Graph create() throws MegaModelVisualizerException {
        MegaModel model = reader.read();

        Graph graph = new Graph(options.getFilePath().getFileName().toString().replaceAll("\\.megal", ""));
        createNodes(model, graph);
        createEdges(model, graph);

        return graph;
    }

    private void createNodes(MegaModel model, Graph graph) {
        createNodesByInstances(model, graph);
        createNodesByFunctions(model, graph);
    }

    private void createNodesByInstances(MegaModel model, Graph graph) {
        List<Node> nodes = model.getInstanceOfMap().entrySet().stream()
            .map(entry -> createNode(entry.getKey(), entry.getValue(), model))
            .collect(Collectors.toList());
        nodes.forEach(graph::add);
    }

    private void createNodesByFunctions(MegaModel model, Graph graph) {
        model.getFunctionDeclarations().forEach((name, actions) -> graph.add(createNode(name, "FunctionDeclaration", model)));
        model.getFunctionApplications().forEach((name, actions) -> graph.add(createNode(name, "FunctionApplication", model)));
    }

    private Node createNode(String name, String type, MegaModel model) {
        return new Node(type, name, getFirstInstanceLink(model, name));
    }

    private String getFirstInstanceLink(MegaModel model, String name) {
        Set<String> links = model.getLinkMap().get(name);
        if (links == null) {
            return "";
        }
        Iterator<String> it = links.iterator();
        return it.hasNext() ? it.next() : "";
    }

    private void createEdges(MegaModel model, Graph graph) {
        model.getRelationshipInstanceMap().forEach((name, relations) -> createEdgesByRelations(graph, name, relations));
    }

    private void createEdgesByRelations(Graph graph, String relationName, Set<Relation> relations) {
        relations.stream()
            .forEach(relation -> createEdge(graph, relation.getSubject(), relation.getObject(), relationName));
    }

    private void createEdge(Graph graph, String from, String to, String relation) {
        Node fromNode = graph.get(from);
        Node toNode = graph.get(to);

        fromNode.connect(relation, toNode);
    }

}
