package saveState;
import systemmanager.SystemManager;
public  class Momento {
	private SystemManager state;
	public Momento(SystemManager state) {
		this.state = state;
	}
	public SystemManager getSaveState() {
		return this.state;
	}
}
