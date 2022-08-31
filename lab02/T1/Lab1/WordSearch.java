package Lab1;
import java.io.*;
import java.util.*;

public class WordSearch {

	public static void main(String[] args) throws FileNotFoundException {
        File soupFile = new File("C:\\Users\\Toshiba\\eclipse-workspace\\PDS_2022\\src\\Lab1\\sopa.txt");

        Scanner fileScanner = new Scanner(soupFile);
        int soupSize = fileScanner.nextLine().length();
        fileScanner.close();

        if (soupSize > 40) {
            System.out.println("ERRO: O tamanho da sopa é maior que 40!");
            return;
        }

        List<String> words = getWordList(soupFile, soupSize);
        ArrayList<String[]> info = new ArrayList<>();

        for (int i = 0; i < words.size(); i++) {
            for (int j = i + 1; j < words.size(); j++) {
                if (words.get(i).contains(words.get(j)) || words.get(j).contains(words.get(i))) {
                    System.out.println("ERRO: A lsita contém palavras duplicadas!");
                    System.exit(1);
                }
            }
        }

        Character[][] soup = getSoupList(soupFile, soupSize);
        for (String word : words) {
            word = word.toUpperCase();
            String[] returnValues = solvePuzzle(soup, word);
            info.add(returnValues);
            System.out.printf("%-12s %-5s %-6s %s\n", returnValues[3], returnValues[2], returnValues[0], returnValues[1]);
        }
        
        showSolvedPuzzle(soup, soup.length, info);
    }
	
	
    //poe as palavras numa array list
    public static ArrayList<String> getWordList(File thisFile, int size) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(thisFile);
        ArrayList<String> words = new ArrayList<>();

        for(int i = 0 ; i<size ; i++)
            fileScanner.nextLine();

