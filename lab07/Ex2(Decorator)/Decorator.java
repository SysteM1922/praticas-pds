package Ex2;

public abstract class Decorator implements ReaderInterface{
	protected ReaderInterface i;

	public Decorator(ReaderInterface i) {
		this.i = i;
	};

	public String next() {
		return i.next();
	}

	public boolean hasNext() {
		return i.hasNext();
	}

}
