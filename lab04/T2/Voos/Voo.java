package Voos;

import java.util.*;

public class Voo implements Lugares {
    private String codigo;
    private Aviao aviao;
    private List<List<String>> reservas;
    private int reservation_number; // sequential reservation number (começa em 1)
    private List<int[]> esquema; // vou usá-lo para dar print (guarda as listas dos lugares)
    private int nExecOcupados; // guardar o número de lugar da executiva ocupados
    private int nTouristOcupados; // guardar o número
    private int[] listasVazias; // guardar 1 se a lista estiver vazia e 0 se a lista não estiver vazia
    private int counterFilas;  // conta o número de filas que já estão preenchidas

    public Voo(String codigo, Aviao aviao) {
        this.codigo = codigo;
        this.aviao = aviao;
        this.reservation_number = 1;
        this.esquema = new ArrayList<int[]>();
        this.reservas = new ArrayList<List<String>>();
        this.listasVazias = new int[this.aviao.getFilas()];
        this.counterFilas = 0;

        for (int i = 0; i < this.aviao.getFilas(); i++) {
            this.listasVazias[i] = 1;
        }

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Aviao getAviao() {
        return aviao;
    }

    public void setAviao(Aviao aviao) {
        this.aviao = aviao;
    }

    public void M(String flight_code) { // print do esquema do voo
        for (int i = 1; i <= this.getAviao().getFilas(); i++) // percorrer filas
        {
            System.out.printf("%3d", i);
        }
        System.out.println();

        if (this.nTouristOcupados == 0) {
            System.out.println("O voo ainda não possui reservas.");
        } else {
            int max_nr_lugares = Math.max(this.aviao.getLugaresT(), this.aviao.getLugaresE());
            int min_nr_lugares = Math.min(this.aviao.getLugaresT(), this.aviao.getLugaresE());
            int counter = 0;
            char a;
            for (a = 'A'; a <= 'Z'; a++) { // imprimir os lugares representados por letras do alfabeto (sequencial)
                if (counter >= max_nr_lugares) { // corre até atingir o número máximo de lugares
                    break;
                }
                System.out.printf("%-4s", String.valueOf(a));
                for (int v : this.esquema.get(counter)) {
                    if (counter == min_nr_lugares) {
                        break;
                    }
                    System.out.printf("%3d", v);
                }
                if (counter < min_nr_lugares) {
                    System.out.println();
                }
                if (counter == min_nr_lugares && min_nr_lugares != max_nr_lugares) {
                    if (this.getAviao().getLugaresE() < this.getAviao().getLugaresT()) {
                        String n_spaces = String.valueOf(3 * this.getAviao().getFilasE());
                        System.out.printf("%" + n_spaces + "s", " ");
                        for (int y = this.getAviao().getLugaresE() + 1; y < this.getAviao().getFilas(); y++) {
                            System.out.printf("%3d", this.esquema.get(counter)[y]);
                        }
                    } else {
                        String n_spaces = String.valueOf(3 * this.getAviao().getFilasT());
                        for (int x = 0; x < this.getAviao().getLugaresE(); x++) {
                            System.out.printf("%3d", this.esquema.get(counter)[x]);
                        }
                        System.out.printf("%" + n_spaces + "s", " ");
                    }
                }
                counter++;
            }
        }
    }

    // por enquanto ainda não precisei desta função
    public void F(String flight_code, int num_seats_executive, int num_seats_tourist) { // acrescentar novo voo

    }

    public void R(String flight_code, char classe, int number_seats) { // assumindo que só se faz uma reserva de cada vez

        for (int n = 0; n < this.aviao.getLugaresE() + this.aviao.getLugaresT(); n++) {
            this.esquema.add(new int[this.aviao.getFilas()]);
        }

        int next_r = this.reservation_number; // next reservation
        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "X", "Y", "Z"};
        System.out.printf("%s:%d = ", flight_code, this.reservation_number);
        List<String> reservasTemp = new ArrayList<String>();

        // A classe executiva começa na primeira linha
        if (classe == 'E') {
            int numSeated = 0;
            int[][] grid = this.getAviao().getExecGrid();
            for (int f = 0; f < this.getAviao().getFilasE(); f++) { // percorrer filas - classe executiva
                if (numSeated == number_seats) {
                    break;
                }
                if (this.nExecOcupados + number_seats > this.aviao.getnE()) {
                    System.out.printf("Não foi possível fazer reserva para o pedido %s%d.\n", String.valueOf(classe), number_seats);
                    this.reservation_number--;
                    break;
                }
                if (this.listasVazias[f] == 1) {
                    for (int l = 0; l < this.getAviao().getLugaresE(); l++) { // percorrer lugares - classe executiva
                        if (numSeated == number_seats) {
                            break;
                        }
                        for (int k = 0; k < number_seats; k++) {
                            if (grid[f][l] == 0) {
                                this.listasVazias[f] = 0;
                                counterFilas++;
                                grid[f][l] = next_r;
                                this.esquema.get(l)[f] = next_r;
                                System.out.printf("%d%s ", f + 1, alphabet[l]);
                                numSeated++;
                                this.nExecOcupados++;
                                reservasTemp.add(classe + " " + String.valueOf(f + 1) + " " + alphabet[l]);
                            }
                        }
                    }
                } else if (this.listasVazias[f] == 0 && f == this.aviao.getFilasE() - 1) {
                    SearchE(flight_code, numSeated, number_seats);
                }
            }
        } else {
            int numSeated = 0;
            Aviao a = this.getAviao();
            int[][] grid = a.getTouristGrid();
            int filas = a.getFilas();
            int filasT = a.getFilasT();
            int lugaresT = a.getLugaresT();
            // só começa a preencher na classe turista depois das filas da classe executiva
            for (int f = a.getFilasE(); f < filas; f++) {
                if (numSeated == number_seats) {
                    break;
                }
                if (this.nTouristOcupados + number_seats > this.aviao.getnT()) {
                    System.out.printf("Não foi possível obter lugares para a reserva: %s %d.", String.valueOf(classe), number_seats);
                    this.reservation_number--;
                    break;
                }
                if (this.listasVazias[f] == 1) { // só irá preencher listas vazias
                    for (int l = 0; l < lugaresT; l++) { // percorrer lugares - classe turística
                        if (numSeated == number_seats) {
                            break;
                        }
                        for (int k = 0; k < number_seats; k++) {
                            //System.out.printf("filasT=%d, f=%d, lugaresT=%d, l=%d\n", filasT, f, lugaresT, l);
                            if (f < filasT && l < lugaresT && grid[f][l] == 0) {
                                this.listasVazias[f] = 0;
                                grid[f][l] = next_r;
                                this.esquema.get(l)[f] = next_r;
                                System.out.printf("%d%s ", f + 1, alphabet[l]);
                                numSeated++;
                                this.nTouristOcupados++;
                                reservasTemp.add(classe + " " + String.valueOf(f + 1) + " " + alphabet[l]);
                            }
                        }
                    }
                } else if (this.listasVazias[f] == 0 && f == filas - 1) {
                    SearchT(flight_code, numSeated, number_seats);
                }
            }
        }
        this.reservas.add(reservasTemp);
        System.out.println();
        this.reservation_number++;
    }

