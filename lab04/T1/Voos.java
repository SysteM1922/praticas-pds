package lab03;
import java.util.*;
public class Voos {
    private Map<voo,aviao> conjuntoVoo;

    private Voos(){
        this.conjuntoVoo= new TreeMap<voo,aviao>();
    }

    public static Voos criarVoos(){
        return  new Voos();
    }
    // metodo com classe turistica e executiva
    public void adicionarVoo(String code,String dadosTuris,String dadosExec){
        if (dadosTuris.contains("x") && dadosExec.contains("x")){
            String[] turistic = dadosTuris.split("x");
            String[] exec = dadosExec.split("x");
            // verificacao dos dados
            try {
                Integer.parseInt(turistic[0]);
                Integer.parseInt(turistic[1]);
                Integer.parseInt(exec[0]);
                Integer.parseInt(exec[1]);
            } catch (Exception e) {
                System.out.println("Erro: Dados invalidos no ficheiro\n Usage: linhaxcoluna");
                return;
            }
        }
        else{
            System.out.println("Dados invalidos no ficheiro\n Usage: linhaxcoluna");
            return;
        }

        // criacao do voo  em caso de dados corretos
        voo v=voo.criarVoo(code);
        aviao a=aviao.criarAviao(dadosTuris,dadosExec);
        this.conjuntoVoo.put(v, a);
    }
    // metodo sem classe executiva
    public void adicionarVoo(String code,String dadosTuris){
        // verificacao dos dados
        if (dadosTuris.contains("x")){
            String[] partida = dadosTuris.split("x");
            try {
                Integer.parseInt(partida[0]);
                Integer.parseInt(partida[1]);
            } catch (Exception e) {
                System.out.println("Erro: Dados invalidos no ficheiro\n Usage: linhaxcoluna");
                return;
            }
        }
        else{
            System.out.println("Erro: Dados invalidos no ficheiro\n Usage: linhaxcoluna");
            return;
        }
        // criacao do voo  em caso de dados corretos
        voo a=voo.criarVoo(code);
        aviao avi=aviao.criarAviao(dadosTuris);
        this.conjuntoVoo.put(a, avi);
    }
    // informacoes do voo
    public void infoVoo(String code){
        for (voo a : this.conjuntoVoo.keySet()) {
            if (a.getCode().equals(code)){
                aviao av=this.conjuntoVoo.get(a);
             
                System.out.printf("Código de voo %s. ",code);
                if (av.getlugaresExec() == 0){
                    System.out.print("Classe executiva não disponivel neste voo. ");
                }
                else{
                    System.out.printf("Lugares disponiveis: %d  em classe Executiva. ",av.getlugaresExec());
                }
                System.out.printf("Lugares disponiveis: %d  em classe Turistica. ",av.getlugaresTuris());
            }
            
        }
        System.out.println("");
    }
    // print aviao mapa
    public void showAviao(String code){
        // variavel caso o voo nao exista
        boolean existe=false;
        for (voo a : this.conjuntoVoo.keySet()) {
            if (a.getCode().equals(code)){
                existe=true;
                aviao av=this.conjuntoVoo.get(a);
                av.printAviao();
            }   
        }
        if (!existe){
            System.out.println("ESTE CÓDIGO INTRODUZIDO NÃO SE ENCONTRA REGISTADO");
        }
    }
    // reserva de lugares
    public void reserva(String code, char tipo, int lugares,boolean codigo ){
        // variavel caso a classe esteja incorreta
        boolean classe=false;
        String compravativo=code;
        compravativo+=":";
        if (tipo == 'T' ){
            classe=true;
        }
        else if (tipo == 'E' ){
            classe=true;
        }
        if (!classe){
            System.out.println("Tipo de passageiros inválido, Usage: T ou E");
            return ;
        }
        // variavel caso o voo nao exista
        boolean existe=false;
        for (voo a : this.conjuntoVoo.keySet()) {
            if (a.getCode().equals(code)){
                aviao av=this.conjuntoVoo.get(a);
                compravativo+=av.reservaLugares(  tipo,  lugares );
                if (codigo ){
                    System.out.println(compravativo);
                }
                existe=true;
            }   
        }
        if(!existe){
            System.out.println("VOO não existe");
        }
    }

    public void apagarReserva(String codigoVoo, int pessoa){
        // variavel caso o voo nao exista
        boolean existe=false;
        for (voo a : this.conjuntoVoo.keySet()) {
            if (a.getCode().equals(codigoVoo)){
                aviao av=this.conjuntoVoo.get(a);
                av.deleteReserva(pessoa);
                existe=true;
            }   
        }
        if(!existe){
            System.out.println("VOO não existe ");
        }
    }
}
