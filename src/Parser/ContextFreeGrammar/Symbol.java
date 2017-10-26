package Parser.ContextFreeGrammar;

/** 终结符（terminal symbol）和非终结符（non-terminals symbol）的父类*/
public abstract class Symbol {
	
	protected String name;
	protected int index;//用于删除无用非终结符
	protected String type;

	/** 构造函数（缺省）*/
	public Symbol(){
	}
	
	/** 构造函数（一）*/
	public Symbol(String name, int index, String type) {
		this.name = name;
		this.index = index;
		this.type = type;
	}

	/** 构造函数（二）*/
	public Symbol(String name, int index) {
		this(name, index, null);
	}
	
	public String getName() {
		return name;
	}
	
	public int getIndex() {
		return index;
	}

	public String getType() {
		return type;
	}

	/** 用于删除无用非终结符*/
	void setIndex(int index) {
		this.index = index;
	}

	public boolean equals(Object o) {
		return (o instanceof Symbol 
				&& name.equals(((Symbol)o).name)
				&& index == ((Symbol)o).index);
	}

	public int hashCode() {
		return isTerminal()? -index: +index;
	}

	public String toString() {
		return getName();
	}

	public abstract boolean isTerminal();
}
