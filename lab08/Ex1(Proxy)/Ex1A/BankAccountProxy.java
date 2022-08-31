public class BankAccountProxy implements BankAccount {
	private BankAccountImpl bank;
	
	public BankAccountProxy(String bank, double initialDeposit) {
		this.bank = new BankAccountImpl(bank, initialDeposit);
	}

	public BankAccountImpl getBank() {
		return bank;
	}

	@Override
	public void deposit(double amount) {
		this.bank.deposit(amount);
	}

	@Override
	public boolean withdraw(double amount) {
		if (Company.user == User.OWNER)
			return this.bank.withdraw(amount);
		System.out.println("Utilizador não autorizado!");
		return false;
	}

	@Override
	public double balance() {
		if (Company.user == User.OWNER)
			return this.bank.balance();
		System.out.println("Utilizador não autorizado!");
		return Double.NaN;
	}
	
}
