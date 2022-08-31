import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		Command<String> add = new addCommand<>(list);
		Command<String> remove = new removeCommand<>(list);

		add.execute("Olha a laranjinha que caiu, caiu.");
		add.execute("Num regato de água, nunca mais se viu.");
		for(String s: list)
			System.out.println(s);
		System.out.println();

		add.undo();
		add.execute("Nunca mais se viu, nem se torna a ver.");
		for(String s: list)
			System.out.println(s);
		System.out.println();

		add.undo();
		add.undo();
		for(String s: list)
			System.out.println(s);
		System.out.println();

		add.execute("Olha a laranjinha que caiu, caiu.");
		add.execute("Num regato de água, nunca mais se viu.");
		add.execute("Nunca mais se viu, nem se torna a ver.");
		add.execute("Cravos à janela, rosas a nascer.");
		for(String s: list)
			System.out.println(s);
		System.out.println();

		remove.execute("Num regato de água, nunca mais se viu.");
		remove.execute("Olha a laranjinha que caiu, caiu.");
		for(String s: list)
			System.out.println(s);
		System.out.println();

		remove.undo();
		for(String s: list)
			System.out.println(s);
		System.out.println();
	}
}