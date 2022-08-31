import java.util.Collection;

public interface Strategy {
	public Collection<Phone> sort(Collection<Phone> c, Spec s);
}
