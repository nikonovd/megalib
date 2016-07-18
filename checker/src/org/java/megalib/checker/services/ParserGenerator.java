/**
 * 
 */
package org.java.megalib.checker.services;

import java.io.FileInputStream;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.java.megalib.antlr.MegalibLexer;
import org.java.megalib.antlr.MegalibParser;

/**
 * @author mmay, aemmerichs
 *
 */
public class ParserGenerator implements IParserGenerator {

	@Override
	public MegalibParser generate(FileInputStream stream) throws IOException {
		ANTLRInputStream antlrStream = new ANTLRInputStream(stream);
		MegalibLexer lexer = new MegalibLexer(antlrStream);
	    CommonTokenStream token = new CommonTokenStream(lexer);	 

	    return new MegalibParser(token);
	}
}