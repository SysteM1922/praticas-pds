package Ex2;

import java.util.Arrays;

public class Reserva {
	private String bookingCode;
	private int numPassageiros;
	private char classe;
	private String[] lugares;
	private int lugarAtual;

	public Reserva(String code, int num, char c) {
		this.bookingCode = code;
		this.numPassageiros = num;
		this.classe = c;
		this.lugares = new String[num];
		this.lugarAtual = 0;
	}

	public void addSeat(String code) {
		lugares[lugarAtual++] = code;
	}

	public String getBookingCode() {
		return bookingCode;
	}

	public void setBookingCode(String bookingCode) {
		this.bookingCode = bookingCode;
	}

	public int getNumPassageiros() {
		return numPassageiros;
	}

	public void setNumPassageiros(int numPassageiros) {
		this.numPassageiros = numPassageiros;
	}

	public char getClasse() {
		return classe;
	}

	public void setClasse(char classe) {
		this.classe = classe;
	}

	public String[] getLugares() {
		return lugares;
	}

	public void setLugares(String[] lugares) {
		this.lugares = lugares;
	}

	public int getLugarAtual() {
		return lugarAtual;
	}

	public void setLugarAtual(int lugarAtual) {
		this.lugarAtual = lugarAtual;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookingCode == null) ? 0 : bookingCode.hashCode());
		result = prime * result + classe;
		result = prime * result + lugarAtual;
		result = prime * result + Arrays.hashCode(lugares);
		result = prime * result + numPassageiros;
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
		Reserva other = (Reserva) obj;
		if (bookingCode == null) {
			if (other.bookingCode != null)
				return false;
		} else if (!bookingCode.equals(other.bookingCode))
			return false;
		if (classe != other.classe)
			return false;
		if (lugarAtual != other.lugarAtual)
			return false;
		if (!Arrays.equals(lugares, other.lugares))
			return false;
		if (numPassageiros != other.numPassageiros)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reserva [bookingCode=" + bookingCode + ", classe=" + classe + ", lugarAtual=" + lugarAtual
				+ ", lugares=" + Arrays.toString(lugares) + ", numPassageiros=" + numPassageiros + "]";
	}

}
