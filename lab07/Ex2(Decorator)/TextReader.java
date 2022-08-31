package Ex2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TextReader implements ReaderInterface {
	private Scanner textReader;

	public TextReader(File file) throws IOException{
		this.textReader = new Scanner(file);
	}

	@Override
	public boolean hasNext() {
		return textReader.hasNext();
	}

	@Override
	public String next() {
		return textReader.nextLine();
	}
}
