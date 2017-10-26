package Parser.ContextFreeGrammar;

/** 终结符*/
public class TerminalSymbol extends Symbol {
	
	public static final int LEFT_ASSOCIATIVE = 0;//左结合
	public static final int RIGHT_ASSOCIATIVE = 1;//右结合
	public static final int NON_ASSOCIATIVE = 2;
	
	public static final int NO_PRECEDENCE = -1;

	private int precedence;//优先权
	private int associativity;//结合性

	TerminalSymbol(String name, int index, String type, 
			int precedence, int associativity) {
		super(name, index, type);
		this.precedence = precedence;
		this.associativity = associativity;
	}

	TerminalSymbol(String name, int index, String type) {
		this(name, index, type, NO_PRECEDENCE, NON_ASSOCIATIVE);
	}

	TerminalSymbol(String name, int index) {
		this(name, index, null);
	}
	
	public int getPrecedence() {
		return precedence;
	}
	
	public int getAssociativity() {
		return associativity;
	}

	public void setPrecedence(int precedence, int associativity) {
		this.precedence = precedence;
		this.associativity = associativity;
	}

	public boolean isTerminal() {
		return true;
	}
}
