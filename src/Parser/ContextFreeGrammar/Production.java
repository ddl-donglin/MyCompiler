package Parser.ContextFreeGrammar;

/** 产生式*/
public class Production {
	
	public static final int LAST_TERMINAL_PRECEDENCE = TerminalSymbol.NO_PRECEDENCE-1;

	/** 产生式（Production）是构成：
	 *  index（序号）： 
	 *  	NonTerminalSymbol（左操作数） → Expression（右操作数）
	 *  */
	private NonTerminalSymbol lhs;//Left Hand Side左操作数
	private Expression rhs;//Right Hand Side右操作数
	private int index;//序号
	private int precedence;//优先权
	private SemanticAction action;//语义动作
	
	/** 构造函数（一）*/
	Production(NonTerminalSymbol lhs, Expression rhs, int index) {
		this(lhs, rhs, LAST_TERMINAL_PRECEDENCE, null, index);
	}

	/** 构造函数（二）*/
	Production(NonTerminalSymbol lhs, Expression rhs, int precedence,
			SemanticAction action, int index) {
		this.lhs = lhs;
		this.rhs = rhs;
		if (precedence == LAST_TERMINAL_PRECEDENCE) {
			ExpressionIterator it = rhs.iterator(true);
			if (it.hasPrevTerminal()) {
				this.precedence = it.prevTerminal().getPrecedence();
			} else {
				this.precedence = TerminalSymbol.NO_PRECEDENCE;
			}
		} else {
			this.precedence = precedence;
		}
		this.action = action;
		this.index = index;
	}
	
	/** 获取左操作数*/
	public NonTerminalSymbol getLHS() {
		return lhs;
	}

	/** 获取右操作数*/
	public Expression getRHS() {
		return rhs;
	}
	
	/** 获取优先权*/
	public int getPrecedence() {
		return precedence;
	}
	
	/** 获取语义动作*/
	public SemanticAction getSemanticAction() {
		return action;
	}

	/** 设置语义动作*/
	void setSemanticAction(SemanticAction action) {
		this.action = action;
	}
	
	/** 获取序号*/
	public int getIndex() {
		return index;
	}

	/** 设置序号*/
	void setIndex(int index) {
		this.index = index;
	}

	public boolean equals(Object o) {
		return (o instanceof Production &&
			lhs.equals(((Production)o).lhs) &&
			rhs.equals(((Production)o).rhs) &&
			index == ((Production)o).index);
	}

	public int hashCode() {
		return index;
	}
	
	public String toString() {
		return lhs+" → "+rhs;
	}

}
