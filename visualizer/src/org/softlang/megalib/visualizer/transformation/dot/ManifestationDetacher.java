/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer.transformation.dot;

import java.util.HashSet;
import java.util.Set;
import org.softlang.megalib.visualizer.models.Edge;
import org.softlang.megalib.visualizer.models.Graph;
import org.softlang.megalib.visualizer.models.Node;
import org.softlang.megalib.visualizer.transformation.GraphProcessor;

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
