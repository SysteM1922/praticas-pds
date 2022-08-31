import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company {
	public static User user;
	private List<Employee> emps = new ArrayList<>();

	public void admitEmployee(Person p, double salary) {
		Employee e = new Employee(p, salary);
		emps.add(e);
		SocialSecurity.regist(p);
		Insurance.regist(p);
		Card card = new Card(p);
		e.associate(card);
		double med=0;
		for(Employee emp: emps)
			med+=emp.getSalary();
		med/=emps.size();
		if(salary>med)
			Parking.allow(p);
	}

	public void paySalaries(int month) {
		for (Employee e : emps) {
			e.paySalary(e.getSalary());
		}
	}

	public List<Employee> employees() {
		return Collections.unmodifiableList(emps);
	}
}