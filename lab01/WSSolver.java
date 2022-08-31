import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class WSSolver {
	public static void main(String[] args) throws IOException {
		File file = new File(args[0]);
		Scanner sc = new Scanner(file);
		ArrayList<String> sol = new ArrayList<>(); // array para guardar as palavras que se pretende procurar
		int row = 0; // conatdor de linhas
		String nextLine = sc.nextLine(); // linha atual inicializada na 1a
		int lineSize = nextLine.length(); // tamanho da linha atual, que corresponde ao número de colunas naquela linha e de linhas esperadas, incializada no tamanho da 1a
		char[][] puzzle = new char[lineSize][lineSize]; // matriz da sopa de letras

		while (sc.hasNextLine()) { // percorrer a sopa
			if (row != 0) // evita se for a primeira linha
				nextLine = sc.nextLine();
			if (nextLine.length() == 0) { // verificar se a linha é vazia
				System.out.println("O ficheiro não deve ter linhas vazias");
				System.exit(1);
			}
			if (!checkIsUpperCase(nextLine)) { // função que verifica se toda a linha está em letras e maiúsculas, caso não esteja significa o fim da sopa de letras
				if (row != lineSize) { // se nr de linhas != nr colunas sopa não é quadrada
					System.out.println("Tamanho do puzzle inválido!");
					System.exit(1);
				} else {
					String[] p = nextLine.split(",|;|\\s"); // split da linha pelos caracteres , ; e \\s
					for (String s : p) {
						if (!checkIsAlpha(s)) { // se a palavra não for exclusivamente composta por letras
							System.out.println("As palavras devem apenas conter apenas letras!");
							System.exit(1);
						}
						sol.add(s); // adicionar palavra à lista de palavras a procurar na sopa
					}
				}
			} else if (lineSize != nextLine.length() || nextLine.length() > 40) { // se o número de colunas for diferente do inicial ou exceder o número limite de 40
				System.out.println("Tamanho de colunas inválido!");
				System.exit(1);
			} else if (row + 1 > lineSize || row + 1 > 40) { // se o número de linhas for diferente do inicial ou exceder o limite de 40
				System.out.println("Tamanho de linhas inválido!");
				System.exit(1);
			} else { // sucesso
				for (int col = 0; col < nextLine.length(); col++)
					puzzle[row][col] = nextLine.charAt(col); // adicionar cada letra da linha da sopa à matriz
				row++; // atualizar o número de linhas para o atual
			}
		}
		sc.close();
		
		// Procurar soluções na sopa

		WordSoup WS = new WordSoup(puzzle); // classse WordSoup
		LinkedHashMap<String, LinkedList<int[]>> sols = WS.solve(sol); // Map com a palavra que foi procurada como chave e lista de valores das coordenadas onde foi encontrado o incio da palavra em chave para impressão

		for (char[] c : puzzle)
			Arrays.fill(c, '.'); // preparar matriz para print enchendo com '.'

		// Validar soluções encontradas

		int[] x = { -1, -1, -1, 0, 0, 1, 1, 1 }; // vetor de coordenadas realtivas à posição a ser avaliada no momento no eixo x
		int[] y = { -1, 0, 1, -1, 1, -1, 0, 1 }; // vetor de coordenadas realtivas à posição a ser avaliada no momento no eixo y

		for (String s1 : sols.keySet()) { // percorrer a lista de palavras que foram procuradas
			for (String s2 : sols.keySet()) {
				if (s1.length() < s2.length() && isSubstring(s1, s2) > -1) { // se uma palavra estiver contida noutra (só pode ser se for mais pequena que a em que pode estar contida)
					for (int a = 0; a < sols.get(s2).size(); a++) { // ver solução encontrada na palavra maior uma a uma
						HashSet<Double> el2 = new HashSet<>(); // set que armazena todas as coordenadas por onde essa palavra passa
						int l2 = sols.get(s2).get(a)[0]; // coordenada linha da primeira letra
						int c2 = sols.get(s2).get(a)[1]; // coordenada coluna da primeira letra
						for (int i = 0; i < s2.length(); i++) {
							el2.add((double) l2 + (double) c2 / (Math.pow(10, String.valueOf(c2).length()))); // converter coordenadas de lista int[] para um valor double e adiconar ao set coorespondente
							l2 += x[sols.get(s2).get(a)[2]]; // avançar para a coordenada seguinte através dos vetores de coordenadas relativas
							c2 += y[sols.get(s2).get(a)[2]]; // avançar para a coordenada seguinte através dos vetores de coordenadas relativas
						}
						for (int e = 0; e < sols.get(s1).size(); e++) { // ver solução encontrada na palavra menor uma a uma
							HashSet<Double> el1 = new HashSet<>(); // set que armazena todas as coordenadas por onde essa palavra passa
							int l1 = sols.get(s1).get(e)[0]; // coordenada linha da primeira letra
							int c1 = sols.get(s1).get(e)[1]; // coordenada coluna da primeira letra
							for (int i = 0; i < s1.length(); i++) {
								el1.add((double) l1 + (double) c1 / (Math.pow(10, String.valueOf(c1).length()))); // converter coordenadas de lista int[] para um valor double e adiconar ao set coorespondente
								l1 += x[sols.get(s1).get(e)[2]]; // avançar para a coordenada seguinte através dos vetores de coordenadas relativas
								c1 += y[sols.get(s1).get(e)[2]]; // avançar para a coordenada seguinte através dos vetores de coordenadas relativas
							}
							if (el2.containsAll(el1)) // se set de coordenadas da palavra maior contiverem todas as do set da palavra menor
								sols.get(s1).remove(e); // remover solução da lista a imprimir
						}
					}
				}
			}
			if (sols.get(s1).size() > 1) { // caso haja mais que uma solução para a mesma palavra a sopa é inválida
				System.out.println("Número de soluções inválido!");
				System.exit(1);
			}
			String dir = "";
			switch (sols.get(s1).get(0)[2]) { // coverter direção da palavra para texto
				case 0:
					dir = "UpLeft";
					break;
				case 1:
					dir = "Up";
					break;
				case 2:
					dir = "UpRight";
					break;
				case 3:
					dir = "Left";
					break;
				case 4:
					dir = "Right";
					break;
				case 5:
					dir = "DownLeft";
					break;
				case 6:
					dir = "Down";
					break;
				case 7:
					dir = "DownRight";
					break;
			}
			System.out.printf("%-15s %-2d\t%d,%-2d\t%-10s\n", s1, s1.length(), sols.get(s1).get(0)[0] + 1,
					sols.get(s1).get(0)[1] + 1, dir); // imprimir palavra nos moldes pedidos
			int c = sols.get(s1).get(0)[1]; // coordenada coluna da primeira letra
			int l = sols.get(s1).get(0)[0]; // coordenada linha da primeira letra
			for (int i = 0; i < s1.length(); i++) {
				puzzle[l][c] = s1.toUpperCase().charAt(i); // colocar letra na matriz para impressão
				l += x[sols.get(s1).get(0)[2]]; // avançar para a coordenada seguinte através dos vetores de coordenadas relativas
				c += y[sols.get(s1).get(0)[2]]; // avançar para a coordenada seguinte através dos vetores de coordenadas relativas
			}
		}
		System.out.print("\n");
		for (char[] cA : puzzle) {
			for (char c : cA)
				System.out.print(c + " "); // imprimir letra a letra as letras da sopa de letras na posição correta
			System.out.print("\n");
		}
	}

	public static boolean checkIsUpperCase(String s) { // função que verifica se toda a string está em maiúsculas
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isUpperCase(s.charAt(i)))
				return false;
		}
		return true;
	}

	public static boolean checkIsAlpha(String s) { // função que verifica se toda a string é constituída por caracteres alfabéticos
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isAlphabetic(s.charAt(i)))
				return false;
		}
		return true;
	}

	public static int isSubstring(String s1, String s2) { // função que verifica se uma string é substring de outra
		int M = s1.length();
		int N = s2.length();
		for (int i = 0; i <= N - M; i++) {
			int j;
			for (j = 0; j < M; j++) {
				if (s2.charAt(i + j) != s1.charAt(j))
					break;
			}
			if (j == M)
				return i;
		}
		return -1;
	}
}
