

public class JogoGalo implements JGaloInterface {
    private char m[][] = new char[3][3]; // Matriz que guarda as jogadas
    private char jogador = 'X'; // indica qual o jogador atual
    private char winner = 'z'; // indica temporariamente o vencedor
    private int count = 0; //nº de jogadas
    public JogoGalo()
    {

    }
    @Override
    // retorna o jogador atual
    public char getActualPlayer()
    {
        return jogador;
    }

    @Override
    // substitui o jogador atual
    public boolean setJogada(int lin,int col)
    {
        this.count++;
        m[lin-1][col-1] = jogador;
        if(this.jogador == 'X')
        {
            this.jogador = 'O';
            return false;
        }
        else{
            this.jogador = 'X';
            return false;
        }
    }

    @Override
    public boolean isFinished()
    {
        
        // 1ª linha preenchida com (X/O)
		if(m[0][0] == m[0][1] && m[0][1] == m[0][2] && m[0][0]!=0) {
			winner=m[0][0];
			return true;
		}

        // 2ª linha preenchida com (X/O)
		if(m[1][0] == m[1][1] && m[1][1] == m[1][2] && m[1][0]!=0) {
			winner=m[1][0];
			return true;
		}

        //3ª linha preenchida com (X/O)
		if(m[2][0] == m[2][1] && m[2][1] == m[2][2] && m[2][0]!=0) {
			winner=m[2][0];
			return true;
		}
		
		
        // 1 diagonal está preenchida com (X/O)
		if(m[0][0] == m[1][1] && m[1][1] == m[2][2] && m[0][0]!=0) {
			winner=m[0][0];
			return true;
		}

		// verifica que a outra diagonal está preenchida com (X/O)
		if(m[0][2] == m[1][1] && m[1][1] == m[2][0] && m[0][2]!=0) {
			winner=m[0][2];
			return true;
		}
		
		// 1ª linha preenchida com (X/O)
		if(m[0][0] == m[1][0] && m[1][0] == m[2][0] && m[0][0]!=0) {
			winner=m[0][0];
			return true;
		}
        // 2ª linha preenchida com (X/O)
		if(m[0][1] == m[1][1] && m[1][1] == m[2][1] && m[0][1]!=0) {
			winner=m[0][1];
			return true;
		}

		// 3ª linha preenchida com (X/O)
		if(m[0][2] == m[1][2] && m[1][2] == m[2][2] && m[0][2]!=0) {
			winner=m[0][2];
			return true;
		}
		
		
        // Se já forem realizadas 9 jogadas e não passar nenhuma das outras verificações é empate.
		if(count==9) {
			winner=' ';
			return true;
		}else {
			winner='z';
			return false;
		}
    }

    @Override
	
	public char checkResult() {
		return winner;
	}



}
