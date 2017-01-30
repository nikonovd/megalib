/*
 *  All rights reserved.
 */
package org.softlang.megalib.visualizer.cli;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.stream.Collectors;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author Dmitri Nikonov <dnikonov at uni-koblenz.de>
 */
public class CommandLine {

    private static final String FILE_OPTION_NAME = "f";

    private static final String TYPE_OPTION_NAME = "t";

    private org.apache.commons.cli.CommandLineParser parser = new DefaultParser();

    private HelpFormatter help = new HelpFormatter();

    private PrintWriter writer;

    private Options options;

    private org.apache.commons.cli.CommandLine cli;

    private Collection<String> allowedTypes;

    public CommandLine(PrintWriter writer, Collection<String> allowedTypes) {
        this.writer = writer;
        this.allowedTypes = allowedTypes;
        this.options = createCommandLineOptions();
    }

    public CommandLine parse(String[] arguments) {
        try {
            this.cli = this.parser.parse(options, arguments);
        } catch (ParseException ex) {
            // If this happens, something went horribly wrong anyway
            throw new RuntimeException(ex);
        }
        return this;
    }

    public boolean isValid() {
        return this.cli.hasOption("f") && this.cli.hasOption("t") && this.allowedTypes.contains(this.cli.getOptionValue(TYPE_OPTION_NAME));
    }

    public void printHelp() {
        this.help.printUsage(this.writer, this.help.getWidth(), "visualizer", this.options);
    }

    public CommandLineArguments getRequiredArguments() {
        return new CommandLineArguments(this.cli.getOptionValue(FILE_OPTION_NAME), this.cli.getOptionValue(TYPE_OPTION_NAME));
    }

    private Options createCommandLineOptions() {
        Option file = Option.builder(FILE_OPTION_NAME)
            .hasArg()
            .argName("file path")
            .longOpt("file")
            .desc("The megamodel file that is to be visualized")
            .build();
        Option type = Option.builder(TYPE_OPTION_NAME)
            .hasArg()
            .argName(this.allowedTypes.stream().collect(Collectors.joining("|")))
            .longOpt("type")
            .desc("The type of visualization.")
            .build();
        return new Options()
            .addOption(file)
            .addOption(type);
    }

}
