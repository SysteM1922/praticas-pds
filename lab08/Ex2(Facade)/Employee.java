public class Employee {
	private double salary;
	private Person person;
	private Card card;

	public Employee(Person p, double s) {
		this.person = p;
		this.salary = s;
	}

	public void associate(Card c) {
		this.card = c;
	}

	public double getSalary() {
		return salary;
	}

	public Card getCard() {
		return card;
	}

	public void paySalary(double d) {
		BankAccount ba = person.getBankAccount();
		ba.deposit(d);
	}
}
