package problemFour;

public class CheckingAccount extends Account{
	private int transactionsNum;
	private final int FREE_TRANSACTIONS = 2;
	
	public CheckingAccount(int number) {
		super(number);
		transactionsNum = 0;
	}
	
	public void deposit(double sum) {
		super.deposit(sum);
		deductFee();			
	}
	public void withdraw(double sum) {
		if (transactionsNum < 2) {
			super.withdraw(sum);
			deductFee();
		}
		else if (super.getBalance() - 0.02 >= sum) {
			super.withdraw(sum);
			deductFee();
		}
		else {
			System.out.println("Недостаточно средств, пополните баланс.");
		}
	}
	public void transfer(double amount, Account account) {
		if (transactionsNum < 2) {
			super.withdraw(amount);
			account.deposit(amount);
			deductFee();
		}
		else if (super.getBalance() - 0.02 >= amount) {
			super.withdraw(amount);
			account.deposit(amount);
			deductFee();
		}
		else {
			System.out.println("Недостаточно средств, пополните баланс.");
		}
	}
	public void deductFee() {
		transactionsNum ++;
		if (transactionsNum > FREE_TRANSACTIONS)
			super.withdraw(0.02);
	}
	public String toString() {
		return "Account number: " + super.getAccountNumber() + "\n"
				+ "Account type: Checking account\nCurrent balance: " 
				+ super.getBalance() + "\nNumber of transactions:" + transactionsNum + "\n";
	}	
}
