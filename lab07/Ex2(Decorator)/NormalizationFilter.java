package Ex2;

import java.text.Normalizer;

public class NormalizationFilter extends Decorator{

	public NormalizationFilter(ReaderInterface i) {
		super(i);
	}

	@Override
	public String next() {
		return Normalizer.normalize(super.next(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").replaceAll("\\p{Punct}", "");
	}
}
