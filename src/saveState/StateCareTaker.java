package saveState;
import java.util.Stack;
public class StateCareTaker {
	private Stack<Momento> states = new Stack<Momento>();
	public StateCareTaker() {
		
	}
	public void addState(Momento m) {
		states.push(m);
	}
	public Momento undo() {
		return states.pop();
	}
	public Momento undoToStart() {
		Momento res = states.peek();
		while(states.empty()) {
			res = states.pop();
		}
		return res;
	}
	public Momento undoToCertainState(int num) {
		Momento res = states.peek();
		for(int i = 0; i < num; i++) {
			if(states.empty()) {
				break;
			}
			
			res = states.pop();
		}
		return res;
	}
}
