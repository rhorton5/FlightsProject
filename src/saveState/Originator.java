package saveState;
import systemmanager.SystemManager;
public class Originator {
	private SystemManager sm;
	public void setState(SystemManager sm) {
		this.sm = sm;
	}
	public Momento save() {
		return new Momento(sm);
	}
	public void undo(Momento m) {
		sm = m.getSaveState();
	}
}
