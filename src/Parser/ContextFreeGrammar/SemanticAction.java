package Parser.ContextFreeGrammar;

import java.util.*;

/** ÓïÒå¶¯×÷*/
public class SemanticAction {
	
	private String returnType;
	private List labels;
	private String code;
	private int offset;

	public SemanticAction(String returnType, List labels, int offset, String code) {
		this.returnType = returnType;
		this.labels = new LinkedList(labels);
		this.offset = offset;
		this.code = code;
	}
	
	public String getReturnType() {
		return returnType;
	}
	
	public List getLabels() {
		return labels;
	}
	
	public int getOffset() {
		return offset;
	}
	
	public String getCode() {
		return code;
	}
	
	public String toString() {
		return "{: "+code+" :}";
	}
}