        while (fileScanner.hasNextLine()) {
            String currentLine = fileScanner.nextLine();
            if (currentLine == null) {
            	System.out.println("A lista contém linhas vazias!");
            	System.exit(0);
            }
            String[] currentWords = currentLine.split("[,; ]");

            for (int i = 0; i < currentWords.length; i++){
            	for(char c : currentWords[i].toCharArray()) {
            		if(!Character.isAlphabetic(c)) {
            			System.out.println("A lista de palavras contém carateres não alfabéticos!");
            			System.exit(0);
            		}
            	}
                words.add(currentWords[i]);
            }

        }
        fileScanner.close();
        return words;
    }

    //poe a sopa numa matriz
    public static Character[][] getSoupList(File thisFile,int size) throws FileNotFoundException{
        Scanner fileScanner = new Scanner(thisFile);

        Character[][] sopa = new Character[size][size];

        for(int i = 0 ; i < size ; i++) {
            String currentLine = fileScanner.nextLine();
            if (currentLine == null) {
            	System.out.println("O puzzle contém linhas vazias!");
            	System.exit(0);
            }
            for (int j = 0; j < size; j++) {        	
            	if (Character.isLowerCase(currentLine.charAt(j))) {
            		System.out.println("A sopa contém letras minúsculas!");
            		System.exit(0);
            	}
            	
                sopa[i][j] = currentLine.charAt(j);
            }
        }
        fileScanner.close();
        return sopa;
    }
    
    public static int checkAllDirs(Character[][] sopa, String palavra, int xInicial, int yInicial) {
    	int[] xDirs = {  0,  0,-1, 1, -1, -1,  1, 1}; 
        int[] yDirs = { -1,  1, 0, 0, -1,  1, -1, 1};


        for (int direcoes = 0; direcoes < 8; direcoes++) //Search the 8 cardinal directions
        {

            int charCounter, proxX = xInicial + xDirs[direcoes], proxY = yInicial + yDirs[direcoes];

            for (charCounter = 1; charCounter < palavra.length(); charCounter++) //NOTE: We already checked the first letter before calling this function ; Check all other chars in the same direction
            {
                // If out of bound
                if (proxX >= sopa.length || proxX < 0 || proxY >= sopa.length || proxY < 0)
                    break;

                // If not matched
                if (sopa[proxX][proxY] != palavra.charAt(charCounter))
                    break;

                //Continue moving
                proxX += xDirs[direcoes];
                proxY += yDirs[direcoes];
            }

            if (charCounter == palavra.length())
                return direcoes;
        }
        return -1;
    	
    }
    
    public static String[] solvePuzzle(Character[][] sopa,String palavra){
        int sizeSopa = sopa.length;
        palavra = palavra.toUpperCase();

        int dir = -1;
        String direcao = "";

        for(int i = 0 ; i< sizeSopa ; i++){
            for(int j = 0 ; j < sizeSopa ; j++){
                if (sopa[i][j] == palavra.charAt(0)) {
                    dir = checkAllDirs(sopa, palavra, i, j);
                    if(dir!=-1) {
                    	switch(dir) {
                    		case 0:
                    			direcao = "Left";
                    			break;
                    		case 1:
                    			direcao = "Right";
                    			break;
                    		case 2:
                    			direcao = "Up";
                    			break;
                    		case 3:
                    			direcao = "Down";
                    			break;
                    		case 4:
                    			direcao = "UpLeft";
                    			break;
                    		case 5:
                    			direcao = "DownLeft";
                    			break;
                    		case 6:
                    			direcao = "UpRight";
                    			break;
                    		case 7:
                    			direcao = "DownRight";
                    			break;
                    		default:
                    			break;
                    			
                    	}
                        return new String[]{++i + "," + ++j, direcao, String.valueOf(palavra.length()), palavra};
                }
                }
            }
        }

        System.out.printf("ERROR: Word %s not found\n",palavra);
        System.exit(1);
        return null;
    }
    
    
    public static void showSolvedPuzzle(Character[][] sopa, int size, ArrayList<String[]> info) {
        for(int i = 0; i < size; i++) {
    		for(int j = 0; j < size; j++) {
                sopa[i][j] = '.'; //toda a sopa fica com asteriscos
            }
        }

    	String[] coord;
        String orientation, palavra;
        int tamanho;
    	for(String[] s : info) { //para cada soluçao
    		coord = s[0].split(","); //coordenadas em q a palavra começa
            orientation = s[1];
            tamanho = Integer.parseInt(s[2]);
            palavra = s[3];

            if (orientation == "Down"){
                for(int i = 1; i<=tamanho; i++){
                    sopa[Integer.parseInt(coord[0])+i-2][Integer.parseInt(coord[1])-1] = palavra.charAt(i-1);
                }
            }
            if (orientation == "Up"){ //se a palavra for de baixo para cima
                for(int i = tamanho; i>0; i--){ // loop com base na length da palavra
                    sopa[i][Integer.parseInt(coord[1])-1] = palavra.charAt(tamanho-i); // escreve a palavra na sopa
                }
            }
            if (orientation == "Right"){
                for(int i = 1; i<=tamanho; i++){
                    sopa[Integer.parseInt(coord[0])-1][Integer.parseInt(coord[1])+i-2] = palavra.charAt(i-1);
                }
            }
            if (orientation == "Left"){
                for(int i = tamanho; i>0; i--){
                    sopa[Integer.parseInt(coord[0])-1][i-1] = palavra.charAt(tamanho-i);
                }
            }
            if (orientation == "UpLeft"){
                for(int i = tamanho; i>0; i--){
                    sopa[Integer.parseInt(coord[0])-1-tamanho+i][Integer.parseInt(coord[1])-1-tamanho+i] = palavra.charAt(tamanho-i);
                }
            }
            if (orientation == "UpRight"){
                for(int i = tamanho; i>0; i--){
                    sopa[Integer.parseInt(coord[0])-1+tamanho-i][Integer.parseInt(coord[1])-1-tamanho+i] = palavra.charAt(tamanho-i);
                }
            }
            if (orientation == "DownRight"){
                for(int i = tamanho; i>0; i--){
                    sopa[Integer.parseInt(coord[0])-1+tamanho-i][Integer.parseInt(coord[1])-1+tamanho-i] = palavra.charAt(tamanho-i);
                }
            }
            if (orientation == "DownLeft"){
                for(int i = tamanho; i>0; i--){
                    sopa[Integer.parseInt(coord[0])-1-tamanho+i][Integer.parseInt(coord[1])-1+tamanho-i] = palavra.charAt(tamanho-i);
                }
            }
        }


        for(int i = 0 ; i < size ; i++) { //mostra a sopa
            System.out.println("");
            for (int j = 0; j < size; j++)
                System.out.print(sopa[i][j]);
        }
        System.out.println("");
    }
}