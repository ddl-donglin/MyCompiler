package Parser.LR1Parser;

import Parser.ContextFreeGrammar.*;
import java.io.*;

public abstract class AbstractParser implements Parser {

	protected CFG g;//上下文无关文法
	protected NonTerminalsSet v;//非终结符集
	protected TerminalsSet t;//终结符集
	protected TerminalsSet toAnalyzeTermianls;//待检查的终结符集
	protected NonTerminalSymbol s;//起始符
	protected Productions p;//产生式集
	protected Production sp;//开始产生式
	

	public AbstractParser(CFG gammar, TerminalsSet toAnalyze, boolean addStartProduction) {
		g = gammar;
		if (addStartProduction) {
			sp = g.addStartProduction();
		}
		v = g.getNonTerminals();
		t = g.getTerminals();
		s = g.getStartSymbol();
		p = g.getProductions();
		toAnalyzeTermianls = toAnalyze;
	}
	
	public CFG getGrammar() {
		return g;
	}

	abstract public void precompute();
	abstract public String parse() throws IOException, SyntaxError;
}
