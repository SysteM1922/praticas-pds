public class SushiChef extends Chef {
	@Override
	public void request(String s) {
		System.out.print("SushiChef: ");
		if (s.toLowerCase().contains("sushi"))
			super.accept(s);
		else
			super.deny(s);
	}
}
