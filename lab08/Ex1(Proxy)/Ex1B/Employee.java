public class Employee {
	private double salary;
	private Person person;

	public Employee(Person p, double s) {
		this.person = p;
		this.salary = s;
	}

	public double getSalary() {
		return salary;
	}

	public void paySalary(double d) {
		BankAccount ba = person.getBankAccount();
		ba.deposit(d);
	}
}
