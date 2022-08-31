package Ex2;

import java.io.File;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		File file = new File("Ex2/file.txt");
		ReaderInterface textR = new TextReader(file);
		while (textR.hasNext()) {
			System.out.println(textR.next());
		}
		System.out.println();
		TermFilter termF = new TermFilter(new TextReader(file));
		while (termF.hasNext()) {
			System.out.println(termF.next());
		}
		System.out.println();
		NormalizationFilter normF = new NormalizationFilter(new TermFilter(new TextReader(file)));
		while (normF.hasNext()) {
			System.out.println(normF.next());
		}
		System.out.println();
		VowelFilter vowelF = new VowelFilter(new NormalizationFilter(new TermFilter(new TextReader(file))));
		while (vowelF.hasNext()) {
			System.out.println(vowelF.next());
		}
		System.out.println();
		CapitalizationFilter capF = new CapitalizationFilter(new VowelFilter(new NormalizationFilter(new TermFilter(new TextReader(file)))));
		while (capF.hasNext()) {
			System.out.println(capF.next());
		}
	}
}
