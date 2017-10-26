package Parser.LR1Parser;

/** ¾ä·¨´íÎó*/
import Parser.ContextFreeGrammar.TerminalSymbol;

public class SyntaxError extends Exception {
	TerminalSymbol a;
	
	public SyntaxError() {
		super();
	}

	public SyntaxError(TerminalSymbol a) {
		super();
		this.a = a;
	}
	
	public SyntaxError(String message) {
		super(message);
	}

	public SyntaxError(String message, TerminalSymbol a) {
		super(message);
		this.a = a;		
	}
	
	public String toString() {
		StringBuffer sb =  new StringBuffer();
		sb.append("Syntax error");
		if (a != null) {
			sb.append(" near tooken "+a);
		}
		String message = getMessage();
		if (message != null) {
			sb.append(": "+message);			
		}
		return sb.toString();
	}
}
