package Ex2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ex2 {
	private static ArrayList<Voo> voos;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		String input = "";
		String[] opts;
		voos = new ArrayList<>();
		do {
			System.out.println("\nEscolha uma opção: (H para ajuda)");
			input = sc.nextLine();
			opts = input.split("\\s+");
			switch (opts[0]) {
				case "H":
					if (opts.length != 1)
						System.out.println("O comando H deve ser passado como: H");
					else
						printMenu();
						break;
				case "I":
					if (opts.length != 2)
						System.out.println("O comando I deve ser passado como: I <filename>");
					else {
						File file = new File(opts[1]);
						if (file.exists()) {
							readFile(file);
						} else
							System.out.println("Nome de ficheiro inválido!");
					}
					break;
				case "M":
					if (opts.length != 2)
						System.out.println("O comando M deve ser passado como: M <flight_code>");
					else if (checkFlightCode(opts[1]))
						System.out.println("Código de voo inválido!");
					else
						printFlight(opts[1]);
						break;
				case "F":
					if (opts.length != 4)
						System.out.println(
								"O comando F deve ser passado como: F flight_code num_seats_executive num_seats_tourist");
					else {
						if (!checkFlightCode(opts[1])) {
							System.out.println("Código de voo inválido!");
							break;
						}
						if (!opts[2].matches("\\d+x\\d+")) {
							System.out.println("Número de lugares deve ser um inteiro!");
							break;
						}
						if (!opts[3].matches("\\d+x\\d+")) {
							System.out.println("Número de lugares deve ser um inteiro!");
							break;
						}
						addFlight(opts[1],opts[2],opts[3]);
					}
					break;
				case "R":
					if (opts.length != 4) {
						System.out.println("O comando R deve ser passado como: R flight_code class number_seats");
					} else {
						if (checkFlightCode(opts[1])) {
							System.out.println("Código de voo inválido!");
							break;
						}
						if (!opts[2].equals("E") && !opts[2].equals("T")) {
							System.out.println("Class deve ser E ou T!");
							break;
						}
						if (!checkStringIsInt(opts[3])) {
							System.out.println("Número de lugares deve ser um inteiro!");
							break;
						}
						if (!checkNumSeats(opts[1], Integer.parseInt(opts[3]), opts[2])) {
							System.out.println("Número de lugares " + opts[2] + " indisponível!");
							break;
						}
						addBooking(opts[1], opts[2], Integer.parseInt(opts[3]));
					}

					break;
				case "C":
					if(opts.length != 2){
						System.out.println("O comando C deve ser passado como: C reservation_code");
					} else {
						if (!opts[1].contains(":")) {
							System.out.println("Código de reserva inválido!");
							break;
						}
						String[] codes = opts[1].split(":");
						if (checkFlightCode(codes[0])) {
							System.out.println("Código de reserva inválido!");
							break;
						}
						if (!checkBookingCode(codes[0], opts[1])) {
							System.out.println("Código de reserva inválido!");
							break;
						}
						cancelBooking(codes[0], opts[1]);
					}
					break;
				case "Q":
					if (opts.length != 1) {
						System.out.println("O comando Q deve ser passado como: Q");
					}
					break;
				default:
					System.out.println("Opção Inválida!");
					break;
			}
		} while (!opts[0].equals("Q"));
		sc.close();
	}

	public static boolean checkFlightCode(String code) {
		for (Voo voo : voos) {
			if (voo.getCodigo().equals(code))
				return false;
		}
		return true;
	}

	public static boolean checkNumSeats(String code, int num, String classe) {
		for (Voo voo : voos) {
			if (voo.getCodigo().equals(code)) {
				if (classe.equals("E")) {
					if(voo.getAviao().getExecutivaDisponiveis()>=num)
						return true;
					else
						return false;
				} else {
					if(voo.getAviao().getTuristicaDisponiveis()>=num)
						return true;
					else
						return false;
				}
			}
		}
		return false;
	}

	public static void printMenu() {
		System.out.println(
				"Ajuda:\n\nH\n\tAjuda\n\nI <filename>\n\tLê um ficheiro de texto contento informação sobre um voo\n\nM <flight_code>\n\tExibe o mapa das reservas de um voo\n\nF <flight_code> <num_seats_executive> <num_seats_tourist>\n\tAcrescenta um novo voo\n\nR <flight_code> <class> <number_seats>\n\tAcrescenta uma nova reserva a um voo\n\nC <reservation_code>\n\tCancela uma reserva\n\nQ\n\tTermina o programa\n");
	}
	
	public static void readFile(File file) throws IOException {
		Scanner sc = new Scanner(file);
		String nextLine;
		Voo voo = new Voo();
		int count = 0;
		Aviao aviao = null;
		while (sc.hasNextLine()) {
			nextLine = sc.nextLine();
			String[] args = nextLine.split("\\s+");
			if (count == 0 && !nextLine.startsWith(">")) {
				System.out.println("Cabeçalho do ficheiro inválido");
				break;
			} else if (count != 0 && !nextLine.matches("T \\d+") && !nextLine.matches("E \\d+")) {
				System.out.println("Formato de Reserva Inválido");
				break;
			}
			if (nextLine.startsWith(">")) {
				String codigo = args[0].substring(1, args[0].length());
				if (!checkFlightCode(codigo)) {
					System.out.println("Código de voo inválido!");
					break;
				}
				System.out.print("Código de voo "+codigo+". ");
				voo.setCodigo(codigo);
				if (args.length == 2) {
					aviao = new Aviao(args[1]);
					voo.setAviao(aviao);
					System.out.println("Lugares disponíveis "+voo.getAviao().getTuristicaDisponiveis()+" lugares em classe Turística.\nClasse executiva não disponível neste voo.");
				} else {
					aviao = new Aviao(args[1], args[2]);
					voo.setAviao(aviao);
					System.out.println("Lugares disponíveis "+voo.getAviao().getExecutivaDisponiveis()+" lugares em classe Executiva; "+voo.getAviao().getTuristicaDisponiveis()+" lugares em classe Turística.");
				}
				voos.add(voo);
			} else {
				
				String classe = args[0];
				int numLugares = Integer.parseInt(args[1]);
				if (checkNumSeats(voo.getCodigo(), numLugares, classe)) {
					voo.addBooking(classe, numLugares, voo.getCodigo(), aviao);
				}
				else
					System.out.println("Não foi possível obter lugares para a reserva: " + classe + " " + numLugares);
			}
			count = 1;
		}
		sc.close();

	}
	
	public static boolean checkStringIsInt(String string) {
		try{
			Integer.parseInt(string);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public static void printFlight(String code) {
		for (Voo voo : voos) {
			if (voo.getCodigo().equals(code)) {
				Aviao aviao = voo.getAviao();
				int numExecLines = 0;
				int numTuristLines = aviao.getTuristica().length;
				int numExecSeats = 0;
				int numTuristSeats = aviao.getTuristica()[0].length;
				if (aviao.getExecutiva().length != 0) {
					numExecLines = aviao.getExecutiva().length;
					numExecSeats = aviao.getExecutiva()[0].length;
				}
				System.out.print(" ");
				int letraCount = 0;
				for (int i = 0; i < numExecLines + numTuristLines; i++) {
					System.out.printf("%3d",i + 1);
				}
				System.out.print("\n");
				for (int i = 0; i < numTuristSeats || i < numExecSeats; i++) {
					System.out.print(((char) ('A' + letraCount++)));
					try{
						for (int e = 0; e < numExecLines; e++) {
							System.out.printf("%3d",aviao.getExecutiva()[e][i]);
						}
					} catch (Exception ex) {
						for (int e = 0; e < numExecLines; e++) {
							System.out.print("   ");
						}
					}
					try{
						for (int e = 0; e < numTuristLines; e++) {
							System.out.printf("%3d",aviao.getTuristica()[e][i]);
						}
					} catch (Exception ex) {
						for (int e = 0; e < numTuristLines; e++) {
							System.out.print("   ");
						}
					}
					System.out.print("\n");
				}
				break;
			}
		}
	}

	public static void addFlight(String codigo, String seatsExecutive, String seatsTourist) {
		for (Voo voo : voos) {
			if (voo.getCodigo().equals(codigo)) {
				System.out.println("Código de voo já existente!");
				return;
			}
		}
		voos.add(new Voo(codigo, seatsExecutive, seatsTourist));
	}

	public static void addBooking(String code, String classe, int num) {
		Reserva reserva = null;
		for (Voo voo : voos) {
			if (voo.getCodigo().equals(code)) {
				reserva = voo.addBooking(classe, num, code, voo.getAviao());
				break;
			}
		}
		System.out.print(reserva.getBookingCode() + " = ");
		int i = 0;
		for (i = 0; i < reserva.getLugares().length - 1; i++) {
			System.out.print(reserva.getLugares()[i] + " | ");
		}
		System.out.println(reserva.getLugares()[i]);
	}

	public static boolean checkBookingCode(String flightCode, String bookingCode) {
		for (Voo voo : voos) {
			if (voo.getCodigo().equals(flightCode)) {
				for (Reserva reserva : voo.getReservas()) {
					if (reserva.getBookingCode().equals(bookingCode)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static void cancelBooking(String flightCode, String bookingCode){
		for (Voo voo : voos) {
			if (voo.getCodigo().equals(flightCode)) {
				voo.removeBooking(bookingCode);
			}
		}
	}
}
