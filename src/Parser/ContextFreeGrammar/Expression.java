package Parser.ContextFreeGrammar;

import java.util.*;

/** 由Symbol构成的双向链*/
public class Expression {
	private Node first;
	private Node last;
	private int size;
	
	/** 定义由Symbol构成的双向链节点类Node*/
	private class Node {
		Symbol sym;
		Node prev;//前向指针
		Node next;//后向指针

		public Node() {
			this(null, null, null);
		}
		
		public Node(Symbol sym, Node prev, Node next) {
			this.sym = sym;
			this.prev = prev;
			this.next = next;
		}

	}
	
	private class WordIteratorP implements ExpressionIterator {
		/** 按数轴定义方向*/
		private final int LEFT = -1;
		private final int NONE = 0;
		private final int RIGHT = +1;
		private Node p;//
		private Node n;//下一个指针next
		private int direction;
		private int index;
		
		public WordIteratorP(boolean end) {
			direction = NONE;
			if (!end) {
				p = null;
				n = first;
				index = 0;
			} else {
				p = last;
				n = null;
				index = Expression.this.size();
			}
		}

		public WordIteratorP() {
			this(false);
		}

		public Object clone() {
			try {
				return super.clone();
			} catch (CloneNotSupportedException e) {
				throw new RuntimeException(e);
			}
		}

		/** 判断下一个（一）：true表示下一个Symbol存在*/
		public boolean hasNext() {
			return n != null;
		}

		/** 判断下一个（二）：true表示下一个Terminal存在*/
		public boolean hasNextTerminal() {
			Node q = n;
			while (q != null) {
				if (q.sym.isTerminal()) {
					return true;
				}
				q = q.next;
			}
			return false;
		}

		/** 判断下一个（三）：true表示下一个NonTerminal存在*/
		public boolean hasNextNonTerminal() {
			Node q = n;
			while (q != null) {
				if (!q.sym.isTerminal()) {
					return true;
				}
				q = q.next;
			}
			return false;
		}

		/** 获取下一个（一）：return下一个Symbol*/
		public Symbol getNext() {
			return n.sym;
		}
		
		/** 获取下一个（二）：return下一个Terminal*/
		public TerminalSymbol nextTerminal() {
			direction = RIGHT;
			while (true) {
				index++;
				p = n;
				n = n.next;
				if (p.sym.isTerminal()) {
					return (TerminalSymbol)p.sym;
				}
			}
		}

		/** 获取下一个（三）：return下一个NonTerminal*/
		public NonTerminalSymbol nextNonTerminal() {
			direction = RIGHT;
			while (true) {
				index++;
				p = n;
				n = n.next;
				if (!p.sym.isTerminal()) {
					return (NonTerminalSymbol)p.sym;
				}
			}
		}

		/** 指针p、n整体后移一位*/
		public Symbol next() {
			direction = RIGHT;
			index++;
			p = n;
			n = n.next;
			return p.sym;
		}

		/** 获取下一个index*/
 		public int nextIndex() {
 			return index;
 		}

 		/** 判断上一个（一）：true表示上一个Symbol存在*/
		public boolean hasPrev() {
			return p != null;
		}

		/** 判断上一个（二）：true表示上一个Terminal存在*/
		public boolean hasPrevTerminal() {
			Node q = p;
			while (q != null) {
				if (q.sym.isTerminal()) {
					return true;
				}
				q = q.prev;
			}
			return false;
		}

		/** 判断上一个（三）：true表示上一个NonTerminal存在*/
		public boolean hasPrevNonTerminal() {
			Node q = p;
			while (q != null) {
				if (!q.sym.isTerminal()) {
					return true;
				}
				q = q.prev;
			}
			return false;
		}

		/** 获取上一个（一）：return上一个Symbol*/
		public Symbol getPrev() {
			return p.sym;
		}

		/** 获取上一个（二）：return上一个Terminal*/
		public TerminalSymbol prevTerminal() {
			direction = LEFT;
			while (true) {
				index--;
				n = p;
				p = p.prev;
				if (n.sym.isTerminal()) {
					return (TerminalSymbol)n.sym;
				}
			}
		}

