package Parser.ContextFreeGrammar;

import java.util.*;

public class Productions {
	
	/** 序号标识的产生式集*/
	private List<Production> prodByIndex = new ArrayList<Production>();	
	/** 左操作数（非终结符）标识的产生式集*/
	private Map<NonTerminalSymbol, LinkedList<Production>> prodByLHS = 
		new LinkedHashMap<NonTerminalSymbol, LinkedList<Production>>();	
	/** 右操作数（表达式）标识的产生式集*/
	private Map<Expression, LinkedList<Production>> prodByRHS = 
		new HashMap<Expression, LinkedList<Production>>();

	
	/** 添加产生式（一）*/
	public Production addNew(NonTerminalSymbol lhs, Expression rhs,
			int precedence, SemanticAction action) {
		Production prod = new Production(lhs, rhs, precedence, action, count());
		prod = addToMaps(prod);
		prodByIndex.add(prod);
		return prod;
	}
	
	/** 添加产生式（二），仅仅将表达式添加到了Maps，不添加到List*/
	private Production addToMaps(Production prod) {
		NonTerminalSymbol lhs = prod.getLHS();
		Expression rhs = prod.getRHS();
		
		//获取左操作数（LHS）标识的产生式集
		LinkedList<Production>  right;
		if (prodByLHS.containsKey(lhs)) {
			right = prodByLHS.get(lhs);
		} else {
			right = new LinkedList<Production> ();
		}		
		//查找重复
		Iterator<Production>  it = right.iterator();
		while (it.hasNext()) {
			Production oldProd = it.next();
			if (lhs.equals(oldProd.getLHS()) && rhs.equals(oldProd.getRHS())) {
				return oldProd;//重复，返回重复表达式
			}
		}
		//没有重复，添加该产生式，并继续下面操作
		right.add(prod);
		prodByLHS.put(lhs, right);
		
		//获取右操作数（RHS）标识的产生式集
		LinkedList<Production>  left;
		if (prodByRHS.containsKey(rhs)) {
			left = prodByRHS.get(rhs);
		} else {
			left = new LinkedList<Production> ();
		}
		//不需要在查找重复了，已经在上面一种情况下检查过了
		left.add(prod);
		prodByRHS.put(rhs, left);
		return prod;
	}

	/** 添加产生式（三）*/
	public Production addNew(NonTerminalSymbol lhs, Expression rhs, int precedence) {
		return addNew(lhs, rhs, precedence, null);
	}

	/** 添加产生式（四）*/
	public Production addNew(NonTerminalSymbol lhs, Expression rhs, SemanticAction action) {
		return addNew(lhs, rhs, Production.LAST_TERMINAL_PRECEDENCE, action);		
	}

	/** 添加产生式（五）*/
	public Production addNew(NonTerminalSymbol lhs, Expression rhs) {
		return addNew(lhs, rhs, Production.LAST_TERMINAL_PRECEDENCE, null);
	}

	/** 添加产生式（六.1）*/
	public void addNew(NonTerminalSymbol lhs, List<Expression> rhsList,
			int precedence, SemanticAction action) {
		Iterator<Expression> it = rhsList.iterator();
		while (it.hasNext()) {
			Expression rhs = (Expression)it.next();
			addNew(lhs, rhs, precedence, action);
		}
	}

	/** 添加产生式（六.2）*/
	public void addNew(NonTerminalSymbol lhs, List<Expression> rhsList, int precedence) {
		addNew(lhs, rhsList, precedence, null);
	}

	/** 添加产生式（六.3）*/
	public void addNew(NonTerminalSymbol lhs, List<Expression> rhsList, SemanticAction action) {
		addNew(lhs, rhsList, Production.LAST_TERMINAL_PRECEDENCE, action);
	}

	/** 添加产生式（六.4）*/
	public void addNew(NonTerminalSymbol lhs, List<Expression> rhsList) {
		addNew(lhs, rhsList, Production.LAST_TERMINAL_PRECEDENCE, null);
	}
	
	/** 返回产生式数目*/
	public int count() {
		return prodByIndex.size();
	}

	/** 获取迭代器*/
	public Iterator<Production> iterator() {
		return Collections.unmodifiableList(prodByIndex).iterator();//返回指定列表的不可修改视图
	}

	public Iterator<Production> iterator(NonTerminalSymbol a) {
		List<Production> prodList = find(a);
		if (prodList == null) {
			// iterator over the empty list
			return new Iterator<Production>() {
				public boolean hasNext() { return false; }
				public Production next() { throw new NoSuchElementException(); }
				public void remove() { throw new UnsupportedOperationException(); }
			};
		} else {
			return Collections.unmodifiableList(prodList).iterator();
		}
	}

	public Production find(int index) {
		if (index < 0 || index >= count()) {
			return null;
		} else {
			return (Production)prodByIndex.get(index);
		}
	}

	/** 查询表达式（一）*/
	public LinkedList<Production> find(NonTerminalSymbol a) {
		return prodByLHS.get(a);
	}

	/** 查询表达式（一）*/
	public LinkedList<Production> find(Expression w) {
		return prodByRHS.get(w);
	}

	/** 只清空Maps，不清空List*/
	private void clearMaps() {
		prodByLHS.clear();
		prodByRHS.clear();
	}

	/** 清空产生式集*/
	public void clear() {
		prodByIndex.clear();
		clearMaps();
	}

	/** 清空Maps，和List*/
	void removeUseless(List<NonTerminalSymbol> uselessVar) {
		
		clearMaps();
		
		List<Production> tmp = new ArrayList<Production>();//用于保存有用的产生式
		for (int i = 0; i<prodByIndex.size(); i++) {
			Production prod = find(i);
			boolean useful = true;
			
			//检查整个产生式是否包含非终结符
			if (uselessVar.contains(prod.getLHS())) {//检查左操作数（一非终结符）
				useful = false;				
			} 
			else {//检查右操作数
				Expression w = prod.getRHS();//获取无用产生式的右操作数（一个表达式）
				ExpressionIterator itW = w.iterator();
				while (itW.hasNextNonTerminal()) {
					NonTerminalSymbol a = itW.nextNonTerminal();
					if (uselessVar.contains(a)) {
						useful = false;
						break;
					}
				}
			}			
			if (useful) {//添加有用的产生式到TMP
				prod.setIndex(tmp.size()); 
				tmp.add(prod);
				addToMaps(prod);
			}
		}
		prodByIndex = tmp;//指向有用表达式
	}

	/** 判断是否可逆*/
	public boolean isInvertible() {
		Iterator<Expression> it = prodByRHS.keySet().iterator();
		while (it.hasNext()) {
			List<Production> rhsList = find((Expression)it.next());
			switch (rhsList.size()) {
				case 0: throw new RuntimeException("Null value in prodByRHS");
				case 1: break;
				default: return false;
			}
		}
		return true;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Set<NonTerminalSymbol> keySet = prodByLHS.keySet();
		Iterator<NonTerminalSymbol> it = keySet.iterator();
		while (it.hasNext()) {
			NonTerminalSymbol var = (NonTerminalSymbol)it.next();
			sb.append(var+" → ");
			List<Production> rhsList = prodByLHS.get(var);
			Iterator<Production> itL = rhsList.iterator();
			while (itL.hasNext()) {
				sb.append(((Production)itL.next()).getRHS());
				if (itL.hasNext()) {
					sb.append(" | ");
				}
			}
			sb.append(";\n");
		}
		return sb.toString();
	}
}
