import java.util.Collection;
import java.util.Stack;

public abstract class Command<E> {
	protected Stack<E> stack;
	protected Collection<E> col;

	Command(Collection<E> col) {
		this.col = col;
		this.stack = new Stack<>();
	}

	public boolean execute(E element) {
		return false;
	}
	
	public boolean undo() {
		return false;
	}
}
