package Voos;
import java.util.*;
import java.io.*;

public class GestorVoos {
	public static Voo readFile(String[] cmd) {
		Voo voo;
		try(Scanner file = new Scanner(new File(cmd[1]))) {
			String[] linha = file.nextLine().split(" ");
			if (!linha[0].startsWith(">")) {
				System.out.println("Ficheiro inválido");
				return null;
			}
			String codigo = linha[0].substring(1);
			System.out.printf("Código de voo %s. Lugares: ", codigo);
			String[] dimension;
			int turisticos = 0; int executivos = 0;
			if (linha.length == 2) {
				dimension = linha[1].split("x");
				turisticos = Integer.parseInt(dimension[0]) * Integer.parseInt(dimension[1]);
				if (turisticos == 0) {
					System.out.println("Ficheiro inválido (deve ter, pelo menos, 1 lugar de classe turística).");
					return null;
				}
				else {
					System.out.printf("%d lugares em classe Turística.\nClasse executiva não disponível neste voo.\n", turisticos);
					voo = new Voo(codigo, new Aviao(Integer.parseInt(dimension[0]),Integer.parseInt(dimension[1])));
					String[] strResev;
					boolean repeat = true;
					while (repeat && file.hasNextLine()) {
						strResev = file.nextLine().split(" ");
						switch (strResev[0]) {
							case "T":
								voo.R(voo.getCodigo(), strResev[0].charAt(0), Integer.parseInt(strResev[1]));
								break;
							case "":
								break;
							default:
								System.out.println("Reserva com classe inválida.");
								repeat = false;
						}
					}
				}
				
			}
			else if (linha.length == 3) {
				String[] dimension1 = linha[1].split("x");
				executivos = Integer.parseInt(dimension1[0]) * Integer.parseInt(dimension1[1]);
				String[] dimension2 = linha[2].split("x");
				turisticos = Integer.parseInt(dimension2[0]) * Integer.parseInt(dimension2[1]);
				if (turisticos == 0 && executivos == 0) {
					System.out.println("Ficheiro inválido (deve ter, pelo menos, 1 lugar).");
					return null;
				}
				else {
					System.out.printf("%d lugares em classe Executiva; %d lugares em classe Turística.\n", executivos, turisticos);
					String[] strResev;
					voo = new Voo(codigo, new Aviao(Integer.parseInt(dimension2[0]), Integer.parseInt(dimension2[1]), Integer.parseInt(dimension1[0]), Integer.parseInt(dimension1[1])));
					boolean repeat = true;
					while (repeat && file.hasNextLine()) {
						strResev = file.nextLine().split(" ");
						switch (strResev[0]) {
							case "T":
								voo.R(voo.getCodigo(), strResev[0].charAt(0), Integer.parseInt(strResev[1]));
								break;
							case "E":
								voo.R(voo.getCodigo(), strResev[0].charAt(0), Integer.parseInt(strResev[1]));
								break;
							case "":
								break;
							default:
								System.out.println("Reserva com classe inválida.");
								repeat = false;
						}
					}
				}
			}
			else {
				System.out.println("Ficheiro inválido.");
				return null;
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("Ficheiro não encontrado.");
			return null;
		}
		return voo;
	}
	
	public static void main(String args[]) {
		HashMap<String, Voo> listaVoos = new HashMap<String, Voo>();
		Scanner input = null;
		boolean modeFile = false;
		if (args.length == 1) {
			try {
				input = new Scanner(new File(args[0]));
				System.out.println(args[0]);
				modeFile = true;
			} catch (FileNotFoundException e) {
				System.out.println("Ficheiro de comandos não encontrado.");
				System.exit(1);
			}
		} else {
			input = new Scanner(System.in);
		}
		System.out.println("Escolha uma opção: (H para ajuda)");
		String line;
		String[] cmd;
		boolean run = true;
		Voo voo;
		while (run) {
			System.out.println();
			if (!modeFile) System.out.printf("> ");
			line = input.nextLine();
			if (modeFile) System.out.println(line);
			cmd = line.split(" ");
			switch (cmd[0]) {
				case "H":
					System.out.println("LISTA DE COMANDOS:");
					System.out.println("[Ler ficheiro de voo]     I <ficheiro>");
					System.out.println("[Exibir mapa de reservas] M <codigoVoo>");
					System.out.println("[Criar voo]               F <codigoVoo> <numLugaresExecutivos> <numLugaresTuristicos>");
					System.out.println("[Criar reserva]           R <codigoVoo> <classe> <numLugares>");
					System.out.println("[Cancelar reserva]        C <codigoReserva>");
					System.out.println("[Sair]                    Q");

					break;
				case "I":
					if (cmd.length != 2) {
						System.out.println("[Ler ficheiro de voo]     I <ficheiro>");
						break;
					}
					voo = readFile(cmd);
					if (voo != null) listaVoos.put(voo.getCodigo(), voo);
					break;
				case "M":
					if (cmd.length == 2 && !cmd[1].isEmpty()) {
						if (listaVoos.containsKey(cmd[1])) {
							Voo v = listaVoos.get(cmd[1]);
							System.out.print("    ");
							v.M(cmd[1]);
						} else {
							System.out.println("O voo não está registado no sistema.");
							break;
						}
					} else {
						System.out.println("Argumento inválido.");
						break;
					}
					System.out.println();
					break;
				case "F":
					if (listaVoos.containsKey(cmd[1]) || cmd[1].matches("[A-Za-z0-9]+")) {
						if (cmd.length == 4) {
							if(cmd[2].matches("^(?=.*?[0-9a-zA-Z])[0-9]*[x][0-9]*$") && cmd[3].matches("^(?=.*?[0-9a-zA-Z])[0-9]*[x][0-9]*$")){
								String[] E = cmd[2].split("x");
								String[] T = cmd[2].split("x");
								int nT = Integer.parseInt(cmd[2].split("x")[0])*Integer.parseInt(cmd[2].split("x")[1]);
								int nE = Integer.parseInt(cmd[3].split("x")[0])*Integer.parseInt(cmd[3].split("x")[1]);
								Voo v = new Voo(cmd[1], new Aviao(Integer.parseInt(T[0]), Integer.parseInt(T[1]), Integer.parseInt(E[0]), Integer.parseInt(E[1])));
								listaVoos.put(cmd[1], v);
								System.out.println("O voo " + cmd[1] + "foi criado.");
							}else{
								System.out.println("A informação sobre o esquema de lugares no avião é do tipo AxB.");
							}
						} else if (cmd.length == 3)
						{
							if(cmd[2].matches("^(?=.*?[0-9a-zA-Z])[0-9]*[x][0-9]*$")){
								String[] T = cmd[2].split("x");
								Voo v = new Voo(cmd[1], new Aviao(Integer.parseInt(T[0]), Integer.parseInt(T[1])));
								listaVoos.put(cmd[1], v);
								System.out.println("O voo " + cmd[1] + " foi criado.");
							}else{
								System.out.println("A informação sobre o esquema de lugares no avião é do tipo AxB.");
							}
						} else {
							System.out.println("Número de argumentos inválido.");
							break;
						}
					} else {
						System.out.println("Código de voo inválido.");
						break;
					}
					break;
				case "R":
					if (cmd.length != 4){
						System.out.println("Número de argumentos inválido.");
						break;
					}
					if (listaVoos.containsKey(cmd[1]))
					{
						if (!cmd[2].matches("T|E")) {
							System.out.println("As únicas classes válidas são: (T)uristíca e (E)xecutiva.");
							break;
						}
						if(!cmd[3].matches("[0-9]+"))
						{
							System.out.println("O número de lugares a reservar tem de ser um símbolo numérico.");
							break;
						}
						Voo v = listaVoos.get(cmd[1]);
						if (cmd[2].charAt(0) == 'E'){
							if (v.getnExecOcupados() + Integer.parseInt(cmd[3]) > v.getAviao().getnE()){
								System.out.println("Não há lugares disponivéis para o número de passageiros especificados.");
								break;
							}
						} else {
							if (v.getnTouristOcupados() + Integer.parseInt(cmd[3]) > v.getAviao().getnT() ){
								System.out.println("Não há lugares disponivéis para o número de passageiros especificados.");
								break;
							}
						}
						v.R(cmd[1], cmd[2].charAt(0), Integer.parseInt(cmd[3]));
					} else
					{
						System.out.println("O voo não está registado no sistema.");
						break;
					}
					break;
				case "C":
					// flight_code:sequential_reservation_number.
					if (cmd.length != 2){
						System.out.println("Número de argumentos inválido.");
						break;
					}
					if (cmd[1].matches("^(?=.*?[0-9a-zA-Z])[0-9]*[:][0-9]*$")){
						String str[] = cmd[1].split(":");
						String codigo = str[0]; 
						// int num = Integer.parseInt(str[1]);
						/*if (num > reserva){
							System.out.println("Não existem reservas com esse número.");
						}*/
						if(listaVoos.containsKey(codigo)){
							listaVoos.get(codigo).C(cmd[1]); // chamar a função 
						} else {
							System.out.println("O código de voo não está registado no sistema.");
							break;
						}
					} else {
						System.out.println("O número de reserva não é válido.");
						break;
					}
					break;
				case "Q":
					System.out.println("A sair do programa...");
					run = false;
					break;
				default:
					System.out.println("Comando não encontrado. Use H para ajuda.");
					break;
			}
		}
		input.close();
	}
}
