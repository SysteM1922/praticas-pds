public class PastaChef extends Chef {
	@Override
	public void request(String s) {
		System.out.print("PastaChef: ");
		if (s.toLowerCase().contains("pasta"))
			super.accept(s);
		else
			super.deny(s);
	}
}
