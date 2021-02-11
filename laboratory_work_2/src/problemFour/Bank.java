package problemFour;
import java.util.Vector;

public class Bank {
	
	static Vector<Account> accounts = new Vector<Account>();
	
	public static void main(String args[]) {
		openAccount(new CheckingAccount(1));
		openAccount(new SavingAccount(2));
		openAccount(new Account(3));
		
		openAccount(new CheckingAccount(4));
		closeAccount(4);
		
		update();
	}
	
	public static void update() {
		for (int i = 0; i < accounts.size(); i++) {
			accounts.get(i).deposit(100);
			accounts.get(i).withdraw(30);
			
			if (accounts.get(i) instanceof SavingAccount)
				((SavingAccount)accounts.get(i)).addInterest();
			else if (accounts.get(i) instanceof CheckingAccount) 
				((CheckingAccount)accounts.get(i)).transfer(50, accounts.get(2));
			
			accounts.get(i).print();
		}
	}
	
	public static void closeAccount(int number) {
		for (int i = 0; i < accounts.size(); i++)
			if (accounts.get(i).getAccountNumber() == number)
				accounts.remove(i);	
	}
	
	public static void openAccount(Account account) {
		accounts.add(account);
	}
				
}

