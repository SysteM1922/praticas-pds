import java.util.ArrayList;
import java.util.List;

public class SocialSecurity {
	private static List<Person> list = new ArrayList<>();

	public static void regist(Person p) {
		list.add(p);
	}

	public static List<Person> getList() {
		return list;
	}
}