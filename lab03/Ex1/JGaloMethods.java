package Ex1;

public class JGaloMethods implements JGaloInterface {

	private String firstPlayer = " ";
	private String secondPlayer = "O";
	private String actualPlayer = " ";
	private String result = " ";
	private String[] table = new String[9];
	private boolean finished = false;
	private static int play = 0;
	
	public JGaloMethods(String[] args) {
		if (args.length > 0 && args[0].equals("O")) {
			firstPlayer = "O";
			secondPlayer = "X";
		} else
			firstPlayer = "X";
	}
	
	public char getActualPlayer() {
		if (play % 2 == 0)
			actualPlayer = firstPlayer;
		else
			actualPlayer = secondPlayer;
		return actualPlayer.charAt(0);
	}
	
	public boolean setJogada(int lin, int col) {
		play++;
		if (play == 9)
			finished = true;
		if (table[(lin-1) * 3 + col-1] == null) {
			table[(lin-1) * 3 + col-1] = actualPlayer;
			if (play > 4) {
				if ((table[0] == actualPlayer && table[0] == table[1] && table[1] == table[2])  ||
				(table[3] == actualPlayer && table[3] == table[4] && table[4] == table[5]) ||
				(table[6] == actualPlayer && table[6] == table[7] && table[7] == table[8]) ||
				(table[0] == actualPlayer && table[0] == table[3] && table[3] == table[6]) ||
				(table[1] == actualPlayer && table[1] == table[4] && table[4] == table[7]) ||
				(table[2] == actualPlayer && table[2] == table[5] && table[5] == table[8]) ||
				(table[0] == actualPlayer && table[0] == table[4] && table[4] == table[8]) ||
				(table[2] == actualPlayer && table[2] == table[4] && table[4] == table[6]))
					result = actualPlayer;
				else
					return true;
				finished = true;
			}
			return true;
		}
		else
			return false;
	}

	public boolean isFinished() {
		return finished;
	}
	
	public char checkResult() {
		return result.charAt(0);
	}
}