		/** 获取上一个（三）：return上一个NonTerminal*/
		public NonTerminalSymbol prevNonTerminal() {
			direction = LEFT;
			while (true) {
				index--;
				n = p;
				p = p.prev;
				if (!n.sym.isTerminal()) {
					return (NonTerminalSymbol)n.sym;
				}
			}
		}

		/** 指针p、n整体前移一位*/
		public Symbol prev() {
			direction = LEFT;
			index--;
			n = p;
			p = p.prev;
			return n.sym;
		}

		/** 获取上一个index*/
 		public int prevIndex() {
 			return index-1;
 		}

		/** 获取当前结点后面的所有节点*/
		public Expression suffix() {
			Expression w = new Expression();
			Node q = n;
			while (q != null) {
				w.addLast(q.sym);
				q = q.next;
			}
			if(w.size()==0) return new Expression(
					new Node(new TerminalSymbol(" ", 0), null, null),
					new Node(new TerminalSymbol(" ", 0), null, null), 
					1);//返回一空格
			else return w;
		}

		/** 获取当前节点前面的所有节点*/
		public Expression prefix() {
			Expression w = new Expression();
			Node q = p;
			while (q != null) {
				w.addFirst(q.sym);
				q = q.prev;
			}
			if(w.size()==0) return new Expression(
					new Node(new TerminalSymbol(" ", 0), null, null),
					new Node(new TerminalSymbol(" ", 0), null, null), 
					1);//返回一空格
			else return w;
		}
		
 		/** 移除一个结点*/
		public void remove() {
			if (direction == LEFT) {
				Expression.this.remove(n);
			} else if (direction == RIGHT) {
				Expression.this.remove(p);
				index--;
			} else {
				throw new IllegalStateException();
			}
		}

		/** 修改一个结点*/
		public void set(Symbol sym) {
			if (direction == LEFT) {
				n.sym = sym;
			} else if (direction == RIGHT) {
				p.sym = sym;
			} else {
				throw new IllegalStateException();
			}
		}
		
		/** 尾部添加一个结点*/
		public void addBefore(Symbol sym) {
			if (n == null) {
				addLast(sym);
				p = last;
			} else {
				addBeforeNode(n, sym);
				p = n.prev;
			}
			index++;
		}

		/** 首部添加一个结点*/
		public void addAfter(Symbol sym) {
			if (p == null) {
				addFirst(sym);
				n = first;
			} else {
				addAfterNode(p, sym);
				n = p.next;
			}
		}

		/** 尾部添加一个表达式*/
		public void addWordBefore(Expression w) {
			if (!w.isEmpty()) {
				if (n == null) {
					addLast(w);
					p = last;
				} else {
					addBeforeNode(n, w);
					p = n.prev;
				}
				index += w.size();
			}
		}

		/** 首部添加一个表达式*/
		public void addWordAfter(Expression w) {
			if (!w.isEmpty()) {
				if (p == null) {
					addFirst(w);
					n = first;
				} else {
					addAfterNode(p, w);
					n = p.next;
				}
			}
		}

	}

	/** 构造函数(private) */
	private Expression(Node first, Node last, int size) {
		this.first = first;
		this.last = last;
		this.size = size;
	}

	/** 构建空表达式(λ/ε labmda/epsilon) */
	public Expression() {
		this(null, null, 0);
	}

	/** 构建某表达式*/
	public Expression(Expression w) {
		this();
		addLast(w);
	}

	/** 由symbol生成表达式（一）*/
	public Expression(Symbol sym) {
		this();
		addLast(sym);
//		this.first = this.last = new Node(sym, null, null);
//		this.size = 1;
	}

	/** 由symbol生成表达式（二）*/
	public Expression(Symbol sym1, Symbol sym2) {
		this(sym1);
		addLast(sym2);
	}

	/** 由symbol生成表达式（三）
	 *  新表达式的顺序就是symbol集的顺序*/
	public Expression(Collection<Symbol> c) {
		this(null, null, 0);
		Iterator<Symbol> it = c.iterator();
		while (it.hasNext()) {
			addLast((Symbol)it.next());
		}
	}

	/** 获取p、q之间子表达式（包括p、q两节点）*/
	private Expression subWord(Node p, Node q) {
		int s = 1;
		Node it = p;
		while (it != q) {
			it = it.next;
			s++;
		}
		return new Expression(p, q, s);
	}

