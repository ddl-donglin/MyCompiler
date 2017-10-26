package Parser.LR1Parser;

import Parser.ContextFreeGrammar.CFG;

import java.io.*;

public interface Parser {
	CFG getGrammar();
	void precompute();
	String parse() throws IOException, SyntaxError;
}
