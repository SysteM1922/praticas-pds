package lab01;
import java.io.*;
import java.util.*;

import javax.swing.plaf.synth.SynthEditorPaneUI;

import java.lang.*;

public class ex1{
    private static ArrayList<WordInfo> results = new ArrayList<WordInfo>();
    private static int linhas = 0;
	private static int colunas = 0; 

     
    static FileWriter arq;
    static PrintWriter gravarArq;
    static {
        try {
            arq = new FileWriter("out.txt");
            gravarArq = new PrintWriter(arq);
        } catch (final IOException e) {
            throw new ExceptionInInitializerError(e.getMessage());
        }
    }
    
    
    
    enum move {
		initial, left, right, up, down, upleft, downleft, downright, upright
	}
    public static void main(String[] args) throws FileNotFoundException {
        // Devolve o ficheiro
        File txt=get_txt(args);
        // Devolve o array de strings da sopa de letras e das palavras a procurar
        ArrayList<String>[]  grid=loadSopa(txt);

        ArrayList<String>  words=grid[1];
        ArrayList<String>  sopa=grid[0];
        char[][] sopaChar=new char[sopa.size()][sopa.get(0).length()];
        // Converte a sopa de letras para uma matriz de caracteres
        for (int i=0;i<sopa.size();i++){
            for (int j=0;j<sopa.get(0).length();j++){
                sopaChar[j][i]=sopa.get(j).charAt(i);
            }
        }

        linhas=sopa.size();
        colunas=sopa.get(0).length();
        int maior_palavra=0;
        // Procura as palavrasna matriz
        for (String search : words) {
            find_word(sopaChar, "", 0, 0, move.initial, search.toUpperCase(), -1, -1);
            if (search.length()>maior_palavra){
                maior_palavra=search.length();
            }
        }

        // Imprime as palavras encontradas e a sua posição
        for (WordInfo result : results) {
                String info = String.format("%-" + maior_palavra + "s\t%-2s\t%-1s,%s\t%-8s\n", result.word, result.word.length(), result.rowFrom,result.colFrom, result.direction);
                gravarArq.printf(info);
                System.out.print(info);
            
        }
        print_sopa();
        gravarArq.close();

    }
    private static File get_txt(String[] args){
        File file=null;
        String filename="";
        boolean error=true;

        boolean input=false;
        while (error){
            //Se nao foi enviado o nome do ficheiro como argumento
            if( args.length < 1 ) {
                System.err.println("must input a filename");
                System.err.println("<filename>.txt");  
                Scanner keyboard = new Scanner(System.in);
                filename = keyboard.nextLine();
                input=true;   
                keyboard.close();                  
            }
            
            if (input){
                try{
                    file = new File(filename);
                    error=false;
                }
                catch(Exception e){
                    System.err.println("File not found");
                    error=true;
                }

            }
            //Caso tenha sido enviado o nome do ficheiro como argumento
            else{
                try{
                    file = new File(args[0]);
                    error=false;
                }
                catch(Exception e){
                    System.err.println("File not found");
                    error=true;
                }
                file = new File(args[0]);
            }
        }
        
        return file;

    }
    private static ArrayList<String>[]  loadSopa(File filename) {

        ArrayList<String> soupa = new ArrayList<String>();
        ArrayList<String> words = new ArrayList<String>();
        try {
            
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String line1 = in.readLine();
            String line="";
            for (char c : line1.toCharArray()) {

                if (c == ' ') {
                    break;
                }
                
                line += c;
            }
            int size = line.length();
            int i=1;
            boolean firstLine = true;
            while (line1 != null) {
                if (line.isEmpty()){
                    System.err.println("Error: Empty line no file");
                    System.exit(1);
                    
                }
                //Verificar o tamamho da sopa através da primeira linha
                if (firstLine) {
                    
                    if (size>40) {
                        System.err.println("Error: Sopa não pode ser maior que 40x40");
                        System.exit(1);
                    }
                    firstLine = false;
                    //Verificar se contem letras minusculas
                    for (int j=0; j<size; j++) {
                        
                        if (Character.isLowerCase(line.charAt(j))) {
                            System.err.println("Error: Sopa nao pode conter letras minusculas");
                            System.exit(1);
                        }
                    }
                    soupa.add(line);
                } else {
                    //Se o número da linha for igual a tamanho das linhas, então as linhas da sopa acabaram e começamos a a guardar as palavras
                    if (i>size) {
                        String[] palavras= line.split("\\W+");
                        for (String word : palavras) {
                            for (int j=0; j<word.length(); j++) {
                                if (Character.isDigit(word.charAt(j))) {
                                    System.err.println("Error: Palavras nao pode conter numeros");
                                    System.exit(1);
                                }
                            }
                            words.add(word);
                        }
                    }
                    else{
                        //Se o número da linha for menor que o tamanho das linhas, então estamos a guardar as linhas da sopa
                        //Verificar se contem letras minusculas
                        for (int j=0; j<size; j++) {
                            if (Character.isLowerCase(line.charAt(j))) {
                                System.err.println("Error: Sopa nao pode conter letras minusculas");
                                System.exit(1);
                            }
                        }
                        soupa.add(line);
                    }
                }
                line1 = in.readLine();
                if (line1 != null) {
                    line = "";
                    for (char c : line1.toCharArray()) {
                        if (c == ' ') {
			    i++;
                            break;
                        }
                        line += c;
                    }
                    
                }

                i++;
            }
            in.close();
        } catch( IOException e ) {
            System.err.println("A file error occurred: " + filename );
            System.exit(1);
        }
        
        ArrayList<String>[] arr = new ArrayList[2];
        arr[0] = soupa;
        arr[1] = words;
        return arr;
    }
    private static void find_word(char[][] matriz, String word, int x, int y, move move, String search, int initial_x, int initial_y) {
		String dum; //palavra temporaria para a recursividade

		int wordsize = word.length(); //tamanho da palavra a ser construida

		if (move == move.initial) { //quando nao foi esoclhido move

			//percorrer a matriz
			for (int yy = y; yy < linhas; yy++) {

				for (int xx = x; xx < colunas; xx++) {

					if (matriz[yy][xx] == search.charAt(wordsize)) { //letra da posicao atual na matriz igual à letra da palavra a procurar
						dum = word + String.valueOf(matriz[yy][xx]); 
						int dumsize = dum.length();
                        //verificar se a palavra a procurar pode estar na sopa de letras na direção de movimento 
						if (xx - (search.length() - dumsize) >= 0) { 
							find_word(matriz, dum, xx - 1, yy, move.left, search, xx + 1, yy + 1);
						}
						if (xx + (search.length() - dumsize) <= colunas) {
							find_word(matriz, dum, xx + 1, yy, move.right, search, xx + 1, yy + 1);
						}
						if (yy - (search.length() - dumsize) >= 0) {
							find_word(matriz, dum, xx, yy - 1, move.up, search, xx + 1, yy + 1);
						}
						if (yy + (search.length() - dumsize) <= linhas) {
							find_word(matriz, dum, xx, yy + 1, move.down, search, xx + 1, yy + 1);
						}
						if (xx - (search.length() - dumsize) >= 0 && yy - (search.length() - dumsize) >= 0) {
							find_word(matriz, dum, xx - 1, yy - 1, move.upleft, search, xx + 1, yy + 1);
						}
						if (xx - (search.length() - dumsize) >= 0 && yy + (search.length() - dumsize) <= linhas) {
							find_word(matriz, dum, xx - 1, yy + 1, move.downleft, search, xx + 1, yy + 1);
						}
						if (yy + (search.length() - dumsize) <= linhas && xx + (search.length() - dumsize) <= colunas) {
							find_word(matriz, dum, xx + 1, yy + 1, move.downright, search, xx + 1, yy + 1);
						}
						if (yy - (search.length() - dumsize) >= 0 && xx + (search.length() - dumsize) <= colunas) {
							find_word(matriz, dum, xx + 1, yy - 1, move.upright, search, xx + 1, yy + 1);
						}
					}
				}
			}

		} else {
			if (matriz[y][x] == search.charAt(wordsize)) { 
				dum = word + String.valueOf(matriz[y][x]);

				// verificar se a palavra dum é igual à palavra procura
				if (dum.equals(search)) {
                    results.add(new WordInfo(search, initial_x, initial_y,move.toString()));
					return ;

				}
				int dumsize = dum.length();
				if (x - (search.length() - dumsize) >= 0 && move == move.left) {
					find_word(matriz, dum, x - 1, y, move.left, search, initial_x, initial_y);
				}
				else if (x + (search.length() - dumsize) <= colunas && move == move.right) {
					find_word(matriz, dum, x + 1, y, move.right, search, initial_x, initial_y);
				}
				else if (y - (search.length() - dumsize) >= 0 && move == move.up) {
					find_word(matriz, dum, x, y - 1, move.up, search, initial_x, initial_y);
				}
				else if (y + (search.length() - dumsize) <= linhas && move == move.down) {
					find_word(matriz, dum, x, y + 1, move.down, search, initial_x, initial_y);
				}
				else if (x - (search.length() - dumsize) >= 0 && y - (search.length() - dumsize) >= 0
						&& move == move.upleft) {
					find_word(matriz, dum, x - 1, y - 1, move.upleft, search, initial_x, initial_y);
				}
				else if (x - (search.length() - dumsize) >= 0 && y + (search.length() - dumsize) <= linhas
						&& move == move.downleft) {
					find_word(matriz, dum, x - 1, y + 1, move.downleft, search, initial_x, initial_y);
				}
				else if (y + (search.length() - dumsize) <= linhas && x + (search.length() - dumsize) <= colunas
						&& move == move.downright) {
					find_word(matriz, dum, x + 1, y + 1, move.downright, search, initial_x, initial_y);
				}
				else if (y - (search.length() - dumsize) >= 0 && x + (search.length() - dumsize) <= colunas
						&& move == move.upright) {
					find_word(matriz, dum, x + 1, y - 1, move.upright, search, initial_x, initial_y);
				}
			}
		}
	}
    private static void print_sopa(){
        char[][] m = new char[linhas][colunas];
        // preenche a matriz com pontos
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                m[i][j] = '.';
            }
        }
        // preenche a matriz dos pontos com as palavras
        for(WordInfo w: results){
            int x = w.getRowFrom()-1;
            int y = w.getColFrom()-1;
            String move = w.getdirection();
            String word = w.getWord();
            for(int i = 0; i < word.length(); i++){
                if(move.equals("left")){
                    m[y][x] = word.charAt(i);
                    x--;
                }
                else if(move.equals("right")){
                    m[y][x] = word.charAt(i);
                    x++;
                }
                else if(move.equals("up")){
                    m[y][x] = word.charAt(i);
                    y--;
                }
                else if(move.equals("down")){
                    m[y][x] = word.charAt(i);
                    y++;
                }
                else if(move.equals("upleft")){
                    m[y][x] = word.charAt(i);
                    x--;
                    y--;
                }
                else if(move.equals("downleft")){
                    m[y][x] = word.charAt(i);
                    x--;
                    y++;
                }
                else if(move.equals("downright")){
                    m[y][x] = word.charAt(i);
                    x++;
                    y++;
                }
                else if(move.equals("upright")){
                    m[y][x] = word.charAt(i);
                    x++;
                    y--;
                }
            }
        }
        // imprime a matriz
        for (int i = 0; i < linhas; i++) {
            gravarArq.printf("\n");
            for (int j = 0; j < colunas; j++) {
                String s = String.valueOf(m[i][j]);
                gravarArq.printf(s);
                gravarArq.printf(" ");
                System.out.print(m[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    public static class WordInfo {

        private String word;
        private int rowFrom;
        private int colFrom;
        private String direction;

        WordInfo(String word, int rowFrom, int colFrom,String direction) {
            this.word = word;
            this.rowFrom = rowFrom;
            this.colFrom = colFrom;
            this.direction=direction;
        }

        public String getWord() { return word; }
        public int getRowFrom() { return rowFrom; }
        public int getColFrom() { return colFrom; }
        public String getdirection() { return direction; }

        
        public int compareTo(WordInfo word2) {
            return word.compareTo(word2.word);
        }
    }
}

