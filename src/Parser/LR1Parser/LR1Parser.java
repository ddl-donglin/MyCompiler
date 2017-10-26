package Parser.LR1Parser;

/** LR1语法分析器*/
import Parser.ContextFreeGrammar.*;
import java.io.*;
import java.util.*;

public class LR1Parser extends AbstractParser {

	protected int stateNo;
	protected ParseAction[][] actionTable;
	protected Integer[][] gotoTable;
	protected Integer startStateIndex;
	protected LR1States states;//项目规范集族

	public interface ParseAction {
		boolean isShiftAction();//移进动作
		boolean isReduceAction();//归约动作
	}

	/** 移进动作*/
	public class ShiftAction implements ParseAction {
		private Integer stateIndex;
		public ShiftAction(Integer stateIndex) {
			this.stateIndex = stateIndex;
		}
		public Integer getStateIndex() {
			return stateIndex;
		}
		public boolean isShiftAction() {
			return true;
		}
		public boolean isReduceAction() {
			return false;
		}
		public String toString() {
			return stateIndex.toString();
		}
	}

	/** 归约动作*/
	public class ReduceAction implements ParseAction {
		private Production production;
		public ReduceAction(Production production) {
			this.production = production;
		}
		public Production getProduction() {
			return production;
		}
		public boolean isShiftAction() {
			return false;
		}
		public boolean isReduceAction() {
			return true;
		}
		public String toString() {
			return production.toString();
		}
	}
	
	public LR1Parser(CFG gammar, TerminalsSet toAnalyze) {
		super(gammar, toAnalyze, true);
	}

	public void precompute() {//预计算
		/** 计算First集*/
		g.computeFirst();

		/** 计算LR(1)分析表*/
		states = new LR1States();//状态集，包含所有状态
		
		//计算起始状态
		LR1State startState = new LR1State();
		LR1Item startItem = new LR1Item(new LR0Item(sp), t.EOF);//起始状态核集合：$Start = S, EOF
		startState.addKernelItem(startItem);//添加起始状态核集合
		startState.closure(g);//闭包运算

		states.add(startState);//添加第一个状态（起始状态）

		//状态动作-转移表：用一维链和一维数组构成二维空间
		List<ParseAction[]> actionList = new LinkedList<ParseAction[]>();
		List<Integer[]> gotoList = new LinkedList<Integer[]>();
		ParseAction[] actionLine = new ParseAction[t.count()];
		Integer[] gotoLine = new Integer[v.count()];
		actionList.add(actionLine);
		gotoList.add(gotoLine);

		int i = 0;
		while (i<states.size()) {
			LR1State state = states.find(i);
			for (int sid = 0; sid<g.symbolCount(); sid++) {
				
				//求新状态
				LR1State newState = new LR1State();//用于保存产生的新状态
				Symbol sym = g.symbol(sid);
//				System.out.println("g.symbol"+g.symbol(sid));
				//if (sym == s) continue;
				Iterator<LR1Item> it = state.iterator(sym);
				while (it.hasNext()) {
					LR1Item item = (LR1Item)it.next();
					newState.addKernelItem(item.nextItem());
				}
				
				//产生了新状态，执行if；没有产生新状态，继续for下一个Symbol
				if (!newState.isEmpty()) {
					LR1State oldState = states.find(newState.getKernelItems());
					//if：新状态不 存在； else：新状态已存在
					if (oldState == null) {
						newState.closure(g);
						states.add(newState);

						actionLine = new ParseAction[t.count()];
						gotoLine = new Integer[v.count()];

						//添加 归约动作
						Iterator<LR1Item> it2 = newState.iterator();
						while (it2.hasNext()) {
							LR1Item item = (LR1Item)it2.next();
							if (item.isComplete()) {
								Production prod = item.getProduction();
								TerminalSymbol a = item.getLookahead();
								if (actionLine[a.getIndex()] == null) {
									actionLine[a.getIndex()] = new ReduceAction(prod);
								} else {
									throw new RuntimeException(
										"Grammar not LR(1). \nReduce-Reduce conflict: "+
										state+"\nState: "+newState+"\nLookahead: "+a+
										"\nProduction 1: "+actionLine[a.getIndex()]+
										"\nProduction 2: "+prod);
								}
							}
						}

						actionList.add(actionLine);
						gotoList.add(gotoLine);
					} else {
						newState = oldState;
					}

					actionLine = (ParseAction[])actionList.get(state.getIndex());
					gotoLine = (Integer[])gotoList.get(state.getIndex());

					if (sym.isTerminal()) {
						TerminalSymbol a = (TerminalSymbol)sym;
						if (actionLine[a.getIndex()] == null) {
							actionLine[a.getIndex()] = new ShiftAction(
								new Integer(newState.getIndex()));
						} else {
							throw new RuntimeException(
								"Grammar not LR(1). \nShift-Reduce conflict: "+
								state+"\nState: "+state+"\nTerminal: "+a+
								"\nProduction: "+actionLine[a.getIndex()]+
								"\nShift state: "+newState);
						}
					} else {
						NonTerminalSymbol x = (NonTerminalSymbol)sym;
						gotoLine[x.getIndex()] = new Integer(newState.getIndex());
					}
				}
			}
			i++;
		}

		startStateIndex = new Integer(startState.getIndex());

		stateNo = states.size();
		actionTable = new ParseAction[stateNo][];
		gotoTable = new Integer[stateNo][];
		for (int k = 0; k<stateNo; k++) {
			actionTable[k] = (ParseAction[])actionList.get(k);
			gotoTable[k] = (Integer[])gotoList.get(k);
		}
		
		//项目集状态个数
		System.out.println(states.size()+" states");
		System.out.println("start: "+startStateIndex);
	}



