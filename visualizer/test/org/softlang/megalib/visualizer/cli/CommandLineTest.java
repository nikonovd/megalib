/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer.cli;

import java.util.Arrays;
import java.util.Collections;
import org.softlang.megalib.visualizer.exceptions.CommandLineException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class CommandLineTest {

    @Test
    public void testValidData() {
        CommandLine cli = new CommandLine(Arrays.asList("graphviz", "testtype"));

        String data[] = {
            "-f",
            "abc123",
            "-t",
            "graphviz"
        };
        cli.parse(data);
        Assert.assertEquals(
            new CommandLineArguments("abc123", "graphviz"), cli.getRequiredArguments());

        String another[] = {
            "-f",
            "abc123",
            "-t",
            "testtype"
        };
        cli.parse(another);
        Assert.assertEquals(
            new CommandLineArguments("abc123", "testtype"), cli.getRequiredArguments());
    }

    @Test(expected = CommandLineException.class)
    public void testWithNoSupportedTypes() {
        CommandLine cli = new CommandLine(Collections.emptyList());

        String data[] = {
            "-f",
            "abc123",
            "-t",
            "test"
        };
        
        cli.parse(data);
    }
    
    @Test(expected = CommandLineException.class)
    public void testWithInvalidType() {
        CommandLine cli = new CommandLine(Arrays.asList("abc", "123"));

        String data[] = {
            "-f",
            "abc123",
            "-t",
            "test"
        };
        
        cli.parse(data);
    }
    
    @Test(expected = CommandLineException.class)
    public void testWithMissingFileArgument() {
        CommandLine cli = new CommandLine(Arrays.asList("abc", "123"));

        String data[] = {
            "-t",
            "test"
        };
        
        cli.parse(data);
    }
    
    @Test(expected = CommandLineException.class)
    public void testWithMissingTypeArgument() {
        CommandLine cli = new CommandLine(Arrays.asList("abc", "123"));

        String data[] = {
            "-f",
            "abc123",
        };
        
        cli.parse(data);
    }

}
