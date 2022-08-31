import java.util.Random;

public class Chef {
	private Chef successor = null;

	public void request(String s) {
		System.out.println("Can I please get a " + s + "?");
		if (this.successor == null)
			System.out.println("We're sorry but that request can't be satisfied by our service!\n");
		else
			successor.request(s);
	}

	public void accept(String s) {
		System.out.println("Starting to cook " + s + ". Out in " + new Random().nextInt(31) + " minutes!\n");
	}

	public void deny(String s) {
		System.out.println("I can't cook that.");
		if (this.successor == null)
			System.out.println("We're sorry but that request can't be satisfied by our service!\n");
		else
			successor.request(s);
	}
	
	public Chef setSucessor(Chef c) {
		this.successor = c;
		return this;
	}
}
