package lab03;
public class aviao {
    private int lugaresExec; // numero de lugares executivo
    private int lugaresTuris;  // numero de lugares turistico
    private int lugaresExecDisponiveis; // numero de lugares executivo disponiveis
    private int lugaresTuristasDisponiveis; // numero de lugares turistico disponiveis
    private int linhasExec; // numero de linhas executivo
    private int colunasExec; // numero de colunas executivo
    private int linhasTuris; // numero de linhas turistico
    private int colunasTuris; // numero de colunas turistico
    private String [] dadosExec; // array com os dados executivos
    private String [] dadosTuris; // array com os dados turisticos
    private int [] [] mapExec; // array com a disposicao dos lugares executivo
    private int [] [] mapTuris; // array com a disposicao dos lugares turistico
    private  int id=0; // numero lugares
    // construtor priv com dados executivos e turisticos
    private aviao(String dadosTuris,String dadosExec ){
        this.dadosTuris=dadosTuris.split("x");
        this.lugaresTuris=Integer.parseInt(this.dadosTuris[0])*Integer.parseInt(this.dadosTuris[1]);
        this.mapTuris=new int [Integer.parseInt(this.dadosTuris[0])] [Integer.parseInt(this.dadosTuris[1])];
        this.dadosExec=dadosExec.split("x");
        this.lugaresExec=Integer.parseInt(this.dadosExec[0])*Integer.parseInt(this.dadosExec[1]);
        this.mapExec=new int [Integer.parseInt(this.dadosExec[0])] [Integer.parseInt(this.dadosExec[1])];
        this.lugaresTuristasDisponiveis=this.lugaresTuris;
        this.lugaresExecDisponiveis=this.lugaresExec;
        this.linhasExec=Integer.parseInt(this.dadosExec[0]);
        this.colunasExec=Integer.parseInt(this.dadosExec[1]);
        this.linhasTuris=Integer.parseInt(this.dadosTuris[0]);
        this.colunasTuris=Integer.parseInt(this.dadosTuris[1]);
    }
    // construtor pub com dados executivos e turisticos
    public static aviao criarAviao(String dadosTuris,String dadosExec){
        return new aviao(dadosTuris,dadosExec);
    }
    // construtor priv sem dados executivos
    private aviao(String dadosTuris){
        this.dadosTuris=dadosTuris.split("x");
        this.lugaresExec=0;
        this.lugaresTuris=Integer.parseInt(this.dadosTuris[0])*Integer.parseInt(this.dadosTuris[1]);
        this.mapTuris=new int [Integer.parseInt(this.dadosTuris[0])] [Integer.parseInt(this.dadosTuris[1])];
        this.lugaresTuristasDisponiveis=this.lugaresTuris;
        this.lugaresExecDisponiveis=this.lugaresExec;
        this.linhasExec=0;
        this.colunasExec=0;
        this.linhasTuris=Integer.parseInt(this.dadosTuris[0]);
        this.colunasTuris=Integer.parseInt(this.dadosTuris[1]);
    }
    // construtor pub sem dados executivos
    public static aviao criarAviao(String dadosTuris){
        return new aviao(dadosTuris);
    }

    public int getlugaresExec() {
        return this.lugaresExec;
    }

    public int getlugaresTuris() {
        return this.lugaresTuris;
    }
    
    // metodo para printar aviao
    public void printAviao(){
        int linhasTuristas=Integer.parseInt(this.dadosTuris[0]);
        int colunasTuristas=Integer.parseInt(this.dadosTuris[1]);
        // caso tenha lugares executivos
        if(this.lugaresExec>0){
            int j;
            int linhasExecutivas=Integer.parseInt(this.dadosExec[0]);
            int colunasExecutivas=Integer.parseInt(this.dadosExec[1]);
            int maxColunas;
            if(colunasExecutivas>= colunasTuristas){
                maxColunas=colunasExecutivas;
            }
            else{
                maxColunas=colunasTuristas;
            }
            System.out.print(" ");
            for (int i= 0; i<linhasExecutivas ;i++){
                System.out.printf("%3s ",i+1);
            }
            for (int a= linhasExecutivas; a<linhasTuristas+ linhasExecutivas;a++){
                System.out.printf("%3s ",a+1);
            }
            System.out.println("");
            // String para as linhas
            StringBuilder abc = new StringBuilder();
            abc.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            for (int m =0;m<maxColunas;m++){
                char classe=abc.charAt(m);
                System.out.print(classe);
                if (this.lugaresTuris !=0){
                    for ( j=0;j<linhasExecutivas;j++){
                        if (m < colunasExecutivas){
                            System.out.printf("%3s ",this.mapExec[j][m]);
                        }
                    }
                }
                String sp=" ";
                if (m >=colunasExecutivas){  
                    for(int n=0;n<linhasExecutivas;n++) {
                        System.out.printf("%3s ",sp);  
                    }   
                }
                for (j=0;j<linhasTuristas;j++){
                    if (m < colunasTuristas){
                        System.out.printf("%3s ",this.mapTuris[j][m]); 
                    }
                }
                System.out.println("");
            }
        }
        else{
            System.out.print(" ");
            for (int a= 0; a<linhasTuristas;a++){
                System.out.printf("%3s ",a+1);
            }
            System.out.println("");
            // String para as linhas
            StringBuilder abc = new StringBuilder();
            abc.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            int j;
            for (int k =0;k<colunasTuristas;k++){
                char classe=abc.charAt(k);
                System.out.print(classe);
                for (j=0;j<linhasTuristas;j++){
                    if (k < colunasTuristas){
                        System.out.printf("%3s ",this.mapTuris[j][k]);
                    }
                }
                System.out.println("");
            }
        }

        
      }

