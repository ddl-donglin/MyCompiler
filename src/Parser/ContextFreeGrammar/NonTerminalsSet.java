package Parser.ContextFreeGrammar;

import java.util.*;

/** 非终结符集：由HashMap和ArrayList组成*/
public class NonTerminalsSet {
	private Map<String, NonTerminalSymbol> varByName = new HashMap<String, NonTerminalSymbol>();
	private List<NonTerminalSymbol> varByIndex = new ArrayList<NonTerminalSymbol>();

	/** 构造函数（缺省）*/
	public NonTerminalsSet(){}
	/** 添加Symbol（一）*/
	public NonTerminalSymbol addNew(String name, String type) {
		NonTerminalSymbol var = new NonTerminalSymbol(name, count(), type);
		if (varByName.containsKey(name)) {
			throw new RuntimeException("重复非终结符 ("+name+")");
		}
		varByName.put(name, var);
		varByIndex.add(var);
		return var;
	}

	/** 添加Symbol（二）*/
	public NonTerminalSymbol addNew(String name) {
		return addNew(name, null);
	}
	
	/** 获取Symbol个数*/
	public int count() {
		return varByIndex.size();
	}

	/** 获取迭代器*/
	public Iterator<NonTerminalSymbol> iterator() {
		return Collections.unmodifiableList(varByIndex).iterator();
	}

	/** 获取Symbol（一）*/
	public NonTerminalSymbol find(String name) {
		return (NonTerminalSymbol)varByName.get(name);
	}

	/** 获取Symbol（二）*/
	public NonTerminalSymbol find(int index) {
		if (index < 0 || index >= count()) {
			return null;
		} else {
			return (NonTerminalSymbol)varByIndex.get(index);
		}
	}

	/** 清空非终结符集*/
	public void clear() {
		varByName.clear();
		varByIndex.clear();
	}
	
	/** 清除varByNam中无用非终结符s */
	void removeUseless(List<NonTerminalSymbol> s) {
		
		//删除varByNam中无用非终结符s
		Iterator<NonTerminalSymbol> it = s.iterator();
		while (it.hasNext()) {
			NonTerminalSymbol a = (NonTerminalSymbol)it.next();
			varByName.remove(a.getName());
		}
		
		//重新设置每个NonTerminalSymbol的index
		List<NonTerminalSymbol> tmp = new ArrayList<NonTerminalSymbol>();
		for (int i = 0; i<varByIndex.size(); i++) {
			NonTerminalSymbol a = (NonTerminalSymbol)varByIndex.get(i);
			if (!s.contains(a)) {
				a.setIndex(tmp.size()); 
				tmp.add(a);
			}
		}
		varByIndex = tmp;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		Iterator<NonTerminalSymbol> it = iterator();
		while (it.hasNext()) {
			sb.append(it.next());
			if (it.hasNext()) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}
	
	public Map<String, NonTerminalSymbol> getVarByName() {
		return varByName;
	}
	public void setVarByName(Map<String, NonTerminalSymbol> varByName) {
		this.varByName = varByName;
	}
	public List<NonTerminalSymbol> getVarByIndex() {
		return varByIndex;
	}
	public void setVarByIndex(List<NonTerminalSymbol> varByIndex) {
		this.varByIndex = varByIndex;
	}
}
