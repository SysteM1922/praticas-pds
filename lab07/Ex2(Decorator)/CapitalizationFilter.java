package Ex2;

import java.lang.Character;

public class CapitalizationFilter extends Decorator {

	public CapitalizationFilter(ReaderInterface i) {
		super(i);
	}

	@Override
	public String next() {
		String s = super.next();
		int size = s.length()-1;
		if(size>1)
			s = Character.toUpperCase(s.charAt(0)) + s.substring(1, size).toLowerCase()
					+ Character.toUpperCase(s.charAt(size));
		else if(size == 1)
			s = String.valueOf(Character.toUpperCase(s.charAt(0)));
		return s;
	}
}