    public String reservaLugares( char tipo, int lugares ){
        int i,j;
        String print="";
        int letra_inicial =65; // letra A em ASCII
        char classe;
        // variavel lugares ocupados
        boolean check=true;
        int linha,coluna,coluna2;
        switch (tipo){
            // caso seja executivo
            case 'E':
                    // verifica se tem lugares disponiveis
                    if(lugares > this.lugaresExecDisponiveis){
                        System.out.printf("Não foi possivel obter lugares para a reserva: %s %d\n",tipo,lugares);
                        return " ";
                    }
                    int linhasExecutivas=Integer.parseInt(this.dadosExec[0]);
                    int colunasExecutivas=Integer.parseInt(this.dadosExec[1]);
                    this.id++;
                    print+=id;
                    print+=" ";
                    for ( linha=0; linha <linhasExecutivas;linha++){
                        check=true;
                        for ( coluna=0; coluna<colunasExecutivas;coluna++){
                            if(this.mapExec[linha][coluna] != 0){
                                check=false;
                                break;
                            }
                        }
                        
                        if (check){
                            for (coluna2=0; coluna2<colunasExecutivas;coluna2++){
                                if(this.mapExec[linha][coluna2] == 0){
                                    this.mapExec[linha][coluna2]=this.id;
                                    lugares--;
                                    this.lugaresExecDisponiveis--;
                                    classe=(char)(letra_inicial+coluna2);
                                    print+=linha+1;
                                    print+=classe;
                                    print+= " | ";
                                    if (lugares ==0){
                                        return print;
                                    }
                                    
                                }
                            }
                        }
                    }
                    for(i=0;i<linhasExecutivas;i++){
                        for (j=0;j<colunasExecutivas;j++){
                            if (this.mapExec[i][j] == 0){
                                this.mapExec[i][j]=this.id;
                                lugares--;
                                this.lugaresExecDisponiveis--;
                                this.lugaresExecDisponiveis--;
                                classe=(char)(letra_inicial+colunasExecutivas);
                                print+=linha+1;
                                print+=classe;
                                print+= " | ";
                                if (lugares ==0){
                                    return print ;
                                }
                            }
                        }
                    }
                
                    break;
            // caso seja turista
            case 'T':
                // verifica se tem lugares disponiveis
                    if(lugares > this.lugaresTuristasDisponiveis){
                        System.out.printf("Não foi possivel obter lugares para a reserva: %s %d\n",tipo,lugares);
                        return " ";
                    }
                    int linhasTuristas=Integer.parseInt(this.dadosTuris[0]);
                    int colunasTuristas=Integer.parseInt(this.dadosTuris[1]);
                    this.id++;
                    print+=id;
                    print+=" ";

                    for ( linha=0; linha <linhasTuristas;linha++){
                        check=true;
                            for ( coluna=0; coluna<colunasTuristas;coluna++){
                                if(this.mapTuris[linha][coluna] != 0){
                                    check=false;
                                    break;
                                    
                                }
                            }
                            if (check){
                            
                                for ( coluna2=0; coluna2<colunasTuristas;coluna2++){
                                    if(this.mapTuris[linha][coluna2] == 0){
                                        this.mapTuris[linha][coluna2]=this.id;
                                        lugares--;
                                        this.lugaresTuristasDisponiveis--;
                                        classe=(char)(letra_inicial+coluna2);
                                        
                                        print+=linha+1+this.linhasExec;
                                        print+=classe;
                                        print+= " | ";
                                        if (lugares ==0){
                                            return print;
                                        }
                                        
                                    }
                                }
                    }
                }
                    for(i=0;i<linhasTuristas;i++){
                        for (j=0;j<colunasTuristas;j++){
                            if (this.mapTuris[i][j] == 0){
                                this.mapTuris[i][j]=this.id;
                                lugares--;
                                this.lugaresTuristasDisponiveis--;
                                classe=(char)(letra_inicial+colunasTuristas);
                                print+=linha+1+this.linhasExec;
                                print+=classe;
                                print+= " | ";
                                if (lugares ==0){
                                    return print;
                                }
                            }
                        }
                    }
                    
                    break;
            default:
                    System.out.println("Classe Incorreta");
                    return " ";
        }
        return " ";
    }
    public void deleteReserva(int numero){
        boolean semreserva=false;
            for (int i =0;i< this.linhasExec;i++){
                for(int j=0;j<this.colunasExec;j++){
                    if(numero== this.mapExec[i][j]){
                        this.mapExec[i][j]=0;
                        semreserva=true;
                    }
                }
            }
            for (int i =0;i< this.linhasTuris;i++){
                for(int j=0;j<this.colunasTuris;j++){
                    if(numero== this.mapTuris[i][j]){
                        this.mapTuris[i][j]=0;
                        semreserva=true;
                    }
                }
            }

        if (!semreserva){
            System.out.println("sequential_reservation_number sem reserva, impossível eliminar");
        }
        else{
            System.out.println("Reserva eliminada");
        }

    }

    @Override
    public String toString() {
        return "{" +
            " lugaresExec='" + getlugaresExec() + "'" +
            ", lugaresTuris='" + getlugaresTuris() + "'" +
            "}";
    
    }
}
