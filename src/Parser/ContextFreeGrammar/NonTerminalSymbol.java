package Parser.ContextFreeGrammar;

/** 非终结符*/
public class NonTerminalSymbol extends Symbol {	
	
	/** 构造函数（缺省）*/
	public NonTerminalSymbol(){
		super();
	}
	/** 构造函数（一）*/
	NonTerminalSymbol(String name, int index, String type) {
		super(name, index, type);
	}

	/** 构造函数（二）*/
	NonTerminalSymbol(String name, int index) {
		this(name, index, null);
	}

	public boolean isTerminal() {
		return false;
	}
}
