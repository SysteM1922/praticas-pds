package lab01;
import java.io.*;
import java.util.*;
public class WSGenerator {
    static List<String> palavras=new ArrayList<String>(); 
	static int size=12; 
	static char m[][] = new char[40][40]; //Matriz
	static String saveFile; 
	static String str_palavras="";

	public static void main(String[] args) {
		String filename=""; 
		for(int i=0; i<args.length; i++) {
			switch(args[i]) {
				case "-i": //ficheiro que tem as palavras
					filename=args[i+1];
					break;
				case "-s": //Tamanho da sopa de letras
					size=Integer.valueOf(args[i+1]);
					if(size>40){
						System.out.println("Tamanho da tabela não pode ser superior a 40x40");
						System.exit(0);
					}
					break;
				case "-o": //Ficheiro que guarda a sopa de letras
					saveFile=args[i+1];
					break;
				default:break;
			}
		}
		
		try {
			File file = new File(filename); 
		    Scanner sc = new Scanner(file); 
		  
		    while (sc.hasNextLine()){
		    	String linha=sc.nextLine().trim().toUpperCase();
		    	str_palavras+=linha+"\n";

		    	if(linha.contains(",") || linha.contains(";") || linha.contains(" ")) {
		    		String[] lista = linha.split("[,; ]");
		    		for(String s : lista){
						palavras.add(s);
						if(s.length() > size){ 
							System.out.println("Palavra maior que sopa");
							System.out.println("Inválido!");
							System.exit(0);
						}
					}
					
		    	}else {
					palavras.add(linha);
					if(linha.length() > size){ 
						System.out.println("Palavra maior que a sopa");
						System.out.println("Inválido!");
						System.exit(0);
					}
		    	}
		  	}
		    sc.close();
		}catch (IOException e) {
			System.out.println("Ficheiro não existe!");
			System.exit(0);
		}

		duplicateWords(palavras);
		matrix(palavras);
		preencher();
		saveSoup();
	
	}



	
	public static void duplicateWords(List<String> palavras)
	{
		for (String palavra : palavras) {
			for (String p2 : palavras) {
				if (palavra.contains(p2) && !palavra.equals(p2)) { 
					System.err.println("Existem palavras duplicadas");
					System.exit(0);
				}
			}
		}
	}
		
