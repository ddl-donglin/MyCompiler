package Parser.LR1Parser;
/** ´íÎó±ê¼Ç*/
import Parser.ContextFreeGrammar.*;

public class Token {
	public static final int UNKNOWN_LINE = -1;
	public static final int UNKNOWN_COLUMN = -1;
	public static final int UNKNOWN_NCHAR = -1;
	public static final int UNKNOWN_LENGTH = -1;

	private TerminalSymbol a;
	private int line, column, nchar, length;	
	private Object value;
	
	public Token(TerminalSymbol a, int line, int column, int nchar, int length, Object value) {
		this.a = a;
		this.line = line;
		this.column = column;
		this.nchar = nchar;
		this.length = length;
		this.value = value;
	}

	public Token(TerminalSymbol a, int line, int column, int nchar, int length) {
		this(a, line, column, nchar, length, null);
	}

	public Token(TerminalSymbol a, int line, int column) {
		this(a, line, column, UNKNOWN_NCHAR, UNKNOWN_LENGTH);
	}
	
	public Token(TerminalSymbol a) {
		this(a, UNKNOWN_LINE, UNKNOWN_COLUMN, UNKNOWN_NCHAR, UNKNOWN_LENGTH);
	}

	public TerminalSymbol getSymbol() {
		return a;
	}
	
	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}

	public int getCharNumber() {
		return nchar;
	}
	
	public int getLength() {
		return length;
	}

	public Object getValue() {
		return value;
	}
	
	public void setSymbol(TerminalSymbol a) {
	    this.a = a;
	}

	public void setLine(int line) {
	    this.line = line;
	}
	
	public void setColumn(int column) {
	    this.column = column;
	}

	public void setCharNumber(int nchar) {
	    this.nchar = nchar;
	}

	public void setLength(int length) {
	    this.length = length;
	}

	public void setValue(Object value) {
	    this.value = value;
	}

	public String toString() {
		StringBuffer sb = new  StringBuffer(a.toString());
		if (line != UNKNOWN_LINE) {
			sb.append("[line="+line+"]");
		}
		if (column != UNKNOWN_COLUMN) {
			sb.append("[column="+column+"]");
		}
		if (nchar != UNKNOWN_NCHAR) {
			sb.append("[nchar="+nchar+"]");
		}
		if (length != UNKNOWN_LENGTH) {
			sb.append("[length="+length+"]");
		}
		if (value != null) {
			sb.append("[value="+value+"]");
		}
		return sb.toString();
	}
}
