/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer;

import java.io.OutputStream;
import org.softlang.megalib.visualizer.models.Edge;
import org.softlang.megalib.visualizer.models.Node;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public interface Visualizer {
    
    public void transformEdge(Edge edge);
    
    public void transformNode(Node node);
    
    public void writeTo(OutputStream stream);
    
}
