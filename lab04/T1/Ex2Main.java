package lab03;
import java.util.*;
import java.io.*;
public class Ex2Main {
    public static void main(String[]args) throws IOException{
                // Lê o ficheiro de dados se existir
                Voos listaVoos=Voos.criarVoos();
                Scanner sc = new Scanner(System.in);
                if (args.length > 0) {   
                        
                        System.out.println("Ficheiro de dados: "+args[0]);
                        Scanner sc1 = null;
                        String [] separar;
                        //Cria um TreeMap para guardar os voos
                        
                        File filename = new File(args[0]);
                                        try{
                                                sc1 = new Scanner(filename);
                                        }
                                        catch(FileNotFoundException e){
                                                System.err.println("Erro: Ficheiro não encontrado");
                                        }
                                        String linha;                           
                                        String te[]; 
                                        
                                        String codigo="";
                                        char tipo;
                                        String [] arr;
                                        boolean lugares=false;  // para saber se o ficheiro tem lugares turistica e/ou executiva
                                        while(sc1.hasNextLine()){
                                                linha =sc1.nextLine();
                                                if(!lugares){
                                                        if (linha.charAt(0)!= '>'){
                                                                System.err.println("Erro: O primeiro caracter deve ser >");
                                                                break;
                                                        }
                                                        else{
                                                                separar=linha.split(">");
                                                                te=separar[1].split(" "); 
                                                                codigo=te[0];
                                                        }
                                                        // caso tenha 3 argumentos tem classe turistica e executiva
                                                        if(te.length==3){
                                                                listaVoos.adicionarVoo(te[0], te[2],te[1]);
                                                                listaVoos.infoVoo(te[0]);
                                                        }
                                                        // caso tenha 2 argumentos tem apenas classe turistica
                                                        else{   
                                                                listaVoos.adicionarVoo(te[0], te[1]);     
                                                                listaVoos.infoVoo(te[0]);
                                                        }
                                                        lugares=true;
                                                }
                                                // Lugares reservados
                                                else{
                                                        arr=linha.split(" ");
                                                        int lugar=0;
                                                                
                                                        tipo=arr[0].toUpperCase().charAt(0);
                                                        try{
                                                                lugar = Integer.parseInt(arr[1]);
                                                        }
                                                        catch (Exception e){
                                                                System.out.println("Erro: O segundo argumento deve ser um número (lugar da reserva)");
                                                                break;
                                                        }
                                                        listaVoos.reserva(codigo,tipo,lugar,false);
                                                }
                                        }
                                        sc1.close();
                }
                char escolha;
                String arg = "";
                boolean exit = false;
                String [] separar;
                //Cria um TreeMap para guardar os voos
                while(true){
                        System.out.println("Escolha uma opção: (H para ajuda)");
                        escolha = sc.next().charAt(0);
                        escolha = Character.toUpperCase(escolha);
                        switch (escolha){
                                case 'H':
                                        System.out.println(
                                                "----------------------------- Menu ----------------------------- \n" +
                                                "'I <ficheiro>': Lê um ficheiro de texto contendo informação sobre o voo. \n" +
                                                "'M <codigoVoo>': Exibe o mapa das reservas de um voo. \n" +
                                                "'F <codigoVoo> <lugaresTuristica> <lugaresExecutiva>': Acrescenta um novo voo, com código, lugares em turística e/ou em executiva (parâmetro opcional). \n" +
                                                "'R <codigoVoo> <classe> <numeroLugares>': Acrescenta uma nova reserva a um voo, com indicação do código do voo, da classe (T/E) e do número de lugares.\n" +
                                                "'C <codigoReserva>': Cancela uma reserva.\n" +
                                                "'Q': Encerra o programa.\n" +
                                                "----------------------------------------------------------------");
                                        break;
                                case 'Q':
                                        exit = true;
                                        break;
                                case 'I':
                                        // Lê o ficheiro de dados se existir
                                        arg = sc.nextLine().trim();
                                        //caso nao coloque argumentos no comando
                                        if(arg.equals("") ){
                                                System.out.println(" Tem de inserir um argumento após o I Ex: I lab03/flight1.txt ");
                                                break;
                                        }
                                        File filename = new File(arg);
                                        Scanner sc2 = null;
                                        try{
                                                sc2 = new Scanner(filename);
                                        }
                                        catch(FileNotFoundException e){
                                                System.err.println("Erro: Ficheiro não encontrado");
                                                break;
                                        }
                                        String linha;                           
                                        String te[]; 
                                        
                                        String codigo="";
                                        char tipo;
                                        String [] arr;
                                        boolean lugares=false;  // para saber se o ficheiro tem lugares turistica e/ou executiva
                                        while(sc2.hasNextLine()){
                                                linha =sc2.nextLine();
                                                if(!lugares){
                                                        if (linha.charAt(0)!= '>'){
                                                                System.err.println("Erro: O primeiro caracter deve ser >");
                                                                break;
                                                        }
                                                        else{
                                                                separar=linha.split(">");
                                                                te=separar[1].split(" "); 
                                                                codigo=te[0];
                                                        }
                                                        // caso tenha 3 argumentos tem classe turistica e executiva
                                                        if(te.length==3){
                                                                listaVoos.adicionarVoo(te[0], te[2],te[1]);
                                                                listaVoos.infoVoo(te[0]);
                                                        }
                                                        // caso tenha 2 argumentos tem apenas classe turistica
                                                        else{   
                                                                listaVoos.adicionarVoo(te[0], te[1]);     
                                                                listaVoos.infoVoo(te[0]);
                                                        }
                                                        lugares=true;
                                                }
                                                // Lugares reservados
                                                else{
                                                        arr=linha.split(" ");
                                                        int lugar=0;
                                                                
                                                        tipo=arr[0].toUpperCase().charAt(0);
                                                        try{
                                                                lugar = Integer.parseInt(arr[1]);
                                                        }
                                                        catch (Exception e){
                                                                System.out.println("Erro: O segundo argumento deve ser um número (lugar da reserva)");
                                                                break;
                                                        }
                                                        listaVoos.reserva(codigo,tipo,lugar,false);
                                                }
                                        }
                                        sc2.close();
                                        break;
                                case 'M':
                                        arg=sc.nextLine().trim();
                                        if(arg.equals("") ){
                                                System.out.println("Erro: Deve inserir um arg após o M ");
                                                break;
                                        }
                                        listaVoos.showAviao(arg);
                                        break;
                                case 'F':
                                        arg = sc.nextLine();
                                        args = arg.split(" ");
                                        // tem lugares turistica e executiva
                                        if (args.length==4){
                                                listaVoos.adicionarVoo(args[1], args[3], args[2]);
                                                break;
                                        // so tem lugares turistica
                                        }else if (args.length==3){
                                                listaVoos.adicionarVoo(args[1], args[2]);
                                                break;
                                        }else{
                                                System.out.println("Erro: Argumento inválido após o F");
                                                
                                        }
                                        break;
                                case 'R':
                                        arg = sc.nextLine();
                                        args = arg.split(" ");
                                        // R + 3 args
                                        if (args.length==4){
                                                try {
                                                        int lugar=Integer.parseInt(args[3]);
                                                        listaVoos.reserva(args[1],args[2].toUpperCase().charAt(0),lugar,true);
                                                } catch (Exception e) {
                                                        System.out.println("Usage: <codigoVoo> <classe> <numeroLugares> Segundo arg e deverá ser um T/E, o terceiro arg deve ser um inteiro ");
                                                        return;
                                                }
                                                break;
                                        }else{
                                                System.out.println("Erro: Deve introduzir um codigo de voo class (T/E) e quantos lugares ");
                                                break;
                                        }                       
                                case 'C':
                                        arg = sc.nextLine();
                                        separar = arg.split(":");
                                        if (separar.length==2){
                                                String codigoVoo=separar[0].trim();
                                                try {
                                                        int lugar=Integer.parseInt(separar[1]);
                                                        listaVoos.apagarReserva(codigoVoo,lugar);
                                                } catch (Exception e) {
                                                        System.out.println("Erro: sequential_reservation_number para inteiro ");
                                                        return;
                                                }
                                                break;
                                        }else{ // sem código
                                                System.out.println("Usage: flight_code:sequential_reservation_number");
                                        } 
                                        break;
                                default:
                                        System.out.println("Erro: Opção não implementada, digite H para ajuda");
                        }if (exit)break;
                }
                sc.close();
        }
}
