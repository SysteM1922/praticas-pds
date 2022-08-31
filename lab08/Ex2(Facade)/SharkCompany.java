import java.util.List;

public class SharkCompany {
	public static void main(String[] args) {
		Person[] persons = { new Person("Maria Silva"),new Person("Manuel Pereira"),new Person("Aurora Machado"),new Person("Augusto Lima") };
		Company shark = new Company();
		Company.user = User.COMPANY;
		shark.admitEmployee(persons[0], 1000);
		shark.admitEmployee(persons[1], 900);
		shark.admitEmployee(persons[2], 1200);
		shark.admitEmployee(persons[3], 1100);
		List<Employee> sharkEmps = shark.employees();
		for (Employee e : sharkEmps)
			System.out.println(e.getSalary());
		shark.paySalaries(1);
		for (Person p : persons) {
			assert SocialSecurity.getList().contains(p);
			assert Insurance.getUsers().contains(p);
		}
		for (int i = 0; i < persons.length; i++)
			assert persons[i].equals(sharkEmps.get(i).getCard().getUser());
		assert Parking.getAllowedList().contains(persons[0]) == false;
		assert Parking.getAllowedList().contains(persons[1]) == false;
		assert Parking.getAllowedList().contains(persons[2]) == false;
		assert Parking.getAllowedList().contains(persons[3]) == true;
		System.out.println("Asserts bem sucedidos.");
	}
}