	/** 获取表达式中symbol数目*/
	public int size() {
		return size;
	}

	/** true表示空 */
	public boolean isEmpty() {
		return first == null;
	}

	public boolean equals(Object o) {
		if (!(o instanceof Expression)) {
			return false;
		}
		Expression w = (Expression)o;
		if (w.size() != size()) {
			return false;
		}
		ExpressionIterator it1 = iterator();
		ExpressionIterator it2 = w.iterator();
		while (it1.hasNext()) {
			if (!it1.next().equals(it2.next())) {
				return false;
			}
		}
		return true;
	}

	public int hashCode() {
		int code = size();
		ExpressionIterator it = iterator();
		while (it.hasNext()) {
			code = 37 * code + it.next().hashCode();
		}
		return code;
	}


	/** true表示word含有某symbol*/
	public boolean contains(Symbol sym) {
		Node p = first;
		while (p != null) {
			if (p.sym.equals(sym)) {
				return true;
			}
			p = p.next;
		}
		return false;
	}

	//实现双向迭代
	/** 获取迭代器，从第一个symbol开始向后迭代*/
	public ExpressionIterator iterator() {
		return new WordIteratorP();
	}

	/** 获取迭代器，end为false，即从iterator()第一个symbol开始向后迭代；
	 *  end为true，从最后一个symbol开始向前迭代*/
	public ExpressionIterator iterator(boolean end) {
		return new WordIteratorP(end);
	}

	/** 将双向链转化成数组*/
	public Symbol[] toArray() {
		Symbol[] arr = new Symbol[size];
		Node p = first;
		int i = 0;
		while (p != null) {
			arr[i++] = p.sym;
			p = p.next;
		}
		return arr;
	}

	/** 在链头添加symbol */
	public void addFirst(Symbol sym) {
		Node p = new Node(sym, null, first);
		if (first == null) {
			first = last = p;
		} else {
			first.prev = p;
			first = p;
		}
		size++;
	}

	/** 将symbol附加到链未 */
	public void addLast(Symbol sym) {
		Node p = new Node(sym, last, null);
		if (last == null) {
			first = last = p;
		} else {
			last.next = p;
			last = p;
		}
		size++;
	}

	/** 在某Node前添加symbol*/
	private void addBeforeNode(Node n, Symbol sym) {
		if (n == first) {
			addFirst(sym);
		} else {
			Node p = n.prev;
			Node q = new Node(sym, p, n);
			p.next = q;
			n.prev = q;
			size++;
		}
	}

	/** 在某Node后添加symbol*/
	private void addAfterNode(Node p, Symbol sym) {
		if (p == last) {
			addLast(sym);
		} else {
			Node n = p.next;
			Node q = new Node(sym, p, n);
			p.next = q;
			n.prev = q;
			size++;
		}
	}

	/** 重载，在链头添加word*/
	public void addFirst(Expression w) {
		if (!w.isEmpty()) {
			Expression temp = new Expression(w);
			if (first != null) {
				first.prev = temp.last;
			}
			if (temp.last != null) {
				temp.last.next = first;
				first = temp.first;
			}
			size += temp.size;
		}
	}

	/** 重载，在链未添加word */
	public void addLast(Expression w) {
		if (!w.isEmpty()) {
			ExpressionIterator it = w.iterator();
			while (it.hasNext()) {
				addLast(it.next());
			}
		}
	}

	/** 重载，在某Node前添加word*/
	private void addBeforeNode(Node n, Expression w) {
		if (!w.isEmpty()) {
			if (n == first) {
				addFirst(w);
			} else {
				Node p = n.prev;
				Expression temp = new Expression(w);
				temp.first.prev = p;
				temp.last.next = n;
				p.next = temp.first;
				n.prev = temp.last;
				size += temp.size;
			}
		}
	}

	/** 重载，在某Node后添加word */
	private void addAfterNode(Node p, Expression w) {
		if (!w.isEmpty()) {
			if (p == last) {
				addLast(w);
			} else {
				Node n = p.next;
				Expression temp = new Expression(w);
				temp.first.prev = p;
				temp.last.next = n;
				p.next = temp.first;
				n.prev = temp.last;
				size += temp.size;
			}
		}
	}

