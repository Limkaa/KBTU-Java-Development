package problemFour;

public class Account {
	private double balance;
	private int accNumber;
	
	public Account() {}
	public Account(int number) {
		balance = 0;
		accNumber = number;
	}
	
	public void deposit(double sum) {
		balance += sum;
	}
	public void withdraw(double sum) {
		if (sum <= balance)
			balance -= sum;
		else
			System.out.println("Недостаточно средств, пополните баланс.");
	}
	public double getBalance() {
		return balance;
	}
	public double getAccountNumber() {
		return accNumber;
	}
	public void transfer(double amount, Account account) {
		if (amount <= balance) {
			this.withdraw(amount);
			account.deposit(amount);
		}
		else {
			System.out.println("Недостаточно средств, пополните баланс.");
		}
	}
	
	public String toString () {
		return "Account number: " + this.getAccountNumber() + "\n"
				+ "Account type: Account\nCurrent balance: " 
				+ this.getBalance() + "\n";
	}
	public final void print() {
		System.out.println(toString());
	}
}
