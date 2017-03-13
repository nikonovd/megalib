/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer.cli;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class CommandLineArguments {

    private String filePath;

    private String type;

    public CommandLineArguments(String filePath, String type) {
        this.filePath = filePath;
        this.type = type;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getType() {
        return type;
    }

}