	public static void matrix(List<String> palavras)
	{
		boolean aux1 = false; //palavra pode ser escrita -> True se não -> False
		for(String word : palavras) {
			while(!aux1){
				double rand_int = Math.random();  
				rand_int = rand_int * 8 + 1; 
				int random_int = (int) rand_int;   

				switch(random_int) {
					case 1: //right
						double rand_n=Math.random();					
						int rand_l= (int) (rand_n*(size));	
						rand_n=Math.random();										
						int rand_c= (int) (rand_n*(size-(word.length()-1))); 	
						
						if(rand_c<0) {
							break;
						}
						
						boolean bol = false;
						for(int i=rand_c; i<rand_c+word.length(); i++) {
							if(m[rand_l][i] != 0){
								bol=true;
							}
						}
						
						if(!bol) {
							int pos=0;
							for(int i=rand_c; i<rand_c+word.length(); i++) {
								m[rand_l][i]=word.charAt(pos);
								pos++;
							}
							aux1=true;
						}
						break;
					case 2: //left
						rand_n=Math.random();				
						rand_l= (int) (rand_n*(size));
						rand_n=Math.random();														
						rand_c= (int) ((rand_n*(size-word.length()))+word.length());	
						
						if(rand_c>=size) {
							break;
						}
						
						bol = false;									 
						for(int i=rand_c; i>rand_c-word.length(); i--) {		
							if(m[rand_l][i] != 0){									
								bol=true;										
							}															
						}																
						
						if(!bol) {
							int pos=0;
							for(int i=rand_c; i>rand_c-word.length(); i--) {	
								m[rand_l][i]=word.charAt(pos);							
								pos++;														
							}
							aux1=true;
						}
						
						break;
					
					case 3: //up
						rand_n=Math.random();
						rand_c= (int) (rand_n*(size));
						rand_n=Math.random();
						rand_l= (int) (rand_n*(size-word.length()) + word.length());

						if(rand_l<0) {
							break;
						}
						
						bol = false;
						for(int i=rand_l; i>rand_l-word.length(); i--) {
							if(m[i][rand_c] != 0){
								bol=true;
							}
						}
						
						if(!bol) {
							int pos=0;
							for(int i=rand_l; i>rand_l-word.length(); i--) {
								m[i][rand_c]=word.charAt(pos);
								pos++;
							}
							aux1=true;
						}
						
						break;
					case 4: //down
						rand_n=Math.random();
						rand_c= (int) (rand_n*(size));
						rand_n=Math.random();
						rand_l= (int) (rand_n*(size-(word.length()-1)));
						
						if(rand_l<0) {
							break;
						}
						
						bol = false;
						for(int i=rand_l; i<rand_l+word.length(); i++) {
							if(m[i][rand_c] != 0){
								bol=true;
							}
						}
						
						if(!bol) {
							int pos=0;
							for(int i=rand_l; i<rand_l+word.length(); i++) {
								m[i][rand_c]=word.charAt(pos);
								pos++;
							}
							aux1=true;
						}
						break;
					case 5: //upLeft
						rand_n=Math.random();
						rand_c= (int) (rand_n*(size-word.length())+word.length());
						rand_n=Math.random();
						rand_l= (int) (rand_n*(size-word.length()) + word.length());
						
						int count=0;
						bol = false;
						for(int i=rand_l; i>rand_l-word.length(); i--) {
							if(m[i][rand_c-count] != 0){
								bol=true;
							}
							count++;
						}
						
						if(!bol) {
							int pos=0;
							for(int i=rand_l; i>rand_l-word.length(); i--) {
								m[i][rand_c-pos]=word.charAt(pos);
								pos++;
							}
							aux1=true;
						}
						break;
						case 6: //upRight
						rand_n=Math.random();
						rand_l= (int) (rand_n*(size-word.length()) + word.length());
						rand_n=Math.random();
						rand_c= (int) (rand_n*(size-(word.length()-1)));
						
						count=0;
						bol = false;
						for(int i=rand_l; i>rand_l-word.length(); i--) {
							if(m[i][rand_c+count] != 0){
								bol=true;
							}
							count++;
						}
						
						if(!bol) {
							int pos=0;
							for(int i=rand_l; i>rand_l-word.length(); i--) {
								m[i][rand_c+pos]=word.charAt(pos);
								pos++;
							}
							aux1=true;
						}
						
						break;
					case 7: //downLeft
						rand_n=Math.random();
						rand_c= (int) ((rand_n*(size-word.length()))+word.length());
						rand_n=Math.random();
						rand_l= (int) (rand_n*(size-(word.length()-1)));
						
						if(rand_l<0 || rand_c>size) {
							break;
						}
						
						count=0;
						bol = false;
						for(int i=rand_l; i<rand_l+word.length(); i++) {
							if(m[i][rand_c-count] != 0){
								bol=true;
							}
							count++;
						}
						
						if(!bol) {
							int pos=0;
							for(int i=rand_l; i<rand_l+word.length(); i++) {
								m[i][rand_c-pos]=word.charAt(pos);
								pos++;
							}
							aux1=true;
						}
						
						
						break;
					case 8: //downRight
						rand_n=Math.random();
						rand_c= (int) (rand_n*(size-(word.length()-1)));
						rand_n=Math.random();
						rand_l= (int) (rand_n*(size-(word.length()-1)));
						
						count=0;
						bol = false;
						for(int i=rand_l; i<rand_l+word.length(); i++) {
							if(m[i][rand_c+count] != 0){
								bol=true;
							}
							count++;
						}
						if(!bol) {
							int pos=0;
							for(int i=rand_l; i<rand_l+word.length(); i++) {
								m[i][rand_c+pos]=word.charAt(pos);
								pos++;
							}
							aux1=true;
						}
						
						break;
					
					default: break;
				}
			}

			aux1=false;
		}
	}
	//Preenchimento do resto da matriz
	public static void preencher()
	{
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (m[i][j] == 0) {
					Random r = new Random();
					char c = (char) (r.nextInt(26) + 'A');
					m[i][j] = c;
				}
			}
		}
	}

	public static void saveSoup()
	{
		int counter=0;
		for (char[] word : m) {
			counter++;
			if(counter<=size) {
				System.out.println(word);
			}
		}
		System.out.println(str_palavras.toLowerCase());
		//Guarda sopa de letras
		if(saveFile!=null){
			try {
	            FileWriter writer = new FileWriter(saveFile, false);
	            counter=0;
	    		for (char[] word : m) {
	    			counter++;
	    			if(counter<=size) {
	    				writer.write(word);
	    				writer.write("\r\n");
	    			}
	    		}
	    		writer.write(str_palavras.toLowerCase());
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
	}
}

		
	

