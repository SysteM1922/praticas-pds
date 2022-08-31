import java.util.Collection;

public class addCommand<E> extends Command<E> {

	addCommand(Collection<E> col) {
		super(col);
	}

	@Override
	public boolean execute(E e) {
		if (this.col.add(e)) {
			this.stack.push(e);
			return true;
		}
		return false;
	}

	@Override
	public boolean undo() {
		if (this.col.remove(this.stack.peek())) {
			this.stack.pop();
			return true;
		}
		return false;
	}
}
