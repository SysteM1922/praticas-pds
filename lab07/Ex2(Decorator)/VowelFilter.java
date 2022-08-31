package Ex2;

public class VowelFilter extends Decorator {
	
	public VowelFilter(ReaderInterface i) {
		super(i);
	}

	@Override
	public String next() {
		return super.next().replaceAll("[aeiouAEIOU]", "");
	}
}