	public String parse() throws IOException, SyntaxError {

		StringBuffer sb = new StringBuffer();//保存分析过程
//		List<Production> prodReduce = new LinkedList<Production>();//保存分析过程中用到的所有归约表达式
		LinkedList<Integer> stackState = new LinkedList<Integer>();//状态栈
		LinkedList<String> stackSymbol = new LinkedList<String>();//符号栈，填入的是Symbol.name
		stackState.add(startStateIndex);
		stackSymbol.add("#");
		
		Iterator<TerminalSymbol> it = toAnalyzeTermianls.iterator();
		TerminalSymbol a = it.next();//输入串的当前指针

		int step = 1;
		sb.append(String.format("%1$-12s\t", "步骤"));
		sb.append(String.format("%1$-12s\t", "状态栈"));
		sb.append(String.format("%1$-12s\t", "符号栈"));
		sb.append(String.format("%1$-12s\t", "剩余串"));
		sb.append(String.format("%1$-12s\t", "ACTION"));
		sb.append(String.format("%1$-12s\t", "GOTO"));
		sb.append('\n');
		
		while (true) {
			
			/** 保存每步解析的状态*/
			
			//保存当前步骤标识
			sb.append(String.format("%1$-12s\t", "("+step+")"));  
			
			//保存每步的状态栈
			String tempSState = new String();
			Iterator<Integer> stackIt = stackState.iterator();
			while (stackIt.hasNext()) {
				tempSState += String.valueOf(stackIt.next());
			}
			sb.append(String.format("%1$-12s\t", tempSState));
			
			//保存每步的符号栈
			String tempSSym = new String();
			Iterator<String> stackSs = stackSymbol.iterator();
			while (stackSs.hasNext()) {
				tempSSym += stackSs.next();
			}
			sb.append(String.format("%1$-12s\t", tempSSym));
			
			//保存每步的余留串
			String tempStr = new String();
			int indexOfA = a.getIndex()-1;
			if(indexOfA != -1){
				for(; indexOfA<toAnalyzeTermianls.count()-1; indexOfA++){
					tempStr += toAnalyzeTermianls.find(indexOfA);
				}
				sb.append(String.format("%1$-12s\t", tempStr+"#"));
			}
			else{
				sb.append(String.format("%1$-12s\t", "#"));
			}
			
			/** 分析输入串 并 保存 ACTION、GOTO*/
			Integer stateIndex = (Integer)stackState.getLast();
			ParseAction pa = actionTable[stateIndex.intValue()][t.find(a.getName()).getIndex()];
			if (pa == null) {
				throw new SyntaxError(a);
			}else{
				if (pa.isShiftAction()) {
					sb.append(String.format("%1$-12s\t", "State"+pa));
					ShiftAction sa = (ShiftAction)pa;
					stackState.add(sa.getStateIndex());
					stackSymbol.add(a.getName());
					a = it.next();
				} else {
					ReduceAction ra = (ReduceAction)pa;
					Production prod = ra.getProduction();
					if (prod == sp) {
						sb.append(String.format("%1$-12s\t", "accept"));
						return new String(sb); // accept
					}
					sb.append(String.format("%1$-12s\t", pa));
					for (int i = 0; i<prod.getRHS().size(); i++) {
						stackState.removeLast();
						stackSymbol.removeLast();
					}
					stateIndex = (Integer)stackState.getLast();
					int nextState = gotoTable[stateIndex.intValue()][prod.getLHS().getIndex()];
					stackState.addLast(nextState);
					sb.append(String.format("%1$-12s\t", String.valueOf(nextState)));
					stackSymbol.add(prod.getLHS().getName());
//					prodReduce.add(prod);
				}
			}
			step++;
			sb.append('\n');
		}
	}
	
