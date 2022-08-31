import java.util.ArrayList;
import java.util.List;

public class Insurance {
	private static List<Person> users = new ArrayList<>();

	public static void regist(Person p) {
		users.add(p);
	}

	public static List<Person> getUsers() {
		return users;
	}
}
