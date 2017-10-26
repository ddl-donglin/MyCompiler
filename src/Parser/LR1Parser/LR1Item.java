// find better name than getSufixAndLookahead

package Parser.LR1Parser;

import Parser.ContextFreeGrammar.*;

public class LR1Item {
	
	private LR0Item lr0item;//lr0核项目
	private TerminalSymbol lookahead;//前看标识符

	public LR1Item(LR0Item lr0item, TerminalSymbol lookahead) {
		this.lr0item = lr0item;
		this.lookahead = lookahead;
	}
	
	public boolean equals(Object o) {
		return (o instanceof LR1Item &&
			lr0item.equals(((LR1Item)o).lr0item) &&
			lookahead  == ((LR1Item)o).lookahead);
	}

	public int hashCode() {
		return 17+lr0item.hashCode()*37+lookahead.hashCode();
	}

	public LR1Item nextItem() {
		return new LR1Item(lr0item.nextItem(), lookahead);
	}
	
	public boolean isComplete() {
		return lr0item.isComplete();
	}

	public boolean isNew() {
		return lr0item.isNew();
	}

	public Symbol getNextSymbol() {
		return lr0item.getNextSymbol();
	}
	
	public Expression getSufixAndLookahead() {
		if (!getNextSymbol().isTerminal()) {
			Expression w = new Expression(lr0item.suffix());
			w.removeFirst();
			w.addLast(lookahead);
			return w;
		} else {
			throw new UnsupportedOperationException();
		}
	}

	public LR0Item getLR0Item() {
		return lr0item;
	}
	
	public Production getProduction() {
		return lr0item.getProduction();
	}

	public int getPosition() {
		return lr0item.getPosition();
	}

	public TerminalSymbol getLookahead() {
		return lookahead;
	}

	public String toString() {
		return "("+lr0item+", "+lookahead+")";
	}
}
