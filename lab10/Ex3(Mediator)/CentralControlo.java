import java.util.LinkedList;

public class CentralControlo extends Thread implements Mediator {
	private Linha line;

	public CentralControlo(Linha line) {
		this.line = line;
	}

	@Override
	public synchronized boolean checkLine(Comboio c) {
		LinkedList<String> list = new LinkedList<>(this.line.getEstacoes().keySet());
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(c.getEstacao())) {
				if (i % 2 != 0) {
					if (c.getSentido() == Sentido.Norte) {
						this.line.getEstacoes().get(list.get(i)).remove(c);
						this.line.getEstacoes().get(list.get(i + 1)).add(c);
						c.setEstacao(list.get(i + 1));
					} else {
						this.line.getEstacoes().get(list.get(i)).remove(c);
						this.line.getEstacoes().get(list.get(i-1)).add(c);
						c.setEstacao(list.get(i - 1));
					}
					return true;
				} else {
					if (c.getSentido() == Sentido.Norte) {
						if (i == list.size() - 1) {
							this.line.getEstacoes().get(list.get(i)).remove(c);
							System.out.println(c.getNr() + "\t| saiu da linha no sentido Norte");
							c.stop();
						} else if (checkLine(list.get(i+1), Sentido.Norte)) {
							this.line.getEstacoes().get(list.get(i)).remove(c);
							this.line.getEstacoes().get(list.get(i + 1)).add(c);
							c.setEstacao(list.get(i + 1));
							return true;
						} else
							return false;
					} else {
						if (i == 0) {
							this.line.getEstacoes().get(list.get(i)).remove(c);
							System.out.println(c.getNr() + "\t| saiu da linha no sentido Sul");
							c.stop();
						} else if (checkLine(list.get(i-1), Sentido.Sul)) {
							this.line.getEstacoes().get(list.get(i)).remove(c);
							this.line.getEstacoes().get(list.get(i-1)).add(c);
							c.setEstacao(list.get(i - 1));
							return true;
						} else
							return false;
					}
				}
			}
		}
		return false;
	}

	public boolean checkLine(String e, Sentido s) {
		if (this.line.getEstacoes().get(e).isEmpty())
			return true;
		else {
			for (Comboio c : this.line.getEstacoes().get(e)) {
				if (!c.getSentido().equals(s))
					return false;
			}
			return true;
		}
	}
}
