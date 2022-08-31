import java.util.Collection;

public class Context {

	private Strategy strat;

	public Context(Strategy strat) {
		this.strat = strat;
	}

	public void setStrategy(Strategy strat) {
		this.strat = strat;
	}

	public Collection<Phone> sort(Collection<Phone> c, Spec s) {
		return strat.sort(c, s);
	}
}
