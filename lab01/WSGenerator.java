import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class WSGenerator {
	public static void main(String[] args) throws IOException {
		String f = ""; // variável de armazenamento do nome do ficheiro a ler as palavras pretendidas
		int lineSize = 0; // variável de armazenamento do número de linhas e colunas da sopa
		String out = ""; // variável de armazenamento do nome do ficheiro para oinde a sopa deve ser gerada
		if (args.length != 4 && args.length != 6) { // se o número de argumentos não corresponder aos esperados
			System.out.println(
					"O programa deve ter como parâmetros de entrada:\nWSGenerator -i <lista de palavras> -s <número de linhas da sopa>\nou\nWSGenerator -i <lista de palavras> -s <número de linhas da sopa> -o <ficheiro de impressão>");
			System.exit(1);
		}
		for (int i = 0; i < args.length; i++) { // getops em Java
			if (args[i].equals("-i"))
				f = args[++i];
			else if (args[i].equals("-s"))
				lineSize = Integer.parseInt(args[++i]);
			else if (args[i].equals("-o"))
				out = args[++i];
			else
				System.out.println(
						"O programa deve ter como parâmetros de entrada:\nWSGenerator -i <lista de palavras> -s <número de linhas da sopa>\nou\nWSGenerator -i <lista de palavras> -s <número de linhas da sopa> -o <ficheiro de impressão>");
		}

		File file = new File(f); // abrir ficheiro inserido
		Scanner sc = new Scanner(file);
		String nextLine = "";
		ArrayList<String> sol = new ArrayList<>(); // array para guardar as palavras que se pretende procurar

		while (sc.hasNextLine()) { // percorrer o ficheiro
			nextLine = sc.nextLine();
			if (nextLine.length() == 0) { // verificar se a linha é vazia
				System.out.println("O ficheiro não deve ter linhas vazias");
				System.exit(1);
			}
			String[] p = nextLine.split(",|;|\\s"); // split da linha pelos caracteres , ; e \\s
			for (String s : p) {
				if (!checkIsAlpha(s)) { // se a palavra não for exclusivamente composta por letras
					System.out.println("As palavras devem apenas conter apenas letras!");
					System.exit(1);
				}
				sol.add(s); // adicionar palavra à lista de palavras a procurar na sopa
			}
		}
		sc.close();

		// Colocar palavras pretendidas na sopa vazia

		char[][] puzzle = new char[lineSize][lineSize]; // matriz da sopa de letras

		for (char[] c : puzzle)
			Arrays.fill(c, '.'); // incializar a soupa de letras com "."

		int[] x = { -1, -1, -1, 0, 0, 1, 1, 1 }; // vetor de coordenadas realtivas à posição a ser avaliada no momento no eixo x
		int[] y = { -1, 0, 1, -1, 1, -1, 0, 1 }; // vetor de coordenadas realtivas à posição a ser avaliada no momento no eixo y

		Random r = new Random(); // variável aleatória
		LinkedList<Double> coordsSols = new LinkedList<>(); // lista para guardar as coordenadas onde foram alocadas letras das soluções

		for (String s : sol) { // percorrer a lista de palavras pretendidas
			int d = -1; // inicializar a direção na qual procurar
			ArrayList<Integer> dir = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7)); // lista de direções possíveis
			for (int i = 0; i < 8; i++) {
				int indx = r.nextInt(dir.size()); // gerar um indice aleatório no range do tamanho da lista de direções
				d = dir.get(indx); // escolher a direção pelo indice aleatório
				dir.remove(indx); // remover direção escolhida da lista
				LinkedList<int[]> cP = searchSpaces(puzzle, s, d); // lista dos conjuntos de pontos em int[] onde uma palavra s na direção d pode ser alocada na sopa
				if (cP.size() != 0) { // se for possível alocar na direção escolhida
					int[] coords = cP.get(r.nextInt(cP.size())); // escolher um conjunto de coordenadas aleatoriamente para alocar a palavra
					int l = coords[0]; // coordenada linha da primeira letra
					int c = coords[1]; // coordenada coluna da primeira letra
					for (int e = 0; e < s.length(); e++) {
						coordsSols.add((double) l + (double) c / (Math.pow(10, String.valueOf(c).length()))); // conveter coordenadas de int[] para double e adicioná-la à lista de coordenadas com letras alocadas
						puzzle[l][c] = s.toUpperCase().charAt(e); // adicionar à soupa a letra em maiúscula
						l += x[d]; // avançar para a coordenada seguinte através dos vetores de coordenadas relativas
						c += y[d]; // avançar para a coordenada seguinte através dos vetores de coordenadas relativas
					}
					break;
				}
			}
		}

		for (char[] c : puzzle) {
			for (int i = 0; i < c.length; i++) {
				if (c[i] == '.')
					c[i] = (char) (r.nextInt(26) + 'A'); // preencher os espaços sem letras com letras geradas aleatoriamente
			}
		}

		WordSoup WS = new WordSoup(puzzle); // classe WordSoup
		LinkedHashMap<String, LinkedList<int[]>> sols = WS.solve(sol); // Map com a palavra que foi procurada como chave e lista de valores das coordenadas onde foi encontrado o incio da palavra em chave para impressão

		// Verificar se não foi gerada aleatoriamente nenhuma palavra das que estão nas pretendidas

		for (String s1 : sols.keySet()) { // percorrer a lista de palavras que foram procuradas
			for (int e = 0; e < sols.get(s1).size(); e++) { // procurar na lista de coordenadas iniciais onde foi encontrada a palavra em análise
				int l = sols.get(s1).get(e)[0]; // coordenada linha da primeira letra
				int c = sols.get(s1).get(e)[1]; // coordenada coluna da primeira letra
				double newCoord = (double) l + (double) c / (Math.pow(10, String.valueOf(c).length())); // converter para double a coordenada em int[] a avaliar
				if (!coordsSols.contains(newCoord)) // se não existir nenhuma solução esperada que comece na coordenada a ser avaliada
					puzzle[l][c] = puzzle[l + x[sols.get(s1).get(e)[2]]][c + y[sols.get(s1).get(e)[2]]]; // substituir a primeira letra pela mesma que a segunda da palavra destruíndo a existência de uma palavra das que são procuradas nessa linha de coordenadas (sempre no caso da língua portuguesa, quase sempre na inglesa)
			}
		}

		WS.setPuzzle(puzzle); // atualizar a sopa para a corrigida
		System.out.print(WS.printPuzzle()); // imprimir a sopa corrigida

		Scanner sc2 = new Scanner(new File(f)); // inicializar iterador de escrita em ficheiros
		if (out != "") { // se tiver sido fornecido um ficheiro de output
			PrintWriter pw = new PrintWriter(new File(out)); // colocar iterador no ficheiro de output dado
			for (char[] c : puzzle) {
				for (char ch : c)
					pw.print(ch); // escrever caracter a caracter da sopa
				pw.print("\n");
			}
			while (sc2.hasNextLine()) {
				nextLine = sc2.nextLine();
				System.out.println(nextLine); // imprimir palavras a procurar na sopa
				pw.println(nextLine); // escrever palavras a procurar na sopa
			}
			pw.close();
		} else {
			while (sc2.hasNextLine())
				System.out.println(sc2.nextLine()); // imprimir palavras a procurar na sopa
		}
		sc2.close();
	}

	public static boolean checkIsAlpha(String s) { // função que verifica se toda a string é constituída por caracteres alfabéticos
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isAlphabetic(s.charAt(i)))
				return false;
		}
		return true;
	}

	public static LinkedList<int[]> searchSpaces(char[][] puzzle, String s, int d) { // procurar espaços que podem alocar a palavra na sopa
		LinkedList<int[]> dList = new LinkedList<>();
		for (int e = 0; e < puzzle.length; e++) {
			for (int i = 0; i < puzzle[e].length; i++) {
				if (puzzle[e][i] == '.' || puzzle[e][i] == s.toUpperCase().charAt(0)) {
					if (checkDir(puzzle, s, d, e, i))
						dList.add(new int[] { e, i });
				}
			}
		}
		return dList;
	}

	public static boolean checkDir(char[][] puzzle, String s, int d, int l, int c) { // função que verifica se direção e tamanho na matriz estão disponíveis para alocar
		int[] x = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int[] y = { -1, 0, 1, -1, 1, -1, 0, 1 };
		int lineSize = puzzle.length;
		HashSet<Character> ch = new HashSet<>();
		for (int i = 0; i < s.length(); i++) {
			try {
				ch.add(puzzle[l][c]);
			} catch (ArrayIndexOutOfBoundsException e) {
				return false;
			}
			if (puzzle[l][c] != s.toUpperCase().charAt(i) && puzzle[l][c] != '.')
				return false;
			l += x[d];
			c += y[d];
		}
		if (0 < l && l < lineSize && 0 < c && c < lineSize && ch.contains('.'))
			return true;
		return false;
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
