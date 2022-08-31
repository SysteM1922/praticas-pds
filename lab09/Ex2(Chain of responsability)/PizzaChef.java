public class PizzaChef extends Chef {
	@Override
	public void request(String s) {
		System.out.print("PizzaChef: ");
		if (s.toLowerCase().contains("pizza"))
			super.accept(s);
		else
			super.deny(s);
	}
}
