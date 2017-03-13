/*
 *  All rights reserved.
 */
package main.org.softlang.megalib.visualizer.transformation;

import java.util.HashSet;
import java.util.Set;
import main.org.softlang.megalib.visualizer.models.Edge;
import main.org.softlang.megalib.visualizer.models.Graph;
import main.org.softlang.megalib.visualizer.models.Node;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class ManifestationDetacher implements GraphProcessor {
    
    private Set<Node> removeable = new HashSet<>();

    public boolean processNode(Node node) {
        Edge manifestsAs = node.getEdge("manifestsAs");
        if (manifestsAs == null) {
            return false;
        }

        String artifactType = manifestsAs.getDestination().getName();
        node.disconnect("manifestsAs").ifPresent(removeable::add);
        node.setType(artifactType);

        return true;
    }

    @Override
    public void processGraph(Graph g) {
        g.forEachNode(this::processNode);
        removeable.forEach(g::remove);
    }

}
