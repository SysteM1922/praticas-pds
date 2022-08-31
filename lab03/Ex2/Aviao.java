package Ex2;

import java.util.Arrays;

public class Aviao {
	private int[][] turistica;
	private int[][] executiva;
	private int turisticaDisponiveis;
	private int executivaDisponiveis;

	public Aviao(String t) {
		String[] dims = t.split("x");
		int[][] lugares = new int[Integer.parseInt(dims[0])][Integer.parseInt(dims[1])];
		for(int[] fila: lugares)
			Arrays.fill(fila, 0);
		this.turisticaDisponiveis = Integer.parseInt(dims[0]) * Integer.parseInt(dims[1]);
		this.turistica = lugares;
		this.executiva = new int[0][0];
		this.executivaDisponiveis = 0;
	}

	public Aviao(String e, String t) {
		String[] dims = e.split("x");
		int[][] lugares = new int[Integer.parseInt(dims[0])][Integer.parseInt(dims[1])];
		for (int[] fila : lugares)
			Arrays.fill(fila, 0);
		this.executivaDisponiveis = Integer.parseInt(dims[0]) * Integer.parseInt(dims[1]);
		this.executiva = lugares;
		dims = t.split("x");
		lugares = new int[Integer.parseInt(dims[0])][Integer.parseInt(dims[1])];
		for (int[] fila : lugares)
			Arrays.fill(fila, 0);
		this.turisticaDisponiveis = Integer.parseInt(dims[0]) * Integer.parseInt(dims[1]);
		this.turistica = lugares;
	}
	
	public void removeBooking(Reserva reserva) {
		for (String s : reserva.getLugares()) {
			int x = 0;
			int y = 0;
			for (int i = 0; i < s.length(); i++) {
				if (Character.isDigit(s.charAt(i)))
					x += Character.getNumericValue(s.charAt(i));
				else
					y += (Character.getNumericValue(s.charAt(i)) - Character.getNumericValue('A'));
			}
			System.out.println(x + " " + y);
			if(reserva.getClasse()=='E')
				executiva[x-1][y] = 0;
			else
				turistica[x-executiva.length-1][y] = 0;
		}	
	};

	public int[][] getTuristica() {
		return turistica;
	}

	public void setTuristica(int[][] turistica) {
		this.turistica = turistica;
	}

	public int[][] getExecutiva() {
		return executiva;
	}

	public void setExecutiva(int[][] executiva) {
		this.executiva = executiva;
	}

	public int getTuristicaDisponiveis() {
		return turisticaDisponiveis;
	}

	public void setTuristicaDisponiveis(int turisticaDisponiveis) {
		this.turisticaDisponiveis = turisticaDisponiveis;
	}

	public int getExecutivaDisponiveis() {
		return executivaDisponiveis;
	}

	public void setExecutivaDisponiveis(int executivaDisponiveis) {
		this.executivaDisponiveis = executivaDisponiveis;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(executiva);
		result = prime * result + executivaDisponiveis;
		result = prime * result + Arrays.deepHashCode(turistica);
		result = prime * result + turisticaDisponiveis;
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
		Aviao other = (Aviao) obj;
		if (!Arrays.deepEquals(executiva, other.executiva))
			return false;
		if (executivaDisponiveis != other.executivaDisponiveis)
			return false;
		if (!Arrays.deepEquals(turistica, other.turistica))
			return false;
		if (turisticaDisponiveis != other.turisticaDisponiveis)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Aviao [executiva=" + Arrays.toString(executiva) + ", executivaDisponiveis=" + executivaDisponiveis
				+ ", turistica=" + Arrays.toString(turistica) + ", turisticaDisponiveis=" + turisticaDisponiveis + "]";
	}

}