	/** 获取头结点标识符*/
	public Symbol getFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		} else {
			return first.sym;
		}
	}

	/** 获取尾节点标识符*/
	public Symbol getLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		} else {
			return last.sym;
		}
	}

	/** 移除节点*/
	private void remove(Node p) {
		if (p.prev == null) {
			first = p.next;
		} else {
			p.prev.next = p.next;
		}
		if (p.next == null) {
			last = p.prev;
		} else {
			p.next.prev = p.prev;
		}
		size--;
	}

	/** 移除头节点*/
	public Symbol removeFirst() {
		if (first == null) {
			throw new NoSuchElementException();
		} else {
			Symbol ret = first.sym;
			if (first.next == null) {
				first = last = null;
				size = 0;
			} else {
				first.next.prev = null;
				first = first.next;
				size--;
			}
			return ret;
		}
	}

	/** 移除尾节点*/
	public Symbol removeLast() {
		if (last == null) {
			throw new NoSuchElementException();
		} else {
			Symbol ret = last.sym;
			if (last.prev == null) {
				first = last = null;
				size = 0;
			} else {
				last.prev.next = null;
				last = last.prev;
				size--;
			}
			return ret;
		}
	}

	/** 移除表达式中第一个出现的某标识符*/
	public boolean remove(Symbol sym) {
		Node p = first;
		while (p != null) {
			if (p.sym.equals(sym)) {
				remove(p);
				return true;
			}
			p = p.next;
		}
		return false;
	}

	/** 清空表达式*/
	public void clear() {
		size = 0;
		first = last = null;
	}

	/** 获取表达式的镜像*/
	public Expression mirror() {
		Expression w = new Expression();
		ExpressionIterator it = iterator();
		while (it.hasNext()) {
			w.addFirst(it.next());
		}
		return w;
	}

	public String toString() {
		if(size()==0)	return "ε";
		else{
			StringBuffer sb = new StringBuffer();
			ExpressionIterator it = iterator();
			while (it.hasNext()) {
				sb.append(it.next());
				if (it.hasNext()) {
					sb.append(" ");
				}
			}
			return sb.toString();
		}
	}


	public static void main(String args[]) {
		List<Symbol> symbolList = new LinkedList<Symbol>();
		NonTerminalsSet v = new NonTerminalsSet();
		TerminalsSet t = new TerminalsSet();

		symbolList.add(t.addNew("LParameters"));
		symbolList.add(v.addNew("exp1"));
		symbolList.add(t.addNew("+"));
		symbolList.add(v.addNew("exp2"));
		symbolList.add(t.addNew("RParameters"));
		Expression w = new Expression(symbolList);
		
		System.out.println("表达式： "+w);
		System.out.println();

		// 正向搜索Terminals
		System.out.print("Terminals only: {");
		ExpressionIterator it = w.iterator();
		while (it.hasNextTerminal()) {
			Symbol sym = it.nextTerminal();
			System.out.print(sym+" ");
		}
		System.out.println("}");
		System.out.println();

		// 反向搜索Terminals
		System.out.print("Terminals backwards: {");
		while (it.hasPrevTerminal()) {
			Symbol sym = it.prevTerminal();
			System.out.print(sym+" ");
		}
		System.out.println("}");
		System.out.println();


		// 正向搜索Non-Terminals
		System.out.print("正向搜索NonTerminals only: {");
		it = w.iterator();
		while (it.hasNextNonTerminal()) {
			Symbol sym = it.nextNonTerminal();
			System.out.print(sym+" ");
		}
		System.out.println("}");
		System.out.println();

		// 反向搜索Non-Terminals
		System.out.print("反向搜索NonTerminals backwards: {");
		while (it.hasPrevNonTerminal()) {
			Symbol sym = it.prevNonTerminal();
			System.out.print(sym+" ");
		}
		System.out.println("}");
		System.out.println();

		Symbol[] s = w.toArray();
		System.out.print("List转化Array: {");
		for (int i = 0; i<s.length; i++) {
			System.out.print(s[i]+" ");
		}
		System.out.println("}");
		System.out.println();

		System.out.println("表达式含有\""+t.find(1).name+"\": "+w.contains(t.find(1)));
		System.out.println("表达式含有\""+"EOF"+"\": "+w.contains(t.find("EOF")));
	}
}
