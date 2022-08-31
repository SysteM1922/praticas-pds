import java.util.concurrent.TimeUnit;

public class Comboio extends Thread{
	private Mediator med;
	private int nr;
	private String estacao;
	private Sentido sentido;

	public Comboio(int nr, String estacao, Sentido sentido, Mediator med) {
		this.nr = nr;
		this.estacao = estacao;
		this.sentido = sentido;
		this.med = med;
		System.out.println(this.nr + "\t| entrou em "+ this.estacao);
	}

	public void run() {
		while (true) {
			try {
				TimeUnit.SECONDS.sleep(1);;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (med.checkLine(this)) {
				System.out.println(this.nr + "\t| avançou para " + this.estacao);
			}
			else
				System.out.println(this.nr + "\t| pediu para avançar de "+ this.estacao);
		}
	}

	public int getNr() {
		return nr;
	}

	public String getEstacao() {
		return estacao;
	}

	public void setEstacao(String estacao) {
		this.estacao = estacao;
	}

	public Sentido getSentido() {
		return sentido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estacao == null) ? 0 : estacao.hashCode());
		result = prime * result + ((med == null) ? 0 : med.hashCode());
		result = prime * result + nr;
		result = prime * result + ((sentido == null) ? 0 : sentido.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comboio other = (Comboio) obj;
		if (estacao == null) {
			if (other.estacao != null)
				return false;
		} else if (!estacao.equals(other.estacao))
			return false;
		if (med == null) {
			if (other.med != null)
				return false;
		} else if (!med.equals(other.med))
			return false;
		if (nr != other.nr)
			return false;
		if (sentido != other.sentido)
			return false;
		return true;
	}
}
