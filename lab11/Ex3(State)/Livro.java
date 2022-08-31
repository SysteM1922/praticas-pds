public class Livro {

	private String titulo;
	private String ISBN;
	private int ano;
	private String autor;
	private State estado;

	public Livro(String titulo, String iSBN, int ano, String autor, State estado) {
		this.titulo = titulo;
		this.ISBN = iSBN;
		this.ano = ano;
		this.autor = autor;
		this.estado = estado;
	}

	public void regista() {
		if (estado.regista(this))
			this.estado = new Disponivel();
		else
			System.out.println("Operação não disponível");
	}
	
	public void requisita() {
		if (estado.requisita(this))
			this.estado = new Emprestado();
		else
			System.out.println("Operação não disponível");
	}
	
	public void devolve() {
		if (estado.devolve(this))
			this.estado = new Disponivel();
		else
			System.out.println("Operação não disponível");
	}
	
	public void reserva() {
		if (estado.reserva(this))
			this.estado = new Reservado();
		else
			System.out.println("Operação não disponível");
	}
	
	public void cancela() {
		if (estado.cancela(this))
			this.estado = new Disponivel();
		else
			System.out.println("Operação não disponível");
	}

	public String toString() {
		return titulo + "\t" + autor + "\t" + estado.toString();
	}
}
