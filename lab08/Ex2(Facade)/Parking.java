import java.util.ArrayList;
import java.util.List;

public class Parking {
	private static List<Person> allowedList = new ArrayList<>();
	
	public static void allow(Person p) {
		allowedList.add(p);
	}

	public static List<Person> getAllowedList() {
		return allowedList;
	}
}
