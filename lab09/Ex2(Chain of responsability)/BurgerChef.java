public class BurgerChef extends Chef {
	@Override
	public void request(String s) {
		System.out.print("BurgerChef: ");
		if (s.toLowerCase().contains("burger"))
			super.accept(s);
		else
			super.deny(s);
	}
}
