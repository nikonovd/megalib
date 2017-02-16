/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer.transformation;

import org.softlang.megalib.visualizer.models.Edge;
import org.softlang.megalib.visualizer.models.Node;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class ManifestationDetacher implements GraphProcessor {

    @Override
    public Node processNode(Node node) {
        Edge manifestsAs = node.getEdge("manifestsAs");
        if (manifestsAs == null) {
            return node;
        }
        
        String artifactType = manifestsAs.getDestination().getName();
        node.disconnect("manifestsAs");
        node.setType(artifactType);
        
        return node;
    }

}
