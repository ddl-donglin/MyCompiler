package Parser.ContextFreeGrammar;

public interface ExpressionIterator
		extends Cloneable {
	public Object clone();

	boolean hasNext();
	boolean hasNextTerminal();
	boolean hasNextNonTerminal();

	Symbol getNext();
	Symbol next();
	TerminalSymbol nextTerminal();
	NonTerminalSymbol nextNonTerminal();
	int nextIndex();

	boolean hasPrev();
	boolean hasPrevTerminal();
	boolean hasPrevNonTerminal();

	Symbol getPrev();
	Symbol prev();
	TerminalSymbol prevTerminal();
	NonTerminalSymbol prevNonTerminal();
	int prevIndex();

	void remove();
	void set(Symbol sym);
	void addBefore(Symbol sym);
	void addAfter(Symbol sym);
	void addWordBefore(Expression w);
	void addWordAfter(Expression w);

	Expression suffix();
	Expression prefix();
}
