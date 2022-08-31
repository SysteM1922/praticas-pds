import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class WordSoup {
	private int rows;
	private int coluns;
	private char[][] puzzle;

	public WordSoup(char[][] puzzle) {
		this.puzzle = puzzle;
		this.coluns = puzzle[0].length;
		this.rows = puzzle.length;
	}

	public LinkedHashMap<String, LinkedList<int[]>> solve(ArrayList<String> sol) { // função para procurar todas as soluções na sopa para uma dada palavra
		LinkedHashMap<String, LinkedList<int[]>> sols = new LinkedHashMap<>();
		for (String s : sol)
			sols.put(s, patternSearch(puzzle, s));
		return sols;
	}
	
	private LinkedList<int[]> patternSearch(char[][] grid, String word) { // função para procurar a palavra na sopa
		LinkedList<int[]> list = new LinkedList<>();
		for (int row = 0; row < this.rows; row++) {
			for (int col = 0; col < this.coluns; col++) {
				if (grid[row][col] == word.toUpperCase().charAt(0)) {
					int d = search2D(grid, row, col, word.toUpperCase());
					if (d != -1) {
						int[] r = { row, col, d };
						list.add(r);
					}
				}
			}
		}
		return list;
	}

	private int search2D(char[][] grid, int row, int col, String word) { // função para procura as coordenadas da palavra na sopa
		int[] x = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int[] y = { -1, 0, 1, -1, 1, -1, 0, 1 };
		if (grid[row][col] != word.charAt(0))
			return -1;
		int len = word.length();
		for (int dir = 0; dir < 8; dir++) {
			int k, rd = row + x[dir], cd = col + y[dir];
			for (k = 1; k < len; k++) {
				if (rd >= this.rows || rd < 0 || cd >= this.coluns || cd < 0)
					break;
				if (grid[rd][cd] != word.charAt(k))
					break;
				rd += x[dir];
				cd += y[dir];
			}
			if (k == len)
				return dir;
		}
		return -1;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColuns() {
		return coluns;
	}

	public void setColuns(int coluns) {
		this.coluns = coluns;
	}

	public char[][] getPuzzle() {
		return puzzle;
	}

	public void setPuzzle(char[][] puzzle) {
		this.puzzle = puzzle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + coluns;
		result = prime * result + Arrays.deepHashCode(puzzle);
		result = prime * result + rows;
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
		WordSoup other = (WordSoup) obj;
		if (coluns != other.coluns)
			return false;
		if (!Arrays.deepEquals(puzzle, other.puzzle))
			return false;
		if (rows != other.rows)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WordSoup [coluns=" + coluns + ", puzzle=" + Arrays.toString(puzzle) + ", rows=" + rows + "]";
	}

	public String printPuzzle() { // função para imprimir toda a sopa
		String s = "";
		for (char[] c : puzzle) {
			for (char ch : c)
				s += (ch);
			s += ("\n");
		}
		return s;
	}

}
