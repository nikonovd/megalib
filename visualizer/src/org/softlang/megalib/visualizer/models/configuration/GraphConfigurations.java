/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer.models.configuration;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class GraphConfigurations {

    private GraphConfigurations() {
    }

    public static GraphConfiguration defaultDotConfiguration() {
        return new GraphConfigurationBuilder()
            .createNodeConfiguration()
                .forItem("File")
                .color("#000000")
                .shape("note")
                .buildNode()
            .buildConfig();
    }

}
