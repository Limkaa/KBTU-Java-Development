package problemFour;

public class SavingAccount extends Account{
	private final double interest = 0.1;
	
	public SavingAccount(int number) {
		super(number);
	}
	
	public void addInterest() {
		super.deposit(this.getBalance()*interest);
	}
	
	public String toString() {
		return "Account number: " + super.getAccountNumber() + "\n"
				+ "Account type: Saving account\nCurrent balance: " 
				+ super.getBalance() + "\n" + "Current interest: " + interest * 100 + "%\n";
	}
	
	
}
