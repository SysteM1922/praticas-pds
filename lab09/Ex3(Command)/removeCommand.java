import java.util.Collection;

public class removeCommand<E> extends Command<E> {

	removeCommand(Collection<E> col) {
		super(col);
	}

	@Override
	public boolean execute(E e) {
		if (this.col.remove(e)) {
			this.stack.push(e);
			return true;
		}
		return false;
	}

	@Override
	public boolean undo() {
		if (this.col.add(this.stack.peek())) {
			this.stack.pop();
			return true;
		}
		return false;
	}
}
