package Ex2;

import java.util.ArrayList;

public class Voo {
	private Aviao aviao;
	private String codigo;
	private ArrayList<Reserva> reservas;
	private int numReserva = 1;

	public Voo() {
		this.reservas = new ArrayList<>();
	};

	public Voo(String codigo, String seatsExecutive, String seatsTourist) {
		if (seatsExecutive.equals("0x0"))
			this.aviao = new Aviao(seatsTourist);
		else
			this.aviao = new Aviao(seatsExecutive, seatsTourist);
		this.codigo = codigo;
		this.reservas = new ArrayList<>();
	}
	
	public Reserva addBooking(String classe, int numLugares, String codigoVoo, Aviao aviao) {
		Reserva reserva = new Reserva(codigoVoo + ":" + String.valueOf(numReserva++), numLugares, classe.charAt(0));
		reservas.add(reserva);
		int[][] lugares;
		if (classe.equals("E")) {
			lugares = aviao.getExecutiva();
			aviao.setExecutivaDisponiveis(aviao.getExecutivaDisponiveis() - numLugares);
		} else {
			lugares = aviao.getTuristica();
			aviao.setTuristicaDisponiveis(aviao.getTuristicaDisponiveis() - numLugares);
		}
		for (int i = 0; i < lugares.length; i++) {
			if (checkLine(lugares[i])) {
				for (int e = 0; e < lugares[i].length && numLugares > 0; e++) {
					lugares[i][e] = numReserva - 1;
					numLugares--;
					if(classe.equals("E"))
						reserva.addSeat(String.valueOf(i + 1) + String.valueOf((char) ('A' + e)));
					else
						reserva.addSeat(String.valueOf(i + 1 + this.aviao.getExecutiva().length) + String.valueOf((char) ('A' + e)));
				}
			}
		}
		for (int i = 0; i < lugares.length; i++) {
			for (int e = 0; e < lugares[i].length && numLugares > 0; e++) {
				if (lugares[i][e] == 0) {
					lugares[i][e] = numReserva - 1;
					numLugares--;
					if(classe.equals("E"))
						reserva.addSeat(String.valueOf(i + 1) + String.valueOf((char) ('A' + e)));
					else
						reserva.addSeat(String.valueOf(i + 1 + this.aviao.getExecutiva().length) + String.valueOf((char) ('A' + e)));
				}
			}
		}
		return reserva;
	}
	
	public void removeBooking(String bookingCode) {
		for (Reserva reserva : reservas) {
			if (reserva.getBookingCode().equals(bookingCode)) {
				reservas.remove(reserva);
				aviao.removeBooking(reserva);
				break;
			}
		}
	}

	private boolean checkLine(int[] line) {
		for (int i : line) {
			if (i != 0) {
				return false;
			}
		}
		return true;
	}

	public Aviao getAviao() {
		return aviao;
	}

	public void setAviao(Aviao aviao) {
		this.aviao = aviao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public ArrayList<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(ArrayList<Reserva> reservas) {
		this.reservas = reservas;
	}

	public int getNumReserva() {
		return numReserva;
	}

	public void setNumReserva(int numReserva) {
		this.numReserva = numReserva;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aviao == null) ? 0 : aviao.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + numReserva;
		result = prime * result + ((reservas == null) ? 0 : reservas.hashCode());
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
		Voo other = (Voo) obj;
		if (aviao == null) {
			if (other.aviao != null)
				return false;
		} else if (!aviao.equals(other.aviao))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (numReserva != other.numReserva)
			return false;
		if (reservas == null) {
			if (other.reservas != null)
				return false;
		} else if (!reservas.equals(other.reservas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Voo [aviao=" + aviao + ", codigo=" + codigo + ", numReserva=" + numReserva + ", reservas=" + reservas
				+ "]";
	}

}
