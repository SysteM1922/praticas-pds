package Ex2;

public class TermFilter extends Decorator {
	private String[] line;
	private int counter;

	public TermFilter(ReaderInterface i) {
		super(i);
		this.counter = 0;
		this.line = super.next().split(" ");
	}

	@Override
	public String next() {
		String s = line[counter++];
		if (counter == line.length) {
			if (super.hasNext()) {
				line = super.next().split(" ");
				counter = 0;
			}
		}
		return s;
	}
	
	@Override
	public boolean hasNext() {
		if (counter < line.length)
			return true;
		return false;
	}
}