	public ParseAction getAction(int state, TerminalSymbol a) {
		return actionTable[state][a.getIndex()];
	}
	
	public int getGoto(int state, NonTerminalSymbol x) {
		Integer newState = gotoTable[state][x.getIndex()];
		if (newState != null) {
			return newState.intValue();
		} else {
			return -1;
		}
	}
	
	public Production getStartProduction() {
		return sp;
	}

	public int getStateNo() {
		return stateNo;
	}
	
	public int getStartState() {
		return startStateIndex.intValue();
	}
	
	public String action_gotoTableToString(){
		StringBuffer sb = new StringBuffer();
		sb.append("\n"+t.toString()+v.toString()+"\n");
		/** 添加表头*/
		sb.append(String.format("%1$-12s\t", " "));
		Iterator<TerminalSymbol> itT = t.iterator();
		while (itT.hasNext()) {
			sb.append(String.format("%1$-12s\t", itT.next()));
		}
		Iterator<NonTerminalSymbol> itV = v.iterator();
		while (itV.hasNext()) {
			String str = itV.next().toString();
			if(!str.equals("$START"))
				sb.append(String.format("%1$-12s\t", str));
		}
		sb.append("\n");
		
		/** 填表*/
		for(int i=0; i<stateNo; i++){
			sb.append(String.format("%1$-12d\t", i));
			for(ParseAction pa: actionTable[i]){
				if(pa!=null){
					if(pa.isShiftAction()){
						sb.append(String.format("%1$-12s\t", "State"+pa.toString()));
					} else{
						sb.append(String.format("%1$-12s\t", pa.toString()));
					}
				}else{
					sb.append(String.format("%1$-12s\t", ""));
				}
			}
			for(Integer ig: gotoTable[i]){
				if(ig!=null){
					sb.append(String.format("%1$-12d\t", ig));
				}else{
					sb.append(String.format("%1$-12s\t", ""));
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
		return new String(sb);
	}
	
	public String getProcess(CFG grammar){
		StringBuffer sb = new StringBuffer();
		sb.append("\n文法产生式\n"+grammar.getProductions().toString()
				+"\n项目规范集族\n"+states
				+"\nLR(1)分析表\n"+action_gotoTableToString()
				+"\n\n");
		return new String(sb);
	}
	
	public static void main(String args[]) throws Exception {
		
	}
}
