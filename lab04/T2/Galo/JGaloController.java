package Galo;

public class JGaloController implements JGaloInterface {
	private char[][] jogo;
	private int jogadas;
	private char atual;
	private char p1;
	private char p2;
	private boolean finished;
	public JGaloController(char p1, char p2) {
		this.jogo = new char[3][3];
		for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) jogo[i][j] = '.';
		this.jogadas = 0;
		this.p1 = p1;
		this.atual = p1;
		this.p2 = p2;
		this.finished = false; 
	}

	public char getActualPlayer() {return this.atual;}
	
	public boolean setJogada(int lin, int col) {
		if (this.finished == true) return false;
		if (lin < 1 || lin > 3 || col < 1 || col > 3) return false;
		int atual = getActualPlayer();
		if (jogo[lin-1][col-1] != '.') return false;
		jogo[lin-1][col-1] = getActualPlayer();
		this.jogadas++;
		if (atual == this.p1) this.atual = this.p2;
		if (atual == this.p2) this.atual = this.p1;
		return true;
	}
	
	public boolean isFinished() {
		if (this.jogadas == 9) {
			this.finished = true;
			return true;
		}
		int atual;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				atual = jogo[i][j];
				if (atual == '.') continue;
				if (i > 0 && i < 2 && jogo[i-1][j] == atual && jogo[i+1][j] == atual) {this.finished = true; return true;}
				if (j > 0 && j < 2 && jogo[i][j-1] == atual && jogo[i][j+1] == atual) {this.finished = true; return true;}
				if (i > 0 && i < 2 && j > 0 && j < 2 && jogo[i-1][j-1] == atual && jogo[i+1][j+1] == atual) {this.finished = true; return true;}
				if (i > 0 && i < 2 && j > 0 && j < 2 && jogo[i-1][j+1] == atual && jogo[i+1][j-1] == atual) {this.finished = true; return true;}
			}
		return false;
	}
	
	public char checkResult() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				atual = jogo[i][j];
				if (atual == '.') continue;
				if (i > 0 && i < 2 && jogo[i-1][j] == atual && jogo[i+1][j] == atual) return atual;
				if (j > 0 && j < 2 && jogo[i][j-1] == atual && jogo[i][j+1] == atual) return atual;
				if (i > 0 && i < 2 && j > 0 && j < 2 && jogo[i-1][j-1] == atual && jogo[i+1][j+1] == atual) return atual;
				if (i > 0 && i < 2 && j > 0 && j < 2 && jogo[i-1][j+1] == atual && jogo[i+1][j-1] == atual) return atual;
			}
		return ' ';
	}
}