    public void C(String reservation_code) {
        String[] input = reservation_code.split(":");
        int nrReserva = Integer.parseInt(input[1]);
        Aviao a = this.getAviao();
        List<String> reserva = this.reservas.get(nrReserva);
        int[][] touristGrid = a.getTouristGrid();
        int[][] execGrid = a.getExecGrid();
        String[] r;
        int f, l;
        for (int i = 0; i < reserva.size(); i++) {
            r = reserva.get(i).split(" ");
            f = Integer.parseInt(r[1]);
            l = Integer.parseInt(String.valueOf(r[2].charAt(0) - 'A' + 1)); //exemplo: A - A + 1 = 65 - 65 + 1 = 1 -> Índice 1
            if (r[0] == "T") touristGrid[f][l] = 0;
            else execGrid[f][l] = 0;
        }
        a.setTouristGrid(touristGrid);
        a.setExecGrid(execGrid);
        this.reservas.set(nrReserva, null);
    }

    public int getnTouristOcupados() {
        return this.nTouristOcupados;
    }

    public int getnExecOcupados() {
        return this.nExecOcupados;
    }

    public void SearchE(String flight_code, int numSeated, int number_seats) {
        int next_r = this.reservation_number; // next reservation
        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "X", "Y", "Z"};
        System.out.printf("%s:%d = ", flight_code, this.reservation_number);
        List<String> reservasTemp = new ArrayList<String>();

        int[][] grid = this.getAviao().getExecGrid();
        for (int f = 0; f < this.getAviao().getFilasE(); f++) { // percorrer filas - classe executiva
            if (numSeated == number_seats) {
                break;
            }
            if (this.nExecOcupados + number_seats > this.aviao.getnE()) {
                System.out.printf("Não foi possível fazer reserva para o pedido E%d.\n", number_seats);
                this.reservation_number--;
                break;
            }
            for (int l = 0; l < this.getAviao().getLugaresE(); l++) { // percorrer lugares - classe executiva
                if (numSeated == number_seats) {
                    break;
                }
                for (int k = 0; k < number_seats; k++) {
                    if (grid[f][l] == 0) {
                        grid[f][l] = next_r;
                        this.esquema.get(l)[f] = next_r;
                        System.out.printf("%d%s ", f + 1, alphabet[l]);
                        numSeated++;
                        this.nExecOcupados++;
                        reservasTemp.add("E" + " " + String.valueOf(f + 1) + " " + alphabet[l]);
                    }
                }
            }
        }
    }

    public void SearchT(String flight_code, int numSeated, int number_seats) {
        int next_r = this.reservation_number; // next reservation
        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "X", "Y", "Z"};
        System.out.printf("%s:%d = ", flight_code, this.reservation_number);
        List<String> reservasTemp = new ArrayList<String>();

        int[][] grid = this.aviao.getTouristGrid();

        // só começa a preencher na classe turista depois das filas da classe executiva
        for (int f = this.aviao.getFilasE(); f < this.aviao.getFilas(); f++) { // percorrer filas - classe executiva
            if (numSeated == number_seats) {
                break;
            }
            if (this.nTouristOcupados + number_seats > this.aviao.getnT()) {
                System.out.printf("Não foi possível obter lugares para a reserva T%d.", number_seats);
                this.reservation_number--;
                break;
            }
            for (int l = 0; l < this.aviao.getLugaresT(); l++) { // percorrer lugares - classe turística
                if (numSeated == number_seats) {
                    break;
                }
                for (int k = 0; k < number_seats; k++) {
                    //System.out.printf("filasT=%d, f=%d, lugaresT=%d, l=%d\n", filasT, f, lugaresT, l);
                    if (f < this.aviao.getFilasT() && l < this.aviao.getLugaresT() && grid[f][l] == 0) {
                        grid[f][l] = next_r;
                        this.esquema.get(l)[f] = next_r;
                        System.out.printf("%d%s ", f + 1, alphabet[l]);
                        numSeated++;
                        this.nTouristOcupados++;
                        reservasTemp.add("T" + " " + String.valueOf(f + 1) + " " + alphabet[l]);
                    }
                }
            }
        }
    }
}
