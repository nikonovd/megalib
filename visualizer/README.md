# megalib visualizer
A visualization library for MegaL models.

## Motivation
[MegaL](https://github.com/softlang/megalib/blob/master/docs/LanguageDescription.pdf) is a text-based modeling language to describe relations between software artifacts and their corresponding concepts. As textual models are rather hard to read, a visualization library is to be considered to create a graphical representation of the model. Megalib visualizer is a component implemented as a part of the megalib providing this functionality.

## Installation
Before installing the visualizer library, please ensure that your target machine provides a Java Runtime Environment with minimal version 8. Check your installed Java version and update if necessary. You can download the latest JRE 8 [here](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html).

This repository provides a runnable binary in form of a jar file. To download the latest release of the visualizer, check the [releases](https://github.com/nikonovd/megalib/releases). It is also possible to compile the project source code yourself. For further instructions, refer to [Custom build](#custom-build)

Anyway, the jar file, regardless of the origin, must obtain the following file hierarchy:
```
---megalib/
   ---models/
      ---Prelude.megal
   ---visualizer
      ---visualizer.jar
```
In concrete means, the executed visualizer jar must be in a folder, which shares its parent folder with a models directory that contains at least the Prelude.megal and your target model you want to visualize. At current stage, this is a strict directory structure to be obtained. Otherwhise the execution will result in erroneous behavior.

## Usage
For correct results please ensure that the directory structure shown above is obtained. The executable binary provides a cli-like command. The execution must be done within the folder containing the visualizer binary. You have to run the binary jar file using java and the following parameters:
```bash
-f(ile) # the file location of the mega model to be visualized (relative or absolute)
-t(ype) # the concrete visualizer type, currently supported: graphviz, yed
```
Every parameter flag takes another argument which will be the value for its key. A concrete example of visualizing a model would be:
```bash
java -jar visualizer.jar -f ../models/MySQL.megal -t graphviz
```
This command takes the MySQL.megal model intoc accounting and visualized it using graphviz. The concrete example will generate a MySQL.dot file in the current directory. Executing the jar file without any arguments will print a supporting dialog that shows the concrete usage manual for the visualizer.

**Note:** Although yed is a valid type parameter, the yed visualizer is not implemented yet. Executing the visualizer with this type will result in an error.

## Custom build
