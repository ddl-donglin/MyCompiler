package Parser.LR1Parser;

import java.util.*;

/** LR1×´Ì¬¼¯*/
public class LR1States {
	private List<LR1State> statesByIndex = new ArrayList<LR1State>();
	private Map statesByKernel = new HashMap();

	public int size() {
		return statesByIndex.size();
	}
	
	public Iterator iterator() {
		return Collections.unmodifiableList(statesByIndex).iterator();
	}
	
	public LR1State find(int index) {
		return (LR1State)statesByIndex.get(index);
	}

	public LR1State find(Collection kernel) {
		return (LR1State)statesByKernel.get(kernel);
	}

	public boolean add(LR1State state) {
		if (!statesByKernel.containsKey(state.getKernelItems())) {
			state.setIndex(statesByIndex.size());
			statesByIndex.add(state);
			statesByKernel.put(state.getKernelItems(), state);
			return false;
		} else {
			return true;
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i<size(); i++) {
			sb.append("I"+i+":"+find(i).toString()+"\n");
		}
		return new String(sb);
	}
}
