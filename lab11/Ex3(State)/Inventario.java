public class Inventario implements State {

	@Override
	public boolean regista(Livro l) {
		return true;
	}

	@Override
	public boolean requisita(Livro l) {
		return false;
	}

	@Override
	public boolean devolve(Livro l) {
		return false;
	}

	@Override
	public boolean reserva(Livro l) {
		return false;
	}

	@Override
	public boolean cancela(Livro l) {
		return false;
	}

	@Override
	public String toString() {
		return "[Inventário]";
	}
	
}
