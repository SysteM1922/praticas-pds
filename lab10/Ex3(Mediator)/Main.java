import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	public static void main(String[] args){
		ArrayList<String> estacoes = new ArrayList<>(
				Arrays.asList("Alferrarede", "Belver", "Barca da Amieira", "Fratel"));
		Mediator med = new CentralControlo(new Linha(estacoes));
		new Comboio(1501, "Alferrarede", Sentido.Norte, med).start();
		new Comboio(200, "Fratel", Sentido.Sul, med).start();
		new Comboio(201, "Belver", Sentido.Norte, med).start();
		new Comboio(1503, "Alferrarede", Sentido.Norte, med).start();
		new Comboio(1500, "Barca da Amieira", Sentido.Norte, med).start();
		new Comboio(202, "Fratel", Sentido.Sul, med).start();
		new Comboio(1505, "Belver", Sentido.Sul, med).start();
		new Comboio(203, "Alferrarede", Sentido.Norte, med).start();
		new Comboio(204, "Fratel", Sentido.Sul, med).start();
		new Comboio(1502, "Barca da Amieira", Sentido.Sul, med).start();
		new Comboio(1507, "Alferrarede", Sentido.Norte, med).start();
	}
}
