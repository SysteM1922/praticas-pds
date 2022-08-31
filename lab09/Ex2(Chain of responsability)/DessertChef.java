public class DessertChef extends Chef {
	@Override
	public void request(String s) {
		System.out.print("DessertChef: ");
		if (s.toLowerCase().contains("dessert"))
			super.accept(s);
		else
			super.deny(s);
	}
}
