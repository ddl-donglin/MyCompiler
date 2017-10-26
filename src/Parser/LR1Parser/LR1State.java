package Parser.LR1Parser;

/** LR1状态*/
import Parser.ContextFreeGrammar.*;
import java.util.*;

public class LR1State {
	
	private int index;//状态序号
	private Set<LR1Item> kernel = new LinkedHashSet<LR1Item>();//核集合
	private Set<LR1Item> closure = new LinkedHashSet<LR1Item>();//闭包

	private Map<Symbol, LinkedList<LR1Item>> itemsByNext = 
		new LinkedHashMap<Symbol, LinkedList<LR1Item>>();//项目集

	public boolean equals(Object o) {
		return (o instanceof LR1State &&
			kernel.equals(((LR1State)o).kernel) &&
			closure.equals(((LR1State)o).closure));
	}

	public int hashCode() {
		return 17+kernel.hashCode()*37+closure.hashCode();
	}

	/** 判断核集合:true表示空*/
	public boolean isEmpty() {
		return kernel.isEmpty();
	}

	private void addToItemsByNext(LR1Item item) {
		if (!item.isComplete()) {
			Symbol sym = item.getNextSymbol();
			LinkedList<LR1Item> itemList;
			if (itemsByNext.containsKey(sym)) {
				itemList = itemsByNext.get(sym);
			} else {
				itemList = new LinkedList<LR1Item>();
			}
			itemList.add(item);
			itemsByNext.put(sym, itemList);
		}
	}

	public boolean addKernelItem(LR1Item item) {
		if (kernel.add(item)) {
			addToItemsByNext(item);
			return true;
		} else {
			return false;
		}
	}

	public Collection<LR1Item> addKernelItemWithClosure(LR1Item item, CFG g) {
		if (addKernelItem(item)) {
			Collection<LR1Item> closureKernel = new LinkedList<LR1Item>();
			closureKernel.add(item);
			return closure(closureKernel, g);
		} else {
			return null;
		}
	}

	public Iterator<LR1Item> iterator() {
		return new Iterator<LR1Item>() {
			Iterator<LR1Item> it = kernel.iterator();
			boolean inKernel = true;
			
			public boolean hasNext() {
				if (it.hasNext() || (inKernel && !closure.isEmpty())) {
					return true; 
				} else {
					return false;
				}
			}
			
			public LR1Item next() {
				if (inKernel && !it.hasNext()) {
					it = closure.iterator();
					inKernel = false;
				}
				return it.next();
			}
			
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	public LinkedList<LR1Item> find(Symbol x) {
		return (LinkedList<LR1Item>)itemsByNext.get(x);
	}

	public Iterator<LR1Item> iterator(Symbol x) {
		List<LR1Item> itemList = find(x);
		if (itemList == null) {
			//迭代器为空
			return new Iterator<LR1Item>() {
				public boolean hasNext() { return false; }
				public LR1Item next() { throw new NoSuchElementException(); }
				public void remove() { throw new UnsupportedOperationException(); }
			};
		} else {
			return Collections.unmodifiableList(itemList).iterator();
		}
	}

	public boolean contains(LR1Item item) {
		return kernel.contains(item) || closure.contains(item);
	}

	/** 闭包运算*/
	private Collection<LR1Item> closure(Collection<LR1Item> closureKernel, CFG g) {
		Collection<LR1Item> newClosureItems = new LinkedList<LR1Item>(closureKernel);
		LinkedList<LR1Item> queue = new LinkedList<LR1Item>(closureKernel);

		while (!queue.isEmpty()) {
			LR1Item item = (LR1Item)queue.removeFirst();
			
			if (!item.isComplete()) {
				Symbol sym = (Symbol)item.getNextSymbol();
				if (!sym.isTerminal()) {
					Iterator<Symbol> firstIt = g.first(item.getSufixAndLookahead()).iterator();
					while (firstIt.hasNext()) {
						TerminalSymbol newLookahead = (TerminalSymbol)firstIt.next();
						List<Production> prodList = g.getProductions().find((NonTerminalSymbol)sym);
						Iterator<Production> pIt = prodList.iterator();
						while (pIt.hasNext()) {
							Production prod = (Production)pIt.next();
							LR1Item newItem = new LR1Item(new LR0Item(prod), newLookahead);
							if (closure.add(newItem)) {
								addToItemsByNext(newItem);
								queue.addLast(newItem);
								newClosureItems.add(newItem);
							}
						}
					}
				}
			}			
		}
		return newClosureItems;
	}
	
	public Collection<LR1Item> closure(CFG g) {
		return closure(kernel, g);
	}

	void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public Collection<LR1Item> getKernelItems() {
		return Collections.unmodifiableSet(kernel);
	}

	public Collection<LR1Item> getClosureItems() {
		return Collections.unmodifiableSet(closure);
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n{");
		Iterator<LR1Item> it = kernel.iterator();
		while (it.hasNext()) {
			sb.append("\n    "+it.next());
			if (it.hasNext()) {
				sb.append(", ");
			}
		}
		sb.append(";");
		it = closure.iterator();
		while (it.hasNext()) {
			sb.append("\n    "+it.next());
			if (it.hasNext()) {
				sb.append(", ");
			}
		}
		sb.append("\n}");
		return new String(sb);
	}
}
