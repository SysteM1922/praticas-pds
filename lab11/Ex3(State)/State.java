public interface State {
	public boolean regista(Livro l);
	public boolean requisita(Livro l);
	public boolean devolve(Livro l);
	public boolean reserva(Livro l);
	public boolean cancela(Livro l);
	public String toString();
}
