import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Linha {
	private LinkedHashMap<String, ArrayList<Comboio>> estacoes = new LinkedHashMap<>();

	public Linha(ArrayList<String> list) {
		for (int i = 0; i < list.size(); i++) {
			if(i==list.size()-1)
				estacoes.put(list.get(i), new ArrayList<>());
			else {
				estacoes.put(list.get(i), new ArrayList<>());
				estacoes.put(list.get(i) + "-" + list.get(i + 1), new ArrayList<>());
			}
		}
	}

	public void addComboio(Comboio c) {
		String e = c.getEstacao();
		estacoes.get(e).add(c);
	}

	public LinkedHashMap<String, ArrayList<Comboio>> getEstacoes() {
		return estacoes;
	}
}
